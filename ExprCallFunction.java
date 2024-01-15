import java.util.Arrays;
import java.util.List;

public class ExprCallFunction extends Expression{
    final Expression callee;
    // final Token paren;
    final List<Expression> arguments;

    ExprCallFunction(Expression callee, /*Token paren,*/ List<Expression> arguments) {
        this.callee = callee;
        // this.paren = paren;
        this.arguments = arguments;
    }

    public String toString(){
        StringBuilder arg = new StringBuilder();
        for(int i = 0; i < arguments.size(); i++) {
            arg.append(arguments.get(i));
            if(i < arguments.size() - 1) arg.append(", ");
        }

        return callee + "(" + arg + ")";
    }
}
