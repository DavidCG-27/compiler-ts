package ast.types;

import ast.Locatable;
import ast.definitions.VarDefinition;
import ast.expressions.Expression;
import visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class FunctionType extends AbstractType {
    private Type returnType;
    private List<VarDefinition> arguments;

    public FunctionType(Type returnType, List<VarDefinition> arguments)
    {
        if(returnType == null || arguments == null)
            throw new IllegalArgumentException("ReturnType and arguments cannot be null");
        setReturnType(returnType);
        setArguments(arguments);
    }

    public Type getReturnType() {
        return returnType;
    }

    public void setReturnType(Type returnType) {
        this.returnType = returnType;
    }

    public void setArguments(List<VarDefinition> arguments) {
        this.arguments = new ArrayList<>(arguments);
    }

    public List<VarDefinition> getArguments() {
        return arguments;
    }

    public <RT, PT> RT accept(Visitor<RT, PT> v, PT p) {
        return v.visit(this,p);
    }

    @Override
    public Type parenthesis(List<Expression> expressions, Locatable locatable) {
        if (expressions.size() != arguments.size()) {
            return new ErrorType("The function expects "+arguments.size()+" arguments but "+expressions.size()+" were received",locatable);
        }
        for (int i = 0; i < arguments.size(); i++) {
            expressions.get(i).getType().mustBePromotes(arguments.get(i).getType(), locatable);
        }
        return returnType;
    }

    @Override
    public String toString() {
        return "FunctionType";
    }

    @Override
    public int getSize() {
        return 0;
    }

}