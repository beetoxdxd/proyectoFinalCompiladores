import java.util.*;
import java.util.stream.Collectors;

public class AnalizadorSintactico implements Parser{
    private int i = 0;
    private Token preanalisis;
    private final List<Token> tokens;


    public AnalizadorSintactico(List<Token> tokens){
        this.tokens = tokens;
        preanalisis = this.tokens.get(i);
    }

    @Override
    public boolean parse(){
        int reduccion = 0, help = 0, saux = 0;
        Stack pila = new Stack<>();

        Stack expGeneral = new Stack<>();
        Stack operadorBinary = new Stack<>();
        Stack operadorUnary = new Stack<>();
        Stack operadorLogical = new Stack<>();
        Stack operadorAssign = new Stack<>();

        List<Expression> arguments = new ArrayList<>();
        List<Token> parameters = new ArrayList<>();
        List<Statement> statements = new ArrayList<>();
        Stack returnAux = new Stack<>();
        Stack elseAux = new Stack<>();
        Stack forAux = new Stack<>();
        Stack varAux = new Stack<>();
        Stack blockAux = new Stack<>();
        Stack funAux = new Stack<>();
        List<Statement> statementsAux = new ArrayList<>();
        List <Statement> ifAux = new ArrayList<>();

        Stack identificadores = new Stack<>();
        Stack funid = new Stack<>();
        Stack varid = new Stack<>();

        pila.push(0);

        while(i < tokens.size()){
            // System.out.println("Pila: " + pila.peek() + ", i: " + i + ", reduccion: " + reduccion + ", preanalisis: " + preanalisis.tipo);
            switch ((int) pila.peek()){
                case 0:
                    if(reduccion != 0){
                        switch (reduccion){
                            case 1: pila.push(1); break;
                            case 2: pila.push(2); break;
                            case 3: pila.push(3); break;
                            case 4: pila.push(4); break;
                            case 6: pila.push(5); break;
                            case 7: pila.push(8); break;
                            case 8: pila.push(9); break;
                            case 12: pila.push(10); break;
                            case 14: pila.push(11); break;
                            case 15: pila.push(12); break;
                            case 17: pila.push(13); break;
                            case 18: pila.push(14); break;
                            case 19: pila.push(15); break;
                            case 20: pila.push(22); break;
                            case 22: pila.push(23);  break;
                            case 24: pila.push(24); break;
                            case 26: pila.push(25); break;
                            case 28: pila.push(26); break;
                            case 30: pila.push(27); break;
                            case 32: pila.push(28); break;
                            case 34: pila.push(29); break;
                            case 35: pila.push(32); break;
                            case 37: pila.push(33); break;
                            default:
                                System.out.println("Error en la reducción del estado 0.");
                                return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.FUN){ pila.push(6); next();}
                        else if(preanalisis.tipo == TipoToken.VAR){ pila.push(7); next();}
                        else if(preanalisis.tipo == TipoToken.FOR){ pila.push(16); next();}
                        else if(preanalisis.tipo == TipoToken.LEFT_PAREN){ pila.push(40); next();}
                        else if(preanalisis.tipo == TipoToken.IF){ pila.push(17); next();}
                        else if(preanalisis.tipo == TipoToken.PRINT){ pila.push(18); next();}
                        else if(preanalisis.tipo == TipoToken.RETURN){ pila.push(19); next();}
                        else if(preanalisis.tipo == TipoToken.WHILE){ pila.push(20); next();}
                        else if(preanalisis.tipo == TipoToken.LEFT_BRACE){ pila.push(21); next();}
                        else if(preanalisis.tipo == TipoToken.RIGHT_BRACE){
                            reduccion = 2; blockAux.push(0);
                        } else if(preanalisis.tipo == TipoToken.BANG){ pila.push(30); next(); operadorUnary.push(previous());}
                        else if(preanalisis.tipo == TipoToken.MINUS){ pila.push(31); next(); operadorUnary.push(previous());}
                        else if(preanalisis.tipo == TipoToken.TRUE){ pila.push(34); next();}
                        else if(preanalisis.tipo == TipoToken.FALSE){ pila.push(35); next();}
                        else if(preanalisis.tipo == TipoToken.NULL){ pila.push(36); next();}
                        else if(preanalisis.tipo == TipoToken.NUMBER){ pila.push(37); next();}
                        else if(preanalisis.tipo == TipoToken.STRING){ pila.push(38); next();}
                        else if(preanalisis.tipo == TipoToken.IDENTIFIER){ pila.push(39); next();}
                        else if(preanalisis.tipo == TipoToken.EOF){
                            reduccion = 2; blockAux.push(0);
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba 'fun', 'var', 'for', '(', 'if', 'print', 'return', 'while', '{', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '$'");
                            return false;
                        }
                    }
                    break;
                case 1:
                    if(preanalisis.tipo == TipoToken.EOF){
                        System.out.println("Resultado correcto, no se presentaron errores.");
                        return true;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba '$'");
                        return false;
                    }
                case 2:
                    if(preanalisis.tipo == TipoToken.EOF){
                        pila.pop();
                        reduccion = 1;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba '$'");
                        return false;
                    }
                    break;
                case 3:
                    if(reduccion != 0){
                        switch (reduccion){
                            case 2: pila.push(41); break;
                            case 3: pila.push(3); break;
                            case 4: pila.push(4); break;
                            case 6: pila.push(5); break;
                            case 7: pila.push(8); break;
                            case 8: pila.push(9); break;
                            case 12: pila.push(10); break;
                            case 14: pila.push(11); break;
                            case 15: pila.push(12); break;
                            case 17: pila.push(13); break;
                            case 18: pila.push(14); break;
                            case 19: pila.push(15); break;
                            case 20: pila.push(22); break;
                            case 22: pila.push(23); break;
                            case 24: pila.push(24); break;
                            case 26: pila.push(25); break;
                            case 28: pila.push(26); break;
                            case 30: pila.push(27); break;
                            case 32: pila.push(28); break;
                            case 34: pila.push(29); break;
                            case 35: pila.push(32); break;
                            case 37: pila.push(33); break;
                            default:
                                System.out.println("Error en la reducción del estado 3.");
                                return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.FUN){ pila.push(6); next();}
                        else if(preanalisis.tipo == TipoToken.VAR){ pila.push(7); next();}
                        else if(preanalisis.tipo == TipoToken.FOR){ pila.push(16); next();}
                        else if(preanalisis.tipo == TipoToken.LEFT_PAREN){ pila.push(40); next();}
                        else if(preanalisis.tipo == TipoToken.IF){ pila.push(17); next();}
                        else if(preanalisis.tipo == TipoToken.PRINT){ pila.push(18); next();}
                        else if(preanalisis.tipo == TipoToken.RETURN){ pila.push(19); next();}
                        else if(preanalisis.tipo == TipoToken.WHILE){ pila.push(20); next();}
                        else if(preanalisis.tipo == TipoToken.LEFT_BRACE){ pila.push(21); next();}
                        else if(preanalisis.tipo == TipoToken.RIGHT_BRACE){
                            reduccion = 2; blockAux.push(0);
                        } else if(preanalisis.tipo == TipoToken.BANG){ pila.push(30); next(); operadorUnary.push(previous());}
                        else if(preanalisis.tipo == TipoToken.MINUS){ pila.push(31); next(); operadorUnary.push(previous());}
                        else if(preanalisis.tipo == TipoToken.TRUE){ pila.push(34); next();}
                        else if(preanalisis.tipo == TipoToken.FALSE){ pila.push(35); next();}
                        else if(preanalisis.tipo == TipoToken.NULL){ pila.push(36); next();}
                        else if(preanalisis.tipo == TipoToken.NUMBER){ pila.push(37); next();}
                        else if(preanalisis.tipo == TipoToken.STRING){ pila.push(38); next();}
                        else if(preanalisis.tipo == TipoToken.IDENTIFIER){ pila.push(39); next();}
                        else if(preanalisis.tipo == TipoToken.EOF){
                            reduccion = 2; blockAux.push(0);
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba 'fun', 'var', 'for', '(', 'if', 'print', 'return', 'while', '{', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '$'");
                            return false;
                        }
                    }
                    break;
                case 4:
                    if(reduccion != 0){
                        switch (reduccion){
                            case 2: pila.push(42); break;
                            case 3: pila.push(3); break;
                            case 4: pila.push(4); break;
                            case 6: pila.push(5); break;
                            case 7: pila.push(8); break;
                            case 8: pila.push(9); break;
                            case 12: pila.push(10); break;
                            case 14: pila.push(11); break;
                            case 15: pila.push(12); break;
                            case 17: pila.push(13); break;
                            case 18: pila.push(14); break;
                            case 19: pila.push(15); break;
                            case 20: pila.push(22); break;
                            case 22: pila.push(23); break;
                            case 24: pila.push(24); break;
                            case 26: pila.push(25); break;
                            case 28: pila.push(26); break;
                            case 30: pila.push(27); break;
                            case 32: pila.push(28); break;
                            case 34: pila.push(29); break;
                            case 35: pila.push(32); break;
                            case 37: pila.push(33); break;
                            default:
                                System.out.println("Error en la reducción del estado 4.");
                                return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.FUN){ pila.push(6); next();}
                        else if(preanalisis.tipo == TipoToken.VAR){ pila.push(7); next();}
                        else if(preanalisis.tipo == TipoToken.FOR){ pila.push(16); next();}
                        else if(preanalisis.tipo == TipoToken.LEFT_PAREN){ pila.push(40); next();}
                        else if(preanalisis.tipo == TipoToken.IF){ pila.push(17); next();}
                        else if(preanalisis.tipo == TipoToken.PRINT){ pila.push(18); next();}
                        else if(preanalisis.tipo == TipoToken.RETURN){ pila.push(19); next();}
                        else if(preanalisis.tipo == TipoToken.WHILE){ pila.push(20); next();}
                        else if(preanalisis.tipo == TipoToken.LEFT_BRACE){ pila.push(21); next();}
                        else if(preanalisis.tipo == TipoToken.RIGHT_BRACE){
                            reduccion = 2; blockAux.push(0);
                        } else if(preanalisis.tipo == TipoToken.BANG){ pila.push(30); next(); operadorUnary.push(previous());}
                        else if(preanalisis.tipo == TipoToken.MINUS){ pila.push(31); next(); operadorUnary.push(previous());}
                        else if(preanalisis.tipo == TipoToken.TRUE){ pila.push(34); next();}
                        else if(preanalisis.tipo == TipoToken.FALSE){ pila.push(35); next();}
                        else if(preanalisis.tipo == TipoToken.NULL){ pila.push(36); next();}
                        else if(preanalisis.tipo == TipoToken.NUMBER){ pila.push(37); next();}
                        else if(preanalisis.tipo == TipoToken.STRING){ pila.push(38); next();}
                        else if(preanalisis.tipo == TipoToken.IDENTIFIER){ pila.push(39); next();}
                        else if(preanalisis.tipo == TipoToken.EOF){
                            reduccion = 2; blockAux.push(0);
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba 'fun', 'var', 'for', '(', 'if', 'print', 'return', 'while', '{', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '$'");
                            return false;
                        }
                    }
                    break;
                case 5:
                    if(reduccion != 0){
                        switch (reduccion){
                            case 2: pila.push(43); break;
                            case 3: pila.push(3); break;
                            case 4: pila.push(4); break;
                            case 6: pila.push(5); break;
                            case 7: pila.push(8); break;
                            case 8: pila.push(9); break;
                            case 12: pila.push(10); break;
                            case 14: pila.push(11); break;
                            case 15: pila.push(12); break;
                            case 17: pila.push(13); break;
                            case 18: pila.push(14); break;
                            case 19: pila.push(15); break;
                            case 20: pila.push(22); break;
                            case 22: pila.push(23); break;
                            case 24: pila.push(24); break;
                            case 26: pila.push(25); break;
                            case 28: pila.push(26); break;
                            case 30: pila.push(27); break;
                            case 32: pila.push(28); break;
                            case 34: pila.push(29); break;
                            case 35: pila.push(32); break;
                            case 37: pila.push(33); break;
                            default:
                                System.out.println("Error en la reducción del estado 5.");
                                return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.FUN){ pila.push(6); next();}
                        else if(preanalisis.tipo == TipoToken.VAR){ pila.push(7); next();}
                        else if(preanalisis.tipo == TipoToken.FOR){ pila.push(16); next();}
                        else if(preanalisis.tipo == TipoToken.LEFT_PAREN){ pila.push(40); next();}
                        else if(preanalisis.tipo == TipoToken.IF){ pila.push(17); next();}
                        else if(preanalisis.tipo == TipoToken.PRINT){ pila.push(18); next();}
                        else if(preanalisis.tipo == TipoToken.RETURN){ pila.push(19); next();}
                        else if(preanalisis.tipo == TipoToken.WHILE){ pila.push(20); next();}
                        else if(preanalisis.tipo == TipoToken.LEFT_BRACE){ pila.push(21); next();}
                        else if(preanalisis.tipo == TipoToken.RIGHT_BRACE){
                            reduccion = 2; blockAux.push(0);
                        } else if(preanalisis.tipo == TipoToken.BANG){ pila.push(30); next(); operadorUnary.push(previous());}
                        else if(preanalisis.tipo == TipoToken.MINUS){ pila.push(31); next(); operadorUnary.push(previous());}
                        else if(preanalisis.tipo == TipoToken.TRUE){ pila.push(34); next();}
                        else if(preanalisis.tipo == TipoToken.FALSE){ pila.push(35); next();}
                        else if(preanalisis.tipo == TipoToken.NULL){ pila.push(36); next();}
                        else if(preanalisis.tipo == TipoToken.NUMBER){ pila.push(37); next();}
                        else if(preanalisis.tipo == TipoToken.STRING){ pila.push(38); next();}
                        else if(preanalisis.tipo == TipoToken.IDENTIFIER){ pila.push(39); next();}
                        else if(preanalisis.tipo == TipoToken.EOF){
                            reduccion = 2; blockAux.push(0);
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba 'fun', 'var', 'for', '(', 'if', 'print', 'return', 'while', '{', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '$'");
                            return false;
                        }
                    }
                    break;
                case 6:
                    if(reduccion != 0){
                        if(reduccion == 38) pila.push(44);
                        else {
                            System.out.println("Error en la reducción del estado 6.");
                            return false;
                        }
                    } else {
                        if(preanalisis.tipo == TipoToken.IDENTIFIER){ pila.push(45); next(); funid.push(previous());}
                        else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba 'id'.");
                            return false;
                        }
                    }
                    break;
                case 7:
                    if(preanalisis.tipo == TipoToken.IDENTIFIER){ pila.push(46); next(); varid.push(previous());}
                    else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba 'id'.");
                        return false;
                    }
                    break;
                case 8:
                    if(preanalisis.tipo == TipoToken.FUN || preanalisis.tipo == TipoToken.VAR || preanalisis.tipo == TipoToken.FOR ||
                            preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.IF || preanalisis.tipo == TipoToken.ELSE ||
                            preanalisis.tipo == TipoToken.PRINT || preanalisis.tipo == TipoToken.RETURN || preanalisis.tipo == TipoToken.WHILE ||
                            preanalisis.tipo == TipoToken.LEFT_BRACE || preanalisis.tipo == TipoToken.RIGHT_BRACE || preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS ||
                            preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL ||
                            preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER ||
                            preanalisis.tipo == TipoToken.EOF){
                        pila.pop();
                        reduccion = 6;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba 'fun', 'var', 'for', '(', 'if', 'else', 'print', 'return', 'while', '{', '}', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '$'");
                        return false;
                    }
                    break;
                case 9:
                    if(preanalisis.tipo == TipoToken.FUN || preanalisis.tipo == TipoToken.VAR || preanalisis.tipo == TipoToken.FOR ||
                            preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.IF || preanalisis.tipo == TipoToken.ELSE ||
                            preanalisis.tipo == TipoToken.PRINT || preanalisis.tipo == TipoToken.RETURN || preanalisis.tipo == TipoToken.WHILE ||
                            preanalisis.tipo == TipoToken.LEFT_BRACE || preanalisis.tipo == TipoToken.RIGHT_BRACE || preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS ||
                            preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL ||
                            preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER ||
                            preanalisis.tipo == TipoToken.EOF){
                        pila.pop();
                        reduccion = 6;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba 'fun', 'var', 'for', '(', 'if', 'else', 'print', 'return', 'while', '{', '}', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '$'");
                        return false;
                    }
                    break;
                case 10:
                    if(preanalisis.tipo == TipoToken.FUN || preanalisis.tipo == TipoToken.VAR || preanalisis.tipo == TipoToken.FOR ||
                            preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.IF || preanalisis.tipo == TipoToken.ELSE ||
                            preanalisis.tipo == TipoToken.PRINT || preanalisis.tipo == TipoToken.RETURN || preanalisis.tipo == TipoToken.WHILE ||
                            preanalisis.tipo == TipoToken.LEFT_BRACE || preanalisis.tipo == TipoToken.RIGHT_BRACE || preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS ||
                            preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL ||
                            preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER ||
                            preanalisis.tipo == TipoToken.EOF){
                        pila.pop();
                        reduccion = 6;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba 'fun', 'var', 'for', '(', 'if', 'else', 'print', 'return', 'while', '{', '}', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '$'");
                        return false;
                    }
                    break;
                case 11:
                    if(preanalisis.tipo == TipoToken.FUN || preanalisis.tipo == TipoToken.VAR || preanalisis.tipo == TipoToken.FOR ||
                            preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.IF || preanalisis.tipo == TipoToken.ELSE ||
                            preanalisis.tipo == TipoToken.PRINT || preanalisis.tipo == TipoToken.RETURN || preanalisis.tipo == TipoToken.WHILE ||
                            preanalisis.tipo == TipoToken.LEFT_BRACE || preanalisis.tipo == TipoToken.RIGHT_BRACE || preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS ||
                            preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL ||
                            preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER ||
                            preanalisis.tipo == TipoToken.EOF){
                        pila.pop();
                        reduccion = 6;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba 'fun', 'var', 'for', '(', 'if', 'else', 'print', 'return', 'while', '{', '}', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '$'");
                        return false;
                    }
                    break;
                case 12:
                    if(preanalisis.tipo == TipoToken.FUN || preanalisis.tipo == TipoToken.VAR || preanalisis.tipo == TipoToken.FOR ||
                            preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.IF || preanalisis.tipo == TipoToken.ELSE ||
                            preanalisis.tipo == TipoToken.PRINT || preanalisis.tipo == TipoToken.RETURN || preanalisis.tipo == TipoToken.WHILE ||
                            preanalisis.tipo == TipoToken.LEFT_BRACE || preanalisis.tipo == TipoToken.RIGHT_BRACE || preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS ||
                            preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL ||
                            preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER ||
                            preanalisis.tipo == TipoToken.EOF){
                        pila.pop();
                        reduccion = 6;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba 'fun', 'var', 'for', '(', 'if', 'else', 'print', 'return', 'while', '{', '}', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '$'");
                        return false;
                    }
                    break;
                case 13:
                    if(preanalisis.tipo == TipoToken.FUN || preanalisis.tipo == TipoToken.VAR || preanalisis.tipo == TipoToken.FOR ||
                            preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.IF || preanalisis.tipo == TipoToken.ELSE ||
                            preanalisis.tipo == TipoToken.PRINT || preanalisis.tipo == TipoToken.RETURN || preanalisis.tipo == TipoToken.WHILE ||
                            preanalisis.tipo == TipoToken.LEFT_BRACE || preanalisis.tipo == TipoToken.RIGHT_BRACE || preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS ||
                            preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL ||
                            preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER ||
                            preanalisis.tipo == TipoToken.EOF){
                        pila.pop();
                        reduccion = 6;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba 'fun', 'var', 'for', '(', 'if', 'else', 'print', 'return', 'while', '{', '}', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '$'");
                        return false;
                    }
                    break;
                case 14:
                    if(preanalisis.tipo == TipoToken.FUN || preanalisis.tipo == TipoToken.VAR || preanalisis.tipo == TipoToken.FOR ||
                            preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.IF || preanalisis.tipo == TipoToken.ELSE ||
                            preanalisis.tipo == TipoToken.PRINT || preanalisis.tipo == TipoToken.RETURN || preanalisis.tipo == TipoToken.WHILE ||
                            preanalisis.tipo == TipoToken.LEFT_BRACE || preanalisis.tipo == TipoToken.RIGHT_BRACE || preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS ||
                            preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL ||
                            preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER ||
                            preanalisis.tipo == TipoToken.EOF){
                        pila.pop();
                        reduccion = 6;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba 'fun', 'var', 'for', '(', 'if', 'else', 'print', 'return', 'while', '{', '}', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '$'");
                        return false;
                    }
                    break;
                case 15:
                    if(preanalisis.tipo == TipoToken.SEMICOLON){
                        pila.push(47); next();
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba ';'");
                        return false;
                    }
                    break;
                case 16:
                    if(preanalisis.tipo == TipoToken.LEFT_PAREN){
                        pila.push(48); next();
                        statementsAux = statements.stream()
                                .collect(Collectors.toList());
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba '('");
                        return false;
                    }
                    break;
                case 17:
                    if(preanalisis.tipo == TipoToken.LEFT_PAREN){
                        pila.push(49); next();
                        statementsAux = statements.stream()
                                .collect(Collectors.toList());
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba '('");
                        return false;
                    }
                    break;
                case 18:
                    if(reduccion != 0){
                        switch (reduccion){
                            case 19: pila.push(50); break;
                            case 20: pila.push(22); break;
                            case 22: pila.push(23); break;
                            case 24: pila.push(24); break;
                            case 26: pila.push(25); break;
                            case 28: pila.push(26); break;
                            case 30: pila.push(27); break;
                            case 32: pila.push(28); break;
                            case 34: pila.push(29); break;
                            case 35: pila.push(32); break;
                            case 37: pila.push(33); break;
                            default:
                                System.out.println("Error en la reducción del estado 18.");
                                return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.LEFT_PAREN){
                            pila.push(40); next();
                        } else if(preanalisis.tipo == TipoToken.BANG){
                            pila.push(30); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.MINUS){
                            pila.push(31); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.TRUE){
                            pila.push(34); next();
                        } else if(preanalisis.tipo == TipoToken.FALSE){
                            pila.push(35); next();
                        } else if(preanalisis.tipo == TipoToken.NULL){
                            pila.push(36); next();
                        } else if(preanalisis.tipo == TipoToken.NUMBER){
                            pila.push(37); next();
                        } else if(preanalisis.tipo == TipoToken.STRING){
                            pila.push(38); next();
                        } else if(preanalisis.tipo == TipoToken.IDENTIFIER){
                            pila.push(39); next();
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba '(', '!', '-', 'true', 'false', 'null', 'number', 'string' o 'id'");
                            return false;
                        }
                    }
                    break;
                case 19:
                    if(reduccion != 0){
                        switch (reduccion){
                            case 16: pila.push(51); break;
                            case 19: pila.push(52); break;
                            case 20: pila.push(22); break;
                            case 22: pila.push(23); break;
                            case 24: pila.push(24); break;
                            case 26: pila.push(25); break;
                            case 28: pila.push(26); break;
                            case 30: pila.push(27); break;
                            case 32: pila.push(28); break;
                            case 34: pila.push(29); break;
                            case 35: pila.push(32); break;
                            case 37: pila.push(33); break;
                            default:
                                System.out.println("Error en la reducción del estado 19.");
                                return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.SEMICOLON){
                            reduccion = 16; returnAux.push(0);
                        } else if(preanalisis.tipo == TipoToken.LEFT_PAREN){
                            pila.push(40); next();
                        } else if(preanalisis.tipo == TipoToken.BANG){
                            pila.push(30); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.MINUS){
                            pila.push(31); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.TRUE){
                            pila.push(34); next();
                        } else if(preanalisis.tipo == TipoToken.FALSE){
                            pila.push(35); next();
                        } else if(preanalisis.tipo == TipoToken.NULL){
                            pila.push(36); next();
                        } else if(preanalisis.tipo == TipoToken.NUMBER){
                            pila.push(37); next();
                        } else if(preanalisis.tipo == TipoToken.STRING){
                            pila.push(38); next();
                        } else if(preanalisis.tipo == TipoToken.IDENTIFIER){
                            pila.push(39); next();
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba ';', '(', '!', '-', 'true', 'false', 'null', 'number', 'string' o 'id'");
                            return false;
                        }
                    }
                    break;
                case 20:
                    if(preanalisis.tipo == TipoToken.LEFT_PAREN){
                        pila.push(53); next();
                        statementsAux = statements.stream()
                                .collect(Collectors.toList());
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba '('.");
                        return false;
                    }
                    break;
                case 21:
                    if(reduccion != 0){
                        switch (reduccion){
                            case 2:
                                pila.push(54); //block.push(1);
                                break;
                            case 3: pila.push(3); break;
                            case 4: pila.push(4); break;
                            case 6: pila.push(5); break;
                            case 7: pila.push(8); break;
                            case 8: pila.push(9); break;
                            case 12: pila.push(10); break;
                            case 14: pila.push(11); break;
                            case 15: pila.push(12); break;
                            case 17: pila.push(13); break;
                            case 18: pila.push(14); break;
                            case 19: pila.push(15); break;
                            case 20: pila.push(22); break;
                            case 22: pila.push(23); break;
                            case 24: pila.push(24); break;
                            case 26: pila.push(25); break;
                            case 28: pila.push(26); break;
                            case 30: pila.push(27); break;
                            case 32: pila.push(28); break;
                            case 34: pila.push(29); break;
                            case 35: pila.push(32); break;
                            case 37: pila.push(33); break;
                            default:
                                System.out.println("Error en la reducción del estado 21.");
                                return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.FUN){ pila.push(6); next();}
                        else if(preanalisis.tipo == TipoToken.VAR){ pila.push(7); next();}
                        else if(preanalisis.tipo == TipoToken.FOR){ pila.push(16); next();}
                        else if(preanalisis.tipo == TipoToken.LEFT_PAREN){ pila.push(40); next();}
                        else if(preanalisis.tipo == TipoToken.IF){ pila.push(17); next();}
                        else if(preanalisis.tipo == TipoToken.PRINT){ pila.push(18); next();}
                        else if(preanalisis.tipo == TipoToken.RETURN){ pila.push(19); next();}
                        else if(preanalisis.tipo == TipoToken.WHILE){ pila.push(20); next();}
                        else if(preanalisis.tipo == TipoToken.LEFT_BRACE){ pila.push(21); next();}
                        else if(preanalisis.tipo == TipoToken.RIGHT_BRACE){
                            reduccion = 2; blockAux.push(0);
                        }else if(preanalisis.tipo == TipoToken.BANG){ pila.push(30); next(); operadorUnary.push(previous());}
                        else if(preanalisis.tipo == TipoToken.MINUS){ pila.push(31); next(); operadorUnary.push(previous());}
                        else if(preanalisis.tipo == TipoToken.TRUE){ pila.push(34); next();}
                        else if(preanalisis.tipo == TipoToken.FALSE){ pila.push(35); next();}
                        else if(preanalisis.tipo == TipoToken.NULL){ pila.push(36); next();}
                        else if(preanalisis.tipo == TipoToken.NUMBER){ pila.push(37); next();}
                        else if(preanalisis.tipo == TipoToken.STRING){ pila.push(38); next();}
                        else if(preanalisis.tipo == TipoToken.IDENTIFIER){ pila.push(39); next();}
                        else if(preanalisis.tipo == TipoToken.EOF){
                            reduccion = 2; blockAux.push(0);
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba 'fun', 'var', 'for', '(', 'if', 'print', 'return', 'while', '{', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '$'");
                            return false;
                        }
                    }
                    break;
                case 22:
                    if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.RIGHT_PAREN || preanalisis.tipo == TipoToken.COMMA){
                        pila.pop();
                        reduccion = 19;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba ';', ')' o ','");
                        return false;
                    }
                    break;
                case 23:
                    if(reduccion != 0){
                        if(reduccion == 21) pila.push(55);
                        else {
                            System.out.println("Error en la reducción del estado 23.");
                            return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.RIGHT_PAREN || preanalisis.tipo == TipoToken.COMMA){
                            reduccion = 21;
                        } else if(preanalisis.tipo == TipoToken.EQUAL){
                            pila.push(56); next(); operadorAssign.push(tokens.get(i-2));
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba '=', ';', ')' o ','");
                            return false;
                        }
                    }
                    break;
                case 24:
                    if(reduccion != 0){
                        if(reduccion == 23) pila.push(57);
                        else {
                            System.out.println("Error en la reducción del estado 24.");
                            return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                                preanalisis.tipo == TipoToken.COMMA){
                            reduccion = 23;
                        } else if(preanalisis.tipo == TipoToken.OR){
                            pila.push(58); next(); operadorLogical.push(previous());
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba 'or', '=', ';', ')' o ','");
                            return false;
                        }
                    }
                    break;
                case 25:
                    if(reduccion != 0){
                        if(reduccion == 25) pila.push(59);
                        else {
                            System.out.println("Error en la reducción del estado 25.");
                            return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                                preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.COMMA){
                            reduccion = 25;
                        } else if(preanalisis.tipo == TipoToken.AND){
                            pila.push(60); next(); operadorLogical.push(previous());
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba 'and', 'or', '=', ';', ')' o ','");
                            return false;
                        }
                    }
                    break;
                case 26:
                    if(reduccion != 0){
                        if(reduccion == 27) pila.push(61);
                        else {
                            System.out.println("Error en la reducción del estado 26.");
                            return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                                preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.COMMA){
                            reduccion = 27;
                        } else if(preanalisis.tipo == TipoToken.BANG_EQUAL){
                            pila.push(92); next(); operadorBinary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.EQUAL_EQUAL){
                            pila.push(93); next(); operadorBinary.push(previous());
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba '!', 'and', 'or', '=', ';', ')' o ','");
                            return false;
                        }
                    }
                    break;
                case 27:
                    if(reduccion != 0) {
                        if (reduccion == 29) pila.push(64);
                        else {
                            System.out.println("Error en la reducción del estado 27.");
                            return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                                preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.BANG_EQUAL ||
                                preanalisis.tipo == TipoToken.EQUAL_EQUAL || preanalisis.tipo == TipoToken.COMMA){
                            reduccion = 29;
                        } else if(preanalisis.tipo == TipoToken.GREATER){
                            pila.push(65); next(); operadorBinary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.LESS){
                            pila.push(66); next(); operadorBinary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.GREATER_EQUAL){
                            pila.push(94); next(); operadorBinary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.LESS_EQUAL){
                            pila.push(96); next(); operadorBinary.push(previous());
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba '>', '<', '!', 'and', 'or', '=', ';', ')' o ','");
                            return false;
                        }
                    }
                    break;
                case 28:
                    if(reduccion != 0) {
                        if (reduccion == 31) pila.push(67);
                        else {
                            System.out.println("Error en la reducción del estado 28.");
                            return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                                preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.BANG_EQUAL ||
                                preanalisis.tipo == TipoToken.EQUAL_EQUAL || preanalisis.tipo == TipoToken.GREATER_EQUAL || preanalisis.tipo == TipoToken.LESS_EQUAL ||
                                preanalisis.tipo == TipoToken.GREATER || preanalisis.tipo == TipoToken.LESS || preanalisis.tipo == TipoToken.COMMA){
                            reduccion = 31;
                        } else if(preanalisis.tipo == TipoToken.MINUS){
                            pila.push(68); next(); operadorBinary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.PLUS){
                            pila.push(69); next(); operadorBinary.push(previous());
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba '-', '+', '>', '<', '!', 'and', 'or', '=', ';', ')' o ','");
                            return false;
                        }
                    }
                    break;
                case 29:
                    if(reduccion != 0) {
                        if (reduccion == 33) pila.push(70);
                        else {
                            System.out.println("Error en la reducción del estado 29.");
                            return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                                preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.BANG_EQUAL ||
                                preanalisis.tipo == TipoToken.EQUAL_EQUAL || preanalisis.tipo == TipoToken.GREATER_EQUAL || preanalisis.tipo == TipoToken.LESS_EQUAL ||
                                preanalisis.tipo == TipoToken.GREATER || preanalisis.tipo == TipoToken.LESS || preanalisis.tipo == TipoToken.MINUS ||
                                preanalisis.tipo == TipoToken.PLUS || preanalisis.tipo == TipoToken.COMMA){
                            reduccion = 33;
                        } else if(preanalisis.tipo == TipoToken.SLASH){
                            pila.push(71); next(); operadorBinary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.STAR){
                            pila.push(72); next(); operadorBinary.push(previous());
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba '/', '*', '-', '+', '>', '<', '!', 'and', 'or', '=', ';', ')' o ','");
                            return false;
                        }
                    }
                    break;
                case 30:
                    if(reduccion != 0){
                        switch (reduccion){
                            case 34: pila.push(73); break;
                            case 35: pila.push(32); break;
                            case 37: pila.push(33); break;
                            default:
                                System.out.println("Error en la reducción del estado 30.");
                                return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.LEFT_PAREN){
                            pila.push(40); next();
                        } else if(preanalisis.tipo == TipoToken.BANG){
                            pila.push(30); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.MINUS){
                            pila.push(31); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.TRUE){
                            pila.push(34); next();
                        } else if(preanalisis.tipo == TipoToken.FALSE){
                            pila.push(35); next();
                        } else if(preanalisis.tipo == TipoToken.NULL){
                            pila.push(36); next();
                        } else if(preanalisis.tipo == TipoToken.NUMBER){
                            pila.push(37); next();
                        } else if(preanalisis.tipo == TipoToken.STRING){
                            pila.push(38); next();
                        } else if(preanalisis.tipo == TipoToken.IDENTIFIER){
                            pila.push(39); next();
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba '(', '!', '-', 'true', 'false', 'null', 'number', 'string' o 'id'");
                            return false;
                        }
                    }
                    break;
                case 31:
                    if(reduccion != 0){
                        switch (reduccion){
                            case 34: pila.push(74); break;
                            case 35: pila.push(32); break;
                            case 37: pila.push(33); break;
                            default:
                                System.out.println("Error en la reducción del estado 31.");
                                return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.LEFT_PAREN){
                            pila.push(40); next();
                        } else if(preanalisis.tipo == TipoToken.BANG){
                            pila.push(30); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.MINUS){
                            pila.push(31); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.TRUE){
                            pila.push(34); next();
                        } else if(preanalisis.tipo == TipoToken.FALSE){
                            pila.push(35); next();
                        } else if(preanalisis.tipo == TipoToken.NULL){
                            pila.push(36); next();
                        } else if(preanalisis.tipo == TipoToken.NUMBER){
                            pila.push(37); next();
                        } else if(preanalisis.tipo == TipoToken.STRING){
                            pila.push(38); next();
                        } else if(preanalisis.tipo == TipoToken.IDENTIFIER){
                            pila.push(39); next();
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba '(', '!', '-', 'true', 'false', 'null', 'number', 'string' o 'id'");
                            return false;
                        }
                    }
                    break;
                case 32:
                    if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                            preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.BANG_EQUAL ||
                            preanalisis.tipo == TipoToken.EQUAL_EQUAL || preanalisis.tipo == TipoToken.GREATER_EQUAL || preanalisis.tipo == TipoToken.LESS_EQUAL ||
                            preanalisis.tipo == TipoToken.GREATER || preanalisis.tipo == TipoToken.LESS || preanalisis.tipo == TipoToken.MINUS ||
                            preanalisis.tipo == TipoToken.PLUS ||preanalisis.tipo == TipoToken.SLASH ||preanalisis.tipo == TipoToken.STAR ||
                            preanalisis.tipo == TipoToken.COMMA){
                        pila.pop();
                        reduccion = 34;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba '/', '*', '-', '+', '>', '<', '!', 'and', 'or', '=', ';', ')' o ','");
                        return false;
                    }
                    break;
                case 33:
                    if(reduccion != 0){
                        if(reduccion == 36) pila.push(75);
                        else{
                            System.out.println("Error en la reducción del estado 33.");
                            return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                                preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.BANG_EQUAL ||
                                preanalisis.tipo == TipoToken.EQUAL_EQUAL || preanalisis.tipo == TipoToken.GREATER_EQUAL || preanalisis.tipo == TipoToken.LESS_EQUAL ||
                                preanalisis.tipo == TipoToken.GREATER || preanalisis.tipo == TipoToken.LESS || preanalisis.tipo == TipoToken.MINUS ||
                                preanalisis.tipo == TipoToken.PLUS ||preanalisis.tipo == TipoToken.SLASH ||preanalisis.tipo == TipoToken.STAR ||
                                preanalisis.tipo == TipoToken.COMMA){
                            reduccion = 36;
                        } else if(preanalisis.tipo == TipoToken.LEFT_PAREN) {
                            pila.push(76); next();
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba '/', '*', '-', '+', '>', '<', '!', 'and', 'or', '=', ';', '(', ')' o ','");
                            return false;
                        }
                    }
                    break;
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                    if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.LEFT_PAREN ||
                            preanalisis.tipo == TipoToken.RIGHT_PAREN || preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND ||
                            preanalisis.tipo == TipoToken.BANG_EQUAL ||preanalisis.tipo == TipoToken.EQUAL_EQUAL || preanalisis.tipo == TipoToken.GREATER_EQUAL ||
                            preanalisis.tipo == TipoToken.LESS_EQUAL || preanalisis.tipo == TipoToken.GREATER || preanalisis.tipo == TipoToken.LESS ||
                            preanalisis.tipo == TipoToken.MINUS || preanalisis.tipo == TipoToken.PLUS ||preanalisis.tipo == TipoToken.SLASH ||
                            preanalisis.tipo == TipoToken.STAR || preanalisis.tipo == TipoToken.COMMA){
                        expGeneral.push(primary(previous()));
                        System.out.println(expGeneral.peek());
                        pila.pop();
                        reduccion = 37;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba '/', '*', '-', '+', '>', '<', '!', 'and', 'or', '=', ';', '(', ')' o ','");
                        return false;
                    }
                    break;
                case 40:
                    if(reduccion != 0){
                        switch (reduccion){
                            case 19: pila.push(77); break;
                            case 20: pila.push(22); break;
                            case 22: pila.push(23); break;
                            case 24: pila.push(24); break;
                            case 26: pila.push(25); break;
                            case 28: pila.push(26); break;
                            case 30: pila.push(27); break;
                            case 32: pila.push(28); break;
                            case 34: pila.push(29); break;
                            case 35: pila.push(32); break;
                            case 37: pila.push(33); break;
                            default:
                                System.out.println("Error en la reducción del estado 40.");
                                return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.LEFT_PAREN){
                            pila.push(40); next();
                        } else if(preanalisis.tipo == TipoToken.BANG){
                            pila.push(30); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.MINUS){
                            pila.push(31); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.TRUE){
                            pila.push(34); next();
                        } else if(preanalisis.tipo == TipoToken.FALSE){
                            pila.push(35); next();
                        } else if(preanalisis.tipo == TipoToken.NULL){
                            pila.push(36); next();
                        } else if(preanalisis.tipo == TipoToken.NUMBER){
                            pila.push(37); next();
                        } else if(preanalisis.tipo == TipoToken.STRING){
                            pila.push(38); next();
                        } else if(preanalisis.tipo == TipoToken.IDENTIFIER){
                            pila.push(39); next();
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba '(', '!', '-', 'true', 'false', 'null', 'number', 'string' o 'id'");
                            return false;
                        }
                    }
                    break;
                case 41:
                case 42:
                case 43:
                    if(preanalisis.tipo == TipoToken.RIGHT_BRACE || preanalisis.tipo == TipoToken.EOF){
                        pila.pop(); pila.pop();
                        reduccion = 2;
                        blockAux.push(1);
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba '}' o '$'.");
                        return false;
                    }
                    break;
                case 44:
                    if(preanalisis.tipo == TipoToken.FUN || preanalisis.tipo == TipoToken.VAR || preanalisis.tipo == TipoToken.FOR || preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.IF ||
                            preanalisis.tipo == TipoToken.PRINT || preanalisis.tipo == TipoToken.RETURN || preanalisis.tipo == TipoToken.WHILE || preanalisis.tipo == TipoToken.LEFT_BRACE ||
                            preanalisis.tipo == TipoToken.RIGHT_BRACE || preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS || preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE ||
                            preanalisis.tipo == TipoToken.NULL || preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER ||
                            preanalisis.tipo == TipoToken.EOF){
                        pila.pop(); pila.pop();
                        reduccion = 3;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba 'fun', 'var', 'for', '(', 'if', 'print', 'return', 'while', '{', '}', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '$'");
                        return false;
                    }
                    break;
                case 45:
                    if(preanalisis.tipo == TipoToken.LEFT_PAREN){
                        pila.push(78); next();
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba '('.");
                        return false;
                    }
                    break;
                case 46:
                    if(reduccion != 0){
                        if(reduccion == 5) pila.push(79);
                        else {
                            System.out.println("Error en la reducción del estado 46.");
                            return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.SEMICOLON){
                            reduccion = 5; varAux.push(0);
                        } else if(preanalisis.tipo == TipoToken.EQUAL){
                            pila.push(80); next();
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba ';' o '='");
                            return false;
                        }
                    }
                    break;
                case 47:
                    if(preanalisis.tipo == TipoToken.FUN || preanalisis.tipo == TipoToken.VAR || preanalisis.tipo == TipoToken.FOR || preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.IF ||
                            preanalisis.tipo == TipoToken.ELSE || preanalisis.tipo == TipoToken.PRINT || preanalisis.tipo == TipoToken.RETURN || preanalisis.tipo == TipoToken.WHILE || preanalisis.tipo == TipoToken.LEFT_BRACE ||
                            preanalisis.tipo == TipoToken.RIGHT_BRACE || preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS || preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE ||
                            preanalisis.tipo == TipoToken.NULL || preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER ||
                            preanalisis.tipo == TipoToken.EOF){
                        StmtExpression se = new StmtExpression((Expression) expGeneral.pop());
                        statements.add(se);
                        statements.forEach(System.out::println);
                        pila.pop(); pila.pop();
                        reduccion = 7;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba 'fun', 'var', 'for', '(', 'if', 'else', 'print', 'return', 'while', '{', '}', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '$'");
                        return false;
                    }
                    break;
                case 48:
                    if(reduccion != 0){
                        switch (reduccion){
                            case 4: pila.push(82); break;
                            case 7: pila.push(83); break;
                            case 9: pila.push(81); break;
                            case 19: pila.push(15); break;
                            case 20: pila.push(22); break;
                            case 22: pila.push(23); break;
                            case 24: pila.push(24); break;
                            case 26: pila.push(25); break;
                            case 28: pila.push(26); break;
                            case 30: pila.push(27); break;
                            case 32: pila.push(28); break;
                            case 34: pila.push(29); break;
                            case 35: pila.push(32); break;
                            case 37: pila.push(33); break;
                            default:
                                System.out.println("Error en la reducción del estado 48.");
                                return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.VAR){
                            pila.push(7); next();
                        } else if(preanalisis.tipo == TipoToken.SEMICOLON){
                            pila.push(84); next();
                        } else if(preanalisis.tipo == TipoToken.LEFT_PAREN){
                            pila.push(40); next();
                        } else if(preanalisis.tipo == TipoToken.BANG){
                            pila.push(30); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.MINUS){
                            pila.push(31); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.TRUE){
                            pila.push(34); next();
                        } else if(preanalisis.tipo == TipoToken.FALSE){
                            pila.push(35); next();
                        } else if(preanalisis.tipo == TipoToken.NULL){
                            pila.push(36); next();
                        } else if(preanalisis.tipo == TipoToken.NUMBER){
                            pila.push(37); next();
                        } else if(preanalisis.tipo == TipoToken.STRING){
                            pila.push(38); next();
                        } else if(preanalisis.tipo == TipoToken.IDENTIFIER){
                            pila.push(39); next();
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba 'var', ';', '(', '!', '-', 'true', 'false', 'null', 'number', 'string' o 'id'");
                            return false;
                        }
                    }
                    break;
                case 49:
                    if(reduccion != 0){
                        switch (reduccion){
                            case 19: pila.push(85); break;
                            case 20: pila.push(22); break;
                            case 22: pila.push(23); break;
                            case 24: pila.push(24); break;
                            case 26: pila.push(25); break;
                            case 28: pila.push(26); break;
                            case 30: pila.push(27); break;
                            case 32: pila.push(28); break;
                            case 34: pila.push(29); break;
                            case 35: pila.push(32); break;
                            case 37: pila.push(33); break;
                            default:
                                System.out.println("Error en la reducción del estado 49.");
                                return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.LEFT_PAREN){
                            pila.push(40); next();
                        } else if(preanalisis.tipo == TipoToken.BANG){
                            pila.push(30); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.MINUS){
                            pila.push(31); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.TRUE){
                            pila.push(34); next();
                        } else if(preanalisis.tipo == TipoToken.FALSE){
                            pila.push(35); next();
                        } else if(preanalisis.tipo == TipoToken.NULL){
                            pila.push(36); next();
                        } else if(preanalisis.tipo == TipoToken.NUMBER){
                            pila.push(37); next();
                        } else if(preanalisis.tipo == TipoToken.STRING){
                            pila.push(38); next();
                        } else if(preanalisis.tipo == TipoToken.IDENTIFIER){
                            pila.push(39); next();
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba '(', '!', '-', 'true', 'false', 'null', 'number', 'string' o 'id'");
                            return false;
                        }
                    }
                    break;
                case 50:
                    if(preanalisis.tipo == TipoToken.SEMICOLON){
                        pila.push(152); next();
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba ';'");
                        return false;
                    }
                    break;
                case 51:
                    if(preanalisis.tipo == TipoToken.SEMICOLON){
                        pila.push(86); next();
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba ';'");
                        return false;
                    }
                    break;
                case 52:
                    if(preanalisis.tipo == TipoToken.SEMICOLON){
                        pila.pop();
                        reduccion = 16; returnAux.push(1);
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba ';'");
                        return false;
                    }
                    break;
                case 53:
                    if(reduccion != 0){
                        switch (reduccion){
                            case 19: pila.push(87); break;
                            case 20: pila.push(22); break;
                            case 22: pila.push(23); break;
                            case 24: pila.push(24); break;
                            case 26: pila.push(25); break;
                            case 28: pila.push(26); break;
                            case 30: pila.push(27); break;
                            case 32: pila.push(28); break;
                            case 34: pila.push(29); break;
                            case 35: pila.push(32); break;
                            case 37: pila.push(33); break;
                            default:
                                System.out.println("Error en la reducción del estado 53.");
                                return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.LEFT_PAREN){
                            pila.push(40); next();
                        } else if(preanalisis.tipo == TipoToken.BANG){
                            pila.push(30); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.MINUS){
                            pila.push(31); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.TRUE){
                            pila.push(34); next();
                        } else if(preanalisis.tipo == TipoToken.FALSE){
                            pila.push(35); next();
                        } else if(preanalisis.tipo == TipoToken.NULL){
                            pila.push(36); next();
                        } else if(preanalisis.tipo == TipoToken.NUMBER){
                            pila.push(37); next();
                        } else if(preanalisis.tipo == TipoToken.STRING){
                            pila.push(38); next();
                        } else if(preanalisis.tipo == TipoToken.IDENTIFIER){
                            pila.push(39); next();
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba '(', '!', '-', 'true', 'false', 'null', 'number', 'string' o 'id'");
                            return false;
                        }
                    }
                    break;
                case 54:
                    if(preanalisis.tipo == TipoToken.RIGHT_BRACE){
                        pila.push(88); next();
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba '}'");
                        return false;
                    }
                    break;
                case 55:
                    if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.RIGHT_PAREN || preanalisis.tipo == TipoToken.COMMA){
                        pila.pop(); pila.pop();
                        reduccion = 20;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba ';', ')' o ','");
                        return false;
                    }
                    break;
                case 56:
                    if(reduccion != 0){
                        switch (reduccion){
                            case 19: pila.push(89); break;
                            case 20: pila.push(22); break;
                            case 22: pila.push(23); break;
                            case 24: pila.push(24); break;
                            case 26: pila.push(25); break;
                            case 28: pila.push(26); break;
                            case 30: pila.push(27); break;
                            case 32: pila.push(28); break;
                            case 34: pila.push(29); break;
                            case 35: pila.push(32); break;
                            case 37: pila.push(33); break;
                            default:
                                System.out.println("Error en la reducción del estado 56.");
                                return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.LEFT_PAREN){
                            pila.push(40); next();
                        } else if(preanalisis.tipo == TipoToken.BANG){
                            pila.push(30); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.MINUS){
                            pila.push(31); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.TRUE){
                            pila.push(34); next();
                        } else if(preanalisis.tipo == TipoToken.FALSE){
                            pila.push(35); next();
                        } else if(preanalisis.tipo == TipoToken.NULL){
                            pila.push(36); next();
                        } else if(preanalisis.tipo == TipoToken.NUMBER){
                            pila.push(37); next();
                        } else if(preanalisis.tipo == TipoToken.STRING){
                            pila.push(38); next();
                        } else if(preanalisis.tipo == TipoToken.IDENTIFIER){
                            pila.push(39); next();
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba '(', '!', '-', 'true', 'false', 'null', 'number', 'string' o 'id'");
                            return false;
                        }
                    }
                    break;
                case 57:
                    if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                            preanalisis.tipo == TipoToken.COMMA){
                        pila.pop(); pila.pop();
                        reduccion = 22;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba '=', ';', ')' o ','");
                        return false;
                    }
                    break;
                case 58:
                    if(reduccion != 0){
                        switch (reduccion){
                            case 24: pila.push(90); break;
                            case 26: pila.push(25); break;
                            case 28: pila.push(26); break;
                            case 30: pila.push(27); break;
                            case 32: pila.push(28); break;
                            case 34: pila.push(29); break;
                            case 35: pila.push(32); break;
                            case 37: pila.push(33); break;
                            default:
                                System.out.println("Error en la reducción del estado 58.");
                                return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.LEFT_PAREN){
                            pila.push(40); next();
                        } else if(preanalisis.tipo == TipoToken.BANG){
                            pila.push(30); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.MINUS){
                            pila.push(31); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.TRUE){
                            pila.push(34); next();
                        } else if(preanalisis.tipo == TipoToken.FALSE){
                            pila.push(35); next();
                        } else if(preanalisis.tipo == TipoToken.NULL){
                            pila.push(36); next();
                        } else if(preanalisis.tipo == TipoToken.NUMBER){
                            pila.push(37); next();
                        } else if(preanalisis.tipo == TipoToken.STRING){
                            pila.push(38); next();
                        } else if(preanalisis.tipo == TipoToken.IDENTIFIER){
                            pila.push(39); next();
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba '(', '!', '-', 'true', 'false', 'null', 'number', 'string' o 'id'");
                            return false;
                        }
                    }
                    break;
                case 59:
                    if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                            preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.COMMA){
                        pila.pop(); pila.pop();
                        reduccion = 24;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba 'or', '=', ';', ')' o ','");
                        return false;
                    }
                    break;
                case 60:
                    if(reduccion != 0){
                        switch (reduccion){
                            case 26: pila.push(91); break;
                            case 28: pila.push(26); break;
                            case 30: pila.push(27); break;
                            case 32: pila.push(28); break;
                            case 34: pila.push(29); break;
                            case 35: pila.push(32); break;
                            case 37: pila.push(33); break;
                            default:
                                System.out.println("Error en la reducción del estado 60.");
                                return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.LEFT_PAREN){
                            pila.push(40); next();
                        } else if(preanalisis.tipo == TipoToken.BANG){
                            pila.push(30); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.MINUS){
                            pila.push(31); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.TRUE){
                            pila.push(34); next();
                        } else if(preanalisis.tipo == TipoToken.FALSE){
                            pila.push(35); next();
                        } else if(preanalisis.tipo == TipoToken.NULL){
                            pila.push(36); next();
                        } else if(preanalisis.tipo == TipoToken.NUMBER){
                            pila.push(37); next();
                        } else if(preanalisis.tipo == TipoToken.STRING){
                            pila.push(38); next();
                        } else if(preanalisis.tipo == TipoToken.IDENTIFIER){
                            pila.push(39); next();
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba '(', '!', '-', 'true', 'false', 'null', 'number', 'string' o 'id'");
                            return false;
                        }
                    }
                    break;
                case 61:
                    if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                            preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.COMMA){
                        pila.pop(); pila.pop();
                        reduccion = 26;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba 'and', 'or', '=', ';', ')' o ','");
                        return false;
                    }
                    break;
                case 64:
                    if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                            preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.BANG_EQUAL ||
                            preanalisis.tipo == TipoToken.EQUAL_EQUAL || preanalisis.tipo == TipoToken.COMMA){
                        pila.pop(); pila.pop();
                        reduccion = 28;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba '!', 'and', 'or', '=', ';', ')' o ','");
                        return false;
                    }
                    break;
                case 65:
                    if(reduccion != 0){
                        switch (reduccion){
                            case 30: pila.push(95); break;
                            case 32: pila.push(28); break;
                            case 34: pila.push(29); break;
                            case 35: pila.push(32); break;
                            case 37: pila.push(33); break;
                            default:
                                System.out.println("Error en la reducción del estado 65.");
                                return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.LEFT_PAREN){
                            pila.push(40); next();
                        } else if(preanalisis.tipo == TipoToken.BANG){
                            pila.push(30); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.MINUS){
                            pila.push(31); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.TRUE){
                            pila.push(34); next();
                        } else if(preanalisis.tipo == TipoToken.FALSE){
                            pila.push(35); next();
                        } else if(preanalisis.tipo == TipoToken.NULL){
                            pila.push(36); next();
                        } else if(preanalisis.tipo == TipoToken.NUMBER){
                            pila.push(37); next();
                        } else if(preanalisis.tipo == TipoToken.STRING){
                            pila.push(38); next();
                        } else if(preanalisis.tipo == TipoToken.IDENTIFIER){
                            pila.push(39); next();
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba '(', '!', '-', 'true', 'false', 'null', 'number', 'string' o 'id'");
                            return false;
                        }
                    }
                    break;
                case 66:
                    if(reduccion != 0){
                        switch (reduccion){
                            case 30: pila.push(97); break;
                            case 32: pila.push(28); break;
                            case 34: pila.push(29); break;
                            case 35: pila.push(32); break;
                            case 37: pila.push(33); break;
                            default:
                                System.out.println("Error en la reducción del estado 66.");
                                return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.LEFT_PAREN){
                            pila.push(40); next();
                        } else if(preanalisis.tipo == TipoToken.BANG){
                            pila.push(30); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.MINUS){
                            pila.push(31); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.TRUE){
                            pila.push(34); next();
                        } else if(preanalisis.tipo == TipoToken.FALSE){
                            pila.push(35); next();
                        } else if(preanalisis.tipo == TipoToken.NULL){
                            pila.push(36); next();
                        } else if(preanalisis.tipo == TipoToken.NUMBER){
                            pila.push(37); next();
                        } else if(preanalisis.tipo == TipoToken.STRING){
                            pila.push(38); next();
                        } else if(preanalisis.tipo == TipoToken.IDENTIFIER){
                            pila.push(39); next();
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba '(', '!', '-', 'true', 'false', 'null', 'number', 'string' o 'id'");
                            return false;
                        }
                    }
                    break;
                case 67:
                    if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                            preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.BANG_EQUAL ||
                            preanalisis.tipo == TipoToken.EQUAL_EQUAL || preanalisis.tipo == TipoToken.GREATER_EQUAL || preanalisis.tipo == TipoToken.LESS_EQUAL ||
                            preanalisis.tipo == TipoToken.GREATER || preanalisis.tipo == TipoToken.LESS || preanalisis.tipo == TipoToken.COMMA){
                        pila.pop(); pila.pop();
                        reduccion = 30;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba '>', '<', '!', 'and', 'or', '=', ';', ')' o ','");
                        return false;
                    }
                    break;
                case 68:
                    if(reduccion != 0){
                        switch (reduccion){
                            case 32: pila.push(98); break;
                            case 34: pila.push(29); break;
                            case 35: pila.push(32); break;
                            case 37: pila.push(33); break;
                            default:
                                System.out.println("Error en la reducción del estado 68.");
                                return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.LEFT_PAREN){
                            pila.push(40); next();
                        } else if(preanalisis.tipo == TipoToken.BANG){
                            pila.push(30); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.MINUS){
                            pila.push(31); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.TRUE){
                            pila.push(34); next();
                        } else if(preanalisis.tipo == TipoToken.FALSE){
                            pila.push(35); next();
                        } else if(preanalisis.tipo == TipoToken.NULL){
                            pila.push(36); next();
                        } else if(preanalisis.tipo == TipoToken.NUMBER){
                            pila.push(37); next();
                        } else if(preanalisis.tipo == TipoToken.STRING){
                            pila.push(38); next();
                        } else if(preanalisis.tipo == TipoToken.IDENTIFIER){
                            pila.push(39); next();
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba '(', '!', '-', 'true', 'false', 'null', 'number', 'string' o 'id'");
                            return false;
                        }
                    }
                    break;
                case 69:
                    if(reduccion != 0){
                        switch (reduccion){
                            case 32: pila.push(99); break;
                            case 34: pila.push(29); break;
                            case 35: pila.push(32); break;
                            case 37: pila.push(33); break;
                            default:
                                System.out.println("Error en la reducción del estado 69.");
                                return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.LEFT_PAREN){
                            pila.push(40); next();
                        } else if(preanalisis.tipo == TipoToken.BANG){
                            pila.push(30); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.MINUS){
                            pila.push(31); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.TRUE){
                            pila.push(34); next();
                        } else if(preanalisis.tipo == TipoToken.FALSE){
                            pila.push(35); next();
                        } else if(preanalisis.tipo == TipoToken.NULL){
                            pila.push(36); next();
                        } else if(preanalisis.tipo == TipoToken.NUMBER){
                            pila.push(37); next();
                        } else if(preanalisis.tipo == TipoToken.STRING){
                            pila.push(38); next();
                        } else if(preanalisis.tipo == TipoToken.IDENTIFIER){
                            pila.push(39); next();
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba '(', '!', '-', 'true', 'false', 'null', 'number', 'string' o 'id'");
                            return false;
                        }
                    }
                    break;
                case 70:
                    if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                            preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.BANG_EQUAL ||
                            preanalisis.tipo == TipoToken.EQUAL_EQUAL || preanalisis.tipo == TipoToken.GREATER_EQUAL || preanalisis.tipo == TipoToken.LESS_EQUAL ||
                            preanalisis.tipo == TipoToken.GREATER || preanalisis.tipo == TipoToken.LESS || preanalisis.tipo == TipoToken.MINUS ||
                            preanalisis.tipo == TipoToken.PLUS || preanalisis.tipo == TipoToken.COMMA){
                        pila.pop(); pila.pop();
                        reduccion = 32;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba '-', '+', '>', '<', '!', 'and', 'or', '=', ';', ')' o ','");
                        return false;
                    }
                    break;
                case 71:
                    if(reduccion != 0){
                        switch (reduccion){
                            case 34: pila.push(100); break;
                            case 35: pila.push(32); break;
                            case 37: pila.push(33); break;
                            default:
                                System.out.println("Error en la reducción del estado 71.");
                                return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.LEFT_PAREN){
                            pila.push(40); next();
                        } else if(preanalisis.tipo == TipoToken.BANG){
                            pila.push(30); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.MINUS){
                            pila.push(31); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.TRUE){
                            pila.push(34); next();
                        } else if(preanalisis.tipo == TipoToken.FALSE){
                            pila.push(35); next();
                        } else if(preanalisis.tipo == TipoToken.NULL){
                            pila.push(36); next();
                        } else if(preanalisis.tipo == TipoToken.NUMBER){
                            pila.push(37); next();
                        } else if(preanalisis.tipo == TipoToken.STRING){
                            pila.push(38); next();
                        } else if(preanalisis.tipo == TipoToken.IDENTIFIER){
                            pila.push(39); next();
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba '(', '!', '-', 'true', 'false', 'null', 'number', 'string' o 'id'");
                            return false;
                        }
                    }
                    break;
                case 72:
                    if(reduccion != 0){
                        switch (reduccion){
                            case 34: pila.push(101); break;
                            case 35: pila.push(32); break;
                            case 37: pila.push(33); break;
                            default:
                                System.out.println("Error en la reducción del estado 72.");
                                return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.LEFT_PAREN){
                            pila.push(40); next();
                        } else if(preanalisis.tipo == TipoToken.BANG){
                            pila.push(30); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.MINUS){
                            pila.push(31); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.TRUE){
                            pila.push(34); next();
                        } else if(preanalisis.tipo == TipoToken.FALSE){
                            pila.push(35); next();
                        } else if(preanalisis.tipo == TipoToken.NULL){
                            pila.push(36); next();
                        } else if(preanalisis.tipo == TipoToken.NUMBER){
                            pila.push(37); next();
                        } else if(preanalisis.tipo == TipoToken.STRING){
                            pila.push(38); next();
                        } else if(preanalisis.tipo == TipoToken.IDENTIFIER){
                            pila.push(39); next();
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba '(', '!', '-', 'true', 'false', 'null', 'number', 'string' o 'id'");
                            return false;
                        }
                    }
                    break;
                case 73:
                case 74:
                    if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                            preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.BANG_EQUAL ||
                            preanalisis.tipo == TipoToken.EQUAL_EQUAL || preanalisis.tipo == TipoToken.GREATER_EQUAL || preanalisis.tipo == TipoToken.LESS_EQUAL ||
                            preanalisis.tipo == TipoToken.GREATER || preanalisis.tipo == TipoToken.LESS || preanalisis.tipo == TipoToken.MINUS ||
                            preanalisis.tipo == TipoToken.PLUS || preanalisis.tipo == TipoToken.SLASH || preanalisis.tipo == TipoToken.STAR ||
                            preanalisis.tipo == TipoToken.COMMA){
                        Expression right = (Expression) expGeneral.pop();
                        ExprUnary eu = new ExprUnary((Token) operadorUnary.pop(), right);
                        expGeneral.push(eu);
                        System.out.println(expGeneral.peek());
                        pila.pop(); pila.pop();
                        reduccion = 34;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba '/', '*', '-', '+', '>', '<', '!', 'and', 'or', '=', ';', ')' o ','");
                        return false;
                    }
                    break;
                case 75:
                    if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                            preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.BANG_EQUAL ||
                            preanalisis.tipo == TipoToken.EQUAL_EQUAL || preanalisis.tipo == TipoToken.GREATER_EQUAL || preanalisis.tipo == TipoToken.LESS_EQUAL ||
                            preanalisis.tipo == TipoToken.GREATER || preanalisis.tipo == TipoToken.LESS || preanalisis.tipo == TipoToken.MINUS ||
                            preanalisis.tipo == TipoToken.PLUS || preanalisis.tipo == TipoToken.SLASH || preanalisis.tipo == TipoToken.STAR ||
                            preanalisis.tipo == TipoToken.COMMA){
                        pila.pop(); pila.pop();
                        reduccion = 35;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba '/', '*', '-', '+', '>', '<', '!', 'and', 'or', '=', ';', ')' o ','");
                        return false;
                    }
                    break;
                case 76:
                    if(reduccion != 0){
                        switch (reduccion){
                            case 19: pila.push(103); break;
                            case 20: pila.push(22); break;
                            case 22: pila.push(23); break;
                            case 24: pila.push(24); break;
                            case 26: pila.push(25); break;
                            case 28: pila.push(26); break;
                            case 30: pila.push(27); break;
                            case 32: pila.push(28); break;
                            case 34: pila.push(29); break;
                            case 35: pila.push(32); break;
                            case 37: pila.push(33); break;
                            case 43: pila.push(102); break;
                            default:
                                System.out.println("Error en la reducción del estado 76.");
                                return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.LEFT_PAREN){
                            pila.push(40); next();
                        } else if(preanalisis.tipo == TipoToken.RIGHT_PAREN){
                            reduccion = 43;
                        } else if(preanalisis.tipo == TipoToken.BANG){
                            pila.push(30); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.MINUS){
                            pila.push(31); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.TRUE){
                            pila.push(34); next();
                        } else if(preanalisis.tipo == TipoToken.FALSE){
                            pila.push(35); next();
                        } else if(preanalisis.tipo == TipoToken.NULL){
                            pila.push(36); next();
                        } else if(preanalisis.tipo == TipoToken.NUMBER){
                            pila.push(37); next();
                        } else if(preanalisis.tipo == TipoToken.STRING){
                            pila.push(38); next();
                        } else if(preanalisis.tipo == TipoToken.IDENTIFIER){
                            pila.push(39); next();
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba '(', ')', '!', '-', 'true', 'false', 'null', 'number', 'string' o 'id'");
                            return false;
                        }
                    }
                    break;
                case 77:
                    if(preanalisis.tipo == TipoToken.RIGHT_PAREN){
                        pila.push(104); next();
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba ')'");
                        return false;
                    }
                    break;
                case 78:
                    if(reduccion != 0){
                        if(reduccion == 40) pila.push(105);
                        else if(reduccion == 41) pila.push(106);
                        else {
                            System.out.println("Error en la reducción del estado 78.");
                            return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.RIGHT_PAREN){
                            reduccion = 40;
                        } else if(preanalisis.tipo == TipoToken.IDENTIFIER){
                            pila.push(107); next(); identificadores.push(previous());
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba ')' o 'id'.");
                            return false;
                        }
                    }
                    break;
                case 79:
                    if(preanalisis.tipo == TipoToken.SEMICOLON){
                        pila.push(108); next();
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba ';'");
                        return false;
                    }
                    break;
                case 80:
                    if(reduccion != 0){
                        switch (reduccion){
                            case 19: pila.push(109); break;
                            case 20: pila.push(22); break;
                            case 22: pila.push(23); break;
                            case 24: pila.push(24); break;
                            case 26: pila.push(25); break;
                            case 28: pila.push(26); break;
                            case 30: pila.push(27); break;
                            case 32: pila.push(28); break;
                            case 34: pila.push(29); break;
                            case 35: pila.push(32); break;
                            case 37: pila.push(33); break;
                            default:
                                System.out.println("Error en la reducción del estado 80.");
                                return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.LEFT_PAREN){
                            pila.push(40); next();
                        } else if(preanalisis.tipo == TipoToken.BANG){
                            pila.push(30); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.MINUS){
                            pila.push(31); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.TRUE){
                            pila.push(34); next();
                        } else if(preanalisis.tipo == TipoToken.FALSE){
                            pila.push(35); next();
                        } else if(preanalisis.tipo == TipoToken.NULL){
                            pila.push(36); next();
                        } else if(preanalisis.tipo == TipoToken.NUMBER){
                            pila.push(37); next();
                        } else if(preanalisis.tipo == TipoToken.STRING){
                            pila.push(38); next();
                        } else if(preanalisis.tipo == TipoToken.IDENTIFIER){
                            pila.push(39); next();
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba '(', '!', '-', 'true', 'false', 'null', 'number', 'string' o 'id'");
                            return false;
                        }
                    }
                    break;
                case 81:
                    if(reduccion != 0){
                        switch (reduccion){
                            case 10: pila.push(110); break;
                            case 19: pila.push(111); break;
                            case 20: pila.push(22); break;
                            case 22: pila.push(23); break;
                            case 24: pila.push(24); break;
                            case 26: pila.push(25); break;
                            case 28: pila.push(26); break;
                            case 30: pila.push(27); break;
                            case 32: pila.push(28); break;
                            case 34: pila.push(29); break;
                            case 35: pila.push(32); break;
                            case 37: pila.push(33); break;
                            default:
                                System.out.println("Error en la reducción del estado 81.");
                                return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.SEMICOLON){
                            pila.push(112); next();
                        } else if(preanalisis.tipo == TipoToken.LEFT_PAREN){
                            pila.push(40); next();
                        } else if(preanalisis.tipo == TipoToken.BANG){
                            pila.push(30); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.MINUS){
                            pila.push(31); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.TRUE){
                            pila.push(34); next();
                        } else if(preanalisis.tipo == TipoToken.FALSE){
                            pila.push(35); next();
                        } else if(preanalisis.tipo == TipoToken.NULL){
                            pila.push(36); next();
                        } else if(preanalisis.tipo == TipoToken.NUMBER){
                            pila.push(37); next();
                        } else if(preanalisis.tipo == TipoToken.STRING){
                            pila.push(38); next();
                        } else if(preanalisis.tipo == TipoToken.IDENTIFIER){
                            pila.push(39); next();
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba ';', '(', '!', '-', 'true', 'false', 'null', 'number', 'string' o 'id'");
                            return false;
                        }
                    }
                    break;
                case 82:
                    if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.BANG ||
                            preanalisis.tipo == TipoToken.MINUS || preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE ||
                            preanalisis.tipo == TipoToken.NULL || preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING ||
                            preanalisis.tipo == TipoToken.IDENTIFIER){
                        pila.pop();
                        reduccion = 9;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba ';', '(', '!', '-', 'true', 'false', 'null', 'number', 'string' o 'id'");
                        return false;
                    }
                    break;
                case 83:
                    if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.BANG ||
                            preanalisis.tipo == TipoToken.MINUS || preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE ||
                            preanalisis.tipo == TipoToken.NULL || preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING ||
                            preanalisis.tipo == TipoToken.IDENTIFIER){
                        pila.pop();
                        reduccion = 9;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba ';', '(', '!', '-', 'true', 'false', 'null', 'number', 'string' o 'id'");
                        return false;
                    }
                    break;
                case 84:
                    if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.BANG ||
                            preanalisis.tipo == TipoToken.MINUS || preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE ||
                            preanalisis.tipo == TipoToken.NULL || preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING ||
                            preanalisis.tipo == TipoToken.IDENTIFIER){
                        pila.pop();
                        reduccion = 9;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba ';', '(', '!', '-', 'true', 'false', 'null', 'number', 'string' o 'id'");
                        return false;
                    }
                    break;
                case 85:
                    if(preanalisis.tipo == TipoToken.RIGHT_PAREN){
                        pila.push(113); next();
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba ')'");
                        return false;
                    }
                    break;
                case 86:
                    if(preanalisis.tipo == TipoToken.FUN || preanalisis.tipo == TipoToken.VAR || preanalisis.tipo == TipoToken.FOR ||
                            preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.IF || preanalisis.tipo == TipoToken.ELSE ||
                            preanalisis.tipo == TipoToken.PRINT || preanalisis.tipo == TipoToken.RETURN || preanalisis.tipo == TipoToken.WHILE ||
                            preanalisis.tipo == TipoToken.LEFT_BRACE || preanalisis.tipo == TipoToken.RIGHT_BRACE || preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS ||
                            preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL ||
                            preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER ||
                            preanalisis.tipo == TipoToken.EOF){
                        StmtReturn sr;
                        int a = (int) returnAux.pop();
                        if(a == 1) sr = new StmtReturn((Expression) expGeneral.pop());
                        else sr = new StmtReturn(null);
                        statements.add(sr);
                        statements.forEach(System.out::println);
                        pila.pop(); pila.pop(); pila.pop();
                        reduccion = 15;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba 'fun', 'var', 'for', '(', 'if', 'else', 'print', 'return', 'while', '{', '}', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '$'");
                        return false;
                    }
                    break;
                case 87:
                    if(preanalisis.tipo == TipoToken.RIGHT_PAREN){
                        pila.push(114); next();
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba ')'");
                        return false;
                    }
                    break;
                case 88:
                    if(preanalisis.tipo == TipoToken.FUN || preanalisis.tipo == TipoToken.VAR || preanalisis.tipo == TipoToken.FOR ||
                            preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.IF || preanalisis.tipo == TipoToken.ELSE ||
                            preanalisis.tipo == TipoToken.PRINT || preanalisis.tipo == TipoToken.RETURN || preanalisis.tipo == TipoToken.WHILE ||
                            preanalisis.tipo == TipoToken.LEFT_BRACE || preanalisis.tipo == TipoToken.RIGHT_BRACE || preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS ||
                            preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL ||
                            preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER ||
                            preanalisis.tipo == TipoToken.EOF){
                        //Collections.reverse(statements);
                        int aux = 1;
                        int k = 0;
                        List<Statement> copy;
                        statements.removeAll(statementsAux);
                        if(blockAux.size() > 1){
                            if(blockAux.size() > 2){
                                while(Objects.equals(blockAux.get(aux).toString(), "1") && Objects.equals(blockAux.get(aux+1).toString(), "0")){
                                    aux+=2; k++;
                                    if(blockAux.size() - 2 <= aux) {
                                        help++;
                                        aux -= help;
                                        break;
                                    }
                                }

                                /*while (k>0){
                                    blockAux.pop(); blockAux.pop();
                                    k--;
                                }*/
                            }
                            copy = statements.stream()
                                    .skip(aux-1 +saux)
                                    .collect(Collectors.toList());
                        } else {
                            copy = statements.stream()
                                    .collect(Collectors.toList());
                        }

                        //if(help == 0) help++;
                        StmtExpression inc;
                        int a = 0;
                        if(!forAux.isEmpty()) a = (int) forAux.pop();
                        if(a == 1){
                            inc = new StmtExpression((Expression) expGeneral.pop());
                            copy.add(inc);
                        }
                        StmtBlock sb = new StmtBlock(copy);
                        for(int j = 0, c = saux - 2; j < copy.size() && !statements.isEmpty() && c!=saux; j++){
                            statements.remove(aux-1 + saux); if(saux > 0) saux--;
                        }
                        for(int j = 0; j < statementsAux.size(); j++) statements.add(statementsAux.get(j));
                        statements.add(sb);
                        saux = statementsAux.size();
                        statementsAux.clear();
                        statements.forEach(System.out::println);

                        /*int k;
                        while(!Objects.equals(block.peek().toString(), "0")){
                            if(block.peek().toString().charAt(0) != '{') aux.add((Statement) block.pop());
                            else{
                                block.pop();
                            }
                        }

                        block.pop();
                        Collections.reverse(aux);
                        StmtBlock sb = new StmtBlock(aux);
                        if(help == 1) blockIndex--;
                        for(int z = 0; z < blockIndex + help; z++){
                            if(!statements.isEmpty()){
                                if(help == 1){
                                    statements.remove(blockIndex); break;
                                }
                                else statements.remove(0);
                            }
                        }

                        if(!sb.toString().equals("{  }")) numBlocks++;
                        blockIndex = numBlocks; help = 1;
                        statements.add(sb);
                        if(!block.empty()){
                            if(block.peek().toString().equals("0") && blockIndex != 0){
                                block.push(sb); blockIndex--;
                            }
                        }*/
                        pila.pop(); pila.pop(); pila.pop();
                        reduccion = 18;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba 'fun', 'var', 'for', '(', 'if', 'else', 'print', 'return', 'while', '{', '}', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '$'");
                        return false;
                    }
                    break;
                case 89:
                    if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.RIGHT_PAREN || preanalisis.tipo == TipoToken.COMMA){
                        Expression value = (Expression) expGeneral.pop();
                        expGeneral.pop();
                        ExprAssign ea = new ExprAssign((Token) operadorAssign.pop(), value);
                        expGeneral.push(ea);
                        System.out.println(expGeneral.peek());
                        pila.pop(); pila.pop();
                        reduccion = 21;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba ';', ')' o ','");
                        return false;
                    }
                    break;
                case 90:
                    if(reduccion != 0){
                        if(reduccion == 23) pila.push(115);
                        else {
                            System.out.println("Error en la reducción del estado 90.");
                            return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                                preanalisis.tipo == TipoToken.COMMA){
                            reduccion = 23;
                        } else if(preanalisis.tipo == TipoToken.OR){
                            pila.push(58); next(); operadorLogical.push(previous());
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba 'or', '=', ';', ')' o ','");
                            return false;
                        }
                    }
                    break;
                case 91:
                    if(reduccion != 0){
                        if(reduccion == 25) pila.push(116);
                        else {
                            System.out.println("Error en la reducción del estado 91.");
                            return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                                preanalisis.tipo == TipoToken.OR ||  preanalisis.tipo == TipoToken.COMMA){
                            reduccion = 25;
                        } else if(preanalisis.tipo == TipoToken.AND){
                            pila.push(60); next(); operadorLogical.push(previous());
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba 'or', '=', ';', ')' o ','");
                            return false;
                        }
                    }
                    break;
                case 92:
                    if(reduccion != 0){
                        switch (reduccion){
                            case 28: pila.push(117); break;
                            case 30: pila.push(27); break;
                            case 32: pila.push(28); break;
                            case 34: pila.push(29); break;
                            case 35: pila.push(32); break;
                            case 37: pila.push(33); break;
                            default:
                                System.out.println("Error en la reducción del estado 92.");
                                return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.LEFT_PAREN){
                            pila.push(40); next();
                        } else if(preanalisis.tipo == TipoToken.BANG){
                            pila.push(30); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.MINUS){
                            pila.push(31); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.TRUE){
                            pila.push(34); next();
                        } else if(preanalisis.tipo == TipoToken.FALSE){
                            pila.push(35); next();
                        } else if(preanalisis.tipo == TipoToken.NULL){
                            pila.push(36); next();
                        } else if(preanalisis.tipo == TipoToken.NUMBER){
                            pila.push(37); next();
                        } else if(preanalisis.tipo == TipoToken.STRING){
                            pila.push(38); next();
                        } else if(preanalisis.tipo == TipoToken.IDENTIFIER){
                            pila.push(39); next();
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba '(', '!', '-', 'true', 'false', 'null', 'number', 'string' o 'id'");
                            return false;
                        }
                    }
                    break;
                case 93:
                    if(reduccion != 0){
                        switch (reduccion){
                            case 28: pila.push(118); break;
                            case 30: pila.push(27); break;
                            case 32: pila.push(28); break;
                            case 34: pila.push(29); break;
                            case 35: pila.push(32); break;
                            case 37: pila.push(33); break;
                            default:
                                System.out.println("Error en la reducción del estado 93.");
                                return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.LEFT_PAREN){
                            pila.push(40); next();
                        } else if(preanalisis.tipo == TipoToken.BANG){
                            pila.push(30); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.MINUS){
                            pila.push(31); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.TRUE){
                            pila.push(34); next();
                        } else if(preanalisis.tipo == TipoToken.FALSE){
                            pila.push(35); next();
                        } else if(preanalisis.tipo == TipoToken.NULL){
                            pila.push(36); next();
                        } else if(preanalisis.tipo == TipoToken.NUMBER){
                            pila.push(37); next();
                        } else if(preanalisis.tipo == TipoToken.STRING){
                            pila.push(38); next();
                        } else if(preanalisis.tipo == TipoToken.IDENTIFIER){
                            pila.push(39); next();
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba '(', '!', '-', 'true', 'false', 'null', 'number', 'string' o 'id'");
                            return false;
                        }
                    }
                    break;
                case 94:
                    if(reduccion != 0){
                        switch (reduccion){
                            case 30: pila.push(119); break;
                            case 32: pila.push(28); break;
                            case 34: pila.push(29); break;
                            case 35: pila.push(32); break;
                            case 37: pila.push(33); break;
                            default:
                                System.out.println("Error en la reducción del estado 94.");
                                return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.LEFT_PAREN){
                            pila.push(40); next();
                        } else if(preanalisis.tipo == TipoToken.BANG){
                            pila.push(30); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.MINUS){
                            pila.push(31); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.TRUE){
                            pila.push(34); next();
                        } else if(preanalisis.tipo == TipoToken.FALSE){
                            pila.push(35); next();
                        } else if(preanalisis.tipo == TipoToken.NULL){
                            pila.push(36); next();
                        } else if(preanalisis.tipo == TipoToken.NUMBER){
                            pila.push(37); next();
                        } else if(preanalisis.tipo == TipoToken.STRING){
                            pila.push(38); next();
                        } else if(preanalisis.tipo == TipoToken.IDENTIFIER){
                            pila.push(39); next();
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba '(', '!', '-', 'true', 'false', 'null', 'number', 'string' o 'id'");
                            return false;
                        }
                    }
                    break;
                case 95:
                    if(reduccion != 0){
                        if(reduccion == 29) pila.push(120);
                        else {
                            System.out.println("Error en la reducción del estado 95.");
                            return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                                preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.BANG_EQUAL ||
                                preanalisis.tipo == TipoToken.EQUAL_EQUAL || preanalisis.tipo == TipoToken.COMMA){
                            reduccion = 29;
                        } else if(preanalisis.tipo == TipoToken.GREATER){
                            pila.push(65); next(); operadorBinary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.LESS){
                            pila.push(66); next(); operadorBinary.push(previous());
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba '>', '<', 'and', 'or', '=', ';', ')' o ','");
                            return false;
                        }
                    }
                    break;
                case 96:
                    if(reduccion != 0){
                        switch (reduccion){
                            case 30: pila.push(121); break;
                            case 32: pila.push(28); break;
                            case 34: pila.push(29); break;
                            case 35: pila.push(32); break;
                            case 37: pila.push(33); break;
                            default:
                                System.out.println("Error en la reducción del estado 96.");
                                return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.LEFT_PAREN){
                            pila.push(40); next();
                        } else if(preanalisis.tipo == TipoToken.BANG){
                            pila.push(30); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.MINUS){
                            pila.push(31); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.TRUE){
                            pila.push(34); next();
                        } else if(preanalisis.tipo == TipoToken.FALSE){
                            pila.push(35); next();
                        } else if(preanalisis.tipo == TipoToken.NULL){
                            pila.push(36); next();
                        } else if(preanalisis.tipo == TipoToken.NUMBER){
                            pila.push(37); next();
                        } else if(preanalisis.tipo == TipoToken.STRING){
                            pila.push(38); next();
                        } else if(preanalisis.tipo == TipoToken.IDENTIFIER){
                            pila.push(39); next();
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba '(', '!', '-', 'true', 'false', 'null', 'number', 'string' o 'id'");
                            return false;
                        }
                    }
                    break;
                case 97:
                    if(reduccion != 0){
                        if(reduccion == 29) pila.push(122);
                        else {
                            System.out.println("Error en la reducción del estado 97.");
                            return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                                preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.BANG_EQUAL ||
                                preanalisis.tipo == TipoToken.EQUAL_EQUAL || preanalisis.tipo == TipoToken.COMMA){
                            reduccion = 29;
                        } else if(preanalisis.tipo == TipoToken.GREATER){
                            pila.push(65); next(); operadorBinary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.LESS){
                            pila.push(66); next(); operadorBinary.push(previous());
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba '>', '<', 'and', 'or', '=', ';', ')' o ','");
                            return false;
                        }
                    }
                    break;
                case 98:
                    if(reduccion != 0){
                        if(reduccion == 31) pila.push(123);
                        else {
                            System.out.println("Error en la reducción del estado 98.");
                            return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                                preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.BANG_EQUAL ||
                                preanalisis.tipo == TipoToken.EQUAL_EQUAL || preanalisis.tipo == TipoToken.GREATER_EQUAL || preanalisis.tipo == TipoToken.LESS_EQUAL ||
                                preanalisis.tipo == TipoToken.GREATER || preanalisis.tipo == TipoToken.LESS || preanalisis.tipo == TipoToken.COMMA){
                            reduccion = 31;
                        } else if(preanalisis.tipo == TipoToken.MINUS){
                            pila.push(68); next(); operadorBinary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.PLUS){
                            pila.push(69); next(); operadorBinary.push(previous());
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba '-', '+', '>', '<', 'and', 'or', '=', ';', ')' o ','");
                            return false;
                        }
                    }
                    break;
                case 99:
                    if(reduccion != 0){
                        if(reduccion == 31) pila.push(124);
                        else {
                            System.out.println("Error en la reducción del estado 99.");
                            return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                                preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.BANG_EQUAL ||
                                preanalisis.tipo == TipoToken.EQUAL_EQUAL || preanalisis.tipo == TipoToken.GREATER_EQUAL || preanalisis.tipo == TipoToken.LESS_EQUAL ||
                                preanalisis.tipo == TipoToken.GREATER || preanalisis.tipo == TipoToken.LESS || preanalisis.tipo == TipoToken.COMMA){
                            reduccion = 31;
                        } else if(preanalisis.tipo == TipoToken.MINUS){
                            pila.push(68); next(); operadorBinary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.PLUS){
                            pila.push(69); next(); operadorBinary.push(previous());
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba '-', '+', '>', '<', 'and', 'or', '=', ';', ')' o ','");
                            return false;
                        }
                    }
                    break;
                case 100:
                    if(reduccion != 0){
                        if(reduccion == 33) pila.push(125);
                        else {
                            System.out.println("Error en la reducción del estado 100.");
                            return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                                preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.BANG_EQUAL ||
                                preanalisis.tipo == TipoToken.EQUAL_EQUAL || preanalisis.tipo == TipoToken.GREATER_EQUAL || preanalisis.tipo == TipoToken.LESS_EQUAL ||
                                preanalisis.tipo == TipoToken.GREATER || preanalisis.tipo == TipoToken.LESS || preanalisis.tipo == TipoToken.MINUS ||
                                preanalisis.tipo == TipoToken.PLUS || preanalisis.tipo == TipoToken.COMMA){
                            reduccion = 33;
                        } else if(preanalisis.tipo == TipoToken.SLASH){
                            pila.push(71); next(); operadorBinary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.STAR){
                            pila.push(72); next(); operadorBinary.push(previous());
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba '/', '*', '-', '+', '>', '<', 'and', 'or', '=', ';', ')' o ','");
                            return false;
                        }
                    }
                    break;
                case 101:
                    if(reduccion != 0){
                        if(reduccion == 33) pila.push(126);
                        else {
                            System.out.println("Error en la reducción del estado 101.");
                            return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                                preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.BANG_EQUAL ||
                                preanalisis.tipo == TipoToken.EQUAL_EQUAL || preanalisis.tipo == TipoToken.GREATER_EQUAL || preanalisis.tipo == TipoToken.LESS_EQUAL ||
                                preanalisis.tipo == TipoToken.GREATER || preanalisis.tipo == TipoToken.LESS || preanalisis.tipo == TipoToken.MINUS ||
                                preanalisis.tipo == TipoToken.PLUS || preanalisis.tipo == TipoToken.COMMA){
                            reduccion = 33;
                        } else if(preanalisis.tipo == TipoToken.SLASH){
                            pila.push(71); next(); operadorBinary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.STAR){
                            pila.push(72); next(); operadorBinary.push(previous());
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba '/', '*', '-', '+', '>', '<', 'and', 'or', '=', ';', ')' o ','");
                            return false;
                        }
                    }
                    break;
                case 102:
                    if(preanalisis.tipo == TipoToken.RIGHT_PAREN){
                        pila.push(127); next();
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba ')'.");
                        return false;
                    }
                    break;
                case 103:
                    if(reduccion != 0){
                        if(reduccion == 44) pila.push(128);
                        else {
                            System.out.println("Error en la reducción del estado 103.");
                            return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.RIGHT_PAREN){
                            reduccion = 44;
                        } else if(preanalisis.tipo == TipoToken.COMMA){
                            pila.push(129); next();
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba ')' o ','");
                            return false;
                        }
                    }
                    break;
                case 104:
                    if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                            preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.BANG_EQUAL ||
                            preanalisis.tipo == TipoToken.EQUAL_EQUAL || preanalisis.tipo == TipoToken.GREATER_EQUAL || preanalisis.tipo == TipoToken.LESS_EQUAL ||
                            preanalisis.tipo == TipoToken.GREATER || preanalisis.tipo == TipoToken.LESS || preanalisis.tipo == TipoToken.MINUS ||
                            preanalisis.tipo == TipoToken.PLUS || preanalisis.tipo == TipoToken.SLASH || preanalisis.tipo == TipoToken.STAR ||
                            preanalisis.tipo == TipoToken.COMMA){
                        ExprGrouping eg = new ExprGrouping((Expression) expGeneral.pop());
                        expGeneral.push(eg);
                        System.out.println(expGeneral.peek());
                        pila.pop(); pila.pop(); pila.pop();
                        reduccion = 37;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba '/', '*', '-', '+', '>', '<', 'and', 'or', '=', ';', '(', ')' o ','");
                        return false;
                    }
                    break;
                case 105:
                    if(preanalisis.tipo == TipoToken.RIGHT_PAREN){
                        pila.push(130); next();
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba ')'.");
                        return false;
                    }
                    break;
                case 106:
                    if(preanalisis.tipo == TipoToken.RIGHT_PAREN){
                        pila.pop();
                        reduccion = 40;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba ')'.");
                        return false;
                    }
                    break;
                case 107:
                    if(reduccion != 0){
                        if(reduccion == 42) pila.push(131);
                        else {
                            System.out.println("Error en la reducción del estado 107.");
                            return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.RIGHT_PAREN){
                            reduccion = 42;
                        } else if(preanalisis.tipo == TipoToken.COMMA){
                            pila.push(132); next();
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba ')' o ','.");
                            return false;
                        }
                    }
                    break;
                case 108:
                    if(preanalisis.tipo == TipoToken.FUN || preanalisis.tipo == TipoToken.VAR || preanalisis.tipo == TipoToken.FOR ||
                            preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.IF ||
                            preanalisis.tipo == TipoToken.PRINT || preanalisis.tipo == TipoToken.RETURN || preanalisis.tipo == TipoToken.WHILE ||
                            preanalisis.tipo == TipoToken.LEFT_BRACE || preanalisis.tipo == TipoToken.RIGHT_BRACE || preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS ||
                            preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL ||
                            preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER ||
                            preanalisis.tipo == TipoToken.EOF || preanalisis.tipo == TipoToken.SEMICOLON){
                        StmtVar sv;
                        int a = (int) varAux.pop();
                        if(a == 1) sv = new StmtVar((Token) varid.pop(), (Expression) expGeneral.pop());
                        else sv = new StmtVar((Token) varid.pop(), null);
                        statements.add(sv);
                        statements.forEach(System.out::println);
                        pila.pop(); pila.pop(); pila.pop(); pila.pop();
                        reduccion = 4;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba ';', 'fun', 'var', 'for', '(', 'if', 'print', 'return', 'while', '{', '}', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '$'");
                        return false;
                    }
                    break;
                case 109:
                    if(preanalisis.tipo == TipoToken.SEMICOLON){
                        pila.pop(); pila.pop();
                        reduccion = 5; varAux.push(1);
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba ';'");
                        return false;
                    }
                    break;
                case 110:
                    if(reduccion != 0){
                        switch (reduccion){
                            case 11: pila.push(133); break;
                            case 19: pila.push(134); break;
                            case 20: pila.push(22); break;
                            case 22: pila.push(23); break;
                            case 24: pila.push(24); break;
                            case 26: pila.push(25); break;
                            case 28: pila.push(26); break;
                            case 30: pila.push(27); break;
                            case 32: pila.push(28); break;
                            case 34: pila.push(29); break;
                            case 35: pila.push(32); break;
                            case 37: pila.push(33); break;
                            default:
                                System.out.println("Error en la reducción del estado 110.");
                                return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.LEFT_PAREN){
                            pila.push(40); next();
                        } else if(preanalisis.tipo == TipoToken.RIGHT_PAREN){
                            reduccion = 11;
                            forAux.push(0);
                        } else if(preanalisis.tipo == TipoToken.BANG){
                            pila.push(30); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.MINUS){
                            pila.push(31); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.TRUE){
                            pila.push(34); next();
                        } else if(preanalisis.tipo == TipoToken.FALSE){
                            pila.push(35); next();
                        } else if(preanalisis.tipo == TipoToken.NULL){
                            pila.push(36); next();
                        } else if(preanalisis.tipo == TipoToken.NUMBER){
                            pila.push(37); next();
                        } else if(preanalisis.tipo == TipoToken.STRING){
                            pila.push(38); next();
                        } else if(preanalisis.tipo == TipoToken.IDENTIFIER){
                            pila.push(39); next();
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba '(', ')', '!', '-', 'true', 'false', 'null', 'number', 'string' o 'id'");
                            return false;
                        }
                    }
                    break;
                case 111:
                    if(preanalisis.tipo == TipoToken.SEMICOLON){
                        pila.push(135); next();
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba ';'");
                        return false;
                    }
                    break;
                case 112:
                    if(preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.RIGHT_PAREN || preanalisis.tipo == TipoToken.BANG ||
                            preanalisis.tipo == TipoToken.MINUS || preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE ||
                            preanalisis.tipo == TipoToken.NULL || preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING ||
                            preanalisis.tipo == TipoToken.IDENTIFIER){
                        pila.pop();
                        reduccion = 10;
                    }
                    break;
                case 113:
                    if(reduccion != 0){
                        switch (reduccion){
                            case 6: pila.push(136); break;
                            case 7: pila.push(8); break;
                            case 8: pila.push(9); break;
                            case 12: pila.push(10); break;
                            case 14: pila.push(11); break;
                            case 15: pila.push(12); break;
                            case 17: pila.push(13); break;
                            case 18: pila.push(14); break;
                            case 19: pila.push(15); break;
                            case 20: pila.push(22); break;
                            case 22: pila.push(23); break;
                            case 24: pila.push(24); break;
                            case 26: pila.push(25); break;
                            case 28: pila.push(26); break;
                            case 30: pila.push(27); break;
                            case 32: pila.push(28); break;
                            case 34: pila.push(29); break;
                            case 35: pila.push(32); break;
                            case 37: pila.push(33); break;
                            default:
                                System.out.println("Error en la reducción del estado 113.");
                                return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.FOR){
                            pila.push(16); next();
                        } else if(preanalisis.tipo == TipoToken.LEFT_PAREN){
                            pila.push(40); next();
                        } else if(preanalisis.tipo == TipoToken.IF){
                            pila.push(17); next();
                        } else if(preanalisis.tipo == TipoToken.PRINT){
                            pila.push(18); next();
                        } else if(preanalisis.tipo == TipoToken.RETURN){
                            pila.push(19); next();
                        } else if(preanalisis.tipo == TipoToken.WHILE){
                            pila.push(20); next();
                        } else if(preanalisis.tipo == TipoToken.LEFT_BRACE){
                            pila.push(21); next();
                        } else if(preanalisis.tipo == TipoToken.BANG){
                            pila.push(30); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.MINUS){
                            pila.push(31); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.TRUE){
                            pila.push(34); next();
                        } else if(preanalisis.tipo == TipoToken.FALSE){
                            pila.push(35); next();
                        } else if(preanalisis.tipo == TipoToken.NULL){
                            pila.push(36); next();
                        } else if(preanalisis.tipo == TipoToken.NUMBER){
                            pila.push(37); next();
                        } else if(preanalisis.tipo == TipoToken.STRING){
                            pila.push(38); next();
                        } else if(preanalisis.tipo == TipoToken.IDENTIFIER){
                            pila.push(39); next();
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba 'for', '(', 'if', 'print', 'return', 'while', '{', '!', '-', 'true', 'false', 'null', 'number', 'string' o 'id'");
                            return false;
                        }
                    }
                    break;
                case 114:
                    if(reduccion != 0){
                        switch (reduccion){
                            case 6: pila.push(137); break;
                            case 7: pila.push(8); break;
                            case 8: pila.push(9); break;
                            case 12: pila.push(10); break;
                            case 14: pila.push(11); break;
                            case 15: pila.push(12); break;
                            case 17: pila.push(13); break;
                            case 18: pila.push(14); break;
                            case 19: pila.push(15); break;
                            case 20: pila.push(22); break;
                            case 22: pila.push(23); break;
                            case 24: pila.push(24); break;
                            case 26: pila.push(25); break;
                            case 28: pila.push(26); break;
                            case 30: pila.push(27); break;
                            case 32: pila.push(28); break;
                            case 34: pila.push(29); break;
                            case 35: pila.push(32); break;
                            case 37: pila.push(33); break;
                            default:
                                System.out.println("Error en la reducción del estado 114.");
                                return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.FOR){
                            pila.push(16); next();
                        } else if(preanalisis.tipo == TipoToken.LEFT_PAREN){
                            pila.push(40); next();
                        } else if(preanalisis.tipo == TipoToken.IF){
                            pila.push(17); next();
                        } else if(preanalisis.tipo == TipoToken.PRINT){
                            pila.push(18); next();
                        } else if(preanalisis.tipo == TipoToken.RETURN){
                            pila.push(19); next();
                        } else if(preanalisis.tipo == TipoToken.WHILE){
                            pila.push(20); next();
                        } else if(preanalisis.tipo == TipoToken.LEFT_BRACE){
                            pila.push(21); next();
                        } else if(preanalisis.tipo == TipoToken.BANG){
                            pila.push(30); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.MINUS){
                            pila.push(31); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.TRUE){
                            pila.push(34); next();
                        } else if(preanalisis.tipo == TipoToken.FALSE){
                            pila.push(35); next();
                        } else if(preanalisis.tipo == TipoToken.NULL){
                            pila.push(36); next();
                        } else if(preanalisis.tipo == TipoToken.NUMBER){
                            pila.push(37); next();
                        } else if(preanalisis.tipo == TipoToken.STRING){
                            pila.push(38); next();
                        } else if(preanalisis.tipo == TipoToken.IDENTIFIER){
                            pila.push(39); next();
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba 'for', '(', 'if', 'print', 'return', 'while', '{', '!', '-', 'true', 'false', 'null', 'number', 'string' o 'id'");
                            return false;
                        }
                    }
                    break;
                case 115:
                    if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                            preanalisis.tipo == TipoToken.COMMA){
                        Expression right = (Expression) expGeneral.pop();
                        Expression left = (Expression) expGeneral.pop();
                        ExprLogical el = new ExprLogical(left, (Token) operadorLogical.pop(), right);
                        expGeneral.push(el);
                        System.out.println(expGeneral.peek());
                        pila.pop(); pila.pop(); pila.pop();
                        reduccion = 23;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba '=', ';', ')' o ','");
                        return false;
                    }
                    break;
                case 116:
                    if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                            preanalisis.tipo == TipoToken.OR ||preanalisis.tipo == TipoToken.COMMA){
                        Expression right = (Expression) expGeneral.pop();
                        Expression left = (Expression) expGeneral.pop();
                        ExprLogical el = new ExprLogical(left, (Token) operadorLogical.pop(), right);
                        expGeneral.push(el);
                        System.out.println(expGeneral.peek());
                        pila.pop(); pila.pop(); pila.pop();
                        reduccion = 25;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba 'or', '=', ';', ')' o ','");
                        return false;
                    }
                    break;
                case 117:
                    if(reduccion != 0){
                        if(reduccion == 27) pila.push(138);
                        else {
                            System.out.println("Error en la reducción del estado 117.");
                            return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                                preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.COMMA){
                            reduccion = 27;
                        } else if(preanalisis.tipo == TipoToken.BANG_EQUAL){
                            pila.push(92); next(); operadorBinary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.EQUAL_EQUAL){
                            pila.push(93); next(); operadorBinary.push(previous());
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba '!', 'and', 'or', '=', ';', ')' o ','");
                            return false;
                        }
                    }
                    break;
                case 118:
                    if(reduccion != 0){
                        if(reduccion == 27) pila.push(139);
                        else {
                            System.out.println("Error en la reducción del estado 118.");
                            return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                                preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.COMMA){
                            reduccion = 27;
                        } else if(preanalisis.tipo == TipoToken.BANG_EQUAL){
                            pila.push(92); next(); operadorBinary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.EQUAL_EQUAL){
                            pila.push(93); next(); operadorBinary.push(previous());
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba '!', 'and', 'or', '=', ';', ')' o ','");
                            return false;
                        }
                    }
                    break;
                case 119:
                    if(reduccion != 0){
                        if(reduccion == 29) pila.push(140);
                        else {
                            System.out.println("Error en la reducción del estado 119.");
                            return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                                preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.BANG_EQUAL ||
                                preanalisis.tipo == TipoToken.EQUAL_EQUAL ||
                                preanalisis.tipo == TipoToken.COMMA){
                            reduccion = 29;
                        } else if(preanalisis.tipo == TipoToken.GREATER){
                            pila.push(65); next(); operadorBinary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.LESS){
                            pila.push(66); next(); operadorBinary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.GREATER_EQUAL){
                            pila.push(94); next(); operadorBinary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.LESS_EQUAL){
                            pila.push(96); next(); operadorBinary.push(previous());
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba '>', '<', '!', 'and', 'or', '=', ';', ')' o ','");
                            return false;
                        }
                    }
                    break;
                case 120:
                    if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                            preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.BANG_EQUAL ||
                            preanalisis.tipo == TipoToken.EQUAL_EQUAL ||
                            preanalisis.tipo == TipoToken.COMMA){
                        Expression right = (Expression) expGeneral.pop();
                        Expression left = (Expression) expGeneral.pop();
                        ExprLogical el = new ExprLogical(left, (Token) operadorBinary.pop(), right);
                        expGeneral.push(el);
                        System.out.println(expGeneral.peek());
                        pila.pop(); pila.pop(); pila.pop();
                        reduccion = 29;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba '!', 'and', 'or', '=', ';', ')' o ','");
                        return false;
                    }
                    break;
                case 121:
                    if(reduccion != 0){
                        if(reduccion == 29) pila.push(141);
                        else {
                            System.out.println("Error en la reducción del estado 121.");
                            return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                                preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.BANG_EQUAL ||
                                preanalisis.tipo == TipoToken.EQUAL_EQUAL ||
                                preanalisis.tipo == TipoToken.COMMA){
                            reduccion = 29;
                        } else if(preanalisis.tipo == TipoToken.GREATER){
                            pila.push(65); next(); operadorBinary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.LESS){
                            pila.push(66); next(); operadorBinary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.GREATER_EQUAL){
                            pila.push(94); next(); operadorBinary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.LESS_EQUAL){
                            pila.push(96); next(); operadorBinary.push(previous());
                        }  else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba '>', '<', '!', 'and', 'or', '=', ';', ')' o ','");
                            return false;
                        }
                    }
                    break;
                case 122:
                    if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                            preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.BANG_EQUAL ||
                            preanalisis.tipo == TipoToken.EQUAL_EQUAL ||
                            preanalisis.tipo == TipoToken.COMMA){
                        Expression right = (Expression) expGeneral.pop();
                        Expression left = (Expression) expGeneral.pop();
                        ExprLogical el = new ExprLogical(left, (Token) operadorBinary.pop(), right);
                        expGeneral.push(el);
                        System.out.println(expGeneral.peek());
                        pila.pop(); pila.pop(); pila.pop();
                        reduccion = 29;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba '!', 'and', 'or', '=', ';', ')' o ','");
                        return false;
                    }
                    break;
                case 123:
                case 124:
                    if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                            preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.BANG_EQUAL ||
                            preanalisis.tipo == TipoToken.EQUAL_EQUAL || preanalisis.tipo == TipoToken.GREATER_EQUAL || preanalisis.tipo == TipoToken.LESS_EQUAL ||
                            preanalisis.tipo == TipoToken.GREATER || preanalisis.tipo == TipoToken.LESS || preanalisis.tipo == TipoToken.COMMA){
                        Expression right = (Expression) expGeneral.pop();
                        Expression left = (Expression) expGeneral.pop();
                        ExprBinary eb = new ExprBinary(left, (Token) operadorBinary.pop(), right);
                        expGeneral.push(eb);
                        System.out.println(expGeneral.peek());
                        pila.pop(); pila.pop(); pila.pop();
                        reduccion = 31;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba '>', '<', '!', 'and', 'or', '=', ';', ')' o ','");
                        return false;
                    }
                    break;
                case 125:
                case 126:
                    if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                            preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.BANG_EQUAL ||
                            preanalisis.tipo == TipoToken.EQUAL_EQUAL || preanalisis.tipo == TipoToken.GREATER_EQUAL || preanalisis.tipo == TipoToken.LESS_EQUAL ||
                            preanalisis.tipo == TipoToken.GREATER || preanalisis.tipo == TipoToken.LESS || preanalisis.tipo == TipoToken.MINUS ||
                            preanalisis.tipo == TipoToken.PLUS || preanalisis.tipo == TipoToken.COMMA){
                        Expression right = (Expression) expGeneral.pop();
                        Expression left = (Expression) expGeneral.pop();
                        ExprBinary eb = new ExprBinary(left, (Token) operadorBinary.pop(), right);
                        expGeneral.push(eb);
                        System.out.println(expGeneral.peek());
                        pila.pop(); pila.pop(); pila.pop();
                        reduccion = 33;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba '-', '+', '>', '<', '!', 'and', 'or', '=', ';', ')' o ','");
                        return false;
                    }
                    break;
                case 127:
                    if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                            preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.BANG_EQUAL ||
                            preanalisis.tipo == TipoToken.EQUAL_EQUAL || preanalisis.tipo == TipoToken.GREATER_EQUAL || preanalisis.tipo == TipoToken.LESS_EQUAL ||
                            preanalisis.tipo == TipoToken.GREATER || preanalisis.tipo == TipoToken.LESS || preanalisis.tipo == TipoToken.MINUS ||
                            preanalisis.tipo == TipoToken.PLUS || preanalisis.tipo == TipoToken.SLASH || preanalisis.tipo == TipoToken.STAR ||
                            preanalisis.tipo == TipoToken.COMMA){
                        Collections.reverse(arguments);
                        List<Expression> copy = arguments.stream()
                                .collect(Collectors.toList());
                        ExprCallFunction ecf = new ExprCallFunction((Expression) expGeneral.pop(), copy);
                        expGeneral.push(ecf); arguments.clear();
                        System.out.println(expGeneral.peek());
                        pila.pop(); pila.pop(); pila.pop();
                        reduccion = 36;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba '/', '*', '-', '+', '>', '<', '!', 'and', 'or', '=', ';', ')' o ','");
                        return false;
                    }
                    break;
                case 128:
                    if(preanalisis.tipo == TipoToken.RIGHT_PAREN){
                        arguments.add((Expression) expGeneral.pop());
                        StringBuilder arg = new StringBuilder();
                        for(int j = 0; j < arguments.size(); j++) {
                            arg.append(arguments.get(j));
                            if(j < arguments.size() - 1) arg.append(", ");
                        }
                        System.out.println(arg);
                        pila.pop(); pila.pop();
                        reduccion = 43;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba ')'");
                        return false;
                    }
                    break;
                case 129:
                    if(reduccion != 0){
                        switch (reduccion){
                            case 19: pila.push(142); break;
                            case 20: pila.push(22); break;
                            case 22: pila.push(23); break;
                            case 24: pila.push(24); break;
                            case 26: pila.push(25); break;
                            case 28: pila.push(26); break;
                            case 30: pila.push(27); break;
                            case 32: pila.push(28); break;
                            case 34: pila.push(29); break;
                            case 35: pila.push(32); break;
                            case 37: pila.push(33); break;
                            default:
                                System.out.println("Error en la reducción del estado 129.");
                                return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.LEFT_PAREN){
                            pila.push(40); next();
                        } else if(preanalisis.tipo == TipoToken.BANG){
                            pila.push(30); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.MINUS){
                            pila.push(31); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.TRUE){
                            pila.push(34); next();
                        } else if(preanalisis.tipo == TipoToken.FALSE){
                            pila.push(35); next();
                        } else if(preanalisis.tipo == TipoToken.NULL){
                            pila.push(36); next();
                        } else if(preanalisis.tipo == TipoToken.NUMBER){
                            pila.push(37); next();
                        } else if(preanalisis.tipo == TipoToken.STRING){
                            pila.push(38); next();
                        } else if(preanalisis.tipo == TipoToken.IDENTIFIER){
                            pila.push(39); next();
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba '(', '!', '-', 'true', 'false', 'null', 'number', 'string' o 'id'");
                            return false;
                        }
                    }
                    break;
                case 130:
                    if(reduccion != 0){
                        if(reduccion == 18) pila.push(143);
                        else {
                            System.out.println("Error en la reducción del estado 130");
                            return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.LEFT_BRACE){
                            pila.push(21); next();
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba '{'");
                            return false;
                        }
                    }
                    break;
                case 131:
                    if(preanalisis.tipo == TipoToken.RIGHT_PAREN){
                        parameters.add((Token) identificadores.pop());
                        StringBuilder par = new StringBuilder();
                        for(int j = 0; j < parameters.size(); j++) {
                            par.append(parameters.get(j).lexema);
                            if(j < parameters.size() - 1) par.append(", ");
                        }
                        System.out.println(par);
                        pila.pop(); pila.pop();
                        reduccion = 41;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba ')'");
                        return false;
                    }
                    break;
                case 132:
                    if(preanalisis.tipo == TipoToken.IDENTIFIER){
                        pila.push(144); next(); identificadores.push(previous());
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba 'id'");
                        return false;
                    }
                    break;
                case 133:
                    if(preanalisis.tipo == TipoToken.RIGHT_PAREN){
                        pila.push(145); next();
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba ')'");
                        return false;
                    }
                    break;
                case 134:
                    if(preanalisis.tipo == TipoToken.RIGHT_PAREN){
                        pila.pop();
                        reduccion = 11;
                        forAux.push(1);
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba ')'");
                        return false;
                    }
                    break;
                case 135:
                    if(preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.RIGHT_PAREN || preanalisis.tipo == TipoToken.BANG ||
                            preanalisis.tipo == TipoToken.MINUS ||preanalisis.tipo == TipoToken.TRUE ||preanalisis.tipo == TipoToken.FALSE ||
                            preanalisis.tipo == TipoToken.NULL ||preanalisis.tipo == TipoToken.NUMBER ||preanalisis.tipo == TipoToken.STRING ||
                            preanalisis.tipo == TipoToken.IDENTIFIER){
                        pila.pop(); pila.pop();
                        reduccion = 10;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba '(', ')', '!', '-', 'true', 'false', 'null', 'number', 'string' o 'id'");
                        return false;
                    }
                    break;
                case 136:
                    if(reduccion != 0){
                        if(reduccion == 13) pila.push(146);
                        else {
                            System.out.println("Error en la reducción del estado 136.");
                            return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.FUN || preanalisis.tipo == TipoToken.VAR || preanalisis.tipo == TipoToken.FOR ||
                                preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.IF || preanalisis.tipo == TipoToken.PRINT ||
                                preanalisis.tipo == TipoToken.RETURN || preanalisis.tipo == TipoToken.WHILE || preanalisis.tipo == TipoToken.LEFT_BRACE ||
                                preanalisis.tipo == TipoToken.RIGHT_BRACE || preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS ||preanalisis.tipo == TipoToken.TRUE ||
                                preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL ||preanalisis.tipo == TipoToken.NUMBER ||
                                preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER || preanalisis.tipo == TipoToken.EOF){
                            reduccion = 13;
                            elseAux.push(0);
                        } else if(preanalisis.tipo == TipoToken.ELSE){
                            pila.push(147); next();
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba 'fun', 'var', 'for', '(', 'if', 'print', 'return', 'while', '{', '}', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '$'");
                            return false;
                        }
                    }
                    break;
                case 137:
                    if(preanalisis.tipo == TipoToken.FUN || preanalisis.tipo == TipoToken.VAR || preanalisis.tipo == TipoToken.FOR ||
                            preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.IF || preanalisis.tipo == TipoToken.ELSE || preanalisis.tipo == TipoToken.PRINT ||
                            preanalisis.tipo == TipoToken.RETURN || preanalisis.tipo == TipoToken.WHILE || preanalisis.tipo == TipoToken.LEFT_BRACE ||
                            preanalisis.tipo == TipoToken.RIGHT_BRACE || preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS ||preanalisis.tipo == TipoToken.TRUE ||
                            preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL ||preanalisis.tipo == TipoToken.NUMBER ||
                            preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER || preanalisis.tipo == TipoToken.EOF){
                        StmtLoop sl = new StmtLoop((Expression) expGeneral.pop(), statements.remove(statements.size()-1));
                        statements.add(sl);
                        statements.forEach(System.out::println);
                        pila.pop(); pila.pop(); pila.pop(); pila.pop(); pila.pop();
                        reduccion = 17;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba 'fun', 'var', 'for', '(', 'if', 'else', 'print', 'return', 'while', '{', '}', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '$'");
                        return false;
                    }
                    break;
                case 138:
                    if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                            preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.COMMA){
                        Expression right = (Expression) expGeneral.pop();
                        Expression left = (Expression) expGeneral.pop();
                        ExprLogical el = new ExprLogical(left, (Token) operadorBinary.pop(), right);
                        expGeneral.push(el);
                        System.out.println(expGeneral.peek());
                        pila.pop(); pila.pop(); pila.pop();
                        reduccion = 27;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba 'and', 'or', '=', ';', ')' o ','");
                        return false;
                    }
                    break;
                case 139:
                    if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                            preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.COMMA){
                        Expression right = (Expression) expGeneral.pop();
                        Expression left = (Expression) expGeneral.pop();
                        ExprLogical el = new ExprLogical(left, (Token) operadorBinary.pop(), right);
                        expGeneral.push(el);
                        System.out.println(expGeneral.peek());
                        pila.pop(); pila.pop(); pila.pop();
                        reduccion = 27;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba 'and', 'or', '=', ';', ')' o ','");
                        return false;
                    }
                    break;
                case 140:
                    if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                            preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.COMMA ||
                            preanalisis.tipo == TipoToken.BANG_EQUAL || preanalisis.tipo == TipoToken.EQUAL_EQUAL){
                        Expression right = (Expression) expGeneral.pop();
                        Expression left = (Expression) expGeneral.pop();
                        ExprLogical el = new ExprLogical(left, (Token) operadorBinary.pop(), right);
                        expGeneral.push(el);
                        System.out.println(expGeneral.peek());
                        pila.pop(); pila.pop(); pila.pop();
                        reduccion = 29;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba '!', 'and', 'or', '=', ';', ')' o ','");
                        return false;
                    }
                    break;
                case 141:
                    if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                            preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.COMMA ||
                            preanalisis.tipo == TipoToken.BANG_EQUAL || preanalisis.tipo == TipoToken.EQUAL_EQUAL){
                        Expression right = (Expression) expGeneral.pop();
                        Expression left = (Expression) expGeneral.pop();
                        ExprLogical el = new ExprLogical(left, (Token) operadorBinary.pop(), right);
                        expGeneral.push(el);
                        System.out.println(expGeneral.peek());
                        pila.pop(); pila.pop(); pila.pop();
                        reduccion = 29;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba '!', 'and', 'or', '=', ';', ')' o ','");
                        return false;
                    }
                    break;
                case 142:
                    if(reduccion != 0){
                        if(reduccion == 44) pila.push(148);
                        else {
                            System.out.println("Error en la reducción del estado 142");
                            return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.COMMA){
                            pila.push(129); next();
                        } else if(preanalisis.tipo == TipoToken.RIGHT_PAREN){
                            reduccion = 44;
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba ',' o ')'");
                            return false;
                        }
                    }
                    break;
                case 143:
                    if(preanalisis.tipo == TipoToken.FUN || preanalisis.tipo == TipoToken.VAR || preanalisis.tipo == TipoToken.FOR ||
                            preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.IF || preanalisis.tipo == TipoToken.PRINT ||
                            preanalisis.tipo == TipoToken.RETURN || preanalisis.tipo == TipoToken.WHILE || preanalisis.tipo == TipoToken.LEFT_BRACE ||
                            preanalisis.tipo == TipoToken.RIGHT_BRACE || preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS ||preanalisis.tipo == TipoToken.TRUE ||
                            preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL ||preanalisis.tipo == TipoToken.NUMBER ||
                            preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER || preanalisis.tipo == TipoToken.EOF){
                        Collections.reverse(parameters);
                        List<Token> copy = parameters.stream()
                                .collect(Collectors.toList());
                        StmtFunction sf;
                        int a = (int) blockAux.pop();
                        if(a == 1) sf = new StmtFunction((Token) funid.pop(), copy, (StmtBlock) statements.remove(0));
                        else{
                            sf = new StmtFunction((Token) funid.pop(), copy, null);

                        }
                        statements.add(sf); parameters.clear();
                        statements.forEach(System.out::println);
                        pila.pop(); pila.pop(); pila.pop(); pila.pop(); pila.pop();
                        reduccion = 38;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba 'fun', 'var', 'for', '(', 'if', 'print', 'return', 'while', '{', '}', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '$'");
                        return false;
                    }
                    break;
                case 144:
                    if(reduccion != 0){
                        if(reduccion == 42) pila.push(149);
                        else {
                            System.out.println("Error en la reducción del estado 144");
                            return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.COMMA){
                            pila.push(132); next();
                        } else if(preanalisis.tipo == TipoToken.RIGHT_PAREN){
                            reduccion = 42;
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba 'id' o ')'");
                            return false;
                        }
                    }
                    break;
                case 145:
                    if(reduccion != 0){
                        switch (reduccion){
                            case 6: pila.push(150); break;
                            case 7: pila.push(8); break;
                            case 8: pila.push(9); break;
                            case 12: pila.push(10); break;
                            case 14: pila.push(11); break;
                            case 15: pila.push(12); break;
                            case 17: pila.push(13); break;
                            case 18: pila.push(14); break;
                            case 19: pila.push(15); break;
                            case 20: pila.push(22); break;
                            case 22: pila.push(23); break;
                            case 24: pila.push(24); break;
                            case 26: pila.push(25); break;
                            case 28: pila.push(26); break;
                            case 30: pila.push(27); break;
                            case 32: pila.push(28); break;
                            case 34: pila.push(29); break;
                            case 35: pila.push(32); break;
                            case 37: pila.push(33); break;
                            default:
                                System.out.println("Error en la reducción del estado 145.");
                                return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.FOR){
                            pila.push(16); next();
                        } else if(preanalisis.tipo == TipoToken.LEFT_PAREN){
                            pila.push(40); next();
                        } else if(preanalisis.tipo == TipoToken.IF){
                            pila.push(17); next();
                        } else if(preanalisis.tipo == TipoToken.PRINT){
                            pila.push(18); next();
                        } else if(preanalisis.tipo == TipoToken.RETURN){
                            pila.push(19); next();
                        } else if(preanalisis.tipo == TipoToken.WHILE){
                            pila.push(20); next();
                        } else if(preanalisis.tipo == TipoToken.LEFT_BRACE){
                            pila.push(21); next();
                        } else if(preanalisis.tipo == TipoToken.BANG){
                            pila.push(30); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.MINUS){
                            pila.push(31); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.TRUE){
                            pila.push(34); next();
                        } else if(preanalisis.tipo == TipoToken.FALSE){
                            pila.push(35); next();
                        } else if(preanalisis.tipo == TipoToken.NULL){
                            pila.push(36); next();
                        } else if(preanalisis.tipo == TipoToken.NUMBER){
                            pila.push(37); next();
                        } else if(preanalisis.tipo == TipoToken.STRING){
                            pila.push(38); next();
                        } else if(preanalisis.tipo == TipoToken.IDENTIFIER){
                            pila.push(39); next();
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba 'for', '(', 'if', 'print', 'return', 'while', '{', '!', '-', 'true', 'false', 'null', 'number', 'string' o 'id'");
                            return false;
                        }
                    }
                    break;
                case 146:
                    if(preanalisis.tipo == TipoToken.FUN || preanalisis.tipo == TipoToken.VAR || preanalisis.tipo == TipoToken.FOR ||
                            preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.IF || preanalisis.tipo == TipoToken.ELSE || preanalisis.tipo == TipoToken.PRINT ||
                            preanalisis.tipo == TipoToken.RETURN || preanalisis.tipo == TipoToken.WHILE || preanalisis.tipo == TipoToken.LEFT_BRACE ||
                            preanalisis.tipo == TipoToken.RIGHT_BRACE || preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS ||preanalisis.tipo == TipoToken.TRUE ||
                            preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL ||preanalisis.tipo == TipoToken.NUMBER ||
                            preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER || preanalisis.tipo == TipoToken.EOF){
                        StmtIf si;
                        int a = (int) elseAux.pop();
                        if(a == 1){
                            si = new StmtIf((Expression) expGeneral.pop(), statements.remove(statements.size()-2), statements.remove(statements.size()-1));

                        }
                        else si = new StmtIf((Expression) expGeneral.pop(), statements.remove(statements.size()-1), null);
                        statements.add(si);
                        statements.forEach(System.out::println);
                        pila.pop(); pila.pop(); pila.pop(); pila.pop(); pila.pop(); pila.pop();
                        reduccion = 12;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba 'fun', 'var', 'for', '(', 'if', 'else', 'print', 'return', 'while', '{', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '$'");
                        return false;
                    }
                    break;
                case 147:
                    if(reduccion != 0){
                        switch (reduccion){
                            case 6: pila.push(151); break;
                            case 7: pila.push(8); break;
                            case 8: pila.push(9); break;
                            case 12: pila.push(10); break;
                            case 14: pila.push(11); break;
                            case 15: pila.push(12); break;
                            case 17: pila.push(13); break;
                            case 18: pila.push(14); break;
                            case 19: pila.push(15); break;
                            case 20: pila.push(22); break;
                            case 22: pila.push(23); break;
                            case 24: pila.push(24); break;
                            case 26: pila.push(25); break;
                            case 28: pila.push(26); break;
                            case 30: pila.push(27); break;
                            case 32: pila.push(28); break;
                            case 34: pila.push(29); break;
                            case 35: pila.push(32); break;
                            case 37: pila.push(33); break;
                            default:
                                System.out.println("Error en la reducción del estado 147.");
                                return false;
                        }

                        reduccion = 0;
                    } else {
                        if(preanalisis.tipo == TipoToken.FOR){
                            pila.push(16); next();
                        } else if(preanalisis.tipo == TipoToken.LEFT_PAREN){
                            pila.push(40); next();
                        } else if(preanalisis.tipo == TipoToken.IF){
                            pila.push(17); next();
                        } else if(preanalisis.tipo == TipoToken.PRINT){
                            pila.push(18); next();
                        } else if(preanalisis.tipo == TipoToken.RETURN){
                            pila.push(19); next();
                        } else if(preanalisis.tipo == TipoToken.WHILE){
                            pila.push(20); next();
                        } else if(preanalisis.tipo == TipoToken.LEFT_BRACE){
                            pila.push(21); next();
                        } else if(preanalisis.tipo == TipoToken.BANG){
                            pila.push(30); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.MINUS){
                            pila.push(31); next(); operadorUnary.push(previous());
                        } else if(preanalisis.tipo == TipoToken.TRUE){
                            pila.push(34); next();
                        } else if(preanalisis.tipo == TipoToken.FALSE){
                            pila.push(35); next();
                        } else if(preanalisis.tipo == TipoToken.NULL){
                            pila.push(36); next();
                        } else if(preanalisis.tipo == TipoToken.NUMBER){
                            pila.push(37); next();
                        } else if(preanalisis.tipo == TipoToken.STRING){
                            pila.push(38); next();
                        } else if(preanalisis.tipo == TipoToken.IDENTIFIER){
                            pila.push(39); next();
                        } else {
                            System.out.println("ERROR ENCONTRADO: Se esperaba 'for', '(', 'if', 'print', 'return', 'while', '{', '!', '-', 'true', 'false', 'null', 'number', 'string' o 'id'");
                            return false;
                        }
                    }
                    break;
                case 148:
                    if(preanalisis.tipo == TipoToken.RIGHT_PAREN){
                        arguments.add((Expression) expGeneral.pop());
                        StringBuilder arg = new StringBuilder();
                        for(int j = 0; j < arguments.size(); j++) {
                            arg.append(arguments.get(j));
                            if(j < arguments.size() - 1) arg.append(", ");
                        }
                        System.out.println(arg);
                        pila.pop(); pila.pop(); pila.pop();
                        reduccion = 44;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba ')'");
                        return false;
                    }
                    break;
                case 149:
                    if(preanalisis.tipo == TipoToken.RIGHT_PAREN){
                        parameters.add((Token) identificadores.pop());
                        StringBuilder par = new StringBuilder();
                        for(int j = 0; j < parameters.size(); j++) {
                            par.append(parameters.get(j).lexema);
                            if(j < parameters.size() - 1) par.append(", ");
                        }
                        System.out.println(par);
                        pila.pop(); pila.pop(); pila.pop();
                        reduccion = 42;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba ')'");
                        return false;
                    }
                    break;
                case 150:
                    if(preanalisis.tipo == TipoToken.FUN || preanalisis.tipo == TipoToken.VAR || preanalisis.tipo == TipoToken.FOR ||
                            preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.IF || preanalisis.tipo == TipoToken.ELSE || preanalisis.tipo == TipoToken.PRINT ||
                            preanalisis.tipo == TipoToken.RETURN || preanalisis.tipo == TipoToken.WHILE || preanalisis.tipo == TipoToken.LEFT_BRACE ||
                            preanalisis.tipo == TipoToken.RIGHT_BRACE || preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS ||preanalisis.tipo == TipoToken.TRUE ||
                            preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL ||preanalisis.tipo == TipoToken.NUMBER ||
                            preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER || preanalisis.tipo == TipoToken.EOF){
                        StmtLoop sl; StmtExpression inc; Expression condition;

                        if(!expGeneral.isEmpty()) condition = (Expression) expGeneral.pop();
                        else condition = null;

                        Statement body = statements.remove(statements.size() - 1);
                        sl = new StmtLoop(condition, body);
                        statements.add(sl);
                        statements.forEach(System.out::println);
                        pila.pop(); pila.pop(); pila.pop(); pila.pop(); pila.pop(); pila.pop(); pila.pop();
                        reduccion = 8;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba 'fun', 'var', 'for', '(', 'if', 'else', 'print', 'return', 'while', '{', '}', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '$'");
                        return false;
                    }
                    break;
                case 151:
                    if(preanalisis.tipo == TipoToken.FUN || preanalisis.tipo == TipoToken.VAR || preanalisis.tipo == TipoToken.FOR ||
                            preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.IF || preanalisis.tipo == TipoToken.ELSE || preanalisis.tipo == TipoToken.PRINT ||
                            preanalisis.tipo == TipoToken.RETURN || preanalisis.tipo == TipoToken.WHILE || preanalisis.tipo == TipoToken.LEFT_BRACE ||
                            preanalisis.tipo == TipoToken.RIGHT_BRACE || preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS ||preanalisis.tipo == TipoToken.TRUE ||
                            preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL ||preanalisis.tipo == TipoToken.NUMBER ||
                            preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER || preanalisis.tipo == TipoToken.EOF){
                        pila.pop(); pila.pop();
                        reduccion = 13;
                        elseAux.push(1);
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba 'fun', 'var', 'for', '(', 'if', 'else', 'print', 'return', 'while', '{', '}', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '$'");
                        return false;
                    }
                    break;
                case 152:
                    if(preanalisis.tipo == TipoToken.FUN || preanalisis.tipo == TipoToken.VAR || preanalisis.tipo == TipoToken.FOR || preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.IF ||
                            preanalisis.tipo == TipoToken.ELSE || preanalisis.tipo == TipoToken.PRINT || preanalisis.tipo == TipoToken.RETURN || preanalisis.tipo == TipoToken.WHILE || preanalisis.tipo == TipoToken.LEFT_BRACE ||
                            preanalisis.tipo == TipoToken.RIGHT_BRACE || preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS || preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE ||
                            preanalisis.tipo == TipoToken.NULL || preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER ||
                            preanalisis.tipo == TipoToken.EOF){
                        StmtPrint sp = new StmtPrint((Expression) expGeneral.pop());
                        statements.add(sp);
                        statements.forEach(System.out::println);
                        pila.pop(); pila.pop(); pila.pop();
                        reduccion = 14;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba 'fun', 'var', 'for', '(', 'if', 'else', 'print', 'return', 'while', '{', '}', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '$'");
                        return false;
                    }
                    break;
            }
        }

        return false;
    }

    private void next(){
        i++;
        preanalisis = tokens.get(i);
    }

    private Expression primary(Token tok){
        if(tok.tipo == TipoToken.TRUE) return new ExprLiteral(true);
        else if(tok.tipo == TipoToken.FALSE) return new ExprLiteral(false);
        else if(tok.tipo == TipoToken.NULL) return new ExprLiteral(null);
        else if(tok.tipo == TipoToken.NUMBER) return new ExprLiteral(tok.literal);
        else if(tok.tipo == TipoToken.STRING) return new ExprLiteral(tok.literal);
        else return new ExprVariable(tok); // Identificador
    }

    private Token previous() {
        return this.tokens.get(i - 1);
    }

}
