package ch.fhnw.cpib.compiler.scanner.token.sentinel;

import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;

public class SentinelToken extends Token {

    public SentinelToken() {
        super(Terminal.SENTINEL);
    }

    @Override
    public String toString() {
        return getTerminal().toString();
    }
}
