package ast.expressions;

public class Logic extends AbstractOperation{
    public Logic(int line, int column, String operation, Expression left, Expression right) {
        super(line, column, operation, left, right);
    }
}
