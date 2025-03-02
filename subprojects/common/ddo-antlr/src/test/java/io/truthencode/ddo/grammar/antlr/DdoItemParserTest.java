/*
SPDX-License-Identifier: Apache-2.0

Copyright 2025 ${author}.

FILE: DdoItemParserTest.java

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package io.truthencode.ddo.grammar.antlr;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.stream.Collectors;

class DdoItemParserTest {
    private static final Logger LOG = LoggerFactory.getLogger(DdoItemParserTest.class);

    /** Default Listener just logs major entries / exits */
    static class DefaultTestListener
            extends io.truthencode.ddo.grammar.antlr.EnchantmentsParserBaseListener {
        private final io.truthencode.ddo.grammar.antlr.EnchantmentsParser parser;

        public DefaultTestListener(io.truthencode.ddo.grammar.antlr.EnchantmentsParser parser) {
            this.parser = parser;
        }

        void showTokenInfo(Token tok) {
            int id = tok.getType();
            var txt = tok.getText();
            var voc = parser.getVocabulary();
            String dn = voc.getDisplayName(id),
                    sn = voc.getSymbolicName(id),
                    ln = voc.getLiteralName(id);
            String sep = System.lineSeparator();

            var msg =
                    Arrays.stream(
                                    new String[] {
                                        String.format("%sToken: %s%s", sep, txt, sep),
                                        String.format("%s: %s %s", "dn", dn, sep),
                                        String.format("%s: %s %s", "sn", sn, sep),
                                        String.format("%s: %s %s", "ln", ln, sep)
                                    })
                            .distinct()
                            .collect(Collectors.joining());

            //            var m = new HashMap<String, String>();
            //            m.put("dn", String.format("%s %s %s", "dn", sep, dn));
            //            m.put("sn", String.format("%s %s %s", "sn", sep, sn));
            //            m.put("ln", String.format("%s %s %s", "ln", sep, ln));
            //            StringBuilder sb = new StringBuilder();
            //            m.forEach(k -> sb.append(k.ge));
            //            String msg = String.format("dn:\t%s\rsn:\t%s\rln:\t%s", dn, sn, ln);
            LOG.info(msg);
        }

        void showLog(
                String tag, io.truthencode.ddo.grammar.antlr.EnchantmentsParser.IdDeclContext ctx) {
            int idx = ctx.getRuleIndex();
            String ph = String.format("%s\tid:%d \tdata:%s", tag, idx, ctx.getText());
            LOG.info(ph);
        }

        void showLog(String tag, String ctx) {
            String ph = String.format("%s:\t%s", tag, ctx);
            LOG.info(ph);
        }

        @Override
        public void visitTerminal(TerminalNode node) {
            showLog("visit terminal node", node.getText());
            var sym = node.getSymbol();

            int iType = sym.getType();

            LOG.info(String.format("node symbol %s\ttype:%d\ttext:%s", sym, iType, sym.getText()));
            showTokenInfo(sym);
        }

        @Override
        public void visitErrorNode(ErrorNode node) {
            showLog("visit error node", node.getText());
        }

        @Override
        public void enterEveryRule(ParserRuleContext ctx) {
            showLog("enter rule", ctx.getText());
        }

        @Override
        public void exitEveryRule(ParserRuleContext ctx) {
            showLog("exit rule", ctx.getText());
        }

        @Override
        public void enterParseEnchantment(EnchantmentsParser.ParseEnchantmentContext ctx) {
            showLog("enterParse", ctx.getText());
        }

        @Override
        public void exitParseEnchantment(EnchantmentsParser.ParseEnchantmentContext ctx) {
            showLog("exitParse", ctx.getText());
        }

        @Override
        public void enterIdDecl(
                io.truthencode.ddo.grammar.antlr.EnchantmentsParser.IdDeclContext ctx) {
            showLog("enterIdDecl", ctx);
        }

        @Override
        public void exitIdDecl(
                io.truthencode.ddo.grammar.antlr.EnchantmentsParser.IdDeclContext ctx) {
            showLog("exitIdDecl", ctx.getText());
        }
    }

    static final String SAMPLE_DATA =
            """
            Physical Sheltering +19
            Blue Augment Slot: Empty
            Fortification +94%%
            +5 Enhancement Bonus
            Competence Healing Amplification +30
            False Life +29
            Flamecleansed Fury\
            """;

    @Test
    void canReadPercent() {
        // TODO: Refactor to inject tree listener
        assertDoesNotThrow(
                () -> {
                    walkText(SAMPLE_DATA);
                });
    }

    @Test
    void canReadNumber() {
        assertDoesNotThrow(
                () -> {
                    walkText(SAMPLE_DATA);
                });
    }

    void walkText(String data) {
        walkText(data, null);
    }

    void walkText(String data, ParseTreeListener listener) {

        io.truthencode.ddo.grammar.antlr.EnchantmentsLexer lexer =
                new io.truthencode.ddo.grammar.antlr.EnchantmentsLexer(
                        CharStreams.fromString(data));

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        io.truthencode.ddo.grammar.antlr.EnchantmentsParser parser =
                new io.truthencode.ddo.grammar.antlr.EnchantmentsParser(tokens);
        ParseTreeListener l = (listener != null) ? listener : new DefaultTestListener(parser);
        ParseTree parseTree = parser.parseEnchantment();
        LOG.info(String.format("parseTree has %d children", parseTree.getChildCount()));
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(l, parseTree);
    }

    @BeforeEach
    void setUp() {}
}
