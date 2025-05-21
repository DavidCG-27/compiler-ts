package codegen;

import ast.Program;
import ast.definitions.Definition;
import ast.definitions.FunctionDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.Expression;
import ast.statements.*;
import ast.types.FunctionType;
import ast.types.VoidType;
import visitor.AbstractVisitor;

public class ExecuteCGVisitor extends AbstractCGVisitor<Void,FunctionDefinition> {
    private ValueCGVisitor valueCGVisitor;
    private AddressCGVisitor addressCGVisitor;
    public ExecuteCGVisitor(CodeGenerator codeGenerator) {
        super(codeGenerator);
        this.addressCGVisitor = new AddressCGVisitor(cg);
        this.valueCGVisitor = new ValueCGVisitor(cg, addressCGVisitor);
        this.addressCGVisitor.setValueCGVisitor(valueCGVisitor);
    }

    /**
     execute [[Program: program -> definition*]]():
         for (Definition def: definition*)
            if(def instanceof VarDefinition)
                execute[[def]]()
         <' * Invocation to the main function>
         <call main>
         <halt>
         for (Definition def: definition*)
            if(def instanceof FunctionDefinition)
                execute[[def]]()
     */
    public Void visit(Program p, FunctionDefinition arg) {
        for(Definition d : p.getDefinitions()){
            if(d instanceof VarDefinition){
                d.accept(this, arg);
            }
        }
        cg.comment("Invocation to the main function");
        cg.call("main");
        cg.halt();
        for(Definition d : p.getDefinitions()){
            if(d instanceof FunctionDefinition){
                d.accept(this, (FunctionDefinition) d);
            }
        }
        return null;
    }

    /**
     execute [[VarDefinition: definition -> type ID]]():
        <' *> type ID <( offset > definition.offset <)>
     */
    public Void visit(VarDefinition v, FunctionDefinition arg) {
        cg.comment(v.getType().toString()+"\t"+v.getName()+"\t(offset "+v.getOffset()+")");
        return null;
    }

    /**
     execute [[FunctionDefinition: definition -> ID type statement*]]():
        ID<:>
            <enter> definition.localBytesSum
            <' * Parameters>
            type.getArguments().forEach(p -> execute[[p]]())
            <' * Local Variables>
            statement*.forEach(s -> execute[[s]]())
     */
    public Void visit(FunctionDefinition f, FunctionDefinition arg) {
        cg.label(f.getName());
        cg.enter(f.getLocalBytesSum());
        cg.comment("Parameters");
        ((FunctionType) f.getType()).getArguments().forEach(a -> a.accept(this, arg));
        cg.comment("Local variables");
        f.getBody().forEach(l -> l.accept(this, arg));
        if (((FunctionType) f.getType()).getReturnType() == VoidType.type){
            int bReturn = 0;
            int bLocals = f.getLocalBytesSum();
            int bParams = 0;
            for (VarDefinition v : ((FunctionType) f.getType()).getArguments())
                bParams += v.getType().getSize();
            cg.ret(bReturn, bLocals, bParams);
        }
        return null;
    }

    /**
     execute[[Write: statement -> expression]]():
        value[[expression]]()
        <out> expression.getType.suffix()
     */
    public Void visit(Write w, FunctionDefinition arg){
        cg.line(w.getLine());
        cg.comment("Write");
        w.getArgument().accept(valueCGVisitor, null);
        cg.out(w.getArgument().getType());
        return null;
    }

    /**
     execute[[Read: statement -> expression]]():
        address[[expression]]()
        <in> expression.getType.suffix()
        <store> expression.getType.suffix()
     */
    public Void visit(Read r, FunctionDefinition arg) {
        cg.line(r.getLine());
        cg.comment("Read");
        r.getArgument().accept(addressCGVisitor, null);
        cg.in(r.getArgument().getType());
        cg.store(r.getArgument().getType());
        return null;
    }

    /**

     execute[[Assignment: statement -> expression1 expression2]]():
        <#line  > statement.getLine()
        <'  * Assignment>
        address[[expression1]]()
        value[[expression2]]()
        <store> expression1.getType().getSuffix()
     */
    public Void visit(Assignment a, FunctionDefinition arg) {
        cg.line(a.getLine());
        cg.comment("Assignment");
        a.getLeft().accept(addressCGVisitor, null);
        a.getRight().accept(valueCGVisitor, null);
        cg.store(a.getLeft().getType());
        return null;
    }

    /**
      execute [[If: statement1 -> expression statement2* statement3*]]():
        <#line  > statement1.getLine()
        <'  * If>
        String elsePart = cg.getLabel();
        String end = cg.getLabel();
        value[[condition]]();
        <jz> elsePart
        statement2*.forEach(s -> execute[[s]]());
        <jpm> end
        elsePart<:>
        statement3*.forEach(s -> execute[[s]]());
        end<:>
     */
    public Void visit(If i, FunctionDefinition arg){
        cg.line(i.getLine());
        cg.comment("If");
        String end = cg.nextLabel();
        if(!i.getElsePart().isEmpty()) {
            String elsePart = cg.nextLabel();
            i.getCondition().accept(valueCGVisitor, null);
            cg.jz(elsePart);
            i.getThenPart().forEach(t -> t.accept(this, arg));
            cg.jmp(end);
            cg.label(elsePart);
            i.getElsePart().forEach(t -> t.accept(this, arg));
            cg.label(end);
        } else{
            i.getCondition().accept(valueCGVisitor, null);
            cg.jz(end);
            i.getThenPart().forEach(t -> t.accept(this, arg));
            cg.label(end);
        }
        return null;
    }

    /**
      execute[[Invocation: statement -> expression1 expression2*]]():
        <#line  > statement.getLine()
        <'  * Invocation>
        value[[(Expression) statement]]()
             if (!statement.getType() instanceof VoidType) {
                 <pop> statement.getType().getSuffix();
             }
     */
    public Void visit(Invocation i, FunctionDefinition arg) {
        cg.line(i.getLine());
        cg.comment("Invocation");
        i.accept(valueCGVisitor, null);
        if(!(i.getType() instanceof VoidType)){
            cg.pop(i.getType());
        }
        return null;
    }

    /*
     execute[[Return: statement -> expression]](FunctionDefinition fd):
         <#line> statement.getLine()
         <' * Return>
         value[[expression]]()
         int bReturn = ((FunctionType) fd.getType()).getReturnType().getSize();
         int bLocals = fd.getLocalBytesSum();
         int bParams = 0;
         for (VarDefinition v : ((FunctionType) fd.getType()).getArguments())
            bParams += v.getType().getSize()
         <ret> bReturn <,> bLocals <,> bParams
    */
    public Void visit(Return r, FunctionDefinition fd) {
        cg.line(r.getLine());
        cg.comment("Return");
        r.getReturnPart().accept(valueCGVisitor, null);
        int bReturn = ((FunctionType) fd.getType()).getReturnType().getSize();
        int bLocals = fd.getLocalBytesSum();
        int bParams = 0;
        for (VarDefinition v : ((FunctionType) fd.getType()).getArguments())
            bParams += v.getType().getSize();
        cg.ret(bReturn, bLocals, bParams);
        return null;
    }

    /*
    execute[[While: statement1 -> expression statement2*]]():
        String condition = cg.nextLabel();
        String end = cg.nextLabel();
        <label> condition <:>
        value[[expression]]()
        <jz> end
        statement2*.forEach(stmnt -> execute[[stmnt]])
        <jmp label> condition
        <label> end <:>
    */
    public Void visit(While w, FunctionDefinition p) {
        cg.line(w.getLine());
        cg.comment("While");
        String condition = cg.nextLabel();
        String end = cg.nextLabel();
        cg.label(condition);
        w.getCondition().accept(valueCGVisitor, null);
        cg.jz(end);
        cg.comment("Body (while)");
        w.getBody().forEach(statement -> statement.accept(this, p));
        cg.jmp(condition);
        cg.label(end);
        return null;
    }
}
