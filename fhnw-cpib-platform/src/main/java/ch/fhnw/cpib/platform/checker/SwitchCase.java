package ch.fhnw.cpib.platform.checker;

import ch.fhnw.cpib.platform.scanner.tokens.Tokens;

public class SwitchCase {

    private Tokens.TypeToken switchType;

    private Tokens.LiteralToken literalToken;

    public SwitchCase(Tokens.TypeToken switchType, Tokens.LiteralToken literalToken) {
        this.switchType = switchType;
        this.literalToken = literalToken;
    }

    public Tokens.TypeToken getSwitchtype() {
        return switchType;
    }

    public Tokens.LiteralToken getLiteraltoken() {
        return literalToken;
    }
}
