package ast.types;

import ast.AbstractLocutable;
import com.sun.jdi.Locatable;
import errorhandler.ErrorHandler;
import visitor.Visitor;

public class ErrorType implements Type{
    private String message;
    private AbstractLocutable locatable;
    public ErrorType(String message, AbstractLocutable locatable) {
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
}
