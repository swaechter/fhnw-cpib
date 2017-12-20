package ch.fhnw.cpib.platform.parser.concrete;

import ch.fhnw.cpib.platform.scanner.tokens.Tokens;

public class ConcreteTree {

    public static class Program extends Item {

        private final Tokens.IdentifierToken identifier;

        private final ProgParamList progparamlist;

        private final OptCpsDecl optcpsdecl;

        private final CpsCmd cpscmd;

        public Program(Tokens.IdentifierToken identifier, ProgParamList progparamlist, OptCpsDecl optcpsdecl, CpsCmd cpscmd, int idendation) {
            super(idendation);
            this.identifier = identifier;
            this.progparamlist = progparamlist;
            this.optcpsdecl = optcpsdecl;
            this.cpscmd = cpscmd;
        }

        @Override
        public String toString() {
            return getHead("<Program Name='" + identifier.getName() + "'>") + progparamlist + optcpsdecl + cpscmd + getHead("</Program>");
        }
    }

    public static abstract class Decl extends Item {

        Decl(int idendation) {
            super(idendation);
        }
    }

    public static class DeclSto extends Decl {

        private final StoDecl stodecl;

        public DeclSto(StoDecl stodecl, int idendation) {
            super(idendation);
            this.stodecl = stodecl;
        }

        @Override
        public String toString() {
            return getHead("<DeclSto>") + stodecl + getHead("</DeclSto>");
        }
    }

    public static class DeclFun extends Decl {

        private final FunDecl fundecl;

        public DeclFun(FunDecl fundecl, int idendation) {
            super(idendation);
            this.fundecl = fundecl;
        }

        @Override
        public String toString() {
            return getHead("<DeclFun>") + fundecl + getHead("</DeclFun>");
        }
    }

    public static class DeclProc extends Decl {

        private final ProcDecl procdecl;

        public DeclProc(ProcDecl procdecl, int idendation) {
            super(idendation);
            this.procdecl = procdecl;
        }

        @Override
        public String toString() {
            return getHead("<DeclProc>") + procdecl + getHead("</DeclProc>");
        }
    }

    public static abstract class StoDecl extends Item {

        StoDecl(int idendation) {
            super(idendation);
        }
    }

    public static class StoDeclIdent extends StoDecl {

        private final TypedIdent typedident;

        public StoDeclIdent(TypedIdent typedident, int idendation) {
            super(idendation);
            this.typedident = typedident;
        }

        @Override
        public String toString() {
            return getHead("<StoDeclIdent>") + typedident + getHead("</StoDeclIdent>");
        }
    }

    public static class StoDeclChangemode extends StoDecl {

        private final Tokens.ChangeModeToken changemode;

        private final TypedIdent typedident;

        public StoDeclChangemode(Tokens.ChangeModeToken changemode, TypedIdent typedident, int idendation) {
            super(idendation);
            this.changemode = changemode;
            this.typedident = typedident;
        }

        @Override
        public String toString() {
            return getHead("<StoDeclChangemode Mode='" + changemode.getChangeMode() + "'>") + typedident + getHead("</StoDeclChangemode>");
        }
    }

    public static class FunDecl extends Item {

        private final Tokens.IdentifierToken identifier;

        private final ParamList paramlist;

        private final StoDecl stodecl;

        private final OptGlobImps optglobimps;

        private final OptCpsStoDecl optcpsstodecl;

        private final CpsCmd cpscmd;

        public FunDecl(Tokens.IdentifierToken identifier, ParamList paramlist, StoDecl stodecl, OptGlobImps optglobimps, OptCpsStoDecl optcpsstodecl, CpsCmd cpscmd, int idendation) {
            super(idendation);
            this.identifier = identifier;
            this.paramlist = paramlist;
            this.stodecl = stodecl;
            this.optglobimps = optglobimps;
            this.optcpsstodecl = optcpsstodecl;
            this.cpscmd = cpscmd;
        }

        @Override
        public String toString() {
            return getHead("<FunDecl Name='" + identifier.getName() + "'>") + paramlist + stodecl + optglobimps + optcpsstodecl + cpscmd + getHead("</FunDecl>");
        }
    }

    public static class ProcDecl extends Item {

        private final Tokens.IdentifierToken identifier;

        private final ParamList paramlist;

        private final OptGlobImps optglobimps;

        private final OptCpsStoDecl optcpsstodecl;

        private final CpsCmd cpscmd;

        public ProcDecl(Tokens.IdentifierToken identifier, ParamList paramlist, OptGlobImps optglobimps, OptCpsStoDecl optcpsstodecl, CpsCmd cpscmd, int idendation) {
            super(idendation);
            this.identifier = identifier;
            this.paramlist = paramlist;
            this.optglobimps = optglobimps;
            this.optcpsstodecl = optcpsstodecl;
            this.cpscmd = cpscmd;
        }

        @Override
        public String toString() {
            return getHead("<ProcDecl Name='" + identifier.getName() + "'>") + paramlist + optglobimps + optcpsstodecl + cpscmd + getHead("</ProcDecl>");
        }
    }

    public static abstract class OptGlobImps extends Item {

        OptGlobImps(int idendation) {
            super(idendation);
        }
    }

    public static class OptGlobImpsGlobal extends OptGlobImps {

        private final GlobImps globimps;

        public OptGlobImpsGlobal(GlobImps globimps, int idendation) {
            super(idendation);
            this.globimps = globimps;
        }

        @Override
        public String toString() {
            return getHead("<OptGlobImpsGlobal>") + globimps + getHead("</OptGlobImpsGlobal>");
        }
    }

    public static class OptGlobImpsEpsilon extends OptGlobImps {

        public OptGlobImpsEpsilon(int idendation) {
            super(idendation);
        }

        @Override
        public String toString() {
            return getHead("<OptGlobImpsEpsilon/>");
        }
    }

    public static class GlobImps extends Item {

        private final GlobImp globimp;

        private final RepGlobImps repglobimps;

        public GlobImps(GlobImp globimp, RepGlobImps repglobimps, int idendation) {
            super(idendation);
            this.globimp = globimp;
            this.repglobimps = repglobimps;
        }

        @Override
        public String toString() {
            return getHead("<GlobImps>") + globimp + repglobimps + getHead("</GlobImps>");
        }
    }

    public static abstract class RepGlobImps extends Item {

        RepGlobImps(int idendation) {
            super(idendation);
        }
    }

    public static class RepGlobImpsComma extends RepGlobImps {

        private final GlobImp globimp;

        private final RepGlobImps repglobimps;

        public RepGlobImpsComma(GlobImp globimp, RepGlobImps repglobimps, int idendation) {
            super(idendation);
            this.globimp = globimp;
            this.repglobimps = repglobimps;
        }

        @Override
        public String toString() {
            return getHead("<RepGlobImpsComma>") + globimp + repglobimps + getHead("</RepGlobImpsComma>");
        }
    }

    public static class RepGlobImpsEpsilon extends RepGlobImps {

        public RepGlobImpsEpsilon(int idendation) {
            super(idendation);
        }

        @Override
        public String toString() {
            return getHead("<RepGlobImpsEpsilon/>");
        }
    }

    public static abstract class OptChangemode extends Item {

        OptChangemode(int idendation) {
            super(idendation);
        }
    }

    public static class OptChangemodeChangemode extends OptChangemode {

        private final Tokens.ChangeModeToken changemode;

        public OptChangemodeChangemode(Tokens.ChangeModeToken changemode, int idendation) {
            super(idendation);
            this.changemode = changemode;
        }

        @Override
        public String toString() {
            return getHead("<OptChangemodeChangemode Mode='" + changemode.getChangeMode() + "'>") + getHead("</OptChangemodeChangemode>");
        }
    }

    public static class OptChangemodeEpsilon extends OptChangemode {

        public OptChangemodeEpsilon(int idendation) {
            super(idendation);
        }

        @Override
        public String toString() {
            return getHead("<OptChangemodeEpsilon/>");
        }
    }

    public static abstract class OptMechmode extends Item {

        OptMechmode(int idendation) {
            super(idendation);
        }
    }

    public static class OptMechmodeMechmode extends OptMechmode {

        private final Tokens.MechModeToken mechmode;

        public OptMechmodeMechmode(Tokens.MechModeToken mechmode, int idendation) {
            super(idendation);
            this.mechmode = mechmode;
        }

        @Override
        public String toString() {
            return getHead("<OptMechmodeMechmode Mode='" + mechmode.getMechMode() + "'>") + getHead("</OptMechmodeMechmode>");
        }
    }

    public static class OptMechmodeEpsilon extends OptMechmode {

        public OptMechmodeEpsilon(int idendation) {
            super(idendation);
        }

        @Override
        public String toString() {
            return getHead("<OptMechmodeEpsilon/>");
        }
    }

    public static abstract class GlobImp extends Item {

        GlobImp(int idendation) {
            super(idendation);
        }
    }

    public static class GlobImpExpression extends GlobImp {

        private final OptChangemode optchangemode;

        private final Tokens.IdentifierToken identifier;

        public GlobImpExpression(OptChangemode optchangemode, Tokens.IdentifierToken identifier, int idendation) {
            super(idendation);
            this.optchangemode = optchangemode;
            this.identifier = identifier;
        }

        @Override
        public String toString() {
            return getHead("<GlobImp Name='" + identifier.getName() + "'>") + optchangemode + getHead("</GlobImp>");
        }
    }

    public static class GlobImpFlowmode extends GlobImp {

        private final Tokens.FlowModeToken flowmode;

        private final OptChangemode optchangemode;

        private final Tokens.IdentifierToken identifier;

        public GlobImpFlowmode(Tokens.FlowModeToken flowmode, OptChangemode optchangemode, Tokens.IdentifierToken identifier, int idendation) {
            super(idendation);
            this.flowmode = flowmode;
            this.optchangemode = optchangemode;
            this.identifier = identifier;
        }

        @Override
        public String toString() {
            return getHead("<GlobImp Mode='" + flowmode.getFlowMode() + "' Name='" + identifier.getName() + "'>") + optchangemode + getHead("</GlobImp>");
        }
    }

    public static abstract class OptCpsDecl extends Item {

        OptCpsDecl(int idendation) {
            super(idendation);
        }
    }

    public static class OptCpsDeclGlobal extends OptCpsDecl {

        private final CpsDecl cpsdecl;

        public OptCpsDeclGlobal(CpsDecl cpsecl, int idendation) {
            super(idendation);
            this.cpsdecl = cpsecl;
        }

        @Override
        public String toString() {
            return getHead("<OptCpsDeclGlobal>") + cpsdecl + getHead("</OptCpsDeclGlobal>");
        }
    }

    public static class OptCpsDeclEpsilon extends OptCpsDecl {

        public OptCpsDeclEpsilon(int idendation) {
            super(idendation);
        }

        @Override
        public String toString() {
            return getHead("<OptCpsDeclEpsilon>") + getHead("</OptCpsDeclEpsilon>");
        }
    }

    public static class CpsDecl extends Item {

        private final Decl decl;

        private final RepCpsDecl repcpsdecl;

        public CpsDecl(Decl decl, RepCpsDecl repcpsdecl, int idendation) {
            super(idendation);
            this.decl = decl;
            this.repcpsdecl = repcpsdecl;
        }

        @Override
        public String toString() {
            return getHead("<CpsDecl>") + decl + repcpsdecl + getHead("</CpsDecl>");
        }
    }

    public static abstract class RepCpsDecl extends Item {

        RepCpsDecl(int idendation) {
            super(idendation);
        }
    }

    public static class RepCpsDeclSemicolon extends RepCpsDecl {

        private final Decl decl;

        private final RepCpsDecl repcpsdecl;

        public RepCpsDeclSemicolon(Decl decl, RepCpsDecl repcpsdecl, int idendation) {
            super(idendation);
            this.decl = decl;
            this.repcpsdecl = repcpsdecl;
        }

        @Override
        public String toString() {
            return getHead("<RepCpsDeclSemicolon>") + decl + repcpsdecl + getHead("</RepCpsDeclSemicolon>");
        }
    }

    public static class RepCpsDeclEpsilon extends RepCpsDecl {

        public RepCpsDeclEpsilon(int idendation) {
            super(idendation);
        }

        @Override
        public String toString() {
            return getHead("<RepCpsDeclEpsilon/>");
        }
    }

    public static abstract class OptCpsStoDecl extends Item {

        OptCpsStoDecl(int idendation) {
            super(idendation);
        }
    }

    public static class OptCpsStoDeclLocal extends OptCpsStoDecl {

        private final CpsStoDecl cpsstodecl;

        public OptCpsStoDeclLocal(CpsStoDecl cpsstodecl, int idendation) {
            super(idendation);
            this.cpsstodecl = cpsstodecl;
        }

        @Override
        public String toString() {
            return getHead("<OptCpsStoDeclLocal>") + cpsstodecl + getHead("</OptCpsStoDeclLocal>");
        }
    }

    public static class OptCpsStoDeclEpsilon extends OptCpsStoDecl {

        public OptCpsStoDeclEpsilon(int idendation) {
            super(idendation);
        }

        @Override
        public String toString() {
            return getHead("<OptCpsStoDeclEpsilon/>");
        }
    }

    public static class CpsStoDecl extends Item {

        private final StoDecl stodecl;

        private final RepCpsStoDecl repcpsstodecl;

        public CpsStoDecl(StoDecl stodecl, RepCpsStoDecl repcpsstodecl, int idendation) {
            super(idendation);
            this.stodecl = stodecl;
            this.repcpsstodecl = repcpsstodecl;
        }

        @Override
        public String toString() {
            return getHead("<CpsStoDecl>") + stodecl + repcpsstodecl + getHead("</CpsStoDecl>");
        }
    }

    public static abstract class RepCpsStoDecl extends Item {

        RepCpsStoDecl(int idendation) {
            super(idendation);
        }
    }

    public static class RepCpsStoDeclSemicolon extends RepCpsStoDecl {

        private final StoDecl stodecl;

        private final RepCpsStoDecl repcpsstodecl;

        public RepCpsStoDeclSemicolon(StoDecl stodecl, RepCpsStoDecl repcpsstodecl, int idendation) {
            super(idendation);
            this.stodecl = stodecl;
            this.repcpsstodecl = repcpsstodecl;
        }

        @Override
        public String toString() {
            return getHead("<RepCpsStoDeclSemicolon>") + stodecl + repcpsstodecl + getHead("</RepCpsStoDeclSemicolon>");
        }
    }

    public static class RepCpsStoDeclEpsilon extends RepCpsStoDecl {

        public RepCpsStoDeclEpsilon(int idendation) {
            super(idendation);
        }

        @Override
        public String toString() {
            return getHead("<RepCpsStoDeclEpsilon>") + getHead("</RepCpsStoDeclEpsilon>");
        }
    }

    public static class ProgParamList extends Item {

        private final OptProgParamList optprogparamlist;

        public ProgParamList(OptProgParamList optprogparamlist, int idendation) {
            super(idendation);
            this.optprogparamlist = optprogparamlist;
        }

        @Override
        public String toString() {
            return getHead("<ProgParamList>") + optprogparamlist + getHead("</ProgParamList>");
        }
    }

    public static abstract class OptProgParamList extends Item {

        OptProgParamList(int idendation) {
            super(idendation);
        }
    }

    public static class OptProgParamListExpression extends OptProgParamList {

        private final ProgParam progparam;

        private final RepProgParamList repprogparamlist;

        public OptProgParamListExpression(ProgParam progparam, RepProgParamList repprogparamlist, int idendation) {
            super(idendation);
            this.progparam = progparam;
            this.repprogparamlist = repprogparamlist;
        }

        @Override
        public String toString() {
            return getHead("<OptProgParamListExpression>") + progparam + repprogparamlist + getHead("</OptProgParamListExpression>");
        }
    }

    public static class OptProgParamListEpsilon extends OptProgParamList {

        public OptProgParamListEpsilon(int idendation) {
            super(idendation);
        }

        @Override
        public String toString() {
            return getHead("<OptProgParamListEpsilon/>");
        }
    }

    public static abstract class RepProgParamList extends Item {

        RepProgParamList(int idendation) {
            super(idendation);
        }
    }

    public static class RepProgParamListComma extends RepProgParamList {

        private final ProgParam progparam;

        private final RepProgParamList repprogparamlist;

        public RepProgParamListComma(ProgParam progparam, RepProgParamList repprogparamlist, int idendation) {
            super(idendation);
            this.progparam = progparam;
            this.repprogparamlist = repprogparamlist;
        }

        @Override
        public String toString() {
            return getHead("<RepProgParamListComma>") + progparam + repprogparamlist + getHead("</RepProgParamListComma>");
        }
    }

    public static class RepProgParamListEpsilon extends RepProgParamList {

        public RepProgParamListEpsilon(int idendation) {
            super(idendation);
        }

        @Override
        public String toString() {
            return getHead("<RepProgParamListEpsilon/>");
        }
    }

    public static abstract class ProgParam extends Item {

        ProgParam(int idendation) {
            super(idendation);
        }
    }

    public static class ProgParamFlowmode extends ProgParam {

        private final Tokens.FlowModeToken flowmode;

        private final OptChangemode optchangemode;

        private final TypedIdent typedident;

        public ProgParamFlowmode(Tokens.FlowModeToken flowmode, OptChangemode optchangemode, TypedIdent typedident, int idendation) {
            super(idendation);
            this.flowmode = flowmode;
            this.optchangemode = optchangemode;
            this.typedident = typedident;
        }

        @Override
        public String toString() {
            return getHead("<ProgParamFlowmode Mode='" + flowmode.getFlowMode() + "'>") + optchangemode + typedident + getHead("<ProgParamFlowmode>");
        }
    }

    public static class ProgParamExpression extends ProgParam {

        private final OptChangemode optchangemode;

        private final TypedIdent typedident;

        public ProgParamExpression(OptChangemode optchangemode, TypedIdent typedident, int idendation) {
            super(idendation);
            this.optchangemode = optchangemode;
            this.typedident = typedident;
        }

        @Override
        public String toString() {
            return getHead("<ProgParamExpression>") + optchangemode + typedident + getHead("<ProgParamExpression>");
        }
    }

    public static class ParamList extends Item {

        private final OptParamList optparamlist;

        public ParamList(OptParamList optparamlist, int idendation) {
            super(idendation);
            this.optparamlist = optparamlist;
        }

        @Override
        public String toString() {
            return getHead("<ParamList>") + optparamlist + getHead("</ParamList>");
        }
    }

    public static abstract class OptParamList extends Item {

        OptParamList(int idendation) {
            super(idendation);
        }
    }

    public static class OptParamListRepeating extends OptParamList {

        private final Param param;

        private final RepParamList repparamlist;

        public OptParamListRepeating(Param param, RepParamList repparamlist, int idendation) {
            super(idendation);
            this.param = param;
            this.repparamlist = repparamlist;
        }

        @Override
        public String toString() {
            return getHead("<OptParamListRepeating>") + param + repparamlist + getHead("</OptParamListRepeating>");
        }
    }

    public static class OptParamListEpsilon extends OptParamList {

        public OptParamListEpsilon(int idendation) {
            super(idendation);
        }

        @Override
        public String toString() {
            return getHead("<OptParamListEpsilon/>");
        }
    }

    public static abstract class RepParamList extends Item {

        RepParamList(int idendation) {
            super(idendation);
        }
    }

    public static class RepParamListComma extends RepParamList {

        private final Param param;

        private final RepParamList repparamlist;

        public RepParamListComma(Param param, RepParamList repparamlist, int idendation) {
            super(idendation);
            this.param = param;
            this.repparamlist = repparamlist;
        }

        @Override
        public String toString() {
            return getHead("<RepParamListComma>") + param + repparamlist + getHead("</RepParamListComma>");
        }
    }

    public static class RepParamListEpsilon extends RepParamList {

        public RepParamListEpsilon(int idendation) {
            super(idendation);
        }

        @Override
        public String toString() {
            return getHead("<RepParamListEpsilon/>");
        }
    }

    public static abstract class Param extends Item {

        Param(int idendation) {
            super(idendation);
        }
    }

    public static class ParamExpression extends Param {

        private final OptMechmode optmechmode;

        private final OptChangemode optchangemode;

        private final TypedIdent typedident;

        public ParamExpression(OptMechmode optmechmode, OptChangemode optchangemode, TypedIdent typedident, int idendation) {
            super(idendation);
            this.optmechmode = optmechmode;
            this.optchangemode = optchangemode;
            this.typedident = typedident;
        }

        @Override
        public String toString() {
            return getHead("<ParamExpression>") + optmechmode + optchangemode + typedident + getHead("</ParamExpression>");
        }
    }

    public static class ParamFlowmode extends Param {

        private final Tokens.FlowModeToken flowmode;

        private final OptMechmode optmechmode;

        private final OptChangemode optchangemode;

        private final TypedIdent typedident;

        public ParamFlowmode(Tokens.FlowModeToken flowmode, OptMechmode optmechmode, OptChangemode optchangemode, TypedIdent typedident, int idendation) {
            super(idendation);
            this.flowmode = flowmode;
            this.optmechmode = optmechmode;
            this.optchangemode = optchangemode;
            this.typedident = typedident;
        }

        @Override
        public String toString() {
            return getHead("<ParamFlowmode Mode='" + flowmode.getFlowMode() + "'>") + optmechmode + optchangemode + typedident + getHead("</ParamFlowmode>");
        }
    }

    public static class TypedIdent extends Item {

        private final Tokens.IdentifierToken identifier;

        private final Tokens.TypeToken type;

        public TypedIdent(Tokens.IdentifierToken identifier, Tokens.TypeToken type, int idendation) {
            super(idendation);
            this.identifier = identifier;
            this.type = type;
        }

        @Override
        public String toString() {
            return getHead("<TypedIdent Name='" + identifier.getName() + "' Type='" + type.getType() + "'/>");
        }
    }

    public static abstract class Cmd extends Item {

        Cmd(int idendation) {
            super(idendation);
        }
    }

    public static class CmdSkip extends Cmd {

        public CmdSkip(int idendation) {
            super(idendation);
        }

        @Override
        public String toString() {
            return getHead("<CmdSkip/>");
        }
    }

    public static class CmdExpression extends Cmd {

        private final Expr expr1;

        private final RepExprList repexprlist1;

        private final Expr expr2;

        private final RepExprList repexprlist2;

        public CmdExpression(Expr expr1, RepExprList repexprlist1, Expr expr2, RepExprList repexprlist2, int idendation) {
            super(idendation);
            this.expr1 = expr1;
            this.repexprlist1 = repexprlist1;
            this.expr2 = expr2;
            this.repexprlist2 = repexprlist2;
        }

        @Override
        public String toString() {
            return getHead("<CmdExpression>") + expr1 + repexprlist1 + expr2 + repexprlist2 + getHead("</CmdExpression>");
        }
    }

    public static class CmdIf extends Cmd {

        private final Expr expr;

        private final CpsCmd cpscmd;

        private final RepElseif repelseif;

        private final OptElse optelse;

        public CmdIf(Expr expr, CpsCmd cpscmd, RepElseif repelseif, OptElse optelse, int idendation) {
            super(idendation);
            this.expr = expr;
            this.cpscmd = cpscmd;
            this.repelseif = repelseif;
            this.optelse = optelse;
        }

        @Override
        public String toString() {
            return getHead("<CmdIf>") + expr + cpscmd + repelseif + optelse + getHead("</CmdIf>");
        }
    }

    public static class CmdSwitch extends Cmd {

        private final Expr expr;

        private final Tokens.LiteralToken literal;

        private final CpsCmd cpscmd;

        private final RepCase repcase;

        private final OptDefault optdefault;

        public CmdSwitch(Expr expr, Tokens.LiteralToken literal, CpsCmd cpscmd, RepCase repcase, OptDefault optdefault, int idendation) {
            super(idendation);
            this.expr = expr;
            this.literal = literal;
            this.cpscmd = cpscmd;
            this.repcase = repcase;
            this.optdefault = optdefault;
        }

        @Override
        public String toString() {
            return getHead("<CmdSwitch Value='" + literal.getValue() + "'>") + expr + cpscmd + repcase + optdefault + getHead("</CmdSwitch>");
        }
    }

    public static class CmdWhile extends Cmd {

        private final Expr expr;

        private final CpsCmd cpscmd;

        public CmdWhile(Expr expr, CpsCmd cpscmd, int idendation) {
            super(idendation);
            this.expr = expr;
            this.cpscmd = cpscmd;
        }

        @Override
        public String toString() {
            return getHead("<CmdWhile>") + expr + cpscmd + getHead("</CmdWhile>");
        }
    }

    public static class CmdCall extends Cmd {

        private final Tokens.IdentifierToken identifier;

        private final ExprList exprlist;

        private final OptGlobInits optglobinits;

        public CmdCall(Tokens.IdentifierToken identifier, ExprList exprlist, OptGlobInits optglobinits, int idendation) {
            super(idendation);
            this.identifier = identifier;
            this.exprlist = exprlist;
            this.optglobinits = optglobinits;
        }

        @Override
        public String toString() {
            return getHead("<CmdCall Name='" + identifier.getName() + "'>") + exprlist + optglobinits + getHead("</CmdCall>");
        }
    }

    public static class CmdDebug extends Cmd {

        private final Expr expr;

        public CmdDebug(Expr expr, int idendation) {
            super(idendation);
            this.expr = expr;
        }

        @Override
        public String toString() {
            return getHead("<CmdExpr>") + expr + getHead("</CmdExpr>");
        }
    }

    public static class CpsCmd extends Item {

        private final Cmd cmd;

        private final RepCpsCmd repcpscmd;

        public CpsCmd(Cmd cmd, RepCpsCmd repcpscmd, int idendation) {
            super(idendation);
            this.cmd = cmd;
            this.repcpscmd = repcpscmd;
        }

        @Override
        public String toString() {
            return getHead("<CpsCmd>") + cmd + repcpscmd + getHead("</CpsCmd>");
        }
    }

    public static abstract class RepCpsCmd extends Item {

        RepCpsCmd(int idendation) {
            super(idendation);
        }
    }

    public static class RepCpsCmdSemicolon extends RepCpsCmd {

        private final Cmd cmd;

        private final RepCpsCmd repcpscmd;

        public RepCpsCmdSemicolon(Cmd cmd, RepCpsCmd repcpscmd, int idendation) {
            super(idendation);
            this.cmd = cmd;
            this.repcpscmd = repcpscmd;
        }

        @Override
        public String toString() {
            return getHead("<RepCpsCmdSemicolon>") + cmd + repcpscmd + getHead("</RepCpsCmdSemicolon>");
        }
    }

    public static class RepCpsCmdEpsilon extends RepCpsCmd {

        public RepCpsCmdEpsilon(int idendation) {
            super(idendation);
        }

        @Override
        public String toString() {
            return getHead("<RepCpsCmdEpsilon/>");
        }
    }

    public static abstract class OptGlobInits extends Item {

        OptGlobInits(int idendation) {
            super(idendation);
        }
    }

    public static class OptGlobInitsInit extends OptGlobInits {

        private final Tokens.IdentifierToken identifier;

        private final RepIdents repidents;

        public OptGlobInitsInit(Tokens.IdentifierToken identifier, RepIdents repidents, int idendation) {
            super(idendation);
            this.identifier = identifier;
            this.repidents = repidents;
        }

        @Override
        public String toString() {
            return getHead("<OptGlobInitsInit Name='" + identifier.getName() + "'>") + repidents + getHead("</OptGlobInitsInit>");
        }
    }

    public static class OptGlobInitsEpsilon extends OptGlobInits {

        public OptGlobInitsEpsilon(int idendation) {
            super(idendation);
        }

        @Override
        public String toString() {
            return getHead("<OptGlobInitsEpsilon/>");
        }
    }

    public static abstract class RepIdents extends Item {

        RepIdents(int idendation) {
            super(idendation);
        }
    }

    public static class RepIdentsComma extends RepIdents {

        private final Tokens.IdentifierToken identifier;

        private final RepIdents repidents;

        public RepIdentsComma(Tokens.IdentifierToken identifier, RepIdents repidents, int idendation) {
            super(idendation);
            this.identifier = identifier;
            this.repidents = repidents;
        }

        @Override
        public String toString() {
            return getHead("<RepIdentsComma Name='" + identifier.getName() + "'>") + repidents + getHead("</RepIdentsComma>");
        }
    }

    public static class RepIdentsEpsilon extends RepIdents {

        public RepIdentsEpsilon(int idendation) {
            super(idendation);
        }

        @Override
        public String toString() {
            return getHead("<RepIdentsEpsilon/>");
        }
    }

    public static class Expr extends Item {

        private final Term1 term1;

        private final RepTerm1 repterm1;

        public Expr(Term1 term1, RepTerm1 repterm1, int idendation) {
            super(idendation);
            this.term1 = term1;
            this.repterm1 = repterm1;
        }

        @Override
        public String toString() {
            return getHead("<Expr>") + term1 + repterm1 + getHead("</Expr>");
        }
    }

    public static abstract class RepTerm1 extends Item {

        RepTerm1(int idendation) {
            super(idendation);
        }
    }

    public static class RepTerm1BoolOpr extends RepTerm1 {

        private final Tokens.BoolOprToken boolopr;

        private final Term1 term1;

        private final RepTerm1 repterm1;

        public RepTerm1BoolOpr(Tokens.BoolOprToken boolopr, Term1 term1, RepTerm1 repterm1, int idendation) {
            super(idendation);
            this.boolopr = boolopr;
            this.term1 = term1;
            this.repterm1 = repterm1;
        }

        @Override
        public String toString() {
            return getHead("<RepTerm1BoolOpr Opr='" + boolopr.getBoolOpr() + "'>") + term1 + repterm1 + getHead("</RepTerm1BoolOpr>");
        }
    }

    public static class RepTerm1Epsilon extends RepTerm1 {

        public RepTerm1Epsilon(int idendation) {
            super(idendation);
        }

        @Override
        public String toString() {
            return getHead("<RepTerm1Epsilon/>");
        }
    }

    public static class Term1 extends Item {

        private final Term2 term2;

        private final RepTerm2 repterm2;

        public Term1(Term2 term2, RepTerm2 repterm2, int idendation) {
            super(idendation);
            this.term2 = term2;
            this.repterm2 = repterm2;
        }

        @Override
        public String toString() {
            return getHead("<Term1>") + term2 + repterm2 + getHead("</Term1>");
        }
    }

    public static abstract class RepTerm2 extends Item {

        RepTerm2(int idendation) {
            super(idendation);
        }
    }

    public static class RepTerm2RelOpr extends RepTerm2 {

        private final Tokens.RelOprToken relopr;

        private final Term2 term2;

        private final RepTerm2 repterm2;

        public RepTerm2RelOpr(Tokens.RelOprToken relopr, Term2 term2, RepTerm2 repterm2, int idendation) {
            super(idendation);
            this.relopr = relopr;
            this.term2 = term2;
            this.repterm2 = repterm2;
        }

        @Override
        public String toString() {
            return getHead("<RepTerm2RelOpr Opr='" + relopr.getRelOpr() + "'>") + term2 + repterm2 + getHead("</RepTerm2RelOpr>");
        }
    }

    public static class RepTerm2Epsilon extends RepTerm2 {

        public RepTerm2Epsilon(int idendation) {
            super(idendation);
        }

        @Override
        public String toString() {
            return getHead("<RepTerm2Epsilon/>");
        }
    }

    public static class Term2 extends Item {

        private final Term3 term3;

        private final RepTerm3 repterm3;

        public Term2(Term3 term3, RepTerm3 repterm3, int idendation) {
            super(idendation);
            this.term3 = term3;
            this.repterm3 = repterm3;
        }

        @Override
        public String toString() {
            return getHead("<Term2>") + term3 + repterm3 + getHead("</Term2>");
        }
    }

    public static abstract class RepTerm3 extends Item {

        RepTerm3(int idendation) {
            super(idendation);
        }
    }

    public static class RepTerm3AddOpr extends RepTerm3 {

        private final Tokens.AddOprToken addopr;

        private final Term3 term3;

        private final RepTerm3 repterm3;

        public RepTerm3AddOpr(Tokens.AddOprToken addopr, Term3 term3, RepTerm3 repterm3, int idendation) {
            super(idendation);
            this.addopr = addopr;
            this.term3 = term3;
            this.repterm3 = repterm3;
        }

        @Override
        public String toString() {
            return getHead("<RepTerm3AddOpr Opr='" + addopr.getAddOpr() + "'>") + term3 + repterm3 + getHead("</RepTerm3AddOpr>");
        }
    }

    public static class RepTerm3Epsilon extends RepTerm3 {

        public RepTerm3Epsilon(int idendation) {
            super(idendation);
        }

        @Override
        public String toString() {
            return getHead("<RepTerm3Epsilon/>");
        }
    }

    public static class Term3 extends Item {

        private final Factor factor;

        private final RepFactor repfactor;

        public Term3(Factor factor, RepFactor repfactor, int idendation) {
            super(idendation);
            this.factor = factor;
            this.repfactor = repfactor;
        }

        @Override
        public String toString() {
            return getHead("<Term3>") + factor + repfactor + getHead("</Term3>");
        }
    }

    public static abstract class RepFactor extends Item {

        RepFactor(int idendation) {
            super(idendation);
        }
    }

    public static class RepFactorMultOpr extends RepFactor {

        private final Tokens.MultOprToken multopr;

        private final Factor factor;

        private final RepFactor repfactor;

        public RepFactorMultOpr(Tokens.MultOprToken multopr, Factor factor, RepFactor repfactor, int idendation) {
            super(idendation);
            this.multopr = multopr;
            this.factor = factor;
            this.repfactor = repfactor;
        }

        @Override
        public String toString() {
            return getHead("<RepFactorMultOpr Opr='" + multopr.getMultOpr() + "'>") + factor + repfactor + getHead("</RepFactorMultOpr>");
        }
    }

    public static class RepFactorEpsilon extends RepFactor {

        public RepFactorEpsilon(int idendation) {
            super(idendation);
        }

        @Override
        public String toString() {
            return getHead("<RepFactorEpsilon/>");
        }
    }

    public static abstract class Factor extends Item {

        Factor(int idendation) {
            super(idendation);
        }
    }

    public static class FactorLiteral extends Factor {

        private final Tokens.LiteralToken literal;

        public FactorLiteral(Tokens.LiteralToken literal, int idendation) {
            super(idendation);
            this.literal = literal;
        }

        @Override
        public String toString() {
            return getHead("<FactorLiteral Name='" + literal.getValue() + "'") + getHead("</FactorLiteral>");
        }
    }

    public static class FactorIdent extends Factor {

        private final Tokens.IdentifierToken identifier;

        private final OptInitOrExprList optinitorexprlist;

        public FactorIdent(Tokens.IdentifierToken identifier, OptInitOrExprList optinitorexprlist, int idendation) {
            super(idendation);
            this.identifier = identifier;
            this.optinitorexprlist = optinitorexprlist;
        }

        @Override
        public String toString() {
            return getHead("<FactorIdent Name='" + identifier.getName() + "'>") + optinitorexprlist + getHead("</FactorIdent>");
        }
    }

    public static class FactorExpression extends Factor {

        private final MonadicOpr monadicopr;

        private final Factor factor;

        public FactorExpression(MonadicOpr monadicopr, Factor factor, int idendation) {
            super(idendation);
            this.monadicopr = monadicopr;
            this.factor = factor;
        }

        @Override
        public String toString() {
            return getHead("<FactorExpression>") + monadicopr + factor + getHead("</FactorExpression>");
        }
    }

    public static class FactorLparen extends Factor {

        private final Expr expr;

        public FactorLparen(Expr expr, int idendation) {
            super(idendation);
            this.expr = expr;
        }

        @Override
        public String toString() {
            return getHead("<FactorLparen>") + expr + getHead("</FactorLparen>");
        }
    }

    public static abstract class OptInitOrExprList extends Item {

        OptInitOrExprList(int idendation) {
            super(idendation);
        }
    }

    public static class OptInitOrExprListInit extends OptInitOrExprList {

        public OptInitOrExprListInit(int idendation) {
            super(idendation);
        }

        @Override
        public String toString() {
            return getHead("<OptInitOrExprListInit/>");
        }
    }

    public static class OptInitOrExprListLparen extends OptInitOrExprList {

        private final ExprList exprlist;

        public OptInitOrExprListLparen(ExprList exprlist, int idendation) {
            super(idendation);
            this.exprlist = exprlist;
        }

        @Override
        public String toString() {
            return getHead("<OptInitOrExprListLparen>") + exprlist + getHead("</OptInitOrExprListLparen>");
        }
    }

    public static class OptInitOrExprListEpsilon extends OptInitOrExprList {

        public OptInitOrExprListEpsilon(int idendation) {
            super(idendation);
        }

        @Override
        public String toString() {
            return getHead("<OptInitOrExprListEpsilon/>");
        }
    }

    public static abstract class MonadicOpr extends Item {

        MonadicOpr(int idendation) {
            super(idendation);
        }
    }

    public static class MonadicOprNot extends MonadicOpr {

        public MonadicOprNot(int idendation) {
            super(idendation);
        }

        @Override
        public String toString() {
            return getHead("<MonadicOprAddopr/>");
        }
    }

    public static class MonadicOprAddopr extends MonadicOpr {

        private final Tokens.AddOprToken addopr;

        public MonadicOprAddopr(Tokens.AddOprToken addopr, int idendation) {
            super(idendation);
            this.addopr = addopr;
        }

        @Override
        public String toString() {
            return getHead("<MonadicOprAddopr Mode='" + addopr.getAddOpr() + "'>") + getHead("</MonadicOprAddopr>");
        }
    }

    public static class ExprList extends Item {

        private final OptExprList optexprlist;

        public ExprList(OptExprList optexprlist, int idendation) {
            super(idendation);
            this.optexprlist = optexprlist;
        }

        @Override
        public String toString() {
            return getHead("<ExprList>") + optexprlist + getHead("</ExprList>");
        }
    }

    public static abstract class OptExprList extends Item {

        OptExprList(int idendation) {
            super(idendation);
        }
    }

    public static class OptExprListExpression extends OptExprList {

        private final Expr expr;

        private final RepExprList repexprlist;

        public OptExprListExpression(Expr expr, RepExprList repexprlist, int idendation) {
            super(idendation);
            this.expr = expr;
            this.repexprlist = repexprlist;
        }

        @Override
        public String toString() {
            return getHead("<OptExprListExpression>") + expr + repexprlist + getHead("</OptExprListExpression>");
        }
    }

    public static class OptExprListEpsilon extends OptExprList {

        public OptExprListEpsilon(int idendation) {
            super(idendation);
        }

        @Override
        public String toString() {
            return getHead("<OptExprListEpsilon/>");
        }
    }

    public static abstract class RepExprList extends Item {

        RepExprList(int idendation) {
            super(idendation);
        }
    }

    public static class RepExprListComma extends RepExprList {

        private final Expr expr;

        private final RepExprList repexprlist;

        public RepExprListComma(Expr expr, RepExprList repexprlist, int idendation) {
            super(idendation);
            this.expr = expr;
            this.repexprlist = repexprlist;
        }

        @Override
        public String toString() {
            return getHead("<RepExprListComma>") + expr + repexprlist + getHead("</RepExprListComma>");
        }
    }

    public static class RepExprListEpsilon extends RepExprList {

        public RepExprListEpsilon(int idendation) {
            super(idendation);
        }

        @Override
        public String toString() {
            return getHead("<RepExprListEpsilon/>");
        }
    }

    public static abstract class RepCase extends Item {

        RepCase(int idendation) {
            super(idendation);
        }
    }

    public static class RepCaseCase extends RepCase {

        private final Tokens.LiteralToken literal;

        private final CpsCmd cpscmd;

        public RepCaseCase(Tokens.LiteralToken literal, CpsCmd cpscmd, int idendation) {
            super(idendation);
            this.literal = literal;
            this.cpscmd = cpscmd;
        }

        @Override
        public String toString() {
            return getHead("<RepCaseCase Name='" + literal.getValue() + "'>") + cpscmd + getHead("</RepCaseCase>");
        }
    }

    public static class RepCaseEpsilon extends RepCase {

        public RepCaseEpsilon(int idendation) {
            super(idendation);
        }

        @Override
        public String toString() {
            return getHead("<RepCaseEpsilon/>");
        }
    }

    public static abstract class OptDefault extends Item {

        OptDefault(int idendation) {
            super(idendation);
        }
    }

    public static class OptDefaultDefault extends OptDefault {

        private final CpsCmd cpscmd;

        public OptDefaultDefault(CpsCmd cpscmd, int idendation) {
            super(idendation);
            this.cpscmd = cpscmd;
        }

        @Override
        public String toString() {
            return getHead("<OptDefaultDefault/>") + cpscmd + getHead("</OptDefaultDefault>");
        }
    }

    public static class OptDefaultEpsilon extends OptDefault {

        public OptDefaultEpsilon(int idendation) {
            super(idendation);
        }

        @Override
        public String toString() {
            return getHead("<OptDefaultEpsilon/>");
        }
    }

    public static abstract class OptElse extends Item {

        OptElse(int idendation) {
            super(idendation);
        }
    }

    public static class OptElseElse extends OptElse {

        private final CpsCmd cpscmd;

        public OptElseElse(CpsCmd cpscmd, int idendation) {
            super(idendation);
            this.cpscmd = cpscmd;
        }

        @Override
        public String toString() {
            return getHead("<OptElseElse>") + cpscmd + getHead("</OptElseElse>");
        }
    }

    public static class OptElseEpsilon extends OptElse {

        public OptElseEpsilon(int idendation) {
            super(idendation);
        }

        @Override
        public String toString() {
            return getHead("<OptElseEpsilon/>");
        }
    }

    public static abstract class RepElseif extends Item {

        RepElseif(int idendation) {
            super(idendation);
        }
    }

    public static class RepElseifElseif extends RepElseif {

        private final Expr expr;

        private final CpsCmd cpscmd;

        private final RepElseif repelseif;

        public RepElseifElseif(Expr expr, CpsCmd cpscmd, RepElseif repelseif, int idendation) {
            super(idendation);
            this.expr = expr;
            this.cpscmd = cpscmd;
            this.repelseif = repelseif;
        }

        @Override
        public String toString() {
            return getHead("<RepElseifElseif>") + expr + cpscmd + repelseif + getHead("</RepElseifElseif>");
        }
    }

    public static class RepElseifEpsilon extends RepElseif {

        public RepElseifEpsilon(int idendation) {
            super(idendation);
        }

        @Override
        public String toString() {
            return getHead("<RepElseifEpsilon/>");
        }
    }
}
