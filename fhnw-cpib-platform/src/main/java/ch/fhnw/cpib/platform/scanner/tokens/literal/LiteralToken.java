package ch.fhnw.cpib.platform.scanner.tokens.literal;

import ch.fhnw.cpib.platform.scanner.terminal.Terminal;
import ch.fhnw.cpib.platform.scanner.tokens.Token;

public class LiteralToken extends Token {

    private final String value;

    public LiteralToken(String value, Terminal terminal) {
        super(terminal);
        this.value = value;
    }

    @Override
    public String toString() {
        return "(" + super.toString() + "," + value + ")";
    }
}
