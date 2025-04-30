package ast.types;

import ast.Locatable;
import ast.expressions.Expression;
import visitor.Visitor;

import java.util.List;

public abstract class AbstractType implements Type {
    @Override
    public void mustBeLogical(Locatable locatable) {
        new ErrorType(this.toString() + " cannot be use as condition", locatable);
    }

    @Override
    public Type arithmetic(Type t, Locatable locatable) {
        if (t instanceof ErrorType) {
            return t;
        }
        return new ErrorType(this.toString() + " cannot be use as arithmetic with "+t.toString(), locatable);
    }

    @Override
    public Type arithmetic(Locatable locatable) {
        return new ErrorType(this.toString() + " cannot be use as arithmetic", locatable);
    }

    @Override
    public Type comparison(Type t, Locatable locatable) {
        if (t instanceof ErrorType) {
            return t;
        }
        return new ErrorType(this.toString() + " cannot be use as comparison with "+t.toString(), locatable);
    }

    @Override
    public Type logical(Type t, Locatable locatable) {
        if (t instanceof ErrorType) {
            return t;
        }
        return new ErrorType(this.toString() + " cannot be use as logical with "+t.toString(), locatable);
    }

    @Override
    public Type logical(Locatable locatable) {
        return new ErrorType(this.toString() + " cannot be use as logical", locatable);
    }

    @Override
    public void mustBePromotes(Type t, Locatable l) {
        if (t instanceof ErrorType) {
            return;
        }
        new ErrorType("Cannot convert from "+t.toString()+" to "+this.toString()+", this operation is not supported", l);
    }

    @Override
    public void mustBeBuiltIn(Locatable locatable) {
        new ErrorType(this.toString() + " cannot be built in", locatable);
    }

    @Override
    public Type canBeCastTo(Type t, Locatable locatable) {
        if (t instanceof ErrorType) {
            return t;
        }
        return new ErrorType(this.toString() + " cannot be cast to "+t.toString(), locatable);
    }

    @Override
    public Type squareBrackets(Type t, Locatable locatable) {
        if (t instanceof ErrorType) {
            return t;
        }
        return new ErrorType(this.toString() + " cannot be use a square brackets with "+t.toString(), locatable);
    }

    @Override
    public Type dot(String fillName, Locatable locatable) {
        return new ErrorType(this.toString() + " cannot use dot at fill "+fillName, locatable);
    }

    @Override
    public Type parenthesis(List<Expression> arguments, Locatable locatable) {
        StringBuilder argTypes = new StringBuilder();
        for (Expression arg : arguments) {
            if (arg.getType() instanceof ErrorType) {
                return arg.getType();
            } else {
                argTypes.append(arg.getType().toString()).append("\t");
            }
        }
        if (argTypes.length() == 0) {
            argTypes.append("any argument received");
        }
        return new ErrorType(this.toString()+" does not support the dot operator", locatable);
    }

    @Override
    public <RT, PT> RT accept(Visitor<RT, PT> v, PT p) {
        return null;
    }

    @Override
    public String suffix(){
        throw new RuntimeException("This type don't support suffixing ("+this.toString()+")");
    }
}

