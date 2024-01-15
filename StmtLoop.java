public class StmtLoop extends Statement {
    final Expression condition;
    final Statement body;

    StmtLoop(Expression condition, Statement body) {
        this.condition = condition;
        this.body = body;
    }

    public String toString(){
        return "loop(" + condition + ")" + body;
    }
}
