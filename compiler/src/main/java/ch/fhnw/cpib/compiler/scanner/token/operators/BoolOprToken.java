package ch.fhnw.cpib.compiler.scanner.token.operators;

import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;

public class BoolOprToken extends Token {

    public enum BoolOpr {
        AND("AND"),
        OR("OR"),
        CAND("CAND"),
        COR("COR");

        private final String name;

        BoolOpr(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    private final BoolOpr boolopr;

    public BoolOprToken(BoolOpr boolopr) {
        super(Terminal.BOOLOPR);
        this.boolopr = boolopr;
    }

    public BoolOpr getBoolOpr() {
        return boolopr;
    }

    @Override
    public String toString() {
        return "TODO";
    }
}
