// Generated from /home/adarro/projects/ddo-calc/subprojects/common/ddo-antlr/src/main/antlr/RomanNumeralsParser.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RomanNumeralsParser}.
 */
public interface RomanNumeralsParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link RomanNumeralsParser#numeral}.
	 * @param ctx the parse tree
	 */
	void enterNumeral(RomanNumeralsParser.NumeralContext ctx);
	/**
	 * Exit a parse tree produced by {@link RomanNumeralsParser#numeral}.
	 * @param ctx the parse tree
	 */
	void exitNumeral(RomanNumeralsParser.NumeralContext ctx);
	/**
	 * Enter a parse tree produced by {@link RomanNumeralsParser#thous_part}.
	 * @param ctx the parse tree
	 */
	void enterThous_part(RomanNumeralsParser.Thous_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link RomanNumeralsParser#thous_part}.
	 * @param ctx the parse tree
	 */
	void exitThous_part(RomanNumeralsParser.Thous_partContext ctx);
	/**
	 * Enter a parse tree produced by {@link RomanNumeralsParser#hundreds}.
	 * @param ctx the parse tree
	 */
	void enterHundreds(RomanNumeralsParser.HundredsContext ctx);
	/**
	 * Exit a parse tree produced by {@link RomanNumeralsParser#hundreds}.
	 * @param ctx the parse tree
	 */
	void exitHundreds(RomanNumeralsParser.HundredsContext ctx);
	/**
	 * Enter a parse tree produced by {@link RomanNumeralsParser#hun_part}.
	 * @param ctx the parse tree
	 */
	void enterHun_part(RomanNumeralsParser.Hun_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link RomanNumeralsParser#hun_part}.
	 * @param ctx the parse tree
	 */
	void exitHun_part(RomanNumeralsParser.Hun_partContext ctx);
	/**
	 * Enter a parse tree produced by {@link RomanNumeralsParser#hun_rep}.
	 * @param ctx the parse tree
	 */
	void enterHun_rep(RomanNumeralsParser.Hun_repContext ctx);
	/**
	 * Exit a parse tree produced by {@link RomanNumeralsParser#hun_rep}.
	 * @param ctx the parse tree
	 */
	void exitHun_rep(RomanNumeralsParser.Hun_repContext ctx);
	/**
	 * Enter a parse tree produced by {@link RomanNumeralsParser#tens}.
	 * @param ctx the parse tree
	 */
	void enterTens(RomanNumeralsParser.TensContext ctx);
	/**
	 * Exit a parse tree produced by {@link RomanNumeralsParser#tens}.
	 * @param ctx the parse tree
	 */
	void exitTens(RomanNumeralsParser.TensContext ctx);
	/**
	 * Enter a parse tree produced by {@link RomanNumeralsParser#tens_part}.
	 * @param ctx the parse tree
	 */
	void enterTens_part(RomanNumeralsParser.Tens_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link RomanNumeralsParser#tens_part}.
	 * @param ctx the parse tree
	 */
	void exitTens_part(RomanNumeralsParser.Tens_partContext ctx);
	/**
	 * Enter a parse tree produced by {@link RomanNumeralsParser#tens_rep}.
	 * @param ctx the parse tree
	 */
	void enterTens_rep(RomanNumeralsParser.Tens_repContext ctx);
	/**
	 * Exit a parse tree produced by {@link RomanNumeralsParser#tens_rep}.
	 * @param ctx the parse tree
	 */
	void exitTens_rep(RomanNumeralsParser.Tens_repContext ctx);
	/**
	 * Enter a parse tree produced by {@link RomanNumeralsParser#ones}.
	 * @param ctx the parse tree
	 */
	void enterOnes(RomanNumeralsParser.OnesContext ctx);
	/**
	 * Exit a parse tree produced by {@link RomanNumeralsParser#ones}.
	 * @param ctx the parse tree
	 */
	void exitOnes(RomanNumeralsParser.OnesContext ctx);
	/**
	 * Enter a parse tree produced by {@link RomanNumeralsParser#ones_rep}.
	 * @param ctx the parse tree
	 */
	void enterOnes_rep(RomanNumeralsParser.Ones_repContext ctx);
	/**
	 * Exit a parse tree produced by {@link RomanNumeralsParser#ones_rep}.
	 * @param ctx the parse tree
	 */
	void exitOnes_rep(RomanNumeralsParser.Ones_repContext ctx);
}