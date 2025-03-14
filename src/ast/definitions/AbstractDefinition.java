package ast.definitions;
import ast.AbstractLocutable;
import ast.types.Type;

public abstract class AbstractDefinition extends AbstractLocutable implements Definition {

    private Type type;
    private String name;

    public AbstractDefinition(int line, int column, Type type, String name) {
        super(line, column);
        if(type == null || name == null) {
            throw new IllegalArgumentException("type and name cannot be null");
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
}
