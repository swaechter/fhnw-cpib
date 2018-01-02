package ch.fhnw.cpib.platform.checker;

import ch.fhnw.cpib.platform.scanner.tokens.Tokens;

public class SwitchCase {

    private Tokens.TypeToken.Type switchType;

    private Tokens.LiteralToken literalToken;

    public SwitchCase(Tokens.TypeToken.Type switchType, Tokens.LiteralToken literalToken) {
        this.switchType = switchType;
        this.literalToken = literalToken;
    }

    public Tokens.TypeToken.Type getSwitchtype() {
        return switchType;
    }

    public Tokens.LiteralToken getLiteraltoken() {
        return literalToken;
    }
}
