package ast.expressions;

import ast.AbstractLocutable;
import ast.types.Type;
import visitor.Visitor;

public class Cast extends AbstractExpression {
    private Expression expression;
    private Type type;

    public Cast(int line, int column, Expression expression, Type type) {
        super(line, column);
        this.expression = expression;
        this.type = type;
    }

    public Expression getExpression() {
        return expression;
    }

    public Type getType() {
        return type;
    }

    private void setExpression(Expression expression) {
        this.expression = expression;
    }

    private void setType(Type type) {
        this.type = type;
    }

    @Override
    public <RT, PT> RT accept(Visitor<RT, PT> v, PT p) {
        return v.visit(this,p);
    }


}
