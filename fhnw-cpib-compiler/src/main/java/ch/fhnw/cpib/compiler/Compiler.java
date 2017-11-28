package ch.fhnw.cpib.compiler;

import ch.fhnw.cpib.compiler.parser.Parser;
import ch.fhnw.cpib.compiler.parser.exception.ParserException;
import ch.fhnw.cpib.compiler.scanner.Scanner;
import ch.fhnw.cpib.compiler.scanner.exception.ScannerException;
import ch.fhnw.cpib.compiler.scanner.tokens.Token;
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
            Token token;
            TokenList tokenlist = scanner.scanFile(file);
            while ((token = tokenlist.nextToken()) != null) {
                System.out.println(token.toString());
            }

            tokenlist.resetCounter();
            parser.parseTokenList(tokenlist);
        } catch (ScannerException exception) {
            System.out.println("During the scanning process, an error occurred: " + exception.getMessage());
            System.exit(1);
        } catch (ParserException exception) {
            System.out.println("During the parsing process, an error occurred: " + exception.getMessage());
            System.exit(1);
        }
    }
}
