package ch.fhnw.cpib.platform.scanner.states;

import ch.fhnw.cpib.platform.scanner.exception.ScannerException;
import ch.fhnw.cpib.platform.scanner.tokens.Tokens;

import java.util.Optional;

public class IdentityState extends State {

    public IdentityState(State state, Character character) {
        super(state, character);
    }

    @Override
    public State handleNewline() throws ScannerException {
        Optional<Tokens.Token> token = getDictionary().lookupToken(this, getToken());
        getTokenList().addToken(token.orElseGet(() -> getDictionary().lookupIdentifier(this, getToken())));
        return new InitialState(this);
    }

    @Override
    public State handleLetter(Character character) throws ScannerException {
        addTokenElement(character);
        return this;
    }

    @Override
    public State handleDigit(Character character) throws ScannerException {
        addTokenElement(character);
        return this;
    }

    @Override
    public State handleSpace(Character character) throws ScannerException {
        Optional<Tokens.Token> token = getDictionary().lookupToken(this, getToken());
        getTokenList().addToken(token.orElseGet(() -> getDictionary().lookupIdentifier(this, getToken())));
        return new InitialState(this);
    }

    @Override
    public State handleUnderScore(Character character) throws ScannerException {
        addTokenElement(character);
        return this;
    }

    @Override
    public State handleDash(Character character) throws ScannerException {
        addTokenElement(character);
        return this;
    }

    @Override
    public State handleSlash(Character character) throws ScannerException {
        Optional<Tokens.Token> token = getDictionary().lookupToken(this, getToken());
        getTokenList().addToken(token.orElseGet(() -> getDictionary().lookupIdentifier(this, getToken())));
        return new CommentState(this, character);
    }

    @Override
    public State handleOperator(Character character) throws ScannerException {
        Optional<Tokens.Token> token = getDictionary().lookupToken(this, getToken());
        getTokenList().addToken(token.orElseGet(() -> getDictionary().lookupIdentifier(this, getToken())));
        return new OperatorState(this, character);
    }

    @Override
    public State handleUnknown(Character character) throws ScannerException {
        return super.handleUnknown(character);
    }
}
