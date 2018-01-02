package ch.fhnw.cpib.platform.checker;

import ch.fhnw.cpib.platform.scanner.tokens.Tokens;

public class ExpressionInfo {

    private String name;

    private Tokens.TypeToken type;

    public ExpressionInfo(String name, Tokens.TypeToken type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Tokens.TypeToken getType() {
        return type;
    }
}
