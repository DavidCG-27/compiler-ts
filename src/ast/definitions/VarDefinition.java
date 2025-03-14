package ast.definitions;

import ast.statements.Statement;
import ast.types.Type;
import visitor.Visitor;

public class VarDefinition extends AbstractDefinition implements Statement {
    public VarDefinition(int line, int column, String name, Type type) {
        super(line, column, type, name);
    }

    @Override
    public <RT, PT> RT accept(Visitor<RT, PT> v, PT p) {
        return v.visit(this,p);
    }

}
