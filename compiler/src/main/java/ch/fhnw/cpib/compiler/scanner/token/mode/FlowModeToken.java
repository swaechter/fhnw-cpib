package ch.fhnw.cpib.compiler.scanner.token.mode;

import ch.fhnw.cpib.compiler.scanner.Token;
import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;

public class FlowModeToken extends Token {

    public enum FlowMode {
        IN("IN"),
        INOUT("INOUT"),
        OUT("OUT");

        private final String name;

        FlowMode(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    private final FlowMode flowmode;

    public FlowModeToken(FlowMode flowmode) {
        super(Terminal.FLOWMODE);
        this.flowmode = flowmode;
    }

    public FlowMode getFlowMode() {
        return flowmode;
    }

    @Override
    public String toString() {
        return "TODO";
    }
}
