package ch.fhnw.cpib.platform.parser;

import ch.fhnw.cpib.platform.TestFiles;
import ch.fhnw.cpib.platform.parser.abstracttree.AbstractTree;
import ch.fhnw.cpib.platform.parser.concretetree.ConcreteTree;
import ch.fhnw.cpib.platform.scanner.Scanner;
import ch.fhnw.cpib.platform.scanner.tokens.TokenList;
import ch.fhnw.cpib.platform.utils.ReaderUtils;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

public class ParserTest {

    @Test
    public void testConcreteParser() throws Exception {
        // Create the scanner and parser
        Scanner scanner = new Scanner();
        Parser parser = new Parser();

        // Parse the files
        for (String filename : TestFiles.filenames) {
            // Load the program
            String content = ReaderUtils.getContentFromInputStream(getClass().getResourceAsStream(filename), StandardCharsets.UTF_8);
            Assert.assertFalse(content.isEmpty());

            // Scan the program
            TokenList tokenlist = scanner.scanString(content);
            Assert.assertTrue(tokenlist.getSize() > 0);

            // Parse the token list
            ConcreteTree.Program program = parser.parseTokenList(tokenlist);
            Assert.assertTrue(program.toString().length() > 0);
        }
    }

    @Test
    public void testAbstractParser() throws Exception {
        // Create the scanner and parser
        Scanner scanner = new Scanner();
        Parser parser = new Parser();

        // Parse the files
        for (String filename : TestFiles.filenames) {
            // Load the program
            String content = ReaderUtils.getContentFromInputStream(getClass().getResourceAsStream(filename), StandardCharsets.UTF_8);
            Assert.assertFalse(content.isEmpty());

            // Scan the program
            TokenList tokenlist = scanner.scanString(content);
            Assert.assertTrue(tokenlist.getSize() > 0);

            // Parse the token list
            ConcreteTree.Program concreteprogram = parser.parseTokenList(tokenlist);
            Assert.assertTrue(concreteprogram.toString().length() > 0);

            // Make the parse tree abstract
            AbstractTree.Program abstractprogram = concreteprogram.toAbstract();
            //System.out.println(abstractprogram);

            // Check the abstract tree
            abstractprogram.check();
        }
    }
}
