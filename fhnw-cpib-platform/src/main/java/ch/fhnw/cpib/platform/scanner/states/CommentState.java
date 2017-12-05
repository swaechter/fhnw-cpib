package ch.fhnw.cpib.platform.scanner.states;

import ch.fhnw.cpib.platform.scanner.exception.ScannerException;
import ch.fhnw.cpib.platform.scanner.tokens.Token;

import java.util.Optional;

public class CommentState extends State {

    public CommentState(State state, Character character) {
        super(state, character);
    }

    @Override
    public State handleNewline() throws ScannerException {
        return new ch.fhnw.cpib.platform.scanner.states.InitialState(this);
    }

    @Override
    public State handleLetter(Character character) throws ScannerException {
        if (getToken().length() == 2) {
            return this;
        } else {
            Optional<Token> token = getDictionary().lookupToken(getToken());
            if (token.isPresent()) {
                getTokenList().addToken(token.get());
                return new ch.fhnw.cpib.platform.scanner.states.IdentityState(this, character);
            } else {
                return super.handleDigit(character);
            }
        }
    }

    @Override
    public State handleDigit(Character character) throws ScannerException {
        if (getToken().length() == 2) {
            return this;
        } else {
            Optional<Token> token = getDictionary().lookupToken(getToken());
            if (token.isPresent()) {
                getTokenList().addToken(token.get());
                return new ch.fhnw.cpib.platform.scanner.states.LiteralState(this, character);
            } else {
                return super.handleDigit(character);
            }
        }
    }

    @Override
    public State handleSpace(Character character) throws ScannerException {
        if (getToken().length() == 2) {
            return this;
        } else {
            Optional<Token> token = getDictionary().lookupToken(getToken());
            if (token.isPresent()) {
                getTokenList().addToken(token.get());
                return new ch.fhnw.cpib.platform.scanner.states.InitialState(this);
            } else {
                return super.handleSpace(character);
            }
        }
    }

    @Override
    public State handleUnderScore(Character character) throws ScannerException {
        return getToken().length() == 2 ? this : super.handleUnderScore(character);
    }

    @Override
    public State handleDash(Character character) throws ScannerException {
        return getToken().length() == 2 ? this : super.handleDash(character);
    }

    @Override
    public State handleSlash(Character character) throws ScannerException {
        if (getToken().length() == 1) {
            addTokenElement(character);
        }

        return this;
    }

    @Override
    public State handleOperator(Character character) throws ScannerException {
        if (getToken().length() == 2) {
            return this;
        } else {
            Optional<Token> token = getDictionary().lookupToken(getToken());
            if (token.isPresent()) {
                getTokenList().addToken(token.get());
                return new ch.fhnw.cpib.platform.scanner.states.OperatorState(this, character);
            } else {
                return super.handleDigit(character);
            }
        }
    }

    @Override
    public State handleUnknown(Character character) throws ScannerException {
        return getToken().length() == 2 ? this : super.handleUnknown(character);
    }
}
