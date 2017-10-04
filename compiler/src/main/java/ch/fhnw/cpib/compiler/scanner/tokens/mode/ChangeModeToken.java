package ch.fhnw.cpib.compiler.scanner.tokens.mode;

import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;
import ch.fhnw.cpib.compiler.scanner.tokens.Token;

public class ChangeModeToken extends Token {

    public enum ChangeMode {
        VAR,
        CONST
    }

    private final ChangeMode changemode;

    public ChangeModeToken(String symbol, Terminal terminal, ChangeMode changemode) {
        super(symbol, terminal);
        this.changemode = changemode;
    }

    public ChangeMode getChangeMode() {
        return changemode;
    }

    @Override
    public String toString() {
        return "(" + getTerminal() + "," + changemode + ")";
    }
}
