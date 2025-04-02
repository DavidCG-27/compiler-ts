package ast.types;

import ast.ASTNode;
import ast.Locatable;
import ast.expressions.Expression;

import java.util.List;

public interface Type extends ASTNode {
    public void mustBeLogical(Locatable locatable);
    public Type arithmetic(Type t, Locatable locatable);
    public Type arithmetic(Locatable locatable); //UnaryMinus
    public Type comparison(Type t, Locatable locatable); //Comparison
    public Type logical(Type t, Locatable locatable); //Logical
    public Type logical(Locatable locatable); //UnaryNot
    public void mustBePromotes(Type t, Locatable l);
    public void mustBeBuiltIn(Locatable locatable); //Input y Log
    public Type canBeCastTo(Type t, Locatable locatable);
    public Type squareBrackets(Type t, Locatable locatable);
    public Type dot(String fillName, Locatable locatable);
    public Type parenthesis(List<Expression> arguments, Locatable locatable);

}
