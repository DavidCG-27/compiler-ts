package codegen;

import ast.definitions.VarDefinition;
import ast.expressions.*;
import ast.types.IntType;
import ast.types.RecordField;
import ast.types.RecordType;

public class AddressCGVisitor extends AbstractCGVisitor<Void, Void> {

    private ValueCGVisitor valueCGVisitor;

    public AddressCGVisitor(CodeGenerator codeGenerator) {
        super(codeGenerator);
    }

    public void setValueCGVisitor(ValueCGVisitor valueCGVisitor) {
        this.valueCGVisitor = valueCGVisitor;
    }

    /**
     address[[Variable: expression -> ID]]():
        if(expression.getDefinition().getScope()==0)
            <pusha> expression.getDefinition().getOffset()
        else
            <push bp>
            <pusha> expression.getDefinition().getOffset()
            <addi>
     */
    public Void visit(Variable v, Void arg) {
        if(v.getDefinition().getScope()==0)
            cg.pusha(((VarDefinition)v.getDefinition()).getOffset());
        else {
            cg.pushbp();
            cg.pusha(((VarDefinition) v.getDefinition()).getOffset());
            cg.add(v.getDefinition().getType());
        }
        return arg;
    }

    /*
        address[[ArrayAccess: expression1 -> expression2 expression2]]():
            address[[expression2]]
            value[[expression3]]
            <pushi> expression1.getType().getSize()
            <muli>
            <addi>
     */
    public Void visit(ArrayAccess a, Void p) {
        a.getArrayExpression().accept(this, p);
        a.getIndexExpression().accept(valueCGVisitor, p);
        cg.pushi(a.getType().getSize());
        cg.mul(IntType.type);
        cg.add(IntType.type);
        return null;
    }

    /*
        address[[StructAccess: expression1 -> expression2 ID]]():
            address[[expression2]]
            <pushi> expression2.getType().getField(expression1.getName()).getOffset()
            <addi>
     */
//    public Void visit(RecordField r, Void p) {
//        r.getName().accept(this, p);
//        this.cg.push(((RecordType) r.getName().getType()).getRecordFields().getOffset());
//        this.cg.add(IntType.getInstance());
//        return null;
//    }

}
