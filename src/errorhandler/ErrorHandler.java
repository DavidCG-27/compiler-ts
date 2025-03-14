package errorhandler;

import ast.types.ErrorType;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ErrorHandler {
    private static final ErrorHandler errorHandler = new ErrorHandler();
    private List<ErrorType> errors = new ArrayList<>();

    private ErrorHandler(){}

    public static ErrorHandler getInstance(){
        return errorHandler;
    }

    public void addError(ErrorType error){
        errors.add(error);
    }

    public void showErrors(PrintStream out){
        errors.forEach(error -> out.println(error));
        if(errors.stream().anyMatch(error -> error.toString()==null)){}
    }

    public boolean anyError(){
        return errors.size() > 0;
    }

}
