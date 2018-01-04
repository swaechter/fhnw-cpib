package ch.fhnw.cpib.platform.checker;

import ch.fhnw.cpib.platform.scanner.tokens.Tokens;

public class SwitchCase {

    private Tokens.LiteralToken literalToken;

    public SwitchCase(Tokens.LiteralToken literalToken) {
        this.literalToken = literalToken;
    }

    public Tokens.LiteralToken getLiteraltoken() {
        return literalToken;
    }
}
