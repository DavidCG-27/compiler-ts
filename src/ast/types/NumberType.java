package ast.types;

public class NumberType implements Type{
    //Singleton
    public static final NumberType type = new NumberType();
    private NumberType() {
    }
}
