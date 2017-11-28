package ch.fhnw.cpib.application;

import ch.fhnw.cpib.compiler.Compiler;

import java.io.File;

public class Application {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: compiler <filename>");
            System.exit(1);
        }

        Compiler compiler = new Compiler();
        compiler.compileFile(new File(args[0]));
    }
}
