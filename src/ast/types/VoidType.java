package ast.types;

import visitor.Visitor;

public class VoidType implements Type {
    //Singleton
    public static VoidType type = new VoidType();
    private VoidType() {}
    @Override
    public <RT, PT> RT accept(Visitor<RT, PT> v, PT p) {
        return v.visit(this,p);
    }
}
