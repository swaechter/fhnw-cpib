package ch.fhnw.cpib.platform.scanner.tokens.operator;

import ch.fhnw.cpib.platform.scanner.terminal.Terminal;
import ch.fhnw.cpib.platform.scanner.tokens.Token;

public class MultOprToken extends Token {

    public enum MultOpr {
        TIMES,
        DIVE,
        MODE
    }

    private final MultOpr multopr;

    public MultOprToken(Terminal terminal, MultOpr multopr) {
        super(terminal);
        this.multopr = multopr;
    }

    public MultOpr getMultOpr() {
        return multopr;
    }

    @Override
    public String toString() {
        return "(" + super.toString() + "," + multopr + ")";
    }
}
