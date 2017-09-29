package ch.fhnw.cpib.compiler.scanner.token.type;

import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;

public class TypeToken extends Token {

    public enum Type {
        BOOL("BOOL"),
        INT("INT"),
        INT64("INT64");

        private final String name;

        Type(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private final Type type;

    public TypeToken(Type type) {
        super(Terminal.TYPE);
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "(" + Terminal.TYPE + ", " + type.toString() + ")";
    }
}
