package ch.fhnw.cpib.compiler.scanner.token.keyword;

import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;
import ch.fhnw.cpib.compiler.scanner.Token;

public class ProcToken extends Token {

    public ProcToken() {
        super(Terminal.PROC);
    }

    @Override
    public String toString() {
        return getTerminal().toString();
    }
}
