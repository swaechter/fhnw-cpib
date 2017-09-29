package ch.fhnw.cpib.compiler.scanner.token.keyword;

import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;
import ch.fhnw.cpib.compiler.scanner.Token;

public class EndifToken extends Token {

    public EndifToken() {
        super(Terminal.ENDIF);
    }

    @Override
    public String toString() {
        return getTerminal().toString();
    }
}
