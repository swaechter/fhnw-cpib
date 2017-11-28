package ch.fhnw.cpib.compiler.scanner;

import ch.fhnw.cpib.compiler.scanner.terminal.Terminal;
import ch.fhnw.cpib.compiler.scanner.tokens.Token;
import ch.fhnw.cpib.compiler.scanner.tokens.TokenList;
import ch.fhnw.cpib.compiler.scanner.tokens.sentinel.SentinelToken;
import ch.fhnw.cpib.compiler.utils.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class ScannerTest {

    private final List<String> stringtokens = Arrays.asList(

        // Line: program intDiv(in  m:int32, in  n:int32,
        "PROGRAM",
        "(IDENT,intDiv)",
        "LPAREN",
        "(FLOWMODE,IN)",
        "(IDENT,m)",
        "COLON",
        "(TYPE,INT)",
        "COMMA",
        "(FLOWMODE,IN)",
        "(IDENT,n)",
        "COLON",
        "(TYPE,INT)",
        "COMMA",

        // Line: out q:int32, out r:int32)
        "(FLOWMODE,OUT)",
        "(IDENT,q)",
        "COLON",
        "(TYPE,INT)",
        "COMMA",
        "(FLOWMODE,OUT)",
        "(IDENT,r)",
        "COLON",
        "(TYPE,INT)",
        "RPAREN",

        // Line: global
        "GLOBAL",

        // Line: proc divide(in copy const m:int32, in copy const n:int32,
        "PROC",
        "(IDENT,divide)",
        "LPAREN",
        "(FLOWMODE,IN)",
        "(MECHMODE,COPY)",
        "(CHANGEMODE,CONST)",
        "(IDENT,m)",
        "COLON",
        "(TYPE,INT)",
        "COMMA",
        "(FLOWMODE,IN)",
        "(MECHMODE,COPY)",
        "(CHANGEMODE,CONST)",
        "(IDENT,n)",
        "COLON",
        "(TYPE,INT)",
        "COMMA",

        // Line: out ref var   q:int32, out ref var   r:int32)
        "(FLOWMODE,OUT)",
        "(MECHMODE,REF)",
        "(CHANGEMODE,VAR)",
        "(IDENT,q)",
        "COLON",
        "(TYPE,INT)",
        "COMMA",
        "(FLOWMODE,OUT)",
        "(MECHMODE,REF)",
        "(CHANGEMODE,VAR)",
        "(IDENT,r)",
        "COLON",
        "(TYPE,INT)",
        "RPAREN",

        // Line: do
        "DO",

        // Line: var p := 2 /2 // Not a comment
        "(CHANGEMODE,VAR)",
        "(IDENT,p)",
        "BECOMES",
        "(LITERAL,2)",
        "(MULTOPR,DIVE)",
        "(LITERAL,2)",

        // Line: q init := 0;
        "(IDENT,q)",
        "INIT",
        "BECOMES",
        "(LITERAL,0)",
        "SEMICOLON",

        // Line: r init := m;
        "(IDENT,r)",
        "INIT",
        "BECOMES",
        "(IDENT,m)",
        "SEMICOLON",

        // Line if p == 2 then
        "IF",
        "(IDENT,p)",
        "(RELOPR,EQ)",
        "(LITERAL,2)",
        "THEN",

        // Line: debugout p
        "DEBUGOUT",
        "(IDENT,p)",

        // Line: elseif p == 3 then
        "ELSEIF",
        "(IDENT,p)",
        "(RELOPR,EQ)",
        "(LITERAL,3)",
        "THEN",

        // Line: debugout p
        "DEBUGOUT",
        "(IDENT,p)",

        // Line: else
        "ELSE",

        // Line: debugout p
        "DEBUGOUT",
        "(IDENT,p)",

        // Line: endif
        "ENDIF",

        // Line: switch p
        "SWITCH",
        "(IDENT,p)",

        // Line: case 2 then
        "CASE",
        "(LITERAL,2)",
        "THEN",

        // Line: debugout p
        "DEBUGOUT",
        "(IDENT,p)",

        // Line: case 3 then
        "CASE",
        "(LITERAL,3)",
        "THEN",

        // Line: debugout p
        "DEBUGOUT",
        "(IDENT,p)",

        // Line: default
        "DEFAULT",

        // Line: debugout p
        "DEBUGOUT",
        "(IDENT,p)",

        // Line: endswitch
        "ENDSWITCH",

        // Line: while r >= n do
        "WHILE",
        "(IDENT,r)",
        "(RELOPR,GE)",
        "(IDENT,n)",
        "DO",

        // Line: q := q + 1 ;
        "(IDENT,q)",
        "BECOMES",
        "(IDENT,q)",
        "(ADDOPR,PLUS)",
        "(LITERAL,1)",
        "SEMICOLON",

        // Line: r := r - n
        "(IDENT,r)",
        "BECOMES",
        "(IDENT,r)",
        "(ADDOPR,MINUS)",
        "(IDENT,n)",

        // Line: endwhile
        "ENDWHILE",

        // Line: endproc
        "ENDPROC",

        // Line: do
        "DO",

        // Line: call divide(m, n, q init, r init)
        "CALL",
        "(IDENT,divide)",
        "LPAREN",
        "(IDENT,m)",
        "COMMA",
        "(IDENT,n)",
        "COMMA",
        "(IDENT,q)",
        "INIT",
        "COMMA",
        "(IDENT,r)",
        "INIT",
        "RPAREN",

        // Line: endprogram
        "ENDPROGRAM",

        // End
        "SENTINEL"
    );

    @Test
    public void testTokenList() throws Exception {
        File file = FileUtils.createTemporaryFileFromInputStream("Temp", ".tmp", ClassLoader.class.getResourceAsStream("/Program.iml"));

        Scanner scanner = new Scanner();

        Token token = null;
        TokenList tokenlist = scanner.parseFile(file);
        Assert.assertEquals(stringtokens.size(), tokenlist.getSize());

        for (String stringtoken : stringtokens) {
            token = tokenlist.nextToken();
            System.out.println("Expected: " + stringtoken + " | Got: " + token.toString());
            Assert.assertTrue(stringtoken.equals(token.toString()));
        }

        Assert.assertTrue(token != null);
        Assert.assertTrue(token instanceof SentinelToken);
        Assert.assertTrue(token.getTerminal().equals(Terminal.SENTINEL));

        tokenlist.resetCounter();
        token = tokenlist.nextToken();

        Assert.assertTrue(token != null);
        Assert.assertTrue(token.getTerminal().equals(Terminal.PROGRAM));
    }
}
