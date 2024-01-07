import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AnalizadorSintactico implements Parser{
    private int i = 0;
    private boolean hayErrores = false;
    private Token preanalisis;
    private final List<Token> tokens;
    private List<TipoToken> terminales = new ArrayList<>();


    public AnalizadorSintactico(List<Token> tokens){
        this.tokens = tokens;
        preanalisis = this.tokens.get(i);
    }

    @Override
    public boolean parse(){
        int reduccion = 0;
        Stack pila = new Stack<>();

        pila.push(0);

        while(i < tokens.size()){
            System.out.println("Pila: " + pila.peek() + ", i: " + i + ", reduccion: " + reduccion + ", preanalisis: " + preanalisis.tipo);
            if(pila.peek().equals(0)){
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
                        reduccion = 2;
                    } else if(preanalisis.tipo == TipoToken.BANG){ pila.push(30); next();}
                    else if(preanalisis.tipo == TipoToken.MINUS){ pila.push(31); next();}
                    else if(preanalisis.tipo == TipoToken.TRUE){ pila.push(34); next();}
                    else if(preanalisis.tipo == TipoToken.FALSE){ pila.push(35); next();}
                    else if(preanalisis.tipo == TipoToken.NULL){ pila.push(36); next();}
                    else if(preanalisis.tipo == TipoToken.NUMBER){ pila.push(37); next();}
                    else if(preanalisis.tipo == TipoToken.STRING){ pila.push(38); next();}
                    else if(preanalisis.tipo == TipoToken.IDENTIFIER){ pila.push(39); next();}
                    else if(preanalisis.tipo == TipoToken.EOF){
                        reduccion = 2;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba 'fun', 'var', 'for', '(', 'if', 'print', 'return', 'while', '{', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '$'");
                        return false;
                    }
                }
            } else if(pila.peek().equals(1)){
                if(preanalisis.tipo == TipoToken.EOF){
                    System.out.println("Resultado correcto, no se presentaron errores.");
                    return true;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba '$'");
                    return false;
                }
            } else if(pila.peek().equals(2)){
                if(preanalisis.tipo == TipoToken.EOF){
                    pila.pop();
                    reduccion = 1;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba '$'");
                    return false;
                }
            } else if(pila.peek().equals(3)){
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
                        reduccion = 2;
                    } else if(preanalisis.tipo == TipoToken.BANG){ pila.push(30); next();}
                    else if(preanalisis.tipo == TipoToken.MINUS){ pila.push(31); next();}
                    else if(preanalisis.tipo == TipoToken.TRUE){ pila.push(34); next();}
                    else if(preanalisis.tipo == TipoToken.FALSE){ pila.push(35); next();}
                    else if(preanalisis.tipo == TipoToken.NULL){ pila.push(36); next();}
                    else if(preanalisis.tipo == TipoToken.NUMBER){ pila.push(37); next();}
                    else if(preanalisis.tipo == TipoToken.STRING){ pila.push(38); next();}
                    else if(preanalisis.tipo == TipoToken.IDENTIFIER){ pila.push(39); next();}
                    else if(preanalisis.tipo == TipoToken.EOF){
                        reduccion = 2;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba 'fun', 'var', 'for', '(', 'if', 'print', 'return', 'while', '{', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '$'");
                        return false;
                    }
                }
            } else if(pila.peek().equals(4)){
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
                        reduccion = 2;
                    } else if(preanalisis.tipo == TipoToken.BANG){ pila.push(30); next();}
                    else if(preanalisis.tipo == TipoToken.MINUS){ pila.push(31); next();}
                    else if(preanalisis.tipo == TipoToken.TRUE){ pila.push(34); next();}
                    else if(preanalisis.tipo == TipoToken.FALSE){ pila.push(35); next();}
                    else if(preanalisis.tipo == TipoToken.NULL){ pila.push(36); next();}
                    else if(preanalisis.tipo == TipoToken.NUMBER){ pila.push(37); next();}
                    else if(preanalisis.tipo == TipoToken.STRING){ pila.push(38); next();}
                    else if(preanalisis.tipo == TipoToken.IDENTIFIER){ pila.push(39); next();}
                    else if(preanalisis.tipo == TipoToken.EOF){
                        reduccion = 2;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba 'fun', 'var', 'for', '(', 'if', 'print', 'return', 'while', '{', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '$'");
                        return false;
                    }
                }
            } else if(pila.peek().equals(5)){
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
                        reduccion = 2;
                    } else if(preanalisis.tipo == TipoToken.BANG){ pila.push(30); next();}
                    else if(preanalisis.tipo == TipoToken.MINUS){ pila.push(31); next();}
                    else if(preanalisis.tipo == TipoToken.TRUE){ pila.push(34); next();}
                    else if(preanalisis.tipo == TipoToken.FALSE){ pila.push(35); next();}
                    else if(preanalisis.tipo == TipoToken.NULL){ pila.push(36); next();}
                    else if(preanalisis.tipo == TipoToken.NUMBER){ pila.push(37); next();}
                    else if(preanalisis.tipo == TipoToken.STRING){ pila.push(38); next();}
                    else if(preanalisis.tipo == TipoToken.IDENTIFIER){ pila.push(39); next();}
                    else if(preanalisis.tipo == TipoToken.EOF){
                        reduccion = 2;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba 'fun', 'var', 'for', '(', 'if', 'print', 'return', 'while', '{', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '$'");
                        return false;
                    }
                }
            } else if(pila.peek().equals(6)){
                if(reduccion != 0){
                    if(reduccion == 38) pila.push(44);
                    else {
                        System.out.println("Error en la reducción del estado 6.");
                        return false;
                    }
                } else {
                    if(preanalisis.tipo == TipoToken.IDENTIFIER){ pila.push(45); next();}
                    else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba 'id'.");
                        return false;
                    }
                }
            } else if(pila.peek().equals(7)){
                if(preanalisis.tipo == TipoToken.IDENTIFIER){ pila.push(46); next();}
                else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba 'id'.");
                    return false;
                }
            } else if(pila.peek().equals(8)){
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
            } else if(pila.peek().equals(9)){
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
            } else if(pila.peek().equals(10)){
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
            } else if(pila.peek().equals(11)){
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
            } else if(pila.peek().equals(12)){
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
            } else if(pila.peek().equals(13)){
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
            } else if(pila.peek().equals(14)){
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
            } else if(pila.peek().equals(15)){
                if(preanalisis.tipo == TipoToken.SEMICOLON){
                    pila.push(47); next();
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba ';'");
                    return false;
                }
            } else if(pila.peek().equals(16)){
                if(preanalisis.tipo == TipoToken.LEFT_PAREN){
                    pila.push(48); next();
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba '('");
                    return false;
                }
            } else if(pila.peek().equals(17)){
                if(preanalisis.tipo == TipoToken.LEFT_PAREN){
                    pila.push(49); next();
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba '('");
                    return false;
                }
            } else if(pila.peek().equals(18)){
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
                        pila.push(30); next();
                    } else if(preanalisis.tipo == TipoToken.MINUS){
                        pila.push(31); next();
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
            } else if(pila.peek().equals(19)){
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
                        reduccion = 16;
                    } else if(preanalisis.tipo == TipoToken.LEFT_PAREN){
                        pila.push(40); next();
                    } else if(preanalisis.tipo == TipoToken.BANG){
                        pila.push(30); next();
                    } else if(preanalisis.tipo == TipoToken.MINUS){
                        pila.push(31); next();
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
            } else if(pila.peek().equals(20)){
                if(preanalisis.tipo == TipoToken.LEFT_PAREN){
                    pila.push(53); next();
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba '('.");
                    return false;
                }
            } else if(pila.peek().equals(21)){
                if(reduccion != 0){
                    switch (reduccion){
                        case 2: pila.push(54); break;
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
                        reduccion = 2;
                    }else if(preanalisis.tipo == TipoToken.BANG){ pila.push(30); next();}
                    else if(preanalisis.tipo == TipoToken.MINUS){ pila.push(31); next();}
                    else if(preanalisis.tipo == TipoToken.TRUE){ pila.push(34); next();}
                    else if(preanalisis.tipo == TipoToken.FALSE){ pila.push(35); next();}
                    else if(preanalisis.tipo == TipoToken.NULL){ pila.push(36); next();}
                    else if(preanalisis.tipo == TipoToken.NUMBER){ pila.push(37); next();}
                    else if(preanalisis.tipo == TipoToken.STRING){ pila.push(38); next();}
                    else if(preanalisis.tipo == TipoToken.IDENTIFIER){ pila.push(39); next();}
                    else if(preanalisis.tipo == TipoToken.EOF){
                        reduccion = 2;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba 'fun', 'var', 'for', '(', 'if', 'print', 'return', 'while', '{', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '$'");
                        return false;
                    }
                }
            } else if(pila.peek().equals(22)){
                if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.RIGHT_PAREN || preanalisis.tipo == TipoToken.COMMA){
                    pila.pop();
                    reduccion = 19;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba ';', ')' o ','");
                    return false;
                }
            } else if(pila.peek().equals(23)){
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
                        pila.push(56); next();
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba '=', ';', ')' o ','");
                        return false;
                    }
                }
            } else if(pila.peek().equals(24)){
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
                        pila.push(58); next();
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba 'or', '=', ';', ')' o ','");
                        return false;
                    }
                }
            } else if(pila.peek().equals(25)){
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
                        pila.push(60); next();
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba 'and', 'or', '=', ';', ')' o ','");
                        return false;
                    }
                }
            } else if(pila.peek().equals(26)){
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
                        pila.push(92); next();
                    } else if(preanalisis.tipo == TipoToken.EQUAL_EQUAL){
                        pila.push(93); next();
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba '!', 'and', 'or', '=', ';', ')' o ','");
                        return false;
                    }
                }
            } else if(pila.peek().equals(27)){
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
                        pila.push(65); next();
                    } else if(preanalisis.tipo == TipoToken.LESS){
                        pila.push(66); next();
                    } else if(preanalisis.tipo == TipoToken.GREATER_EQUAL){
                        pila.push(94); next();
                    } else if(preanalisis.tipo == TipoToken.LESS_EQUAL){
                        pila.push(96); next();
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba '>', '<', '!', 'and', 'or', '=', ';', ')' o ','");
                        return false;
                    }
                }
            } else if(pila.peek().equals(28)){
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
                        pila.push(68); next();
                    } else if(preanalisis.tipo == TipoToken.PLUS){
                        pila.push(69); next();
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba '-', '+', '>', '<', '!', 'and', 'or', '=', ';', ')' o ','");
                        return false;
                    }
                }
            } else if(pila.peek().equals(29)){
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
                        pila.push(71); next();
                    } else if(preanalisis.tipo == TipoToken.STAR){
                        pila.push(72); next();
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba '/', '*', '-', '+', '>', '<', '!', 'and', 'or', '=', ';', ')' o ','");
                        return false;
                    }
                }
            } else if(pila.peek().equals(30)){
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
                        pila.push(30); next();
                    } else if(preanalisis.tipo == TipoToken.MINUS){
                        pila.push(31); next();
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
            } else if(pila.peek().equals(31)){
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
                        pila.push(30); next();
                    } else if(preanalisis.tipo == TipoToken.MINUS){
                        pila.push(31); next();
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
            } else if(pila.peek().equals(32)){
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
            } else if(pila.peek().equals(33)){
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
            } else if(pila.peek().equals(34)){
                if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.LEFT_PAREN ||
                        preanalisis.tipo == TipoToken.RIGHT_PAREN || preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND ||
                        preanalisis.tipo == TipoToken.BANG_EQUAL ||preanalisis.tipo == TipoToken.EQUAL_EQUAL || preanalisis.tipo == TipoToken.GREATER_EQUAL ||
                        preanalisis.tipo == TipoToken.LESS_EQUAL || preanalisis.tipo == TipoToken.GREATER || preanalisis.tipo == TipoToken.LESS ||
                        preanalisis.tipo == TipoToken.MINUS || preanalisis.tipo == TipoToken.PLUS ||preanalisis.tipo == TipoToken.SLASH ||
                        preanalisis.tipo == TipoToken.STAR || preanalisis.tipo == TipoToken.COMMA){
                    pila.pop();
                    reduccion = 37;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba '/', '*', '-', '+', '>', '<', '!', 'and', 'or', '=', ';', '(', ')' o ','");
                    return false;
                }
            } else if(pila.peek().equals(35)){
                if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.LEFT_PAREN ||
                        preanalisis.tipo == TipoToken.RIGHT_PAREN || preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND ||
                        preanalisis.tipo == TipoToken.BANG_EQUAL ||preanalisis.tipo == TipoToken.EQUAL_EQUAL || preanalisis.tipo == TipoToken.GREATER_EQUAL ||
                        preanalisis.tipo == TipoToken.LESS_EQUAL || preanalisis.tipo == TipoToken.GREATER || preanalisis.tipo == TipoToken.LESS ||
                        preanalisis.tipo == TipoToken.MINUS || preanalisis.tipo == TipoToken.PLUS ||preanalisis.tipo == TipoToken.SLASH ||
                        preanalisis.tipo == TipoToken.STAR || preanalisis.tipo == TipoToken.COMMA){
                    pila.pop();
                    reduccion = 37;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba '/', '*', '-', '+', '>', '<', '!', 'and', 'or', '=', ';', '(', ')' o ','");
                    return false;
                }
            } else if(pila.peek().equals(36)){
                if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.LEFT_PAREN ||
                        preanalisis.tipo == TipoToken.RIGHT_PAREN || preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND ||
                        preanalisis.tipo == TipoToken.BANG_EQUAL ||preanalisis.tipo == TipoToken.EQUAL_EQUAL || preanalisis.tipo == TipoToken.GREATER_EQUAL ||
                        preanalisis.tipo == TipoToken.LESS_EQUAL || preanalisis.tipo == TipoToken.GREATER || preanalisis.tipo == TipoToken.LESS ||
                        preanalisis.tipo == TipoToken.MINUS || preanalisis.tipo == TipoToken.PLUS ||preanalisis.tipo == TipoToken.SLASH ||
                        preanalisis.tipo == TipoToken.STAR || preanalisis.tipo == TipoToken.COMMA){
                    pila.pop();
                    reduccion = 37;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba '/', '*', '-', '+', '>', '<', '!', 'and', 'or', '=', ';', '(', ')' o ','");
                    return false;
                }
            } else if(pila.peek().equals(37)){
                if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.LEFT_PAREN ||
                        preanalisis.tipo == TipoToken.RIGHT_PAREN || preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND ||
                        preanalisis.tipo == TipoToken.BANG_EQUAL ||preanalisis.tipo == TipoToken.EQUAL_EQUAL || preanalisis.tipo == TipoToken.GREATER_EQUAL ||
                        preanalisis.tipo == TipoToken.LESS_EQUAL || preanalisis.tipo == TipoToken.GREATER || preanalisis.tipo == TipoToken.LESS ||
                        preanalisis.tipo == TipoToken.MINUS || preanalisis.tipo == TipoToken.PLUS ||preanalisis.tipo == TipoToken.SLASH ||
                        preanalisis.tipo == TipoToken.STAR || preanalisis.tipo == TipoToken.COMMA){
                    pila.pop();
                    reduccion = 37;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba '/', '*', '-', '+', '>', '<', '!', 'and', 'or', '=', ';', '(', ')' o ','");
                    return false;
                }
            } else if(pila.peek().equals(38)){
                if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.LEFT_PAREN ||
                        preanalisis.tipo == TipoToken.RIGHT_PAREN || preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND ||
                        preanalisis.tipo == TipoToken.BANG_EQUAL ||preanalisis.tipo == TipoToken.EQUAL_EQUAL || preanalisis.tipo == TipoToken.GREATER_EQUAL ||
                        preanalisis.tipo == TipoToken.LESS_EQUAL || preanalisis.tipo == TipoToken.GREATER || preanalisis.tipo == TipoToken.LESS ||
                        preanalisis.tipo == TipoToken.MINUS || preanalisis.tipo == TipoToken.PLUS ||preanalisis.tipo == TipoToken.SLASH ||
                        preanalisis.tipo == TipoToken.STAR || preanalisis.tipo == TipoToken.COMMA){
                    pila.pop();
                    reduccion = 37;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba '/', '*', '-', '+', '>', '<', '!', 'and', 'or', '=', ';', '(', ')' o ','");
                    return false;
                }
            } else if(pila.peek().equals(39)){
                if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.LEFT_PAREN ||
                        preanalisis.tipo == TipoToken.RIGHT_PAREN || preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND ||
                        preanalisis.tipo == TipoToken.BANG_EQUAL ||preanalisis.tipo == TipoToken.EQUAL_EQUAL || preanalisis.tipo == TipoToken.GREATER_EQUAL ||
                        preanalisis.tipo == TipoToken.LESS_EQUAL || preanalisis.tipo == TipoToken.GREATER || preanalisis.tipo == TipoToken.LESS ||
                        preanalisis.tipo == TipoToken.MINUS || preanalisis.tipo == TipoToken.PLUS ||preanalisis.tipo == TipoToken.SLASH ||
                        preanalisis.tipo == TipoToken.STAR || preanalisis.tipo == TipoToken.COMMA){
                    pila.pop();
                    reduccion = 37;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba '/', '*', '-', '+', '>', '<', '!', 'and', 'or', '=', ';', '(', ')' o ','");
                    return false;
                }
            } else if(pila.peek().equals(40)){
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
                        pila.push(30); next();
                    } else if(preanalisis.tipo == TipoToken.MINUS){
                        pila.push(31); next();
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
            } else if(pila.peek().equals(41)){
                if(preanalisis.tipo == TipoToken.RIGHT_BRACE || preanalisis.tipo == TipoToken.EOF){
                    pila.pop(); pila.pop();
                    reduccion = 2;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba '}' o '$'.");
                    return false;
                }
            } else if(pila.peek().equals(42)){
                if(preanalisis.tipo == TipoToken.RIGHT_BRACE || preanalisis.tipo == TipoToken.EOF){
                    pila.pop(); pila.pop();
                    reduccion = 2;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba '}' o '$'.");
                    return false;
                }
            } else if(pila.peek().equals(43)){
                if(preanalisis.tipo == TipoToken.RIGHT_BRACE || preanalisis.tipo == TipoToken.EOF){
                    pila.pop(); pila.pop();
                    reduccion = 2;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba '}' o '$'.");
                    return false;
                }
            } else if(pila.peek().equals(44)){
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
            } else if(pila.peek().equals(45)){
                if(preanalisis.tipo == TipoToken.LEFT_PAREN){
                    pila.push(78); next();
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba '('.");
                    return false;
                }
            } else if(pila.peek().equals(46)){
                if(reduccion != 0){
                    if(reduccion == 5) pila.push(79);
                    else {
                        System.out.println("Error en la reducción del estado 46.");
                        return false;
                    }

                    reduccion = 0;
                } else {
                    if(preanalisis.tipo == TipoToken.SEMICOLON){
                        reduccion = 5;
                    } else if(preanalisis.tipo == TipoToken.EQUAL){
                        pila.push(80); next();
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba ';' o '='");
                        return false;
                    }
                }
            } else if(pila.peek().equals(47)){
                if(preanalisis.tipo == TipoToken.FUN || preanalisis.tipo == TipoToken.VAR || preanalisis.tipo == TipoToken.FOR || preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.IF ||
                        preanalisis.tipo == TipoToken.ELSE || preanalisis.tipo == TipoToken.PRINT || preanalisis.tipo == TipoToken.RETURN || preanalisis.tipo == TipoToken.WHILE || preanalisis.tipo == TipoToken.LEFT_BRACE ||
                        preanalisis.tipo == TipoToken.RIGHT_BRACE || preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS || preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE ||
                        preanalisis.tipo == TipoToken.NULL || preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER ||
                        preanalisis.tipo == TipoToken.EOF){
                    pila.pop(); pila.pop();
                    reduccion = 7;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba 'fun', 'var', 'for', '(', 'if', 'else', 'print', 'return', 'while', '{', '}', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '$'");
                    return false;
                }
            } else if(pila.peek().equals(48)){
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
                        pila.push(30); next();
                    } else if(preanalisis.tipo == TipoToken.MINUS){
                        pila.push(31); next();
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
            } else if(pila.peek().equals(49)){
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
                        pila.push(30); next();
                    } else if(preanalisis.tipo == TipoToken.MINUS){
                        pila.push(31); next();
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
            } else if(pila.peek().equals(50)){
                if(preanalisis.tipo == TipoToken.SEMICOLON){
                    pila.push(152); next();
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba ';'");
                    return false;
                }
            } else if(pila.peek().equals(51)){
                if(preanalisis.tipo == TipoToken.SEMICOLON){
                    pila.push(86); next();
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba ';'");
                    return false;
                }
            } else if(pila.peek().equals(52)){
                if(preanalisis.tipo == TipoToken.SEMICOLON){
                    pila.pop();
                    reduccion = 16;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba ';'");
                    return false;
                }
            } else if(pila.peek().equals(53)){
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
                        pila.push(30); next();
                    } else if(preanalisis.tipo == TipoToken.MINUS){
                        pila.push(31); next();
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
            } else if(pila.peek().equals(54)){
                if(preanalisis.tipo == TipoToken.RIGHT_BRACE){
                    pila.push(88); next();
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba '}'");
                    return false;
                }
            } else if(pila.peek().equals(55)){
                if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.RIGHT_PAREN || preanalisis.tipo == TipoToken.COMMA){
                    pila.pop(); pila.pop();
                    reduccion = 20;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba ';', ')' o ','");
                    return false;
                }
            } else if(pila.peek().equals(56)){
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
                        pila.push(30); next();
                    } else if(preanalisis.tipo == TipoToken.MINUS){
                        pila.push(31); next();
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
            } else if(pila.peek().equals(57)){
                if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                        preanalisis.tipo == TipoToken.COMMA){
                    pila.pop(); pila.pop();
                    reduccion = 22;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba '=', ';', ')' o ','");
                    return false;
                }
            } else if(pila.peek().equals(58)){
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
                        pila.push(30); next();
                    } else if(preanalisis.tipo == TipoToken.MINUS){
                        pila.push(31); next();
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
            } else if(pila.peek().equals(59)){
                if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                        preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.COMMA){
                    pila.pop(); pila.pop();
                    reduccion = 24;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba 'or', '=', ';', ')' o ','");
                    return false;
                }
            } else if(pila.peek().equals(60)){
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
                        pila.push(30); next();
                    } else if(preanalisis.tipo == TipoToken.MINUS){
                        pila.push(31); next();
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
            } else if(pila.peek().equals(61)){
                if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                        preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.COMMA){
                    pila.pop(); pila.pop();
                    reduccion = 26;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba 'and', 'or', '=', ';', ')' o ','");
                    return false;
                }
            } else if(pila.peek().equals(62)){

            } else if(pila.peek().equals(63)){

            } else if(pila.peek().equals(64)){
                if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                        preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.BANG_EQUAL ||
                        preanalisis.tipo == TipoToken.EQUAL_EQUAL || preanalisis.tipo == TipoToken.COMMA){
                    pila.pop(); pila.pop();
                    reduccion = 28;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba '!', 'and', 'or', '=', ';', ')' o ','");
                    return false;
                }
            } else if(pila.peek().equals(65)){
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
                        pila.push(30); next();
                    } else if(preanalisis.tipo == TipoToken.MINUS){
                        pila.push(31); next();
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
            } else if(pila.peek().equals(66)){
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
                        pila.push(30); next();
                    } else if(preanalisis.tipo == TipoToken.MINUS){
                        pila.push(31); next();
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
            } else if(pila.peek().equals(67)){
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
            } else if(pila.peek().equals(68)){
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
                        pila.push(30); next();
                    } else if(preanalisis.tipo == TipoToken.MINUS){
                        pila.push(31); next();
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
            } else if(pila.peek().equals(69)){
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
                        pila.push(30); next();
                    } else if(preanalisis.tipo == TipoToken.MINUS){
                        pila.push(31); next();
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
            } else if(pila.peek().equals(70)){
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
            } else if(pila.peek().equals(71)){
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
                        pila.push(30); next();
                    } else if(preanalisis.tipo == TipoToken.MINUS){
                        pila.push(31); next();
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
            } else if(pila.peek().equals(72)){
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
                        pila.push(30); next();
                    } else if(preanalisis.tipo == TipoToken.MINUS){
                        pila.push(31); next();
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
            } else if(pila.peek().equals(73)){
                if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                        preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.BANG_EQUAL ||
                        preanalisis.tipo == TipoToken.EQUAL_EQUAL || preanalisis.tipo == TipoToken.GREATER_EQUAL || preanalisis.tipo == TipoToken.LESS_EQUAL ||
                        preanalisis.tipo == TipoToken.GREATER || preanalisis.tipo == TipoToken.LESS || preanalisis.tipo == TipoToken.MINUS ||
                        preanalisis.tipo == TipoToken.PLUS || preanalisis.tipo == TipoToken.SLASH || preanalisis.tipo == TipoToken.STAR ||
                        preanalisis.tipo == TipoToken.COMMA){
                    pila.pop(); pila.pop();
                    reduccion = 34;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba '/', '*', '-', '+', '>', '<', '!', 'and', 'or', '=', ';', ')' o ','");
                    return false;
                }
            } else if(pila.peek().equals(74)){
                if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                        preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.BANG_EQUAL ||
                        preanalisis.tipo == TipoToken.EQUAL_EQUAL || preanalisis.tipo == TipoToken.GREATER_EQUAL || preanalisis.tipo == TipoToken.LESS_EQUAL ||
                        preanalisis.tipo == TipoToken.GREATER || preanalisis.tipo == TipoToken.LESS || preanalisis.tipo == TipoToken.MINUS ||
                        preanalisis.tipo == TipoToken.PLUS || preanalisis.tipo == TipoToken.SLASH || preanalisis.tipo == TipoToken.STAR ||
                        preanalisis.tipo == TipoToken.COMMA){
                    pila.pop(); pila.pop();
                    reduccion = 34;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba '/', '*', '-', '+', '>', '<', '!', 'and', 'or', '=', ';', ')' o ','");
                    return false;
                }
            } else if(pila.peek().equals(75)){
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
            } else if(pila.peek().equals(76)){
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
                        pila.push(30); next();
                    } else if(preanalisis.tipo == TipoToken.MINUS){
                        pila.push(31); next();
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
            } else if(pila.peek().equals(77)){
                if(preanalisis.tipo == TipoToken.RIGHT_PAREN){
                    pila.push(104); next();
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba ')'");
                    return false;
                }
            } else if(pila.peek().equals(78)){
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
                        pila.push(107); next();
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba ')' o 'id'.");
                        return false;
                    }
                }
            } else if(pila.peek().equals(79)){
                if(preanalisis.tipo == TipoToken.SEMICOLON){
                    pila.push(108); next();
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba ';'");
                    return false;
                }
            } else if(pila.peek().equals(80)){
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
                        pila.push(30); next();
                    } else if(preanalisis.tipo == TipoToken.MINUS){
                        pila.push(31); next();
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
            } else if(pila.peek().equals(81)){
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
                        pila.push(30); next();
                    } else if(preanalisis.tipo == TipoToken.MINUS){
                        pila.push(31); next();
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
            } else if(pila.peek().equals(82)){
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
            } else if(pila.peek().equals(83)){
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
            } else if(pila.peek().equals(84)){
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
            } else if(pila.peek().equals(85)){
                if(preanalisis.tipo == TipoToken.RIGHT_PAREN){
                    pila.push(113); next();
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba ')'");
                    return false;
                }
            } else if(pila.peek().equals(86)){
                if(preanalisis.tipo == TipoToken.FUN || preanalisis.tipo == TipoToken.VAR || preanalisis.tipo == TipoToken.FOR ||
                        preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.IF || preanalisis.tipo == TipoToken.ELSE ||
                        preanalisis.tipo == TipoToken.PRINT || preanalisis.tipo == TipoToken.RETURN || preanalisis.tipo == TipoToken.WHILE ||
                        preanalisis.tipo == TipoToken.LEFT_BRACE || preanalisis.tipo == TipoToken.RIGHT_BRACE || preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS ||
                        preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL ||
                        preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER ||
                        preanalisis.tipo == TipoToken.EOF){
                    pila.pop(); pila.pop(); pila.pop();
                    reduccion = 15;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba 'fun', 'var', 'for', '(', 'if', 'else', 'print', 'return', 'while', '{', '}', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '$'");
                    return false;
                }
            } else if(pila.peek().equals(87)){
                if(preanalisis.tipo == TipoToken.RIGHT_PAREN){
                    pila.push(114); next();
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba ')'");
                    return false;
                }
            } else if(pila.peek().equals(88)){
                if(preanalisis.tipo == TipoToken.FUN || preanalisis.tipo == TipoToken.VAR || preanalisis.tipo == TipoToken.FOR ||
                        preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.IF || preanalisis.tipo == TipoToken.ELSE ||
                        preanalisis.tipo == TipoToken.PRINT || preanalisis.tipo == TipoToken.RETURN || preanalisis.tipo == TipoToken.WHILE ||
                        preanalisis.tipo == TipoToken.LEFT_BRACE || preanalisis.tipo == TipoToken.RIGHT_BRACE || preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS ||
                        preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL ||
                        preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER ||
                        preanalisis.tipo == TipoToken.EOF){
                    pila.pop(); pila.pop(); pila.pop();
                    reduccion = 18;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba 'fun', 'var', 'for', '(', 'if', 'else', 'print', 'return', 'while', '{', '}', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '$'");
                    return false;
                }
            } else if(pila.peek().equals(89)){
                if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.RIGHT_PAREN || preanalisis.tipo == TipoToken.COMMA){
                    pila.pop(); pila.pop();
                    reduccion = 21;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba ';', ')' o ','");
                    return false;
                }
            } else if(pila.peek().equals(90)){
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
                        pila.push(58); next();
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba 'or', '=', ';', ')' o ','");
                        return false;
                    }
                }
            } else if(pila.peek().equals(91)){
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
                        pila.push(60); next();
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba 'or', '=', ';', ')' o ','");
                        return false;
                    }
                }
            } else if(pila.peek().equals(92)){
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
                        pila.push(30); next();
                    } else if(preanalisis.tipo == TipoToken.MINUS){
                        pila.push(31); next();
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
            } else if(pila.peek().equals(93)){
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
                        pila.push(30); next();
                    } else if(preanalisis.tipo == TipoToken.MINUS){
                        pila.push(31); next();
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
            } else if(pila.peek().equals(94)){
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
                        pila.push(30); next();
                    } else if(preanalisis.tipo == TipoToken.MINUS){
                        pila.push(31); next();
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
            } else if(pila.peek().equals(95)){
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
                        pila.push(65); next();
                    } else if(preanalisis.tipo == TipoToken.LESS){
                        pila.push(66); next();
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba '>', '<', 'and', 'or', '=', ';', ')' o ','");
                        return false;
                    }
                }
            } else if(pila.peek().equals(96)){
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
                        pila.push(30); next();
                    } else if(preanalisis.tipo == TipoToken.MINUS){
                        pila.push(31); next();
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
            } else if(pila.peek().equals(97)){
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
                        pila.push(65); next();
                    } else if(preanalisis.tipo == TipoToken.LESS){
                        pila.push(66); next();
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba '>', '<', 'and', 'or', '=', ';', ')' o ','");
                        return false;
                    }
                }
            } else if(pila.peek().equals(98)){
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
                        pila.push(68); next();
                    } else if(preanalisis.tipo == TipoToken.PLUS){
                        pila.push(69); next();
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba '-', '+', '>', '<', 'and', 'or', '=', ';', ')' o ','");
                        return false;
                    }
                }
            } else if(pila.peek().equals(99)){
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
                        pila.push(68); next();
                    } else if(preanalisis.tipo == TipoToken.PLUS){
                        pila.push(69); next();
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba '-', '+', '>', '<', 'and', 'or', '=', ';', ')' o ','");
                        return false;
                    }
                }
            } else if(pila.peek().equals(100)){
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
                        pila.push(71); next();
                    } else if(preanalisis.tipo == TipoToken.STAR){
                        pila.push(72); next();
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba '/', '*', '-', '+', '>', '<', 'and', 'or', '=', ';', ')' o ','");
                        return false;
                    }
                }
            } else if(pila.peek().equals(101)){
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
                        pila.push(71); next();
                    } else if(preanalisis.tipo == TipoToken.STAR){
                        pila.push(72); next();
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba '/', '*', '-', '+', '>', '<', 'and', 'or', '=', ';', ')' o ','");
                        return false;
                    }
                }
            } else if(pila.peek().equals(102)){
                if(preanalisis.tipo == TipoToken.RIGHT_PAREN){
                    pila.push(127); next();
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba ')'.");
                    return false;
                }
            } else if(pila.peek().equals(103)){
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
            } else if(pila.peek().equals(104)){
                if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                        preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.BANG_EQUAL ||
                        preanalisis.tipo == TipoToken.EQUAL_EQUAL || preanalisis.tipo == TipoToken.GREATER_EQUAL || preanalisis.tipo == TipoToken.LESS_EQUAL ||
                        preanalisis.tipo == TipoToken.GREATER || preanalisis.tipo == TipoToken.LESS || preanalisis.tipo == TipoToken.MINUS ||
                        preanalisis.tipo == TipoToken.PLUS || preanalisis.tipo == TipoToken.SLASH || preanalisis.tipo == TipoToken.STAR ||
                        preanalisis.tipo == TipoToken.COMMA){
                    pila.pop(); pila.pop(); pila.pop();
                    reduccion = 37;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba '/', '*', '-', '+', '>', '<', 'and', 'or', '=', ';', '(', ')' o ','");
                    return false;
                }
            } else if(pila.peek().equals(105)){
                if(preanalisis.tipo == TipoToken.RIGHT_PAREN){
                    pila.push(130); next();
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba ')'.");
                    return false;
                }
            } else if(pila.peek().equals(106)){
                if(preanalisis.tipo == TipoToken.RIGHT_PAREN){
                    pila.pop();
                    reduccion = 40;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba ')'.");
                    return false;
                }
            } else if(pila.peek().equals(107)){
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
            } else if(pila.peek().equals(108)){
                if(preanalisis.tipo == TipoToken.FUN || preanalisis.tipo == TipoToken.VAR || preanalisis.tipo == TipoToken.FOR ||
                        preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.IF ||
                        preanalisis.tipo == TipoToken.PRINT || preanalisis.tipo == TipoToken.RETURN || preanalisis.tipo == TipoToken.WHILE ||
                        preanalisis.tipo == TipoToken.LEFT_BRACE || preanalisis.tipo == TipoToken.RIGHT_BRACE || preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS ||
                        preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL ||
                        preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER ||
                        preanalisis.tipo == TipoToken.EOF || preanalisis.tipo == TipoToken.SEMICOLON){
                    pila.pop(); pila.pop(); pila.pop(); pila.pop();
                    reduccion = 4;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba ';', 'fun', 'var', 'for', '(', 'if', 'print', 'return', 'while', '{', '}', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '$'");
                    return false;
                }
            } else if(pila.peek().equals(109)){
                if(preanalisis.tipo == TipoToken.SEMICOLON){
                    pila.pop(); pila.pop();
                    reduccion = 5;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba ';'");
                    return false;
                }
            } else if(pila.peek().equals(110)){
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
                    } else if(preanalisis.tipo == TipoToken.BANG){
                        pila.push(30); next();
                    } else if(preanalisis.tipo == TipoToken.MINUS){
                        pila.push(31); next();
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
            } else if(pila.peek().equals(111)){
                if(preanalisis.tipo == TipoToken.SEMICOLON){
                    pila.push(135); next();
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba ';'");
                    return false;
                }
            } else if(pila.peek().equals(112)){
                if(preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.RIGHT_PAREN || preanalisis.tipo == TipoToken.BANG ||
                        preanalisis.tipo == TipoToken.MINUS || preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE ||
                        preanalisis.tipo == TipoToken.NULL || preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING ||
                        preanalisis.tipo == TipoToken.IDENTIFIER){
                    pila.pop();
                    reduccion = 10;
                }
            } else if(pila.peek().equals(113)){
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
                        pila.push(30); next();
                    } else if(preanalisis.tipo == TipoToken.MINUS){
                        pila.push(31); next();
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
            } else if(pila.peek().equals(114)){
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
                        pila.push(30); next();
                    } else if(preanalisis.tipo == TipoToken.MINUS){
                        pila.push(31); next();
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
            } else if(pila.peek().equals(115)){
                if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                        preanalisis.tipo == TipoToken.COMMA){
                    pila.pop(); pila.pop(); pila.pop();
                    reduccion = 23;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba '=', ';', ')' o ','");
                    return false;
                }
            } else if(pila.peek().equals(116)){
                if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                        preanalisis.tipo == TipoToken.OR ||preanalisis.tipo == TipoToken.COMMA){
                    pila.pop(); pila.pop(); pila.pop();
                    reduccion = 25;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba 'or', '=', ';', ')' o ','");
                    return false;
                }
            } else if(pila.peek().equals(117)){
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
                        pila.push(92); next();
                    } else if(preanalisis.tipo == TipoToken.EQUAL_EQUAL){
                        pila.push(93); next();
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba '!', 'and', 'or', '=', ';', ')' o ','");
                        return false;
                    }
                }
            } else if(pila.peek().equals(118)){
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
                        pila.push(92); next();
                    } else if(preanalisis.tipo == TipoToken.EQUAL_EQUAL){
                        pila.push(93); next();
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba '!', 'and', 'or', '=', ';', ')' o ','");
                        return false;
                    }
                }
            } else if(pila.peek().equals(119)){
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
                        pila.push(65); next();
                    } else if(preanalisis.tipo == TipoToken.LESS){
                        pila.push(66); next();
                    } else if(preanalisis.tipo == TipoToken.GREATER_EQUAL){
                        pila.push(94); next();
                    } else if(preanalisis.tipo == TipoToken.LESS_EQUAL){
                        pila.push(96); next();
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba '>', '<', '!', 'and', 'or', '=', ';', ')' o ','");
                        return false;
                    }
                }
            } else if(pila.peek().equals(120)){
                if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                        preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.BANG_EQUAL ||
                        preanalisis.tipo == TipoToken.EQUAL_EQUAL ||
                        preanalisis.tipo == TipoToken.COMMA){
                    pila.pop(); pila.pop(); pila.pop();
                    reduccion = 29;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba '!', 'and', 'or', '=', ';', ')' o ','");
                    return false;
                }
            } else if(pila.peek().equals(121)){
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
                        pila.push(65); next();
                    } else if(preanalisis.tipo == TipoToken.LESS){
                        pila.push(66); next();
                    } else if(preanalisis.tipo == TipoToken.GREATER_EQUAL){
                        pila.push(94); next();
                    } else if(preanalisis.tipo == TipoToken.LESS_EQUAL){
                        pila.push(96); next();
                    }  else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba '>', '<', '!', 'and', 'or', '=', ';', ')' o ','");
                        return false;
                    }
                }
            } else if(pila.peek().equals(122)){
                if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                        preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.BANG_EQUAL ||
                        preanalisis.tipo == TipoToken.EQUAL_EQUAL ||
                        preanalisis.tipo == TipoToken.COMMA){
                    pila.pop(); pila.pop(); pila.pop();
                    reduccion = 29;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba '!', 'and', 'or', '=', ';', ')' o ','");
                    return false;
                }
            } else if(pila.peek().equals(123)){
                if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                        preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.BANG_EQUAL ||
                        preanalisis.tipo == TipoToken.EQUAL_EQUAL || preanalisis.tipo == TipoToken.GREATER_EQUAL || preanalisis.tipo == TipoToken.LESS_EQUAL ||
                        preanalisis.tipo == TipoToken.GREATER || preanalisis.tipo == TipoToken.LESS || preanalisis.tipo == TipoToken.COMMA){
                    pila.pop(); pila.pop(); pila.pop();
                    reduccion = 31;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba '>', '<', '!', 'and', 'or', '=', ';', ')' o ','");
                    return false;
                }
            } else if(pila.peek().equals(124)){
                if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                        preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.BANG_EQUAL ||
                        preanalisis.tipo == TipoToken.EQUAL_EQUAL || preanalisis.tipo == TipoToken.GREATER_EQUAL || preanalisis.tipo == TipoToken.LESS_EQUAL ||
                        preanalisis.tipo == TipoToken.GREATER || preanalisis.tipo == TipoToken.LESS || preanalisis.tipo == TipoToken.COMMA){
                    pila.pop(); pila.pop(); pila.pop();
                    reduccion = 31;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba '>', '<', '!', 'and', 'or', '=', ';', ')' o ','");
                    return false;
                }
            } else if(pila.peek().equals(125)){
                if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                        preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.BANG_EQUAL ||
                        preanalisis.tipo == TipoToken.EQUAL_EQUAL || preanalisis.tipo == TipoToken.GREATER_EQUAL || preanalisis.tipo == TipoToken.LESS_EQUAL ||
                        preanalisis.tipo == TipoToken.GREATER || preanalisis.tipo == TipoToken.LESS || preanalisis.tipo == TipoToken.MINUS ||
                        preanalisis.tipo == TipoToken.PLUS || preanalisis.tipo == TipoToken.COMMA){
                    pila.pop(); pila.pop(); pila.pop();
                    reduccion = 33;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba '-', '+', '>', '<', '!', 'and', 'or', '=', ';', ')' o ','");
                    return false;
                }
            } else if(pila.peek().equals(126)){
                if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                        preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.BANG_EQUAL ||
                        preanalisis.tipo == TipoToken.EQUAL_EQUAL || preanalisis.tipo == TipoToken.GREATER_EQUAL || preanalisis.tipo == TipoToken.LESS_EQUAL ||
                        preanalisis.tipo == TipoToken.GREATER || preanalisis.tipo == TipoToken.LESS || preanalisis.tipo == TipoToken.MINUS ||
                        preanalisis.tipo == TipoToken.PLUS || preanalisis.tipo == TipoToken.COMMA){
                    pila.pop(); pila.pop(); pila.pop();
                    reduccion = 33;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba '-', '+', '>', '<', '!', 'and', 'or', '=', ';', ')' o ','");
                    return false;
                }
            } else if(pila.peek().equals(127)){
                if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                        preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.BANG_EQUAL ||
                        preanalisis.tipo == TipoToken.EQUAL_EQUAL || preanalisis.tipo == TipoToken.GREATER_EQUAL || preanalisis.tipo == TipoToken.LESS_EQUAL ||
                        preanalisis.tipo == TipoToken.GREATER || preanalisis.tipo == TipoToken.LESS || preanalisis.tipo == TipoToken.MINUS ||
                        preanalisis.tipo == TipoToken.PLUS || preanalisis.tipo == TipoToken.SLASH || preanalisis.tipo == TipoToken.STAR ||
                        preanalisis.tipo == TipoToken.COMMA){
                    pila.pop(); pila.pop(); pila.pop();
                    reduccion = 36;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba '/', '*', '-', '+', '>', '<', '!', 'and', 'or', '=', ';', ')' o ','");
                    return false;
                }
            } else if(pila.peek().equals(128)){
                if(preanalisis.tipo == TipoToken.RIGHT_PAREN){
                    pila.pop(); pila.pop();
                    reduccion = 43;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba ')'");
                    return false;
                }
            } else if(pila.peek().equals(129)){
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
                        pila.push(30); next();
                    } else if(preanalisis.tipo == TipoToken.MINUS){
                        pila.push(31); next();
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
            } else if(pila.peek().equals(130)){
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
            } else if(pila.peek().equals(131)){
                if(preanalisis.tipo == TipoToken.RIGHT_PAREN){
                    pila.pop(); pila.pop();
                    reduccion = 41;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba ')'");
                    return false;
                }
            } else if(pila.peek().equals(132)){
                if(preanalisis.tipo == TipoToken.IDENTIFIER){
                    pila.push(144); next();
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba 'id'");
                    return false;
                }
            } else if(pila.peek().equals(133)){
                if(preanalisis.tipo == TipoToken.RIGHT_PAREN){
                    pila.push(145); next();
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba ')'");
                    return false;
                }
            } else if(pila.peek().equals(134)){
                if(preanalisis.tipo == TipoToken.RIGHT_PAREN){
                    pila.pop();
                    reduccion = 11;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba ')'");
                    return false;
                }
            } else if(pila.peek().equals(135)){
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
            } else if(pila.peek().equals(136)){
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
                    } else if(preanalisis.tipo == TipoToken.ELSE){
                        pila.push(147); next();
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba 'fun', 'var', 'for', '(', 'if', 'print', 'return', 'while', '{', '}', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '$'");
                        return false;
                    }
                }
            } else if(pila.peek().equals(137)){
                if(preanalisis.tipo == TipoToken.FUN || preanalisis.tipo == TipoToken.VAR || preanalisis.tipo == TipoToken.FOR ||
                        preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.IF || preanalisis.tipo == TipoToken.ELSE || preanalisis.tipo == TipoToken.PRINT ||
                        preanalisis.tipo == TipoToken.RETURN || preanalisis.tipo == TipoToken.WHILE || preanalisis.tipo == TipoToken.LEFT_BRACE ||
                        preanalisis.tipo == TipoToken.RIGHT_BRACE || preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS ||preanalisis.tipo == TipoToken.TRUE ||
                        preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL ||preanalisis.tipo == TipoToken.NUMBER ||
                        preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER || preanalisis.tipo == TipoToken.EOF){
                    pila.pop(); pila.pop(); pila.pop(); pila.pop(); pila.pop();
                    reduccion = 17;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba 'fun', 'var', 'for', '(', 'if', 'else', 'print', 'return', 'while', '{', '}', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '$'");
                    return false;
                }
            } else if(pila.peek().equals(138)){
                if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                        preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.COMMA){
                    pila.pop(); pila.pop(); pila.pop();
                    reduccion = 27;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba 'and', 'or', '=', ';', ')' o ','");
                    return false;
                }
            } else if(pila.peek().equals(139)){
                if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                        preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.COMMA){
                    pila.pop(); pila.pop(); pila.pop();
                    reduccion = 27;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba 'and', 'or', '=', ';', ')' o ','");
                    return false;
                }
            } else if(pila.peek().equals(140)){
                if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                        preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.COMMA ||
                        preanalisis.tipo == TipoToken.BANG_EQUAL || preanalisis.tipo == TipoToken.EQUAL_EQUAL){
                    pila.pop(); pila.pop(); pila.pop();
                    reduccion = 29;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba '!', 'and', 'or', '=', ';', ')' o ','");
                    return false;
                }
            } else if(pila.peek().equals(141)){
                if(preanalisis.tipo == TipoToken.SEMICOLON || preanalisis.tipo == TipoToken.EQUAL || preanalisis.tipo == TipoToken.RIGHT_PAREN ||
                        preanalisis.tipo == TipoToken.OR || preanalisis.tipo == TipoToken.AND || preanalisis.tipo == TipoToken.COMMA ||
                        preanalisis.tipo == TipoToken.BANG_EQUAL || preanalisis.tipo == TipoToken.EQUAL_EQUAL){
                    pila.pop(); pila.pop(); pila.pop();
                    reduccion = 29;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba '!', 'and', 'or', '=', ';', ')' o ','");
                    return false;
                }
            } else if(pila.peek().equals(142)){
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
            } else if(pila.peek().equals(143)){
                if(preanalisis.tipo == TipoToken.FUN || preanalisis.tipo == TipoToken.VAR || preanalisis.tipo == TipoToken.FOR ||
                        preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.IF || preanalisis.tipo == TipoToken.PRINT ||
                        preanalisis.tipo == TipoToken.RETURN || preanalisis.tipo == TipoToken.WHILE || preanalisis.tipo == TipoToken.LEFT_BRACE ||
                        preanalisis.tipo == TipoToken.RIGHT_BRACE || preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS ||preanalisis.tipo == TipoToken.TRUE ||
                        preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL ||preanalisis.tipo == TipoToken.NUMBER ||
                        preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER || preanalisis.tipo == TipoToken.EOF){
                    pila.pop(); pila.pop(); pila.pop(); pila.pop(); pila.pop();
                    reduccion = 38;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba 'fun', 'var', 'for', '(', 'if', 'print', 'return', 'while', '{', '}', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '$'");
                    return false;
                }
            } else if(pila.peek().equals(144)){
                if(reduccion != 0){
                    if(reduccion == 42) pila.push(149);
                    else {
                        System.out.println("Error en la reducción del estado 144");
                        return false;
                    }

                    reduccion = 0;
                } else {
                    if(preanalisis.tipo == TipoToken.IDENTIFIER){
                        pila.push(132); next();
                    } else if(preanalisis.tipo == TipoToken.RIGHT_PAREN){
                        reduccion = 42;
                    } else {
                        System.out.println("ERROR ENCONTRADO: Se esperaba 'id' o ')'");
                        return false;
                    }
                }
            } else if(pila.peek().equals(145)){
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
                        pila.push(30); next();
                    } else if(preanalisis.tipo == TipoToken.MINUS){
                        pila.push(31); next();
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
            } else if(pila.peek().equals(146)){
                if(preanalisis.tipo == TipoToken.FUN || preanalisis.tipo == TipoToken.VAR || preanalisis.tipo == TipoToken.FOR ||
                        preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.IF || preanalisis.tipo == TipoToken.ELSE || preanalisis.tipo == TipoToken.PRINT ||
                        preanalisis.tipo == TipoToken.RETURN || preanalisis.tipo == TipoToken.WHILE || preanalisis.tipo == TipoToken.LEFT_BRACE ||
                        preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS ||preanalisis.tipo == TipoToken.TRUE ||
                        preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL ||preanalisis.tipo == TipoToken.NUMBER ||
                        preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER || preanalisis.tipo == TipoToken.EOF){
                    pila.pop(); pila.pop(); pila.pop(); pila.pop(); pila.pop(); pila.pop();
                    reduccion = 12;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba 'fun', 'var', 'for', '(', 'if', 'else', 'print', 'return', 'while', '{', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '$'");
                    return false;
                }
            } else if(pila.peek().equals(147)){
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
                        pila.push(30); next();
                    } else if(preanalisis.tipo == TipoToken.MINUS){
                        pila.push(31); next();
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
            } else if(pila.peek().equals(148)){
                if(preanalisis.tipo == TipoToken.RIGHT_PAREN){
                    pila.pop(); pila.pop(); pila.pop();
                    reduccion = 44;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba ')'");
                    return false;
                }
            } else if(pila.peek().equals(149)){
                if(preanalisis.tipo == TipoToken.RIGHT_PAREN){
                    pila.pop(); pila.pop(); pila.pop();
                    reduccion = 42;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba ')'");
                    return false;
                }
            } else if(pila.peek().equals(150)){
                if(preanalisis.tipo == TipoToken.FUN || preanalisis.tipo == TipoToken.VAR || preanalisis.tipo == TipoToken.FOR ||
                        preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.IF || preanalisis.tipo == TipoToken.ELSE || preanalisis.tipo == TipoToken.PRINT ||
                        preanalisis.tipo == TipoToken.RETURN || preanalisis.tipo == TipoToken.WHILE || preanalisis.tipo == TipoToken.LEFT_BRACE ||
                        preanalisis.tipo == TipoToken.RIGHT_BRACE || preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS ||preanalisis.tipo == TipoToken.TRUE ||
                        preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL ||preanalisis.tipo == TipoToken.NUMBER ||
                        preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER || preanalisis.tipo == TipoToken.EOF){
                    pila.pop(); pila.pop(); pila.pop(); pila.pop(); pila.pop(); pila.pop(); pila.pop();
                    reduccion = 8;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba 'fun', 'var', 'for', '(', 'if', 'else', 'print', 'return', 'while', '{', '}', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '$'");
                    return false;
                }
            } else if(pila.peek().equals(151)){
                if(preanalisis.tipo == TipoToken.FUN || preanalisis.tipo == TipoToken.VAR || preanalisis.tipo == TipoToken.FOR ||
                        preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.IF || preanalisis.tipo == TipoToken.ELSE || preanalisis.tipo == TipoToken.PRINT ||
                        preanalisis.tipo == TipoToken.RETURN || preanalisis.tipo == TipoToken.WHILE || preanalisis.tipo == TipoToken.LEFT_BRACE ||
                        preanalisis.tipo == TipoToken.RIGHT_BRACE || preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS ||preanalisis.tipo == TipoToken.TRUE ||
                        preanalisis.tipo == TipoToken.FALSE || preanalisis.tipo == TipoToken.NULL ||preanalisis.tipo == TipoToken.NUMBER ||
                        preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER || preanalisis.tipo == TipoToken.EOF){
                    pila.pop(); pila.pop();
                    reduccion = 13;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba 'fun', 'var', 'for', '(', 'if', 'else', 'print', 'return', 'while', '{', '}', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '$'");
                    return false;
                }
            } else if(pila.peek().equals(152)){
                if(preanalisis.tipo == TipoToken.FUN || preanalisis.tipo == TipoToken.VAR || preanalisis.tipo == TipoToken.FOR || preanalisis.tipo == TipoToken.LEFT_PAREN || preanalisis.tipo == TipoToken.IF ||
                        preanalisis.tipo == TipoToken.ELSE || preanalisis.tipo == TipoToken.PRINT || preanalisis.tipo == TipoToken.RETURN || preanalisis.tipo == TipoToken.WHILE || preanalisis.tipo == TipoToken.LEFT_BRACE ||
                        preanalisis.tipo == TipoToken.RIGHT_BRACE || preanalisis.tipo == TipoToken.BANG || preanalisis.tipo == TipoToken.MINUS || preanalisis.tipo == TipoToken.TRUE || preanalisis.tipo == TipoToken.FALSE ||
                        preanalisis.tipo == TipoToken.NULL || preanalisis.tipo == TipoToken.NUMBER || preanalisis.tipo == TipoToken.STRING || preanalisis.tipo == TipoToken.IDENTIFIER ||
                        preanalisis.tipo == TipoToken.EOF){
                    pila.pop(); pila.pop(); pila.pop();
                    reduccion = 14;
                } else {
                    System.out.println("ERROR ENCONTRADO: Se esperaba 'fun', 'var', 'for', '(', 'if', 'else', 'print', 'return', 'while', '{', '}', '!', '-', 'true', 'false', 'null', 'number', 'string', 'id' o '$'");
                    return false;
                }
            }
        }

        return false;
    }

    private void next(){
        i++;
        preanalisis = tokens.get(i);
    }
}
