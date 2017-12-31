package ch.fhnw.cpib.platform.generator;

public class GeneratorException extends Exception {

    public GeneratorException(String message, Exception exception) {
        super(message, exception);
    }

    public GeneratorException(String message) {
        super(message);
    }
}
