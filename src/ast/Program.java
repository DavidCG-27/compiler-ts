package ast;

import ast.definitions.Definition;

import java.util.ArrayList;
import java.util.List;

public class Program implements ASTNode{
    private List<Definition> definitions;
    public Program(List<Definition> definitions) {
        if (definitions == null)
            throw new IllegalArgumentException("Definitions cannot be null");
        this.definitions = new ArrayList<>(definitions);
    }
}
