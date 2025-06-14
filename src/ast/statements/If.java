package ast.statements;

import ast.AbstractLocatable;
import ast.expressions.Expression;
import visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class If extends AbstractStatement {
    private List<Statement> thenPart;
    private List<Statement> elsePart;
    private Expression condition;

    public If(int line, int column, List<Statement> thenPart, List<Statement> elsePart, Expression condition) {
        super(line,column);
        if(thenPart == null || elsePart == null || condition == null)
            throw new IllegalArgumentException("Null or empty expression");
        setThenPart(thenPart);
        setElsePart(elsePart);
        setCondition(condition);
    }

    public List<Statement> getThenPart() {
        return new ArrayList<Statement>(thenPart);
    }

    public List<Statement> getElsePart() {
        return new ArrayList<>(elsePart);
    }

    public Expression getCondition() {
        return condition;
    }

    private void setCondition(Expression condition) {
        if(condition == null)
            throw new IllegalArgumentException("Null expression");
        this.condition = condition;
    }

    private void setThenPart(List<Statement> thenPart) {
        if(thenPart == null)
            throw new IllegalArgumentException("Null expression");
        this.thenPart = new ArrayList<>(thenPart);
    }

    private void setElsePart(List<Statement> elsePart) {
        if(elsePart == null)
            throw new IllegalArgumentException("Null expression");
        this.elsePart = new ArrayList<>(elsePart);
    }

    @Override
    public <RT, PT> RT accept(Visitor<RT, PT> v, PT p) {
        return v.visit(this,p);
    }
}
