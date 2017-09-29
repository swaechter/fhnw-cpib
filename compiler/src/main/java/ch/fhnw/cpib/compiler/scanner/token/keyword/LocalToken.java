package ch.fhnw.cpib.compiler.scanner.token.keyword;

import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;
import ch.fhnw.cpib.compiler.scanner.Token;

public class LocalToken extends Token {

    public LocalToken() {
        super(Terminal.LOCAL);
    }

    @Override
    public String toString() {
        return "TODO";
    }
}
