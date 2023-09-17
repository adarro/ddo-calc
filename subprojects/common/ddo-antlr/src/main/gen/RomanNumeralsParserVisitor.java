// Generated from /home/adarro/projects/ddo-calc/subprojects/common/ddo-antlr/src/main/antlr/RomanNumeralsParser.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link RomanNumeralsParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface RomanNumeralsParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link RomanNumeralsParser#numeral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumeral(RomanNumeralsParser.NumeralContext ctx);
	/**
	 * Visit a parse tree produced by {@link RomanNumeralsParser#thous_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThous_part(RomanNumeralsParser.Thous_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link RomanNumeralsParser#hundreds}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHundreds(RomanNumeralsParser.HundredsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RomanNumeralsParser#hun_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHun_part(RomanNumeralsParser.Hun_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link RomanNumeralsParser#hun_rep}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHun_rep(RomanNumeralsParser.Hun_repContext ctx);
	/**
	 * Visit a parse tree produced by {@link RomanNumeralsParser#tens}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTens(RomanNumeralsParser.TensContext ctx);
	/**
	 * Visit a parse tree produced by {@link RomanNumeralsParser#tens_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTens_part(RomanNumeralsParser.Tens_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link RomanNumeralsParser#tens_rep}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTens_rep(RomanNumeralsParser.Tens_repContext ctx);
	/**
	 * Visit a parse tree produced by {@link RomanNumeralsParser#ones}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOnes(RomanNumeralsParser.OnesContext ctx);
	/**
	 * Visit a parse tree produced by {@link RomanNumeralsParser#ones_rep}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOnes_rep(RomanNumeralsParser.Ones_repContext ctx);
}