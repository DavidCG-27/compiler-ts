package ast.types;

import ast.Locatable;
import visitor.Visitor;

public class CharType extends AbstractType {
    //Singleton
    public static final CharType type = new CharType();
    private CharType() {}
    @Override
    public <RT, PT> RT accept(Visitor<RT, PT> v, PT p) {
        return v.visit(this,p);
    }

    @Override
    public Type arithmetic(Type t, Locatable locatable) {
        if(this == t){
            return t;
        }
        return super.arithmetic(t,locatable);
    }

    @Override
    public void mustBeBuiltIn(Locatable locatable) {
    }

    @Override
    public void mustBePromotes(Type t, Locatable l) {
        if(t != this){
            super.mustBePromotes(t, l);
        }
    }

    @Override
    public String toString() {
        return "CharType";
    }

    @Override
    public int getSize() {
        return 1;
    }
}
