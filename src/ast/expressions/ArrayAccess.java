package ast.expressions;

import ast.AbstractLocutable;
import visitor.Visitor;

public class ArrayAccess extends AbstractExpression {
    private Expression arrayExpression;
    private Expression indexExpression;

    public ArrayAccess(int line, int column, Expression arrayExpression, Expression indexExpression) {
        super(line, column);
        if (arrayExpression == null || indexExpression == null) {
            throw new IllegalArgumentException("arrayExpression and indexExpression cannot be null");
        }
        setArrayExpression(arrayExpression);
        setIndexExpression(indexExpression);
    }

    private void setArrayExpression(Expression arrayExpression) {
        this.arrayExpression = arrayExpression;
    }

    private void setIndexExpression(Expression indexExpression) {
        this.indexExpression = indexExpression;
    }

    public Expression getArrayExpression() {
        return arrayExpression;
    }

    public Expression getIndexExpression() {
        return indexExpression;
    }

    @Override
    public <RT, PT> RT accept(Visitor<RT, PT> v, PT p) {
        return v.visit(this,p);
    }
}
