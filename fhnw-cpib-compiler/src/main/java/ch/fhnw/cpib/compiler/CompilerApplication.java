package ch.fhnw.cpib.compiler;

import ch.fhnw.cpib.platform.Compiler;

import java.io.File;

public class CompilerApplication {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java -jar ./compiler.jar <filename.iml>");
            System.exit(1);
        }

        Compiler compiler = new Compiler();
        compiler.compileFile(new File(args[0]));
    }
}
