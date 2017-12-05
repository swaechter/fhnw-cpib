package ch.fhnw.cpib.platform.scanner.tokens.mode;

import ch.fhnw.cpib.platform.scanner.terminal.Terminal;
import ch.fhnw.cpib.platform.scanner.tokens.Token;

public class MechModeToken extends Token {

    public enum MechMode {
        COPY,
        REF
    }

    private final MechMode mechmode;

    public MechModeToken(Terminal terminal, MechMode mechmode) {
        super(terminal);
        this.mechmode = mechmode;
    }

    public MechMode getMechMode() {
        return mechmode;
    }

    @Override
    public String toString() {
        return "(" + getTerminal() + "," + mechmode + ")";
    }
}
