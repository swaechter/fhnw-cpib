package ch.fhnw.cpib.platform.checker;

import ch.fhnw.cpib.platform.scanner.tokens.Tokens;

public class ExpressionInfo {
    private String name;
    private Tokens.TypeToken.Type type;

    public ExpressionInfo(String name, Tokens.TypeToken.Type type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Tokens.TypeToken.Type getType() {
        return type;
    }
}
