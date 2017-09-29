package ch.fhnw.cpib.compiler.scanner.token.keyword;

import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;

public class CallToken extends Token {

    public CallToken() {
        super(Terminal.CALL);
    }

    @Override
    public String toString() {
        return "TODO";
    }
}
