package ch.fhnw.cpib.platform.parser.concrete;

import ch.fhnw.cpib.platform.scanner.terminal.Terminal;
import ch.fhnw.cpib.platform.scanner.tokens.Token;
import ch.fhnw.cpib.platform.scanner.tokens.TokenList;

public class Context {

    private final TokenList tokenlist;

    private Token token;

    private Terminal terminal;

    public Context(TokenList tokenlist) {
        this.tokenlist = tokenlist;
    }

    public TokenList getTokenList() {
        return tokenlist;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }
}
