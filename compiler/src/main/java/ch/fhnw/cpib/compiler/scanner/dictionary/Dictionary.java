package ch.fhnw.cpib.compiler.scanner.dictionary;

import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;
import ch.fhnw.cpib.compiler.scanner.tokens.Token;
import ch.fhnw.cpib.compiler.scanner.tokens.mode.ChangeModeToken;
import ch.fhnw.cpib.compiler.scanner.tokens.mode.FlowModeToken;
import ch.fhnw.cpib.compiler.scanner.tokens.mode.MechModeToken;
import ch.fhnw.cpib.compiler.scanner.tokens.operator.AddOprToken;
import ch.fhnw.cpib.compiler.scanner.tokens.operator.BoolOprToken;
import ch.fhnw.cpib.compiler.scanner.tokens.operator.MultOprToken;
import ch.fhnw.cpib.compiler.scanner.tokens.operator.RelOprToken;
import ch.fhnw.cpib.compiler.scanner.tokens.type.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Dictionary {

    private final List<Token> tokens;

    public Dictionary() {
        this.tokens = new ArrayList<>();

        // Add all regular tokens
        tokens.add(new Token("call", Terminal.CALL));
        tokens.add(new Token("debugin", Terminal.DEBUGIN));
        tokens.add(new Token("debugout", Terminal.DEBUGOUT));
        tokens.add(new Token("do", Terminal.DO));
        tokens.add(new Token("else", Terminal.ELSE));
        tokens.add(new Token("endfun", Terminal.ENDFUN));
        tokens.add(new Token("endproc", Terminal.ENDPROC));
        tokens.add(new Token("endprogram", Terminal.ENDPROGRAM));
        tokens.add(new Token("endwhile", Terminal.ENDWHILE));
        tokens.add(new Token("endif", Terminal.ENDIF));
        tokens.add(new Token("fun", Terminal.FUN));
        tokens.add(new Token("global", Terminal.GLOBAL));
        tokens.add(new Token("if", Terminal.IF));
        tokens.add(new Token("init", Terminal.INIT));
        tokens.add(new Token("local", Terminal.LOCAL));
        tokens.add(new Token("not", Terminal.NOT));
        tokens.add(new Token("proc", Terminal.PROC));
        tokens.add(new Token("program", Terminal.PROGRAM));
        tokens.add(new Token("returns", Terminal.RETURNS));
        tokens.add(new Token("skip", Terminal.SKIP));
        tokens.add(new Token("then", Terminal.THEN));
        tokens.add(new Token("while", Terminal.WHILE));
        tokens.add(new Token("sentinel", Terminal.SENTINEL));

        // Symbols
        tokens.add(new Token("(", Terminal.LPAREN));
        tokens.add(new Token(")", Terminal.RPAREN));
        tokens.add(new Token(",", Terminal.COMMA));
        tokens.add(new Token(";", Terminal.SEMICOLON));
        tokens.add(new Token(":", Terminal.COLON));
        tokens.add(new Token(":=", Terminal.BECOMES));

        // Add all type tokens
        tokens.add(new TypeToken("bool", Terminal.TYPE, TypeToken.Type.BOOL));
        tokens.add(new TypeToken("int", Terminal.TYPE, TypeToken.Type.INT));
        tokens.add(new TypeToken("int32", Terminal.TYPE, TypeToken.Type.INT));
        tokens.add(new TypeToken("int64", Terminal.TYPE, TypeToken.Type.INT64));

        // Add all mode tokens
        tokens.add(new ChangeModeToken("var", Terminal.CHANGEMODE, ChangeModeToken.ChangeMode.VAR));
        tokens.add(new ChangeModeToken("const", Terminal.CHANGEMODE, ChangeModeToken.ChangeMode.CONST));
        tokens.add(new FlowModeToken("in", Terminal.FLOWMODE, FlowModeToken.FlowMode.IN));
        tokens.add(new FlowModeToken("inout", Terminal.FLOWMODE, FlowModeToken.FlowMode.INOUT));
        tokens.add(new FlowModeToken("out", Terminal.FLOWMODE, FlowModeToken.FlowMode.OUT));
        tokens.add(new MechModeToken("copy", Terminal.MECHMODE, MechModeToken.MechMode.COPY));
        tokens.add(new MechModeToken("ref", Terminal.MECHMODE, MechModeToken.MechMode.REF));

        // All all operator tokens
        tokens.add(new AddOprToken("+", Terminal.ADDOPR, AddOprToken.AddOpr.PLUS));
        tokens.add(new AddOprToken("-", Terminal.ADDOPR, AddOprToken.AddOpr.MINUS));
        tokens.add(new BoolOprToken("&&", Terminal.BOOLOPR, BoolOprToken.Bool.AND));
        tokens.add(new BoolOprToken("||", Terminal.BOOLOPR, BoolOprToken.Bool.OR));
        tokens.add(new BoolOprToken("&?", Terminal.BOOLOPR, BoolOprToken.Bool.CAND));
        tokens.add(new BoolOprToken("|?", Terminal.BOOLOPR, BoolOprToken.Bool.COR));
        tokens.add(new MultOprToken("*", Terminal.MULTOPR, MultOprToken.MultOpr.TIMES));
        tokens.add(new MultOprToken("/", Terminal.MULTOPR, MultOprToken.MultOpr.DIVE));
        tokens.add(new MultOprToken("%", Terminal.MULTOPR, MultOprToken.MultOpr.MODE));
        tokens.add(new RelOprToken("==", Terminal.RELOPR, RelOprToken.RelOpr.EQ));
        tokens.add(new RelOprToken("!=", Terminal.RELOPR, RelOprToken.RelOpr.NE));
        tokens.add(new RelOprToken("<", Terminal.RELOPR, RelOprToken.RelOpr.LT));
        tokens.add(new RelOprToken(">", Terminal.RELOPR, RelOprToken.RelOpr.GT));
        tokens.add(new RelOprToken("<=", Terminal.RELOPR, RelOprToken.RelOpr.LE));
        tokens.add(new RelOprToken(">=", Terminal.RELOPR, RelOprToken.RelOpr.GE));
    }

    public Optional<Token> lookupToken(String symbol) {
        return tokens.stream().filter(token -> symbol.equalsIgnoreCase(token.getSymbol())).findFirst();
    }
}
