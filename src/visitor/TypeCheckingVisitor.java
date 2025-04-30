package visitor;

import ast.definitions.FunctionDefinition;
import ast.expressions.*;
import ast.statements.*;
import ast.types.CharType;
import ast.types.FunctionType;
import ast.types.IntType;
import ast.types.NumberType;

public class TypeCheckingVisitor extends AbstractVisitor<Void, Void> {
    @Override
    public Void visit(IntLiteral i, Void p) {
        super.visit(i, p);
        i.setType(IntType.type);
        return p;
    }

    @Override
    public Void visit(CharLiteral c, Void p) {
        super.visit(c, p);
        c.setType(CharType.type);
        return p;
    }

    @Override
    public Void visit(NumberLiteral n, Void p) {
        super.visit(n, p);
        n.setType(NumberType.type);
        return p;
    }

    @Override
    public Void visit(Variable v, Void p) {
        super.visit(v, p);
        v.setType(v.getDefinition().getType());
        return p;
    }

    @Override
    public Void visit(Cast c, Void p) {
        super.visit(c, p);
        c.setType(c.getExpression().getType().canBeCastTo(c.getCastType(), c));
        return p;
    }

    @Override
    public Void visit(FieldAccess f, Void p) {
        super.visit(f, p);
        f.setType(f.getLeft().getType().dot(f.getFillName(),f));
        return p;
    }

    @Override
    public Void visit(Arithmetic a, Void p) {
        super.visit(a, p);
        a.setType(a.getLeft().getType().arithmetic(a.getRight().getType(),a));
        return p;
    }

    @Override
    public Void visit(Comparison c, Void p) {
        super.visit(c, p);
        c.setType(c.getLeft().getType().comparison(c.getRight().getType(),c));
        return p;
    }

    @Override
    public Void visit(ArrayAccess a, Void p) {
        super.visit(a, p);
        a.setType(a.getArrayExpression().getType().squareBrackets(a.getIndexExpression().getType(), a));
        return p;
    }

    @Override
    public Void visit(Logic l, Void p) {
        super.visit(l, p);
        l.setType(l.getLeft().getType().logical(l.getRight().getType(),l));
        return p;
    }

    @Override
    public Void visit(UnaryMinus u, Void p) {
        super.visit(u, p);
        u.setType(u.getOperand().getType().arithmetic(u));
        return p;
    }

    @Override
    public Void visit(UnaryNot u, Void p) {
        super.visit(u, p);
        u.setType(u.getOperand().getType().logical(u));
        return p;
    }

    @Override
    public Void visit(Write w, Void p) {
        super.visit(w, p);
        w.getArgument().getType().mustBeBuiltIn(w);
        return p;
    }

    @Override
    public Void visit(Read r, Void p) {
        super.visit(r, p);
        r.getArgument().getType().mustBeBuiltIn(r);
        return p;
    }

    @Override
    public Void visit(While w, Void p) {
        w.getBody().forEach(s -> s.setReturnType(w.getReturnType()));
        super.visit(w, p);
        w.getCondition().getType().mustBeLogical(w);
        return p;
    }

    @Override
    public Void visit(If i, Void p) {
        i.getThenPart().forEach(s -> s.setReturnType(i.getReturnType()));
        i.getElsePart().forEach(s -> s.setReturnType(i.getReturnType()));
        super.visit(i, p);
        i.getCondition().getType().mustBeLogical(i);
        return p;
    }

    @Override
    public Void visit(Assignment a, Void p) {
        super.visit(a, p);
        a.getLeft().getType().mustBePromotes(a.getRight().getType(), a);
        return p;
    }

    @Override
    public Void visit(Invocation i, Void p) {
        super.visit(i, p);
        i.setType(i.getName().getType().parenthesis(i.getArguments(), i));
        return p;
    }

    @Override
    public Void visit(Return w, Void p) {
        super.visit(w, p);
        w.getReturnPart().getType().mustBePromotes(w.getReturnType(), w);
        return p;
    }

    @Override
    public Void visit(FunctionDefinition f, Void p) {
        f.getBody().forEach(s -> s.setReturnType(((FunctionType) f.getType()).getReturnType()));
        super.visit(f, p);
        return p;
    }
}
