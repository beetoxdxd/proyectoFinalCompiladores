public class StmtReturn extends Statement {
    final Expression value;

    StmtReturn(Expression value) {
        this.value = value;
    }

    public String toString(){
        if(value != null) return "return " + value + ";";
        else return "return;";
    }
}
