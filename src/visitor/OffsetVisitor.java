package visitor;

import ast.definitions.FunctionDefinition;
import ast.definitions.VarDefinition;
import ast.types.FunctionType;
import ast.types.RecordField;
import ast.types.RecordType;

public class OffsetVisitor extends AbstractVisitor<Void, Void> {
    private int countSizeGlobal;
    private int countSizeLocal;
    @Override
    public Void visit(VarDefinition v, Void p) {
        if(v.getScope()==0) {
            v.setOffset(countSizeGlobal);
            countSizeGlobal += v.getType().getSize();
        } else {
            countSizeLocal -= v.getType().getSize();
            v.setOffset(countSizeLocal);
        }
        super.visit(v, p);
        return null;
    }

    @Override
    public Void visit(FunctionDefinition f, Void p) {
        countSizeLocal = 0;
        f.getType().accept(this, p);
        f.getBody().forEach(s -> s.accept(this, p));
        f.setLocalBytesSum(-countSizeLocal);
        return null;
    }

    @Override
    public Void visit(RecordType r, Void p) {
        int total = 0;
        for (RecordField field : r.getRecordFields()) {
            field.setOffset(total);
            total += field.getType().getSize();
            field.getType().accept(this, p);
        }
        return null;
    }

    @Override
    public Void visit(FunctionType f, Void p) {
        int total = 4;
        for (int i = f.getArguments().size()-1; i >= 0; i--) {
            f.getArguments().get(i).setOffset(total);
            total += f.getArguments().get(i).getType().getSize();
        }
        return null;
    }




}
