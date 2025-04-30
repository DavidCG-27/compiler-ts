package codegen;

import ast.Program;
import ast.definitions.FunctionDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.*;
import ast.statements.*;
import ast.types.*;
import visitor.Visitor;

public class AbstractCGVisitor<RT,PT> implements Visitor<RT,PT> {

    CodeGenerator cg;

    public AbstractCGVisitor(CodeGenerator cg) {
        this.cg = cg;
    }

    @Override
    public RT visit(Comparison c, PT p) {
        throw new IllegalStateException("This action is not permitted");
    }

    @Override
    public RT visit(Logic l, PT p) {
        throw new IllegalStateException("This action is not permitted");
    }

    @Override
    public RT visit(Arithmetic a, PT p) {
        throw new IllegalStateException("This action is not permitted");
    }

    @Override
    public RT visit(UnaryMinus u, PT p) {
        throw new IllegalStateException("This action is not permitted");
    }

    @Override
    public RT visit(UnaryNot u, PT p) {
        throw new IllegalStateException("This action is not permitted");
    }

    @Override
    public RT visit(FieldAccess f, PT p) {
        throw new IllegalStateException("This action is not permitted");
    }

    @Override
    public RT visit(ArrayAccess a, PT p) {
        throw new IllegalStateException("This action is not permitted");
    }

    @Override
    public RT visit(IntLiteral i, PT p) {
        throw new IllegalStateException("This action is not permitted");
    }

    @Override
    public RT visit(NumberLiteral n, PT p) {
        throw new IllegalStateException("This action is not permitted");
    }

    @Override
    public RT visit(CharLiteral c, PT p) {
        throw new IllegalStateException("This action is not permitted");
    }

    @Override
    public RT visit(Variable v, PT p) {
        throw new IllegalStateException("This action is not permitted");
    }

    @Override
    public RT visit(Cast c, PT p) {
        throw new IllegalStateException("This action is not permitted");
    }

    @Override
    public RT visit(Invocation i, PT p) {
        throw new IllegalStateException("This action is not permitted");
    }

    @Override
    public RT visit(While w, PT p) {
        throw new IllegalStateException("This action is not permitted");
    }

    @Override
    public RT visit(Write w, PT p) {
        throw new IllegalStateException("This action is not permitted");
    }

    @Override
    public RT visit(Read r, PT p) {
        throw new IllegalStateException("This action is not permitted");
    }

    @Override
    public RT visit(If i, PT p) {
        throw new IllegalStateException("This action is not permitted");
    }

    @Override
    public RT visit(Return w, PT p) {
        throw new IllegalStateException("This action is not permitted");
    }

    @Override
    public RT visit(Assignment a, PT p) {
        throw new IllegalStateException("This action is not permitted");
    }

    @Override
    public RT visit(VarDefinition v, PT p) {
        throw new IllegalStateException("This action is not permitted");
    }

    @Override
    public RT visit(FunctionDefinition f, PT p) {
        throw new IllegalStateException("This action is not permitted");
    }

    @Override
    public RT visit(RecordField r, PT p) {
        throw new IllegalStateException("This action is not permitted");
    }

    @Override
    public RT visit(FunctionType f, PT p) {
        throw new IllegalStateException("This action is not permitted");
    }

    @Override
    public RT visit(RecordType r, PT p) {
        throw new IllegalStateException("This action is not permitted");
    }

    @Override
    public RT visit(ArrayType a, PT p) {
        throw new IllegalStateException("This action is not permitted");
    }

    @Override
    public RT visit(CharType c, PT p) {
        throw new IllegalStateException("This action is not permitted");
    }

    @Override
    public RT visit(ErrorType s, PT p) {
        throw new IllegalStateException("This action is not permitted");
    }

    @Override
    public RT visit(IntType i, PT p) {
        throw new IllegalStateException("This action is not permitted");
    }

    @Override
    public RT visit(NumberType n, PT p) {
        throw new IllegalStateException("This action is not permitted");
    }

    @Override
    public RT visit(VoidType v, PT p) {
        throw new IllegalStateException("This action is not permitted");
    }

    @Override
    public RT visit(Program a, PT p) {
        throw new IllegalStateException("This action is not permitted");
    }

}
