package ast.expressions;

import ast.Locatable;
import ast.types.Type;

public interface Expression extends Locatable {
    public boolean getLValue();
    public void setLValue(boolean value);
    public Type getType();
    public void setType(Type type);
}
