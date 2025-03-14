// Generated from C:/Users/David/OneDrive - Universidad de Oviedo/Escritorio/UNI/Tercer Curso/Segundo Semestre/Diseño de Lenguajes de Programación/dlp/src/parser/TSmm.g4 by ANTLR 4.13.2
package parser;

import ast.*;
import ast.definitions.*;
import ast.expressions.*;
import ast.statements.*;
import ast.types.*;
import java.util.*;
import parser.LexerHelper;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TSmmParser}.
 */
public interface TSmmListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TSmmParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(TSmmParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link TSmmParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(TSmmParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link TSmmParser#varDefinition}.
	 * @param ctx the parse tree
	 */
	void enterVarDefinition(TSmmParser.VarDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TSmmParser#varDefinition}.
	 * @param ctx the parse tree
	 */
	void exitVarDefinition(TSmmParser.VarDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TSmmParser#functionDefinition}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDefinition(TSmmParser.FunctionDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TSmmParser#functionDefinition}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDefinition(TSmmParser.FunctionDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TSmmParser#mainFunction}.
	 * @param ctx the parse tree
	 */
	void enterMainFunction(TSmmParser.MainFunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TSmmParser#mainFunction}.
	 * @param ctx the parse tree
	 */
	void exitMainFunction(TSmmParser.MainFunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TSmmParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(TSmmParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TSmmParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(TSmmParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TSmmParser#simpleType}.
	 * @param ctx the parse tree
	 */
	void enterSimpleType(TSmmParser.SimpleTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TSmmParser#simpleType}.
	 * @param ctx the parse tree
	 */
	void exitSimpleType(TSmmParser.SimpleTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TSmmParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(TSmmParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link TSmmParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(TSmmParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link TSmmParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(TSmmParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link TSmmParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(TSmmParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link TSmmParser#functionInvocation}.
	 * @param ctx the parse tree
	 */
	void enterFunctionInvocation(TSmmParser.FunctionInvocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link TSmmParser#functionInvocation}.
	 * @param ctx the parse tree
	 */
	void exitFunctionInvocation(TSmmParser.FunctionInvocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link TSmmParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(TSmmParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TSmmParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(TSmmParser.ExpressionContext ctx);
}