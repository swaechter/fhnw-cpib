package ch.fhnw.cpib.compiler;

import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.parser.exception.ParserException;
import ch.fhnw.cpib.compiler.scanner.Scanner;
import ch.fhnw.cpib.compiler.scanner.exception.ScannerException;
import ch.fhnw.cpib.compiler.scanner.tokens.TokenList;

import java.io.File;

public class Compiler {

    private final Scanner scanner;

    private final Parser parser;

    public Compiler() {
        this.scanner = new Scanner();
        this.parser = new Parser();
    }

    public void compileFile(File file) {
        try {
            // Show the file path
            System.out.println("Scanning file: " + file.getAbsolutePath());
            System.out.println();

            // Scan the source code and show the token list
            TokenList tokenlist = scanner.scanFile(file);
            System.out.println("Scanned token list: " + tokenlist.toString());
            System.out.println();

            // Parse the concrete tree and show it
            parser.parseTokenList(tokenlist);
            System.out.println("Parsed concrete tree: Not implemented yet!");
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
