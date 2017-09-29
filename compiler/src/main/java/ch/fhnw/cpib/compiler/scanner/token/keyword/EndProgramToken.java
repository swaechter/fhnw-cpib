package ch.fhnw.cpib.compiler.scanner.token.keyword;

import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;

public class EndProgramToken extends Token {

    public EndProgramToken() {
        super(Terminal.ENDPROGRAM);
    }

    @Override
    public String toString() {
        return "TODO";
    }
}
