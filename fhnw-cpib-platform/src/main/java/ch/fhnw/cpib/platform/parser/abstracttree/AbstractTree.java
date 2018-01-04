package ch.fhnw.cpib.platform.parser.abstracttree;

import ch.fhnw.cpib.platform.checker.*;
import ch.fhnw.cpib.platform.scanner.tokens.Tokens;

import java.util.*;

public class AbstractTree {

    public static class Program extends AbstractNode {

        public final Tokens.IdentifierToken identifier;

        public final ProgParam progparam;

        public final Declaration declaration;

        public final Cmd cmd;

        public Program(Tokens.IdentifierToken identifier, ProgParam progparam, Declaration declaration, Cmd cmd) {
            super(0);
            this.identifier = identifier;
            this.progparam = progparam;
            this.declaration = declaration;
            this.cmd = cmd;
        }

        @Override
        public String toString() {
            return getHead("<Program>")
                + getBody("<Ident Name='" + identifier.getName() + "'/>")
                + (progparam != null ? progparam : getBody("<NoProgramParameter/>"))
                + (declaration != null ? declaration : getBody("<NoDeclarations/>"))
                + (cmd != null ? cmd : getBody("<NoCmd/>"))
                + ("</Program>");
        }

        public void check(Checker checker) throws CheckerException {
            if (progparam != null) {
                progparam.check(checker);
            }
            if (declaration != null) {
                declaration.check(checker);
            }
            if (cmd != null) {
                cmd.check(checker);
            }
        }

        /*@Override
        public void generateCode(Generator generator) {
            generator.appendLine(".class public " + identifier.getName());
            generator.appendLine(".super java/lang/Object");
            generator.appendLine(".method public static main([Ljava/lang/String;)V");
            generator.appendLine(".limit stack 100");
            generator.appendLine(".limit locals 100");
            generator.appendLine("getstatic java/lang/System/out Ljava/io/PrintStream;");
            generator.appendLine("ldc 42");
            generator.appendLine("invokevirtual java/io/PrintStream/println(I)V");
            generator.appendLine("return");
            generator.appendLine(".end method");
        }*/

        public String getProgramName() {
            return identifier.getName();
        }
    }

    public static class ProgParam extends AbstractNode {

        public final Tokens.FlowModeToken flowmode;

        public final Tokens.ChangeModeToken changemode;

        public final TypedIdent typedident;

        public final ProgParam nextprogparam;

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

        public void check(Checker checker) throws CheckerException {
            //check if identifier exist in global store table
            if (checker.getGlobalStoreTable().getStore(typedident.getIdentifier().getName()) != null) {
                throw new CheckerException("Identifier " + typedident.getIdentifier().getName() + " is already declared");
            }
            //store identifier in global store table
            checker.getGlobalStoreTable().addStore(new Store(typedident.getIdentifier().getName(), typedident.getType(), changemode.getChangeMode() == Tokens.ChangeModeToken.ChangeMode.CONST));
            if (nextprogparam != null) {
                nextprogparam.check(checker);
            }
        }
    }

    public static class Param extends AbstractNode {

        public final Tokens.FlowModeToken flowmode;

        public final Tokens.MechModeToken mechmode;

        public final Tokens.ChangeModeToken changemode;

        public final TypedIdent typedident;

        public final Param nextparam;

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

        public void check(Checker checker, Routine routine) throws CheckerException {
            Store store = checker.getGlobalStoreTable().getStore(typedident.getIdentifier().getName());
            switch (flowmode.getFlowMode()) {
                case IN:
                    //passing parameter must be constant
                    if (store != null && mechmode.getMechMode() == Tokens.MechModeToken.MechMode.REF && !store.isConst()) {
                        throw new CheckerException("IN reference parameter can not be var! Ident: " + store.getIdentifier());
                    }
                    routine.addParameter(new Parameter(typedident.getIdentifier().getName(), typedident.getType(), flowmode.getFlowMode(), mechmode.getMechMode(), changemode.getChangeMode()));
                    break;
                case INOUT:
                    if (routine.getRoutineType() != RoutineType.PROCEDURE) {
                        throw new CheckerException("INOUT parameter in function declaration! Ident: " + store.getIdentifier());
                    }
                    if (store != null && store.isConst()) {
                        throw new CheckerException("INOUT parameter can not be constant! Ident: " + store.getIdentifier());
                    }
                    routine.addParameter(new Parameter(typedident.getIdentifier().getName(), typedident.getType(), flowmode.getFlowMode(), mechmode.getMechMode(), changemode.getChangeMode()));
                    break;
                case OUT:
                    if (routine.getRoutineType() != RoutineType.PROCEDURE) {
                        throw new CheckerException("OUT parameter in function declaration! Ident: " + store.getIdentifier());
                    }
                    routine.addParameter(new Parameter(typedident.getIdentifier().getName(), typedident.getType(), flowmode.getFlowMode(), mechmode.getMechMode(), changemode.getChangeMode()));
                    break;
                default:
                    break;
            }
            if (nextparam != null) {
                nextparam.check(checker, routine);
            }
        }
    }

    public abstract static class Declaration extends AbstractNode {

        public final Declaration nextdeclaration;

        public Declaration(Declaration nextdeclaration, int idendation) {
            super(idendation);
            this.nextdeclaration = nextdeclaration;
        }

        public Declaration getNextDeclaration() {
            return nextdeclaration;
        }

        public abstract Tokens.TypeToken.Type check(Checker checker) throws CheckerException;
    }

    public static class StoDecl extends Declaration {

        public final Tokens.ChangeModeToken changemode;

        public final TypedIdent typedident;

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

        @Override
        public Tokens.TypeToken.Type check(Checker checker) throws CheckerException {
            //check if global scope applies
            StoreTable storetable;
            if (checker.getScope() == null) {
                storetable = checker.getGlobalStoreTable();
            } else {
                storetable = checker.getScope().getStoreTable();
            }

            //check if identifier exist in global store table
            String identifier = typedident.getIdentifier().getName();
            if (storetable.getStore(identifier) != null) {
                throw new CheckerException("Identifier " + typedident.getIdentifier().getName() + " is already declared");
            }
            //store identifier in global store table
            storetable.addStore(new Store(typedident.getIdentifier().getName(), typedident.getType(), false));

            Store store = storetable.getStore(typedident.getIdentifier().getName());
            store.setRelative(true);
            store.setReference(false);

            if (getNextDeclaration() != null) {
                getNextDeclaration().check(checker);
            }
            return null;
        }
    }

    public static class FunDecl extends Declaration {

        public final Tokens.IdentifierToken identifier;

        public final Param param;

        public final Declaration storedeclaration;

        public final GlobalImport globalimport;

        public final Declaration declaration;

        public final Cmd cmd;

        public FunDecl(Tokens.IdentifierToken identifier, Param param, Declaration storedeclaration, GlobalImport globalimport, Declaration declaration, Cmd cmd, Declaration nextdeclaration, int idendation) {
            super(nextdeclaration, idendation);
            this.identifier = identifier;
            this.param = param;
            this.storedeclaration = storedeclaration;
            this.globalimport = globalimport;
            this.declaration = declaration;
            this.cmd = cmd;
        }

        @Override
        public String toString() {
            return getHead("<FunDecl>")
                + getBody("<Ident Name='" + identifier.getName() + "'/>")
                + (param != null ? param : getBody("<NoParam/>"))
                + (storedeclaration != null ? storedeclaration : getBody("<NoNextStoreDeclaration/>"))
                + (globalimport != null ? globalimport : getBody("<NoGlobalImport/>"))
                + (cmd != null ? cmd : getBody("<NoCmd/>"))
                + (declaration != null ? declaration : getBody("<NoDeclaration/>"))
                + (getNextDeclaration() != null ? getNextDeclaration() : getBody("<NoNextDeclaration/>"))
                + getHead("</FunDecl>");
        }

        @Override
        public Tokens.TypeToken.Type check(Checker checker) throws CheckerException {
            //check if function exist in global routine table
            Routine function = new Routine(identifier.getName(), RoutineType.FUNCTION);
            //store function in global routine table if not
            if (!checker.getGlobalRoutineTable().insert(function)) {
                throw new CheckerException("Function " + identifier.getName() + " is already declared.");
            }
            checker.setScope(function.getScope());
            if (param != null) {
                param.check(checker, function);
            }
            if (storedeclaration != null) {
                storedeclaration.check(checker);
            }
            if (globalimport != null) {
                globalimport.check(function);
            }
            if (cmd != null) {
                cmd.check(checker);
            }
            checker.setScope(null);
            if (getNextDeclaration() != null) {
                getNextDeclaration().check(checker);
            }
            return null;
        }
    }

    public static class ProcDecl extends Declaration {

        public final Tokens.IdentifierToken identifier;

        public final Param param;

        public final GlobalImport globalimport;

        public final Declaration declaration;

        public final Cmd cmd;

        public ProcDecl(Tokens.IdentifierToken identifier, Param param, GlobalImport globalimport, Declaration declaration, Declaration nextdeclaration, Cmd cmd, int idendation) {
            super(nextdeclaration, idendation);
            this.identifier = identifier;
            this.param = param;
            this.globalimport = globalimport;
            this.declaration = declaration;
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
                + (getNextDeclaration() != null ? getNextDeclaration() : getBody("<NoNextDeclaration/>"))
                + getHead("</ProcDecl>");
        }

        @Override
        public Tokens.TypeToken.Type check(Checker checker) throws CheckerException {
            //store function in global procedure table
            Routine procedure = new Routine(identifier.getName(), RoutineType.PROCEDURE);
            checker.getGlobalRoutineTable().insert(procedure);
            checker.setScope(procedure.getScope());
            if (param != null) {
                param.check(checker, procedure);
            }
            if (globalimport != null) {
                globalimport.check(procedure);
            }
            if (cmd != null) {
                cmd.check(checker);
            }
            if (declaration != null) {
                declaration.check(checker);
            }
            checker.setScope(null);
            if (getNextDeclaration() != null) {
                getNextDeclaration().check(checker);
            }
            return null;
        }
    }

    public abstract static class Cmd extends AbstractNode {

        public final Cmd nextcmd;

        public Cmd(Cmd nextcmd, int idendation) {
            super(idendation);
            this.nextcmd = nextcmd;
        }

        public Cmd getNextCmd() {
            return nextcmd;
        }

        public abstract void check(Checker checker) throws CheckerException;
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

        @Override
        public void check(Checker checker) throws CheckerException {
            if (getNextCmd() != null) {
                getNextCmd().check(checker);
            }
        }
    }

    public static class AssiCmd extends Cmd {

        public final Expression targetExpression;

        public final ExpressionList targetExpressionList;

        public final Expression sourceExpression;

        public final ExpressionList sourceExpressionList;

        public AssiCmd(Expression targetExpression, ExpressionList targetExpressionList, Expression sourceExpression, ExpressionList sourceExpressionList, Cmd nextcmd, int idendation) {
            super(nextcmd, idendation);
            this.targetExpression = targetExpression;
            this.targetExpressionList = targetExpressionList;
            this.sourceExpression = sourceExpression;
            this.sourceExpressionList = sourceExpressionList;
        }

        @Override
        public String toString() {
            return getHead("<AssiCmd>")
                + targetExpression
                + (targetExpressionList != null ? targetExpressionList : getBody("<NoNextExpressionList/>"))
                + sourceExpression
                + (sourceExpressionList != null ? sourceExpressionList : getBody("<NoNextExpressionList/>"))
                + (getNextCmd() != null ? getNextCmd() : getBody("<NoNextCmd/>"))
                + getHead("</AssiCmd>");
        }

        @Override
        public void check(Checker checker) throws CheckerException {
            List<ExpressionInfo> targetExprInfos = new ArrayList<>();
            List<ExpressionInfo> sourceExprInfos = new ArrayList<>();

            ExpressionInfo sourceExprInfo = sourceExpression.check(checker);
            ExpressionInfo targetExprInfo = targetExpression.check(checker);

            if (targetExpressionList != null) {
                targetExpressionList.check(checker, targetExprInfos);
            }
            if (sourceExpressionList != null) {
                sourceExpressionList.check(checker, sourceExprInfos);
            }

            //check if first type on each side has same type
            boolean normalassignmentvalid = targetExprInfo.getType() == sourceExprInfo.getType();

            //normal assignment
            if (targetExpressionList == null && sourceExpressionList == null) {
                //check if null because variable can be used without initialisation
                if (!normalassignmentvalid && targetExprInfo.getType() != null && sourceExprInfo.getType() != null) {
                    //allow assignment INT to INT64
                    if (targetExprInfo.getType() != Tokens.TypeToken.Type.INT64 && sourceExprInfo.getType() != Tokens.TypeToken.Type.INT) {
                        throw new CheckerException("Assignment not possible due different datatypes: " +
                            targetExprInfo.getType() + " = " + sourceExprInfo.getType());
                    }
                }
            }
            if (getNextCmd() != null) {
                getNextCmd().check(checker);
            }
        }
    }

    public static class SwitchCmd extends Cmd {

        public final Expression expression;

        public final RepCaseCmd repcasecmd;

        public final Cmd cmd;

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
                + (getNextCmd() != null ? getNextCmd() : getBody("<NoNextCmd/>"))
                + getHead("</SwitchCmd>");
        }

        @Override
        public void check(Checker checker) throws CheckerException {
            ExpressionInfo exprinfo = expression.check(checker);
            Switch s = new Switch(exprinfo.getName(), exprinfo.getType());
            //store switch with name and type
            checker.getGlobalSwitchTable().insert(s);
            repcasecmd.check(checker);
            if (cmd != null) {
                cmd.check(checker);
            }
            if (getNextCmd() != null) {
                getNextCmd().check(checker);
            }
        }
    }

    public static class RepCaseCmd extends Cmd {

        public final Tokens.LiteralToken literal;

        public final Cmd cmd;

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

        @Override
        public void check(Checker checker) throws CheckerException {
            HashMap<String, Switch> map = checker.getGlobalSwitchTable().getTable();
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                Switch s = (Switch) pair.getValue();
                //check if case literal vales are different
                if (!s.getSwitchCaseList().isEmpty()) {
                    for (SwitchCase c : s.getSwitchCaseList()) {
                        if (c.getLiteraltoken().getValue().equals(literal.getValue())) {
                            throw new CheckerException("Case literal values have the same value.");
                        }
                    }
                }

                //check if switch type and case literal type have the same type
                if (!s.getSwitchCaseList().isEmpty()) {
                    for (SwitchCase c : s.getSwitchCaseList()) {
                        if (s.getType() != literal.getType()) {
                            throw new CheckerException("SwitchCase expr and case literal are not from the same type. " +
                                "Current switch expr type: " + s.getType() +
                                "Current case literal type: " + literal.getType());
                        }
                    }
                }

                //add case literal with value and type
                SwitchCase switchCase = new SwitchCase(literal);
                //store case to switch
                s.addSwitchCase(switchCase);
                checker.getGlobalSwitchTable().insert(s);
            }
            if (getNextCmd() != null) {
                getNextCmd().check(checker);
            }
        }
    }

    public static class CondCmd extends Cmd {

        public final Expression expression;

        public final Cmd cmd;

        public final RepCondCmd repcondcmd;

        public final Cmd othercmd;

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

        @Override
        public void check(Checker checker) throws CheckerException {
            //check expr return type is from type BOOL
            ExpressionInfo exprinfo = expression.check(checker);
            if (exprinfo.getType() != Tokens.TypeToken.Type.BOOL) {
                throw new CheckerException("IF condition needs to be BOOL. Current type: " + exprinfo.getType());
            }
            if (repcondcmd != null) {
                repcondcmd.check(checker);
            }
            if (othercmd != null) {
                othercmd.check(checker);
            }
            if (getNextCmd() != null) {
                getNextCmd().check(checker);
            }
        }
    }

    public static class RepCondCmd extends Cmd {

        public final Expression expression;

        public final Cmd cmd;

        public final RepCondCmd repcondcmd;

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
                + (getNextCmd() != null ? getNextCmd() : getBody("<NoNextCmd/>"))
                + getHead("</RepCondCmd>");
        }

        @Override
        public void check(Checker checker) throws CheckerException {
            //check expr return type is from type BOOL
            ExpressionInfo exprinfo = expression.check(checker);
            if (exprinfo.getType() != Tokens.TypeToken.Type.BOOL) {
                throw new CheckerException("ELSEIF condition needs to be BOOL. Current type: " + exprinfo.getType());
            }
            if (repcondcmd != null) {
                repcondcmd.check(checker);
            }
            if (getNextCmd() != null) {
                getNextCmd().check(checker);
            }
        }
    }

    public static class WhileCmd extends Cmd {

        public final Expression expression;

        public final Cmd cmd;

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

        @Override
        public void check(Checker checker) throws CheckerException {
            //check expr return type is from type BOOL
            ExpressionInfo exprinfo = expression.check(checker);
            if (exprinfo.getType() != Tokens.TypeToken.Type.BOOL && exprinfo.getType() != null) {
                throw new CheckerException("WHILE condition needs to be BOOL. Current type: " + exprinfo.getType());
            }
            if (getNextCmd() != null) {
                getNextCmd().check(checker);
            }
        }
    }

    public static class ProcCallCmd extends Cmd {

        public final RoutineCall routinecall;

        public final GlobalInit globalinit;

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

        @Override
        public void check(Checker checker) throws CheckerException {
            if (globalinit != null) {
                globalinit.check();
            }
            if (getNextCmd() != null) {
                getNextCmd().check(checker);
            }
        }
    }

    public static class InputCmd extends Cmd {

        public final Expression expression;

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

        @Override
        public void check(Checker checker) throws CheckerException {
            if (getNextCmd() != null) {
                getNextCmd().check(checker);
            }
        }
    }

    public static class OutputCmd extends Cmd {

        public final Expression expression;

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

        @Override
        public void check(Checker checker) throws CheckerException {
            if (getNextCmd() != null) {
                getNextCmd().check(checker);
            }
        }
    }

    public abstract static class TypedIdent<T> extends AbstractNode {

        public TypedIdent(int idendation) {
            super(idendation);
        }

        public abstract Tokens.IdentifierToken getIdentifier();

        public abstract Tokens.TypeToken.Type getType();
    }

    public static class TypedIdentIdent extends TypedIdent<Tokens.IdentifierToken> {

        public final Tokens.IdentifierToken identifier1;

        public final Tokens.IdentifierToken identifier2;

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

        @Override
        public Tokens.IdentifierToken getIdentifier() {
            return identifier1;
        }

        @Override
        public Tokens.TypeToken.Type getType() {
            return null;
        }
    }

    public static class TypedIdentType extends TypedIdent<Tokens.IdentifierToken> {

        public final Tokens.IdentifierToken identifier;

        public final Tokens.TypeToken.Type type;

        public TypedIdentType(Tokens.IdentifierToken identifier, Tokens.TypeToken.Type type, int idendation) {
            super(idendation);
            this.identifier = identifier;
            this.type = type;
        }

        @Override
        public String toString() {
            return getHead("<TypedIdentType>")
                + getBody("<Ident Name='" + identifier.getName() + "'/>")
                + getBody("<Type Type='" + type + "'/>")
                + getHead("</TypedIdentType>");
        }

        @Override
        public Tokens.IdentifierToken getIdentifier() {
            return identifier;
        }

        @Override
        public Tokens.TypeToken.Type getType() {
            return type;
        }
    }

    public abstract static class Expression<T> extends AbstractNode {

        public Expression(int idendation) {
            super(idendation);
        }

        public abstract ExpressionInfo check(Checker checker) throws CheckerException;
    }

    public static class LiteralExpr extends Expression {

        public final Tokens.LiteralToken literal;

        public LiteralExpr(Tokens.LiteralToken literal, int idendation) {
            super(idendation);
            this.literal = literal;
        }

        @Override
        public String toString() {
            return getHead("<LiteralExpr>")
                + getBody("<Literal Value='" + literal.getValue() + "'/>")
                + getHead("</LiteralExpr>");
        }

        public ExpressionInfo check() throws CheckerException {
            //check Lvalue
            throw new CheckerException("Found literal " + literal.getValue() + "in the left part of an assignement");
        }

        @Override
        public ExpressionInfo check(Checker checker) throws CheckerException {
            if (literal.getType() == Tokens.TypeToken.Type.BOOL) {
                return new ExpressionInfo(String.valueOf(literal.getValue()), Tokens.TypeToken.Type.BOOL);
            } else if (literal.getType() == Tokens.TypeToken.Type.INT) {
                return new ExpressionInfo(String.valueOf(literal.getValue()), Tokens.TypeToken.Type.INT);
            } else if (literal.getType() == Tokens.TypeToken.Type.INT64) {
                return new ExpressionInfo(String.valueOf(literal.getValue()), Tokens.TypeToken.Type.INT64);
            }
            throw new CheckerException("Invalid literal type");
        }
    }

    public static class StoreExpr extends Expression {

        public final Tokens.IdentifierToken identifier;

        public final boolean initialized;

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

        @Override
        public ExpressionInfo check(Checker checker) throws CheckerException {
            //check if global scope applies
            StoreTable storetable;
            if (checker.getScope() == null) {
                storetable = checker.getGlobalStoreTable();
            } else {
                storetable = checker.getScope().getStoreTable();
            }

            Store store = storetable.getStore(identifier.getName());
            if (store == null) {
                //check if store is declared on global scope
                store = checker.getGlobalStoreTable().getStore(identifier.getName());
                //check if identifier in routine defined
                HashMap map = checker.getGlobalRoutineTable().getTable();
                Routine routine = null;
                Iterator it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    routine = (Routine) pair.getValue();
                    List<Parameter> parameters = routine.getParameters();
                    for (Parameter parameter : parameters) {
                        if (parameter.getName().equals(identifier.getName())) {
                            return new ExpressionInfo(parameter.getName(), parameter.getType());
                        }
                    }
                }
                //store not initialized variable because before a initialisation of a variable i can be used
                if (checker.getGlobalStoreTable().getStore(identifier.getName()) == null) {
                    checker.getGlobalStoreTable().addStore(new Store(identifier.getName(), null, false));
                    return new ExpressionInfo(identifier.getName(), null);
                }
                //throw exception in the end
                //if (store == null) {
                //    throw new CheckerException("Identifier " + identifier.getName() + " is not declared");
                //}
            }
            return new ExpressionInfo(store.getIdentifier(), store.getType());
        }
    }

    public static class FunCallExpr extends Expression {

        public final RoutineCall routinecall;

        public FunCallExpr(RoutineCall routinecall, int idendation) {
            super(idendation);
            this.routinecall = routinecall;
        }

        @Override
        public String toString() {
            return getHead("<FunCallExpr>")
                + routinecall
                + getHead("</FunCallExpr>");
        }

        public ExpressionInfo check(Checker checker) throws CheckerException {
            return routinecall.check(checker);
        }
    }

    public static class MonadicExpr extends Expression {

        public final Tokens.OperationToken operation;

        public final Expression expression;

        public MonadicExpr(Tokens.OperationToken operation, Expression expression, int idendation) {
            super(idendation);
            this.operation = operation;
            this.expression = expression;
        }

        @Override
        public String toString() {
            return getHead("<MonadicExpr>")
                + getBody("<Operation Operation='" + operation.getOperation() + "'/>")
                + expression
                + getHead("</MonadicExpr>");
        }

        @Override
        public ExpressionInfo check(Checker checker) throws CheckerException {
            return expression.check(checker);
        }
    }

    public static class DyadicExpr extends Expression {

        public final Tokens.OperationToken operation;

        public final Expression expression1;

        public final Expression expression2;

        public DyadicExpr(Tokens.OperationToken operation, Expression expression1, Expression expression2, int idendation) {
            super(idendation);
            this.operation = operation;
            this.expression1 = expression1;
            this.expression2 = expression2;
        }

        @Override
        public String toString() {
            return getHead("<DyadicExpr>")
                + getBody("<Operation Operation='" + operation.getOperation() + "'/>")
                + expression1
                + expression2
                + getHead("</DyadicExpr>");
        }

        @Override
        public ExpressionInfo check(Checker checker) throws CheckerException {
            ExpressionInfo exprinfo1 = expression1.check(checker);
            ExpressionInfo exprinfo2 = expression2.check(checker);
            Tokens.OperationToken.Operation opr = operation.getOperation();

            switch (opr) {
                case PLUS:
                    if (exprinfo1.getType() == Tokens.TypeToken.Type.INT64 && exprinfo2.getType() == Tokens.TypeToken.Type.INT64) {
                        return new ExpressionInfo(exprinfo1.getName(), Tokens.TypeToken.Type.INT64);
                    }
                    if (exprinfo1.getType() == Tokens.TypeToken.Type.INT && exprinfo2.getType() == Tokens.TypeToken.Type.INT) {
                        return new ExpressionInfo(exprinfo1.getName(), Tokens.TypeToken.Type.INT);
                    }
                    break;
                case TIMES:
                    if (exprinfo1.getType() == Tokens.TypeToken.Type.INT64 && exprinfo2.getType() == Tokens.TypeToken.Type.INT64) {
                        return new ExpressionInfo(exprinfo1.getName(), Tokens.TypeToken.Type.INT64);
                    }
                    if (exprinfo1.getType() == Tokens.TypeToken.Type.INT && exprinfo2.getType() == Tokens.TypeToken.Type.INT) {
                        return new ExpressionInfo(exprinfo1.getName(), Tokens.TypeToken.Type.INT);
                    }
                    break;
                case DIVE:
                    if (exprinfo1.getType() == Tokens.TypeToken.Type.INT64 && exprinfo2.getType() == Tokens.TypeToken.Type.INT64) {
                        return new ExpressionInfo(exprinfo1.getName(), Tokens.TypeToken.Type.INT64);
                    }
                    if (exprinfo1.getType() == Tokens.TypeToken.Type.INT && exprinfo2.getType() == Tokens.TypeToken.Type.INT) {
                        return new ExpressionInfo(exprinfo1.getName(), Tokens.TypeToken.Type.INT);
                    }
                    break;
                case MODE:
                    if (exprinfo1.getType() == Tokens.TypeToken.Type.INT64 && exprinfo2.getType() == Tokens.TypeToken.Type.INT64) {
                        return new ExpressionInfo(exprinfo1.getName(), Tokens.TypeToken.Type.INT64);
                    }
                    if (exprinfo1.getType() == Tokens.TypeToken.Type.INT && exprinfo2.getType() == Tokens.TypeToken.Type.INT) {
                        return new ExpressionInfo(exprinfo1.getName(), Tokens.TypeToken.Type.INT);
                    }
                    break;
                case EQ:
                    return new ExpressionInfo(exprinfo1.getName(), Tokens.TypeToken.Type.BOOL);
                case NE:
                    // Also booleans possible, no Exception thrown
                    if (exprinfo1.getType() == Tokens.TypeToken.Type.BOOL && exprinfo2.getType() == Tokens.TypeToken.Type.BOOL) {
                        return new ExpressionInfo(exprinfo1.getName(), Tokens.TypeToken.Type.BOOL);
                    }
                    break;
                case GT:
                    return new ExpressionInfo(exprinfo1.getName(), Tokens.TypeToken.Type.BOOL);
                case LT:
                    return new ExpressionInfo(exprinfo1.getName(), Tokens.TypeToken.Type.BOOL);
                case GE:
                    return new ExpressionInfo(exprinfo1.getName(), Tokens.TypeToken.Type.BOOL);
                case LE:
                    if (exprinfo1.getType() == Tokens.TypeToken.Type.INT64 && exprinfo2.getType() == Tokens.TypeToken.Type.INT64) {
                        return new ExpressionInfo(exprinfo1.getName(), Tokens.TypeToken.Type.INT64);
                    }
                    if (exprinfo1.getType() == Tokens.TypeToken.Type.INT && exprinfo2.getType() == Tokens.TypeToken.Type.INT) {
                        return new ExpressionInfo(exprinfo1.getName(), Tokens.TypeToken.Type.INT);
                    }
                case CAND:
                    break;
                case COR:
                    break;
                case AND:
                    break;
                case OR:
                    if (exprinfo1.getType() == Tokens.TypeToken.Type.BOOL && exprinfo2.getType() == Tokens.TypeToken.Type.BOOL) {
                        return new ExpressionInfo(exprinfo1.getName(), Tokens.TypeToken.Type.BOOL);
                    }
                    break;
            }
            return new ExpressionInfo(exprinfo1.getName(), exprinfo1.getType());
        }
    }

    public static class RoutineCall extends AbstractNode {

        public final Tokens.IdentifierToken identifier;

        public final ExpressionList expressionlist;

        public RoutineCall(Tokens.IdentifierToken identifier, ExpressionList expressionlist, int idendation) {
            super(idendation);
            this.identifier = identifier;
            this.expressionlist = expressionlist;
        }

        @Override
        public String toString() {
            return getHead("<RoutineCall>")
                + getBody("<Ident Name='" + identifier.getName() + "'/>")
                + (expressionlist != null ? expressionlist : getBody("<NoNextExpressionList/>"))
                + getHead("</RoutineCall>");
        }

        public ExpressionInfo check(Checker checker) throws CheckerException {
            Routine calledroutine = checker.getGlobalRoutineTable().lookup(identifier.getName());
            if (calledroutine == null) {
                throw new CheckerException("Routine " + identifier.getName() + " is not declared.");
            }

            List<ExpressionInfo> exprinfos = new ArrayList<>();
            if (expressionlist != null) {
                expressionlist.check(checker, exprinfos);
            }
            List<Parameter> parameters = calledroutine.getParameters();

            //check number of arguments
            if (parameters.size() != exprinfos.size()) {
                throw new CheckerException("Routine call: Number of arguments don't match: " + identifier.getName() + " expected: " +
                    parameters.size() + ", call has " + exprinfos.size());
            }

            //check for type
            for (int i = 0; i < parameters.size(); i++) {
                if (parameters.get(i).getType() != exprinfos.get(i).getType() && exprinfos.get(i).getType() != null) {
                    throw new CheckerException("Routine call: Type of " + (i + 1) + ". Argument does not match. Expected: "
                        + parameters.get(i).getType() + ", call has: " + exprinfos.get(i).getType());
                }
            }
            return new ExpressionInfo(calledroutine.getIdentifier(), calledroutine.getReturnType());
        }
    }

    public static class ExpressionList extends AbstractNode {

        public final Expression expression;

        public final ExpressionList expressionlist;

        public ExpressionList(Expression expression, ExpressionList expressionlist, int idendation) {
            super(idendation);
            this.expression = expression;
            this.expressionlist = expressionlist;
        }

        @Override
        public String toString() {
            return getHead("<ExpressionList>")
                + expression
                + (expressionlist != null ? expression : getBody("<NoNextExpressionList/>"))
                + getHead("</ExpressionList>");
        }

        public void check(Checker checker, List<ExpressionInfo> expressioninfos) throws CheckerException {
            expressioninfos.add(expression.check(checker));
            if (expressionlist != null) {
                expressionlist.check(checker, expressioninfos);
            }
        }
    }

    public static class GlobalInit extends AbstractNode {

        public final Tokens.IdentifierToken identifier;

        public final GlobalInit nextglobalinit;

        public GlobalInit(Tokens.IdentifierToken identifier, GlobalInit nextglobalinit, int idendation) {
            super(idendation);
            this.identifier = identifier;
            this.nextglobalinit = nextglobalinit;
        }

        @Override
        public String toString() {
            return getHead("<GlobalInit>")
                + getBody("<Ident Name='" + identifier.getName() + "'/>")
                + (nextglobalinit != null ? nextglobalinit : getBody("<NoNextGlobalInit/>"))
                + getHead("</GlobalInit>");
        }

        public void check() {
            if (nextglobalinit != null) {
                nextglobalinit.check();
            }
        }
    }

    public static class GlobalImport extends AbstractNode {

        public final Tokens.FlowModeToken flowmode;

        public final Tokens.ChangeModeToken changemode;

        public final Tokens.IdentifierToken identifier;

        public final GlobalImport nextglobalimport;

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

        public void check(Routine routine) {
            routine.addGlobalImport(this);
        }
    }
}
