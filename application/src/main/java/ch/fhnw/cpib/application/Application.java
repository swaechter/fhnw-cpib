package ch.fhnw.cpib.application;

import ch.fhnw.cpib.compiler.Compiler;

public class Application {

    public static void main(String[] args) {
        Compiler compiler = new Compiler();
        System.out.println("Text from the compiler: " + compiler.getText());
    }
}
