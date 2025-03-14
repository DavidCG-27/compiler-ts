package ast.statements;

import ast.AbstractLocutable;
import ast.expressions.Expression;
import visitor.Visitor;

public class Return extends AbstractLocutable implements Statement {
    private Expression returnPart;

    public Return(int line, int column, Expression returnPart) {
        super(line,column);
        if (returnPart == null) {
            throw new IllegalArgumentException("returnPart cannot be null");
        }
        this.returnPart = returnPart;
    }

    private void setReturnPart(Expression returnPart) {
        if(returnPart == null) {
            throw new IllegalArgumentException("returnPart cannot be null");
        }
        this.returnPart = returnPart;
    }

    public Expression getReturnPart() {
        return returnPart;
    }

    @Override
    public <RT, PT> RT accept(Visitor<RT, PT> v, PT p) {
        return v.visit(this,p);
    }
}
