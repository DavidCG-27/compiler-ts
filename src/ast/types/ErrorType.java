package ast.types;

import ast.Locatable;
import ast.expressions.Expression;
import errorhandler.ErrorHandler;
import visitor.Visitor;

import java.util.List;

public class ErrorType extends AbstractType{
    private String message;
    private Locatable locatable;
    public ErrorType(String message, Locatable locatable) {
        this.message = message;
        this.locatable = locatable;
        ErrorHandler.getInstance().addError(this);
    }

    @Override
    public String toString() {
        return "Error in "+locatable.getLine()+":"+locatable.getColumn()+" -> "+message;
    }
    @Override
    public <RT, PT> RT accept(Visitor<RT, PT> v, PT p) {
        return v.visit(this,p);
    }

    @Override
    public void mustBeLogical(Locatable locatable) {}

    @Override
    public Type arithmetic(Type t, Locatable locatable) {
        return this;
    }

    @Override
    public Type arithmetic(Locatable locatable) {
        return this;
    }

    @Override
    public Type comparison(Type t, Locatable locatable) {
        return this;
    }

    @Override
    public Type logical(Type t, Locatable locatable) {
        return this;
    }

    @Override
    public Type logical(Locatable locatable) {
        return this;
    }

    @Override
    public void mustBePromotes(Type t, Locatable l) {}

    @Override
    public void mustBeBuiltIn(Locatable locatable) {}

    @Override
    public Type canBeCastTo(Type t, Locatable locatable) {
        return this;
    }

    @Override
    public Type squareBrackets(Type t, Locatable locatable) {
        return this;
    }

    @Override
    public Type dot(String fillName, Locatable locatable) {
        return this;
    }

    @Override
    public Type parenthesis(List<Expression> arguments, Locatable locatable) {
        return this;
    }
}
