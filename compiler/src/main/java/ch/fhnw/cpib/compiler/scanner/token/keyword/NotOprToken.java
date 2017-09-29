package ch.fhnw.cpib.compiler.scanner.token.keyword;

import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;
import ch.fhnw.cpib.compiler.scanner.Token;

public class NotOprToken extends Token {

    public NotOprToken() {
        super(Terminal.NOTOPR);
    }

    @Override
    public String toString() {
        return getTerminal().toString();
    }
}
