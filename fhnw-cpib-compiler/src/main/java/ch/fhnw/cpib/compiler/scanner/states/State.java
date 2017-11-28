package ch.fhnw.cpib.compiler.scanner.states;

import ch.fhnw.cpib.compiler.scanner.dictionary.Dictionary;
import ch.fhnw.cpib.compiler.scanner.exception.ScannerException;
import ch.fhnw.cpib.compiler.scanner.tokens.TokenList;

public abstract class State {

    private final TokenList tokenlist;

    private final Dictionary dictionary;

    private final StringBuilder stringbuilder;

    protected State(TokenList tokenList, Dictionary dictionary) {
        this.tokenlist = tokenList;
        this.dictionary = dictionary;
        this.stringbuilder = new StringBuilder();
    }

    protected State(State state) {
        this.tokenlist = state.tokenlist;
        this.dictionary = state.dictionary;
        this.stringbuilder = new StringBuilder();
    }

    protected State(State state, Character character) {
        this(state);
        addTokenElement(character);
    }

    public TokenList getTokenList() {
        return tokenlist;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    protected String getToken() {
        return stringbuilder.toString();
    }

    protected void addTokenElement(Character character) {
        stringbuilder.append(character);
    }

    public State handleNewline() throws ScannerException {
        throw new ScannerException("Unable to handle a newline");
    }

    public State handleLetter(Character character) throws ScannerException {
        throw new ScannerException("Unable to handle a " + character);
    }

    public State handleDigit(Character character) throws ScannerException {
        throw new ScannerException("Unable to handle a " + character);
    }

    public State handleSpace(Character character) throws ScannerException {
        throw new ScannerException("Unable to handle a space");
    }

    public State handleUnderScore(Character character) throws ScannerException {
        throw new ScannerException("Unable to handle an " + character);
    }

    public State handleDash(Character character) throws ScannerException {
        throw new ScannerException("Unable to handle a " + character);
    }

    public State handleSlash(Character character) throws ScannerException {
        throw new ScannerException("Unable to handle a " + character);
    }

    public State handleOperator(Character character) throws ScannerException {
        throw new ScannerException("Unable to handle a " + character);
    }

    public State handleUnknown(Character character) throws ScannerException {
        throw new ScannerException("Unable to handle the unknown character " + character);
    }
}
