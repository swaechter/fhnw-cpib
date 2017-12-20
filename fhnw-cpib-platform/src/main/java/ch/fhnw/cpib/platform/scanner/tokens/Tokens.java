package ch.fhnw.cpib.platform.scanner.tokens;

public class Tokens {

    public static class Token {

        private final Terminal terminal;

        public Token(Terminal terminal) {
            this.terminal = terminal;
        }

        public final Terminal getTerminal() {
            return terminal;
        }

        public String toString() {
            return terminal.toString();
        }
    }

    public static class IdentifierToken extends Token {

        private final String name;

        public IdentifierToken(String name, Terminal terminal) {
            super(terminal);
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "(" + super.toString() + "," + name + ")";
        }
    }

    public static class LiteralToken extends Token {

        private final String value;

        public LiteralToken(String value, Terminal terminal) {
            super(terminal);
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "(" + super.toString() + "," + value + ")";
        }
    }

    public static class ChangeModeToken extends Token {

        public enum ChangeMode {
            VAR,
            CONST
        }

        private final ChangeMode changemode;

        public ChangeModeToken(Terminal terminal, ChangeMode changemode) {
            super(terminal);
            this.changemode = changemode;
        }

        public ChangeMode getChangeMode() {
            return changemode;
        }

        @Override
        public String toString() {
            return "(" + getTerminal() + "," + changemode + ")";
        }
    }

    public static class FlowModeToken extends Token {

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

    public static class MechModeToken extends Token {

        public enum MechMode {
            COPY,
            REF
        }

        private final MechMode mechmode;

        public MechModeToken(Terminal terminal, MechMode mechmode) {
            super(terminal);
            this.mechmode = mechmode;
        }

        public MechMode getMechMode() {
            return mechmode;
        }

        @Override
        public String toString() {
            return "(" + getTerminal() + "," + mechmode + ")";
        }
    }

    public static class AddOprToken extends Token {

        public enum AddOpr {
            PLUS,
            MINUS
        }

        private final AddOpr addopr;

        public AddOprToken(Terminal terminal, AddOpr addopr) {
            super(terminal);
            this.addopr = addopr;
        }

        public AddOpr getAddOpr() {
            return addopr;
        }

        @Override
        public String toString() {
            return "(" + super.toString() + "," + addopr + ")";
        }
    }

    public static class BoolOprToken extends Token {

        public enum Bool {
            AND,
            OR,
            CAND,
            COR
        }

        private final Bool boolopr;

        public BoolOprToken(Terminal terminal, Bool boolopr) {
            super(terminal);
            this.boolopr = boolopr;
        }

        public Bool getBoolOpr() {
            return boolopr;
        }

        @Override
        public String toString() {
            return "(" + super.toString() + "," + boolopr + ")";
        }
    }

    public static class MultOprToken extends Token {

        public enum MultOpr {
            TIMES,
            DIVE,
            MODE
        }

        private final MultOpr multopr;

        public MultOprToken(Terminal terminal, MultOpr multopr) {
            super(terminal);
            this.multopr = multopr;
        }

        public MultOpr getMultOpr() {
            return multopr;
        }

        @Override
        public String toString() {
            return "(" + super.toString() + "," + multopr + ")";
        }
    }

    public static class RelOprToken extends Token {

        public enum RelOpr {
            EQ,
            NE,
            LT,
            GT,
            LE,
            GE
        }

        private final RelOpr relopr;

        public RelOprToken(Terminal terminal, RelOpr relopr) {
            super(terminal);
            this.relopr = relopr;
        }

        public RelOpr getRelOpr() {
            return relopr;
        }

        @Override
        public String toString() {
            return "(" + super.toString() + "," + relopr + ")";
        }
    }

    public static class TypeToken extends Token {

        public enum Type {
            BOOL,
            INT,
            INT64
        }

        private final Type type;

        public TypeToken(Terminal terminal, Type type) {
            super(terminal);
            this.type = type;
        }

        public Type getType() {
            return type;
        }

        public String toString() {
            return "(" + getTerminal() + "," + type + ")";
        }
    }

    public static class SentinelToken extends Token {

        public SentinelToken(Terminal terminal) {
            super(terminal);
        }
    }
}
