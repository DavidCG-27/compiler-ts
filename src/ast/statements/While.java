package ast.statements;

import ast.AbstractLocutable;
import ast.expressions.Expression;

import java.util.ArrayList;
import java.util.List;

public class While extends AbstractLocutable implements Statement{
    private List<Statement> body;
    private Expression condition;

    public While(int line, int column, Expression condition, List<Statement> body) {
        super(line,column);
        if(condition == null || body == null)
            throw new IllegalArgumentException("The condition and the body cannot be null");
        this.condition = condition;
        this.body = body;
    }

    public List<Statement> getBody() {
        return new ArrayList<>(body);
    }

    private void setBody(List<Statement> body) {
        if(body == null)
            throw new IllegalArgumentException("The body cannot be null");
        this.body = new ArrayList<>(body);
    }

    public Expression getCondition() {
        return condition;
    }

    private void setCondition(Expression condition) {
        if(condition == null)
            throw new IllegalArgumentException("The condition cannot be null");
        this.condition = condition;
    }
}
