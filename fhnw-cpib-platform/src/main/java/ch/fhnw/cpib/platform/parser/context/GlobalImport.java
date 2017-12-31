package ch.fhnw.cpib.platform.parser.context;

import ch.fhnw.cpib.platform.scanner.tokens.Tokens;

public class GlobalImport {
    private String identifier;
    private Tokens.ChangeModeToken changemode;
    private Tokens.OperationToken flowmode;

    public GlobalImport(String identifier, Tokens.ChangeModeToken.ChangeMode changeMode, Tokens.OperationToken flowMode) {
        this.identifier = identifier;
        this.changemode = changemode;
        this.flowmode = flowmode;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Tokens.ChangeModeToken getChangeMode() {
        return changemode;
    }

    public Tokens.OperationToken getFlowMode() {
        return flowmode;
    }
}
