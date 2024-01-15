class ExprVariable extends Expression {
    final Token name;

    ExprVariable(Token name) {
        this.name = name;
    }

    public String toString(){
        return name.lexema;
    }
}