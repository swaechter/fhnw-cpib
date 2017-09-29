package ch.fhnw.cpib.compiler.scanner.token.symbol;

import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;

public class RParenToken extends Token {

    public RParenToken() {
        super(Terminal.RPAREN);
    }

    @Override
    public String toString() {
        return "TODO";
    }
}
