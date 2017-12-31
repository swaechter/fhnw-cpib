package ch.fhnw.cpib.platform.parser.context;

import ch.fhnw.cpib.platform.scanner.tokens.Tokens;

public class CSymbol {
    private String identifier;
    private Tokens.TypeToken.Type type;

    public CSymbol(String identifier, Tokens.TypeToken.Type type) {
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
