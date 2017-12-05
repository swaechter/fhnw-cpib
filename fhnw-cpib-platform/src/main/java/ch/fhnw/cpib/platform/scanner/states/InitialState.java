package ch.fhnw.cpib.platform.scanner.states;

import ch.fhnw.cpib.platform.scanner.dictionary.Dictionary;
import ch.fhnw.cpib.platform.scanner.exception.ScannerException;
import ch.fhnw.cpib.platform.scanner.tokens.TokenList;

public class InitialState extends State {

    public InitialState(TokenList tokenlist, Dictionary dictionary) {
        super(tokenlist, dictionary);
    }

    public InitialState(State state) {
        super(state);
    }

    @Override
    public State handleNewline() throws ScannerException {
        return this;
    }

    @Override
    public State handleLetter(Character character) throws ScannerException {
        return new IdentityState(this, character);
    }

    @Override
    public State handleDigit(Character character) throws ScannerException {
        return new LiteralState(this, character);
    }

    @Override
    public State handleSpace(Character character) throws ScannerException {
        return this;
    }

    @Override
    public State handleUnderScore(Character character) throws ScannerException {
        return new IdentityState(this, character);
    }

    @Override
    public State handleDash(Character character) throws ScannerException {
        return super.handleDash(character);
    }

    @Override
    public State handleSlash(Character character) throws ScannerException {
        return new CommentState(this, character);
    }

    @Override
    public State handleOperator(Character character) throws ScannerException {
        return new OperatorState(this, character);
    }

    @Override
    public State handleUnknown(Character character) throws ScannerException {
        return super.handleUnknown(character);
    }
}
