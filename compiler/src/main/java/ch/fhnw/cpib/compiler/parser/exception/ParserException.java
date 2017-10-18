package ch.fhnw.cpib.compiler.parser.exception;

public class ParserException extends Exception {

    public ParserException(String message, Exception exception) {
        super(message, exception);
    }

    public ParserException(String message) {
        super(message);
    }
}
