package ast.definitions;

import ast.Locutable;
import ast.types.Type;

public interface Definition extends Locutable {
    public Type getType();
    public String getName();
    public int getScope();
    public void setScope(int scope);
}
