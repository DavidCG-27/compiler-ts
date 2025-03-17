package ast;

import ast.definitions.Definition;
import visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Program implements ASTNode{
    private List<Definition> definitions;
    public Program(List<Definition> definitions) {
        if (definitions == null)
            throw new IllegalArgumentException("Definitions cannot be null");
        this.definitions = new ArrayList<>(definitions);
    }

    public List<Definition> getDefinitions() {
        return new ArrayList<>(definitions);
    }

    @Override
    public <RT, PT> RT accept(Visitor<RT, PT> v, PT p) {
        return v.visit(this,p);
    }
}
