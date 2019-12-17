// Generated from /home/buraindo/Documents/Programming/IdeaProjects/TM3/src/main/java/ego/Ego.g4 by ANTLR 4.7.2
package ego;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link EgoParser}.
 */
public interface EgoListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link EgoParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(EgoParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link EgoParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(EgoParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link EgoParser#blocks}.
	 * @param ctx the parse tree
	 */
	void enterBlocks(EgoParser.BlocksContext ctx);
	/**
	 * Exit a parse tree produced by {@link EgoParser#blocks}.
	 * @param ctx the parse tree
	 */
	void exitBlocks(EgoParser.BlocksContext ctx);
	/**
	 * Enter a parse tree produced by {@link EgoParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(EgoParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link EgoParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(EgoParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link EgoParser#line}.
	 * @param ctx the parse tree
	 */
	void enterLine(EgoParser.LineContext ctx);
	/**
	 * Exit a parse tree produced by {@link EgoParser#line}.
	 * @param ctx the parse tree
	 */
	void exitLine(EgoParser.LineContext ctx);
	/**
	 * Enter a parse tree produced by {@link EgoParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(EgoParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link EgoParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(EgoParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link EgoParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(EgoParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link EgoParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(EgoParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link EgoParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(EgoParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link EgoParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(EgoParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link EgoParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(EgoParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link EgoParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(EgoParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link EgoParser#rvalue}.
	 * @param ctx the parse tree
	 */
	void enterRvalue(EgoParser.RvalueContext ctx);
	/**
	 * Exit a parse tree produced by {@link EgoParser#rvalue}.
	 * @param ctx the parse tree
	 */
	void exitRvalue(EgoParser.RvalueContext ctx);
	/**
	 * Enter a parse tree produced by {@link EgoParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(EgoParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link EgoParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(EgoParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link EgoParser#numberLiteral}.
	 * @param ctx the parse tree
	 */
	void enterNumberLiteral(EgoParser.NumberLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link EgoParser#numberLiteral}.
	 * @param ctx the parse tree
	 */
	void exitNumberLiteral(EgoParser.NumberLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link EgoParser#doubleLiteral}.
	 * @param ctx the parse tree
	 */
	void enterDoubleLiteral(EgoParser.DoubleLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link EgoParser#doubleLiteral}.
	 * @param ctx the parse tree
	 */
	void exitDoubleLiteral(EgoParser.DoubleLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link EgoParser#charLiteral}.
	 * @param ctx the parse tree
	 */
	void enterCharLiteral(EgoParser.CharLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link EgoParser#charLiteral}.
	 * @param ctx the parse tree
	 */
	void exitCharLiteral(EgoParser.CharLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link EgoParser#stringLiteral}.
	 * @param ctx the parse tree
	 */
	void enterStringLiteral(EgoParser.StringLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link EgoParser#stringLiteral}.
	 * @param ctx the parse tree
	 */
	void exitStringLiteral(EgoParser.StringLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link EgoParser#operation}.
	 * @param ctx the parse tree
	 */
	void enterOperation(EgoParser.OperationContext ctx);
	/**
	 * Exit a parse tree produced by {@link EgoParser#operation}.
	 * @param ctx the parse tree
	 */
	void exitOperation(EgoParser.OperationContext ctx);
	/**
	 * Enter a parse tree produced by {@link EgoParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(EgoParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link EgoParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(EgoParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link EgoParser#call}.
	 * @param ctx the parse tree
	 */
	void enterCall(EgoParser.CallContext ctx);
	/**
	 * Exit a parse tree produced by {@link EgoParser#call}.
	 * @param ctx the parse tree
	 */
	void exitCall(EgoParser.CallContext ctx);
	/**
	 * Enter a parse tree produced by {@link EgoParser#fargs}.
	 * @param ctx the parse tree
	 */
	void enterFargs(EgoParser.FargsContext ctx);
	/**
	 * Exit a parse tree produced by {@link EgoParser#fargs}.
	 * @param ctx the parse tree
	 */
	void exitFargs(EgoParser.FargsContext ctx);
	/**
	 * Enter a parse tree produced by {@link EgoParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs(EgoParser.ArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link EgoParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs(EgoParser.ArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link EgoParser#arg}.
	 * @param ctx the parse tree
	 */
	void enterArg(EgoParser.ArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link EgoParser#arg}.
	 * @param ctx the parse tree
	 */
	void exitArg(EgoParser.ArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link EgoParser#farg}.
	 * @param ctx the parse tree
	 */
	void enterFarg(EgoParser.FargContext ctx);
	/**
	 * Exit a parse tree produced by {@link EgoParser#farg}.
	 * @param ctx the parse tree
	 */
	void exitFarg(EgoParser.FargContext ctx);
	/**
	 * Enter a parse tree produced by {@link EgoParser#multiargs}.
	 * @param ctx the parse tree
	 */
	void enterMultiargs(EgoParser.MultiargsContext ctx);
	/**
	 * Exit a parse tree produced by {@link EgoParser#multiargs}.
	 * @param ctx the parse tree
	 */
	void exitMultiargs(EgoParser.MultiargsContext ctx);
	/**
	 * Enter a parse tree produced by {@link EgoParser#multifargs}.
	 * @param ctx the parse tree
	 */
	void enterMultifargs(EgoParser.MultifargsContext ctx);
	/**
	 * Exit a parse tree produced by {@link EgoParser#multifargs}.
	 * @param ctx the parse tree
	 */
	void exitMultifargs(EgoParser.MultifargsContext ctx);
	/**
	 * Enter a parse tree produced by {@link EgoParser#multiarg}.
	 * @param ctx the parse tree
	 */
	void enterMultiarg(EgoParser.MultiargContext ctx);
	/**
	 * Exit a parse tree produced by {@link EgoParser#multiarg}.
	 * @param ctx the parse tree
	 */
	void exitMultiarg(EgoParser.MultiargContext ctx);
	/**
	 * Enter a parse tree produced by {@link EgoParser#multifarg}.
	 * @param ctx the parse tree
	 */
	void enterMultifarg(EgoParser.MultifargContext ctx);
	/**
	 * Exit a parse tree produced by {@link EgoParser#multifarg}.
	 * @param ctx the parse tree
	 */
	void exitMultifarg(EgoParser.MultifargContext ctx);
	/**
	 * Enter a parse tree produced by {@link EgoParser#functionName}.
	 * @param ctx the parse tree
	 */
	void enterFunctionName(EgoParser.FunctionNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link EgoParser#functionName}.
	 * @param ctx the parse tree
	 */
	void exitFunctionName(EgoParser.FunctionNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link EgoParser#flow}.
	 * @param ctx the parse tree
	 */
	void enterFlow(EgoParser.FlowContext ctx);
	/**
	 * Exit a parse tree produced by {@link EgoParser#flow}.
	 * @param ctx the parse tree
	 */
	void exitFlow(EgoParser.FlowContext ctx);
	/**
	 * Enter a parse tree produced by {@link EgoParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(EgoParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link EgoParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(EgoParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link EgoParser#whileLoop}.
	 * @param ctx the parse tree
	 */
	void enterWhileLoop(EgoParser.WhileLoopContext ctx);
	/**
	 * Exit a parse tree produced by {@link EgoParser#whileLoop}.
	 * @param ctx the parse tree
	 */
	void exitWhileLoop(EgoParser.WhileLoopContext ctx);
	/**
	 * Enter a parse tree produced by {@link EgoParser#forLoop}.
	 * @param ctx the parse tree
	 */
	void enterForLoop(EgoParser.ForLoopContext ctx);
	/**
	 * Exit a parse tree produced by {@link EgoParser#forLoop}.
	 * @param ctx the parse tree
	 */
	void exitForLoop(EgoParser.ForLoopContext ctx);
	/**
	 * Enter a parse tree produced by {@link EgoParser#scope}.
	 * @param ctx the parse tree
	 */
	void enterScope(EgoParser.ScopeContext ctx);
	/**
	 * Exit a parse tree produced by {@link EgoParser#scope}.
	 * @param ctx the parse tree
	 */
	void exitScope(EgoParser.ScopeContext ctx);
	/**
	 * Enter a parse tree produced by {@link EgoParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(EgoParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link EgoParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(EgoParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link EgoParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterPredicate(EgoParser.PredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link EgoParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitPredicate(EgoParser.PredicateContext ctx);
}