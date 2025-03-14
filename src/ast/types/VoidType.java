package ast.types;

public class VoidType implements Type {
    //Singleton
    public static VoidType type = new VoidType();
    private VoidType() {}
}
