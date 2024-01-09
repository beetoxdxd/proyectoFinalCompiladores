public class Token {

    final TipoToken tipo;
    final String lexema;
    final Object literal;
    final int posicion;

    // CONSTRUCTOR
    public Token(TipoToken tipo, String lexema, int posicion) {
        this.tipo = tipo;
        this.lexema = lexema;
        this.literal = null;
        this.posicion = posicion;
    }
    // SOBRECARGA
    public Token(TipoToken tipo, String lexema, Object literal, int posicion) {
        this.tipo = tipo;
        this.lexema = lexema;
        this.literal = literal;
        this.posicion = posicion;
    }

    public TipoToken getTipo() {
        return tipo;
    }

    public String getLexema() {
        return lexema;
    }

    public Object getLiteral() {
        return literal;
    }

    public int getPosicion() {
        return posicion;
    }

    public String toString() {
        return "<" + tipo + " " + lexema + " " + literal + " " + posicion + ">";
    }
}
