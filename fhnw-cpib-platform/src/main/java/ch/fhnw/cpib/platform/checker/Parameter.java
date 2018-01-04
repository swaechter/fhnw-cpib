package ch.fhnw.cpib.platform.checker;

import ch.fhnw.cpib.platform.scanner.tokens.Tokens;

public class Parameter {

    private String name;

    private Tokens.TypeToken.Type type;

    private Tokens.FlowModeToken.FlowMode flowmode;

    private Tokens.MechModeToken.MechMode mechmode;

    private Tokens.ChangeModeToken.ChangeMode changemode;

    public Parameter(String name, Tokens.TypeToken.Type type, Tokens.FlowModeToken.FlowMode flowmode, Tokens.MechModeToken.MechMode mechmode, Tokens.ChangeModeToken.ChangeMode changemode) {
        this.name = name;
        this.type = type;
        this.flowmode = flowmode;
        this.mechmode = mechmode;
        this.changemode = changemode;
    }

    public String getName() {
        return name;
    }

    public Tokens.TypeToken.Type getType() {
        return type;
    }

    public Tokens.FlowModeToken.FlowMode getFlowMode() {
        return flowmode;
    }

    public Tokens.MechModeToken.MechMode getMechMode() {
        return mechmode;
    }

    public Tokens.ChangeModeToken.ChangeMode getChangeMode() {
        return changemode;
    }
}
