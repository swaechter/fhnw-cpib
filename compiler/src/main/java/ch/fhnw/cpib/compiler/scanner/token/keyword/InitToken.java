package ch.fhnw.cpib.compiler.scanner.token.keyword;

import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;
import ch.fhnw.cpib.compiler.scanner.Token;

public class InitToken extends Token {

    public InitToken() {
        super(Terminal.INIT);
    }

    @Override
    public String toString() {
        return getTerminal().toString();
    }
}
