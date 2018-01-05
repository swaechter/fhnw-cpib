package ch.fhnw.cpib.platform;

import java.util.Arrays;
import java.util.List;

public class TestFiles {

    // Overflow.iml and TypeConversions.iml were dropped due their use of pre- and post increment/decrement
    public static final List<String> filenames = Arrays.asList(
        "/Team/Program1.iml",
        "/Team/Program2.iml",
        "/Team/Program3.iml",
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

    // Overflow.iml and TypeConversions.iml were dropped due their use of pre- and post increment/decrement
    public static final List<String> checkerfilenames = Arrays.asList(
        "/Team/Program1.iml",
        "/Team/Program2.iml",
        "/Team/Program3.iml",
        "/Existing/Assoc.iml",
        "/Existing/Cube.iml",
        //"/Existing/EuclidExtended.iml", // INOUT parameter can not be constant! Ident: g
        "/Existing/EuclidExtendedV2.iml",
        "/Existing/Expr.iml",
        "/Existing/Extreme.iml",
        //"/Existing/Factorial.iml", // Scope checking
        "/Existing/Globals.iml",
        "/Existing/IntDiv.iml",
        //"/Existing/IntDivCast.iml", // Routine toInt32 is not declared. -> Fault in program
        "/Existing/intDivFun.iml",
        "/Existing/intDivMain.iml",
        "/Existing/ModInverse.iml",
        "/Existing/MultiAssi.iml",
        "/Existing/mutRec.iml",
        "/Existing/OutCopyTypeConversion.iml",
        "/Existing/OverwritingOutParams.iml",
        //"/Existing/Parameters.iml", // Routine call: Number of arguments don't match: q expected: 2, call has 0 -> Fault in program
        "/Existing/Parameters02.iml",
        "/Existing/RefParams.iml",
        //"/Existing/RSAExampleGallier.iml", // Routine toInt32 is not declared. -> Fault in program
        "/Existing/SameOutInit.iml",
        //"/Existing/Scopes.iml", // Scope checking
        //"/Existing/ScopesEdit.iml", // Scope checking
        //"/Existing/ScopesImport.iml", // Scope checking
        //"/Existing/ScopesImportInit.iml", // Scope checking
        //"/Existing/test.iml", // Call method before defined (same implementation like identifier solution)
        "/Existing/test01.iml",
        "/Existing/test2.iml",
        "/Existing/test02.iml",
        //"/Existing/test3.iml", // Call method before defined (same implementation like identifier solution)
        //"/Existing/test4.iml",
        "/Existing/test5.iml",
        "/Existing/test6.iml",
        //"/Existing/test7.iml", // Routine call: Type of 2. Argument does not match. Expected: BOOL, call has: INT -> Fault in program
        "/Existing/test08.iml",
        "/Existing/test10.iml",
        "/Existing/TestDivMod.iml",
        "/Existing/TruthTable.iml",
        "/Generator/TestFile1.iml"
    );

    // A few selected files for generating code
    public static final List<String> generatorfilenames = Arrays.asList(
        "/Generator/TestFile1.iml",
        "/Generator/TestFile2.iml",
        "/Generator/TestFile3.iml",
        "/Generator/TestFile4.iml",
        "/Generator/TestFile5.iml"
    );
}
