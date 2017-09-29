package ch.fhnw.cpib.compiler.scanner;

import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;

public abstract class Token {

    private final Terminal terminal;

    public Token(Terminal terminal) {
        this.terminal = terminal;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public abstract String toString();
}
