package ast.types;

import visitor.Visitor;

public class CharType implements Type {
    //Singleton
    public static final CharType type = new CharType();
    private CharType() {}
    @Override
    public <RT, PT> RT accept(Visitor<RT, PT> v, PT p) {
        return v.visit(this,p);
    }
}
