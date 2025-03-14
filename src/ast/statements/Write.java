package ast.statements;

import ast.AbstractLocutable;
import ast.expressions.Expression;

public class Write extends AbstractLocutable implements Statement {
    private Expression argument;

    public Write(int line, int column, Expression argument) {
        super(line,column);
        if(argument == null)
            throw new IllegalArgumentException("Argument cannot be null");
        setArgument(argument);
    }

    public Expression getArgument() {
        return argument;
    }

    private void setArgument(Expression argument) {
        if(argument == null)
            throw new IllegalArgumentException("Argument cannot be null");
        this.argument = argument;
    }
}
