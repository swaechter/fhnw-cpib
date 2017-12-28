package ch.fhnw.cpib.platform.parser.concretetree;

import ch.fhnw.cpib.platform.scanner.tokens.Terminal;
import ch.fhnw.cpib.platform.scanner.tokens.TokenList;
import ch.fhnw.cpib.platform.scanner.tokens.Tokens;

public class Context {

    private final TokenList tokenlist;

    private Tokens.Token token;

    private Terminal terminal;

    public Context(TokenList tokenlist) {
        this.tokenlist = tokenlist;
    }

    public TokenList getTokenList() {
        return tokenlist;
    }

    public Tokens.Token getToken() {
        return token;
    }

    public void setToken(Tokens.Token token) {
        this.token = token;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }
}
