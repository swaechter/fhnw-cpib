package ch.fhnw.cpib.platform;

import ch.fhnw.cpib.platform.parser.Parser;
import ch.fhnw.cpib.platform.parser.concrete.ConcreteTree;
import ch.fhnw.cpib.platform.parser.exception.ParserException;
import ch.fhnw.cpib.platform.scanner.Scanner;
import ch.fhnw.cpib.platform.scanner.exception.ScannerException;
import ch.fhnw.cpib.platform.scanner.tokens.TokenList;

public class Compiler {

    private final Scanner scanner;

    private final Parser parser;

    public Compiler() {
        this.scanner = new Scanner();
        this.parser = new Parser();
    }

    public void compileString(String content) {
        try {
            // Show the file path
            System.out.println("Scanning content: " + content);
            System.out.println();

            // Scan the source code and show the token list
            TokenList tokenlist = scanner.scanString(content);
            System.out.println("Scanned token list: " + tokenlist.toString());
            System.out.println();

            // Parse the concrete tree and show it
            ConcreteTree.Program program = parser.parseTokenList(tokenlist);
            System.out.println("Concrete parsing tree: " + program);

            System.out.println();
        } catch (ScannerException exception) {
            System.out.println("During the scanning process, an error occurred: " + exception.getMessage());
            System.exit(1);
        } catch (ParserException exception) {
            System.out.println("During the parsing process, an error occurred: " + exception.getMessage());
            System.exit(1);
        }
    }
}
