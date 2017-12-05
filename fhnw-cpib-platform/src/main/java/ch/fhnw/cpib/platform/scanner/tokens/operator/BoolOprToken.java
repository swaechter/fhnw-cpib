package ch.fhnw.cpib.platform.scanner.tokens.operator;

import ch.fhnw.cpib.platform.scanner.terminal.Terminal;
import ch.fhnw.cpib.platform.scanner.tokens.Token;

public class BoolOprToken extends Token {

    public enum Bool {
        AND,
        OR,
        CAND,
        COR
    }

    private final Bool boolopr;

    public BoolOprToken(Terminal terminal, Bool boolopr) {
        super(terminal);
        this.boolopr = boolopr;
    }

    public Bool getBoolOpr() {
        return boolopr;
    }

    @Override
    public String toString() {
        return "(" + super.toString() + "," + boolopr + ")";
    }
}
