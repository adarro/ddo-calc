package io.truthencode.ddo.grammar.antlr;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.truthencode.ddo.grammar.antlr.EnchantmentsParserBaseListener;
import io.truthencode.ddo.grammar.antlr.EnchantmentsParser;


import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


class DdoItemParserTest {
    private static final Logger LOG =
        LoggerFactory.getLogger(DdoItemParserTest.class);

    /**
     * Default Listener just logs major entries / exits
     */
    class DefaultTestListener extends EnchantmentsParserBaseListener {
        private final EnchantmentsParser parser;

        public DefaultTestListener(EnchantmentsParser parser) {
            this.parser = parser;
        }

        void showTokenInfo(Token tok) {
            int id = tok.getType();
            var txt = tok.getText();
            var voc = parser.getVocabulary();
            String dn = voc.getDisplayName(id), sn = voc.getSymbolicName(id), ln = voc.getLiteralName(id);
            String sep = System.lineSeparator();

            var msg = Arrays.stream(new String[]{
                String.format("%sToken: %s%s", sep, txt, sep),
                String.format("%s: %s %s", "dn", dn, sep),
                String.format("%s: %s %s", "sn", sn, sep),
                String.format("%s: %s %s", "ln", ln, sep)}).distinct().collect(Collectors.joining());

//            var m = new HashMap<String, String>();
//            m.put("dn", String.format("%s %s %s", "dn", sep, dn));
//            m.put("sn", String.format("%s %s %s", "sn", sep, sn));
//            m.put("ln", String.format("%s %s %s", "ln", sep, ln));
//            StringBuilder sb = new StringBuilder();
//            m.forEach(k -> sb.append(k.ge));
//            String msg = String.format("dn:\t%s\rsn:\t%s\rln:\t%s", dn, sn, ln);
            LOG.info(msg);
        }

        void showLog(String tag, EnchantmentsParser.IdDeclContext ctx) {
            int idx = ctx.getRuleIndex();
            String ph = String.format(tag + "\tid:%d \tdata:%s", idx, ctx.getText());
            LOG.info(ph);

        }

        void showLog(String tag, String ctx) {
            String ph = String.format(tag + ":\t%s", ctx);
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
        public void enterParse(EnchantmentsParser.ParseContext ctx) {
            showLog("enterParse", ctx.getText());
        }

        @Override
        public void exitParse(EnchantmentsParser.ParseContext ctx) {
            showLog("exitParse", ctx.getText());
        }

        @Override
        public void enterIdDecl(EnchantmentsParser.IdDeclContext ctx) {
            showLog("enterIdDecl", ctx);
        }

        @Override
        public void exitIdDecl(EnchantmentsParser.IdDeclContext ctx) {
            showLog("exitIdDecl", ctx.getText());
        }

    }

    final static String SomeData = "Physical Sheltering +19\n" +
        "Blue Augment Slot: Empty\n" +
        "Fortification +94%\n" +
        "+5 Enhancement Bonus\n" +
        "Competence Healing Amplification +30\n" +
        "False Life +29\n" +
        "Flamecleansed Fury";


    @Test
    void canReadPercent() {
        // TODO: Refactor to inject tree listener
        assertDoesNotThrow(() -> {
            walkText(SomeData);
        });
    }

    @Test
    void canReadNumber() {
        assertDoesNotThrow(() -> {
            walkText(SomeData);
        });
    }

    void walkText(String data) {
        walkText(data, null);
    }

    void walkText(String data, ParseTreeListener listener) {

        EnchantmentsLexer lexer = new EnchantmentsLexer(CharStreams.fromString(data));

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        EnchantmentsParser parser = new EnchantmentsParser(tokens);
        ParseTreeListener l = (listener != null) ? listener : new DefaultTestListener(parser);
        ParseTree parseTree = parser.parse();
        LOG.info(String.format("parseTree has %d children", parseTree.getChildCount()));
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(l, parseTree);
    }

    @BeforeEach
    void setUp() {
    }


}