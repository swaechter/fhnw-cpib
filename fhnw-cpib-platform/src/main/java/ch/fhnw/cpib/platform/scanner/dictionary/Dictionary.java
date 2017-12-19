package ch.fhnw.cpib.platform.scanner.dictionary;

import ch.fhnw.cpib.platform.scanner.tokens.Terminal;
import ch.fhnw.cpib.platform.scanner.tokens.Tokens;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Dictionary {

    private final Map<String, Tokens.Token> tokens;

    public Dictionary() {
        this.tokens = new HashMap<>();

        // Add all regular tokens
        tokens.put("call", new Tokens.Token(Terminal.CALL));
        tokens.put("case", new Tokens.Token(Terminal.CASE));
        tokens.put("debugin", new Tokens.Token(Terminal.DEBUGIN));
        tokens.put("debugout", new Tokens.Token(Terminal.DEBUGOUT));
        tokens.put("default", new Tokens.Token(Terminal.DEFAULT));
        tokens.put("do", new Tokens.Token(Terminal.DO));
        tokens.put("else", new Tokens.Token(Terminal.ELSE));
        tokens.put("elseif", new Tokens.Token(Terminal.ELSEIF));
        tokens.put("endfun", new Tokens.Token(Terminal.ENDFUN));
        tokens.put("endproc", new Tokens.Token(Terminal.ENDPROC));
        tokens.put("endprogram", new Tokens.Token(Terminal.ENDPROGRAM));
        tokens.put("endswitch", new Tokens.Token(Terminal.ENDSWITCH));
        tokens.put("endwhile", new Tokens.Token(Terminal.ENDWHILE));
        tokens.put("endif", new Tokens.Token(Terminal.ENDIF));
        tokens.put("fun", new Tokens.Token(Terminal.FUN));
        tokens.put("global", new Tokens.Token(Terminal.GLOBAL));
        tokens.put("if", new Tokens.Token(Terminal.IF));
        tokens.put("init", new Tokens.Token(Terminal.INIT));
        tokens.put("local", new Tokens.Token(Terminal.LOCAL));
        tokens.put("not", new Tokens.Token(Terminal.NOT));
        tokens.put("proc", new Tokens.Token(Terminal.PROC));
        tokens.put("program", new Tokens.Token(Terminal.PROGRAM));
        tokens.put("returns", new Tokens.Token(Terminal.RETURNS));
        tokens.put("skip", new Tokens.Token(Terminal.SKIP));
        tokens.put("switch", new Tokens.Token(Terminal.SWITCH));
        tokens.put("then", new Tokens.Token(Terminal.THEN));
        tokens.put("while", new Tokens.Token(Terminal.WHILE));
        tokens.put("sentinel", new Tokens.Token(Terminal.SENTINEL));

        // Add all regular legacy tokens
        tokens.put("?", new Tokens.Token(Terminal.DEBUGIN));
        tokens.put("!", new Tokens.Token(Terminal.DEBUGOUT));

        // Symbols
        tokens.put("(", new Tokens.Token(Terminal.LPAREN));
        tokens.put(")", new Tokens.Token(Terminal.RPAREN));
        tokens.put(",", new Tokens.Token(Terminal.COMMA));
        tokens.put(";", new Tokens.Token(Terminal.SEMICOLON));
        tokens.put(":", new Tokens.Token(Terminal.COLON));
        tokens.put(":=", new Tokens.Token(Terminal.BECOMES));

        // Add all type tokens
        tokens.put("bool", new Tokens.TypeToken(Terminal.TYPE, Tokens.TypeToken.Type.BOOL));
        tokens.put("int", new Tokens.TypeToken(Terminal.TYPE, Tokens.TypeToken.Type.INT));
        tokens.put("int32", new Tokens.TypeToken(Terminal.TYPE, Tokens.TypeToken.Type.INT));
        tokens.put("int64", new Tokens.TypeToken(Terminal.TYPE, Tokens.TypeToken.Type.INT64));

        // Add all mode tokens
        tokens.put("var", new Tokens.ChangeModeToken(Terminal.CHANGEMODE, Tokens.ChangeModeToken.ChangeMode.VAR));
        tokens.put("const", new Tokens.ChangeModeToken(Terminal.CHANGEMODE, Tokens.ChangeModeToken.ChangeMode.CONST));
        tokens.put("in", new Tokens.FlowModeToken(Terminal.FLOWMODE, Tokens.FlowModeToken.FlowMode.IN));
        tokens.put("inout", new Tokens.FlowModeToken(Terminal.FLOWMODE, Tokens.FlowModeToken.FlowMode.INOUT));
        tokens.put("out", new Tokens.FlowModeToken(Terminal.FLOWMODE, Tokens.FlowModeToken.FlowMode.OUT));
        tokens.put("copy", new Tokens.MechModeToken(Terminal.MECHMODE, Tokens.MechModeToken.MechMode.COPY));
        tokens.put("ref", new Tokens.MechModeToken(Terminal.MECHMODE, Tokens.MechModeToken.MechMode.REF));

        // All all operator tokens
        tokens.put("+", new Tokens.AddOprToken(Terminal.ADDOPR, Tokens.AddOprToken.AddOpr.PLUS));
        tokens.put("-", new Tokens.AddOprToken(Terminal.ADDOPR, Tokens.AddOprToken.AddOpr.MINUS));
        tokens.put("&&", new Tokens.BoolOprToken(Terminal.BOOLOPR, Tokens.BoolOprToken.Bool.AND));
        tokens.put("||", new Tokens.BoolOprToken(Terminal.BOOLOPR, Tokens.BoolOprToken.Bool.OR));
        tokens.put("&?", new Tokens.BoolOprToken(Terminal.BOOLOPR, Tokens.BoolOprToken.Bool.CAND));
        tokens.put("|?", new Tokens.BoolOprToken(Terminal.BOOLOPR, Tokens.BoolOprToken.Bool.COR));
        tokens.put("*", new Tokens.MultOprToken(Terminal.MULTOPR, Tokens.MultOprToken.MultOpr.TIMES));
        tokens.put("/", new Tokens.MultOprToken(Terminal.MULTOPR, Tokens.MultOprToken.MultOpr.DIVE));
        tokens.put("%", new Tokens.MultOprToken(Terminal.MULTOPR, Tokens.MultOprToken.MultOpr.MODE));
        tokens.put("==", new Tokens.RelOprToken(Terminal.RELOPR, Tokens.RelOprToken.RelOpr.EQ));
        tokens.put("=", new Tokens.RelOprToken(Terminal.RELOPR, Tokens.RelOprToken.RelOpr.EQ));
        tokens.put("/=", new Tokens.RelOprToken(Terminal.RELOPR, Tokens.RelOprToken.RelOpr.NE));
        tokens.put("<", new Tokens.RelOprToken(Terminal.RELOPR, Tokens.RelOprToken.RelOpr.LT));
        tokens.put(">", new Tokens.RelOprToken(Terminal.RELOPR, Tokens.RelOprToken.RelOpr.GT));
        tokens.put("<=", new Tokens.RelOprToken(Terminal.RELOPR, Tokens.RelOprToken.RelOpr.LE));
        tokens.put(">=", new Tokens.RelOprToken(Terminal.RELOPR, Tokens.RelOprToken.RelOpr.GE));

        // Add all legacy operator tokens
        tokens.put("divE", new Tokens.MultOprToken(Terminal.MULTOPR, Tokens.MultOprToken.MultOpr.DIVE));
        tokens.put("divF", new Tokens.MultOprToken(Terminal.MULTOPR, Tokens.MultOprToken.MultOpr.DIVE));
        tokens.put("divT", new Tokens.MultOprToken(Terminal.MULTOPR, Tokens.MultOprToken.MultOpr.DIVE));
        tokens.put("modE", new Tokens.MultOprToken(Terminal.MULTOPR, Tokens.MultOprToken.MultOpr.MODE));
        tokens.put("modF", new Tokens.MultOprToken(Terminal.MULTOPR, Tokens.MultOprToken.MultOpr.MODE));
        tokens.put("modT", new Tokens.MultOprToken(Terminal.MULTOPR, Tokens.MultOprToken.MultOpr.MODE));
    }

    public Optional<Tokens.Token> lookupToken(String symbol) {
        return tokens.entrySet().stream().filter(e -> e.getKey().equalsIgnoreCase(symbol)).map(Map.Entry::getValue).findFirst();
    }

    public Tokens.Token lookupLiteral(String name) {
        return new Tokens.LiteralToken(name, Terminal.LITERAL);
    }

    public Tokens.Token lookupIdentifier(String value) {
        return new Tokens.IdentifierToken(value, Terminal.IDENT);
    }

    public Tokens.Token lookupSentinel() {
        return new Tokens.SentinelToken(Terminal.SENTINEL);
    }
}
