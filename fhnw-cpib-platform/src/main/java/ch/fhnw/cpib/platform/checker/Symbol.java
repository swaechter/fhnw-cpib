package ch.fhnw.cpib.platform.checker;

import ch.fhnw.cpib.platform.scanner.tokens.Tokens;

public class Symbol {
    private String identifier;
    private Tokens.TypeToken.Type type;

    public Symbol(String identifier, Tokens.TypeToken.Type type) {
        this.identifier = identifier;
        this.type = type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Tokens.TypeToken.Type getType() {
        return type;
    }
}
