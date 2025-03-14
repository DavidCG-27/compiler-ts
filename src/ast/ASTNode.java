package ast;

import visitor.Visitor;

public interface ASTNode {
    public <RT,PT> RT accept(Visitor<RT,PT> v, PT p);
}
