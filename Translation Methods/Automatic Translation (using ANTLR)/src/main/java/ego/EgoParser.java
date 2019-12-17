// Generated from /home/buraindo/Documents/Programming/IdeaProjects/TM3/src/main/java/ego/Ego.g4 by ANTLR 4.7.2
package ego;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class EgoParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		NUMBER=1, STRING=2, CHAR=3, DOUBLE=4, COMMA=5, OPEN=6, CLOSE=7, BLOCK_OPEN=8, 
		BLOCK_CLOSE=9, SEMICOLON=10, DOT=11, NEWLINE=12, FUNCTION=13, RETURNS=14, 
		RETURN=15, OF=16, MULTIPLY=17, PLUS=18, MINUS=19, DIVIDE=20, MODULO=21, 
		AND=22, OR=23, XOR=24, NOT=25, LESS=26, MORE_=27, EQUALS=28, NOT_EQUALS=29, 
		ASSIGN=30, AS=31, IF=32, WHILE=33, FOR=34, IN=35, T_INT=36, T_LONG=37, 
		T_CHAR=38, T_STRING=39, T_DOUBLE=40, T_NOTHING=41, NEXT_INT=42, NEXT_LONG=43, 
		NEXT_CHAR=44, NEXT_DOUBLE=45, NEXT_LINE=46, PRINT_INT=47, PRINT_LONG=48, 
		PRINT_CHAR=49, PRINT_DOUBLE=50, PRINT_STRING=51, IDENTIFIER=52, WHITESPACE=53;
	public static final int
		RULE_program = 0, RULE_blocks = 1, RULE_block = 2, RULE_line = 3, RULE_assignment = 4, 
		RULE_returnStatement = 5, RULE_type = 6, RULE_name = 7, RULE_rvalue = 8, 
		RULE_literal = 9, RULE_numberLiteral = 10, RULE_doubleLiteral = 11, RULE_charLiteral = 12, 
		RULE_stringLiteral = 13, RULE_operation = 14, RULE_function = 15, RULE_call = 16, 
		RULE_fargs = 17, RULE_args = 18, RULE_arg = 19, RULE_farg = 20, RULE_multiargs = 21, 
		RULE_multifargs = 22, RULE_multiarg = 23, RULE_multifarg = 24, RULE_functionName = 25, 
		RULE_flow = 26, RULE_ifStatement = 27, RULE_whileLoop = 28, RULE_forLoop = 29, 
		RULE_scope = 30, RULE_statement = 31, RULE_predicate = 32;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "blocks", "block", "line", "assignment", "returnStatement", 
			"type", "name", "rvalue", "literal", "numberLiteral", "doubleLiteral", 
			"charLiteral", "stringLiteral", "operation", "function", "call", "fargs", 
			"args", "arg", "farg", "multiargs", "multifargs", "multiarg", "multifarg", 
			"functionName", "flow", "ifStatement", "whileLoop", "forLoop", "scope", 
			"statement", "predicate"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, "','", "'('", "')'", "'{'", "'}'", "';'", 
			"'.'", "'\n'", "'function'", "'returns'", "'return'", "'of'", "'*'", 
			"'+'", "'-'", "'/'", "'%'", null, null, "'^'", "'!'", "'<'", "'>'", null, 
			null, null, "'as'", "'if'", "'while'", "'for'", "'in'", "'Int'", "'Long'", 
			"'Char'", "'String'", "'Double'", "'Nothing'", "'nextInt'", "'nextLong'", 
			"'nextChar'", "'nextDouble'", "'nextLine'", "'printInt'", "'printLong'", 
			"'printChar'", "'printDouble'", "'printString'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "NUMBER", "STRING", "CHAR", "DOUBLE", "COMMA", "OPEN", "CLOSE", 
			"BLOCK_OPEN", "BLOCK_CLOSE", "SEMICOLON", "DOT", "NEWLINE", "FUNCTION", 
			"RETURNS", "RETURN", "OF", "MULTIPLY", "PLUS", "MINUS", "DIVIDE", "MODULO", 
			"AND", "OR", "XOR", "NOT", "LESS", "MORE_", "EQUALS", "NOT_EQUALS", "ASSIGN", 
			"AS", "IF", "WHILE", "FOR", "IN", "T_INT", "T_LONG", "T_CHAR", "T_STRING", 
			"T_DOUBLE", "T_NOTHING", "NEXT_INT", "NEXT_LONG", "NEXT_CHAR", "NEXT_DOUBLE", 
			"NEXT_LINE", "PRINT_INT", "PRINT_LONG", "PRINT_CHAR", "PRINT_DOUBLE", 
			"PRINT_STRING", "IDENTIFIER", "WHITESPACE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Ego.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public EgoParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public BlocksContext blocks() {
			return getRuleContext(BlocksContext.class,0);
		}
		public TerminalNode EOF() { return getToken(EgoParser.EOF, 0); }
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EgoVisitor ) return ((EgoVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			blocks();
			setState(67);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlocksContext extends ParserRuleContext {
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public BlocksContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blocks; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).enterBlocks(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).exitBlocks(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EgoVisitor ) return ((EgoVisitor<? extends T>)visitor).visitBlocks(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlocksContext blocks() throws RecognitionException {
		BlocksContext _localctx = new BlocksContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_blocks);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(69);
				block();
				}
				}
				setState(72); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEWLINE) | (1L << FUNCTION) | (1L << RETURN) | (1L << IF) | (1L << WHILE) | (1L << FOR) | (1L << NEXT_INT) | (1L << NEXT_LONG) | (1L << NEXT_CHAR) | (1L << NEXT_DOUBLE) | (1L << NEXT_LINE) | (1L << PRINT_INT) | (1L << PRINT_LONG) | (1L << PRINT_CHAR) | (1L << PRINT_DOUBLE) | (1L << PRINT_STRING) | (1L << IDENTIFIER))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
		}
		public LineContext line() {
			return getRuleContext(LineContext.class,0);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EgoVisitor ) return ((EgoVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_block);
		try {
			setState(76);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FUNCTION:
				enterOuterAlt(_localctx, 1);
				{
				setState(74);
				function();
				}
				break;
			case NEWLINE:
			case RETURN:
			case IF:
			case WHILE:
			case FOR:
			case NEXT_INT:
			case NEXT_LONG:
			case NEXT_CHAR:
			case NEXT_DOUBLE:
			case NEXT_LINE:
			case PRINT_INT:
			case PRINT_LONG:
			case PRINT_CHAR:
			case PRINT_DOUBLE:
			case PRINT_STRING:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(75);
				line();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LineContext extends ParserRuleContext {
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(EgoParser.NEWLINE, 0); }
		public FlowContext flow() {
			return getRuleContext(FlowContext.class,0);
		}
		public CallContext call() {
			return getRuleContext(CallContext.class,0);
		}
		public ReturnStatementContext returnStatement() {
			return getRuleContext(ReturnStatementContext.class,0);
		}
		public LineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_line; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).enterLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).exitLine(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EgoVisitor ) return ((EgoVisitor<? extends T>)visitor).visitLine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LineContext line() throws RecognitionException {
		LineContext _localctx = new LineContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_line);
		try {
			setState(89);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(78);
				assignment();
				setState(79);
				match(NEWLINE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(81);
				flow();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(82);
				call();
				setState(83);
				match(NEWLINE);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(85);
				returnStatement();
				setState(86);
				match(NEWLINE);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(88);
				match(NEWLINE);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(EgoParser.ASSIGN, 0); }
		public RvalueContext rvalue() {
			return getRuleContext(RvalueContext.class,0);
		}
		public List<TerminalNode> AS() { return getTokens(EgoParser.AS); }
		public TerminalNode AS(int i) {
			return getToken(EgoParser.AS, i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).exitAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EgoVisitor ) return ((EgoVisitor<? extends T>)visitor).visitAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_assignment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			name();
			setState(92);
			match(ASSIGN);
			setState(93);
			rvalue();
			setState(98);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AS) {
				{
				{
				setState(94);
				match(AS);
				setState(95);
				type();
				}
				}
				setState(100);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnStatementContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(EgoParser.RETURN, 0); }
		public RvalueContext rvalue() {
			return getRuleContext(RvalueContext.class,0);
		}
		public List<TerminalNode> AS() { return getTokens(EgoParser.AS); }
		public TerminalNode AS(int i) {
			return getToken(EgoParser.AS, i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public ReturnStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).enterReturnStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).exitReturnStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EgoVisitor ) return ((EgoVisitor<? extends T>)visitor).visitReturnStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnStatementContext returnStatement() throws RecognitionException {
		ReturnStatementContext _localctx = new ReturnStatementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_returnStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101);
			match(RETURN);
			setState(102);
			rvalue();
			setState(107);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AS) {
				{
				{
				setState(103);
				match(AS);
				setState(104);
				type();
				}
				}
				setState(109);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode T_INT() { return getToken(EgoParser.T_INT, 0); }
		public TerminalNode T_LONG() { return getToken(EgoParser.T_LONG, 0); }
		public TerminalNode T_CHAR() { return getToken(EgoParser.T_CHAR, 0); }
		public TerminalNode T_DOUBLE() { return getToken(EgoParser.T_DOUBLE, 0); }
		public TerminalNode T_STRING() { return getToken(EgoParser.T_STRING, 0); }
		public TerminalNode T_NOTHING() { return getToken(EgoParser.T_NOTHING, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EgoVisitor ) return ((EgoVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T_INT) | (1L << T_LONG) | (1L << T_CHAR) | (1L << T_STRING) | (1L << T_DOUBLE) | (1L << T_NOTHING))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(EgoParser.IDENTIFIER, 0); }
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).enterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).exitName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EgoVisitor ) return ((EgoVisitor<? extends T>)visitor).visitName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RvalueContext extends ParserRuleContext {
		public List<NameContext> name() {
			return getRuleContexts(NameContext.class);
		}
		public NameContext name(int i) {
			return getRuleContext(NameContext.class,i);
		}
		public List<CallContext> call() {
			return getRuleContexts(CallContext.class);
		}
		public CallContext call(int i) {
			return getRuleContext(CallContext.class,i);
		}
		public List<LiteralContext> literal() {
			return getRuleContexts(LiteralContext.class);
		}
		public LiteralContext literal(int i) {
			return getRuleContext(LiteralContext.class,i);
		}
		public List<OperationContext> operation() {
			return getRuleContexts(OperationContext.class);
		}
		public OperationContext operation(int i) {
			return getRuleContext(OperationContext.class,i);
		}
		public RvalueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rvalue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).enterRvalue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).exitRvalue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EgoVisitor ) return ((EgoVisitor<? extends T>)visitor).visitRvalue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RvalueContext rvalue() throws RecognitionException {
		RvalueContext _localctx = new RvalueContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_rvalue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(114);
				name();
				}
				break;
			case 2:
				{
				setState(115);
				call();
				}
				break;
			case 3:
				{
				setState(116);
				literal();
				}
				break;
			}
			setState(127);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MULTIPLY) | (1L << PLUS) | (1L << MINUS) | (1L << DIVIDE) | (1L << MODULO) | (1L << AND) | (1L << OR) | (1L << XOR))) != 0)) {
				{
				{
				setState(119);
				operation();
				setState(123);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
				case 1:
					{
					setState(120);
					name();
					}
					break;
				case 2:
					{
					setState(121);
					call();
					}
					break;
				case 3:
					{
					setState(122);
					literal();
					}
					break;
				}
				}
				}
				setState(129);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public NumberLiteralContext numberLiteral() {
			return getRuleContext(NumberLiteralContext.class,0);
		}
		public DoubleLiteralContext doubleLiteral() {
			return getRuleContext(DoubleLiteralContext.class,0);
		}
		public CharLiteralContext charLiteral() {
			return getRuleContext(CharLiteralContext.class,0);
		}
		public StringLiteralContext stringLiteral() {
			return getRuleContext(StringLiteralContext.class,0);
		}
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EgoVisitor ) return ((EgoVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_literal);
		try {
			setState(134);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER:
				enterOuterAlt(_localctx, 1);
				{
				setState(130);
				numberLiteral();
				}
				break;
			case DOUBLE:
				enterOuterAlt(_localctx, 2);
				{
				setState(131);
				doubleLiteral();
				}
				break;
			case CHAR:
				enterOuterAlt(_localctx, 3);
				{
				setState(132);
				charLiteral();
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 4);
				{
				setState(133);
				stringLiteral();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumberLiteralContext extends ParserRuleContext {
		public List<TerminalNode> NUMBER() { return getTokens(EgoParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(EgoParser.NUMBER, i);
		}
		public NumberLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numberLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).enterNumberLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).exitNumberLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EgoVisitor ) return ((EgoVisitor<? extends T>)visitor).visitNumberLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberLiteralContext numberLiteral() throws RecognitionException {
		NumberLiteralContext _localctx = new NumberLiteralContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_numberLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			match(NUMBER);
			setState(140);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NUMBER) {
				{
				{
				setState(137);
				match(NUMBER);
				}
				}
				setState(142);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DoubleLiteralContext extends ParserRuleContext {
		public List<TerminalNode> DOUBLE() { return getTokens(EgoParser.DOUBLE); }
		public TerminalNode DOUBLE(int i) {
			return getToken(EgoParser.DOUBLE, i);
		}
		public List<OperationContext> operation() {
			return getRuleContexts(OperationContext.class);
		}
		public OperationContext operation(int i) {
			return getRuleContext(OperationContext.class,i);
		}
		public DoubleLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doubleLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).enterDoubleLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).exitDoubleLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EgoVisitor ) return ((EgoVisitor<? extends T>)visitor).visitDoubleLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DoubleLiteralContext doubleLiteral() throws RecognitionException {
		DoubleLiteralContext _localctx = new DoubleLiteralContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_doubleLiteral);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			match(DOUBLE);
			setState(149);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(144);
					operation();
					setState(145);
					match(DOUBLE);
					}
					} 
				}
				setState(151);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CharLiteralContext extends ParserRuleContext {
		public List<TerminalNode> CHAR() { return getTokens(EgoParser.CHAR); }
		public TerminalNode CHAR(int i) {
			return getToken(EgoParser.CHAR, i);
		}
		public List<OperationContext> operation() {
			return getRuleContexts(OperationContext.class);
		}
		public OperationContext operation(int i) {
			return getRuleContext(OperationContext.class,i);
		}
		public CharLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_charLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).enterCharLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).exitCharLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EgoVisitor ) return ((EgoVisitor<? extends T>)visitor).visitCharLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CharLiteralContext charLiteral() throws RecognitionException {
		CharLiteralContext _localctx = new CharLiteralContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_charLiteral);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			match(CHAR);
			setState(158);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(153);
					operation();
					setState(154);
					match(CHAR);
					}
					} 
				}
				setState(160);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StringLiteralContext extends ParserRuleContext {
		public List<TerminalNode> STRING() { return getTokens(EgoParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(EgoParser.STRING, i);
		}
		public List<TerminalNode> PLUS() { return getTokens(EgoParser.PLUS); }
		public TerminalNode PLUS(int i) {
			return getToken(EgoParser.PLUS, i);
		}
		public StringLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).enterStringLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).exitStringLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EgoVisitor ) return ((EgoVisitor<? extends T>)visitor).visitStringLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringLiteralContext stringLiteral() throws RecognitionException {
		StringLiteralContext _localctx = new StringLiteralContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_stringLiteral);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			match(STRING);
			setState(166);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(162);
					match(PLUS);
					setState(163);
					match(STRING);
					}
					} 
				}
				setState(168);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperationContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(EgoParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(EgoParser.MINUS, 0); }
		public TerminalNode MULTIPLY() { return getToken(EgoParser.MULTIPLY, 0); }
		public TerminalNode DIVIDE() { return getToken(EgoParser.DIVIDE, 0); }
		public TerminalNode MODULO() { return getToken(EgoParser.MODULO, 0); }
		public TerminalNode AND() { return getToken(EgoParser.AND, 0); }
		public TerminalNode XOR() { return getToken(EgoParser.XOR, 0); }
		public TerminalNode OR() { return getToken(EgoParser.OR, 0); }
		public OperationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).enterOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).exitOperation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EgoVisitor ) return ((EgoVisitor<? extends T>)visitor).visitOperation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationContext operation() throws RecognitionException {
		OperationContext _localctx = new OperationContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_operation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MULTIPLY) | (1L << PLUS) | (1L << MINUS) | (1L << DIVIDE) | (1L << MODULO) | (1L << AND) | (1L << OR) | (1L << XOR))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionContext extends ParserRuleContext {
		public TerminalNode FUNCTION() { return getToken(EgoParser.FUNCTION, 0); }
		public FunctionNameContext functionName() {
			return getRuleContext(FunctionNameContext.class,0);
		}
		public TerminalNode OPEN() { return getToken(EgoParser.OPEN, 0); }
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public TerminalNode CLOSE() { return getToken(EgoParser.CLOSE, 0); }
		public TerminalNode RETURNS() { return getToken(EgoParser.RETURNS, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ScopeContext scope() {
			return getRuleContext(ScopeContext.class,0);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).enterFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).exitFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EgoVisitor ) return ((EgoVisitor<? extends T>)visitor).visitFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_function);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
			match(FUNCTION);
			setState(172);
			functionName();
			setState(173);
			match(OPEN);
			setState(174);
			args();
			setState(175);
			match(CLOSE);
			setState(176);
			match(RETURNS);
			setState(177);
			type();
			setState(178);
			scope();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CallContext extends ParserRuleContext {
		public FunctionNameContext functionName() {
			return getRuleContext(FunctionNameContext.class,0);
		}
		public TerminalNode OPEN() { return getToken(EgoParser.OPEN, 0); }
		public FargsContext fargs() {
			return getRuleContext(FargsContext.class,0);
		}
		public TerminalNode CLOSE() { return getToken(EgoParser.CLOSE, 0); }
		public CallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_call; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).enterCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).exitCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EgoVisitor ) return ((EgoVisitor<? extends T>)visitor).visitCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CallContext call() throws RecognitionException {
		CallContext _localctx = new CallContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_call);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			functionName();
			setState(181);
			match(OPEN);
			setState(182);
			fargs();
			setState(183);
			match(CLOSE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FargsContext extends ParserRuleContext {
		public FargContext farg() {
			return getRuleContext(FargContext.class,0);
		}
		public MultifargsContext multifargs() {
			return getRuleContext(MultifargsContext.class,0);
		}
		public FargsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fargs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).enterFargs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).exitFargs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EgoVisitor ) return ((EgoVisitor<? extends T>)visitor).visitFargs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FargsContext fargs() throws RecognitionException {
		FargsContext _localctx = new FargsContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_fargs);
		try {
			setState(188);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(186);
				farg();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(187);
				multifargs();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgsContext extends ParserRuleContext {
		public ArgContext arg() {
			return getRuleContext(ArgContext.class,0);
		}
		public MultiargsContext multiargs() {
			return getRuleContext(MultiargsContext.class,0);
		}
		public ArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).enterArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).exitArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EgoVisitor ) return ((EgoVisitor<? extends T>)visitor).visitArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_args);
		try {
			setState(193);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(191);
				arg();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(192);
				multiargs();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode OF() { return getToken(EgoParser.OF, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).enterArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).exitArg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EgoVisitor ) return ((EgoVisitor<? extends T>)visitor).visitArg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgContext arg() throws RecognitionException {
		ArgContext _localctx = new ArgContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_arg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(195);
			name();
			setState(196);
			match(OF);
			setState(197);
			type();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FargContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public RvalueContext rvalue() {
			return getRuleContext(RvalueContext.class,0);
		}
		public FargContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_farg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).enterFarg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).exitFarg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EgoVisitor ) return ((EgoVisitor<? extends T>)visitor).visitFarg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FargContext farg() throws RecognitionException {
		FargContext _localctx = new FargContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_farg);
		try {
			setState(201);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(199);
				name();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(200);
				rvalue();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultiargsContext extends ParserRuleContext {
		public ArgContext arg() {
			return getRuleContext(ArgContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(EgoParser.COMMA, 0); }
		public MultiargContext multiarg() {
			return getRuleContext(MultiargContext.class,0);
		}
		public MultiargsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiargs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).enterMultiargs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).exitMultiargs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EgoVisitor ) return ((EgoVisitor<? extends T>)visitor).visitMultiargs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiargsContext multiargs() throws RecognitionException {
		MultiargsContext _localctx = new MultiargsContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_multiargs);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(203);
			arg();
			setState(204);
			match(COMMA);
			setState(205);
			multiarg();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultifargsContext extends ParserRuleContext {
		public FargContext farg() {
			return getRuleContext(FargContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(EgoParser.COMMA, 0); }
		public MultifargContext multifarg() {
			return getRuleContext(MultifargContext.class,0);
		}
		public MultifargsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multifargs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).enterMultifargs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).exitMultifargs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EgoVisitor ) return ((EgoVisitor<? extends T>)visitor).visitMultifargs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultifargsContext multifargs() throws RecognitionException {
		MultifargsContext _localctx = new MultifargsContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_multifargs);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(207);
			farg();
			setState(208);
			match(COMMA);
			setState(209);
			multifarg();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultiargContext extends ParserRuleContext {
		public ArgContext arg() {
			return getRuleContext(ArgContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(EgoParser.COMMA, 0); }
		public MultiargContext multiarg() {
			return getRuleContext(MultiargContext.class,0);
		}
		public MultiargContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiarg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).enterMultiarg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).exitMultiarg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EgoVisitor ) return ((EgoVisitor<? extends T>)visitor).visitMultiarg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiargContext multiarg() throws RecognitionException {
		MultiargContext _localctx = new MultiargContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_multiarg);
		try {
			setState(216);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(211);
				arg();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(212);
				arg();
				setState(213);
				match(COMMA);
				setState(214);
				multiarg();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultifargContext extends ParserRuleContext {
		public FargContext farg() {
			return getRuleContext(FargContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(EgoParser.COMMA, 0); }
		public MultifargContext multifarg() {
			return getRuleContext(MultifargContext.class,0);
		}
		public MultifargContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multifarg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).enterMultifarg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).exitMultifarg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EgoVisitor ) return ((EgoVisitor<? extends T>)visitor).visitMultifarg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultifargContext multifarg() throws RecognitionException {
		MultifargContext _localctx = new MultifargContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_multifarg);
		try {
			setState(223);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(218);
				farg();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(219);
				farg();
				setState(220);
				match(COMMA);
				setState(221);
				multifarg();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionNameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(EgoParser.IDENTIFIER, 0); }
		public TerminalNode NEXT_INT() { return getToken(EgoParser.NEXT_INT, 0); }
		public TerminalNode NEXT_CHAR() { return getToken(EgoParser.NEXT_CHAR, 0); }
		public TerminalNode NEXT_LONG() { return getToken(EgoParser.NEXT_LONG, 0); }
		public TerminalNode NEXT_DOUBLE() { return getToken(EgoParser.NEXT_DOUBLE, 0); }
		public TerminalNode NEXT_LINE() { return getToken(EgoParser.NEXT_LINE, 0); }
		public TerminalNode PRINT_INT() { return getToken(EgoParser.PRINT_INT, 0); }
		public TerminalNode PRINT_CHAR() { return getToken(EgoParser.PRINT_CHAR, 0); }
		public TerminalNode PRINT_LONG() { return getToken(EgoParser.PRINT_LONG, 0); }
		public TerminalNode PRINT_DOUBLE() { return getToken(EgoParser.PRINT_DOUBLE, 0); }
		public TerminalNode PRINT_STRING() { return getToken(EgoParser.PRINT_STRING, 0); }
		public FunctionNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).enterFunctionName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).exitFunctionName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EgoVisitor ) return ((EgoVisitor<? extends T>)visitor).visitFunctionName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionNameContext functionName() throws RecognitionException {
		FunctionNameContext _localctx = new FunctionNameContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_functionName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(225);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEXT_INT) | (1L << NEXT_LONG) | (1L << NEXT_CHAR) | (1L << NEXT_DOUBLE) | (1L << NEXT_LINE) | (1L << PRINT_INT) | (1L << PRINT_LONG) | (1L << PRINT_CHAR) | (1L << PRINT_DOUBLE) | (1L << PRINT_STRING) | (1L << IDENTIFIER))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FlowContext extends ParserRuleContext {
		public ForLoopContext forLoop() {
			return getRuleContext(ForLoopContext.class,0);
		}
		public WhileLoopContext whileLoop() {
			return getRuleContext(WhileLoopContext.class,0);
		}
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public FlowContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_flow; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).enterFlow(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).exitFlow(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EgoVisitor ) return ((EgoVisitor<? extends T>)visitor).visitFlow(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FlowContext flow() throws RecognitionException {
		FlowContext _localctx = new FlowContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_flow);
		try {
			setState(230);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FOR:
				enterOuterAlt(_localctx, 1);
				{
				setState(227);
				forLoop();
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 2);
				{
				setState(228);
				whileLoop();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 3);
				{
				setState(229);
				ifStatement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfStatementContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(EgoParser.IF, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ScopeContext scope() {
			return getRuleContext(ScopeContext.class,0);
		}
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).exitIfStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EgoVisitor ) return ((EgoVisitor<? extends T>)visitor).visitIfStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_ifStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(232);
			match(IF);
			setState(233);
			statement();
			setState(234);
			scope();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhileLoopContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(EgoParser.WHILE, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ScopeContext scope() {
			return getRuleContext(ScopeContext.class,0);
		}
		public WhileLoopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileLoop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).enterWhileLoop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).exitWhileLoop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EgoVisitor ) return ((EgoVisitor<? extends T>)visitor).visitWhileLoop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhileLoopContext whileLoop() throws RecognitionException {
		WhileLoopContext _localctx = new WhileLoopContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_whileLoop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(236);
			match(WHILE);
			setState(237);
			statement();
			setState(238);
			scope();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForLoopContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(EgoParser.FOR, 0); }
		public List<NameContext> name() {
			return getRuleContexts(NameContext.class);
		}
		public NameContext name(int i) {
			return getRuleContext(NameContext.class,i);
		}
		public TerminalNode IN() { return getToken(EgoParser.IN, 0); }
		public List<TerminalNode> DOT() { return getTokens(EgoParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(EgoParser.DOT, i);
		}
		public ScopeContext scope() {
			return getRuleContext(ScopeContext.class,0);
		}
		public List<TerminalNode> NUMBER() { return getTokens(EgoParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(EgoParser.NUMBER, i);
		}
		public ForLoopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forLoop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).enterForLoop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).exitForLoop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EgoVisitor ) return ((EgoVisitor<? extends T>)visitor).visitForLoop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForLoopContext forLoop() throws RecognitionException {
		ForLoopContext _localctx = new ForLoopContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_forLoop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(240);
			match(FOR);
			setState(241);
			name();
			setState(242);
			match(IN);
			setState(245);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				{
				setState(243);
				name();
				}
				break;
			case NUMBER:
				{
				setState(244);
				match(NUMBER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(247);
			match(DOT);
			setState(248);
			match(DOT);
			setState(251);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				{
				setState(249);
				name();
				}
				break;
			case NUMBER:
				{
				setState(250);
				match(NUMBER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(253);
			scope();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ScopeContext extends ParserRuleContext {
		public TerminalNode BLOCK_OPEN() { return getToken(EgoParser.BLOCK_OPEN, 0); }
		public TerminalNode BLOCK_CLOSE() { return getToken(EgoParser.BLOCK_CLOSE, 0); }
		public List<LineContext> line() {
			return getRuleContexts(LineContext.class);
		}
		public LineContext line(int i) {
			return getRuleContext(LineContext.class,i);
		}
		public ScopeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scope; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).enterScope(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).exitScope(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EgoVisitor ) return ((EgoVisitor<? extends T>)visitor).visitScope(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScopeContext scope() throws RecognitionException {
		ScopeContext _localctx = new ScopeContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_scope);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(255);
			match(BLOCK_OPEN);
			setState(259);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEWLINE) | (1L << RETURN) | (1L << IF) | (1L << WHILE) | (1L << FOR) | (1L << NEXT_INT) | (1L << NEXT_LONG) | (1L << NEXT_CHAR) | (1L << NEXT_DOUBLE) | (1L << NEXT_LINE) | (1L << PRINT_INT) | (1L << PRINT_LONG) | (1L << PRINT_CHAR) | (1L << PRINT_DOUBLE) | (1L << PRINT_STRING) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(256);
				line();
				}
				}
				setState(261);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(262);
			match(BLOCK_CLOSE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(EgoParser.NOT, 0); }
		public TerminalNode OPEN() { return getToken(EgoParser.OPEN, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode CLOSE() { return getToken(EgoParser.CLOSE, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public CallContext call() {
			return getRuleContext(CallContext.class,0);
		}
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EgoVisitor ) return ((EgoVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_statement);
		try {
			setState(272);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(264);
				match(NOT);
				setState(265);
				match(OPEN);
				setState(266);
				statement();
				setState(267);
				match(CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(269);
				name();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(270);
				call();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(271);
				predicate();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PredicateContext extends ParserRuleContext {
		public TerminalNode LESS() { return getToken(EgoParser.LESS, 0); }
		public TerminalNode MORE_() { return getToken(EgoParser.MORE_, 0); }
		public TerminalNode EQUALS() { return getToken(EgoParser.EQUALS, 0); }
		public TerminalNode NOT_EQUALS() { return getToken(EgoParser.NOT_EQUALS, 0); }
		public List<NameContext> name() {
			return getRuleContexts(NameContext.class);
		}
		public NameContext name(int i) {
			return getRuleContext(NameContext.class,i);
		}
		public List<CallContext> call() {
			return getRuleContexts(CallContext.class);
		}
		public CallContext call(int i) {
			return getRuleContext(CallContext.class,i);
		}
		public List<LiteralContext> literal() {
			return getRuleContexts(LiteralContext.class);
		}
		public LiteralContext literal(int i) {
			return getRuleContext(LiteralContext.class,i);
		}
		public PredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).enterPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EgoListener ) ((EgoListener)listener).exitPredicate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof EgoVisitor ) return ((EgoVisitor<? extends T>)visitor).visitPredicate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PredicateContext predicate() throws RecognitionException {
		PredicateContext _localctx = new PredicateContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_predicate);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				{
				setState(274);
				name();
				}
				break;
			case 2:
				{
				setState(275);
				call();
				}
				break;
			case 3:
				{
				setState(276);
				literal();
				}
				break;
			}
			setState(279);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LESS) | (1L << MORE_) | (1L << EQUALS) | (1L << NOT_EQUALS))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(283);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				{
				setState(280);
				name();
				}
				break;
			case 2:
				{
				setState(281);
				call();
				}
				break;
			case 3:
				{
				setState(282);
				literal();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\67\u0120\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\3\2\3\2\3\2\3\3\6\3I\n\3\r\3\16\3J\3\4\3\4\5\4O\n\4\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\\\n\5\3\6\3\6\3\6\3\6\3\6\7"+
		"\6c\n\6\f\6\16\6f\13\6\3\7\3\7\3\7\3\7\7\7l\n\7\f\7\16\7o\13\7\3\b\3\b"+
		"\3\t\3\t\3\n\3\n\3\n\5\nx\n\n\3\n\3\n\3\n\3\n\5\n~\n\n\7\n\u0080\n\n\f"+
		"\n\16\n\u0083\13\n\3\13\3\13\3\13\3\13\5\13\u0089\n\13\3\f\3\f\7\f\u008d"+
		"\n\f\f\f\16\f\u0090\13\f\3\r\3\r\3\r\3\r\7\r\u0096\n\r\f\r\16\r\u0099"+
		"\13\r\3\16\3\16\3\16\3\16\7\16\u009f\n\16\f\16\16\16\u00a2\13\16\3\17"+
		"\3\17\3\17\7\17\u00a7\n\17\f\17\16\17\u00aa\13\17\3\20\3\20\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23"+
		"\3\23\5\23\u00bf\n\23\3\24\3\24\3\24\5\24\u00c4\n\24\3\25\3\25\3\25\3"+
		"\25\3\26\3\26\5\26\u00cc\n\26\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30"+
		"\3\31\3\31\3\31\3\31\3\31\5\31\u00db\n\31\3\32\3\32\3\32\3\32\3\32\5\32"+
		"\u00e2\n\32\3\33\3\33\3\34\3\34\3\34\5\34\u00e9\n\34\3\35\3\35\3\35\3"+
		"\35\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\5\37\u00f8\n\37\3\37"+
		"\3\37\3\37\3\37\5\37\u00fe\n\37\3\37\3\37\3 \3 \7 \u0104\n \f \16 \u0107"+
		"\13 \3 \3 \3!\3!\3!\3!\3!\3!\3!\3!\5!\u0113\n!\3\"\3\"\3\"\5\"\u0118\n"+
		"\"\3\"\3\"\3\"\3\"\5\"\u011e\n\"\3\"\2\2#\2\4\6\b\n\f\16\20\22\24\26\30"+
		"\32\34\36 \"$&(*,.\60\62\64\668:<>@B\2\6\3\2&+\3\2\23\32\3\2,\66\3\2\34"+
		"\37\2\u0125\2D\3\2\2\2\4H\3\2\2\2\6N\3\2\2\2\b[\3\2\2\2\n]\3\2\2\2\fg"+
		"\3\2\2\2\16p\3\2\2\2\20r\3\2\2\2\22w\3\2\2\2\24\u0088\3\2\2\2\26\u008a"+
		"\3\2\2\2\30\u0091\3\2\2\2\32\u009a\3\2\2\2\34\u00a3\3\2\2\2\36\u00ab\3"+
		"\2\2\2 \u00ad\3\2\2\2\"\u00b6\3\2\2\2$\u00be\3\2\2\2&\u00c3\3\2\2\2(\u00c5"+
		"\3\2\2\2*\u00cb\3\2\2\2,\u00cd\3\2\2\2.\u00d1\3\2\2\2\60\u00da\3\2\2\2"+
		"\62\u00e1\3\2\2\2\64\u00e3\3\2\2\2\66\u00e8\3\2\2\28\u00ea\3\2\2\2:\u00ee"+
		"\3\2\2\2<\u00f2\3\2\2\2>\u0101\3\2\2\2@\u0112\3\2\2\2B\u0117\3\2\2\2D"+
		"E\5\4\3\2EF\7\2\2\3F\3\3\2\2\2GI\5\6\4\2HG\3\2\2\2IJ\3\2\2\2JH\3\2\2\2"+
		"JK\3\2\2\2K\5\3\2\2\2LO\5 \21\2MO\5\b\5\2NL\3\2\2\2NM\3\2\2\2O\7\3\2\2"+
		"\2PQ\5\n\6\2QR\7\16\2\2R\\\3\2\2\2S\\\5\66\34\2TU\5\"\22\2UV\7\16\2\2"+
		"V\\\3\2\2\2WX\5\f\7\2XY\7\16\2\2Y\\\3\2\2\2Z\\\7\16\2\2[P\3\2\2\2[S\3"+
		"\2\2\2[T\3\2\2\2[W\3\2\2\2[Z\3\2\2\2\\\t\3\2\2\2]^\5\20\t\2^_\7 \2\2_"+
		"d\5\22\n\2`a\7!\2\2ac\5\16\b\2b`\3\2\2\2cf\3\2\2\2db\3\2\2\2de\3\2\2\2"+
		"e\13\3\2\2\2fd\3\2\2\2gh\7\21\2\2hm\5\22\n\2ij\7!\2\2jl\5\16\b\2ki\3\2"+
		"\2\2lo\3\2\2\2mk\3\2\2\2mn\3\2\2\2n\r\3\2\2\2om\3\2\2\2pq\t\2\2\2q\17"+
		"\3\2\2\2rs\7\66\2\2s\21\3\2\2\2tx\5\20\t\2ux\5\"\22\2vx\5\24\13\2wt\3"+
		"\2\2\2wu\3\2\2\2wv\3\2\2\2x\u0081\3\2\2\2y}\5\36\20\2z~\5\20\t\2{~\5\""+
		"\22\2|~\5\24\13\2}z\3\2\2\2}{\3\2\2\2}|\3\2\2\2~\u0080\3\2\2\2\177y\3"+
		"\2\2\2\u0080\u0083\3\2\2\2\u0081\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082"+
		"\23\3\2\2\2\u0083\u0081\3\2\2\2\u0084\u0089\5\26\f\2\u0085\u0089\5\30"+
		"\r\2\u0086\u0089\5\32\16\2\u0087\u0089\5\34\17\2\u0088\u0084\3\2\2\2\u0088"+
		"\u0085\3\2\2\2\u0088\u0086\3\2\2\2\u0088\u0087\3\2\2\2\u0089\25\3\2\2"+
		"\2\u008a\u008e\7\3\2\2\u008b\u008d\7\3\2\2\u008c\u008b\3\2\2\2\u008d\u0090"+
		"\3\2\2\2\u008e\u008c\3\2\2\2\u008e\u008f\3\2\2\2\u008f\27\3\2\2\2\u0090"+
		"\u008e\3\2\2\2\u0091\u0097\7\6\2\2\u0092\u0093\5\36\20\2\u0093\u0094\7"+
		"\6\2\2\u0094\u0096\3\2\2\2\u0095\u0092\3\2\2\2\u0096\u0099\3\2\2\2\u0097"+
		"\u0095\3\2\2\2\u0097\u0098\3\2\2\2\u0098\31\3\2\2\2\u0099\u0097\3\2\2"+
		"\2\u009a\u00a0\7\5\2\2\u009b\u009c\5\36\20\2\u009c\u009d\7\5\2\2\u009d"+
		"\u009f\3\2\2\2\u009e\u009b\3\2\2\2\u009f\u00a2\3\2\2\2\u00a0\u009e\3\2"+
		"\2\2\u00a0\u00a1\3\2\2\2\u00a1\33\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a3\u00a8"+
		"\7\4\2\2\u00a4\u00a5\7\24\2\2\u00a5\u00a7\7\4\2\2\u00a6\u00a4\3\2\2\2"+
		"\u00a7\u00aa\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\35"+
		"\3\2\2\2\u00aa\u00a8\3\2\2\2\u00ab\u00ac\t\3\2\2\u00ac\37\3\2\2\2\u00ad"+
		"\u00ae\7\17\2\2\u00ae\u00af\5\64\33\2\u00af\u00b0\7\b\2\2\u00b0\u00b1"+
		"\5&\24\2\u00b1\u00b2\7\t\2\2\u00b2\u00b3\7\20\2\2\u00b3\u00b4\5\16\b\2"+
		"\u00b4\u00b5\5> \2\u00b5!\3\2\2\2\u00b6\u00b7\5\64\33\2\u00b7\u00b8\7"+
		"\b\2\2\u00b8\u00b9\5$\23\2\u00b9\u00ba\7\t\2\2\u00ba#\3\2\2\2\u00bb\u00bf"+
		"\3\2\2\2\u00bc\u00bf\5*\26\2\u00bd\u00bf\5.\30\2\u00be\u00bb\3\2\2\2\u00be"+
		"\u00bc\3\2\2\2\u00be\u00bd\3\2\2\2\u00bf%\3\2\2\2\u00c0\u00c4\3\2\2\2"+
		"\u00c1\u00c4\5(\25\2\u00c2\u00c4\5,\27\2\u00c3\u00c0\3\2\2\2\u00c3\u00c1"+
		"\3\2\2\2\u00c3\u00c2\3\2\2\2\u00c4\'\3\2\2\2\u00c5\u00c6\5\20\t\2\u00c6"+
		"\u00c7\7\22\2\2\u00c7\u00c8\5\16\b\2\u00c8)\3\2\2\2\u00c9\u00cc\5\20\t"+
		"\2\u00ca\u00cc\5\22\n\2\u00cb\u00c9\3\2\2\2\u00cb\u00ca\3\2\2\2\u00cc"+
		"+\3\2\2\2\u00cd\u00ce\5(\25\2\u00ce\u00cf\7\7\2\2\u00cf\u00d0\5\60\31"+
		"\2\u00d0-\3\2\2\2\u00d1\u00d2\5*\26\2\u00d2\u00d3\7\7\2\2\u00d3\u00d4"+
		"\5\62\32\2\u00d4/\3\2\2\2\u00d5\u00db\5(\25\2\u00d6\u00d7\5(\25\2\u00d7"+
		"\u00d8\7\7\2\2\u00d8\u00d9\5\60\31\2\u00d9\u00db\3\2\2\2\u00da\u00d5\3"+
		"\2\2\2\u00da\u00d6\3\2\2\2\u00db\61\3\2\2\2\u00dc\u00e2\5*\26\2\u00dd"+
		"\u00de\5*\26\2\u00de\u00df\7\7\2\2\u00df\u00e0\5\62\32\2\u00e0\u00e2\3"+
		"\2\2\2\u00e1\u00dc\3\2\2\2\u00e1\u00dd\3\2\2\2\u00e2\63\3\2\2\2\u00e3"+
		"\u00e4\t\4\2\2\u00e4\65\3\2\2\2\u00e5\u00e9\5<\37\2\u00e6\u00e9\5:\36"+
		"\2\u00e7\u00e9\58\35\2\u00e8\u00e5\3\2\2\2\u00e8\u00e6\3\2\2\2\u00e8\u00e7"+
		"\3\2\2\2\u00e9\67\3\2\2\2\u00ea\u00eb\7\"\2\2\u00eb\u00ec\5@!\2\u00ec"+
		"\u00ed\5> \2\u00ed9\3\2\2\2\u00ee\u00ef\7#\2\2\u00ef\u00f0\5@!\2\u00f0"+
		"\u00f1\5> \2\u00f1;\3\2\2\2\u00f2\u00f3\7$\2\2\u00f3\u00f4\5\20\t\2\u00f4"+
		"\u00f7\7%\2\2\u00f5\u00f8\5\20\t\2\u00f6\u00f8\7\3\2\2\u00f7\u00f5\3\2"+
		"\2\2\u00f7\u00f6\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9\u00fa\7\r\2\2\u00fa"+
		"\u00fd\7\r\2\2\u00fb\u00fe\5\20\t\2\u00fc\u00fe\7\3\2\2\u00fd\u00fb\3"+
		"\2\2\2\u00fd\u00fc\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\u0100\5> \2\u0100"+
		"=\3\2\2\2\u0101\u0105\7\n\2\2\u0102\u0104\5\b\5\2\u0103\u0102\3\2\2\2"+
		"\u0104\u0107\3\2\2\2\u0105\u0103\3\2\2\2\u0105\u0106\3\2\2\2\u0106\u0108"+
		"\3\2\2\2\u0107\u0105\3\2\2\2\u0108\u0109\7\13\2\2\u0109?\3\2\2\2\u010a"+
		"\u010b\7\33\2\2\u010b\u010c\7\b\2\2\u010c\u010d\5@!\2\u010d\u010e\7\t"+
		"\2\2\u010e\u0113\3\2\2\2\u010f\u0113\5\20\t\2\u0110\u0113\5\"\22\2\u0111"+
		"\u0113\5B\"\2\u0112\u010a\3\2\2\2\u0112\u010f\3\2\2\2\u0112\u0110\3\2"+
		"\2\2\u0112\u0111\3\2\2\2\u0113A\3\2\2\2\u0114\u0118\5\20\t\2\u0115\u0118"+
		"\5\"\22\2\u0116\u0118\5\24\13\2\u0117\u0114\3\2\2\2\u0117\u0115\3\2\2"+
		"\2\u0117\u0116\3\2\2\2\u0118\u0119\3\2\2\2\u0119\u011d\t\5\2\2\u011a\u011e"+
		"\5\20\t\2\u011b\u011e\5\"\22\2\u011c\u011e\5\24\13\2\u011d\u011a\3\2\2"+
		"\2\u011d\u011b\3\2\2\2\u011d\u011c\3\2\2\2\u011eC\3\2\2\2\33JN[dmw}\u0081"+
		"\u0088\u008e\u0097\u00a0\u00a8\u00be\u00c3\u00cb\u00da\u00e1\u00e8\u00f7"+
		"\u00fd\u0105\u0112\u0117\u011d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}