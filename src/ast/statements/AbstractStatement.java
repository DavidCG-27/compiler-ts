package ast.statements;

import ast.AbstractLocatable;
import ast.types.Type;
import visitor.Visitor;

public abstract class AbstractStatement extends AbstractLocatable implements Statement {

    private Type returnType;

    public AbstractStatement(int line, int column) {
        super(line, column);
    }

    @Override
    public Type getReturnType() {
        return returnType;
    }

    @Override
    public void setReturnType(Type returnType) {
        this.returnType = returnType;
    }
}
