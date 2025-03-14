package ast.expressions;

import ast.AbstractLocutable;

public class FieldAccess extends AbstractExpression {
    private String fillName;
    private Expression left;

    public FieldAccess(int line, int column, String fillName, Expression left) {
        super(line, column);
        if(fillName == null || left == null)
            throw new IllegalArgumentException("fillName and left cannot be null");
        setFillName(fillName);
        setLeft(left);
    }

    private void setFillName(String fillName) {
        this.fillName = fillName;
    }

    private void setLeft(Expression left) {
        this.left = left;
    }

    public String getFillName() {
        return fillName;
    }

    public Expression getLeft() {
        return left;
    }
}
