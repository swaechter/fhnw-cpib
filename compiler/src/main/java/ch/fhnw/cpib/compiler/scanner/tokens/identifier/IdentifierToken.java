package ch.fhnw.cpib.compiler.scanner.tokens.identifier;

import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;
import ch.fhnw.cpib.compiler.scanner.tokens.Token;

public class IdentifierToken extends Token {

    public IdentifierToken(String name, Terminal terminal) {
        super(name, terminal);
    }

    @Override
    public String toString() {
        return "(" + super.toString() + "," + getSymbol() + ")";
    }
}
