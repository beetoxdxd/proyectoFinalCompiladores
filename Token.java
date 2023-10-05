public class Token {

    final TipoToken tipo;
    final String lexema;
    final Object literal;

    // CONSTRUCTOR
    public Token(TipoToken tipo, String lexema) {
        this.tipo = tipo;
        this.lexema = lexema;
        this.literal = null;
    }
    // SOBRECARGA
    public Token(TipoToken tipo, String lexema, Object literal) {
        this.tipo = tipo;
        this.lexema = lexema;
        this.literal = literal;
    }

    public String toString() {
        return "<" + tipo + " " + lexema + " " + literal + ">";
    }
}
