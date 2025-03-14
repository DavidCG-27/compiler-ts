package ast.expressions;

import visitor.Visitor;

public class Arithmetic extends AbstractOperation{
    public Arithmetic(int line, int column, String operation, Expression left, Expression right) {
        super(line, column, operation, left, right);
    }

    @Override
    public <RT, PT> RT accept(Visitor<RT, PT> v, PT p) {
        return v.visit(this,p);
    }
}
