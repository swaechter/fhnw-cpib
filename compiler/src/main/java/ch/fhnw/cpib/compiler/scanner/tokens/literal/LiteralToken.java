package ch.fhnw.cpib.compiler.scanner.tokens.literal;

import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;
import ch.fhnw.cpib.compiler.scanner.tokens.Token;

public class LiteralToken extends Token {

    public LiteralToken(String name, Terminal terminal) {
        super(name, terminal);
    }

    @Override
    public String toString() {
        return "(" + super.toString() + "," + getSymbol() + ")";
    }
}
