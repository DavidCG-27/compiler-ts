package visitor;
import ast.Program;
import ast.definitions.*;
import ast.expressions.*;
import ast.statements.*;
import ast.types.*;

public interface Visitor<RT, PT> {
    // Expresiones
    public RT visit(Comparison c, PT p);
    public RT visit(Logic l, PT p);
    public RT visit(Arithmetic a, PT p);
    public RT visit(UnaryMinus u, PT p);
    public RT visit(UnaryNot u, PT p);
    public RT visit(FieldAccess f, PT p);
    public RT visit(ArrayAccess a, PT p);
    public RT visit(IntLiteral i, PT p);
    public RT visit(NumberLiteral n, PT p);
    public RT visit(CharLiteral c, PT p);
    public RT visit(Variable v, PT p);
    public RT visit(Cast c, PT p);

    // Statement
    public RT visit(Invocation i, PT p);
    public RT visit(While w, PT p);
    public RT visit(Write w, PT p);
    public RT visit(Read r, PT p);
    public RT visit(If i, PT p);
    public RT visit(Return w, PT p);
    public RT visit(Assignment a, PT p);

    // Definition
    public RT visit(VarDefinition v, PT p);
    public RT visit(FunctionDefinition f, PT p);

    //RecordField
    public RT visit(RecordField r, PT p);

    // Type
    public RT visit(FunctionType f, PT p);
    public RT visit(RecordType r, PT p);
    public RT visit(ArrayType a, PT p);
    public RT visit(CharType c, PT p);
    public RT visit(ErrorType s, PT p);
    public RT visit(IntType i, PT p);
    public RT visit(NumberType n, PT p);
    public RT visit(VoidType v, PT p);

    // Program
    public RT visit(Program a, PT p);

}
