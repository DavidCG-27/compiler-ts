grammar TSmm;	

@header {
import ast.*;
import ast.definitions.*;
import ast.expressions.*;
import ast.statements.*;
import ast.types.*;
import java.util.*;
import parser.LexerHelper;
}

program returns [Program ast] locals [List<Definition> definitions = new ArrayList<Definition>()]:
((varDefinition {$definitions.addAll($varDefinition.ast);})|(functionDefinition {$definitions.add($functionDefinition.ast);}))*  mainFunction{$definitions.add($mainFunction.ast);} EOF {$ast=new Program($definitions);}
;

varDefinition returns [List<VarDefinition> ast = new ArrayList<>()] locals [List<String> ids = new ArrayList<>()]:
'let' P1=ID (',' PX=ID {$ids.add($PX.text);})* ':' type ';' {$ast.add(new VarDefinition($P1.getLine(), $P1.getCharPositionInLine()+1, $P1.text, $type.ast)); $ids.forEach(id -> $ast.add(new VarDefinition($P1.getLine(), $P1.getCharPositionInLine()+1, id, $type.ast)));}
;

functionDefinition returns [FunctionDefinition ast] locals [List<VarDefinition> args = new ArrayList<>(), List<Statement> body = new ArrayList<>(), Type t]:
'function' NAME=ID '(' (P1=ID ':' t1=simpleType {$args.add(new VarDefinition($P1.getLine(), $P1.getCharPositionInLine()+1, $P1.text, $t1.ast));} (',' PX=ID ':' tx=simpleType {$args.add(new VarDefinition($PX.getLine(), $PX.getCharPositionInLine()+1, $PX.text, $tx.ast));})*)? ')' ':' ((simpleType {$t = $simpleType.ast;})|('void' {$t = VoidType.type;})) '{' (varDefinition {$body.addAll($varDefinition.ast);})* (statement {$body.addAll($statement.ast);})* '}' {$ast = new FunctionDefinition($NAME.getLine(), $NAME.getCharPositionInLine()+1,new FunctionType($t, $args), $NAME.text, $body);}
;

mainFunction returns [FunctionDefinition ast] locals [List<Statement> st = new ArrayList<>();]:
'function' P='main' '(' ')' ':' 'void' '{' (varDefinition {$st.addAll($varDefinition.ast);})* (statement {$st.addAll($statement.ast);})* '}' {$ast=new FunctionDefinition($P.getLine(), $P.getCharPositionInLine()+1, new FunctionType(VoidType.type, new ArrayList<VarDefinition>()), "main", $st);}
;

type returns [Type ast] locals [List<RecordField> fields = new ArrayList<>();]:
simpleType {$ast=$simpleType.ast;}
| '['INT_CONSTANT']' type {$ast=new ArrayType($type.ast, LexerHelper.lexemeToInt($INT_CONSTANT.text));}
| '[' (varDefinition {$varDefinition.ast.forEach(varDef -> {
        if($fields.stream().anyMatch(field -> field.getName().equals(varDef.getName()))){
            $fields.add(new RecordField(varDef.getLine(), varDef.getColumn(), varDef.getName(), new ErrorType("Error: duplicated field",varDef)));
        }
        else{
            $fields.add(new RecordField(varDef.getLine(), varDef.getColumn(), varDef.getName(), varDef.getType()));
        }
        });
    })+ ']' {$ast = new RecordType($fields);}
;

simpleType returns [Type ast]:
'int' {$ast=IntType.type;}
| 'number' {$ast=NumberType.type;}
| 'char' {$ast=CharType.type;}
;

statement returns [List<Statement> ast = new ArrayList<>();] locals [List<Statement> bl = new ArrayList<>();]:
'log' ex0=expression {$ast.add(new Write($ex0.ast.getLine(), $ex0.ast.getColumn(), $ex0.ast));}(',' ex1=expression {$ast.add(new Write($ex1.ast.getLine(), $ex1.ast.getColumn(), $ex1.ast));})* ';'
| 'input' ex2=expression {$ast.add(new Read($ex2.ast.getLine(), $ex2.ast.getColumn(), $ex2.ast));} (',' ex3=expression {$ast.add(new Read($ex3.ast.getLine(), $ex3.ast.getColumn(), $ex3.ast));})* ';'
| ex4=expression '=' ex5=expression ';' {$ast.add(new Assignment($ex4.ast.getLine(), $ex4.ast.getColumn(), $ex4.ast, $ex5.ast));}
| 'if' '(' condIf=expression ')' b1=block ('else' b2=block {$bl=$b2.ast;})? {$ast.add(new If($condIf.ast.getLine(), $condIf.ast.getColumn(),$b1.ast, $bl, $condIf.ast));}
| 'while' '(' condWh=expression ')' b3=block {$ast.add(new While($condWh.ast.getLine(), $condWh.ast.getColumn(), $condWh.ast, $b3.ast));}
| 'return' ret=expression ';' {$ast.add(new Return($ret.ast.getLine(), $ret.ast.getColumn(), $ret.ast));}
| functionInvocation ';' {$ast.add($functionInvocation.ast);}
;

block returns [List<Statement> ast = new ArrayList<Statement>()]:
statement {$ast.addAll($statement.ast);}
| '{' (statement {$ast.addAll($statement.ast);})* '}'
;

functionInvocation returns [Invocation ast] locals [List<Expression> args = new ArrayList<Expression>();]:
ID '(' ((ex1=expression {$args.add($ex1.ast);} (',' ex2=expression {$args.add($ex2.ast);})*)*)? ')' {$ast=new Invocation($ID.getLine(), $ID.getCharPositionInLine()+1, new Variable($ID.getLine(), $ID.getCharPositionInLine()+1, $ID.text), $args);}
;

expression returns [Expression ast]:
'(' parentesis=expression ')' {$ast=$parentesis.ast;}
| ex=expression '[' ex0=expression ']' {$ast=new ArrayAccess($ex.ast.getLine(), $ex.ast.getColumn(), $ex.ast, $ex0.ast);}
| ex9=expression '.' ID {$ast=new FieldAccess($ex9.ast.getLine(), $ex9.ast.getColumn(), $ID.text, $ex9.ast);}
| '(' cast=expression 'as' type ')' {$ast=new Cast($cast.ast.getLine(), $cast.ast.getColumn(), $cast.ast, $type.ast);}
| '-' unary=expression {$ast=new UnaryMinus($unary.ast.getLine(), $unary.ast.getColumn(), $unary.ast);}
| '!' not=expression {$ast=new UnaryNot($not.ast.getLine(), $not.ast.getColumn(), $not.ast);}
| ex1=expression OP=('*'|'/'|'%') ex2=expression {$ast=new Arithmetic($ex1.ast.getLine(),$ex1.ast.getColumn(),$OP.text,$ex1.ast,$ex2.ast);}
| ex3=expression OP=('+'|'-') ex4=expression {$ast=new Arithmetic($ex3.ast.getLine(),$ex3.ast.getColumn(),$OP.text,$ex3.ast,$ex4.ast);}
| ex5=expression OP=('>'|'>='|'<'|'<='|'!='|'==') ex6=expression {$ast=new Comparison($ex5.ast.getLine(),$ex5.ast.getColumn(),$OP.text,$ex5.ast,$ex6.ast);}
| ex7=expression OP=('&&'|'||') ex8=expression {$ast=new Logic($ex7.ast.getLine(),$ex7.ast.getColumn(),$OP.text,$ex7.ast,$ex8.ast);}
| functionInvocation {$ast=$functionInvocation.ast;}
| INT_CONSTANT {$ast=new IntLiteral($INT_CONSTANT.getLine(), $INT_CONSTANT.getCharPositionInLine()+1, LexerHelper.lexemeToInt($INT_CONSTANT.text));}
| REAL_CONSTANT {$ast=new NumberLiteral($REAL_CONSTANT.getLine(), $REAL_CONSTANT.getCharPositionInLine()+1, LexerHelper.lexemeToReal($REAL_CONSTANT.text));}
| CHAR_CONSTANT {$ast=new CharLiteral($CHAR_CONSTANT.getLine(), $CHAR_CONSTANT.getCharPositionInLine()+1, LexerHelper.lexemeToChar($CHAR_CONSTANT.text));}
| ID {$ast=new Variable($ID.getLine(), $ID.getCharPositionInLine()+1, $ID.text);}
;

// LEXICAL PATTERNS
INT_CONSTANT: [1-9][0-9]*
            | '0'
            ;
ID: [a-zA-Z_][a-zA-Z0-9_]*
;
SINGLE_COMMENT: '//'.*?('\n'|EOF) -> skip
    ;
MULTI_COMMENT: '/*'.*?'*/' -> skip
    ;
CHAR_CONSTANT: '\''.'\''
            | '\'\\'INT_CONSTANT'\''
            | '\'\\n\''
            | '\'\\t\''
            ;
fragment
DIGIT: [0-9]
    ;
fragment
MANTISA: INT_CONSTANT*'.'DIGIT+
        | INT_CONSTANT+'.'DIGIT*
        ;
fragment
EXPONENTE: [eE][+-]?INT_CONSTANT
        ;
REAL_CONSTANT: MANTISA EXPONENTE?
            | INT_CONSTANT EXPONENTE
            ;
WHITE_SPACE: ' '+ -> skip
            ;
SALTO_LINEA:'\n'+ -> skip
            ;
TABULADOR:'\t'+ -> skip
            ;
RETORNO_CARRO:'\r'+ -> skip
;
