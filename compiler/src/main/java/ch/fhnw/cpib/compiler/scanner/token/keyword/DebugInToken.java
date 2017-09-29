package ch.fhnw.cpib.compiler.scanner.token.keyword;

import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;
import ch.fhnw.cpib.compiler.scanner.Token;

public class DebugInToken extends Token {

    public DebugInToken() {
        super(Terminal.DEBUGIN);
    }

    @Override
    public String toString() {
        return getTerminal().toString();
    }
}
