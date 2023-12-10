import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AnalizadorSintactico implements Parser{
    private int i = 0;
    private boolean hayErrores = false;
    private Token preanalisis;
    private final List<Token> tokens;
    private List<TipoToken> terminales = new ArrayList<>();


    public AnalizadorSintactico(List<Token> tokens){
        this.tokens = tokens;
        preanalisis = this.tokens.get(i);
    }

    @Override
    public boolean parse(){

        return false;
    }

    private void next(){
        i++;
        preanalisis = tokens.get(i);
    }
}
