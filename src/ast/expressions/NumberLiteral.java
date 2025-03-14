package ast.expressions;

import ast.AbstractLocutable;
import visitor.Visitor;

public class NumberLiteral extends AbstractExpression {
    private Number value;

    public NumberLiteral(int line, int column, Number value) {
        super(line, column);
        this.value = value;
    }

    public Number getValue() {
        return value;
    }

    private void setValue(Number value) {
        this.value = value;
    }
    @Override
    public <RT, PT> RT accept(Visitor<RT, PT> v, PT p) {
        return v.visit(this,p);
    }
}
