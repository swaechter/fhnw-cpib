package ch.fhnw.cpib.platform.parser;

import ch.fhnw.cpib.platform.parser.exception.ParserException;
import ch.fhnw.cpib.platform.scanner.tokens.TokenList;

public class Parser {

    private final ch.fhnw.cpib.platform.parser.Table table;

    public Parser() {
        this.table = new ch.fhnw.cpib.platform.parser.Table();
    }

    public void parseTokenList(TokenList tokenlist) throws ParserException {
    }
}
