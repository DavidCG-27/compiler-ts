package ast.expressions;

public abstract class AbstractOperation extends AbstractExpression {

    private String operation;
    private Expression left;
    private Expression right;

    public AbstractOperation(int line, int column, String operation, Expression left, Expression right) {
        super(line, column);
        if(left == null || right == null || operation == null){
            throw new IllegalArgumentException("left and right cannot be null");
        }
        setOperation(operation);
        setLeft(left);
        setRight(right);
    }

    public String getOperation() {
        return operation;
    }

    private void setOperation(String operation) {
        this.operation = operation;
    }

    public Expression getLeft() {
        return left;
    }

    private void setLeft(Expression left) {
        this.left = left;
    }

    public Expression getRight() {
        return right;
    }

    private void setRight(Expression right) {
        this.right = right;
    }
}
