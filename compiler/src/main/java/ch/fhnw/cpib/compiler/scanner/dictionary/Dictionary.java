package ch.fhnw.cpib.compiler.scanner.dictionary;

import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;
import ch.fhnw.cpib.compiler.scanner.tokens.Token;
import ch.fhnw.cpib.compiler.scanner.tokens.identifier.IdentifierToken;
import ch.fhnw.cpib.compiler.scanner.tokens.literal.LiteralToken;
import ch.fhnw.cpib.compiler.scanner.tokens.mode.ChangeModeToken;
import ch.fhnw.cpib.compiler.scanner.tokens.mode.FlowModeToken;
import ch.fhnw.cpib.compiler.scanner.tokens.mode.MechModeToken;
import ch.fhnw.cpib.compiler.scanner.tokens.operator.AddOprToken;
import ch.fhnw.cpib.compiler.scanner.tokens.operator.BoolOprToken;
import ch.fhnw.cpib.compiler.scanner.tokens.operator.MultOprToken;
import ch.fhnw.cpib.compiler.scanner.tokens.operator.RelOprToken;
import ch.fhnw.cpib.compiler.scanner.tokens.sentinel.SentinelToken;
import ch.fhnw.cpib.compiler.scanner.tokens.type.TypeToken;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Dictionary {

    private final Map<String, Token> tokens;

    public Dictionary() {
        this.tokens = new HashMap<>();

        // Add all regular tokens
        tokens.put("call", new Token(Terminal.CALL));
        tokens.put("debugin", new Token(Terminal.DEBUGIN));
        tokens.put("debugout", new Token(Terminal.DEBUGOUT));
        tokens.put("do", new Token(Terminal.DO));
        tokens.put("else", new Token(Terminal.ELSE));
        tokens.put("endfun", new Token(Terminal.ENDFUN));
        tokens.put("endproc", new Token(Terminal.ENDPROC));
        tokens.put("endprogram", new Token(Terminal.ENDPROGRAM));
        tokens.put("endwhile", new Token(Terminal.ENDWHILE));
        tokens.put("endif", new Token(Terminal.ENDIF));
        tokens.put("fun", new Token(Terminal.FUN));
        tokens.put("global", new Token(Terminal.GLOBAL));
        tokens.put("if", new Token(Terminal.IF));
        tokens.put("init", new Token(Terminal.INIT));
        tokens.put("local", new Token(Terminal.LOCAL));
        tokens.put("not", new Token(Terminal.NOT));
        tokens.put("proc", new Token(Terminal.PROC));
        tokens.put("program", new Token(Terminal.PROGRAM));
        tokens.put("returns", new Token(Terminal.RETURNS));
        tokens.put("skip", new Token(Terminal.SKIP));
        tokens.put("then", new Token(Terminal.THEN));
        tokens.put("while", new Token(Terminal.WHILE));
        tokens.put("sentinel", new Token(Terminal.SENTINEL));

        // Symbols
        tokens.put("(", new Token(Terminal.LPAREN));
        tokens.put(")", new Token(Terminal.RPAREN));
        tokens.put(",", new Token(Terminal.COMMA));
        tokens.put(";", new Token(Terminal.SEMICOLON));
        tokens.put(":", new Token(Terminal.COLON));
        tokens.put(":=", new Token(Terminal.BECOMES));

        // Add all type tokens
        tokens.put("bool", new TypeToken(Terminal.TYPE, TypeToken.Type.BOOL));
        tokens.put("int", new TypeToken(Terminal.TYPE, TypeToken.Type.INT));
        tokens.put("int32", new TypeToken(Terminal.TYPE, TypeToken.Type.INT));
        tokens.put("int64", new TypeToken(Terminal.TYPE, TypeToken.Type.INT64));

        // Add all mode tokens
        tokens.put("var", new ChangeModeToken(Terminal.CHANGEMODE, ChangeModeToken.ChangeMode.VAR));
        tokens.put("const", new ChangeModeToken(Terminal.CHANGEMODE, ChangeModeToken.ChangeMode.CONST));
        tokens.put("in", new FlowModeToken(Terminal.FLOWMODE, FlowModeToken.FlowMode.IN));
        tokens.put("inout", new FlowModeToken(Terminal.FLOWMODE, FlowModeToken.FlowMode.INOUT));
        tokens.put("out", new FlowModeToken(Terminal.FLOWMODE, FlowModeToken.FlowMode.OUT));
        tokens.put("copy", new MechModeToken(Terminal.MECHMODE, MechModeToken.MechMode.COPY));
        tokens.put("ref", new MechModeToken(Terminal.MECHMODE, MechModeToken.MechMode.REF));

        // All all operator tokens
        tokens.put("+", new AddOprToken(Terminal.ADDOPR, AddOprToken.AddOpr.PLUS));
        tokens.put("-", new AddOprToken(Terminal.ADDOPR, AddOprToken.AddOpr.MINUS));
        tokens.put("&&", new BoolOprToken(Terminal.BOOLOPR, BoolOprToken.Bool.AND));
        tokens.put("||", new BoolOprToken(Terminal.BOOLOPR, BoolOprToken.Bool.OR));
        tokens.put("&?", new BoolOprToken(Terminal.BOOLOPR, BoolOprToken.Bool.CAND));
        tokens.put("|?", new BoolOprToken(Terminal.BOOLOPR, BoolOprToken.Bool.COR));
        tokens.put("*", new MultOprToken(Terminal.MULTOPR, MultOprToken.MultOpr.TIMES));
        tokens.put("/", new MultOprToken(Terminal.MULTOPR, MultOprToken.MultOpr.DIVE));
        tokens.put("%", new MultOprToken(Terminal.MULTOPR, MultOprToken.MultOpr.MODE));
        tokens.put("==", new RelOprToken(Terminal.RELOPR, RelOprToken.RelOpr.EQ));
        tokens.put("!=", new RelOprToken(Terminal.RELOPR, RelOprToken.RelOpr.NE));
        tokens.put("<", new RelOprToken(Terminal.RELOPR, RelOprToken.RelOpr.LT));
        tokens.put(">", new RelOprToken(Terminal.RELOPR, RelOprToken.RelOpr.GT));
        tokens.put("<=", new RelOprToken(Terminal.RELOPR, RelOprToken.RelOpr.LE));
        tokens.put(">=", new RelOprToken(Terminal.RELOPR, RelOprToken.RelOpr.GE));
    }

    public Optional<Token> lookupToken(String symbol) {
        return tokens.entrySet().stream().filter(e -> e.getKey().equalsIgnoreCase(symbol)).map(Map.Entry::getValue).findFirst();
    }

    public Token lookupLiteral(String name) {
        return new LiteralToken(name, Terminal.LITERAL);
    }

    public Token lookupIdentifier(String value) {
        return new IdentifierToken(value, Terminal.IDENT);
    }

    public Token lookupSentinel() {
        return new SentinelToken(Terminal.SENTINEL);
    }
}
