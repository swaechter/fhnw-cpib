package ch.fhnw.cpib.compiler.scanner;

public enum State {
    INITIAL,
    COMMENT,
    IDENTITY,
    LITERAL,
    OPERATOR
}
