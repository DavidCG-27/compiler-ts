package ast.definitions;
import ast.AbstractLocatable;
import ast.types.Type;

public abstract class AbstractDefinition extends AbstractLocatable implements Definition {

    private Type type;
    private String name;
    private int scope;

    public AbstractDefinition(int line, int column, Type type, String name) {
        super(line, column);
        if(name == null) {
            throw new IllegalArgumentException("name cannot be null");
        }
        this.type = type;
        this.name = name;
    }

    @Override
    public Type getType() {
        return this.type;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getScope() {
        return this.scope;
    }

    @Override
    public void setScope(int scope) {
        this.scope = scope;
    }
}
