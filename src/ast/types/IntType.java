package ast.types;

import ast.Locatable;
import visitor.Visitor;

public class IntType extends AbstractType {
    //Singleton
    public static final IntType type = new IntType();
    private IntType() {
    }
    @Override
    public <RT, PT> RT accept(Visitor<RT, PT> v, PT p) {
        return v.visit(this,p);
    }

    @Override
    public void mustBeLogical(Locatable locatable) {}

    @Override
    public Type arithmetic(Type t, Locatable locatable) {
        if(this == t){
            return t;
        }
        return super.arithmetic(t,locatable);
    }

    @Override
    public Type comparison(Type t, Locatable locatable) {
        if(this == t){
            return t;
        }
        return super.comparison(t,locatable);
    }

    @Override
    public Type logical(Type t, Locatable locatable) {
        if(this == t){
            return t;
        }
        return super.comparison(t,locatable);
    }

    @Override
    public Type logical(Locatable locatable) {
        return this;
    }

    @Override
    public Type arithmetic(Locatable locatable) {
        return this;
    }

    @Override
    public Type canBeCastTo(Type t, Locatable locatable) {
        if(t == this || t == NumberType.type){
            return t;
        }
        return super.canBeCastTo(t, locatable);
    }

    @Override
    public String toString() {
        return "IntType";
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
}
