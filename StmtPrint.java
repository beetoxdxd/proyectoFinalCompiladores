public class StmtPrint extends Statement {
    final Expression expression;

    StmtPrint(Expression expression) {
        this.expression = expression;
    }

    public String toString(){
        return "print " + expression + ";";
    }
}
