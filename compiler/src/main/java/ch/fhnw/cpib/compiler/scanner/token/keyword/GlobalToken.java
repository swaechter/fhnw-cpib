package ch.fhnw.cpib.compiler.scanner.token.keyword;

import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;
import ch.fhnw.cpib.compiler.scanner.Token;

public class GlobalToken extends Token {

    public GlobalToken() {
        super(Terminal.GLOBAL);
    }

    @Override
    public String toString() {
        return "TODO";
    }
}
