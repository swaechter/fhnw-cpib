package ch.fhnw.cpib.compiler.scanner.token.symbol;

import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;

public class SemicolonToken extends Token {

    public SemicolonToken() {
        super(Terminal.SEMICOLON);
    }

    @Override
    public String toString() {
        return getTerminal().toString();
    }
}
