public class StmtIf extends Statement {
    final Expression condition;
    final Statement thenBranch;
    final Statement elseBranch;

    StmtIf(Expression condition, Statement thenBranch, Statement elseBranch) {
        this.condition = condition;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }

    public String toString(){
        if(elseBranch == null) return "if(" + condition + ")" + thenBranch;
        return "if(" + condition +")" + thenBranch + " else " + elseBranch;

    }
}
