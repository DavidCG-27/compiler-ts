package ast.types;

import visitor.Visitor;

public class VoidType extends AbstractType {
    //Singleton
    public static VoidType type = new VoidType();
    private VoidType() {}
    @Override
    public <RT, PT> RT accept(Visitor<RT, PT> v, PT p) {
        return v.visit(this,p);
    }
    @Override
    public String toString() {
        return "VoidType";
    }

    @Override
    public int getSize() {
        return 0;
    }

}
