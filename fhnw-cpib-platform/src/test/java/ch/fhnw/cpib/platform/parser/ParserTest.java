package ch.fhnw.cpib.platform.parser;

import ch.fhnw.cpib.platform.scanner.Scanner;
import ch.fhnw.cpib.platform.scanner.tokens.TokenList;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class ParserTest {

    // Overflow.iml and TypeConversions.iml were dropped due their use of pre- and post increment/decrement
    private static final List<String> filenames = Arrays.asList(
        //"/Team/Program1.iml",
        "/Team/Program2.iml",
        //"/Existing/Assoc.iml",
        //"/Existing/Cube.iml",
        //"/Existing/EuclidExtended.iml",
        //"/Existing/EuclidExtendedV2.iml",
        //"/Existing/Expr.iml",
        //"/Existing/Extreme.iml",
        //"/Existing/Factorial.iml",
        "/Existing/Globals.iml",
        "/Existing/IntDiv.iml",
        //"/Existing/IntDivCast.iml",
        //"/Existing/intDivFun.iml",
        "/Existing/intDivMain.iml",
        //"/Existing/ModInverse.iml",
        "/Existing/MultiAssi.iml",
        "/Existing/mutRec.iml",
        //"/Existing/OutCopyTypeConversion.iml",
        "/Existing/OverwritingOutParams.iml",
        //"/Existing/Parameters.iml",
        //"/Existing/Parameters02.iml",
        "/Existing/RefParams.iml",
        //"/Existing/RSAExampleGallier.iml",
        "/Existing/SameOutInit.iml",
        //"/Existing/Scopes.iml",
        //"/Existing/ScopesEdit.iml",
        "/Existing/ScopesImport.iml",
        "/Existing/ScopesImportInit.iml",
        //"/Existing/test.iml",
        "/Existing/test01.iml",
        //"/Existing/test2.iml",
        "/Existing/test02.iml",
        //"/Existing/test3.iml",
        //"/Existing/test4.iml",
        "/Existing/test5.iml",
        "/Existing/test6.iml",
        //"/Existing/test7.iml",
        "/Existing/test08.iml",
        "/Existing/test10.iml"
        //"/Existing/TestDivMod.iml",
        //"/Existing/TruthTable.iml"
    );

    @Test
    public void testParser() throws Exception {
        // Create the scanner and parser
        Scanner scanner = new Scanner();
        Parser parser = new Parser();

        // Parse the files
        for (String filename : filenames) {
            // Load the program
            InputStream inputstream = getClass().getResourceAsStream(filename);
            String content = IOUtils.toString(inputstream, StandardCharsets.UTF_8);
            Assert.assertFalse(content.isEmpty());

            // Scan the program
            TokenList tokenlist = scanner.scanString(content);
            Assert.assertTrue(tokenlist.getSize() > 0);

            // Parse the token list
            parser.parseTokenList(tokenlist);
        }
    }
}
