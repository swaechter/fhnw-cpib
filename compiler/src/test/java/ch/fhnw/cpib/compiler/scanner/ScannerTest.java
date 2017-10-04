package ch.fhnw.cpib.compiler.scanner;

import ch.fhnw.cpib.compiler.scanner.tokens.Token;
import ch.fhnw.cpib.compiler.scanner.tokens.TokenList;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class ScannerTest {

    private final List<String> stringtokens = Arrays.asList(

        // Line 1: program intDiv(in  m:int32, in  n:int32,
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

        // Line 2: out q:int32, out r:int32)
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

        // Line 3: global
        "GLOBAL",

        // Line 4: proc divide(in copy const m:int32, in copy const n:int32,
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

        // Line 5: out ref var   q:int32, out ref var   r:int32)
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

        // Line 6: do
        "DO",

        // Line 7: q init := 0;
        "(IDENT,q)",
        "INIT",
        "BECOMES",
        "(LITERAL,0)",
        "SEMICOLON",

        // Line 8: r init := m;
        "(IDENT,r)",
        "INIT",
        "BECOMES",
        "(IDENT,m)",
        "SEMICOLON",

        // Line 9: while r >= n do
        "WHILE",
        "(IDENT,r)",
        "(RELOPR,GE)",
        "(IDENT,n)",
        "DO",

        // Line 10: q := q + 1 ;
        "(IDENT,q)",
        "BECOMES",
        "(IDENT,q)",
        "(ADDOPR,PLUS)",
        "(LITERAL,1)",
        "SEMICOLON",

        // Line 11: r := r - n
        "(IDENT,r)",
        "BECOMES",
        "(IDENT,r)",
        "(ADDOPR,MINUS)",
        "(IDENT,n)",

        // Line 12: endwhile
        "ENDWHILE",

        // Line 13: endproc
        "ENDPROC",

        // Line 14: do
        "DO",

        // Line 15: call divide(m, n, q init, r init)
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

        // Line 16: endprogram
        "ENDPROGRAM"
    );

    @Test
    public void testTokenList() throws Exception {
        File file = createTemporaryFileFromInputStream("Temp", ".tmp", ClassLoader.class.getResourceAsStream("/Program.iml"));

        Scanner scanner = new Scanner();

        TokenList tokenlist = scanner.parseFile(file);
        for (String stringtoken : stringtokens) {
            Token token = tokenlist.nextToken();
            System.out.println("Expected: " + stringtoken + " | Got: " + token.toString());
            Assert.assertTrue(stringtoken.equals(token.toString()));
        }
    }

    private File createTemporaryFileFromInputStream(String suffix, String prefix, InputStream inputstream) throws IOException {
        File file = File.createTempFile(suffix, prefix);
        OutputStream outputstream = new FileOutputStream(file);
        int result = inputstream.read();
        while (result != -1) {
            outputstream.write((byte) result);
            result = inputstream.read();
        }
        return file;
    }
}
