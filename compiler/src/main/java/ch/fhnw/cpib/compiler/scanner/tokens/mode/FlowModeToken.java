package ch.fhnw.cpib.compiler.scanner.tokens.mode;

import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;
import ch.fhnw.cpib.compiler.scanner.tokens.Token;

public class FlowModeToken extends Token {

    public enum FlowMode {
        IN,
        INOUT,
        OUT
    }

    private final FlowMode flowmode;

    public FlowModeToken(String symbol, Terminal terminal, FlowMode flowmode) {
        super(symbol, terminal);
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
