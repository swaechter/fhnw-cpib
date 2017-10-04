package ch.fhnw.cpib.compiler.scanner.terminal;

public enum Terminal {

    // Identity
    IDENT("IDENT"),

    // Literal
    LITERAL("LITERAL"),

    // Operator
    MULTOPR("MULTOPR"),
    ADDOPR("ADDOPR"),
    RELOPR("RELOPR"),
    BOOLOPR("BOOLOPR"),

    // Type
    TYPE("TYPE"),

    // Symbols
    COLON(":"),
    COMMA(","),
    SEMICOLON(";"),
    BECOMES(":="),
    LPAREN("("),
    RPAREN(")"),

    // Modes
    FLOWMODE("FLOWMODE"),
    CHANGEMODE("CHANGEMODE"),
    MECHMODE("MECHMODE"),

    // Keywords
    PROGRAM("PROGRAM"),
    CALL("CALL"),
    IF("IF"),
    ELSE("ELSE"),
    ENDIF("ENDIF"),
    DEBUGIN("DEBUGIN"),
    DEBUGOUT("DEBUGOUT"),
    NOT("NOT"),
    FUN("FUN"),
    GLOBAL("GLOBAL"),
    LOCAL("LOCAL"),
    INIT("INIT"),
    PROC("PROC"),
    RETURNS("RETURNS"),
    SKIP("SKIP"),
    THEN("THEN"),
    WHILE("WHILE"),
    DO("DO"),
    ENDWHILE("ENDWHILE"),
    ENDPROGRAM("ENDPROGRAM"),
    ENDFUN("ENDFUN"),
    ENDPROC("ENDPROC"),
    SENTINEL("SENTINEL");

    private final String name;

    Terminal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
