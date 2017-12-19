package ch.fhnw.cpib.platform.parser;

import ch.fhnw.cpib.platform.parser.concrete.ConcreteTree;
import ch.fhnw.cpib.platform.parser.concrete.Context;
import ch.fhnw.cpib.platform.parser.exception.ParserException;
import ch.fhnw.cpib.platform.scanner.terminal.Terminal;
import ch.fhnw.cpib.platform.scanner.tokens.Token;
import ch.fhnw.cpib.platform.scanner.tokens.TokenList;
import ch.fhnw.cpib.platform.scanner.tokens.identifier.IdentifierToken;
import ch.fhnw.cpib.platform.scanner.tokens.literal.LiteralToken;
import ch.fhnw.cpib.platform.scanner.tokens.mode.ChangeModeToken;
import ch.fhnw.cpib.platform.scanner.tokens.type.TypeToken;

public class Parser {

    public Parser() {
    }

    public ConcreteTree.Program parseTokenList(TokenList tokenlist) throws ParserException {
        // Get the first token to start with
        tokenlist.resetCounter();
        Token token = tokenlist.nextToken();

        // Initialize the context
        Context context = new Context(tokenlist);
        context.setToken(token);
        context.setTerminal(token.getTerminal());

        // Parse the beginning
        ConcreteTree.Program program = parseProgram(context);
        consumeTerminal(context, Terminal.SENTINEL);
        return program;
    }

    private Token consumeTerminal(Context context, Terminal expectedterminal) throws ParserException {
        if (context.getTerminal() == expectedterminal) {
            //System.out.println("Consume: " + expectedterminal);
            Token consumedtoken = context.getToken();
            if (context.getTerminal() != Terminal.SENTINEL) {
                Token token = context.getTokenList().nextToken();
                context.setToken(token);
                context.setTerminal(token.getTerminal());
            }
            return consumedtoken;
        } else {
            throw new ParserException("Parser expected the terminal " + expectedterminal + ", but found the terminal " + context.getTerminal());
        }
    }

    private ConcreteTree.Program parseProgram(Context context) throws ParserException {
        //System.out.println(context.getTokenList());
        switch (context.getTerminal()) {
            case PROGRAM:
                consumeTerminal(context, Terminal.PROGRAM);
                IdentifierToken identifier = (IdentifierToken) consumeTerminal(context, Terminal.IDENT);
                ConcreteTree.ProgParamList progparamlist = parseProgParamList(context);
                ConcreteTree.OptCpsDecl optcpsdecl = parseOptCpsDecl(context);
                consumeTerminal(context, Terminal.DO);
                ConcreteTree.CpsCmd cpscmd = parseCpsCmd(context);
                consumeTerminal(context, Terminal.ENDPROGRAM);
                return new ConcreteTree.Program(identifier, progparamlist, optcpsdecl, cpscmd);
            default:
                throw new ParserException("Invalid terminal in program: " + context.getTerminal());
        }
    }

    private ConcreteTree.Decl parseDecl(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case CHANGEMODE:
            case IDENT:
                ConcreteTree.StoDecl stodecl = parseStoDecl(context);
                return new ConcreteTree.Decl();
            case FUN:
                ConcreteTree.FunDecl fundecl = parseFunDecl(context);
                return new ConcreteTree.Decl();
            case PROC:
                ConcreteTree.ProcDecl procdecl = parseProcDecl(context);
                return new ConcreteTree.Decl();
            default:
                throw new ParserException("Invalid terminal in decl: " + context.getTerminal());
        }
    }

    private ConcreteTree.StoDecl parseStoDecl(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case IDENT:
                ConcreteTree.TypedIdent typedident1 = parseTypedIdent(context);
                return new ConcreteTree.StoDecl();
            case CHANGEMODE:
                ChangeModeToken changemode = (ChangeModeToken) consumeTerminal(context, Terminal.CHANGEMODE);
                ConcreteTree.TypedIdent typedident2 = parseTypedIdent(context);
                return new ConcreteTree.StoDecl();
            default:
                throw new ParserException("Invalid terminal in stoDecl: " + context.getTerminal());
        }
    }

    private ConcreteTree.FunDecl parseFunDecl(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case FUN:
                consumeTerminal(context, Terminal.FUN);
                IdentifierToken identifier = (IdentifierToken) consumeTerminal(context, Terminal.IDENT);
                ConcreteTree.ParamList paramlist = parseParamList(context);
                consumeTerminal(context, Terminal.RETURNS);
                ConcreteTree.StoDecl stodecl = parseStoDecl(context);
                ConcreteTree.OptGlobImps optGlobImps = parseOptGlobImps(context);
                ConcreteTree.OptCpsStoDecl optcpsstodecl = parseOptCpsStoDecl(context);
                consumeTerminal(context, Terminal.DO);
                ConcreteTree.CpsCmd cpscmd = parseCpsCmd(context);
                consumeTerminal(context, Terminal.ENDFUN);
                return new ConcreteTree.FunDecl();
            default:
                throw new ParserException("Invalid terminal in funDecl: " + context.getTerminal());
        }
    }

    private ConcreteTree.ProcDecl parseProcDecl(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case PROC:
                consumeTerminal(context, Terminal.PROC);
                IdentifierToken identifier = (IdentifierToken) consumeTerminal(context, Terminal.IDENT);
                ConcreteTree.ParamList paramlist = parseParamList(context);
                ConcreteTree.OptGlobImps optglobimps = parseOptGlobImps(context);
                ConcreteTree.OptCpsStoDecl optcpsstodecl = parseOptCpsStoDecl(context);
                consumeTerminal(context, Terminal.DO);
                ConcreteTree.CpsCmd cpscmd = parseCpsCmd(context);
                consumeTerminal(context, Terminal.ENDPROC);
                return new ConcreteTree.ProcDecl();
            default:
                throw new ParserException("Invalid terminal in procDecl: " + context.getTerminal());

        }
    }

    private ConcreteTree.OptGlobImps parseOptGlobImps(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case GLOBAL:
                consumeTerminal(context, Terminal.GLOBAL);
                ConcreteTree.GlobImps globimps = parseGlobImps(context);
                return new ConcreteTree.OptGlobImps();
            case DO:
            case LOCAL:
                // TODO: Add epsilon
                return new ConcreteTree.OptGlobImps();
            default:
                throw new ParserException("Invalid terminal in optGlobImps: " + context.getTerminal());
        }
    }

    private ConcreteTree.GlobImps parseGlobImps(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case FLOWMODE:
            case IDENT:
            case CHANGEMODE:
                ConcreteTree.GlobImp globimp = parseGlobImp(context);
                ConcreteTree.RepGlobImps repglobimps = parseRepGlobImps(context);
                return new ConcreteTree.GlobImps();
            default:
                throw new ParserException("Invalid terminal in globImps: " + context.getTerminal());
        }
    }

    private ConcreteTree.RepGlobImps parseRepGlobImps(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case COMMA:
                consumeTerminal(context, Terminal.COMMA);
                ConcreteTree.GlobImp globimp = parseGlobImp(context);
                ConcreteTree.RepGlobImps repglobimps = parseRepGlobImps(context);
                return new ConcreteTree.RepGlobImps();
            case DO:
            case LOCAL:
                // TODO: Add epsilon
                return new ConcreteTree.RepGlobImps();
            default:
                throw new ParserException("Invalid terminal in repGlobImps: " + context.getTerminal());
        }
    }

    private ConcreteTree.OptChangemode parseOptChangemode(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case CHANGEMODE:
                consumeTerminal(context, Terminal.CHANGEMODE);
                return new ConcreteTree.OptChangemode();
            case IDENT:
                // TODO: Add epsilon
                return new ConcreteTree.OptChangemode();
            default:
                throw new ParserException("Invalid terminal in optChangemode: " + context.getTerminal());
        }
    }

    private ConcreteTree.OptMechmode parseOptMechmode(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case MECHMODE:
                consumeTerminal(context, Terminal.MECHMODE);
                return new ConcreteTree.OptMechmode();
            case IDENT:
            case CHANGEMODE:
                // TODO: Add epsilon
                return new ConcreteTree.OptMechmode();
            default:
                throw new ParserException("Invalid terminal in optMechmode: " + context.getTerminal());
        }
    }

    private ConcreteTree.GlobImp parseGlobImp(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case IDENT:
            case CHANGEMODE:
                ConcreteTree.OptChangemode optchangemode1 = parseOptChangemode(context);
                IdentifierToken identifier1 = (IdentifierToken) consumeTerminal(context, Terminal.IDENT);
                return new ConcreteTree.GlobImp();
            case FLOWMODE:
                consumeTerminal(context, Terminal.FLOWMODE);
                ConcreteTree.OptChangemode optchangemode2 = parseOptChangemode(context);
                IdentifierToken identifier2 = (IdentifierToken) consumeTerminal(context, Terminal.IDENT);
                return new ConcreteTree.GlobImp();
            default:
                throw new ParserException("Invalid terminal in globImp: " + context.getTerminal());
        }
    }

    private ConcreteTree.OptCpsDecl parseOptCpsDecl(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case GLOBAL:
                consumeTerminal(context, Terminal.GLOBAL);
                ConcreteTree.CpsDecl cpsecl = parseCpsDecl(context);
                return new ConcreteTree.OptCpsDecl();
            case DO:
                // TODO: Add epsilon
                return new ConcreteTree.OptCpsDecl();
            default:
                throw new ParserException("Invalid terminal in optCpsDecl: " + context.getTerminal());
        }
    }

    private ConcreteTree.CpsDecl parseCpsDecl(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case PROC:
            case FUN:
            case CHANGEMODE:
            case IDENT:
                ConcreteTree.Decl decl = parseDecl(context);
                ConcreteTree.RepCpsDecl repcpsdecl = parseRepCpsDecl(context);
                return new ConcreteTree.CpsDecl();
            default:
                throw new ParserException("Invalid terminal in cpsDecl: " + context.getTerminal());
        }
    }

    private ConcreteTree.RepCpsDecl parseRepCpsDecl(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case SEMICOLON:
                consumeTerminal(context, Terminal.SEMICOLON);
                ConcreteTree.Decl decl = parseDecl(context);
                ConcreteTree.RepCpsDecl repcpsdecl = parseRepCpsDecl(context);
                return new ConcreteTree.RepCpsDecl();
            case DO:
                // TODO: Add epsilon
                return new ConcreteTree.RepCpsDecl();
            default:
                throw new ParserException("Invalid terminal in repCpsDecl: " + context.getTerminal());
        }
    }

    private ConcreteTree.OptCpsStoDecl parseOptCpsStoDecl(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case LOCAL:
                consumeTerminal(context, Terminal.LOCAL);
                ConcreteTree.CpsStoDecl cpsstodecl = parseCpsStoDecl(context);
                return new ConcreteTree.OptCpsStoDecl();
            case DO:
                // TODO: Add epsilon
                return new ConcreteTree.OptCpsStoDecl();
            default:
                throw new ParserException("Invalid terminal in optCpsStoDecl: " + context.getTerminal());
        }
    }

    private ConcreteTree.CpsStoDecl parseCpsStoDecl(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case CHANGEMODE:
            case IDENT:
                ConcreteTree.StoDecl stodecl = parseStoDecl(context);
                ConcreteTree.RepCpsStoDecl repcpsstodecl = parseRepCpsStoDecl(context);
                return new ConcreteTree.CpsStoDecl();
            default:
                throw new ParserException("Invalid terminal in cpsStoDecl: " + context.getTerminal());
        }
    }

    private ConcreteTree.RepCpsStoDecl parseRepCpsStoDecl(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case SEMICOLON:
                consumeTerminal(context, Terminal.SEMICOLON);
                ConcreteTree.StoDecl stodecl = parseStoDecl(context);
                ConcreteTree.RepCpsStoDecl repcpsstodecl = parseRepCpsStoDecl(context);
                return new ConcreteTree.RepCpsStoDecl();
            case DO:
                // TODO: Add epsilon
                return new ConcreteTree.RepCpsStoDecl();
            default:
                throw new ParserException("Invalid terminal in repCpsStoDecl: " + context.getTerminal());
        }
    }

    private ConcreteTree.ProgParamList parseProgParamList(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case LPAREN:
                consumeTerminal(context, Terminal.LPAREN);
                ConcreteTree.OptProgParamList optprogparamlist = parseOptProgParamList(context);
                consumeTerminal(context, Terminal.RPAREN);
                return new ConcreteTree.ProgParamList();
            default:
                throw new ParserException("Invalid terminal in progParamList: " + context.getTerminal());
        }
    }

    private ConcreteTree.OptProgParamList parseOptProgParamList(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case FLOWMODE:
            case IDENT:
            case CHANGEMODE:
                ConcreteTree.ProgParam progparam = parseProgParam(context);
                ConcreteTree.RepProgParamList repprogparamlist = parseRepProgParamList(context);
                return new ConcreteTree.OptProgParamList();
            case RPAREN:
                // TODO: Add epsilon
                return new ConcreteTree.OptProgParamList();
            default:
                throw new ParserException("Invalid terminal in optProgParamList: " + context.getTerminal());
        }
    }

    private ConcreteTree.RepProgParamList parseRepProgParamList(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case COMMA:
                consumeTerminal(context, Terminal.COMMA);
                ConcreteTree.ProgParam progparam = parseProgParam(context);
                ConcreteTree.RepProgParamList repprogparamlist = parseRepProgParamList(context);
                return new ConcreteTree.RepProgParamList();
            case RPAREN:
                // TODO: Add epsilon
                return new ConcreteTree.RepProgParamList();
            default:
                throw new ParserException("Invalid terminal in repProgParamList: " + context.getTerminal());
        }
    }

    private ConcreteTree.ProgParam parseProgParam(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case FLOWMODE:
                consumeTerminal(context, Terminal.FLOWMODE);
                ConcreteTree.OptChangemode optchangemode1 = parseOptChangemode(context);
                ConcreteTree.TypedIdent typeident1 = parseTypedIdent(context);
                return new ConcreteTree.ProgParam();
            case IDENT:
            case CHANGEMODE:
                ConcreteTree.OptChangemode optchangemode2 = parseOptChangemode(context);
                ConcreteTree.TypedIdent typeident2 = parseTypedIdent(context);
                return new ConcreteTree.ProgParam();
            default:
                throw new ParserException("Invalid terminal in progPara: " + context.getTerminal());
        }
    }

    private ConcreteTree.ParamList parseParamList(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case LPAREN:
                consumeTerminal(context, Terminal.LPAREN);
                ConcreteTree.OptParamList optparamlist = parseOptParamList(context);
                consumeTerminal(context, Terminal.RPAREN);
                return new ConcreteTree.ParamList();
            default:
                throw new ParserException("Invalid terminal in paramList: " + context.getTerminal());
        }
    }

    private ConcreteTree.OptParamList parseOptParamList(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case FLOWMODE:
            case IDENT:
            case CHANGEMODE:
            case MECHMODE:
                ConcreteTree.Param param = parseParam(context);
                ConcreteTree.RepParamList repparamlist = parseRepParamList(context);
                return new ConcreteTree.OptParamList();
            case RPAREN:
                // TODO: Add epsilon
                return new ConcreteTree.OptParamList();
            default:
                throw new ParserException("Invalid terminal in optParamList: " + context.getTerminal());
        }
    }

    private ConcreteTree.RepParamList parseRepParamList(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case COMMA:
                consumeTerminal(context, Terminal.COMMA);
                ConcreteTree.Param param = parseParam(context);
                ConcreteTree.RepParamList repparamlist = parseRepParamList(context);
                return new ConcreteTree.RepParamList();
            case RPAREN:
                // TODO: Add epsilon
                return new ConcreteTree.RepParamList();
            default:
                throw new ParserException("Invalid terminal in repParamList: " + context.getTerminal());
        }
    }

    private ConcreteTree.Param parseParam(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case IDENT:
            case CHANGEMODE:
            case MECHMODE:
                ConcreteTree.OptMechmode mechmode1 = parseOptMechmode(context);
                ConcreteTree.OptChangemode changemode1 = parseOptChangemode(context);
                ConcreteTree.TypedIdent typedindent1 = parseTypedIdent(context);
                return new ConcreteTree.Param();
            case FLOWMODE:
                consumeTerminal(context, Terminal.FLOWMODE);
                ConcreteTree.OptMechmode mechmode2 = parseOptMechmode(context);
                ConcreteTree.OptChangemode changemode2 = parseOptChangemode(context);
                ConcreteTree.TypedIdent typedindent2 = parseTypedIdent(context);
                return new ConcreteTree.Param();
            default:
                throw new ParserException("Invalid terminal in param: " + context.getTerminal());
        }
    }

    private ConcreteTree.TypedIdent parseTypedIdent(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case IDENT:
                IdentifierToken identifier = (IdentifierToken) consumeTerminal(context, Terminal.IDENT);
                consumeTerminal(context, Terminal.COLON);
                TypeToken type = (TypeToken) consumeTerminal(context, Terminal.TYPE);
                return new ConcreteTree.TypedIdent();
            default:
                throw new ParserException("Invalid terminal in typedIdent: " + context.getTerminal());
        }
    }

    private ConcreteTree.Cmd parseCmd(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case SKIP:
                consumeTerminal(context, Terminal.SKIP);
                return new ConcreteTree.Cmd();
            case LPAREN:
            case ADDOPR:
            case NOT:
            case IDENT:
            case LITERAL:
                ConcreteTree.Expr expr1 = parseExpr(context);
                ConcreteTree.RepExprList repexprlist1 = parseRepExprList(context);
                consumeTerminal(context, Terminal.BECOMES);
                ConcreteTree.Expr expr2 = parseExpr(context);
                ConcreteTree.RepExprList repexprlist2 = parseRepExprList(context);
                return new ConcreteTree.Cmd();
            case IF:
                consumeTerminal(context, Terminal.IF);
                ConcreteTree.Expr expr3 = parseExpr(context);
                consumeTerminal(context, Terminal.THEN);
                ConcreteTree.CpsCmd cpscmd1 = parseCpsCmd(context);
                ConcreteTree.RepElseif repelseif = parseRepElseif(context);
                ConcreteTree.OptElse optelse1 = parseOptElse(context);
                consumeTerminal(context, Terminal.ENDIF);
                return new ConcreteTree.Cmd();
            case SWITCH:
                consumeTerminal(context, Terminal.SWITCH);
                ConcreteTree.Expr expr4 = parseExpr(context);
                consumeTerminal(context, Terminal.CASE);
                LiteralToken literal1 = (LiteralToken) consumeTerminal(context, Terminal.LITERAL);
                consumeTerminal(context, Terminal.THEN);
                ConcreteTree.CpsCmd cpscmd3 = parseCpsCmd(context);
                ConcreteTree.RepCase repcase1 = parseRepCase(context);
                ConcreteTree.OptDefault optdefault1 = parseOptDefault(context);
                consumeTerminal(context, Terminal.ENDSWITCH);
                return new ConcreteTree.Cmd();
            case WHILE:
                consumeTerminal(context, Terminal.WHILE);
                ConcreteTree.Expr expr5 = parseExpr(context);
                consumeTerminal(context, Terminal.DO);
                ConcreteTree.CpsCmd cpscmd4 = parseCpsCmd(context);
                consumeTerminal(context, Terminal.ENDWHILE);
                return new ConcreteTree.Cmd();
            case CALL:
                consumeTerminal(context, Terminal.CALL);
                IdentifierToken identifier1 = (IdentifierToken) consumeTerminal(context, Terminal.IDENT);
                ConcreteTree.ExprList exprlist1 = parseExprList(context);
                ConcreteTree.OptGlobInits optglobinits = parseOptGlobInits(context);
                return new ConcreteTree.Cmd();
            case DEBUGIN:
                consumeTerminal(context, Terminal.DEBUGIN);
                ConcreteTree.Expr expr6 = parseExpr(context);
                return new ConcreteTree.Cmd();
            case DEBUGOUT:
                consumeTerminal(context, Terminal.DEBUGOUT);
                ConcreteTree.Expr expr7 = parseExpr(context);
                return new ConcreteTree.Cmd();
            default:
                throw new ParserException("Invalid terminal in cmd: " + context.getTerminal());
        }
    }

    private ConcreteTree.CpsCmd parseCpsCmd(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case DEBUGOUT:
            case DEBUGIN:
            case CALL:
            case WHILE:
            case SWITCH:
            case IF:
            case LPAREN:
            case ADDOPR:
            case NOT:
            case IDENT:
            case LITERAL:
            case SKIP:
                ConcreteTree.Cmd cmd = parseCmd(context);
                ConcreteTree.RepCpsCmd repcpscmd = parseRepCpsCmd(context);
                return new ConcreteTree.CpsCmd();
            default:
                throw new ParserException("Invalid terminal in cpsCmd: " + context.getTerminal());
        }
    }

    private ConcreteTree.RepCpsCmd parseRepCpsCmd(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case SEMICOLON:
                consumeTerminal(context, Terminal.SEMICOLON);
                ConcreteTree.Cmd cmd = parseCmd(context);
                ConcreteTree.RepCpsCmd repcpscmd = parseRepCpsCmd(context);
                return new ConcreteTree.RepCpsCmd();
            case ENDWHILE:
            case ENDSWITCH:
            case DEFAULT:
            case CASE:
            case ENDIF:
            case ELSE:
            case ELSEIF:
            case ENDPROC:
            case ENDFUN:
            case ENDPROGRAM:
                // TODO: Add epsilon
                return new ConcreteTree.RepCpsCmd();
            default:
                throw new ParserException("Invalid terminal in repCpsCmd: " + context.getTerminal());
        }
    }

    private ConcreteTree.OptGlobInits parseOptGlobInits(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case INIT:
                consumeTerminal(context, Terminal.INIT);
                IdentifierToken identifier = (IdentifierToken) consumeTerminal(context, Terminal.IDENT);
                ConcreteTree.RepIdents repidents = parseRepIdents(context);
                return new ConcreteTree.OptGlobInits();
            case ENDWHILE:
            case ENDSWITCH:
            case DEFAULT:
            case CASE:
            case ENDIF:
            case ELSE:
            case ELSEIF:
            case ENDPROC:
            case ENDFUN:
            case ENDPROGRAM:
            case SEMICOLON:
                // TODO: Add epsilon
                return new ConcreteTree.OptGlobInits();
            default:
                throw new ParserException("Invalid terminal in optGlobInits: " + context.getTerminal());
        }
    }

    private ConcreteTree.RepIdents parseRepIdents(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case COMMA:
                consumeTerminal(context, Terminal.COMMA);
                IdentifierToken identifier = (IdentifierToken) consumeTerminal(context, Terminal.IDENT);
                ConcreteTree.RepIdents repidents = parseRepIdents(context);
                return new ConcreteTree.RepIdents();
            case ENDWHILE:
            case ENDSWITCH:
            case DEFAULT:
            case CASE:
            case ENDIF:
            case ELSE:
            case ELSEIF:
            case ENDPROC:
            case ENDFUN:
            case ENDPROGRAM:
            case SEMICOLON:
                // TODO: Add epsilon
                return new ConcreteTree.RepIdents();
            default:
                throw new ParserException("Invalid terminal in repIdents: " + context.getTerminal());
        }
    }

    private ConcreteTree.Expr parseExpr(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case LPAREN:
            case ADDOPR:
            case NOT:
            case IDENT:
            case LITERAL:
                ConcreteTree.Term1 term1 = parseTerm1(context);
                ConcreteTree.RepTerm1 repterm1 = parseRepTerm1(context);
                return new ConcreteTree.Expr();
            default:
                throw new ParserException("Invalid terminal in expr: " + context.getTerminal());
        }
    }

    private ConcreteTree.RepTerm1 parseRepTerm1(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case BOOLOPR:
                consumeTerminal(context, Terminal.BOOLOPR);
                ConcreteTree.Term1 term1 = parseTerm1(context);
                ConcreteTree.RepTerm1 repterm1 = parseRepTerm1(context);
                return new ConcreteTree.RepTerm1();
            case RPAREN:
            case DO:
            case THEN:
            case ENDWHILE:
            case ENDSWITCH:
            case DEFAULT:
            case CASE:
            case ENDIF:
            case ELSE:
            case ELSEIF:
            case ENDPROC:
            case ENDFUN:
            case ENDPROGRAM:
            case SEMICOLON:
            case BECOMES:
            case COMMA:
                // TODO: Add epsilon
                return new ConcreteTree.RepTerm1();
            default:
                throw new ParserException("Invalid terminal in repTerm2: " + context.getTerminal());
        }
    }

    private ConcreteTree.Term1 parseTerm1(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case LPAREN:
            case ADDOPR:
            case NOT:
            case IDENT:
            case LITERAL:
                ConcreteTree.Term2 term2 = parseTerm2(context);
                ConcreteTree.RepTerm2 repterm2 = parseRepTerm2(context);
                return new ConcreteTree.Term1();
            default:
                throw new ParserException("Invalid terminal in term1: " + context.getTerminal());
        }
    }

    private ConcreteTree.RepTerm2 parseRepTerm2(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case RELOPR:
                consumeTerminal(context, Terminal.RELOPR);
                ConcreteTree.Term2 term2 = parseTerm2(context);
                ConcreteTree.RepTerm2 repterm2 = parseRepTerm2(context);
                return new ConcreteTree.RepTerm2();
            case RPAREN:
            case DO:
            case THEN:
            case ENDWHILE:
            case ENDSWITCH:
            case DEFAULT:
            case CASE:
            case ENDIF:
            case ELSE:
            case ELSEIF:
            case ENDPROC:
            case ENDFUN:
            case ENDPROGRAM:
            case SEMICOLON:
            case BECOMES:
            case COMMA:
            case BOOLOPR:
                // TODO: Add epsilon
                return new ConcreteTree.RepTerm2();
            default:
                throw new ParserException("Invalid terminal in repTerm2: " + context.getTerminal());
        }
    }

    private ConcreteTree.Term2 parseTerm2(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case LPAREN:
            case ADDOPR:
            case NOT:
            case IDENT:
            case LITERAL:
                ConcreteTree.Term3 term3 = parseTerm3(context);
                ConcreteTree.RepTerm3 repterm3 = parseRepTerm3(context);
                return new ConcreteTree.Term2();
            default:
                throw new ParserException("Invalid terminal in term2: " + context.getTerminal());
        }
    }

    private ConcreteTree.RepTerm3 parseRepTerm3(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case ADDOPR:
                consumeTerminal(context, Terminal.ADDOPR);
                ConcreteTree.Term3 term3 = parseTerm3(context);
                ConcreteTree.RepTerm3 repterm3 = parseRepTerm3(context);
                return new ConcreteTree.RepTerm3();
            case RPAREN:
            case DO:
            case THEN:
            case ENDWHILE:
            case ENDSWITCH:
            case DEFAULT:
            case CASE:
            case ENDIF:
            case ELSE:
            case ELSEIF:
            case ENDPROC:
            case ENDFUN:
            case ENDPROGRAM:
            case SEMICOLON:
            case BECOMES:
            case COMMA:
            case BOOLOPR:
            case RELOPR:
                // TODO: Add epsilon
                return new ConcreteTree.RepTerm3();
            default:
                throw new ParserException("Invalid terminal in repTerm3: " + context.getTerminal());
        }
    }

    private ConcreteTree.Term3 parseTerm3(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case LPAREN:
            case ADDOPR:
            case NOT:
            case IDENT:
            case LITERAL:
                ConcreteTree.Factor factor = parseFactor(context);
                ConcreteTree.RepFactor repfactor = parseRepFactor(context);
                return new ConcreteTree.Term3();
            default:
                throw new ParserException("Invalid terminal in term3: " + context.getTerminal());
        }
    }

    private ConcreteTree.RepFactor parseRepFactor(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case MULTOPR:
                consumeTerminal(context, Terminal.MULTOPR);
                ConcreteTree.Factor factor = parseFactor(context);
                ConcreteTree.RepFactor repfactor = parseRepFactor(context);
                return new ConcreteTree.RepFactor();
            case RPAREN:
            case DO:
            case THEN:
            case ENDWHILE:
            case ENDSWITCH:
            case DEFAULT:
            case CASE:
            case ENDIF:
            case ELSE:
            case ELSEIF:
            case ENDPROC:
            case ENDFUN:
            case ENDPROGRAM:
            case SEMICOLON:
            case BECOMES:
            case COMMA:
            case BOOLOPR:
            case RELOPR:
            case ADDOPR:
                // TODO: Add epsilon
                return new ConcreteTree.RepFactor();
            default:
                throw new ParserException("Invalid terminal in repFactor: " + context.getTerminal());
        }
    }

    private ConcreteTree.Factor parseFactor(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case LITERAL:
                consumeTerminal(context, Terminal.LITERAL);
                return new ConcreteTree.Factor();
            case IDENT:
                IdentifierToken identifier1 = (IdentifierToken) consumeTerminal(context, Terminal.IDENT);
                ConcreteTree.OptInitOrExprList optinitorexprlist1 = parseOptInitOrExprList(context);
                return new ConcreteTree.Factor();
            case ADDOPR:
            case NOT:
                ConcreteTree.MonadicOpr monadicopr1 = parseMonadicOpr(context);
                ConcreteTree.Factor factor1 = parseFactor(context);
                return new ConcreteTree.Factor();
            case LPAREN:
                consumeTerminal(context, Terminal.LPAREN);
                ConcreteTree.Expr expr1 = parseExpr(context);
                consumeTerminal(context, Terminal.RPAREN);
                return new ConcreteTree.Factor();
            default:
                throw new ParserException("Invalid terminal in factor: " + context.getTerminal());
        }
    }

    private ConcreteTree.OptInitOrExprList parseOptInitOrExprList(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case INIT:
                consumeTerminal(context, Terminal.INIT);
                return new ConcreteTree.OptInitOrExprList();
            case LPAREN:
                ConcreteTree.ExprList exprlist = parseExprList(context);
                return new ConcreteTree.OptInitOrExprList();
            case RPAREN:
            case DO:
            case THEN:
            case ENDWHILE:
            case ENDSWITCH:
            case DEFAULT:
            case CASE:
            case ENDIF:
            case ELSE:
            case ELSEIF:
            case ENDPROC:
            case ENDFUN:
            case ENDPROGRAM:
            case SEMICOLON:
            case BECOMES:
            case COMMA:
            case BOOLOPR:
            case RELOPR:
            case ADDOPR:
            case MULTOPR:
                // TODO: Add epsilon
                return new ConcreteTree.OptInitOrExprList();
            default:
                throw new ParserException("Invalid terminal in optInitOrExprList: " + context.getTerminal());
        }
    }

    private ConcreteTree.MonadicOpr parseMonadicOpr(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case NOT:
                consumeTerminal(context, Terminal.NOT);
                return new ConcreteTree.MonadicOpr();
            case ADDOPR:
                consumeTerminal(context, Terminal.ADDOPR);
                return new ConcreteTree.MonadicOpr();
            default:
                throw new ParserException("Invalid terminal in monadicOpr: " + context.getTerminal());
        }
    }

    private ConcreteTree.ExprList parseExprList(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case LPAREN:
                consumeTerminal(context, Terminal.LPAREN);
                ConcreteTree.OptExprList optexprlist = parseOptExprList(context);
                consumeTerminal(context, Terminal.RPAREN);
                return new ConcreteTree.ExprList();
            default:
                throw new ParserException("Invalid terminal in exprList: " + context.getTerminal());
        }
    }

    private ConcreteTree.OptExprList parseOptExprList(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case LPAREN:
            case ADDOPR:
            case NOT:
            case IDENT:
            case LITERAL:
                ConcreteTree.Expr expr = parseExpr(context);
                ConcreteTree.RepExprList repexprlist = parseRepExprList(context);
                return new ConcreteTree.OptExprList();
            case RPAREN:
                // TODO: Add epsilon
                return new ConcreteTree.OptExprList();
            default:
                throw new ParserException("Invalid terminal in optExprList: " + context.getTerminal());
        }
    }

    private ConcreteTree.RepExprList parseRepExprList(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case COMMA:
                consumeTerminal(context, Terminal.COMMA);
                ConcreteTree.Expr expr = parseExpr(context);
                ConcreteTree.RepExprList repexprlist = parseRepExprList(context);
                return new ConcreteTree.RepExprList();
            case RPAREN:
            case ENDWHILE:
            case ENDSWITCH:
            case DEFAULT:
            case CASE:
            case ENDIF:
            case ELSE:
            case ELSEIF:
            case ENDPROC:
            case ENDFUN:
            case ENDPROGRAM:
            case SEMICOLON:
            case BECOMES:
                // TODO: Add epsilon
                return new ConcreteTree.RepExprList();
            default:
                throw new ParserException("Invalid terminal in repExprList: " + context.getTerminal());
        }

    }

    private ConcreteTree.RepCase parseRepCase(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case CASE:
                consumeTerminal(context, Terminal.CASE);
                LiteralToken literal = (LiteralToken) consumeTerminal(context, Terminal.LITERAL);
                consumeTerminal(context, Terminal.THEN);
                ConcreteTree.CpsCmd cpscmd = parseCpsCmd(context);
                return new ConcreteTree.RepCase();
            case ENDSWITCH:
                // TODO: Add epsilon
                return new ConcreteTree.RepCase();
            default:
                throw new ParserException("Invalid terminal in repCase: " + context.getTerminal());
        }
    }

    private ConcreteTree.OptDefault parseOptDefault(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case DEFAULT:
                consumeTerminal(context, Terminal.DEFAULT);
                consumeTerminal(context, Terminal.THEN);
                ConcreteTree.CpsCmd cpscmd = parseCpsCmd(context);
                return new ConcreteTree.OptDefault();
            case ENDSWITCH:
                // TODO: Add epsilon
                return new ConcreteTree.OptDefault();
            default:
                throw new ParserException("Invalid terminal in optDefault: " + context.getTerminal());
        }
    }

    private ConcreteTree.OptElse parseOptElse(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case ELSE:
                consumeTerminal(context, Terminal.ELSE);
                ConcreteTree.CpsCmd cpscmd = parseCpsCmd(context);
                return new ConcreteTree.OptElse();
            case ENDIF:
                // TODO: Add epsilon
                return new ConcreteTree.OptElse();
            default:
                throw new ParserException("Invalid terminal in optElse: " + context.getTerminal());
        }

    }

    private ConcreteTree.RepElseif parseRepElseif(Context context) throws ParserException {
        switch (context.getTerminal()) {
            case ELSEIF:
                consumeTerminal(context, Terminal.ELSEIF);
                ConcreteTree.Expr expr = parseExpr(context);
                consumeTerminal(context, Terminal.THEN);
                ConcreteTree.CpsCmd cpscmd = parseCpsCmd(context);
                ConcreteTree.RepElseif repelseif = parseRepElseif(context);
                return new ConcreteTree.RepElseif();
            case ENDIF:
            case ELSE:
                // TODO: Add epsilon
                return new ConcreteTree.RepElseif();
            default:
                throw new ParserException("Invalid terminal in repElseif: " + context.getTerminal());
        }
    }
}
