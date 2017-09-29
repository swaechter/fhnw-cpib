package ch.fhnw.cpib.compiler.scanner.token.keyword;

import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;
import ch.fhnw.cpib.compiler.scanner.Token;

public class ElseToken extends Token {

    public ElseToken() {
        super(Terminal.ELSE);
    }

    @Override
    public String toString() {
        return getTerminal().toString();
    }
}
