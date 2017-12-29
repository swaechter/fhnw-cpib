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

        private final Tokens.IdentifierToken identifier;

        private final Param param;

        private final StoDecl storedeclaration;

        private final GlobalImport globalimport1;

        private final Cmd cmd;

        private final GlobalImport globalimport2;

        public FunDecl(Tokens.IdentifierToken identifier, Param param, StoDecl storedeclaration, GlobalImport globalimport1, Cmd cmd, GlobalImport globalimport2, int idendation) {
            super(storedeclaration, idendation);
            this.identifier = identifier;
            this.param = param;
            this.storedeclaration = storedeclaration;
            this.globalimport1 = globalimport1;
            this.cmd = cmd;
            this.globalimport2 = globalimport2;
        }

        @Override
        public String toString() {
            return getHead("<FunDecl>")
                + getBody("<Ident Name='" + identifier.getName() + "'/>")
                + (param != null ? param : getBody("<NoParam/>"))
                + (storedeclaration != null ? storedeclaration : getBody("<NoNextStoreDeclaration/>"))
                + (globalimport1 != null ? globalimport1 : getBody("<NoGlobalImport/>"))
                + (cmd != null ? cmd : getBody("<NoCmd/>"))
                + (globalimport2 != null ? globalimport2 : getBody("<NoGlobalImport/>"))
                + getHead("</FunDecl>");
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
            super(nextcmd, idendation); // TODO: Check if skip does really what we think
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

    public static class SwitchCmd extends Cmd {

        private final Expression expression;

        private final RepCaseCmd repcasecmd;

        private final Cmd cmd;

        public SwitchCmd(Expression expression, RepCaseCmd repcasecmd, Cmd cmd, Cmd nextcmd, int idendation) {
            super(nextcmd, idendation);
            this.expression = expression;
            this.repcasecmd = repcasecmd;
            this.cmd = cmd;
        }

        @Override
        public String toString() {
            return getHead("<SwitchCmd>")
                + expression
                + repcasecmd
                + (cmd != null ? cmd : getBody("<NoDefaultCmd/>"))
                + getHead("</SwitchCmd>");
        }
    }

    public static class RepCaseCmd extends Cmd {

        private final Tokens.LiteralToken literal;

        private final Cmd cmd;

        public RepCaseCmd(Tokens.LiteralToken literal, Cmd cmd, RepCaseCmd nextcmd, int idendation) {
            super(nextcmd, idendation);
            this.literal = literal;
            this.cmd = cmd;
        }

        @Override
        public String toString() {
            return getHead("<RepCaseCmd>")
                + getBody("<Literal Value='" + literal.getValue() + "'/>")
                + cmd
                + (getNextCmd() != null ? getNextCmd() : getBody("<NoNextRepCaseCmd/>"))
                + getHead("</RepCaseCmd>");
        }
    }

    public static class CondCmd extends Cmd {

        private final Expression expression;

        private final Cmd cmd;

        private final RepCondCmd repcondcmd;

        private final Cmd othercmd;

        public CondCmd(Expression expression, Cmd cmd, RepCondCmd repcondcmd, Cmd othercmd, Cmd nextcmd, int idendation) {
            super(nextcmd, idendation);
            this.expression = expression;
            this.cmd = cmd;
            this.repcondcmd = repcondcmd;
            this.othercmd = othercmd;
        }

        @Override
        public String toString() {
            return getHead("<CondCmd>")
                + expression
                + cmd
                + (repcondcmd != null ? repcondcmd : getBody("<NoNextRepCondCmd/>"))
                + (othercmd != null ? othercmd : getBody("<NoOtherCmd/>"))
                + (getNextCmd() != null ? getNextCmd() : getBody("<NoNextCmd/>"))
                + getHead("</CondCmd>");
        }
    }

    public static class RepCondCmd extends Cmd {

        private final Expression expression;

        private final Cmd cmd;

        private final RepCondCmd repcondcmd;

        public RepCondCmd(Expression expression, Cmd cmd, RepCondCmd repCondCmd, int idendation) {
            super(null, idendation);
            this.expression = expression;
            this.cmd = cmd;
            this.repcondcmd = repCondCmd;
        }

        @Override
        public String toString() {
            return getHead("<RepCondCmd>")
                + expression
                + cmd
                + (repcondcmd != null ? repcondcmd : getBody("<NoNextRepCondCmd/>"))
                + getHead("</RepCondCmd>");
        }
    }

    public static class WhileCmd extends Cmd {

        private final Expression expression;

        private final Cmd cmd;

        public WhileCmd(Expression expression, Cmd cmd, Cmd nextcmd, int idendation) {
            super(nextcmd, idendation);
            this.expression = expression;
            this.cmd = cmd;
        }

        @Override
        public String toString() {
            return getHead("<WhileCmd>")
                + expression
                + cmd
                + (getNextCmd() != null ? getNextCmd() : getBody("<NoCmd/>"))
                + getHead("</WhileCmd>");
        }
    }

    public static class ProcCallCmd extends Cmd {

        private final RoutineCall routinecall;

        private final GlobalInit globalinit;

        public ProcCallCmd(RoutineCall routinecall, GlobalInit globalinit, Cmd nextcmd, int idendation) {
            super(nextcmd, idendation);
            this.routinecall = routinecall;
            this.globalinit = globalinit;
        }

        @Override
        public String toString() {
            return getHead("<ProcCallCmd>")
                + routinecall
                + (globalinit != null ? globalinit : getBody("<NoGlobalInit/>"))
                + (getNextCmd() != null ? getNextCmd() : getBody("<NoNextCmd/>"))
                + getHead("</ProcCallCmd>");
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
            return getHead("<InputCmd>")
                + (expression != null ? expression : getBody("<NoExpression/>"))
                + (getNextCmd() != null ? getNextCmd() : getBody("<NoNextCmd/>"))
                + getHead("</InputCmd>");
        }
    }

    public static class OutputCmd extends Cmd {

        private final Expression expression;

        public OutputCmd(Expression expression, Cmd nextcmd, int idendation) {
            super(nextcmd, idendation);
            this.expression = expression;
        }

        @Override
        public String toString() {
            return getHead("<OutputCmd>")
                + (expression != null ? expression : getBody("<NoExpression/>"))
                + (getNextCmd() != null ? getNextCmd() : getBody("<NoNextCmd/>"))
                + getHead("</OutputCmd>");
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
            return getHead("<RoutineCall>")
                + getBody("<Ident Name='" + identifier.getName() + "'/>")
                + expressionlist
                + getHead("</RoutineCall>");
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

        private final Tokens.FlowModeToken flowmode;

        private final Tokens.ChangeModeToken changemode;

        private final Tokens.IdentifierToken identifier;

        private final GlobalImport nextglobalimport;

        public GlobalImport(Tokens.FlowModeToken flowmode, Tokens.ChangeModeToken changemode, Tokens.IdentifierToken identifier, GlobalImport nextglobalimport, int idendation) {
            super(idendation);
            this.flowmode = flowmode;
            this.changemode = changemode;
            this.identifier = identifier;
            this.nextglobalimport = nextglobalimport;
        }

        @Override
        public String toString() {
            return getHead("<GlobalImport>")
                + getBody("<Mode Name='FLOWMODE' Attribute='" + flowmode.getFlowMode() + "'/>'")
                + getBody("<Mode Name='CHANGEMODE' Attribute='" + changemode.getChangeMode() + "'/>'")
                + getBody("<Ident Name='" + identifier.getName() + "'/>")
                + (nextglobalimport != null ? nextglobalimport : getBody("<NoNextGlobalImport/>"))
                + getHead("</GlobalImport>");
        }
    }
}