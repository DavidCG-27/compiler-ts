package ast.types;

import ast.definitions.VarDefinition;
import visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class FunctionType implements Type {
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

}
