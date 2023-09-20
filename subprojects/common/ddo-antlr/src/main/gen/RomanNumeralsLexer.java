// Generated from /home/adarro/projects/ddo-calc/subprojects/common/ddo-antlr/src/main/antlr/RomanNumeralsLexer.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class RomanNumeralsLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		M=1, CD=2, D=3, CM=4, C=5, CC=6, CCC=7, XL=8, L=9, XC=10, X=11, XX=12, 
		XXX=13, IV=14, V=15, IX=16, I=17, II=18, III=19;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"M", "CD", "D", "CM", "C", "CC", "CCC", "XL", "L", "XC", "X", "XX", "XXX", 
			"IV", "V", "IX", "I", "II", "III"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'M'", "'CD'", "'D'", "'CM'", "'C'", "'CC'", "'CCC'", "'XL'", "'L'", 
			"'XC'", "'X'", "'XX'", "'XXX'", "'IV'", "'V'", "'IX'", "'I'", "'II'", 
			"'III'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "M", "CD", "D", "CM", "C", "CC", "CCC", "XL", "L", "XC", "X", "XX", 
			"XXX", "IV", "V", "IX", "I", "II", "III"
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


	public RomanNumeralsLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "RomanNumeralsLexer.g4"; }

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
		"\u0004\u0000\u0013\\\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001"+
		"\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001"+
		"\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0000\u0000\u0013\u0001\u0001\u0003\u0002\u0005\u0003"+
		"\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015"+
		"\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012"+
		"%\u0013\u0001\u0000\u0000[\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003"+
		"\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007"+
		"\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001"+
		"\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000"+
		"\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000"+
		"\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000"+
		"\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000"+
		"\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000"+
		"\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000"+
		"\u0000%\u0001\u0000\u0000\u0000\u0001\'\u0001\u0000\u0000\u0000\u0003"+
		")\u0001\u0000\u0000\u0000\u0005,\u0001\u0000\u0000\u0000\u0007.\u0001"+
		"\u0000\u0000\u0000\t1\u0001\u0000\u0000\u0000\u000b3\u0001\u0000\u0000"+
		"\u0000\r6\u0001\u0000\u0000\u0000\u000f:\u0001\u0000\u0000\u0000\u0011"+
		"=\u0001\u0000\u0000\u0000\u0013?\u0001\u0000\u0000\u0000\u0015B\u0001"+
		"\u0000\u0000\u0000\u0017D\u0001\u0000\u0000\u0000\u0019G\u0001\u0000\u0000"+
		"\u0000\u001bK\u0001\u0000\u0000\u0000\u001dN\u0001\u0000\u0000\u0000\u001f"+
		"P\u0001\u0000\u0000\u0000!S\u0001\u0000\u0000\u0000#U\u0001\u0000\u0000"+
		"\u0000%X\u0001\u0000\u0000\u0000\'(\u0005M\u0000\u0000(\u0002\u0001\u0000"+
		"\u0000\u0000)*\u0005C\u0000\u0000*+\u0005D\u0000\u0000+\u0004\u0001\u0000"+
		"\u0000\u0000,-\u0005D\u0000\u0000-\u0006\u0001\u0000\u0000\u0000./\u0005"+
		"C\u0000\u0000/0\u0005M\u0000\u00000\b\u0001\u0000\u0000\u000012\u0005"+
		"C\u0000\u00002\n\u0001\u0000\u0000\u000034\u0005C\u0000\u000045\u0005"+
		"C\u0000\u00005\f\u0001\u0000\u0000\u000067\u0005C\u0000\u000078\u0005"+
		"C\u0000\u000089\u0005C\u0000\u00009\u000e\u0001\u0000\u0000\u0000:;\u0005"+
		"X\u0000\u0000;<\u0005L\u0000\u0000<\u0010\u0001\u0000\u0000\u0000=>\u0005"+
		"L\u0000\u0000>\u0012\u0001\u0000\u0000\u0000?@\u0005X\u0000\u0000@A\u0005"+
		"C\u0000\u0000A\u0014\u0001\u0000\u0000\u0000BC\u0005X\u0000\u0000C\u0016"+
		"\u0001\u0000\u0000\u0000DE\u0005X\u0000\u0000EF\u0005X\u0000\u0000F\u0018"+
		"\u0001\u0000\u0000\u0000GH\u0005X\u0000\u0000HI\u0005X\u0000\u0000IJ\u0005"+
		"X\u0000\u0000J\u001a\u0001\u0000\u0000\u0000KL\u0005I\u0000\u0000LM\u0005"+
		"V\u0000\u0000M\u001c\u0001\u0000\u0000\u0000NO\u0005V\u0000\u0000O\u001e"+
		"\u0001\u0000\u0000\u0000PQ\u0005I\u0000\u0000QR\u0005X\u0000\u0000R \u0001"+
		"\u0000\u0000\u0000ST\u0005I\u0000\u0000T\"\u0001\u0000\u0000\u0000UV\u0005"+
		"I\u0000\u0000VW\u0005I\u0000\u0000W$\u0001\u0000\u0000\u0000XY\u0005I"+
		"\u0000\u0000YZ\u0005I\u0000\u0000Z[\u0005I\u0000\u0000[&\u0001\u0000\u0000"+
		"\u0000\u0001\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}