package visitor;

import ast.definitions.Definition;
import ast.definitions.FunctionDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.Variable;
import ast.statements.Invocation;
import ast.types.ErrorType;
import symboltable.SymbolTable;

public class IdentificationVisitor extends AbstractVisitor<Void,Void>{

    private final SymbolTable symbolTable = new SymbolTable();

    @Override
    public Void visit(Variable v, Void arg) {
        Definition def = symbolTable.find(v.getName());
        if(def==null){
            new ErrorType("Variable "+v.getName()+" no tiene declaración", v);
        }
        v.setDefinition(def);
        super.visit(v, arg);
        return arg;
    }

    @Override
    public Void visit(VarDefinition v, Void arg) {
        if(!symbolTable.insert(v))
            new ErrorType("Ya existe una declaración para la variable "+v.getName(), v);
        super.visit(v, arg);
        return arg;
    }

    @Override
    public Void visit(FunctionDefinition f, Void arg) {
        if(!symbolTable.insert(f))
            new ErrorType("Ya existe una declaración para la función "+f.getName(), f);
        symbolTable.set();
        super.visit(f, arg);
        symbolTable.reset();
        return arg;
    }
}
