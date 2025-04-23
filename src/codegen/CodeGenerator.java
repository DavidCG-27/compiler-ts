package codegen;

import ast.types.Type;

import java.io.PrintWriter;

public class CodeGenerator {
    private PrintWriter out;
    private String sourceFileName;
    private int counterLabels;
    public CodeGenerator(String outputFilename, String sourceFilename) {
        try{
            this.out=new PrintWriter(outputFilename);
            this.sourceFileName=sourceFilename;
        }catch (Exception e){
            System.err.println("Error opening the file '" + outputFilename + "'");
            System.exit(-1);
        }
    }

    /** ----------- FUNCTIONS ----------- **/

    public void call(String function){
        out.println("call\t"+function);
        out.flush();
    }

    public void enter(int size){
        out.println("\t\tenter\t\t"+size);
        out.flush();
    }

    public void halt(){
        out.println("halt");
        out.flush();
    }

    public void ret(int bytesToReturn, int bytesLocalVariables, int bytesParameters){
        out.println("\t\tret "+bytesToReturn+", "+bytesLocalVariables+", "+bytesParameters);
        out.flush();
    }

    public void comment(String message){
        out.println("\t\t\' *\t"+message);
        out.flush();
    }

    /** ----------- I/O ----------- **/

    public void in(Type type){
        out.println("\t\tin"+type.suffix());
        out.flush();
    }

    public void out(Type type){
        out.println("\t\tout"+type.suffix());
        out.flush();
    }

    /** ----------- LOAD AND STORE ----------- **/

    public void store(Type type){
        out.println("\t\tstore"+type.suffix());
        out.flush();
    }

    public void load(Type type){
        out.println("\t\tload"+type.suffix());
        out.flush();
    }

    /** ----------- PUSH ----------- **/

    public void pushb(char c){
        out.println("\t\tpushb "+((int)c));
        out.flush();
    }

    public void pushf(double r){
        out.println("\t\tpushf "+r);
        out.flush();
    }

    public void pusha(int address){
        out.println("\t\tpusha "+address);
        out.flush();
    }

    public void pushbp(){
        out.println("\t\tpush bp");
        out.flush();
    }

    public void pushi(int i){
        out.println("\t\tpushi "+i);
        out.flush();
    }

    /** ----------- POPPING AND DUPLICATING ----------- **/

    public void pop(Type type){
        out.println("\t\tpop"+type.suffix());
        out.flush();
    }

    public void dup(Type type){
        out.println("\t\tdup"+type.suffix());
        out.flush();
    }

    /** ----------- ARITHMETIC OPERATIONS ----------- **/

    public void add(Type type){
        out.println("\t\tadd"+type.suffix());
        out.flush();
    }

    public void sub(Type type){
        out.println("\t\tsub"+type.suffix());
        out.flush();
    }

    public void mul(Type type){
        out.println("\t\tmul"+type.suffix());
        out.flush();
    }

    public void div(Type type){
        out.println("\t\tdiv"+type.suffix());
        out.flush();
    }

    public void mod(Type type){
        out.println("\t\tmod"+type.suffix());
        out.flush();
    }

    public void arithmetic(String operator, Type type){
        switch (operator){
            case "+":
                this.add(type);
                break;
            case "-":
                this.sub(type);
                break;
            case "*":
                this.mul(type);
                break;
            case "/":
                this.div(type);
                break;
            case "%":
                this.mod(type);
                break;
        }
    }

    /** ----------- COMPARISON OPERATIONS ----------- **/

    public void gt(Type type){
        out.println("\t\tgt"+type.suffix());
        out.flush();
    }

    public void lt(Type type){
        out.println("\t\tlt"+type.suffix());
        out.flush();
    }

    public void ge(Type type){
        out.println("\t\tge"+type.suffix());
        out.flush();
    }

    public void le(Type type){
        out.println("\t\tle"+type.suffix());
        out.flush();
    }

    public void eq(Type type){
        out.println("\t\teq "+type.suffix());
        out.flush();
    }

    public void ne(Type type){
        out.println("\t\tne "+type.suffix());
        out.flush();
    }

    public void comparison(String operator, Type type){
        switch (operator){
            case ">":
                this.gt(type);
                break;
            case "<":
                this.lt(type);
                break;
            case ">=":
                this.ge(type);
                break;
            case "<=":
                this.le(type);
                break;
            case "==":
                this.eq(type);
                break;
            case "!=":
                this.ne(type);
                break;
        }
    }

    /** ----------- LOGICAL OPERATIONS ----------- **/

    public void and(){
        out.println("\t\tand");
        out.flush();
    }

    public void or(){
        out.println("\t\tor");
        out.flush();
    }

    public void not(){
        out.println("\t\tnot");
        out.flush();
    }

    public void logical(String operator){
        switch (operator){
            case "&&":
                this.and();
                break;
            case "||":
                this.or();
                break;
            case "!":
                this.not();
                break;
        }
    }

    /** ----------- CONVERSIONS ----------- **/

    public void b2i(){
        out.println("\t\tb2i");
        out.flush();
    }

    public void i2f(){
        out.println("\t\ti2f");
        out.flush();
    }

    public void f2i(){
        out.println("\t\tf2i");
        out.flush();
    }

    public void i2b(){
        out.println("\t\ti2b");
        out.flush();
    }

    public void convertTo(Type type1, Type type2){
        if(type1.getSize() == 1 && type2.getSize() == 2){
            this.b2i();
        }
        else if(type1.getSize() == 2 && type2.getSize() == 4){
            this.i2f();
        }
        else if(type1.getSize() == 4 && type2.getSize() == 2){
            this.f2i();
        }
        else if(type1.getSize() == 2 && type2.getSize() == 1){
            this.i2b();
        }
    }

    /** ----------- JUMPS ----------- **/

    public void label(String label){
        out.println("\t\t"+label+":");
        out.flush();
    }

    public String nextLabel() {
        return "label"+(counterLabels++);
    }

    public void jmp(String label){
        out.println("\t\tjmp "+label);
        out.flush();
    }

    public void jz(String label){
        out.println("\t\tjz "+label);
        out.flush();
    }

    public void jnz(String label){
        out.println("\t\tjnz "+label);
        out.flush();
    }

    /** ----------- DEBUGGING INFO ----------- **/

    public void source(){
        out.println("#source "+this.sourceFileName);
        out.flush();
    }

    public void line(int line){
        out.println("#line "+line);
        out.flush();
    }



}
