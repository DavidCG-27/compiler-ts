package ast.expressions;


public class UnaryMinus extends UnaryOperation {

    public UnaryMinus(int line, int column, Expression operand) {
        super(line, column, operand);
    }
}
