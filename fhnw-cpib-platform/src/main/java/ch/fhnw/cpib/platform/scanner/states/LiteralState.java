package ch.fhnw.cpib.platform.scanner.states;

import ch.fhnw.cpib.platform.scanner.exception.ScannerException;

public class LiteralState extends State {

    public LiteralState(State state, Character character) {
        super(state, character);
    }

    @Override
    public State handleNewline() throws ScannerException {
        getTokenList().addToken(getDictionary().lookupLiteral(getToken()));
        return new InitialState(this);
    }

    @Override
    public State handleLetter(Character character) throws ScannerException {
        return super.handleLetter(character);
    }

    @Override
    public State handleDigit(Character character) throws ScannerException {
        addTokenElement(character);
        return this;
    }

    @Override
    public State handleSpace(Character character) throws ScannerException {
        getTokenList().addToken(getDictionary().lookupLiteral(getToken()));
        return new InitialState(this);
    }

    @Override
    public State handleUnderScore(Character character) throws ScannerException {
        return super.handleUnderScore(character);
    }

    @Override
    public State handleDash(Character character) throws ScannerException {
        return this;
    }

    @Override
    public State handleSlash(Character character) throws ScannerException {
        getTokenList().addToken(getDictionary().lookupLiteral(getToken()));
        return new CommentState(this, character);
    }

    @Override
    public State handleOperator(Character character) throws ScannerException {
        getTokenList().addToken(getDictionary().lookupLiteral(getToken()));
        return new OperatorState(this, character);
    }

    @Override
    public State handleUnknown(Character character) throws ScannerException {
        return super.handleUnknown(character);
    }
}
