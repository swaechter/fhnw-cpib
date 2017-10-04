package ch.fhnw.cpib.compiler.scanner.tokens.operator;

import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;
import ch.fhnw.cpib.compiler.scanner.tokens.Token;

public class MultOprToken extends Token {

    public enum MultOpr {
        TIMES,
        DIVE,
        MODE
    }

    private final MultOpr multopr;

    public MultOprToken(String symbol, Terminal terminal, MultOpr multopr) {
        super(symbol, terminal);
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
