package ch.fhnw.cpib.compiler.scanner.token.keyword;

import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;
import ch.fhnw.cpib.compiler.scanner.Token;

public class EndWhileToken extends Token {

    public EndWhileToken() {
        super(Terminal.ENDWHILE);
    }

    @Override
    public String toString() {
        return "TODO";
    }
}
