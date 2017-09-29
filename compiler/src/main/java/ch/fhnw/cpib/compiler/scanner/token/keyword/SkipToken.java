package ch.fhnw.cpib.compiler.scanner.token.keyword;

import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;

public class SkipToken extends Token {

    public SkipToken() {
        super(Terminal.SKIP);
    }

    @Override
    public String toString() {
        return "TODO";
    }
}
