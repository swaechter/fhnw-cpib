package ch.fhnw.cpib.compiler.scanner.token.mode;

import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;

public class MechModeToken extends Token {

    public enum MechMode {
        COPY("COPY"),
        VAR("VAR");

        private final String name;

        MechMode(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    private final MechMode mechmode;

    public MechModeToken(MechMode mechmode) {
        super(Terminal.MECHMODE);
        this.mechmode = mechmode;
    }

    public MechMode getMechMode() {
        return mechmode;
    }

    @Override
    public String toString() {
        return "TODO";
    }
}
