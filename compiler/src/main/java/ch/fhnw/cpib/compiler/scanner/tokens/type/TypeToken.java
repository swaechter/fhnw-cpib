package ch.fhnw.cpib.compiler.scanner.tokens.type;

import ch.fhnw.cpib.compiler.scanner.tokens.Token;
import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;

public class TypeToken extends Token {

    public enum Type {
        BOOL,
        INT,
        INT64
    }

    private final Type type;

    public TypeToken(String symbol, Terminal terminal, Type type) {
        super(symbol, terminal);
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public String toString() {
        return "(" + getTerminal() + "," + type + ")";
    }
}
