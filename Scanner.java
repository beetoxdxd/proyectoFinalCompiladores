import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scanner {

    private static final Map<String, TipoToken> palabrasReservadas;
    private static final Map<Character, TipoToken> unSoloCaracter;

    static {
        palabrasReservadas = new HashMap<>();
        palabrasReservadas.put("and",    TipoToken.AND);
        palabrasReservadas.put("else",   TipoToken.ELSE);
        palabrasReservadas.put("false",  TipoToken.FALSE);
        palabrasReservadas.put("for",    TipoToken.FOR);
        palabrasReservadas.put("fun",    TipoToken.FUN);
        palabrasReservadas.put("if",     TipoToken.IF);
        palabrasReservadas.put("null",   TipoToken.NULL);
        palabrasReservadas.put("or",     TipoToken.OR);
        palabrasReservadas.put("print",  TipoToken.PRINT);
        palabrasReservadas.put("return", TipoToken.RETURN);
        palabrasReservadas.put("true",   TipoToken.TRUE);
        palabrasReservadas.put("var",    TipoToken.VAR);
        palabrasReservadas.put("while",  TipoToken.WHILE);
    }

    static {
        unSoloCaracter = new HashMap<>();
        unSoloCaracter.put('(', TipoToken.LEFT_PAREN);
        unSoloCaracter.put(')', TipoToken.RIGHT_PAREN);
        unSoloCaracter.put('{', TipoToken.LEFT_BRACE);
        unSoloCaracter.put('}', TipoToken.RIGHT_BRACE);
        unSoloCaracter.put(',', TipoToken.COMMA);
        unSoloCaracter.put('.', TipoToken.DOT);
        unSoloCaracter.put('-', TipoToken.MINUS);
        unSoloCaracter.put('+', TipoToken.PLUS);
        unSoloCaracter.put(';', TipoToken.SEMICOLON);
        unSoloCaracter.put('/',TipoToken.SLASH);
        unSoloCaracter.put('*', TipoToken.STAR);
    }

    private final String source;

    private final List<Token> tokens = new ArrayList<>();

    public Scanner(String source){
        this.source = source + " ";
    }

    public List<Token> scan() throws Exception {
        int estado = 0, espacio = 0, bandera = 0, linea = 0;
        String lexema = "";
        char c;

        for(int i=0; i<source.length(); i++) {
            c = source.charAt(i);

            if (c == '\n') linea++;

            switch (estado) {
                case 0:
                    if (Character.isLetter(c)) {
                        estado = 13;
                        lexema += c;
                    } else if (Character.isDigit(c)) {
                        estado = 15;
                        lexema += c;
                    } else if (c == '\"') {
                        estado = 24;
                        lexema += c;
                        bandera = 0;
                    } else if (c == '/') {
                        estado = 26;
                        lexema += c;
                    } else if (c == '>') {
                        estado = 1;
                        lexema += c;
                    } else if (c == '<') {
                        estado = 4;
                        lexema += c;
                    } else if (c == '=') {
                        estado = 7;
                        lexema += c;
                    } else if (c == '!') {
                        estado = 10;
                        lexema += c;
                    } else if (c == '(' || c == ')' || c == '{' || c == '}' || c == ',' || c == '.' || c == '-' || c == '+' || c == ';' || c == '*') {
                        TipoToken tipTok = unSoloCaracter.get(c);
                        if (tipTok != null) {
                            lexema += c;
                            Token t = new Token(tipTok, lexema);
                            tokens.add(t);

                            estado = 0;
                            lexema = "";
                        }
                    } else if(c > 32){
                        estado = 0;
                        lexema = "";
                        bandera = 3;
                    }
                    break;

                case 1:
                    if (c == '=') {
                        lexema += c;

                        Token t = new Token(TipoToken.GREATER_EQUAL, lexema);
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                    } else {
                        Token t = new Token(TipoToken.GREATER, lexema);
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                        i--;
                    }
                    break;

                case 4:
                    if (c == '=') {
                        lexema += c;

                        Token t = new Token(TipoToken.LESS_EQUAL, lexema);
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                    } else {
                        Token t = new Token(TipoToken.LESS, lexema);
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                        i--;
                    }
                    break;

                case 7:
                    if (c == '=') {
                        lexema += c;

                        Token t = new Token(TipoToken.EQUAL_EQUAL, lexema);
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                    } else {
                        Token t = new Token(TipoToken.EQUAL, lexema);
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                        i--;
                    }
                    break;

                case 10:
                    if (c == '=') {
                        lexema += c;

                        Token t = new Token(TipoToken.BANG_EQUAL, lexema);
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                    } else {
                        Token t = new Token(TipoToken.BANG, lexema);
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                        i--;
                    }
                    break;


                case 13:
                    if (Character.isLetterOrDigit(c)) {
                        estado = 13;
                        lexema += c;
                    } else {
                        TipoToken tt = palabrasReservadas.get(lexema);

                        if (tt == null) {
                            Token t = new Token(TipoToken.IDENTIFIER, lexema);
                            tokens.add(t);
                        } else {
                            Token t = new Token(tt, lexema);
                            tokens.add(t);
                        }

                        estado = 0;
                        lexema = "";
                        i--;

                    }
                    break;

                case 15:
                    if (Character.isDigit(c)) {
                        estado = 15;
                        lexema += c;
                    } else if (c == '.') {
                        lexema += c;
                        estado = 16;
                    } else if (c == 'E') {
                        lexema += c;
                        estado = 18;
                    } else {
                        Token t = new Token(TipoToken.NUMBER, lexema, Integer.valueOf(lexema));
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                        i--;
                    }
                    break;

                case 16:
                    if (Character.isDigit(c)) {
                        estado = 17;
                        lexema += c;
                    } else {
                        lexema = "";
                        estado = 0;
                    }
                    break;

                case 17:
                    if (Character.isDigit(c)) {
                        estado = 17;
                        lexema += c;
                    } else if (c == 'E') {
                        estado = 18;
                        lexema += c;
                    } else {
                        Token t = new Token(TipoToken.NUMBER, lexema, Float.valueOf(lexema));
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                        i--;
                    }
                    break;

                case 18:
                    if (Character.isDigit(c)) {
                        estado = 20;
                        lexema += c;
                    } else if (c == '+' || c == '-') {
                        estado = 19;
                        lexema += c;
                    } else {
                        lexema = "";
                        estado = 0;
                    }
                    break;

                case 19:
                    if (Character.isDigit(c)) {
                        lexema += c;
                        estado = 20;
                    } else {
                        lexema = "";
                        estado = 0;
                    }
                    break;

                case 20:
                    if (Character.isDigit(c)) {
                        lexema += c;
                        estado = 20;
                    } else {
                        Token t = new Token(TipoToken.NUMBER, lexema, Long.valueOf(lexema));
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                        i--;
                    }
                    break;

                case 24:
                    if (c == '\"') {
                        lexema += c;
                        char[] lit = new char[lexema.length() - 2];
                        for (int j = 0; j < lit.length; j++) {
                            lit[j] = lexema.charAt(j + 1);
                        }

                        Token t = new Token(TipoToken.STRING, lexema, String.copyValueOf(lit));
                        tokens.add(t);

                        estado = 0;
                        lexema = "";
                        bandera = 1;
                        espacio = 0;
                    } else if (c == '\n') {
                        espacio++;
                        estado = 0;
                        lexema = "";
                    } else {
                        estado = 24;
                        lexema += c;
                    }
                    break;

                case 26:
                    if (c == '*') {
                        lexema += c;
                        estado = 27;
                    } else if (c == '/') {
                        lexema += c;
                        estado = 30;
                        espacio = 0;
                    } else {
                        Token t = new Token(TipoToken.SLASH, lexema);
                        tokens.add(t);

                        i--;
                        estado = 0;
                        lexema = "";
                    }
                    break;

                case 27:
                    if (c == '*') {
                        lexema += c;
                        estado = 28;
                    } else {
                        lexema += c;
                        estado = 27;
                    }
                    break;

                case 28:
                    if (c == '/') {
                        lexema = "";
                        estado = 0;
                    } else if (c == '*') {
                        lexema += c;
                        estado = 28;
                    } else {
                        lexema += c;
                        estado = 27;
                    }
                    break;

                case 30:
                    if (c == '\n') {
                        estado = 0;
                        lexema = "";
                    } else {
                        lexema += c;
                        estado = 30;
                    }
                    break;
            }

            if(bandera == 3){
                Interprete.error(linea, "Carácter no válido.");
                bandera = 0;
            }
        }
        if(espacio == 1 && bandera == 0){
            Interprete.error(linea, "Se ha detectado un salto de línea sin haber completado la cadena, cuando no debería de haber.");
        }


        return tokens;
    }
}
