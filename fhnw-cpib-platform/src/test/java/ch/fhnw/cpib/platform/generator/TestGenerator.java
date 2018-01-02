package ch.fhnw.cpib.platform.generator;

import ch.fhnw.cpib.platform.TestFiles;
import ch.fhnw.cpib.platform.checker.Checker;
import ch.fhnw.cpib.platform.parser.Parser;
import ch.fhnw.cpib.platform.parser.abstracttree.AbstractTree;
import ch.fhnw.cpib.platform.parser.concretetree.ConcreteTree;
import ch.fhnw.cpib.platform.scanner.Scanner;
import ch.fhnw.cpib.platform.scanner.tokens.TokenList;
import ch.fhnw.cpib.platform.utils.ReaderUtils;
import org.javatuples.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class TestGenerator {

    @Test
    public void testGenerator() throws Exception {
        // Create the scanner, parser and generator
        Scanner scanner = new Scanner();
        Parser parser = new Parser();
        Generator generator = new Generator();

        // Parse the files
        for (String filename : TestFiles.generatorfilenames) {
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

            // Check the abstract tree
            abstractprogram.check(new Checker());

            // Generate the Jasmin file
            String jasmincontent = generator.generateJasminContent(abstractprogram);
            Assert.assertTrue(jasmincontent.length() > 0);
            //System.out.println(jasmincontent);

            // Generate the Java JAR file
            File javaclassfile = generator.generateJarFile(abstractprogram, jasmincontent);
            Assert.assertTrue(javaclassfile.exists());

            // Execute the Java JAR file
            Pair<String, String> output = generator.executeJarFile(javaclassfile);
            //System.out.println("Regular Output:");
            //System.out.println(output.getValue0());
            //System.out.println();
            //System.out.println("Error Output:");
            //System.out.println(output.getValue1());
            //System.out.println();
            Assert.assertTrue(output.getValue0().contains("42"));
        }
    }
}
