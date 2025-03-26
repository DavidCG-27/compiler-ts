package ast.types;

import visitor.Visitor;

public class IntType implements Type {
    //Singleton
    public static final IntType type = new IntType();
    private IntType() {
    }
    @Override
    public <RT, PT> RT accept(Visitor<RT, PT> v, PT p) {
        return v.visit(this,p);
    }
}
