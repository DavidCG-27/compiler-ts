package visitor;

import ast.Program;
import ast.definitions.FunctionDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.*;
import ast.statements.*;
import ast.types.*;
import errorhandler.ErrorHandler;

public class LValueVisitor extends AbstractVisitor<Void, Void> {
    @Override
    public Void visit(Comparison c, Void p) {
        super.visit(c, p);
        c.setLValue(false);
        return null;
    }

    @Override
    public Void visit(Logic l, Void p) {
        super.visit(l, p);
        l.setLValue(false);
        return null;
    }

    @Override
    public Void visit(Arithmetic a, Void p) {
        super.visit(a, p);
        a.setLValue(false);
        return null;
    }

    @Override
    public Void visit(UnaryMinus u, Void p) {
        super.visit(u, p);
        u.setLValue(false);
        return null;
    }

    @Override
    public Void visit(UnaryNot u, Void p) {
        super.visit(u, p);
        u.setLValue(false);
        return null;
    }

    @Override
    public Void visit(FieldAccess f, Void p) {
        super.visit(f, p);
        f.setLValue(true);
        return null;
    }

    @Override
    public Void visit(ArrayAccess a, Void p) {
        super.visit(a, p);
        a.setLValue(true);
        return null;
    }

    @Override
    public Void visit(IntLiteral i, Void p) {
        i.setLValue(false);
        return null;
    }

    @Override
    public Void visit(NumberLiteral n, Void p) {
        n.setLValue(false);
        return null;
    }

    @Override
    public Void visit(CharLiteral c, Void p) {
        c.setLValue(false);
        return null;
    }

    @Override
    public Void visit(Variable v, Void p) {
        v.setLValue(true);
        return null;
    }

    @Override
    public Void visit(Cast c, Void p) {
        super.visit(c, p);
        c.setLValue(false);
        return null;
    }

    @Override
    public Void visit(Read r, Void p) {
        super.visit(r, p);
        if(!r.getArgument().getLValue()){
            new ErrorType("Argument cannot be written (not LValue)", r);
        }
        return null;
    }

    @Override
    public Void visit(Assignment a, Void p) {
        super.visit(a, p);
        if(!a.getLeft().getLValue()){
            new ErrorType("Left side is not LValue", a);
        }
        return null;
    }
}
