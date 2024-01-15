import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StmtBlock extends Statement{
    final List<Statement> statements;

    StmtBlock(List<Statement> statements) {
        this.statements = statements;
    }

    public String toString() {
        StringBuilder st = new StringBuilder();
        for (int i = 0; i < statements.size(); i++) {
            if(statements.get(i) != null) st.append(statements.get(i));
            else st.append("null");
            if (i < statements.size() - 1) st.append(" ");
        }

        return "{ " + st.toString() + " }";
    }
}
