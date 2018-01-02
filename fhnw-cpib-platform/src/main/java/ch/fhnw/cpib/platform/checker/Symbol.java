package ch.fhnw.cpib.platform.checker;

import ch.fhnw.cpib.platform.scanner.tokens.Tokens;

public class Symbol {

    private String identifier;

    private Tokens.TypeToken type;

    public Symbol(String identifier, Tokens.TypeToken type) {
        this.identifier = identifier;
        this.type = type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Tokens.TypeToken getType() {
        return type;
    }
}
