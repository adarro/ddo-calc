/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2025
 *
 * Author: Andre White.
 * FILE: EnchantmentParserTest.kt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.truthencode.ddo.grammar.antlr

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.equals.shouldBeEqual
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker
import org.slf4j.LoggerFactory

@Suppress("TOO_MANY_LINES_IN_LAMBDA", "PARAMETER_NAME_IN_OUTER_LAMBDA")
class EnchantmentParserTest :
    DescribeSpec({
        val logger = LoggerFactory.getLogger(this::class.java.canonicalName)

        class EnchantmentListener : EnchantmentsParserBaseListener() {
            override fun enterSlug(ctx: EnchantmentsParser.SlugContext?) {
                super.enterSlug(ctx)
                logger.info("Slug: ${ctx?.text}")
            }
        }

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

        describe("Enchantment Parser") {
            it("Instantiates") {
                val lexer = EnchantmentsLexer(CharStreams.fromString(data))
                val tokens = CommonTokenStream(lexer)
                val ep = EnchantmentsParser(tokens)
                val walker = ParseTreeWalker()

                walker.walk(EnchantmentListener(), ep.parseEnchantment())
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
                it("Should negate one score") { true shouldBeEqual !false }
            }
        }
    })
