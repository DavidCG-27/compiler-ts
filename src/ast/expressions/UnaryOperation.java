package ast.expressions;

public abstract class UnaryOperation extends AbstractExpression {
    private Expression operand;
    public UnaryOperation(int line, int column, Expression operand) {
        super(line,column);
        setOperand(operand);
    }

    public Expression getOperand() {
        return operand;
    }

    private void setOperand(Expression operand) {
        this.operand = operand;
    }
}
