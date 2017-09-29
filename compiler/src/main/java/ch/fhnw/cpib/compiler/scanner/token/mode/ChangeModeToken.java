package ch.fhnw.cpib.compiler.scanner.token.mode;

import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;

public class ChangeModeToken extends Token {

    public enum ChangeMode {
        VAR("VAR"),
        CONST("CONST");

        private final String name;

        ChangeMode(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    private final ChangeMode changemode;

    public ChangeModeToken(ChangeMode changemode) {
        super(Terminal.CHANGEMODE);
        this.changemode = changemode;
    }

    public ChangeMode getChangeMode() {
        return changemode;
    }

    @Override
    public String toString() {
        return "TODO";
    }
}
