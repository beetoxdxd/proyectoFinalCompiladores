import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class sintacticoRecursivo implements Parser {
    private int i = 0;
    private boolean hayErrores = false;
    private Token preanalisis;
    private final List<Token> tokens;
    private final List<Statement> statements;

    public sintacticoRecursivo(List<Token> tokens){
        this.tokens = tokens;
        preanalisis = this.tokens.get(i);
        statements = new ArrayList<>();
    }

    @Override
    public boolean parse() {
        List<Statement> stmt = program();

        if(preanalisis.tipo == TipoToken.EOF && !hayErrores) {
            System.out.println("\nANÁLISIS SINTÁCTICO CORRECTO: No se encontraron errores.\n");
            assert stmt != null;
            stmt.forEach(System.out::println);
            return true;
        }

        System.out.println("\nERROR ENCONTRADO.");
        return false;
    }

    // PROGRAM -> DECLARATION (Al final se convierte en una lista de statements)
    private List<Statement> program(){
        if(hayErrores) return null;

        declaration(statements);
        return statements;
    }

    // DECLARATION -> FUN_DECL DECLARATION (recibe una lista de statements)
    private void declaration(List<Statement> stmts){
        if(hayErrores) return ;

        if(preanalisis.tipo == TipoToken.FUN){
            stmts.add(fun_decl());
            declaration(stmts);
        } else if(preanalisis.tipo == TipoToken.VAR){
            stmts.add(var_decl());
            declaration(stmts);
        } else if(preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL ||
                preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER ||
                preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS ||
                preanalisis.tipo == TipoToken.FOR || preanalisis.tipo == TipoToken.IF || preanalisis.tipo == TipoToken.PRINT ||
                preanalisis.tipo == TipoToken.RETURN || preanalisis.tipo == TipoToken.WHILE || preanalisis.tipo == TipoToken.LEFT_BRACE){
            Statement st = statement();
            stmts.add(st);
            declaration(stmts);
        }

        return ; // En caso de que DECLARATION -> ε (es metodo void)
    }

    private Statement fun_decl(){
        if(hayErrores) return null;

        match(TipoToken.FUN);
        return function();
    }

    private Statement var_decl(){
        if(hayErrores) return null;

        match(TipoToken.VAR);
        match(TipoToken.IDENTIFIER);
        StmtVar sv = new StmtVar(previous(), var_init());
        match(TipoToken.SEMICOLON);

        return sv;
    }

    private Expression var_init(){
        if(hayErrores) return null;

        if(preanalisis.tipo == TipoToken.EQUAL){
            match(TipoToken.EQUAL);
            return expression();
        }

        return null; // En caso de que VAR_INIT -> ε
    }

    private Statement statement(){
        if(hayErrores) return null;

        if(preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL ||
                preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER ||
                preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS){
            return expr_stmt();
        } else if(preanalisis.tipo == TipoToken.FOR){
            return for_stmt();
        } else if(preanalisis.tipo == TipoToken.IF){
            return if_stmt();
        } else if(preanalisis.tipo == TipoToken.PRINT){
            return print_stmt();
        } else if(preanalisis.tipo == TipoToken.RETURN){
            return return_stmt();
        } else if(preanalisis.tipo == TipoToken.WHILE){
            return while_stmt();
        } else if(preanalisis.tipo == TipoToken.LEFT_BRACE){
            return block();
        }

        hayErrores = true;
        System.out.println("ERROR SINTÁCTICO ENCONTRADO: Se esperaba 'for', 'if', 'print', 'return', 'while', '{', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '('");
        System.out.println("\tSe encontró: " + preanalisis.tipo);
        return null;
    }

    private Statement expr_stmt(){
        if(hayErrores) return null;

        StmtExpression se = new StmtExpression(expression());
        match(TipoToken.SEMICOLON);
        return se;
    }

    private Statement for_stmt(){
        if(hayErrores) return null;

        match(TipoToken.FOR);
        match(TipoToken.LEFT_PAREN);
        Statement for1 = for_stmt_1();
        Expression for2 = for_stmt_2();
        Expression for3 = for_stmt_3();
        match(TipoToken.RIGHT_PAREN);
        Statement body = statement();

        if(for3 != null){
            StmtExpression se = new StmtExpression(for3);
            body = new StmtBlock(Arrays.asList(body, se));
        }
        body = new StmtLoop(for2, body);
        if(for1 != null) body = new StmtBlock(Arrays.asList(for1, body));

        return body;
    }

    private Statement for_stmt_1(){
        if(hayErrores) return null;

        if(preanalisis.tipo == TipoToken.VAR){
            return var_decl();
        } else if(preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL ||
                preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER ||
                preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS){
            return expr_stmt();
        } else if(preanalisis.tipo == TipoToken.SEMICOLON)
            return null;

        hayErrores = true;
        System.out.println("ERROR SINTÁCTICO ENCONTRADO: Se esperaba 'var', ';', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '('");
        return null;
    }

    private Expression for_stmt_2() {
        if (hayErrores) return null;

        if(preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL ||
                preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER ||
                preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS) {
            Expression exp = expression();
            match(TipoToken.SEMICOLON);
            return exp;
        } else if(preanalisis.tipo == TipoToken.SEMICOLON){
            match(TipoToken.SEMICOLON);
            return null;
        }

        hayErrores = true;
        System.out.println("ERROR SINTÁCTICO ENCONTRADO: Se esperaba ';', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '('");
        return null;
    }

    private Expression for_stmt_3(){
        if(hayErrores) return null;

        if(preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL ||
                preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER ||
                preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS) {
            return expression();
        }

        return null;
    }

    private Statement if_stmt(){
        if(hayErrores) return null;

        match(TipoToken.IF);
        match(TipoToken.LEFT_PAREN);
        Expression exp = expression();
        match(TipoToken.RIGHT_PAREN);
        Statement then = statement();
        Statement els = else_statement();

        return new StmtIf(exp, then, els);
    }

    private Statement else_statement(){
        if(hayErrores) return null;

        if(preanalisis.tipo == TipoToken.ELSE){
            match(TipoToken.ELSE);
            return statement();
        }

        return null;
    }

    private Statement print_stmt(){
        if(hayErrores) return null;

        match(TipoToken.PRINT);
        Expression exp = expression();
        match(TipoToken.SEMICOLON);
        return new StmtPrint(exp);
    }

    private Statement return_stmt(){
        if(hayErrores) return null;

        match(TipoToken.RETURN);
        StmtReturn sr = new StmtReturn(return_exp_opc());
        match(TipoToken.SEMICOLON);

        return sr;
    }

    private Expression return_exp_opc(){
        if(hayErrores) return null;

        if(preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL ||
                preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER ||
                preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS){
            return expression();
        }

        return null;
    }

    private Statement while_stmt(){
        if(hayErrores) return null;

        match(TipoToken.WHILE);
        match(TipoToken.LEFT_PAREN);
        Expression exp = expression();
        match(TipoToken.RIGHT_PAREN);
        Statement body = statement();

        return new StmtLoop(exp, body);
    }

    private Statement block(){
        if(hayErrores) return null;

        match(TipoToken.LEFT_BRACE);
        List<Statement> st = new ArrayList<>();
        declaration(st);
        StmtBlock sb = new StmtBlock(st);
        match(TipoToken.RIGHT_BRACE);

        return sb;
    }

    private Expression expression(){
        if(hayErrores) return null;

        return assignment();
    }

    private Expression assignment(){
        if(hayErrores) return null;
        return assignment_opc(logic_or());
    }

    private Expression assignment_opc(Expression value){
        if(hayErrores) return null;

        if(preanalisis.tipo == TipoToken.EQUAL){
            match(TipoToken.EQUAL);
            return new ExprAssign(tokens.get(i-2), expression());
        }

        return value; // En caso de que ASSIGNMENT_OPC -> ε
    }

    private Expression logic_or(){
        if(hayErrores) return null;
        return logic_or_2(logic_and());
    }

    private Expression logic_or_2(Expression left){
        if(hayErrores) return null;

        if(preanalisis.tipo == TipoToken.OR){
            match(TipoToken.OR);
            ExprLogical el = new ExprLogical(left, previous(), logic_and());
            return logic_or_2(el);
        }

        return left; // En caso de que LOGIC_OR_2 -> ε
    }

    private Expression logic_and(){
        if(hayErrores) return null;
        return logic_and_2(equality());
    }

    private Expression logic_and_2(Expression left){
        if(hayErrores) return null;

        if(preanalisis.tipo == TipoToken.AND){
            match(TipoToken.AND);
            ExprLogical el = new ExprLogical(left, previous(), equality());
            return logic_and_2(el);
        }

        return left; // En caso de que LOGIC_AND_2 -> ε
    }

    private Expression equality(){
        if(hayErrores) return null;
        return equality_2(comparison());
    }

    private Expression equality_2(Expression left){
        if(hayErrores) return null;

        if(preanalisis.tipo == TipoToken.BANG_EQUAL){
            match(TipoToken.BANG_EQUAL);
            ExprBinary eb = new ExprBinary(left, previous(), comparison());
            return equality_2(eb);
        } else if(preanalisis.tipo == TipoToken.EQUAL_EQUAL){
            match(TipoToken.EQUAL_EQUAL);
            ExprBinary eb = new ExprBinary(left, previous(), comparison());
            return equality_2(eb);
        }

        return left; // En caso de que EQUALITY_2 -> ε
    }

    private Expression comparison(){
        if(hayErrores) return null;
        return comparison_2(term());
    }

    private Expression comparison_2(Expression left){
        if(hayErrores) return null;

        if(preanalisis.tipo == TipoToken.GREATER){
            match(TipoToken.GREATER);
            ExprBinary eb = new ExprBinary(left, previous(), term());
            return comparison_2(eb);
        } else if(preanalisis.tipo == TipoToken.GREATER_EQUAL){
            match(TipoToken.GREATER_EQUAL);
            ExprBinary eb = new ExprBinary(left, previous(), term());
            return comparison_2(eb);
        } else if(preanalisis.tipo == TipoToken.LESS){
            match(TipoToken.LESS);
            ExprBinary eb = new ExprBinary(left, previous(), term());
            return comparison_2(eb);
        } else if(preanalisis.tipo == TipoToken.LESS_EQUAL){
            match(TipoToken.LESS_EQUAL);
            ExprBinary eb = new ExprBinary(left, previous(), term());
            return comparison_2(eb);
        }

        return left; // En caso de que COMPARISON_2 -> ε
    }

    private Expression term(){
        if(hayErrores) return null;
        return term_2(factor());
    }

    private Expression term_2(Expression left){
        if(hayErrores) return null;

        if(preanalisis.tipo == TipoToken.MINUS){
            match(TipoToken.MINUS);
            ExprBinary eb = new ExprBinary(left, previous(), factor());
            return term_2(eb);
        } else if(preanalisis.tipo == TipoToken.PLUS){
            match(TipoToken.PLUS);
            ExprBinary eb = new ExprBinary(left, previous(), factor());
            return term_2(eb);
        }

        return left; // En caso de que TERM_2 -> ε
    }

    private Expression factor(){
        if(hayErrores) return null;
        return factor_2(unary());
    }

    private Expression factor_2(Expression left){
        if(hayErrores) return null;

        if(preanalisis.tipo == TipoToken.SLASH){
            match(TipoToken.SLASH);
            ExprBinary eb = new ExprBinary(left, previous(), unary());
            return factor_2(eb);
        } else if(preanalisis.tipo == TipoToken.STAR){
            match(TipoToken.STAR);
            ExprBinary eb = new ExprBinary(left, previous(), unary());
            return factor_2(eb);
        }

        return left; // En caso de que FACTOR_2 -> ε
    }

    private Expression unary(){
        if(hayErrores) return null;

        if(preanalisis.tipo == TipoToken.BANG){
            match(TipoToken.BANG);
            return new ExprUnary(previous(), unary());
        } else if(preanalisis.tipo == TipoToken.MINUS){
            match(TipoToken.MINUS);
            return new ExprUnary(previous(), unary());
        } else if(preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL ||
                preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER ||
                preanalisis.tipo == TipoToken.LEFT_PAREN){
            return call();
        }

        errorSintactico();
        System.out.println("\tSe encontró: " + preanalisis.tipo);
        return null;
    }

    private Expression call(){
        if(hayErrores) return null;
        return call_2(primary());
    }

    private Expression call_2(Expression callName){
        if(hayErrores) return null;

        if(preanalisis.tipo == TipoToken.LEFT_PAREN){
            match(TipoToken.LEFT_PAREN);
            List<Expression> arg = arguments_opc();
            match(TipoToken.RIGHT_PAREN);
            return new ExprCallFunction(callName, arg);
        }

        return callName; // En caso de que CALL_2 -> ε
    }
    private Expression primary(){
        if(hayErrores) return null;

        if(preanalisis.tipo == TipoToken.TRUE){
            match(TipoToken.TRUE); return new ExprLiteral(true);
        } else if(preanalisis.tipo == TipoToken.FALSE){
            match(TipoToken.FALSE); return new ExprLiteral(false);
        } else if(preanalisis.tipo == TipoToken.NULL){
                match(TipoToken.NULL); return new ExprLiteral(null);
        } else if(preanalisis.tipo == TipoToken.NUMBER){
            match(TipoToken.NUMBER); return new ExprLiteral(previous().literal);
        } else if(preanalisis.tipo == TipoToken.STRING){
            match(TipoToken.STRING); return new ExprLiteral(previous().literal);
        } else if(preanalisis.tipo == TipoToken.IDENTIFIER){
            match(TipoToken.IDENTIFIER); return new ExprVariable(previous());
        } else if(preanalisis.tipo == TipoToken.LEFT_PAREN){
            match(TipoToken.LEFT_PAREN);
            Expression e = expression();
            match(TipoToken.RIGHT_PAREN);
            return new ExprGrouping(e);
        }

        hayErrores = true;
        System.out.println("ERROR SINTÁCTICO ENCONTRADO: Se esperaba 'true', 'false', 'null', 'number', 'string', 'id' o '('");
        System.out.println("\tSe encontró: " + preanalisis.tipo);
        return null;
    }

    private Statement function(){
        if(hayErrores) return null;

        match(TipoToken.IDENTIFIER);
        Token name = previous();
        match(TipoToken.LEFT_PAREN);
        List<Token> params = parameters_opc();
        match(TipoToken.RIGHT_PAREN);

        return new StmtFunction(name, params, (StmtBlock) block());
    }

    private List<Token> parameters_opc(){
        if(hayErrores) return null;

        List<Token> params = new ArrayList<>();
        if(preanalisis.tipo == TipoToken.RIGHT_PAREN) return params; // En caso de que no haya parametros

        return parameters();
    }

    private List<Token> parameters(){
        if(hayErrores) return null;

        match(TipoToken.IDENTIFIER);
        List<Token> params = new ArrayList<>();
        params.add(previous());

        return parameters_2(params);
    }

    private List<Token> parameters_2(List<Token> list){
        if(hayErrores) return null;

        if(preanalisis.tipo == TipoToken.COMMA){
            match(TipoToken.COMMA);
            match(TipoToken.IDENTIFIER);
            list.add(previous());
            parameters_2(list);
        }

        return list;
    }

    private List<Expression> arguments_opc(){
        if(hayErrores) return null;

        List<Expression> exp = new ArrayList<>();
        if(preanalisis.tipo == TipoToken.RIGHT_PAREN) return exp; // En caso de que no haya argumentos
        exp.add(expression());
        return arguments(exp);
    }

    private List<Expression> arguments(List<Expression> args){ // Recibe una lista de expresiones que son los argumentos
        if(hayErrores) return null;

        if(preanalisis.tipo == TipoToken.COMMA){
            match(TipoToken.COMMA);
            args.add(expression());
            arguments(args);
        }

        return args; // En caso de que ARGUMENTS -> ε
    }



    private void match(TipoToken tt){
        if(preanalisis.tipo == tt){
            i++;
            preanalisis = tokens.get(i);
        }
        else{
            hayErrores = true;
            System.out.println("ERROR SINTÁCTICO ENCONTRADO: Se esperaba " + tt + " pero se encontró " + preanalisis.tipo);
        }
    }

    private Token previous() {
        return this.tokens.get(i - 1);
    }

    private void errorSintactico(){
        hayErrores = true;
        System.out.println("ERROR SINTÁCTICO ENCONTRADO: Se esperaba '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '('");
    }
}