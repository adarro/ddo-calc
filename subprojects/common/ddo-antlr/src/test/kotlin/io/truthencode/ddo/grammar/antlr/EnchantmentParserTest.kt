package io.truthencode.ddo.grammar.antlr

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.equals.shouldBeEqual
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker

class EnchantmentParserTest :
    DescribeSpec({

        val data =
            """
            Physical Sheltering +41
            Super Extra Long Multiword -23
            Colorless Augment Slot: Empty
            Fortification +94%
            +5 Enhancement Bonus
            Competence Healing Amplification +30
            False Life +29
            Flamecleansed Fury
            Physical and Magical Sheltering +2
            Unknown -4
            """.trimIndent()

        class EnchantmentListener : EnchantmentsParserBaseListener() {
            override fun enterSlug(ctx: EnchantmentsParser.SlugContext?) {
                super.enterSlug(ctx)
                println("Slug: ${ctx?.text}")
            }
        }

        describe("Enchantment Parser") {
            it("Instantiates") {
                val lexer = EnchantmentsLexer(CharStreams.fromString(data))
                val tokens = CommonTokenStream(lexer)
                val p = EnchantmentsParser(tokens)
                val walker = ParseTreeWalker()

                walker.walk(EnchantmentListener(), p.parseEnchantment())
            }
            describe("with a strike") {
                it("adds ten") {
                    // test here
                }
                it("carries strike to the next frame") {
                    // test here
                }
            }

            describe("for the opposite team") {
                it("Should negate one score") {
                    true shouldBeEqual !false
                }
            }
        }
    })