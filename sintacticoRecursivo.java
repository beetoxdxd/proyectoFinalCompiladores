import java.beans.Expression;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class sintacticoRecursivo implements Parser {
    private int i = 0;
    private boolean hayErrores = false;
    private Token preanalisis;
    private final List<Token> tokens;
    private List<Statement> statements;

    public sintacticoRecursivo(List<Token> tokens){
        this.tokens = tokens;
        preanalisis = this.tokens.get(i);
    }

    @Override
    public boolean parse() {
        program();

        if(preanalisis.tipo == TipoToken.EOF && !hayErrores) {
            System.out.println("Resultado correcto, no se encontraron errores.");
            return true;
        }

        System.out.println("ERROR ENCONTRADO AL FINAL.");
        return false;
    }

    // PROGRAM -> DECLARATION (Al final se convierte en una lista de statements)
    private List<Statement> program(){
        declaration(statements);
        return statements;
    }

    // DECLARATION -> FUN_DECL DECLARATION (recibe una lista de statements)
    private void declaration(List<Statement> statements){

    }

    private Expression unary(){
        if(hayErrores) return null;

        if(preanalisis.tipo == TipoToken.BANG){
            match(TipoToken.BANG);
            return new ExprUnary(previous(), unary());
        } else if(preanalisis.tipo == TipoToken.MINUS){
            match(TipoToken.MINUS);
            return new ExprUnary(previous(), unary())
        } else if(preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL ||
                preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER ||
                preanalisis.tipo == TipoToken.LEFT_PAREN){
            return call();
        }

        hayErrores = true;
        System.out.println("ERROR ENCONTRADO: Se esperaba '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '('");
        return null;
    }

    private Expression call(){
        if(hayErrores) return null;

        if(preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL ||
                preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER ||
                preanalisis.tipo == TipoToken.LEFT_PAREN)
            return call_2(primary());


        hayErrores = true;
        System.out.println("ERROR ENCONTRADO: Se esperaba 'true', 'false', 'null', 'number', 'string', 'id' o '('");
        return null;
    }

    private Expression call_2(Expression callName){
        if(hayErrores) return null;

        if(preanalisis.tipo == TipoToken.LEFT_PAREN){
            match(TipoToken.LEFT_PAREN);
            List<Expression> arg = arguments_opc();
            match(TipoToken.RIGHT_PAREN);
            return new ExprCallFunction(callName, arg);
        }

        return callName;
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
            return ExprGrouping(e);
        }

        hayErrores = true;
        System.out.println("ERROR ENCONTRADO: Se esperaba 'true', 'false', 'null', 'number', 'string', 'id' o '('");
        return null;
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

        return args;
    }

    // D -> distinct P | P
    private void D(){
        if(hayErrores)
            return;

        if(preanalisis.tipo == TipoToken.DISTINCT){
            match(TipoToken.DISTINCT);
            P();
        }
        else{
            P();
        }
    }

    // P -> * | A
    private void P(){
        if(hayErrores)
            return;

        if(preanalisis.tipo == TipoToken.ASTERISCO){
            match(TipoToken.ASTERISCO);
        }
        else{
            A();
        }
    }

    // A -> A2 A1
    private void A(){
        if(hayErrores)
            return;

        A2();
        A1();
    }

    // A2 -> id A3
    private void A2(){
        if(hayErrores)
            return;

        if(preanalisis.tipo == TipoToken.IDENTIFICADOR){
            match(TipoToken.IDENTIFICADOR);
            A3();
        }
        else{
            hayErrores = true;
            System.out.println("Se esperaba un 'identificador'");
        }
    }

    // A1 -> ,A | Ɛ
    private void A1(){
        if(hayErrores)
            return;

        if(preanalisis.tipo == TipoToken.COMA){
            match(TipoToken.COMA);
            A();
        }
    }

    // A3 -> . id | Ɛ
    private void A3(){
        if(hayErrores)
            return;

        if(preanalisis.tipo == TipoToken.PUNTO){
            match(TipoToken.PUNTO);
            match(TipoToken.IDENTIFICADOR);
        }
    }

    // T -> T2 T1
    private void T(){
        if(hayErrores)
            return;

        T2();
        T1();
    }

    // T1 -> , T | Ɛ
    private void T1(){
        if(hayErrores)
            return;

        if(preanalisis.tipo == TipoToken.COMA){
            match(TipoToken.COMA);
            T();
        }
    }

    // T2 -> id T3
    private void T2(){
        if(hayErrores)
            return;

        if(preanalisis.tipo == TipoToken.IDENTIFICADOR){
            match(TipoToken.IDENTIFICADOR);
            T3();
        }
        else{
            hayErrores=true;
            System.out.println("Se esperaba un identificador");
        }
    }

    // T3 -> id | Ɛ
    private void T3(){
        if(hayErrores)
            return;

        if(preanalisis.tipo == TipoToken.IDENTIFICADOR){
            match(TipoToken.IDENTIFICADOR);
        }
    }

    private void match(TipoToken tt){
        if(preanalisis.tipo == tt){
            i++;
            preanalisis = tokens.get(i);
        }
        else{
            hayErrores = true;
            System.out.println("Error encontrado");
        }
    }

    private Token previous() {
        return this.tokens.get(i - 1);
    }
}