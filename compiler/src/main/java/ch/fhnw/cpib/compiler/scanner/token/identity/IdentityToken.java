package ch.fhnw.cpib.compiler.scanner.token.identity;

import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;

public class IdentityToken extends Token {

    private final String name;

    public IdentityToken(String name) {
        super(Terminal.IDENT);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "(" + Terminal.IDENT + ", " + name.toString() + ")";
    }
}
