package ch.fhnw.cpib.compiler.scanner.token.keyword;

import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;

public class WhileToken extends Token {

    public WhileToken() {
        super(Terminal.WHILE);
    }

    @Override
    public String toString() {
        return "TODO";
    }
}
