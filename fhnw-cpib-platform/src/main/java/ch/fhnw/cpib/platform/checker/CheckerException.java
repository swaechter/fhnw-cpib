package ch.fhnw.cpib.platform.checker;

public class CheckerException extends Exception {

    public CheckerException(String message, Exception exception) {
        super(message, exception);
    }

    public CheckerException(String message) {
        super(message);
    }
}
