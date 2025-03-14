// Generated from C:/Users/David/OneDrive - Universidad de Oviedo/Escritorio/UNI/Tercer Curso/Segundo Semestre/Diseño de Lenguajes de Programación/dlp/src/parser/TSmm.g4 by ANTLR 4.13.2
package parser;

import ast.*;
import ast.definitions.*;
import ast.expressions.*;
import ast.statements.*;
import ast.types.*;
import java.util.*;
import parser.LexerHelper;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TSmmParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TSmmVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TSmmParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(TSmmParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link TSmmParser#varDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDefinition(TSmmParser.VarDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link TSmmParser#functionDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDefinition(TSmmParser.FunctionDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link TSmmParser#mainFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMainFunction(TSmmParser.MainFunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link TSmmParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(TSmmParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link TSmmParser#simpleType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleType(TSmmParser.SimpleTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link TSmmParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(TSmmParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link TSmmParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(TSmmParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link TSmmParser#functionInvocation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionInvocation(TSmmParser.FunctionInvocationContext ctx);
	/**
	 * Visit a parse tree produced by {@link TSmmParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(TSmmParser.ExpressionContext ctx);
}