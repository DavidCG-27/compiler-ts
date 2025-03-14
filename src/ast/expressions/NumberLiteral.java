package ast.expressions;

import ast.AbstractLocutable;

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
}
