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
            cg.pushi(((VarDefinition) v.getDefinition()).getOffset());
            cg.add(IntType.type);
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
        cg.pushi(a.getType().getSize());
        a.getIndexExpression().accept(valueCGVisitor, p);
        cg.mul(IntType.type);
        cg.add(IntType.type);
        return null;
    }

    /*
        address[[FieldAccess: expression1 -> expression2 ID]]():
            address[[expression2]]
            <pushi> expression2.getType().getField(expression1.getName()).getOffset()
            <addi>
     */
    public Void visit(FieldAccess r, Void p) {
        r.getLeft().accept(this, p);
        int offset = 0;
        for(var rf : ((RecordType) r.getLeft().getType()).getRecordFields()){
            if(rf.getName().equalsIgnoreCase(r.getFillName())){
                offset = rf.getOffset();
            }
        }
        this.cg.pushi(offset);
        this.cg.add(IntType.type);
        return null;
    }

}
