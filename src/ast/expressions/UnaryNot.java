package ast.expressions;

public class UnaryNot extends UnaryOperation{
    public UnaryNot(int line, int column, Expression operand) {
        super(line, column, operand);
    }
}
