package ch.fhnw.cpib.compiler.scanner.tokens;

import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;

public class Token {

    private final Terminal terminal;

    public Token(Terminal terminal) {
        this.terminal = terminal;
    }

    public final Terminal getTerminal() {
        return terminal;
    }

    public String toString() {
        return terminal.toString();
    }
}
