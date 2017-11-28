package ch.fhnw.cpib.compiler.parser;

import ch.fhnw.cpib.compiler.parser.exception.ParserException;
import ch.fhnw.cpib.compiler.scanner.tokens.TokenList;

public class Parser {

    private final Table table;

    public Parser() {
        this.table = new Table();
    }

    public void parseTokenList(TokenList tokenlist) throws ParserException {
    }
}
