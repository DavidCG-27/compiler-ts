package ast.statements;

import ast.AbstractLocutable;
import ast.expressions.Expression;

public class Assignment extends AbstractLocutable implements Statement{
    private Expression left;
    private Expression right;

    public Assignment(int line, int column, Expression left, Expression right) {
        super(line, column);
        if(left == null || right == null)
            throw new IllegalArgumentException("left and right cannot be null");
        setLeft(left);
        setRight(right);
    }

    public Expression getLeft() {
        return left;
    }

    private void setLeft(Expression left) {
        if(left == null)
            throw new IllegalArgumentException("left cannot be null");
        this.left = left;
    }

    public Expression getRight() {
        return right;
    }

    private void setRight(Expression right) {
        this.right = right;
    }

}
