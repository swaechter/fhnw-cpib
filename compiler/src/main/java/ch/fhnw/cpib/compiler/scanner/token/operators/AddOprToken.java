package ch.fhnw.cpib.compiler.scanner.token.operators;

import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;

public class AddOprToken extends Token {

    public enum AddOpr {
        PLUS("PLUS"),
        MINUS("MINUS");

        private final String name;

        AddOpr(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    private final AddOpr addropr;

    public AddOprToken(AddOpr addropr) {
        super(Terminal.ADDOPR);
        this.addropr = addropr;
    }

    public AddOpr getAddrOpr() {
        return addropr;
    }

    @Override
    public String toString() {
        return "(" + Terminal.ADDOPR + ", " + addropr.toString() + ")";
    }
}
