package ast.statements;

import ast.Locatable;
import ast.types.Type;

public interface Statement extends Locatable {
    public Type getReturnType();
    public void setReturnType(Type returnType);
}
