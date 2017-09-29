package ch.fhnw.cpib.compiler.scanner;

import java.io.File;

public class Scanner {

    private final File file;

    public Scanner(File file) {
        this.file = file;
    }

    public TokenList parseFile() {
        TokenList tokenlist = new TokenList();
        return tokenlist;
    }
}
