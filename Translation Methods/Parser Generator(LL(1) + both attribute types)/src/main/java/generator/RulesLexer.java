// Generated from /home/buraindo/Documents/Programming/IdeaProjects/ParserGenerator/src/main/java/generator/Rules.g4 by ANTLR 4.7.2
package generator;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RulesLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		GRAMMAR=1, IMPORT=2, HEADER=3, RETURNS=4, PACKAGE=5, SKIP_RULE=6, OR=7, 
		COLON=8, SEMICOLON=9, CURLY_OPEN=10, CURLY_CLOSE=11, OPEN=12, CLOSE=13, 
		DOT=14, ASTERISK=15, SQUARE_OPEN=16, SQUARE_CLOSE=17, EQUALS_SIGN=18, 
		TOKEN_NAME=19, IDENTIFIER=20, CODE=21, ARGUMENT=22, REGEX=23, WHITESPACE=24;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"GRAMMAR", "IMPORT", "HEADER", "RETURNS", "PACKAGE", "SKIP_RULE", "OR", 
			"COLON", "SEMICOLON", "CURLY_OPEN", "CURLY_CLOSE", "OPEN", "CLOSE", "DOT", 
			"ASTERISK", "SQUARE_OPEN", "SQUARE_CLOSE", "EQUALS_SIGN", "TOKEN_NAME", 
			"IDENTIFIER", "CODE", "ARGUMENT", "REGEX", "WHITESPACE"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'grammar'", "'import'", "'header'", "'returns'", "'package'", 
			"'-> skip'", "'|'", "':'", "';'", "'{'", "'}'", "'('", "')'", "'.'", 
			"'*'", "'['", "']'", "'='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "GRAMMAR", "IMPORT", "HEADER", "RETURNS", "PACKAGE", "SKIP_RULE", 
			"OR", "COLON", "SEMICOLON", "CURLY_OPEN", "CURLY_CLOSE", "OPEN", "CLOSE", 
			"DOT", "ASTERISK", "SQUARE_OPEN", "SQUARE_CLOSE", "EQUALS_SIGN", "TOKEN_NAME", 
			"IDENTIFIER", "CODE", "ARGUMENT", "REGEX", "WHITESPACE"
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


	public RulesLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Rules.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\32\u00a0\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\n"+
		"\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21"+
		"\3\22\3\22\3\23\3\23\3\24\6\24{\n\24\r\24\16\24|\3\25\6\25\u0080\n\25"+
		"\r\25\16\25\u0081\3\26\3\26\6\26\u0086\n\26\r\26\16\26\u0087\3\26\3\26"+
		"\3\27\3\27\6\27\u008e\n\27\r\27\16\27\u008f\3\27\3\27\3\30\3\30\7\30\u0096"+
		"\n\30\f\30\16\30\u0099\13\30\3\30\3\30\3\31\3\31\3\31\3\31\5\u0087\u008f"+
		"\u0097\2\32\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33"+
		"\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\3\2\5\4\2C\\aa"+
		"\4\2C\\c|\5\2\13\f\17\17\"\"\2\u00a4\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2"+
		"\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23"+
		"\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2"+
		"\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2"+
		"\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\3\63\3\2\2\2\5;\3\2"+
		"\2\2\7B\3\2\2\2\tI\3\2\2\2\13Q\3\2\2\2\rY\3\2\2\2\17a\3\2\2\2\21c\3\2"+
		"\2\2\23e\3\2\2\2\25g\3\2\2\2\27i\3\2\2\2\31k\3\2\2\2\33m\3\2\2\2\35o\3"+
		"\2\2\2\37q\3\2\2\2!s\3\2\2\2#u\3\2\2\2%w\3\2\2\2\'z\3\2\2\2)\177\3\2\2"+
		"\2+\u0083\3\2\2\2-\u008b\3\2\2\2/\u0093\3\2\2\2\61\u009c\3\2\2\2\63\64"+
		"\7i\2\2\64\65\7t\2\2\65\66\7c\2\2\66\67\7o\2\2\678\7o\2\289\7c\2\29:\7"+
		"t\2\2:\4\3\2\2\2;<\7k\2\2<=\7o\2\2=>\7r\2\2>?\7q\2\2?@\7t\2\2@A\7v\2\2"+
		"A\6\3\2\2\2BC\7j\2\2CD\7g\2\2DE\7c\2\2EF\7f\2\2FG\7g\2\2GH\7t\2\2H\b\3"+
		"\2\2\2IJ\7t\2\2JK\7g\2\2KL\7v\2\2LM\7w\2\2MN\7t\2\2NO\7p\2\2OP\7u\2\2"+
		"P\n\3\2\2\2QR\7r\2\2RS\7c\2\2ST\7e\2\2TU\7m\2\2UV\7c\2\2VW\7i\2\2WX\7"+
		"g\2\2X\f\3\2\2\2YZ\7/\2\2Z[\7@\2\2[\\\7\"\2\2\\]\7u\2\2]^\7m\2\2^_\7k"+
		"\2\2_`\7r\2\2`\16\3\2\2\2ab\7~\2\2b\20\3\2\2\2cd\7<\2\2d\22\3\2\2\2ef"+
		"\7=\2\2f\24\3\2\2\2gh\7}\2\2h\26\3\2\2\2ij\7\177\2\2j\30\3\2\2\2kl\7*"+
		"\2\2l\32\3\2\2\2mn\7+\2\2n\34\3\2\2\2op\7\60\2\2p\36\3\2\2\2qr\7,\2\2"+
		"r \3\2\2\2st\7]\2\2t\"\3\2\2\2uv\7_\2\2v$\3\2\2\2wx\7?\2\2x&\3\2\2\2y"+
		"{\t\2\2\2zy\3\2\2\2{|\3\2\2\2|z\3\2\2\2|}\3\2\2\2}(\3\2\2\2~\u0080\t\3"+
		"\2\2\177~\3\2\2\2\u0080\u0081\3\2\2\2\u0081\177\3\2\2\2\u0081\u0082\3"+
		"\2\2\2\u0082*\3\2\2\2\u0083\u0085\5\25\13\2\u0084\u0086\13\2\2\2\u0085"+
		"\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0088\3\2\2\2\u0087\u0085\3\2"+
		"\2\2\u0088\u0089\3\2\2\2\u0089\u008a\5\27\f\2\u008a,\3\2\2\2\u008b\u008d"+
		"\5\31\r\2\u008c\u008e\13\2\2\2\u008d\u008c\3\2\2\2\u008e\u008f\3\2\2\2"+
		"\u008f\u0090\3\2\2\2\u008f\u008d\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0092"+
		"\5\33\16\2\u0092.\3\2\2\2\u0093\u0097\7$\2\2\u0094\u0096\13\2\2\2\u0095"+
		"\u0094\3\2\2\2\u0096\u0099\3\2\2\2\u0097\u0098\3\2\2\2\u0097\u0095\3\2"+
		"\2\2\u0098\u009a\3\2\2\2\u0099\u0097\3\2\2\2\u009a\u009b\7$\2\2\u009b"+
		"\60\3\2\2\2\u009c\u009d\t\4\2\2\u009d\u009e\3\2\2\2\u009e\u009f\b\31\2"+
		"\2\u009f\62\3\2\2\2\b\2|\u0081\u0087\u008f\u0097\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}