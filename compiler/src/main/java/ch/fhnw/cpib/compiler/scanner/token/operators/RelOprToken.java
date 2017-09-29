package ch.fhnw.cpib.compiler.scanner.token.operators;

import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;

public class RelOprToken extends Token {

    public enum RelOpr {
        EQ("EQ"),
        NE("NE"),
        LT("LT"),
        GT("GT"),
        LE("LE"),
        GE("GE");

        private final String name;

        RelOpr(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    private final RelOpr relopr;

    public RelOprToken(RelOpr relopr) {
        super(Terminal.RELOPR);
        this.relopr = relopr;
    }

    public RelOpr getRelOpr() {
        return relopr;
    }

    @Override
    public String toString() {
        return "(" + Terminal.RELOPR + ", " + relopr.toString() + ")";
    }
}
