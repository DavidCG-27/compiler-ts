package ast;

public abstract class AbstractLocutable implements Locutable {
    private int line;
    private int column;

    public AbstractLocutable(int line, int column) {
        this.line = line;
        this.column = column;
    }

    @Override
    public int getLine() {
        return line;
    }

    @Override
    public int getColumn() {
        return column;
    }
}
