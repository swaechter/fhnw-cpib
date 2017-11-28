package ch.fhnw.cpib.compiler.scanner.tokens.identifier;

import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;
import ch.fhnw.cpib.compiler.scanner.tokens.Token;

public class IdentifierToken extends Token {

    private final String name;

    public IdentifierToken(String name, Terminal terminal) {
        super(terminal);
        this.name = name;
    }

    @Override
    public String toString() {
        return "(" + super.toString() + "," + name + ")";
    }
}
