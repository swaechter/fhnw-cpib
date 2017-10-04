package ch.fhnw.cpib.compiler;

import ch.fhnw.cpib.compiler.scanner.Scanner;
import ch.fhnw.cpib.compiler.scanner.tokens.TokenList;
import ch.fhnw.cpib.compiler.scanner.exception.ScannerException;
import ch.fhnw.cpib.compiler.scanner.tokens.Token;

import java.io.File;

public class Compiler {

    private final Scanner scanner;

    public Compiler() {
        this.scanner = new Scanner();
    }

    public void compileFile(File file) {
        try {
            Token token;
            TokenList tokenlist = scanner.parseFile(file);
            while ((token = tokenlist.nextToken()) != null) {
                System.out.println(token.toString());
            }
        } catch (ScannerException exception) {
            System.out.println(exception.getMessage());
            System.exit(1);
        }
    }
}
