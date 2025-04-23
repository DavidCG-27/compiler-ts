package codegen;

import ast.expressions.*;
import ast.statements.Invocation;
import ast.types.FunctionType;
import ast.types.IntType;
import ast.types.RecordField;
import visitor.AbstractVisitor;

public class ValueCGVisitor extends AbstractCGVisitor<Void,Void> {
    private AddressCGVisitor addressCGVisitor;
    public ValueCGVisitor(CodeGenerator codeGenerator, AddressCGVisitor addressCGVisitor) {
        super(codeGenerator);
        this.addressCGVisitor = addressCGVisitor;
    }
    /**
     value[[Variable: expression -> ID]]():
        address[[expression]]
        <load> expression.getType().suffix()
     */
    public Void visit(Variable v, Void arg){
        v.accept(addressCGVisitor,null);
        cg.load(v.getType());
        return arg;
    }

    /**
     value[[Arithmetic: expression1 -> expression2 (+|-|*|/|%) expression3]]():
         value[[expression2]]()
         cg.convertTo(expression2.getType(), expression1.getType())
         value[[expression3]]()
         cg.convertTo(expression3.getType(), expression1.getType())
         cg.arithmetic(expression1.getOperation(), expression1.getType())
     */
    public Void visit(Arithmetic a, Void p) {

        a.getLeft().accept(this, p);
        cg.convertTo(a.getLeft().getType(), a.getType());
        a.getRight().accept(this, p);
        cg.convertTo(a.getRight().getType(), a.getType());
        cg.arithmetic(a.getOperation(), a.getType());
        return null;
    }

    /*
        value[[ArrayAccess: expression1 -> expression2 expression3]]():
            address[[expression1]]
            <load> expression1.type.suffix()
     */
    public Void visit(ArrayAccess a, Void p) {
        a.accept(this.addressCGVisitor, p);
        cg.load(a.getType());
        return null;
    }

    /*
        value[[Cast: expression1-> type expression2]]():
            value[[expression2]]
            cg.convertTo(expression2.getType(), type);
     */
    public Void visit(Cast c, Void p) {
        c.getExpression().accept(this, p);
        cg.convertTo(c.getExpression().getType(), c.getType());
        return null;
    }

    /*
        value[[CharLiteral: expression -> CHAR_CONSTANT]]():
            <pushb> CHAR_CONSTANT
     */
    public Void visit(CharLiteral c, Void p) {
        cg.pushb(c.getValue());
        return null;
    }

    /*
        value[[Comparison: expression1 -> expression2 (==|!=|>=|<=|>|<) expression3]]():
            value[[expression2]]()
            cg.convertTo(expression2.getType(), expression1.getType())
            value[[expression3]]()
            cg.convertTo(expression3.getType(), expression1.getType())
            cg.comparison(expression1.getOperator(), expression1.getType())
     */
    public Void visit(Comparison c, Void p) {
        c.getLeft().accept(this, p);
        cg.convertTo(c.getLeft().getType(), c.getType());
        c.getRight().accept(this, p);
        cg.convertTo(c.getRight().getType(), c.getType());
        cg.comparison(c.getOperation(), c.getType());
        return null;
    }

    /*
        value[[FunctionInvocation: expression1 -> expression2 expression3*]]():
            for (int i = 0; i < expression3*.size(); i++) {
                expression3*.get(i).accept(this, p);
                this.cg.convertTo(expression3*.get(i).getType(),
                     ((FunctionType) expression2.getDefinition().getType()).getArguments().get(i).getType());
            }
            <call> expression2
     */
    public Void visit(Invocation in, Void p) {
        for (int i = 0; i < in.getArguments().size(); i++) {
            in.getArguments().get(i).accept(this, p);
            this.cg.convertTo(in.getArguments().get(i).getType(),
                    ((FunctionType) in.getName().getDefinition().getType()).getArguments().get(i).getType());
        }
        this.cg.call(in.getName().getName());
        return null;
    }

    /*
        value[[IntLiteral: expression -> INT_CONSTANT]]():
            <pushi> INT_CONSTANT
     */
    public Void visit(IntLiteral i, Void p) {
        cg.pushi(i.getValue());
        return null;
    }

    /*
        value[[Logical: expression1 -> expression2 (&&|||) expression3]]() =
            value[[expression2]]()
            value[[expression3]]()
            cg.logical(expression1.getOperation())
     */
    public Void visit(Logic l, Void p) {
        l.getLeft().accept(this, p);
        l.getRight().accept(this, p);
        this.cg.logical(l.getOperation());
        return null;
    }

    /*
        value[[NumberLiteral: expression -> NUMBER_CONSTANT]]():
            <pushf> NUMBER_CONSTANT
     */
    public Void visit(NumberLiteral n, Void p) {
        cg.pushf(n.getValue());
        return null;
    }

    /*
        value[[StructAccess: expression1 -> expression2 ID]]():
            address[[expression1]]
            <load> expression1.getType().getSuffix()
     */
    public Void visit(RecordField r, Void p) {
        r.accept(addressCGVisitor, p);
        cg.load(r.getType());
        return null;
    }

    /*
        value[[UnaryMinus: expression1 -> expression2]]():
            value[[expression2]]()
            cg.convertTo(expression2.getType(), expression1.getType())
            <pushi> -1
            cg.convertTo(IntType.type, expression1.getType())
            <mul> expression1.getType().getSuffix()
     */
    public Void visit(UnaryMinus u, Void p) {
        u.getOperand().accept(this, p);
        cg.convertTo(u.getOperand().getType(), u.getType());
        cg.pushi(-1);
        cg.convertTo(IntType.type, u.getType());
        cg.mul(u.getType());
        return null;
    }

    /*
        value[[UnaryNot: expression1 -> expression2]]():
            value[[expression2]]()
            cg.logical(expression1.getOperand())
     */
    public Void visit(UnaryNot u, Void p) {
        u.getOperand().accept(this, p);
        cg.not();
        return null;
    }

}
