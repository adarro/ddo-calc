// Generated from /home/adarro/projects/ddo-calc/subprojects/common/ddo-antlr/src/main/antlr/RomanNumeralsParser.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class RomanNumeralsParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		M=1, CM=2, CD=3, D=4, C=5, CC=6, CCC=7, XC=8, XL=9, L=10, X=11, XX=12, 
		XXX=13, IV=14, V=15, IX=16, I=17, III=18;
	public static final int
		RULE_numeral = 0, RULE_thous_part = 1, RULE_hundreds = 2, RULE_hun_part = 3, 
		RULE_hun_rep = 4, RULE_tens = 5, RULE_tens_part = 6, RULE_tens_rep = 7, 
		RULE_ones = 8, RULE_ones_rep = 9;
	private static String[] makeRuleNames() {
		return new String[] {
			"numeral", "thous_part", "hundreds", "hun_part", "hun_rep", "tens", "tens_part", 
			"tens_rep", "ones", "ones_rep"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "M", "CM", "CD", "D", "C", "CC", "CCC", "XC", "XL", "L", "X", "XX", 
			"XXX", "IV", "V", "IX", "I", "III"
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
	public String getGrammarFileName() { return "RomanNumeralsParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public RomanNumeralsParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NumeralContext extends ParserRuleContext {
		public Thous_partContext thous_part() {
			return getRuleContext(Thous_partContext.class,0);
		}
		public HundredsContext hundreds() {
			return getRuleContext(HundredsContext.class,0);
		}
		public NumeralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numeral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RomanNumeralsParserListener ) ((RomanNumeralsParserListener)listener).enterNumeral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RomanNumeralsParserListener ) ((RomanNumeralsParserListener)listener).exitNumeral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RomanNumeralsParserVisitor ) return ((RomanNumeralsParserVisitor<? extends T>)visitor).visitNumeral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumeralContext numeral() throws RecognitionException {
		NumeralContext _localctx = new NumeralContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_numeral);
		try {
			setState(25);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(20);
				thous_part(0);
				setState(21);
				hundreds();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(23);
				thous_part(0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(24);
				hundreds();
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

	@SuppressWarnings("CheckReturnValue")
	public static class Thous_partContext extends ParserRuleContext {
		public TerminalNode M() { return getToken(RomanNumeralsParser.M, 0); }
		public Thous_partContext thous_part() {
			return getRuleContext(Thous_partContext.class,0);
		}
		public Thous_partContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_thous_part; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RomanNumeralsParserListener ) ((RomanNumeralsParserListener)listener).enterThous_part(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RomanNumeralsParserListener ) ((RomanNumeralsParserListener)listener).exitThous_part(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RomanNumeralsParserVisitor ) return ((RomanNumeralsParserVisitor<? extends T>)visitor).visitThous_part(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Thous_partContext thous_part() throws RecognitionException {
		return thous_part(0);
	}

	private Thous_partContext thous_part(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Thous_partContext _localctx = new Thous_partContext(_ctx, _parentState);
		Thous_partContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_thous_part, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(28);
			match(M);
			}
			_ctx.stop = _input.LT(-1);
			setState(34);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Thous_partContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_thous_part);
					setState(30);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(31);
					match(M);
					}
					} 
				}
				setState(36);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class HundredsContext extends ParserRuleContext {
		public Hun_partContext hun_part() {
			return getRuleContext(Hun_partContext.class,0);
		}
		public TensContext tens() {
			return getRuleContext(TensContext.class,0);
		}
		public HundredsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hundreds; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RomanNumeralsParserListener ) ((RomanNumeralsParserListener)listener).enterHundreds(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RomanNumeralsParserListener ) ((RomanNumeralsParserListener)listener).exitHundreds(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RomanNumeralsParserVisitor ) return ((RomanNumeralsParserVisitor<? extends T>)visitor).visitHundreds(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HundredsContext hundreds() throws RecognitionException {
		HundredsContext _localctx = new HundredsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_hundreds);
		try {
			setState(42);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(37);
				hun_part();
				setState(38);
				tens();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(40);
				hun_part();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(41);
				tens();
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

	@SuppressWarnings("CheckReturnValue")
	public static class Hun_partContext extends ParserRuleContext {
		public TerminalNode CM() { return getToken(RomanNumeralsParser.CM, 0); }
		public TerminalNode CD() { return getToken(RomanNumeralsParser.CD, 0); }
		public TerminalNode D() { return getToken(RomanNumeralsParser.D, 0); }
		public Hun_repContext hun_rep() {
			return getRuleContext(Hun_repContext.class,0);
		}
		public Hun_partContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hun_part; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RomanNumeralsParserListener ) ((RomanNumeralsParserListener)listener).enterHun_part(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RomanNumeralsParserListener ) ((RomanNumeralsParserListener)listener).exitHun_part(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RomanNumeralsParserVisitor ) return ((RomanNumeralsParserVisitor<? extends T>)visitor).visitHun_part(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Hun_partContext hun_part() throws RecognitionException {
		Hun_partContext _localctx = new Hun_partContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_hun_part);
		try {
			setState(50);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(44);
				match(CM);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(45);
				match(CD);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(46);
				match(D);
				setState(47);
				hun_rep();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(48);
				match(D);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(49);
				hun_rep();
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

	@SuppressWarnings("CheckReturnValue")
	public static class Hun_repContext extends ParserRuleContext {
		public TerminalNode C() { return getToken(RomanNumeralsParser.C, 0); }
		public TerminalNode CC() { return getToken(RomanNumeralsParser.CC, 0); }
		public TerminalNode CCC() { return getToken(RomanNumeralsParser.CCC, 0); }
		public Hun_repContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hun_rep; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RomanNumeralsParserListener ) ((RomanNumeralsParserListener)listener).enterHun_rep(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RomanNumeralsParserListener ) ((RomanNumeralsParserListener)listener).exitHun_rep(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RomanNumeralsParserVisitor ) return ((RomanNumeralsParserVisitor<? extends T>)visitor).visitHun_rep(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Hun_repContext hun_rep() throws RecognitionException {
		Hun_repContext _localctx = new Hun_repContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_hun_rep);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 224L) != 0)) ) {
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

	@SuppressWarnings("CheckReturnValue")
	public static class TensContext extends ParserRuleContext {
		public Tens_partContext tens_part() {
			return getRuleContext(Tens_partContext.class,0);
		}
		public OnesContext ones() {
			return getRuleContext(OnesContext.class,0);
		}
		public TensContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tens; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RomanNumeralsParserListener ) ((RomanNumeralsParserListener)listener).enterTens(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RomanNumeralsParserListener ) ((RomanNumeralsParserListener)listener).exitTens(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RomanNumeralsParserVisitor ) return ((RomanNumeralsParserVisitor<? extends T>)visitor).visitTens(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TensContext tens() throws RecognitionException {
		TensContext _localctx = new TensContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_tens);
		try {
			setState(59);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(54);
				tens_part();
				setState(55);
				ones();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(57);
				tens_part();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(58);
				ones();
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

	@SuppressWarnings("CheckReturnValue")
	public static class Tens_partContext extends ParserRuleContext {
		public TerminalNode XC() { return getToken(RomanNumeralsParser.XC, 0); }
		public TerminalNode XL() { return getToken(RomanNumeralsParser.XL, 0); }
		public TerminalNode L() { return getToken(RomanNumeralsParser.L, 0); }
		public Tens_repContext tens_rep() {
			return getRuleContext(Tens_repContext.class,0);
		}
		public Tens_partContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tens_part; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RomanNumeralsParserListener ) ((RomanNumeralsParserListener)listener).enterTens_part(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RomanNumeralsParserListener ) ((RomanNumeralsParserListener)listener).exitTens_part(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RomanNumeralsParserVisitor ) return ((RomanNumeralsParserVisitor<? extends T>)visitor).visitTens_part(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Tens_partContext tens_part() throws RecognitionException {
		Tens_partContext _localctx = new Tens_partContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_tens_part);
		try {
			setState(67);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(61);
				match(XC);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(62);
				match(XL);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(63);
				match(L);
				setState(64);
				tens_rep();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(65);
				match(L);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(66);
				tens_rep();
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

	@SuppressWarnings("CheckReturnValue")
	public static class Tens_repContext extends ParserRuleContext {
		public TerminalNode X() { return getToken(RomanNumeralsParser.X, 0); }
		public TerminalNode XX() { return getToken(RomanNumeralsParser.XX, 0); }
		public TerminalNode XXX() { return getToken(RomanNumeralsParser.XXX, 0); }
		public Tens_repContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tens_rep; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RomanNumeralsParserListener ) ((RomanNumeralsParserListener)listener).enterTens_rep(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RomanNumeralsParserListener ) ((RomanNumeralsParserListener)listener).exitTens_rep(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RomanNumeralsParserVisitor ) return ((RomanNumeralsParserVisitor<? extends T>)visitor).visitTens_rep(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Tens_repContext tens_rep() throws RecognitionException {
		Tens_repContext _localctx = new Tens_repContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_tens_rep);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 14336L) != 0)) ) {
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

	@SuppressWarnings("CheckReturnValue")
	public static class OnesContext extends ParserRuleContext {
		public Ones_repContext ones_rep() {
			return getRuleContext(Ones_repContext.class,0);
		}
		public TerminalNode IV() { return getToken(RomanNumeralsParser.IV, 0); }
		public TerminalNode V() { return getToken(RomanNumeralsParser.V, 0); }
		public TerminalNode IX() { return getToken(RomanNumeralsParser.IX, 0); }
		public OnesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ones; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RomanNumeralsParserListener ) ((RomanNumeralsParserListener)listener).enterOnes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RomanNumeralsParserListener ) ((RomanNumeralsParserListener)listener).exitOnes(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RomanNumeralsParserVisitor ) return ((RomanNumeralsParserVisitor<? extends T>)visitor).visitOnes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OnesContext ones() throws RecognitionException {
		OnesContext _localctx = new OnesContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_ones);
		try {
			setState(77);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(71);
				ones_rep();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(72);
				match(IV);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(73);
				match(V);
				setState(74);
				ones_rep();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(75);
				match(V);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(76);
				match(IX);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Ones_repContext extends ParserRuleContext {
		public List<TerminalNode> I() { return getTokens(RomanNumeralsParser.I); }
		public TerminalNode I(int i) {
			return getToken(RomanNumeralsParser.I, i);
		}
		public TerminalNode III() { return getToken(RomanNumeralsParser.III, 0); }
		public Ones_repContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ones_rep; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RomanNumeralsParserListener ) ((RomanNumeralsParserListener)listener).enterOnes_rep(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RomanNumeralsParserListener ) ((RomanNumeralsParserListener)listener).exitOnes_rep(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RomanNumeralsParserVisitor ) return ((RomanNumeralsParserVisitor<? extends T>)visitor).visitOnes_rep(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Ones_repContext ones_rep() throws RecognitionException {
		Ones_repContext _localctx = new Ones_repContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_ones_rep);
		try {
			setState(83);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(79);
				match(I);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(80);
				match(I);
				setState(81);
				match(I);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(82);
				match(III);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1:
			return thous_part_sempred((Thous_partContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean thous_part_sempred(Thous_partContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0012V\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0003\u0000\u001a\b\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0005\u0001!\b\u0001\n\u0001\f\u0001$\t\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002"+
		"+\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0003\u00033\b\u0003\u0001\u0004\u0001\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005<\b\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0003\u0006D\b\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b"+
		"\u0001\b\u0001\b\u0001\b\u0003\bN\b\b\u0001\t\u0001\t\u0001\t\u0001\t"+
		"\u0003\tT\b\t\u0001\t\u0000\u0001\u0002\n\u0000\u0002\u0004\u0006\b\n"+
		"\f\u000e\u0010\u0012\u0000\u0002\u0001\u0000\u0005\u0007\u0001\u0000\u000b"+
		"\r`\u0000\u0019\u0001\u0000\u0000\u0000\u0002\u001b\u0001\u0000\u0000"+
		"\u0000\u0004*\u0001\u0000\u0000\u0000\u00062\u0001\u0000\u0000\u0000\b"+
		"4\u0001\u0000\u0000\u0000\n;\u0001\u0000\u0000\u0000\fC\u0001\u0000\u0000"+
		"\u0000\u000eE\u0001\u0000\u0000\u0000\u0010M\u0001\u0000\u0000\u0000\u0012"+
		"S\u0001\u0000\u0000\u0000\u0014\u0015\u0003\u0002\u0001\u0000\u0015\u0016"+
		"\u0003\u0004\u0002\u0000\u0016\u001a\u0001\u0000\u0000\u0000\u0017\u001a"+
		"\u0003\u0002\u0001\u0000\u0018\u001a\u0003\u0004\u0002\u0000\u0019\u0014"+
		"\u0001\u0000\u0000\u0000\u0019\u0017\u0001\u0000\u0000\u0000\u0019\u0018"+
		"\u0001\u0000\u0000\u0000\u001a\u0001\u0001\u0000\u0000\u0000\u001b\u001c"+
		"\u0006\u0001\uffff\uffff\u0000\u001c\u001d\u0005\u0001\u0000\u0000\u001d"+
		"\"\u0001\u0000\u0000\u0000\u001e\u001f\n\u0002\u0000\u0000\u001f!\u0005"+
		"\u0001\u0000\u0000 \u001e\u0001\u0000\u0000\u0000!$\u0001\u0000\u0000"+
		"\u0000\" \u0001\u0000\u0000\u0000\"#\u0001\u0000\u0000\u0000#\u0003\u0001"+
		"\u0000\u0000\u0000$\"\u0001\u0000\u0000\u0000%&\u0003\u0006\u0003\u0000"+
		"&\'\u0003\n\u0005\u0000\'+\u0001\u0000\u0000\u0000(+\u0003\u0006\u0003"+
		"\u0000)+\u0003\n\u0005\u0000*%\u0001\u0000\u0000\u0000*(\u0001\u0000\u0000"+
		"\u0000*)\u0001\u0000\u0000\u0000+\u0005\u0001\u0000\u0000\u0000,3\u0005"+
		"\u0002\u0000\u0000-3\u0005\u0003\u0000\u0000./\u0005\u0004\u0000\u0000"+
		"/3\u0003\b\u0004\u000003\u0005\u0004\u0000\u000013\u0003\b\u0004\u0000"+
		"2,\u0001\u0000\u0000\u00002-\u0001\u0000\u0000\u00002.\u0001\u0000\u0000"+
		"\u000020\u0001\u0000\u0000\u000021\u0001\u0000\u0000\u00003\u0007\u0001"+
		"\u0000\u0000\u000045\u0007\u0000\u0000\u00005\t\u0001\u0000\u0000\u0000"+
		"67\u0003\f\u0006\u000078\u0003\u0010\b\u00008<\u0001\u0000\u0000\u0000"+
		"9<\u0003\f\u0006\u0000:<\u0003\u0010\b\u0000;6\u0001\u0000\u0000\u0000"+
		";9\u0001\u0000\u0000\u0000;:\u0001\u0000\u0000\u0000<\u000b\u0001\u0000"+
		"\u0000\u0000=D\u0005\b\u0000\u0000>D\u0005\t\u0000\u0000?@\u0005\n\u0000"+
		"\u0000@D\u0003\u000e\u0007\u0000AD\u0005\n\u0000\u0000BD\u0003\u000e\u0007"+
		"\u0000C=\u0001\u0000\u0000\u0000C>\u0001\u0000\u0000\u0000C?\u0001\u0000"+
		"\u0000\u0000CA\u0001\u0000\u0000\u0000CB\u0001\u0000\u0000\u0000D\r\u0001"+
		"\u0000\u0000\u0000EF\u0007\u0001\u0000\u0000F\u000f\u0001\u0000\u0000"+
		"\u0000GN\u0003\u0012\t\u0000HN\u0005\u000e\u0000\u0000IJ\u0005\u000f\u0000"+
		"\u0000JN\u0003\u0012\t\u0000KN\u0005\u000f\u0000\u0000LN\u0005\u0010\u0000"+
		"\u0000MG\u0001\u0000\u0000\u0000MH\u0001\u0000\u0000\u0000MI\u0001\u0000"+
		"\u0000\u0000MK\u0001\u0000\u0000\u0000ML\u0001\u0000\u0000\u0000N\u0011"+
		"\u0001\u0000\u0000\u0000OT\u0005\u0011\u0000\u0000PQ\u0005\u0011\u0000"+
		"\u0000QT\u0005\u0011\u0000\u0000RT\u0005\u0012\u0000\u0000SO\u0001\u0000"+
		"\u0000\u0000SP\u0001\u0000\u0000\u0000SR\u0001\u0000\u0000\u0000T\u0013"+
		"\u0001\u0000\u0000\u0000\b\u0019\"*2;CMS";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}