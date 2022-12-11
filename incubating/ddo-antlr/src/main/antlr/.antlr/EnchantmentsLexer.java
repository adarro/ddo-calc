// Generated from /home/adarro/IdeaProjects/ddo-calc/incubating/ddo-antlr/src/main/antlr/EnchantmentsLexer.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class EnchantmentsLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LineBreak=1, ENCHANTMENT=2, AUGMENTSLOT=3, LBL_AUGMENT_SLOT=4, ENHANCEMENT_BONUS=5, 
		FORT=6, SHELTERING=7, SHELTERTYPE=8, AUGMENTCOLOR=9, AUGMENTOPTION=10, 
		FilledAugmentSlot=11, EmptyAugmentSlot=12, WS=13, PCT=14, NUM=15, SIGN=16, 
		UNKNOWN_ID=17;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"LineBreak", "ENCHANTMENT", "AUGMENTSLOT", "LBL_AUGMENT_SLOT", "ENHANCEMENT_BONUS", 
			"FORT", "SHELTERING", "SHELTERTYPE", "AUGMENTCOLOR", "AUGMENTOPTION", 
			"FilledAugmentSlot", "EmptyAugmentSlot", "WS", "PCT", "NUM", "SIGN", 
			"UNUM", "UNKNOWN_ID", "MULTI_WORDS", "MULTI_WORD", "SIMPLE_WORD"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, "'Augment Slot:'", "'Enhancement Bonus'", "'Fortification'", 
			null, null, null, null, "'todo:MARKER '", "'Empty'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "LineBreak", "ENCHANTMENT", "AUGMENTSLOT", "LBL_AUGMENT_SLOT", 
			"ENHANCEMENT_BONUS", "FORT", "SHELTERING", "SHELTERTYPE", "AUGMENTCOLOR", 
			"AUGMENTOPTION", "FilledAugmentSlot", "EmptyAugmentSlot", "WS", "PCT", 
			"NUM", "SIGN", "UNKNOWN_ID"
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


	public EnchantmentsLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "EnchantmentsLexer.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\23\u0125\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\5\2/\n\2\3\2\3\2\5\2"+
		"\63\n\2\3\2\3\2\3\3\3\3\3\3\3\3\5\3;\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\5\t\u00a1\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u00ca\n\n\3\13"+
		"\3\13\5\13\u00ce\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\6\16\u00e4\n\16\r\16\16\16\u00e5\3\16"+
		"\3\16\3\17\6\17\u00eb\n\17\r\17\16\17\u00ec\3\17\3\17\3\20\6\20\u00f2"+
		"\n\20\r\20\16\20\u00f3\3\20\3\20\3\21\3\21\3\22\6\22\u00fb\n\22\r\22\16"+
		"\22\u00fc\3\23\3\23\3\23\5\23\u0102\n\23\3\24\6\24\u0105\n\24\r\24\16"+
		"\24\u0106\3\24\6\24\u010a\n\24\r\24\16\24\u010b\3\24\3\24\3\25\6\25\u0111"+
		"\n\25\r\25\16\25\u0112\3\25\6\25\u0116\n\25\r\25\16\25\u0117\3\25\3\25"+
		"\3\26\6\26\u011d\n\26\r\26\16\26\u011e\3\26\6\26\u0122\n\26\r\26\16\26"+
		"\u0123\2\2\27\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16"+
		"\33\17\35\20\37\21!\22#\2%\23\'\2)\2+\2\3\2\7\5\2\13\f\17\17\"\"\4\2-"+
		"-//\3\2\62;\4\2C\\c|\3\2c|\2\u013a\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2"+
		"\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3"+
		"\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2"+
		"\2\2\37\3\2\2\2\2!\3\2\2\2\2%\3\2\2\2\3\62\3\2\2\2\5:\3\2\2\2\7<\3\2\2"+
		"\2\tB\3\2\2\2\13P\3\2\2\2\rb\3\2\2\2\17p\3\2\2\2\21\u00a0\3\2\2\2\23\u00c9"+
		"\3\2\2\2\25\u00cd\3\2\2\2\27\u00cf\3\2\2\2\31\u00dc\3\2\2\2\33\u00e3\3"+
		"\2\2\2\35\u00ea\3\2\2\2\37\u00f1\3\2\2\2!\u00f7\3\2\2\2#\u00fa\3\2\2\2"+
		"%\u0101\3\2\2\2\'\u0104\3\2\2\2)\u0110\3\2\2\2+\u011c\3\2\2\2-/\7\17\2"+
		"\2.-\3\2\2\2./\3\2\2\2/\60\3\2\2\2\60\63\7\f\2\2\61\63\7\17\2\2\62.\3"+
		"\2\2\2\62\61\3\2\2\2\63\64\3\2\2\2\64\65\b\2\2\2\65\4\3\2\2\2\66;\5\r"+
		"\7\2\67;\5\7\4\28;\5\13\6\29;\5\17\b\2:\66\3\2\2\2:\67\3\2\2\2:8\3\2\2"+
		"\2:9\3\2\2\2;\6\3\2\2\2<=\5\23\n\2=>\7\"\2\2>?\5\t\5\2?@\7\"\2\2@A\5\25"+
		"\13\2A\b\3\2\2\2BC\7C\2\2CD\7w\2\2DE\7i\2\2EF\7o\2\2FG\7g\2\2GH\7p\2\2"+
		"HI\7v\2\2IJ\7\"\2\2JK\7U\2\2KL\7n\2\2LM\7q\2\2MN\7v\2\2NO\7<\2\2O\n\3"+
		"\2\2\2PQ\7G\2\2QR\7p\2\2RS\7j\2\2ST\7c\2\2TU\7p\2\2UV\7e\2\2VW\7g\2\2"+
		"WX\7o\2\2XY\7g\2\2YZ\7p\2\2Z[\7v\2\2[\\\7\"\2\2\\]\7D\2\2]^\7q\2\2^_\7"+
		"p\2\2_`\7w\2\2`a\7u\2\2a\f\3\2\2\2bc\7H\2\2cd\7q\2\2de\7t\2\2ef\7v\2\2"+
		"fg\7k\2\2gh\7h\2\2hi\7k\2\2ij\7e\2\2jk\7c\2\2kl\7v\2\2lm\7k\2\2mn\7q\2"+
		"\2no\7p\2\2o\16\3\2\2\2pq\5\21\t\2qr\7\"\2\2rs\7U\2\2st\7j\2\2tu\7g\2"+
		"\2uv\7n\2\2vw\7v\2\2wx\7g\2\2xy\7t\2\2yz\7k\2\2z{\7p\2\2{|\7i\2\2|\20"+
		"\3\2\2\2}~\7R\2\2~\177\7j\2\2\177\u0080\7{\2\2\u0080\u0081\7u\2\2\u0081"+
		"\u0082\7k\2\2\u0082\u0083\7e\2\2\u0083\u0084\7c\2\2\u0084\u0085\7n\2\2"+
		"\u0085\u0086\7\"\2\2\u0086\u0087\7c\2\2\u0087\u0088\7p\2\2\u0088\u0089"+
		"\7f\2\2\u0089\u008a\7\"\2\2\u008a\u008b\7O\2\2\u008b\u008c\7c\2\2\u008c"+
		"\u008d\7i\2\2\u008d\u008e\7k\2\2\u008e\u008f\7e\2\2\u008f\u0090\7c\2\2"+
		"\u0090\u00a1\7n\2\2\u0091\u0092\7R\2\2\u0092\u0093\7j\2\2\u0093\u0094"+
		"\7{\2\2\u0094\u0095\7u\2\2\u0095\u0096\7k\2\2\u0096\u0097\7e\2\2\u0097"+
		"\u0098\7c\2\2\u0098\u00a1\7n\2\2\u0099\u009a\7O\2\2\u009a\u009b\7c\2\2"+
		"\u009b\u009c\7i\2\2\u009c\u009d\7k\2\2\u009d\u009e\7e\2\2\u009e\u009f"+
		"\7c\2\2\u009f\u00a1\7n\2\2\u00a0}\3\2\2\2\u00a0\u0091\3\2\2\2\u00a0\u0099"+
		"\3\2\2\2\u00a1\22\3\2\2\2\u00a2\u00a3\7D\2\2\u00a3\u00a4\7n\2\2\u00a4"+
		"\u00a5\7w\2\2\u00a5\u00ca\7g\2\2\u00a6\u00a7\7Q\2\2\u00a7\u00a8\7t\2\2"+
		"\u00a8\u00a9\7c\2\2\u00a9\u00aa\7p\2\2\u00aa\u00ab\7i\2\2\u00ab\u00ca"+
		"\7g\2\2\u00ac\u00ad\7[\2\2\u00ad\u00ae\7g\2\2\u00ae\u00af\7n\2\2\u00af"+
		"\u00b0\7n\2\2\u00b0\u00b1\7q\2\2\u00b1\u00ca\7y\2\2\u00b2\u00b3\7T\2\2"+
		"\u00b3\u00b4\7g\2\2\u00b4\u00ca\7f\2\2\u00b5\u00b6\7I\2\2\u00b6\u00b7"+
		"\7t\2\2\u00b7\u00b8\7g\2\2\u00b8\u00b9\7g\2\2\u00b9\u00ca\7p\2\2\u00ba"+
		"\u00bb\7R\2\2\u00bb\u00bc\7w\2\2\u00bc\u00bd\7t\2\2\u00bd\u00be\7r\2\2"+
		"\u00be\u00bf\7n\2\2\u00bf\u00ca\7g\2\2\u00c0\u00c1\7E\2\2\u00c1\u00c2"+
		"\7q\2\2\u00c2\u00c3\7n\2\2\u00c3\u00c4\7q\2\2\u00c4\u00c5\7t\2\2\u00c5"+
		"\u00c6\7n\2\2\u00c6\u00c7\7g\2\2\u00c7\u00c8\7u\2\2\u00c8\u00ca\7u\2\2"+
		"\u00c9\u00a2\3\2\2\2\u00c9\u00a6\3\2\2\2\u00c9\u00ac\3\2\2\2\u00c9\u00b2"+
		"\3\2\2\2\u00c9\u00b5\3\2\2\2\u00c9\u00ba\3\2\2\2\u00c9\u00c0\3\2\2\2\u00ca"+
		"\24\3\2\2\2\u00cb\u00ce\5\31\r\2\u00cc\u00ce\5\27\f\2\u00cd\u00cb\3\2"+
		"\2\2\u00cd\u00cc\3\2\2\2\u00ce\26\3\2\2\2\u00cf\u00d0\7v\2\2\u00d0\u00d1"+
		"\7q\2\2\u00d1\u00d2\7f\2\2\u00d2\u00d3\7q\2\2\u00d3\u00d4\7<\2\2\u00d4"+
		"\u00d5\7O\2\2\u00d5\u00d6\7C\2\2\u00d6\u00d7\7T\2\2\u00d7\u00d8\7M\2\2"+
		"\u00d8\u00d9\7G\2\2\u00d9\u00da\7T\2\2\u00da\u00db\7\"\2\2\u00db\30\3"+
		"\2\2\2\u00dc\u00dd\7G\2\2\u00dd\u00de\7o\2\2\u00de\u00df\7r\2\2\u00df"+
		"\u00e0\7v\2\2\u00e0\u00e1\7{\2\2\u00e1\32\3\2\2\2\u00e2\u00e4\t\2\2\2"+
		"\u00e3\u00e2\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e5\u00e6"+
		"\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00e8\b\16\3\2\u00e8\34\3\2\2\2\u00e9"+
		"\u00eb\5\37\20\2\u00ea\u00e9\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec\u00ea\3"+
		"\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00ef\7\'\2\2\u00ef"+
		"\36\3\2\2\2\u00f0\u00f2\5!\21\2\u00f1\u00f0\3\2\2\2\u00f2\u00f3\3\2\2"+
		"\2\u00f3\u00f1\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00f6"+
		"\5#\22\2\u00f6 \3\2\2\2\u00f7\u00f8\t\3\2\2\u00f8\"\3\2\2\2\u00f9\u00fb"+
		"\t\4\2\2\u00fa\u00f9\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc\u00fa\3\2\2\2\u00fc"+
		"\u00fd\3\2\2\2\u00fd$\3\2\2\2\u00fe\u0102\5\'\24\2\u00ff\u0102\5+\26\2"+
		"\u0100\u0102\5)\25\2\u0101\u00fe\3\2\2\2\u0101\u00ff\3\2\2\2\u0101\u0100"+
		"\3\2\2\2\u0102&\3\2\2\2\u0103\u0105\5)\25\2\u0104\u0103\3\2\2\2\u0105"+
		"\u0106\3\2\2\2\u0106\u0104\3\2\2\2\u0106\u0107\3\2\2\2\u0107\u0109\3\2"+
		"\2\2\u0108\u010a\7\"\2\2\u0109\u0108\3\2\2\2\u010a\u010b\3\2\2\2\u010b"+
		"\u0109\3\2\2\2\u010b\u010c\3\2\2\2\u010c\u010d\3\2\2\2\u010d\u010e\5+"+
		"\26\2\u010e(\3\2\2\2\u010f\u0111\5+\26\2\u0110\u010f\3\2\2\2\u0111\u0112"+
		"\3\2\2\2\u0112\u0110\3\2\2\2\u0112\u0113\3\2\2\2\u0113\u0115\3\2\2\2\u0114"+
		"\u0116\7\"\2\2\u0115\u0114\3\2\2\2\u0116\u0117\3\2\2\2\u0117\u0115\3\2"+
		"\2\2\u0117\u0118\3\2\2\2\u0118\u0119\3\2\2\2\u0119\u011a\5+\26\2\u011a"+
		"*\3\2\2\2\u011b\u011d\t\5\2\2\u011c\u011b\3\2\2\2\u011d\u011e\3\2\2\2"+
		"\u011e\u011c\3\2\2\2\u011e\u011f\3\2\2\2\u011f\u0121\3\2\2\2\u0120\u0122"+
		"\t\6\2\2\u0121\u0120\3\2\2\2\u0122\u0123\3\2\2\2\u0123\u0121\3\2\2\2\u0123"+
		"\u0124\3\2\2\2\u0124,\3\2\2\2\24\2.\62:\u00a0\u00c9\u00cd\u00e5\u00ec"+
		"\u00f3\u00fc\u0101\u0106\u010b\u0112\u0117\u011e\u0123\4\b\2\2\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}