package ch.fhnw.cpib.compiler.scanner.tokens.operator;

import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;
import ch.fhnw.cpib.compiler.scanner.tokens.Token;

public class AddOprToken extends Token {

    public enum AddOpr {
        PLUS,
        MINUS
    }

    private final AddOpr addopr;

    public AddOprToken(Terminal terminal, AddOpr addopr) {
        super(terminal);
        this.addopr = addopr;
    }

    public AddOpr getAddOpr() {
        return addopr;
    }

    @Override
    public String toString() {
        return "(" + super.toString() + "," + addopr + ")";
    }
}
