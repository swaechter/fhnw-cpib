package ch.fhnw.cpib.platform.parser.exception;

public class ContextException extends Exception {

    public ContextException(String message) {
        System.out.println("[ContextError] " + message);
        System.exit(1);
    }

}
