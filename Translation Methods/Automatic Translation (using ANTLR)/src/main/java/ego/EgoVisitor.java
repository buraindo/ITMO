// Generated from /home/buraindo/Documents/Programming/IdeaProjects/TM3/src/main/java/ego/Ego.g4 by ANTLR 4.7.2
package ego;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link EgoParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface EgoVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link EgoParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(EgoParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link EgoParser#blocks}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlocks(EgoParser.BlocksContext ctx);
	/**
	 * Visit a parse tree produced by {@link EgoParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(EgoParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link EgoParser#line}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLine(EgoParser.LineContext ctx);
	/**
	 * Visit a parse tree produced by {@link EgoParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(EgoParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link EgoParser#returnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStatement(EgoParser.ReturnStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link EgoParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(EgoParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link EgoParser#name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName(EgoParser.NameContext ctx);
	/**
	 * Visit a parse tree produced by {@link EgoParser#rvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRvalue(EgoParser.RvalueContext ctx);
	/**
	 * Visit a parse tree produced by {@link EgoParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(EgoParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link EgoParser#numberLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberLiteral(EgoParser.NumberLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link EgoParser#doubleLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoubleLiteral(EgoParser.DoubleLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link EgoParser#charLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharLiteral(EgoParser.CharLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link EgoParser#stringLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLiteral(EgoParser.StringLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link EgoParser#operation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperation(EgoParser.OperationContext ctx);
	/**
	 * Visit a parse tree produced by {@link EgoParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(EgoParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link EgoParser#call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCall(EgoParser.CallContext ctx);
	/**
	 * Visit a parse tree produced by {@link EgoParser#fargs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFargs(EgoParser.FargsContext ctx);
	/**
	 * Visit a parse tree produced by {@link EgoParser#args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgs(EgoParser.ArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link EgoParser#arg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArg(EgoParser.ArgContext ctx);
	/**
	 * Visit a parse tree produced by {@link EgoParser#farg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFarg(EgoParser.FargContext ctx);
	/**
	 * Visit a parse tree produced by {@link EgoParser#multiargs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiargs(EgoParser.MultiargsContext ctx);
	/**
	 * Visit a parse tree produced by {@link EgoParser#multifargs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultifargs(EgoParser.MultifargsContext ctx);
	/**
	 * Visit a parse tree produced by {@link EgoParser#multiarg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiarg(EgoParser.MultiargContext ctx);
	/**
	 * Visit a parse tree produced by {@link EgoParser#multifarg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultifarg(EgoParser.MultifargContext ctx);
	/**
	 * Visit a parse tree produced by {@link EgoParser#functionName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionName(EgoParser.FunctionNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link EgoParser#flow}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFlow(EgoParser.FlowContext ctx);
	/**
	 * Visit a parse tree produced by {@link EgoParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(EgoParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link EgoParser#whileLoop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileLoop(EgoParser.WhileLoopContext ctx);
	/**
	 * Visit a parse tree produced by {@link EgoParser#forLoop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForLoop(EgoParser.ForLoopContext ctx);
	/**
	 * Visit a parse tree produced by {@link EgoParser#scope}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScope(EgoParser.ScopeContext ctx);
	/**
	 * Visit a parse tree produced by {@link EgoParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(EgoParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link EgoParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate(EgoParser.PredicateContext ctx);
}