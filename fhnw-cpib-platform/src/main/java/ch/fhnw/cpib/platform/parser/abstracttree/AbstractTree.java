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

    public static class Param extends AbstractNode {

        private final Tokens.FlowModeToken flowmode;

        private final Tokens.MechModeToken mechmode;

        private final Tokens.ChangeModeToken changemode;

        private final TypedIdent typedident;

        private final Param nextparam;

        public Param(Tokens.FlowModeToken flowmode, Tokens.MechModeToken mechmode, Tokens.ChangeModeToken changemode, TypedIdent typedident, Param nextparam, int idendation) {
            super(idendation);
            this.flowmode = flowmode;
            this.mechmode = mechmode;
            this.changemode = changemode;
            this.typedident = typedident;
            this.nextparam = nextparam;
        }

        @Override
        public String toString() {
            return getHead("<Param>")
                + getBody("<Mode Name='FLOWMODE' Attribute='" + flowmode.getFlowMode() + "'/>'")
                + getBody("<Mode Name='MECHMODE' Attribute='" + mechmode.getMechMode() + "'/>'")
                + getBody("<Mode Name='CHANGEMODE' Attribute='" + changemode.getChangeMode() + "'/>'")
                + typedident
                + (nextparam != null ? nextparam : getBody("<NoNextParam/>"))
                + getHead("</Param>");
        }
    }

    public abstract static class Declaration extends AbstractNode {

        private final Declaration nextdeclaration;

        public Declaration(Declaration nextdeclaration, int idendation) {
            super(idendation);
            this.nextdeclaration = nextdeclaration;
        }

        public Declaration getNextDeclaration() {
            return nextdeclaration;
        }
    }

    public static class StoDecl extends Declaration {

        private final Tokens.ChangeModeToken changemode;

        private final TypedIdent typedident;

        public StoDecl(Tokens.ChangeModeToken changemode, TypedIdent typedident, Declaration nextdeclaration, int idendation) {
            super(nextdeclaration, idendation);
            this.changemode = changemode;
            this.typedident = typedident;
        }

        @Override
        public String toString() {
            return getHead("<StoDecl>")
                + getBody("<Mode Name='CHANGEMODE' Attribute='" + changemode.getChangeMode() + "'/>'")
                + typedident
                + (getNextDeclaration() != null ? getNextDeclaration() : getBody("<NoNextDeclaration/>"))
                + getHead("</StoDecl>");
        }
    }

    public static class FunDecl extends Declaration {

        public FunDecl(Declaration nextdeclaration, int idendation) {
            super(nextdeclaration, idendation);
        }

        @Override
        public String toString() {
            return getHead("<TODO>")
                + getBody("TODO FunDecl")
                + getHead("</TODO>");
        }
    }

    public static class ProcDecl extends Declaration {

        private final Tokens.IdentifierToken identifier;

        private final Param param;

        private final GlobalImport globalimport;

        private final Declaration declaration;

        private final Declaration nextdeclaration;

        private final Cmd cmd;

        public ProcDecl(Tokens.IdentifierToken identifier, Param param, GlobalImport globalimport, Declaration declaration, Declaration nextdeclaration, Cmd cmd, int idendation) {
            super(nextdeclaration, idendation);
            this.identifier = identifier;
            this.param = param;
            this.globalimport = globalimport;
            this.declaration = declaration;
            this.nextdeclaration = nextdeclaration;
            this.cmd = cmd;
        }

        @Override
        public String toString() {
            return getHead("<ProcDecl>")
                + getBody("<Ident Name='" + identifier.getName() + "'/>")
                + (param != null ? param : getBody("<NoNextParam/>"))
                + (globalimport != null ? globalimport : getBody("<NoGlobalImport/>"))
                + (cmd != null ? cmd : getBody("<NoCmd/>"))
                + (declaration != null ? declaration : getBody("<NoDeclaration/>"))
                + (nextdeclaration != null ? nextdeclaration : getBody("<NoNextDeclaration/>"))
                + getHead("</ProcDecl>");
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
    }

    public static class SkipCmd extends Cmd {

        public SkipCmd(Cmd nextcmd, int idendation) {
            super(nextcmd, idendation);
        }

        @Override
        public String toString() {
            return getHead("<CmdSkip>")
                + (getNextCmd() != null ? getNextCmd() : getBody("<NoNextCmd/>"))
                + getHead("</CmdSkip>");
        }
    }

    public static class AssiCmd extends Cmd {

        public AssiCmd(Cmd nextcmd, int idendation) {
            super(nextcmd, idendation);
        }

        @Override
        public String toString() {
            return getHead("<TODO>")
                + getBody("TODO AssiCmd")
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
                + getBody("TODO CpsCmd")
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
                + getBody("TODO CondCmd")
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
                + getBody("TODO WhileCmd")
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
                + getBody("TODO ProcCall")
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
                + getBody("TODO Output Cmd")
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
    }

    public static class LiteralExpr extends Expression {

        private final Tokens.LiteralToken literal;

        public LiteralExpr(Tokens.LiteralToken literal, int idendation) {
            super(idendation);
            this.literal = literal;
        }

        @Override
        public String toString() {
            return getHead("<LiteralExpr>")
                + getBody("TODO Literal: " + literal.getValue())
                + getHead("</LiteralExpr>");
        }
    }

    public static class StoreExpr extends Expression {

        private final Tokens.IdentifierToken identifier;

        private final boolean initialized;

        public StoreExpr(Tokens.IdentifierToken identifier, boolean initialized, int idendation) {
            super(idendation);
            this.identifier = identifier;
            this.initialized = initialized;
        }

        @Override
        public String toString() {
            return getHead("<StoreExpr>")
                + getBody("<Ident Name='" + identifier.getName() + "'/>")
                + getBody("<Initialized>" + initialized + "</Initialized>")
                + getHead("</StoreExpr>");
        }
    }

    public static class FunCallExpr extends Expression {

        public FunCallExpr(RoutineCall foo, int idendation) {
            super(idendation);
        }

        @Override
        public String toString() {
            return getHead("<TODO>")
                + getBody("TODO FunCallExpr")
                + getHead("</TODO>");
        }
    }

    public static class MonadicExpr extends Expression {

        private final Tokens.OperationToken operation;

        private final Expression expression;

        public MonadicExpr(Tokens.OperationToken operation, Expression expression, int idendation) {
            super(idendation);
            this.operation = operation;
            this.expression = expression;
        }

        @Override
        public String toString() {
            return getHead("<MonadicExpr>")
                + getBody("TODO Operator: " + operation.getOperation())
                + expression
                + getHead("</MonadicExpr>");
        }
    }

    public static class DyadicExpr extends Expression {

        private final Tokens.OperationToken operation;

        private final Expression expression1;

        private final Expression expression2;

        public DyadicExpr(Tokens.OperationToken operation, Expression expression1, Expression expression2, int idendation) {
            super(idendation);
            this.operation = operation;
            this.expression1 = expression1;
            this.expression2 = expression2;
        }

        @Override
        public String toString() {
            return getHead("<ExprDyadic>")
                + getBody("TODO Operator: " + operation.getOperation())
                + expression1
                + expression2
                + getHead("</ExprDyadic>");
        }
    }

    public static class RoutineCall extends AbstractNode {

        private final Tokens.IdentifierToken identifier;

        private final ExpressionList expressionlist;

        public RoutineCall(Tokens.IdentifierToken identifier, ExpressionList expressionlist, int idendation) {
            super(idendation);
            this.identifier = identifier;
            this.expressionlist = expressionlist;
        }

        @Override
        public String toString() {
            return getHead("<TODO>")
                + getBody("TODO Routine Call")
                + getHead("</TODO>");
        }
    }

    public static class ExpressionList extends AbstractNode {

        private final Expression expression;

        private final ExpressionList expressionList;

        public ExpressionList(Expression expression, ExpressionList expressionList, int idendation) {
            super(idendation);
            this.expression = expression;
            this.expressionList = expressionList;
        }

        @Override
        public String toString() {
            return getHead("<TODO>")
                + getBody("TODO ExpressionList")
                + getHead("</TODO>");
        }
    }

    public static class GlobalInit extends AbstractNode {

        public GlobalInit(int idendation) {
            super(idendation);
        }

        @Override
        public String toString() {
            return getHead("<GlobalInit>")
                + getBody("TODO GlobalInit")
                + getHead("</GlobalInit>");
        }
    }

    public static class GlobalImport extends AbstractNode {

        public GlobalImport(int idendation) {
            super(idendation);
        }

        @Override
        public String toString() {
            return getHead("<GlobalImport>")
                + getBody("TODO Global Import")
                + getHead("</GlobalImport>");
        }
    }
}
