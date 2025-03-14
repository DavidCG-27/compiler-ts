package ast.expressions;

public class Arithmetic extends AbstractOperation{
    public Arithmetic(int line, int column, String operation, Expression left, Expression right) {
        super(line, column, operation, left, right);
    }
}
