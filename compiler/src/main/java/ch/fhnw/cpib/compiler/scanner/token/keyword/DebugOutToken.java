package ch.fhnw.cpib.compiler.scanner.token.keyword;

import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;
import ch.fhnw.cpib.compiler.scanner.Token;

public class DebugOutToken extends Token {

    public DebugOutToken() {
        super(Terminal.DEBUGOUT);
    }

    @Override
    public String toString() {
        return getTerminal().toString();
    }
}
