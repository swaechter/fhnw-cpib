package ch.fhnw.cpib.compiler.scanner.token.symbol;

import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;

public class LParenToken extends Token {

    public LParenToken() {
        super(Terminal.LPAREN);
    }

    @Override
    public String toString() {
        return "TODO";
    }
}
