package ch.fhnw.cpib.compiler.scanner.token.symbol;

import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;

public class CommaToken extends Token {

    public CommaToken() {
        super(Terminal.COMMA);
    }

    @Override
    public String toString() {
        return "TODO";
    }
}
