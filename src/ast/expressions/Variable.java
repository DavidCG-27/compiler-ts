package ast.expressions;

import ast.AbstractLocutable;

public class Variable extends AbstractExpression {
    private String name;

    public Variable( int line, int column, String name) {
        super(line, column);
        setName(name);
    }

    public String getName() {
        return name;
    }

    private void setName( String name ) {
        this.name = name;
    }
}
