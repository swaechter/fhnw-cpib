package ch.fhnw.cpib.platform.checker;

import ch.fhnw.cpib.platform.scanner.tokens.Tokens;

public class GlobalImport {
    private String identifier;
    private Tokens.FlowModeToken flowmode;
    private Tokens.ChangeModeToken changemode;

    public GlobalImport(String identifier, Tokens.FlowModeToken flowmode, Tokens.ChangeModeToken changemode) {
        this.identifier = identifier;
        this.flowmode = flowmode;
        this.changemode = changemode;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Tokens.FlowModeToken getFlowMode() {
        return flowmode;
    }

    public Tokens.ChangeModeToken getChangemOde() {
        return changemode;
    }
}
