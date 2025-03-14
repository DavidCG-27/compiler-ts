package ast.expressions;

import visitor.Visitor;

public class UnaryNot extends UnaryOperation{
    public UnaryNot(int line, int column, Expression operand) {
        super(line, column, operand);
    }
    @Override
    public <RT, PT> RT accept(Visitor<RT, PT> v, PT p) {
        return v.visit(this,p);
    }
}
