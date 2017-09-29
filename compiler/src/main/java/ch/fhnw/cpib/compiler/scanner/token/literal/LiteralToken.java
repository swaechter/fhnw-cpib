package ch.fhnw.cpib.compiler.scanner.token.literal;

import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;

public class LiteralToken extends Token {

    private final String name;

    public LiteralToken(String name) {
        super(Terminal.LITERAL);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "TODO";
    }
}
