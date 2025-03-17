package ast.statements;

import ast.AbstractLocutable;
import ast.expressions.Expression;
import visitor.Visitor;

public class Read extends AbstractLocutable implements Statement {
    private Expression argument;

    public Read(int line, int column, Expression argument) {
        super(line,column);
        if(argument == null)
            throw new IllegalArgumentException("Parameter cannot be null");
        setArgument(argument);
    }

    public Expression getArgument() {
        return argument;
    }

    private void setArgument(Expression argument) {
        if(argument == null)
            throw new IllegalArgumentException("Parameter cannot be null");
        this.argument = argument;
    }

    @Override
    public <RT, PT> RT accept(Visitor<RT, PT> v, PT p) {
        return v.visit(this,p);
    }
}
