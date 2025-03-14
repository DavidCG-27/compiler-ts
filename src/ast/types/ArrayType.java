package ast.types;

public class ArrayType implements Type {
    private Type type;
    private int length;
    public ArrayType(Type primitiveType, int length) {
        if(primitiveType == null || length <= 0)
            throw new IllegalArgumentException("Null primitive type");
        setType(primitiveType);
        setLength(length);
    }

    public Type getType() {
        return type;
    }
    private void setType(Type type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    private void setLength(int length) {
        this.length = length;
    }
}
