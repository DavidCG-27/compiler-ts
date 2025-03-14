package ast.definitions;

import ast.statements.Statement;
import ast.types.Type;

import java.util.ArrayList;
import java.util.List;

public class FunctionDefinition extends AbstractDefinition{
    private List<Statement> body;

    public FunctionDefinition(int line, int column, Type type, String name, List<Statement> body) {
        super(line, column, type, name);
        if(body == null)
            throw new IllegalArgumentException("body cannot be null");
        this.body = body;
    }
    
    public List<Statement> getBody() {
        return new ArrayList<>(body);
    }

    private void setBody(List<Statement> body) {
        this.body = new ArrayList<>(body);
    }
}
