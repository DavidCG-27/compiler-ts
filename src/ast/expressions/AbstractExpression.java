package ast.expressions;

import ast.AbstractLocutable;

public abstract class AbstractExpression extends AbstractLocutable implements Expression {

    private boolean lvalue;

    public AbstractExpression(int line, int column) {
        super(line, column);
    }

    public boolean getLValue() {
        return lvalue;
    }

    public void setLValue(boolean lvalue) {
        this.lvalue = lvalue;
    }


}
