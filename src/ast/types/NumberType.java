package ast.types;

import ast.Locatable;
import visitor.Visitor;

public class NumberType extends AbstractType{
    //Singleton
    public static final NumberType type = new NumberType();
    private NumberType() {
    }
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
    public Type arithmetic(Locatable locatable) {
        return this;
    }

    @Override
    public void mustBePromotes(Type t, Locatable l) {
        if(t != this){
            super.mustBePromotes(t, l);
        }
    }

    @Override
    public void mustBeBuiltIn(Locatable locatable) {
    }

    @Override
    public Type canBeCastTo(Type t, Locatable locatable) {
        if(t == this || t == IntType.type || t == CharType.type){
            return t;
        }
        return super.canBeCastTo(t, locatable);
    }

    @Override
    public String toString() {
        return "NumberType";
    }

    @Override
    public int getSize() {
        return 4;
    }

    @Override
    public String suffix(){
        return "f";
    }
}
