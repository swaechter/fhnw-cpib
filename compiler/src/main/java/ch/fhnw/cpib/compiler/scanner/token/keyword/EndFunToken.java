package ch.fhnw.cpib.compiler.scanner.token.keyword;

import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;
import ch.fhnw.cpib.compiler.scanner.Token;

public class EndFunToken extends Token {

    public EndFunToken() {
        super(Terminal.ENDFUN);
    }

    @Override
    public String toString() {
        return getTerminal().toString();
    }
}
