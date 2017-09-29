package ch.fhnw.cpib.compiler.scanner.token.keyword;

import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;
import ch.fhnw.cpib.compiler.scanner.Token;

public class EndProcToken extends Token {

    public EndProcToken() {
        super(Terminal.ENDPROC);
    }

    @Override
    public String toString() {
        return getTerminal().toString();
    }
}
