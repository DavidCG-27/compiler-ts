package ast.types;

import ast.Locatable;
import visitor.Visitor;

public class ArrayType extends AbstractType {
    private Type of;
    private int length;
    public ArrayType(Type primitiveType, int length) {
        if(primitiveType == null || length <= 0)
            throw new IllegalArgumentException("Null primitive type");
        setOf(primitiveType);
        setLength(length);
    }

    public Type getOf() {
        return of;
    }
    private void setOf(Type of) {
        this.of = of;
    }

    public int getLength() {
        return length;
    }

    private void setLength(int length) {
        this.length = length;
    }

    @Override
    public <RT, PT> RT accept(Visitor<RT, PT> v, PT p) {
        return v.visit(this,p);
    }

    @Override
    public Type squareBrackets(Type t, Locatable locatable) {
        if(t == IntType.type)
            return of;
        return super.squareBrackets(t, locatable);
    }

    @Override
    public String toString() {
        return "ArrayType";
    }

    @Override
    public int getSize(){
        return length*of.getSize();
    }
}
