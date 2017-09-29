package ch.fhnw.cpib.compiler.scanner.token.keyword;

import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;
import ch.fhnw.cpib.compiler.scanner.Token;

public class DoToken extends Token {

    public DoToken() {
        super(Terminal.DO);
    }

    @Override
    public String toString() {
        return getTerminal().toString();
    }
}
