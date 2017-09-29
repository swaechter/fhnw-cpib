package ch.fhnw.cpib.compiler.scanner.token.keyword;

import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;
import ch.fhnw.cpib.compiler.scanner.Token;

public class ProgramToken extends Token {

    public ProgramToken() {
        super(Terminal.PROGRAM);
    }

    @Override
    public String toString() {
        return "TODO";
    }
}
