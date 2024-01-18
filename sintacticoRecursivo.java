import java.util.*;

public class sintacticoRecursivo implements Parser {
    private int i = 0;
    private boolean hayErrores = false;
    private boolean semantico = false;
    private List<String> dentroFuncion;
    private Token preanalisis;
    private final List<Token> tokens;
    private final List<Statement> statements;
    private tablaSimbolos tabla;
    private Object value = null;
    private Stack valLeft;
    private Stack identificadores;

    public sintacticoRecursivo(List<Token> tokens){
        this.tokens = tokens;
        preanalisis = this.tokens.get(i);
        statements = new ArrayList<>();
        tabla = new tablaSimbolos();
        dentroFuncion = new ArrayList<>();
        valLeft = new Stack<>();
        identificadores = new Stack<>();
    }

    @Override
    public boolean parse() {
        List<Statement> stmt = program();
        boolean f1 = false, f2 = false;

        if(preanalisis.tipo == TipoToken.EOF && !hayErrores) {
            System.out.println("\nANÁLISIS SINTÁCTICO CORRECTO: No se encontraron errores.\n");
            assert stmt != null;
            stmt.forEach(System.out::println);
            f1 = true;
        }

        if(!semantico){
            System.out.println("\nANÁLISIS SEMÁNTICO CORRECTO: No se encontraron errores.\n");
            System.out.println(tabla.toString());
            f2 = true;
        }

        if(f1 && f2) return true;
        System.out.println("\nERROR ENCONTRADO.");
        return false;
    }

    // PROGRAM -> DECLARATION (Al final se convierte en una lista de statements)
    private List<Statement> program(){
        if(hayErrores) return null;

        declaration(statements, tabla);
        return statements;
    }

    // DECLARATION -> FUN_DECL DECLARATION (recibe una lista de statements)
    private void declaration(List<Statement> stmts, tablaSimbolos t){
        if(hayErrores) return ;

        if(preanalisis.tipo == TipoToken.FUN){
            stmts.add(fun_decl(t));
            declaration(stmts, t);
        } else if(preanalisis.tipo == TipoToken.VAR){
            stmts.add(var_decl(t));
            declaration(stmts, t);
        } else if(preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL ||
                preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER ||
                preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS ||
                preanalisis.tipo == TipoToken.FOR || preanalisis.tipo == TipoToken.IF || preanalisis.tipo == TipoToken.PRINT ||
                preanalisis.tipo == TipoToken.RETURN || preanalisis.tipo == TipoToken.WHILE || preanalisis.tipo == TipoToken.LEFT_BRACE){
            stmts.add(statement(t));
            declaration(stmts, t);
        }

        return ; // En caso de que DECLARATION -> ε (es metodo void)
    }

    private Statement fun_decl(tablaSimbolos t){
        if(hayErrores) return null;

        match(TipoToken.FUN);
        return function(t);
    }

    private Statement var_decl(tablaSimbolos t){
        if(hayErrores) return null;

        match(TipoToken.VAR);
        match(TipoToken.IDENTIFIER);
        String id = previous().lexema;
        StmtVar sv = new StmtVar(previous(), var_init(t));
        match(TipoToken.SEMICOLON);

        if(!t.existeIdentificador(id)){
            t.asignar(id, value);
            //System.out.println(value);
        } else {
            semantico = true;
            System.out.println("ERROR SEMÁNTICO ENCONTRADO: La variable " + id + " ya se había declarado.");
        }

        return sv;
    }

    private Expression var_init(tablaSimbolos t){
        if(hayErrores) return null;

        if(preanalisis.tipo == TipoToken.EQUAL){
            match(TipoToken.EQUAL);
            Expression exp = expression(t);
            return exp;
        }

        return null; // En caso de que VAR_INIT -> ε
    }

    private Statement statement(tablaSimbolos t){
        if(hayErrores) return null;

        if(preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL ||
                preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER ||
                preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS){
            return expr_stmt(t);
        } else if(preanalisis.tipo == TipoToken.FOR){
            return for_stmt(t);
        } else if(preanalisis.tipo == TipoToken.IF){
            return if_stmt(t);
        } else if(preanalisis.tipo == TipoToken.PRINT){
            return print_stmt(t);
        } else if(preanalisis.tipo == TipoToken.RETURN){
            return return_stmt(t);
        } else if(preanalisis.tipo == TipoToken.WHILE){
            return while_stmt(t);
        } else if(preanalisis.tipo == TipoToken.LEFT_BRACE){
            return block(t);
        }

        hayErrores = true;
        System.out.println("ERROR SINTÁCTICO ENCONTRADO: Se esperaba 'for', 'if', 'print', 'return', 'while', '{', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '('");
        System.out.println("\tSe encontró: " + preanalisis.tipo);
        return null;
    }

    private Statement expr_stmt(tablaSimbolos t){
        if(hayErrores) return null;

        StmtExpression se = new StmtExpression(expression(t));
        match(TipoToken.SEMICOLON);
        return se;
    }

    private Statement for_stmt(tablaSimbolos t){
        if(hayErrores) return null;

        match(TipoToken.FOR);
        match(TipoToken.LEFT_PAREN);
        Statement for1 = for_stmt_1(t);
        Expression for2 = for_stmt_2(t);
        Expression for3 = for_stmt_3(t);
        match(TipoToken.RIGHT_PAREN);
        Statement body = statement(t);

        if(for3 != null){
            StmtExpression se = new StmtExpression(for3);
            body = new StmtBlock(Arrays.asList(body, se));
        }
        body = new StmtLoop(for2, body);
        if(for1 != null) body = new StmtBlock(Arrays.asList(for1, body));

        return body;
    }

    private Statement for_stmt_1(tablaSimbolos t){
        if(hayErrores) return null;

        if(preanalisis.tipo == TipoToken.VAR){
            return var_decl(t);
        } else if(preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL ||
                preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER ||
                preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS){
            return expr_stmt(t);
        } else if(preanalisis.tipo == TipoToken.SEMICOLON)
            return null;

        hayErrores = true;
        System.out.println("ERROR SINTÁCTICO ENCONTRADO: Se esperaba 'var', ';', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '('");
        return null;
    }

    private Expression for_stmt_2(tablaSimbolos t) {
        if (hayErrores) return null;

        if(preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL ||
                preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER ||
                preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS) {
            Expression exp = expression(t);
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

    private Expression for_stmt_3(tablaSimbolos t){
        if(hayErrores) return null;

        if(preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL ||
                preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER ||
                preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS) {
            return expression(t);
        }

        return null;
    }

    private Statement if_stmt(tablaSimbolos t){
        if(hayErrores) return null;

        match(TipoToken.IF);
        match(TipoToken.LEFT_PAREN);
        Expression exp = expression(t);
        match(TipoToken.RIGHT_PAREN);
        Statement then = statement(t);
        Statement els = else_statement(t);

        return new StmtIf(exp, then, els);
    }

    private Statement else_statement(tablaSimbolos t){
        if(hayErrores) return null;

        if(preanalisis.tipo == TipoToken.ELSE){
            match(TipoToken.ELSE);
            return statement(t);
        }

        return null;
    }

    private Statement print_stmt(tablaSimbolos t){
        if(hayErrores) return null;

        match(TipoToken.PRINT);
        Expression exp = expression(t);
        match(TipoToken.SEMICOLON);
        return new StmtPrint(exp);
    }

    private Statement return_stmt(tablaSimbolos t){
        if(hayErrores) return null;

        match(TipoToken.RETURN);
        StmtReturn sr = new StmtReturn(return_exp_opc(t));
        match(TipoToken.SEMICOLON);

        return sr;
    }

    private Expression return_exp_opc(tablaSimbolos t){
        if(hayErrores) return null;

        if(preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL ||
                preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER ||
                preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS){
            return expression(t);
        }

        return null;
    }

    private Statement while_stmt(tablaSimbolos t){
        if(hayErrores) return null;

        match(TipoToken.WHILE);
        match(TipoToken.LEFT_PAREN);
        Expression exp = expression(t);
        match(TipoToken.RIGHT_PAREN);
        Statement body = statement(t);

        return new StmtLoop(exp, body);
    }

    private Statement block(tablaSimbolos t){
        if(hayErrores) return null;

        match(TipoToken.LEFT_BRACE);
        List<Statement> st = new ArrayList<>();
        declaration(st, t);
        StmtBlock sb = new StmtBlock(st);
        match(TipoToken.RIGHT_BRACE);

        return sb;
    }

    private Expression expression(tablaSimbolos t){
        if(hayErrores) return null;

        return assignment(t);
    }

    private Expression assignment(tablaSimbolos t){
        if(hayErrores) return null;
        return assignment_opc(logic_or(t), t);
    }

    private Expression assignment_opc(Expression val, tablaSimbolos t){
        if(hayErrores) return null;

        if(preanalisis.tipo == TipoToken.EQUAL){
            match(TipoToken.EQUAL);
            Token id = tokens.get(i-2);
            Expression e = expression(t);
            // Verificar que el identificador ha sido declarado y asignarle su valor

            if(!t.existeIdentificador(id.lexema)){
                System.out.println("ERROR SEMÁNTICO ENCONTRADO: La variable " + id.lexema + " NO se ha declarado.");
                semantico = true;
            } else {
                t.asignar(id.lexema, value);
            }

            return new ExprAssign(id, e);
        }

        return val; // En caso de que ASSIGNMENT_OPC -> ε
    }

    private Expression logic_or(tablaSimbolos t){
        if(hayErrores) return null;
        return logic_or_2(logic_and(t), t);
    }

    private Expression logic_or_2(Expression left, tablaSimbolos t){
        if(hayErrores) return null;

        if(preanalisis.tipo == TipoToken.OR){
            match(TipoToken.OR);
            Token operador = previous();
            valLeft.push(value);
            String aux = "";
            if(!identificadores.isEmpty()) aux = (String) identificadores.peek();
            Expression la = logic_and(t);
            boolean nsh = false;

            if(!identificadores.isEmpty()){
                if(value == null && dentroFuncion.contains(aux) && dentroFuncion.contains(identificadores.peek())) nsh = true;
            }

            if(!nsh){
                if(value instanceof Boolean){
                    if(valLeft.peek() instanceof Boolean){
                        Boolean l = (Boolean) valLeft.pop();
                        Boolean bol = (Boolean) value;

                        if(bol || l) value = true;
                        else value = false;
                    } else {
                        System.out.println("ERROR SEMÁNTICO ENCONTRADO: No se puede realizar la operación " + operador.lexema + " con el dato " + valLeft.pop());
                        semantico = true;
                    }
                } else {
                    System.out.println("ERROR SEMÁNTICO ENCONTRADO: No se puede aplicar el operando " + operador.lexema + " a " + value);
                    semantico = true;
                }
            }

            ExprLogical el = new ExprLogical(left, operador, la);
            return logic_or_2(el, t);
        }

        return left; // En caso de que LOGIC_OR_2 -> ε
    }

    private Expression logic_and(tablaSimbolos t){
        if(hayErrores) return null;
        return logic_and_2(equality(t), t);
    }

    private Expression logic_and_2(Expression left, tablaSimbolos t){
        if(hayErrores) return null;

        if(preanalisis.tipo == TipoToken.AND){
            match(TipoToken.AND);
            Token operador = previous();
            valLeft.push(value);
            String aux = "";
            if(!identificadores.isEmpty()) aux = (String) identificadores.peek();
            Expression e = equality(t);
            boolean nsh = false;

            if(!identificadores.isEmpty()){
                if(value == null && dentroFuncion.contains(aux) && dentroFuncion.contains(identificadores.peek())) nsh = true;
            }

            if(!nsh){
                if(value instanceof Boolean){
                    if(valLeft.peek() instanceof Boolean){
                        Boolean l = (Boolean) valLeft.pop();
                        Boolean bol = (Boolean) value;

                        if(bol && l) value = true;
                        else value = false;
                    } else {
                        System.out.println("ERROR SEMÁNTICO ENCONTRADO: No se puede realizar la operación " + operador.lexema + " con el dato " + valLeft.pop());
                        semantico = true;
                    }
                } else {
                    System.out.println("ERROR SEMÁNTICO ENCONTRADO: No se puede aplicar el operando " + operador.lexema + " a " + value);
                    semantico = true;
                }
            }

            ExprLogical el = new ExprLogical(left, operador, e);
            return logic_and_2(el, t);
        }

        return left; // En caso de que LOGIC_AND_2 -> ε
    }

    private Expression equality(tablaSimbolos t){
        if(hayErrores) return null;
        return equality_2(comparison(t), t);
    }

    private Expression equality_2(Expression left, tablaSimbolos t){
        if(hayErrores) return null;

        if(preanalisis.tipo == TipoToken.BANG_EQUAL){
            match(TipoToken.BANG_EQUAL);
            Token operando = previous();
            valLeft.push(value);
            String aux = "";
            if(!identificadores.isEmpty()) aux = (String) identificadores.peek();
            Expression c = comparison(t);
            boolean nsh = false;

            if(!identificadores.isEmpty()){
                if(value == null && dentroFuncion.contains(aux) && dentroFuncion.contains(identificadores.peek())) nsh = true;
            }

            if(!nsh){
                if(value instanceof Number){
                    if(valLeft.peek() instanceof Number){
                        Number l = (Number) valLeft.pop();
                        Number num = (Number) value;
                        int entero = num.intValue(), enteroL = l.intValue();
                        float decimal = num.floatValue(), decimalL = l.floatValue();

                        if(num.intValue() == num.floatValue()){
                            if(l.intValue() == l.floatValue()) value = enteroL != entero; // Enteros los dos
                            else value = (float) decimalL != entero; // Decimal el valor de la izquierda
                        } else {
                            if(l.intValue() == l.floatValue()) value = (float) enteroL != decimal; // Entero el valor de la izquierda
                            else value = decimalL != decimal; // Decimales los dos
                        }
                    } else {
                        System.out.println("ERROR SEMÁNTICO ENCONTRADO: No se puede realizar la operación " + operando.lexema + " con el dato " + valLeft.pop());
                        semantico = true;
                    }
                } else if(value instanceof Boolean){
                    if(valLeft.peek() instanceof Boolean){
                        Boolean l = (Boolean) valLeft.pop();
                        Boolean bol = (Boolean) value;

                        if(bol != l) value = true;
                        else value = false;
                    } else {
                        System.out.println("ERROR SEMÁNTICO ENCONTRADO: No se puede realizar la operación " + operando.lexema + " con el dato " + valLeft.pop());
                        semantico = true;
                    }
                } else {
                    System.out.println("ERROR SEMÁNTICO ENCONTRADO: No se puede aplicar el operando " + operando.lexema + " a " + value);
                    semantico = true;
                }
            }

            ExprBinary eb = new ExprBinary(left, operando, c);
            return equality_2(eb, t);
        } else if(preanalisis.tipo == TipoToken.EQUAL_EQUAL){
            match(TipoToken.EQUAL_EQUAL);
            Token operando = previous();
            valLeft.push(value);
            String aux = "";
            if(!identificadores.isEmpty()) aux = (String) identificadores.peek();
            Expression c = comparison(t);
            boolean nsh = false;

            if(!identificadores.isEmpty()){
                if(value == null && dentroFuncion.contains(aux) && dentroFuncion.contains(identificadores.peek())) nsh = true;
            }

            if(!nsh){
                if(value instanceof Number){
                    if(valLeft.peek() instanceof Number){
                        Number l = (Number) valLeft.pop();
                        Number num = (Number) value;
                        int entero = num.intValue(), enteroL = l.intValue();
                        float decimal = num.floatValue(), decimalL = l.floatValue();

                        if(num.intValue() == num.floatValue()){
                            if(l.intValue() == l.floatValue()) value = enteroL == entero; // Enteros los dos
                            else value = (float) decimalL == entero; // Decimal el valor de la izquierda
                        } else {
                            if(l.intValue() == l.floatValue()) value = (float) enteroL == decimal; // Entero el valor de la izquierda
                            else value = decimalL == decimal; // Decimales los dos
                        }
                    } else {
                        System.out.println("ERROR SEMÁNTICO ENCONTRADO: No se puede realizar la operación " + operando.lexema + " con el dato " + valLeft.pop());
                        semantico = true;
                    }
                } else if(value instanceof Boolean){
                    if(valLeft.peek() instanceof Boolean){
                        Boolean l = (Boolean) valLeft.pop();
                        Boolean bol = (Boolean) value;

                        if(bol == l) value = true;
                        else value = false;
                    } else {
                        System.out.println("ERROR SEMÁNTICO ENCONTRADO: No se puede realizar la operación " + operando.lexema + " con el dato " + valLeft.pop());
                        semantico = true;
                    }
                } else {
                    System.out.println("ERROR SEMÁNTICO ENCONTRADO: No se puede aplicar el operando " + operando.lexema + " a " + value);
                    semantico = true;
                }
            }

            ExprBinary eb = new ExprBinary(left, operando, c);
            return equality_2(eb, t);
        }

        return left; // En caso de que EQUALITY_2 -> ε
    }

    private Expression comparison(tablaSimbolos t){
        if(hayErrores) return null;
        return comparison_2(term(t), t);
    }

    private Expression comparison_2(Expression left, tablaSimbolos ta){
        if(hayErrores) return null;

        if(preanalisis.tipo == TipoToken.GREATER){
            match(TipoToken.GREATER);
            Token operando = previous();
            valLeft.push(value);
            String aux = "";
            if(!identificadores.isEmpty()) aux = (String) identificadores.peek();
            Expression t = term(ta);
            boolean nsh = false;

            if(!identificadores.isEmpty()){
                if(value == null && dentroFuncion.contains(aux) && dentroFuncion.contains(identificadores.peek())) nsh = true;
            }

            if(!nsh){
                if(value instanceof Number){
                    if(valLeft.peek() instanceof Number){
                        Number l = (Number) valLeft.pop();
                        Number num = (Number) value;
                        int entero = num.intValue(), enteroL = l.intValue();
                        float decimal = num.floatValue(), decimalL = l.floatValue();

                        if(num.intValue() == num.floatValue()){
                            if(l.intValue() == l.floatValue()) value = enteroL > entero; // Enteros los dos
                            else value = (float) decimalL > entero; // Decimal el valor de la izquierda
                        } else {
                            if(l.intValue() == l.floatValue()) value = (float) enteroL > decimal; // Entero el valor de la izquierda
                            else value = decimalL > decimal; // Decimales los dos
                        }
                    } else {
                        System.out.println("ERROR SEMÁNTICO ENCONTRADO: No se puede realizar la operación " + operando.lexema + " con el dato " + valLeft.pop());
                        semantico = true;
                    }
                } else {
                    System.out.println("ERROR SEMÁNTICO ENCONTRADO: No se puede aplicar el operando " + operando.lexema + " a " + value);
                    semantico = true;
                }
            }

            ExprBinary eb = new ExprBinary(left, operando, t);
            return comparison_2(eb, ta);
        } else if(preanalisis.tipo == TipoToken.GREATER_EQUAL){
            match(TipoToken.GREATER_EQUAL);
            Token operando = previous();
            valLeft.push(value);
            String aux = "";
            if(!identificadores.isEmpty()) aux = (String) identificadores.peek();
            Expression t = term(ta);
            boolean nsh = false;

            if(!identificadores.isEmpty()){
                if(value == null && dentroFuncion.contains(aux) && dentroFuncion.contains(identificadores.peek())) nsh = true;
            }

            if(!nsh){
                if(value instanceof Number){
                    if(valLeft.peek() instanceof Number){
                        Number l = (Number) valLeft.pop();
                        Number num = (Number) value;
                        int entero = num.intValue(), enteroL = l.intValue();
                        float decimal = num.floatValue(), decimalL = l.floatValue();

                        if(num.intValue() == num.floatValue()){
                            if(l.intValue() == l.floatValue()) value = enteroL >= entero; // Enteros los dos
                            else value = (float) decimalL >= entero; // Decimal el valor de la izquierda
                        } else {
                            if(l.intValue() == l.floatValue()) value = (float) enteroL >= decimal; // Entero el valor de la izquierda
                            else value = decimalL >= decimal; // Decimales los dos
                        }
                    } else {
                        System.out.println("ERROR SEMÁNTICO ENCONTRADO: No se puede realizar la operación " + operando.lexema + " con el dato " + valLeft.pop());
                        semantico = true;
                    }
                } else {
                    System.out.println("ERROR SEMÁNTICO ENCONTRADO: No se puede aplicar el operando " + operando.lexema + " a " + value);
                    semantico = true;
                }
            }

            ExprBinary eb = new ExprBinary(left, operando, t);
            return comparison_2(eb, ta);
        } else if(preanalisis.tipo == TipoToken.LESS){
            match(TipoToken.LESS);
            Token operando = previous();
            valLeft.push(value);
            String aux = "";
            if(!identificadores.isEmpty()) aux = (String) identificadores.peek();
            Expression t = term(ta);
            boolean nsh = false;

            if(!identificadores.isEmpty()){
                if(value == null && dentroFuncion.contains(aux) && dentroFuncion.contains(identificadores.peek())) nsh = true;
            }

            if(!nsh){
                if(value instanceof Number){
                    if(valLeft.peek() instanceof Number){
                        Number l = (Number) valLeft.pop();
                        Number num = (Number) value;
                        int entero = num.intValue(), enteroL = l.intValue();
                        float decimal = num.floatValue(), decimalL = l.floatValue();

                        if(num.intValue() == num.floatValue()){
                            if(l.intValue() == l.floatValue()) value = enteroL < entero; // Enteros los dos
                            else value = (float) decimalL < entero; // Decimal el valor de la izquierda
                        } else {
                            if(l.intValue() == l.floatValue()) value = (float) enteroL < decimal; // Entero el valor de la izquierda
                            else value = decimalL < decimal; // Decimales los dos
                        }
                    } else {
                        System.out.println("ERROR SEMÁNTICO ENCONTRADO: No se puede realizar la operación " + operando.lexema + " con el dato " + valLeft.pop());
                        semantico = true;
                    }
                } else {
                    System.out.println("ERROR SEMÁNTICO ENCONTRADO: No se puede aplicar el operando " + operando.lexema + " a " + value);
                    semantico = true;
                }
            }

            ExprBinary eb = new ExprBinary(left, operando, t);
            return comparison_2(eb, ta);
        } else if(preanalisis.tipo == TipoToken.LESS_EQUAL){
            match(TipoToken.LESS_EQUAL);
            Token operando = previous();
            valLeft.push(value);
            String aux = "";
            if(!identificadores.isEmpty()) aux = (String) identificadores.peek();
            Expression t = term(ta);
            boolean nsh = false;

            if(!identificadores.isEmpty()){
                if(value == null && dentroFuncion.contains(aux) && dentroFuncion.contains(identificadores.peek())) nsh = true;
            }

            if(!nsh){
                if(value instanceof Number){
                    if(valLeft.peek() instanceof Number){
                        Number l = (Number) valLeft.pop();
                        Number num = (Number) value;
                        int entero = num.intValue(), enteroL = l.intValue();
                        float decimal = num.floatValue(), decimalL = l.floatValue();

                        if(num.intValue() == num.floatValue()){
                            if(l.intValue() == l.floatValue()) value = enteroL <= entero; // Enteros los dos
                            else value = (float) decimalL <= entero; // Decimal el valor de la izquierda
                        } else {
                            if(l.intValue() == l.floatValue()) value = (float) enteroL <= decimal; // Entero el valor de la izquierda
                            else value = decimalL <= decimal; // Decimales los dos
                        }
                    } else {
                        System.out.println("ERROR SEMÁNTICO ENCONTRADO: No se puede realizar la operación " + operando.lexema + " con el dato " + valLeft.pop());
                        semantico = true;
                    }
                } else {
                    System.out.println("ERROR SEMÁNTICO ENCONTRADO: No se puede aplicar el operando " + operando.lexema + " a " + value);
                    semantico = true;
                }
            }

            ExprBinary eb = new ExprBinary(left, operando, t);
            return comparison_2(eb, ta);
        }

        return left; // En caso de que COMPARISON_2 -> ε
    }

    private Expression term(tablaSimbolos t){
        if(hayErrores) return null;
        return term_2(factor(t), t);
    }

    private Expression term_2(Expression left, tablaSimbolos t){
        if(hayErrores) return null;

        if(preanalisis.tipo == TipoToken.MINUS){
            match(TipoToken.MINUS);
            Token operador = previous();
            valLeft.push(value);
            String aux = "";
            if(!identificadores.isEmpty()) aux = (String) identificadores.peek();
            Expression f = factor(t);
            boolean nsh = false;

            if(!identificadores.isEmpty()){
                if(value == null && dentroFuncion.contains(aux) && dentroFuncion.contains(identificadores.peek())) nsh = true;
            }

            if(!nsh) {
                if (value instanceof Number) {
                    if (valLeft.peek() instanceof Number) {
                        Number l = (Number) valLeft.pop();
                        Number num = (Number) value;
                        int entero = num.intValue(), enteroL = l.intValue();
                        float decimal = num.floatValue(), decimalL = l.floatValue();

                        if (num.intValue() == num.floatValue()) {
                            if (l.intValue() == l.floatValue()) value = enteroL - entero; // Enteros los dos
                            else value = (float) decimalL - entero; // Decimal el valor de la izquierda
                        } else {
                            if (l.intValue() == l.floatValue())
                                value = (float) enteroL - decimal; // Entero el valor de la izquierda
                            else value = decimalL - decimal; // Decimales los dos
                        }
                    } else {
                        System.out.println("ERROR SEMÁNTICO ENCONTRADO: No se puede realizar la operación " + operador.lexema + " con el dato " + valLeft.pop());
                        semantico = true;
                    }
                } else {
                    System.out.println("ERROR SEMÁNTICO ENCONTRADO: No se puede aplicar el operando " + operador.lexema + " a " + value);
                    semantico = true;
                }
            }

            ExprBinary eb = new ExprBinary(left, operador, f);
            return term_2(eb, t);
        } else if(preanalisis.tipo == TipoToken.PLUS){
            match(TipoToken.PLUS);
            Token operador = previous();
            valLeft.push(value);
            String aux = "";
            if(!identificadores.isEmpty()) aux = (String) identificadores.peek();
            Expression f = factor(t);
            boolean nsh = false;

            if(!identificadores.isEmpty()){
                if(value == null && dentroFuncion.contains(aux) && dentroFuncion.contains(identificadores.peek())) nsh = true;
            }

            if(!nsh){
                if(value instanceof Number){
                    if(valLeft.peek() instanceof Number){
                        Number l = (Number) valLeft.pop();
                        Number num = (Number) value;
                        int entero = num.intValue(), enteroL = l.intValue();
                        float decimal = num.floatValue(), decimalL = l.floatValue();

                        if(num.intValue() == num.floatValue()){
                            if(l.intValue() == l.floatValue()) value = enteroL + entero; // Enteros los dos
                            else value = (float) decimalL + entero; // Decimal el valor de la izquierda
                        } else {
                            if(l.intValue() == l.floatValue()) value = (float) enteroL + decimal; // Entero el valor de la izquierda
                            else value = decimalL + decimal; // Decimales los dos
                        }
                    } else {
                        System.out.println("ERROR SEMÁNTICO ENCONTRADO: No se puede realizar la operación " + operador.lexema + " con el dato " + valLeft.pop());
                        semantico = true;
                    }
                } else if(value instanceof String){
                    String rig = (String) value;
                    if(valLeft.peek() instanceof String){
                        value = (String) valLeft.pop() + rig;
                    } else {
                        System.out.println("ERROR SEMÁNTICO ENCONTRADO: No se puede aplicar el operando " + operador.lexema + " a " + value);
                        semantico = true;
                    }
                } else {
                    System.out.println("ERROR SEMÁNTICO ENCONTRADO: No se puede aplicar el operando " + operador.lexema + " a " + value);
                    semantico = true;
                }
            }

            ExprBinary eb = new ExprBinary(left, operador, f);
            return term_2(eb, t);
        }

        return left; // En caso de que TERM_2 -> ε
    }

    private Expression factor(tablaSimbolos t){
        if(hayErrores) return null;
        return factor_2(unary(t), t);
    }

    private Expression factor_2(Expression left, tablaSimbolos t){
        if(hayErrores) return null;

        if(preanalisis.tipo == TipoToken.SLASH){
            match(TipoToken.SLASH);
            Token operador = previous();
            valLeft.push(value);
            String aux = "";
            if(!identificadores.isEmpty()) aux = (String) identificadores.peek();
            Expression u = unary(t);
            boolean nsh = false;

            if(!identificadores.isEmpty()){
                if(value == null && dentroFuncion.contains(aux) && dentroFuncion.contains(identificadores.peek())) nsh = true;
            }

            if(!nsh){
                if(value instanceof Number){
                    if(valLeft.peek() instanceof Number){
                        Number l = (Number) valLeft.pop();
                        Number num = (Number) value;
                        int entero = num.intValue(), enteroL = l.intValue();
                        float decimal = num.floatValue(), decimalL = l.floatValue();

                        if(num.intValue() == num.floatValue()){
                            if(l.intValue() == l.floatValue()) value = (float) enteroL / entero; // Enteros los dos
                            else value = (float) decimalL / entero; // Decimal el valor de la izquierda
                        } else {
                            if(l.intValue() == l.floatValue()) value = (float) enteroL / decimal; // Entero el valor de la izquierda
                            else value = (float) decimalL / decimal; // Decimales los dos
                        }
                    } else {
                        System.out.println("ERROR SEMÁNTICO ENCONTRADO: No se puede realizar la operación " + operador.lexema + " con el dato " + valLeft.pop());
                        semantico = true;
                    }
                } else {
                    System.out.println("ERROR SEMÁNTICO ENCONTRADO: No se puede aplicar el operando " + operador.lexema + " a " + value);
                    semantico = true;
                }
            }

            ExprBinary eb = new ExprBinary(left, operador, u);
            return factor_2(eb, t);
        } else if(preanalisis.tipo == TipoToken.STAR){
            match(TipoToken.STAR);
            Token operador = previous();
            valLeft.push(value);
            String aux = "";
            if(!identificadores.isEmpty()) aux = (String) identificadores.peek();
            Expression u = unary(t);
            boolean nsh = false;

            if(!identificadores.isEmpty()){
                if(value == null && dentroFuncion.contains(aux) && dentroFuncion.contains(identificadores.peek())) nsh = true;
            }
            if(!nsh){
                if(value instanceof Number){
                    if(valLeft.peek() instanceof Number){
                        Number l = (Number) valLeft.pop();
                        Number num = (Number) value;
                        int entero = num.intValue(), enteroL = l.intValue();
                        float decimal = num.floatValue(), decimalL = l.floatValue();

                        if(num.intValue() == num.floatValue()){
                            if(l.intValue() == l.floatValue()) value = enteroL * entero; // Enteros los dos
                            else value = decimalL * entero; // Decimal el valor de la izquierda
                        } else {
                            if(l.intValue() == l.floatValue()) value = (float) enteroL * decimal; // Entero el valor de la izquierda
                            else value =  decimalL * decimal; // Decimales los dos
                        }
                    } else {
                        System.out.println("ERROR SEMÁNTICO ENCONTRADO: No se puede realizar la operación " + operador.lexema + " con el dato " + valLeft.pop());
                        semantico = true;
                    }
                } else {
                    System.out.println("ERROR SEMÁNTICO ENCONTRADO: No se puede aplicar el operando " + operador.lexema + " a " + value);
                    semantico = true;
                }
            }

            ExprBinary eb = new ExprBinary(left, operador, u);
            return factor_2(eb, t);
        }

        return left; // En caso de que FACTOR_2 -> ε
    }

    private Expression unary(tablaSimbolos t){
        if(hayErrores) return null;

        if(preanalisis.tipo == TipoToken.BANG){
            match(TipoToken.BANG);
            Token operador = previous();
            Expression u = unary(t);
            boolean nsh = false;

            if(!identificadores.isEmpty()){
                if(value == null && dentroFuncion.contains(identificadores.peek())) nsh = true;
            }

            if(!nsh){
                if (!(value instanceof Boolean)){
                    System.out.println("ERROR SEMÁNTICO ENCONTRADO: No se puede aplicar el operando " + operador.lexema + " a " + value);
                    semantico = true;
                } else {
                    Boolean bol = (Boolean) value;
                    if(bol) value = false;
                    else value = true;
                }
            }

            return new ExprUnary(operador, u);
        } else if(preanalisis.tipo == TipoToken.MINUS){
            match(TipoToken.MINUS);
            Token operador = previous();
            Expression u = unary(t);
            boolean nsh = false;

            if(!identificadores.isEmpty()){
                if(value == null && dentroFuncion.contains(identificadores.peek())) nsh = true;
            }

            if(!nsh){
                if(!(value instanceof Number)){
                    System.out.println("ERROR SEMÁNTICO ENCONTRADO: No se puede aplicar el operando " + operador.lexema + " a " + value);
                    semantico = true;
                } else {
                    Number num = (Number) value;
                    int entero = num.intValue();
                    float decimal = num.floatValue();

                    if(num.intValue() == num.floatValue()){
                        entero = entero * (-1);
                        value = entero;
                    } else {
                        decimal = decimal * (-1);
                        value = decimal;
                    }
                }
            }

            return new ExprUnary(operador, u);
        } else if(preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL ||
                preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER ||
                preanalisis.tipo == TipoToken.LEFT_PAREN){
            return call(t);
        }

        errorSintactico();
        System.out.println("\tSe encontró: " + preanalisis.tipo);
        return null;
    }

    private Expression call(tablaSimbolos t){
        if(hayErrores) return null;
        return call_2(primary(t), t);
    }

    private Expression call_2(Expression callName, tablaSimbolos t){
        if(hayErrores) return null;

        if(preanalisis.tipo == TipoToken.LEFT_PAREN){
            match(TipoToken.LEFT_PAREN);
            List<Expression> arg = arguments_opc(t);
            match(TipoToken.RIGHT_PAREN);
            return new ExprCallFunction(callName, arg);
        }

        return callName; // En caso de que CALL_2 -> ε
    }
    private Expression primary(tablaSimbolos t){
        if(hayErrores) return null;

        if(preanalisis.tipo == TipoToken.TRUE){
            match(TipoToken.TRUE); value = true; return new ExprLiteral(true);
        } else if(preanalisis.tipo == TipoToken.FALSE){
            match(TipoToken.FALSE); value = false; return new ExprLiteral(false);
        } else if(preanalisis.tipo == TipoToken.NULL){
                match(TipoToken.NULL); value = null; return new ExprLiteral(null);
        } else if(preanalisis.tipo == TipoToken.NUMBER){
            match(TipoToken.NUMBER);
            value = previous().literal;
            return new ExprLiteral(previous().literal);
        } else if(preanalisis.tipo == TipoToken.STRING){
            match(TipoToken.STRING); value = previous().literal; return new ExprLiteral(previous().literal);
        } else if(preanalisis.tipo == TipoToken.IDENTIFIER){
            match(TipoToken.IDENTIFIER);

            if(!t.existeIdentificador(previous().lexema)){
                System.out.println("ERROR SEMÁNTICO ENCONTRADO: La variable " + previous().lexema + " NO se ha declarado.");
                semantico = true;
            } else {
                value = t.obtener(previous().lexema);
                identificadores.push(previous().lexema);
            }

            return new ExprVariable(previous());
        } else if(preanalisis.tipo == TipoToken.LEFT_PAREN){
            match(TipoToken.LEFT_PAREN);
            Expression e = expression(t);
            match(TipoToken.RIGHT_PAREN);
            return new ExprGrouping(e);
        }

        hayErrores = true;
        System.out.println("ERROR SINTÁCTICO ENCONTRADO: Se esperaba 'true', 'false', 'null', 'number', 'string', 'id' o '('");
        System.out.println("\tSe encontró: " + preanalisis.tipo);
        return null;
    }

    private Statement function(tablaSimbolos t){
        if(hayErrores) return null;

        match(TipoToken.IDENTIFIER);
        Token name = previous();
        match(TipoToken.LEFT_PAREN);
        List<Token> params = parameters_opc();
        match(TipoToken.RIGHT_PAREN);
        tablaSimbolos taux = new tablaSimbolos();
        int k = 0;

        if(t.existeIdentificador(name.lexema)){
            System.out.println("ERROR SEMÁNTICO ENCONTRADO: La variable " + name.lexema + " YA se había declarado.");
            semantico = true;
            taux = null;
        } else {
            for(k = 0; k < Objects.requireNonNull(params).size(); k++){
                if(taux.existeIdentificador(params.get(k).lexema)){
                    System.out.println("ERROR SEMÁNTICO ENCONTRADO: La variable " + params.get(k).lexema + " YA se había declarado.");
                    semantico = true;
                    break;
                } else {
                    taux.asignar(params.get(k).lexema, null);
                    dentroFuncion.add(params.get(k).lexema);
                }
            }
        }
        StmtBlock sb = (StmtBlock) block(taux);
        for(int j = 0; j < k && !dentroFuncion.isEmpty(); j++)
            dentroFuncion.remove(dentroFuncion.size() - 1);

        if(!t.existeIdentificador(name.lexema)){
            t.asignar(name.lexema, null);
            //System.out.println(value);
        } else {
            semantico = true;
            System.out.println("ERROR SEMÁNTICO ENCONTRADO: La variable " + name.lexema + " ya se había declarado.");
        }

        return new StmtFunction(name, params, sb);
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

    private List<Expression> arguments_opc(tablaSimbolos t){
        if(hayErrores) return null;

        List<Expression> exp = new ArrayList<>();
        if(preanalisis.tipo == TipoToken.RIGHT_PAREN) return exp; // En caso de que no haya argumentos
        exp.add(expression(t));
        return arguments(exp, t);
    }

    private List<Expression> arguments(List<Expression> args, tablaSimbolos t){ // Recibe una lista de expresiones que son los argumentos
        if(hayErrores) return null;

        if(preanalisis.tipo == TipoToken.COMMA){
            match(TipoToken.COMMA);
            args.add(expression(t));
            arguments(args, t);
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