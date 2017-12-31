package ch.fhnw.cpib.platform.parser.context;

import ch.fhnw.cpib.platform.scanner.tokens.Tokens;

public class Parameter {
    private Tokens.TypeToken.Type type;
    private Tokens.FlowModeToken flowmode;
    private Tokens.MechModeToken mechmode;
    private Tokens.ChangeModeToken changemode;

    public Parameter(Tokens.TypeToken.Type type, Tokens.FlowModeToken flowmode, Tokens.MechModeToken mechmode, Tokens.ChangeModeToken changemode) {
        this.type = type;
        this.flowmode = flowmode;
        this.mechmode = mechmode;
        this.changemode = changemode;
    }

    public Tokens.TypeToken.Type getType() {
        return type;
    }

    public Tokens.FlowModeToken getFlowMode() {
        return flowmode;
    }

    public Tokens.MechModeToken getMechmode() {
        return mechmode;
    }

    public Tokens.ChangeModeToken getChangeMode() {
        return changemode;
    }
}
