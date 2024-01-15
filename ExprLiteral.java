
class ExprLiteral extends Expression {
    final Object value;

    ExprLiteral(Object value) {
        this.value = value;
    }

    public String toString(){
        if(value == null) return null;
        return value.toString();
    }
}
