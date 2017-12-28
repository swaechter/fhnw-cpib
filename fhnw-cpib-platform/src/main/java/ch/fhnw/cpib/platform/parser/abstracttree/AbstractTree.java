package ch.fhnw.cpib.platform.parser.abstracttree;

import ch.fhnw.cpib.platform.scanner.tokens.Tokens;

public class AbstractTree {

    public static class Program extends AbstractNode {

        private final Tokens.IdentifierToken identifier;

        private final ProgParam progparam;

        private final Declaration declaration;

        private final Cmd cmd;

        public Program(Tokens.IdentifierToken identifier, ProgParam progparam, Declaration declaration, Cmd cmd) {
            super(0);
            this.identifier = identifier;
            this.progparam = progparam;
            this.declaration = declaration;
            this.cmd = cmd;
        }

        public String toString() {
            return getHead("<Program>")
                + getBody("<Ident Name='" + identifier.getName() + "'/>")
                + (progparam != null ? progparam : getBody("<NoProgramParameter/>"))
                + (declaration != null ? declaration : getBody("<NoDeclarations/>"))
                + (cmd != null ? cmd : getBody("<NoCmd/>"))
                + ("</Program>");
        }
    }

    public static class ProgParam extends AbstractNode {

        private final Tokens.FlowModeToken flowmode;

        private final Tokens.ChangeModeToken changemode;

        private final TypedIdent typedident;

        private final ProgParam nextprogparam;

        public ProgParam(Tokens.FlowModeToken flowmode, Tokens.ChangeModeToken changemode, TypedIdent typedident, ProgParam nextprogparam, int idendation) {
            super(idendation);
            this.flowmode = flowmode;
            this.changemode = changemode;
            this.typedident = typedident;
            this.nextprogparam = nextprogparam;
        }

        @Override
        public String toString() {
            return getHead("<ProgParam>")
                + getBody("<Mode Name='FLOWMODE' Attribute='" + flowmode.getFlowMode() + "'/>'")
                + getBody("<Mode Name='CHANGEMODE' Attribute='" + changemode.getChangeMode() + "'/>'")
                + typedident
                + (nextprogparam != null ? nextprogparam : getBody("<NoNextProgParam/>"))
                + getHead("</ProgParam>");
        }
    }

    public abstract static class Declaration extends AbstractNode {

        public Declaration(int idendation) {
            super(idendation);
        }

        @Override
        public String toString() {
            return getHead("<TODO>")
                + getBody("TODO Content")
                + getHead("</TODO>");
        }
    }

    public abstract static class Cmd extends AbstractNode {

        private final Cmd nextcmd;

        public Cmd(Cmd nextcmd, int idendation) {
            super(idendation);
            this.nextcmd = nextcmd;
        }

        public Cmd getNextCmd() {
            return nextcmd;
        }

        @Override
        public String toString() {
            return getHead("<TODO>")
                + getBody("TODO Content")
                + getHead("</TODO>");
        }
    }

    public static class SkipCmd extends Cmd {

        public SkipCmd(Cmd nextcmd, int idendation) {
            super(nextcmd, idendation);
        }

        @Override
        public String toString() {
            return getHead("<TODO>")
                + getBody("TODO Content")
                + getHead("</TODO>");
        }
    }

    public static class AssiCmd extends Cmd {

        public AssiCmd(Cmd nextcmd, int idendation) {
            super(nextcmd, idendation);
        }

        @Override
        public String toString() {
            return getHead("<TODO>")
                + getBody("TODO Content")
                + getHead("</TODO>");
        }
    }

    public static class CpsCmd extends Cmd {

        public CpsCmd(Cmd nextcmd, int idendation) {
            super(nextcmd, idendation);
        }

        @Override
        public String toString() {
            return getHead("<TODO>")
                + getBody("TODO Content")
                + getHead("</TODO>");
        }
    }

    // TODO: SwitchCmd extends Cmd

    public static class CondCmd extends Cmd {

        public CondCmd(Cmd nextcmd, int idendation) {
            super(nextcmd, idendation);
        }

        @Override
        public String toString() {
            return getHead("<TODO>")
                + getBody("TODO Content")
                + getHead("</TODO>");
        }
    }

    public static class WhileCmd extends Cmd {

        public WhileCmd(Cmd nextcmd, int idendation) {
            super(nextcmd, idendation);
        }

        @Override
        public String toString() {
            return getHead("<TODO>")
                + getBody("TODO Content")
                + getHead("</TODO>");
        }
    }

    public static class ProcCallCmd extends Cmd {

        public ProcCallCmd(Cmd nextcmd, int idendation) {
            super(nextcmd, idendation);
        }

        @Override
        public String toString() {
            return getHead("<TODO>")
                + getBody("TODO Content")
                + getHead("</TODO>");
        }
    }

    public static class InputCmd extends Cmd {

        private final Expression expression;

        public InputCmd(Expression expression, Cmd nextcmd, int idendation) {
            super(nextcmd, idendation);
            this.expression = expression;
        }

        @Override
        public String toString() {
            return getHead("<CmdInput>")
                + (expression != null ? expression : getBody("<NoExpression/>"))
                + (getNextCmd() != null ? getNextCmd() : getBody("<NoNextElement/>"))
                + getHead("</CmdInput>");
        }
    }

    public static class OutputCmd extends Cmd {

        public OutputCmd(Cmd nextcmd, int idendation) {
            super(nextcmd, idendation);
        }

        @Override
        public String toString() {
            return getHead("<TODO>")
                + getBody("TODO Content")
                + getHead("</TODO>");
        }
    }

    public abstract static class TypedIdent<T> extends AbstractNode {

        public TypedIdent(int idendation) {
            super(idendation);
        }
    }

    public static class TypedIdentIdent extends TypedIdent<Tokens.IdentifierToken> {

        private final Tokens.IdentifierToken identifier1;

        private final Tokens.IdentifierToken identifier2;

        public TypedIdentIdent(Tokens.IdentifierToken identifier1, Tokens.IdentifierToken identifier2, int idendation) {
            super(idendation);
            this.identifier1 = identifier1;
            this.identifier2 = identifier2;
        }

        @Override
        public String toString() {
            return getHead("<TypedIdentIdent>")
                + getBody("<Ident Name='" + identifier1.getName() + "'/>")
                + getBody("<Ident Name='" + identifier2.getName() + "'/>")
                + getHead("</TypedIdentIdent>");
        }
    }

    public static class TypedIdentType extends TypedIdent<Tokens.IdentifierToken> {

        private final Tokens.IdentifierToken identifier;

        private final Tokens.TypeToken type;

        public TypedIdentType(Tokens.IdentifierToken identifier, Tokens.TypeToken type, int idendation) {
            super(idendation);
            this.identifier = identifier;
            this.type = type;
        }

        @Override
        public String toString() {
            return getHead("<TypedIdentType>")
                + getBody("<Ident Name='" + identifier.getName() + "'/>")
                + getBody("<Type Type='" + type.getType() + "'/>")
                + getHead("</TypedIdentType>");
        }
    }

    public abstract static class Expression<T> extends AbstractNode {

        public Expression(int idendation) {
            super(idendation);
        }

        @Override
        public String toString() {
            return getHead("<TODO>")
                + getBody("TODO Content")
                + getHead("</TODO>");
        }
    }

    public static class LiteralExpr extends Expression {

        public LiteralExpr(int idendation) {
            super(idendation);
        }

        @Override
        public String toString() {
            return getHead("<TODO>")
                + getBody("TODO Content")
                + getHead("</TODO>");
        }
    }

    public static class StoreExpr extends Expression {

        public StoreExpr(int idendation) {
            super(idendation);
        }

        @Override
        public String toString() {
            return getHead("<TODO>")
                + getBody("TODO Content")
                + getHead("</TODO>");
        }
    }

    public static class FunCallExpr extends Expression {

        public FunCallExpr(int idendation) {
            super(idendation);
        }

        @Override
        public String toString() {
            return getHead("<TODO>")
                + getBody("TODO Content")
                + getHead("</TODO>");
        }
    }

    public static class MonadicExpr extends Expression {

        public MonadicExpr(int idendation) {
            super(idendation);
        }

        @Override
        public String toString() {
            return getHead("<TODO>")
                + getBody("TODO Content")
                + getHead("</TODO>");
        }
    }

    public static class DyadicExpr extends Expression {

        public DyadicExpr(int idendation) {
            super(idendation);
        }

        @Override
        public String toString() {
            return getHead("<TODO>")
                + getBody("TODO Content")
                + getHead("</TODO>");
        }
    }
}
