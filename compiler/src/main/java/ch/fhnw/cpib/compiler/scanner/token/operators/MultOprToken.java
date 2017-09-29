package ch.fhnw.cpib.compiler.scanner.token.operators;

import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;

public class MultOprToken extends Token {

    public enum MultOpr {
        TIMES("TIMES"),
        DIVE("DIVE"),
        MODE("MODE");

        private final String name;

        MultOpr(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    private final MultOpr multopr;

    public MultOprToken(MultOpr multopr) {
        super(Terminal.MULTOPR);
        this.multopr = multopr;
    }

    public MultOpr getMultOpr() {
        return multopr;
    }

    @Override
    public String toString() {
        return "(" + Terminal.MULTOPR + ", " + multopr.toString() + ")";
    }
}
