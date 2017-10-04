package ch.fhnw.cpib.compiler.scanner.tokens.operator;

import ch.fhnw.cpib.compiler.scanner.tokens.Token;
import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;

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

    public RelOprToken(String symbol, Terminal terminal, RelOpr relopr) {
        super(symbol, terminal);
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
