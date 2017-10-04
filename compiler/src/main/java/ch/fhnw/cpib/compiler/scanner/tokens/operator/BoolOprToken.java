package ch.fhnw.cpib.compiler.scanner.tokens.operator;

import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;
import ch.fhnw.cpib.compiler.scanner.tokens.Token;

public class BoolOprToken extends Token {

    public enum Bool {
        AND,
        OR,
        CAND,
        COR
    }

    private final Bool boolopr;

    public BoolOprToken(String symbol, Terminal terminal, Bool boolopr) {
        super(symbol, terminal);
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
