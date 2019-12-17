// Generated from /home/buraindo/Documents/Programming/IdeaProjects/TM3/src/main/java/ego/Ego.g4 by ANTLR 4.7.2
package ego;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class EgoLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"COLON", "EQUALS_SIGN", "AMPERSAND", "VERTICAL", "NUMBER", "STRING", 
			"CHAR", "DOUBLE", "COMMA", "OPEN", "CLOSE", "BLOCK_OPEN", "BLOCK_CLOSE", 
			"SEMICOLON", "DOT", "NEWLINE", "FUNCTION", "RETURNS", "RETURN", "OF", 
			"MULTIPLY", "PLUS", "MINUS", "DIVIDE", "MODULO", "AND", "OR", "XOR", 
			"NOT", "LESS", "MORE_", "EQUALS", "NOT_EQUALS", "ASSIGN", "AS", "IF", 
			"WHILE", "FOR", "IN", "T_INT", "T_LONG", "T_CHAR", "T_STRING", "T_DOUBLE", 
			"T_NOTHING", "NEXT_INT", "NEXT_LONG", "NEXT_CHAR", "NEXT_DOUBLE", "NEXT_LINE", 
			"PRINT_INT", "PRINT_LONG", "PRINT_CHAR", "PRINT_DOUBLE", "PRINT_STRING", 
			"IDENTIFIER", "WHITESPACE"
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


	public EgoLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Ego.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\67\u0189\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\3\2\3\2\3\3\3\3"+
		"\3\4\3\4\3\5\3\5\3\6\6\6\177\n\6\r\6\16\6\u0080\3\7\3\7\7\7\u0085\n\7"+
		"\f\7\16\7\u0088\13\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3"+
		"\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\26\3\26"+
		"\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3\34"+
		"\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3!\3\"\3\"\3\"\3#\3#\3$\3$"+
		"\3$\3%\3%\3%\3&\3&\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3(\3(\3(\3)\3)\3)\3)\3"+
		"*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3,\3,\3,\3,\3,\3,\3,\3-\3-\3-\3-\3-\3-\3"+
		"-\3.\3.\3.\3.\3.\3.\3.\3.\3/\3/\3/\3/\3/\3/\3/\3/\3\60\3\60\3\60\3\60"+
		"\3\60\3\60\3\60\3\60\3\60\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61"+
		"\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\63\3\63\3\63"+
		"\3\63\3\63\3\63\3\63\3\63\3\63\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64"+
		"\3\64\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\66\3\66\3\66"+
		"\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\67\3\67\3\67\3\67\3\67\3\67\3\67"+
		"\3\67\3\67\3\67\3\67\3\67\38\38\38\38\38\38\38\38\38\38\38\38\39\79\u017a"+
		"\n9\f9\169\u017d\139\39\39\79\u0181\n9\f9\169\u0184\139\3:\3:\3:\3:\3"+
		"\u0086\2;\3\2\5\2\7\2\t\2\13\3\r\4\17\5\21\6\23\7\25\b\27\t\31\n\33\13"+
		"\35\f\37\r!\16#\17%\20\'\21)\22+\23-\24/\25\61\26\63\27\65\30\67\319\32"+
		";\33=\34?\35A\36C\37E G!I\"K#M$O%Q&S\'U(W)Y*[+],_-a.c/e\60g\61i\62k\63"+
		"m\64o\65q\66s\67\3\2\6\3\2\62;\4\2C\\c|\6\2\62;C\\aac|\5\2\13\f\17\17"+
		"\"\"\2\u0188\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3"+
		"\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2"+
		"\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2"+
		"\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2"+
		"\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2"+
		"\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2"+
		"O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3"+
		"\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2"+
		"\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\3"+
		"u\3\2\2\2\5w\3\2\2\2\7y\3\2\2\2\t{\3\2\2\2\13~\3\2\2\2\r\u0082\3\2\2\2"+
		"\17\u008b\3\2\2\2\21\u008f\3\2\2\2\23\u0093\3\2\2\2\25\u0095\3\2\2\2\27"+
		"\u0097\3\2\2\2\31\u0099\3\2\2\2\33\u009b\3\2\2\2\35\u009d\3\2\2\2\37\u009f"+
		"\3\2\2\2!\u00a1\3\2\2\2#\u00a3\3\2\2\2%\u00ac\3\2\2\2\'\u00b4\3\2\2\2"+
		")\u00bb\3\2\2\2+\u00be\3\2\2\2-\u00c0\3\2\2\2/\u00c2\3\2\2\2\61\u00c4"+
		"\3\2\2\2\63\u00c6\3\2\2\2\65\u00c8\3\2\2\2\67\u00cb\3\2\2\29\u00ce\3\2"+
		"\2\2;\u00d0\3\2\2\2=\u00d2\3\2\2\2?\u00d4\3\2\2\2A\u00d6\3\2\2\2C\u00d9"+
		"\3\2\2\2E\u00dc\3\2\2\2G\u00de\3\2\2\2I\u00e1\3\2\2\2K\u00e4\3\2\2\2M"+
		"\u00ea\3\2\2\2O\u00ee\3\2\2\2Q\u00f1\3\2\2\2S\u00f5\3\2\2\2U\u00fa\3\2"+
		"\2\2W\u00ff\3\2\2\2Y\u0106\3\2\2\2[\u010d\3\2\2\2]\u0115\3\2\2\2_\u011d"+
		"\3\2\2\2a\u0126\3\2\2\2c\u012f\3\2\2\2e\u013a\3\2\2\2g\u0143\3\2\2\2i"+
		"\u014c\3\2\2\2k\u0156\3\2\2\2m\u0160\3\2\2\2o\u016c\3\2\2\2q\u017b\3\2"+
		"\2\2s\u0185\3\2\2\2uv\7<\2\2v\4\3\2\2\2wx\7?\2\2x\6\3\2\2\2yz\7(\2\2z"+
		"\b\3\2\2\2{|\7~\2\2|\n\3\2\2\2}\177\t\2\2\2~}\3\2\2\2\177\u0080\3\2\2"+
		"\2\u0080~\3\2\2\2\u0080\u0081\3\2\2\2\u0081\f\3\2\2\2\u0082\u0086\7$\2"+
		"\2\u0083\u0085\13\2\2\2\u0084\u0083\3\2\2\2\u0085\u0088\3\2\2\2\u0086"+
		"\u0087\3\2\2\2\u0086\u0084\3\2\2\2\u0087\u0089\3\2\2\2\u0088\u0086\3\2"+
		"\2\2\u0089\u008a\7$\2\2\u008a\16\3\2\2\2\u008b\u008c\7)\2\2\u008c\u008d"+
		"\13\2\2\2\u008d\u008e\7)\2\2\u008e\20\3\2\2\2\u008f\u0090\5\13\6\2\u0090"+
		"\u0091\5\37\20\2\u0091\u0092\5\13\6\2\u0092\22\3\2\2\2\u0093\u0094\7."+
		"\2\2\u0094\24\3\2\2\2\u0095\u0096\7*\2\2\u0096\26\3\2\2\2\u0097\u0098"+
		"\7+\2\2\u0098\30\3\2\2\2\u0099\u009a\7}\2\2\u009a\32\3\2\2\2\u009b\u009c"+
		"\7\177\2\2\u009c\34\3\2\2\2\u009d\u009e\7=\2\2\u009e\36\3\2\2\2\u009f"+
		"\u00a0\7\60\2\2\u00a0 \3\2\2\2\u00a1\u00a2\7\f\2\2\u00a2\"\3\2\2\2\u00a3"+
		"\u00a4\7h\2\2\u00a4\u00a5\7w\2\2\u00a5\u00a6\7p\2\2\u00a6\u00a7\7e\2\2"+
		"\u00a7\u00a8\7v\2\2\u00a8\u00a9\7k\2\2\u00a9\u00aa\7q\2\2\u00aa\u00ab"+
		"\7p\2\2\u00ab$\3\2\2\2\u00ac\u00ad\7t\2\2\u00ad\u00ae\7g\2\2\u00ae\u00af"+
		"\7v\2\2\u00af\u00b0\7w\2\2\u00b0\u00b1\7t\2\2\u00b1\u00b2\7p\2\2\u00b2"+
		"\u00b3\7u\2\2\u00b3&\3\2\2\2\u00b4\u00b5\7t\2\2\u00b5\u00b6\7g\2\2\u00b6"+
		"\u00b7\7v\2\2\u00b7\u00b8\7w\2\2\u00b8\u00b9\7t\2\2\u00b9\u00ba\7p\2\2"+
		"\u00ba(\3\2\2\2\u00bb\u00bc\7q\2\2\u00bc\u00bd\7h\2\2\u00bd*\3\2\2\2\u00be"+
		"\u00bf\7,\2\2\u00bf,\3\2\2\2\u00c0\u00c1\7-\2\2\u00c1.\3\2\2\2\u00c2\u00c3"+
		"\7/\2\2\u00c3\60\3\2\2\2\u00c4\u00c5\7\61\2\2\u00c5\62\3\2\2\2\u00c6\u00c7"+
		"\7\'\2\2\u00c7\64\3\2\2\2\u00c8\u00c9\5\7\4\2\u00c9\u00ca\5\7\4\2\u00ca"+
		"\66\3\2\2\2\u00cb\u00cc\5\t\5\2\u00cc\u00cd\5\t\5\2\u00cd8\3\2\2\2\u00ce"+
		"\u00cf\7`\2\2\u00cf:\3\2\2\2\u00d0\u00d1\7#\2\2\u00d1<\3\2\2\2\u00d2\u00d3"+
		"\7>\2\2\u00d3>\3\2\2\2\u00d4\u00d5\7@\2\2\u00d5@\3\2\2\2\u00d6\u00d7\5"+
		"\5\3\2\u00d7\u00d8\5\5\3\2\u00d8B\3\2\2\2\u00d9\u00da\5;\36\2\u00da\u00db"+
		"\5\5\3\2\u00dbD\3\2\2\2\u00dc\u00dd\5\5\3\2\u00ddF\3\2\2\2\u00de\u00df"+
		"\7c\2\2\u00df\u00e0\7u\2\2\u00e0H\3\2\2\2\u00e1\u00e2\7k\2\2\u00e2\u00e3"+
		"\7h\2\2\u00e3J\3\2\2\2\u00e4\u00e5\7y\2\2\u00e5\u00e6\7j\2\2\u00e6\u00e7"+
		"\7k\2\2\u00e7\u00e8\7n\2\2\u00e8\u00e9\7g\2\2\u00e9L\3\2\2\2\u00ea\u00eb"+
		"\7h\2\2\u00eb\u00ec\7q\2\2\u00ec\u00ed\7t\2\2\u00edN\3\2\2\2\u00ee\u00ef"+
		"\7k\2\2\u00ef\u00f0\7p\2\2\u00f0P\3\2\2\2\u00f1\u00f2\7K\2\2\u00f2\u00f3"+
		"\7p\2\2\u00f3\u00f4\7v\2\2\u00f4R\3\2\2\2\u00f5\u00f6\7N\2\2\u00f6\u00f7"+
		"\7q\2\2\u00f7\u00f8\7p\2\2\u00f8\u00f9\7i\2\2\u00f9T\3\2\2\2\u00fa\u00fb"+
		"\7E\2\2\u00fb\u00fc\7j\2\2\u00fc\u00fd\7c\2\2\u00fd\u00fe\7t\2\2\u00fe"+
		"V\3\2\2\2\u00ff\u0100\7U\2\2\u0100\u0101\7v\2\2\u0101\u0102\7t\2\2\u0102"+
		"\u0103\7k\2\2\u0103\u0104\7p\2\2\u0104\u0105\7i\2\2\u0105X\3\2\2\2\u0106"+
		"\u0107\7F\2\2\u0107\u0108\7q\2\2\u0108\u0109\7w\2\2\u0109\u010a\7d\2\2"+
		"\u010a\u010b\7n\2\2\u010b\u010c\7g\2\2\u010cZ\3\2\2\2\u010d\u010e\7P\2"+
		"\2\u010e\u010f\7q\2\2\u010f\u0110\7v\2\2\u0110\u0111\7j\2\2\u0111\u0112"+
		"\7k\2\2\u0112\u0113\7p\2\2\u0113\u0114\7i\2\2\u0114\\\3\2\2\2\u0115\u0116"+
		"\7p\2\2\u0116\u0117\7g\2\2\u0117\u0118\7z\2\2\u0118\u0119\7v\2\2\u0119"+
		"\u011a\7K\2\2\u011a\u011b\7p\2\2\u011b\u011c\7v\2\2\u011c^\3\2\2\2\u011d"+
		"\u011e\7p\2\2\u011e\u011f\7g\2\2\u011f\u0120\7z\2\2\u0120\u0121\7v\2\2"+
		"\u0121\u0122\7N\2\2\u0122\u0123\7q\2\2\u0123\u0124\7p\2\2\u0124\u0125"+
		"\7i\2\2\u0125`\3\2\2\2\u0126\u0127\7p\2\2\u0127\u0128\7g\2\2\u0128\u0129"+
		"\7z\2\2\u0129\u012a\7v\2\2\u012a\u012b\7E\2\2\u012b\u012c\7j\2\2\u012c"+
		"\u012d\7c\2\2\u012d\u012e\7t\2\2\u012eb\3\2\2\2\u012f\u0130\7p\2\2\u0130"+
		"\u0131\7g\2\2\u0131\u0132\7z\2\2\u0132\u0133\7v\2\2\u0133\u0134\7F\2\2"+
		"\u0134\u0135\7q\2\2\u0135\u0136\7w\2\2\u0136\u0137\7d\2\2\u0137\u0138"+
		"\7n\2\2\u0138\u0139\7g\2\2\u0139d\3\2\2\2\u013a\u013b\7p\2\2\u013b\u013c"+
		"\7g\2\2\u013c\u013d\7z\2\2\u013d\u013e\7v\2\2\u013e\u013f\7N\2\2\u013f"+
		"\u0140\7k\2\2\u0140\u0141\7p\2\2\u0141\u0142\7g\2\2\u0142f\3\2\2\2\u0143"+
		"\u0144\7r\2\2\u0144\u0145\7t\2\2\u0145\u0146\7k\2\2\u0146\u0147\7p\2\2"+
		"\u0147\u0148\7v\2\2\u0148\u0149\7K\2\2\u0149\u014a\7p\2\2\u014a\u014b"+
		"\7v\2\2\u014bh\3\2\2\2\u014c\u014d\7r\2\2\u014d\u014e\7t\2\2\u014e\u014f"+
		"\7k\2\2\u014f\u0150\7p\2\2\u0150\u0151\7v\2\2\u0151\u0152\7N\2\2\u0152"+
		"\u0153\7q\2\2\u0153\u0154\7p\2\2\u0154\u0155\7i\2\2\u0155j\3\2\2\2\u0156"+
		"\u0157\7r\2\2\u0157\u0158\7t\2\2\u0158\u0159\7k\2\2\u0159\u015a\7p\2\2"+
		"\u015a\u015b\7v\2\2\u015b\u015c\7E\2\2\u015c\u015d\7j\2\2\u015d\u015e"+
		"\7c\2\2\u015e\u015f\7t\2\2\u015fl\3\2\2\2\u0160\u0161\7r\2\2\u0161\u0162"+
		"\7t\2\2\u0162\u0163\7k\2\2\u0163\u0164\7p\2\2\u0164\u0165\7v\2\2\u0165"+
		"\u0166\7F\2\2\u0166\u0167\7q\2\2\u0167\u0168\7w\2\2\u0168\u0169\7d\2\2"+
		"\u0169\u016a\7n\2\2\u016a\u016b\7g\2\2\u016bn\3\2\2\2\u016c\u016d\7r\2"+
		"\2\u016d\u016e\7t\2\2\u016e\u016f\7k\2\2\u016f\u0170\7p\2\2\u0170\u0171"+
		"\7v\2\2\u0171\u0172\7U\2\2\u0172\u0173\7v\2\2\u0173\u0174\7t\2\2\u0174"+
		"\u0175\7k\2\2\u0175\u0176\7p\2\2\u0176\u0177\7i\2\2\u0177p\3\2\2\2\u0178"+
		"\u017a\7a\2\2\u0179\u0178\3\2\2\2\u017a\u017d\3\2\2\2\u017b\u0179\3\2"+
		"\2\2\u017b\u017c\3\2\2\2\u017c\u017e\3\2\2\2\u017d\u017b\3\2\2\2\u017e"+
		"\u0182\t\3\2\2\u017f\u0181\t\4\2\2\u0180\u017f\3\2\2\2\u0181\u0184\3\2"+
		"\2\2\u0182\u0180\3\2\2\2\u0182\u0183\3\2\2\2\u0183r\3\2\2\2\u0184\u0182"+
		"\3\2\2\2\u0185\u0186\t\5\2\2\u0186\u0187\3\2\2\2\u0187\u0188\b:\2\2\u0188"+
		"t\3\2\2\2\7\2\u0080\u0086\u017b\u0182\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}