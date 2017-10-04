package ch.fhnw.cpib.compiler.scanner.tokens;

import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;

public class Token {

    private final String symbol;

    private final Terminal terminal;

    public Token(String symbol, Terminal terminal) {
        this.symbol = symbol;
        this.terminal = terminal;
    }

    public final String getSymbol() {
        return symbol;
    }

    public final Terminal getTerminal() {
        return terminal;
    }

    public String toString() {
        return terminal.toString();
    }
}
