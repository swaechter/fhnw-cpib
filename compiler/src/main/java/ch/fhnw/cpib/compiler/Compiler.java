package ch.fhnw.cpib.compiler;

import ch.fhnw.cpib.compiler.scanner.Scanner;
import ch.fhnw.cpib.compiler.scanner.TokenList;
import ch.fhnw.cpib.compiler.scanner.Token;

import java.io.File;

public class Compiler {

    public void compileFile(File file) {
        Scanner scanner = new Scanner(file);

        Token token;
        TokenList tokenlist = scanner.parseFile();
        while ((token = tokenlist.nextToken()) != null) {
            System.out.println(token.toString());
        }
    }
}
