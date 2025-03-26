package visitor;

import ast.Program;
import ast.definitions.FunctionDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.*;
import ast.statements.*;
import ast.types.*;
import symboltable.SymbolTable;

public abstract class AbstractVisitor<RT,PT> implements Visitor<RT,PT>{
    @Override
    public RT visit(Comparison c, PT p) {
        c.getLeft().accept(this, p);
        c.getRight().accept(this, p);
        return null;
    }

    @Override
    public RT visit(Logic l, PT p) {
        l.getLeft().accept(this, p);
        l.getRight().accept(this, p);
        return null;    }

    @Override
    public RT visit(Arithmetic a, PT p) {
        a.getLeft().accept(this, p);
        a.getRight().accept(this, p);
        return null;
    }

    @Override
    public RT visit(UnaryMinus u, PT p) {
        u.getOperand().accept(this, p);
        return null;
    }

    @Override
    public RT visit(UnaryNot u, PT p) {
        u.getOperand().accept(this, p);
        return null;    }

    @Override
    public RT visit(FieldAccess f, PT p) {
        f.getLeft().accept(this, p);
        return null;
    }

    @Override
    public RT visit(ArrayAccess a, PT p) {
        a.getArrayExpression().accept(this, p);
        a.getIndexExpression().accept(this, p);
        return null;
    }

    @Override
    public RT visit(IntLiteral i, PT p) {
        return null;
    }

    @Override
    public RT visit(NumberLiteral n, PT p) {
        return null;
    }

    @Override
    public RT visit(CharLiteral c, PT p) {
        return null;
    }

    @Override
    public RT visit(Variable v, PT p) {
        return null;
    }

    @Override
    public RT visit(Cast c, PT p) {
        c.getExpression().accept(this, p);
        return null;
    }

    @Override
    public RT visit(Invocation i, PT p) {
        i.getArguments().forEach(a -> a.accept(this, p));
        i.getName().accept(this, p);
        return null;
    }

    @Override
    public RT visit(While w, PT p) {
        w.getCondition().accept(this, p);
        w.getBody().forEach(l -> l.accept(this, p));
        return null;
    }

    @Override
    public RT visit(Write w, PT p) {
        w.getArgument().accept(this, p);
        return null;
    }

    @Override
    public RT visit(Read r, PT p) {
        r.getArgument().accept(this, p);
        return null;
    }

    @Override
    public RT visit(If i, PT p) {
        i.getCondition().accept(this, p);
        i.getThenPart().forEach(l -> l.accept(this, p));
        i.getElsePart().forEach(l -> l.accept(this, p));
        return null;
    }

    @Override
    public RT visit(Return w, PT p) {
        w.getReturnPart().accept(this, p);
        return null;
    }

    @Override
    public RT visit(Assignment a, PT p) {
        a.getLeft().accept(this, p);
        a.getRight().accept(this, p);
        return null;
    }

    @Override
    public RT visit(VarDefinition v, PT p) {
        v.getType().accept(this, p);
        return null;
    }

    @Override
    public RT visit(FunctionDefinition f, PT p) {
        f.getType().accept(this, p);
        f.getBody().forEach(l -> l.accept(this, p));
        return null;
    }

    @Override
    public RT visit(RecordField r, PT p) {
        r.getType().accept(this, p);
        return null;
    }

    @Override
    public RT visit(FunctionType f, PT p) {
        f.getArguments().forEach(a -> a.accept(this, p));
        f.getReturnType().accept(this, p);
        return null;
    }

    @Override
    public RT visit(RecordType r, PT p) {
        r.getRecordFields().forEach(a -> a.accept(this, p));
        return null;
    }

    @Override
    public RT visit(ArrayType a, PT p) {
        a.getType().accept(this, p);
        return null;
    }

    @Override
    public RT visit(CharType c, PT p) {
        return null;
    }

    @Override
    public RT visit(ErrorType s, PT p) {
        return null;
    }

    @Override
    public RT visit(IntType i, PT p) {
        return null;
    }

    @Override
    public RT visit(NumberType n, PT p) {
        return null;
    }

    @Override
    public RT visit(VoidType v, PT p) {
        return null;
    }

    @Override
    public RT visit(Program a, PT p) {
        a.getDefinitions().forEach(l -> l.accept(this, p));
        return null;
    }
}
