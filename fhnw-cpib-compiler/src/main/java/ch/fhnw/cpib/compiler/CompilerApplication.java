package ch.fhnw.cpib.compiler;

import ch.fhnw.cpib.platform.Compiler;
import ch.fhnw.cpib.platform.utils.ReaderUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CompilerApplication {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java -jar ./compiler.jar <filename.iml>");
            System.exit(1);
        }

        File file = new File(args[0]);
        if (!file.exists()) {
            System.err.println("The given file does not exist: " + file.getAbsolutePath());
            System.exit(1);
        }

        try {
            Compiler compiler = new Compiler();
            compiler.compileString(ReaderUtils.getContentFromFile(file, StandardCharsets.UTF_8));
        } catch (IOException exception) {
            System.err.println("The given file can't be read: " + exception.getMessage());
            System.exit(1);
        }
    }
}
