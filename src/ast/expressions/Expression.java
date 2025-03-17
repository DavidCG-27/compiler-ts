package ast.expressions;

import ast.Locutable;

public interface Expression extends Locutable {
    public boolean getLValue();
    public void setLValue(boolean value);
}
