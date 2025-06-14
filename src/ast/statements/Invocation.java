package ast.statements;

import ast.definitions.Definition;
import ast.expressions.AbstractExpression;
import ast.expressions.Expression;
import ast.expressions.Variable;
import ast.types.Type;
import visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Invocation extends AbstractExpression implements Statement {
    private List<Expression> arguments;
    private Variable name;
    private Type returnType;

    public Invocation(int line, int column, Variable name, List<Expression> arguments) {
        super(line,column);
        if (arguments == null) {
            throw new IllegalArgumentException("arguments cannot be null");
        }
        setArguments(arguments);
        setName(name);
    }

    public List<Expression> getArguments() {
        return new ArrayList<Expression>(arguments);
    }

    private void setArguments(List<Expression> arguments) {
        if (arguments == null) {
            throw new IllegalArgumentException("arguments cannot be null");
        }
        this.arguments = new ArrayList<>(arguments);
    }

    public Variable getName() {
        return name;
    }

    private void setName(Variable name) {
        if (name == null) {
            throw new IllegalArgumentException("name cannot be null");
        }
        this.name = name;
    }

    public Type getReturnType() {
        return returnType;
    }

    public void setReturnType(Type returnType) {
        this.returnType = returnType;
    }

    @Override
    public <RT, PT> RT accept(Visitor<RT, PT> v, PT p) {
        return v.visit(this,p);
    }
}
