package ast.expressions;

import visitor.Visitor;

public class IntLiteral extends AbstractExpression {

    private int value;

    public IntLiteral(int line, int column, int value) {
        super(line, column);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    private void setValue(int value) {
        this.value = value;
    }
    @Override
    public <RT, PT> RT accept(Visitor<RT, PT> v, PT p) {
        return v.visit(this,p);
    }
}
