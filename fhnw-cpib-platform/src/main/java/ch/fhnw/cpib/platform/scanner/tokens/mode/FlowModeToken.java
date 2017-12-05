package ch.fhnw.cpib.platform.scanner.tokens.mode;

import ch.fhnw.cpib.platform.scanner.terminal.Terminal;
import ch.fhnw.cpib.platform.scanner.tokens.Token;

public class FlowModeToken extends Token {

    public enum FlowMode {
        IN,
        INOUT,
        OUT
    }

    private final FlowMode flowmode;

    public FlowModeToken(Terminal terminal, FlowMode flowmode) {
        super(terminal);
        this.flowmode = flowmode;
    }

    public FlowMode getFlowMode() {
        return flowmode;
    }

    @Override
    public String toString() {
        return "(" + getTerminal() + "," + flowmode + ")";
    }
}
