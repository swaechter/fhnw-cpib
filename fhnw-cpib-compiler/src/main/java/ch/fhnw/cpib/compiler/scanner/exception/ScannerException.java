package ch.fhnw.cpib.compiler.scanner.exception;

public class ScannerException extends Exception {

    public ScannerException(String message, Exception exception) {
        super(message, exception);
    }

    public ScannerException(String message) {
        super(message);
    }
}
