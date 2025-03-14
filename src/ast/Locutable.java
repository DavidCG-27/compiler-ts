package ast;

public interface Locutable extends ASTNode{
    public int getLine();
    public int getColumn();
}
