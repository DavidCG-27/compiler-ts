package ast.types;

import visitor.Visitor;

public class NumberType implements Type{
    //Singleton
    public static final NumberType type = new NumberType();
    private NumberType() {
    }
    @Override
    public <RT, PT> RT accept(Visitor<RT, PT> v, PT p) {
        return v.visit(this,p);
    }
}
