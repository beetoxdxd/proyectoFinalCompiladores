import java.util.List;

public class StmtFunction extends Statement {
    final Token name;
    final List<Token> params;
    final StmtBlock body;

    StmtFunction(Token name, List<Token> params, StmtBlock body) {
        this.name = name;
        this.params = params;
        this.body = body;
    }

    public String toString(){
        StringBuilder par = new StringBuilder();
        for(int i = 0; i < params.size(); i++) {
            par.append(params.get(i).lexema);
            if(i < params.size() - 1) par.append(", ");
        }
        if(body == null) return "fun " + name.lexema + "(" + par + "){}";
        else return "fun " + name.lexema + "(" + par + ")" + body;
    }
}
