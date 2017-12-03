package ch.fhnw.cpib.compiler.scanner;

import ch.fhnw.cpib.compiler.scanner.tokens.TokenList;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScannerTest {

    // Overflow.iml and TypeConversions.iml were dropped due their use of pre- and post increment/decrement
    private static final List<String> filenames = Arrays.asList(
        "/Team/Program1.iml",
        "/Team/Program2.iml",
        "/Existing/Assoc.iml",
        "/Existing/Cube.iml",
        "/Existing/EuclidExtended.iml",
        "/Existing/EuclidExtendedV2.iml",
        "/Existing/Expr.iml",
        "/Existing/Extreme.iml",
        "/Existing/Factorial.iml",
        "/Existing/Globals.iml",
        "/Existing/IntDiv.iml",
        "/Existing/IntDivCast.iml",
        "/Existing/intDivFun.iml",
        "/Existing/intDivMain.iml",
        "/Existing/ModInverse.iml",
        "/Existing/MultiAssi.iml",
        "/Existing/mutRec.iml",
        "/Existing/OutCopyTypeConversion.iml",
        "/Existing/OverwritingOutParams.iml",
        "/Existing/Parameters.iml",
        "/Existing/Parameters02.iml",
        "/Existing/RefParams.iml",
        "/Existing/RSAExampleGallier.iml",
        "/Existing/SameOutInit.iml",
        "/Existing/Scopes.iml",
        "/Existing/ScopesEdit.iml",
        "/Existing/ScopesImport.iml",
        "/Existing/ScopesImportInit.iml",
        "/Existing/test.iml",
        "/Existing/test01.iml",
        "/Existing/test2.iml",
        "/Existing/test02.iml",
        "/Existing/test3.iml",
        "/Existing/test4.iml",
        "/Existing/test5.iml",
        "/Existing/test6.iml",
        "/Existing/test7.iml",
        "/Existing/test08.iml",
        "/Existing/test10.iml",
        "/Existing/TestDivMod.iml",
        "/Existing/TruthTable.iml"
    );

    private final static Map<String, String> tokenmap = new HashMap<>();

    private final static Scanner scanner = new Scanner();

    @BeforeClass
    public static void loadTokenMap() throws Exception {
        // Load the expected token map
        InputStream inputstream = ScannerTest.class.getResourceAsStream("/TokenMap.txt");
        for (String line : IOUtils.readLines(inputstream, StandardCharsets.UTF_8)) {
            String[] parameters = line.split(":", 2);
            Assert.assertEquals(2, parameters.length);
            Assert.assertTrue(parameters[0].length() > 0);
            Assert.assertTrue(parameters[1].length() > 0);
            tokenmap.put(parameters[0], parameters[1]);
        }
    }

    @Test
    public void testTokenListsFromResource() throws Exception {
        // Scan the tokens
        for (String filename : filenames) {
            InputStream fileinputstream = getClass().getResourceAsStream(filename);
            String filecontent = IOUtils.toString(fileinputstream, StandardCharsets.UTF_8);
            Assert.assertFalse(filecontent.isEmpty());

            TokenList tokenlist = scanner.scanString(filecontent);
            Assert.assertTrue(tokenlist.getSize() > 0);

            String realtokenlist = tokenmap.get(filename);
            Assert.assertEquals(realtokenlist, tokenlist.toString());
            //System.out.println(filename + ":" + tokenlist.toString());
        }
    }

    @Test
    public void testTokenListFromFileSystem() throws Exception {
        Assert.assertTrue(filenames.size() > 0);
        String filename = filenames.get(0);

        // Create a temporary file
        InputStream fileinputstream = getClass().getResourceAsStream(filename);
        File file = createTemporaryFileFromInputStream("temp", "tmp", fileinputstream);

        TokenList tokenlist = scanner.scanFile(file);
        Assert.assertTrue(tokenlist.getSize() > 0);

        String realtokenlist = tokenmap.get(filename);
        Assert.assertEquals(realtokenlist, tokenlist.toString());
    }

    private File createTemporaryFileFromInputStream(String suffix, String prefix, InputStream inputstream) throws IOException {
        File file = File.createTempFile(suffix, prefix);
        file.deleteOnExit();

        OutputStream outputstream = new FileOutputStream(file);
        int result = inputstream.read();
        while (result != -1) {
            outputstream.write((byte) result);
            result = inputstream.read();
        }

        outputstream.close();

        return file;
    }
}
