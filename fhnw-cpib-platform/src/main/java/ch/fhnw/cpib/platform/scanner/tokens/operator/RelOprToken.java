package ch.fhnw.cpib.platform.scanner.tokens.operator;

import ch.fhnw.cpib.platform.scanner.terminal.Terminal;
import ch.fhnw.cpib.platform.scanner.tokens.Token;

public class RelOprToken extends Token {

    public enum RelOpr {
        EQ,
        NE,
        LT,
        GT,
        LE,
        GE
    }

    private final RelOpr relopr;

    public RelOprToken(Terminal terminal, RelOpr relopr) {
        super(terminal);
        this.relopr = relopr;
    }

    public RelOpr getRelOpr() {
        return relopr;
    }

    @Override
    public String toString() {
        return "(" + super.toString() + "," + relopr + ")";
    }
}
