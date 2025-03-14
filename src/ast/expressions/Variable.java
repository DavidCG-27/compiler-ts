package ast.expressions;

import ast.AbstractLocutable;
import visitor.Visitor;

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

    @Override
    public <RT, PT> RT accept(Visitor<RT, PT> v, PT p) {
        return v.visit(this,p);
    }
}
