package ast.expressions;

public class Comparison extends AbstractOperation{

    public Comparison(int line, int column, String operation, Expression left, Expression right) {
        super(line, column, operation, left, right);
    }
}
