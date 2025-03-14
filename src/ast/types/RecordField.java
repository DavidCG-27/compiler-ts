package ast.types;


import ast.AbstractLocutable;

public class RecordField extends AbstractLocutable {
    private String fieldName;
    private Type type;

    public RecordField(int line, int column, String fieldName, Type type) {
        super(line, column);
        if(type == null || fieldName == null)
            throw new IllegalArgumentException("Type cannot be null or zero length");
        setFieldName(fieldName);
        setType(type);
    }

    public Type getType() {
        return type;
    }

    private void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return fieldName;
    }

    private void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
