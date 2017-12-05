package ch.fhnw.cpib.platform.scanner.states;

import ch.fhnw.cpib.platform.scanner.exception.ScannerException;
import ch.fhnw.cpib.platform.scanner.tokens.Token;

import java.util.Optional;

public class OperatorState extends State {

    public OperatorState(State state, Character character) {
        super(state, character);
    }

    @Override
    public State handleNewline() throws ScannerException {
        Optional<Token> token = getDictionary().lookupToken(getToken());
        if (token.isPresent()) {
            getTokenList().addToken(token.get());
            return new InitialState(this);
        } else {
            return super.handleNewline();
        }
    }

    @Override
    public State handleLetter(Character character) throws ScannerException {
        Optional<Token> token = getDictionary().lookupToken(getToken());
        if (token.isPresent()) {
            getTokenList().addToken(token.get());
            return new IdentityState(this, character);
        } else {
            return super.handleLetter(character);
        }
    }

    @Override
    public State handleDigit(Character character) throws ScannerException {
        Optional<Token> token = getDictionary().lookupToken(getToken());
        if (token.isPresent()) {
            getTokenList().addToken(token.get());
            return new LiteralState(this, character);
        } else {
            return super.handleDigit(character);
        }
    }

    @Override
    public State handleSpace(Character character) throws ScannerException {
        Optional<Token> token = getDictionary().lookupToken(getToken());
        if (token.isPresent()) {
            getTokenList().addToken(token.get());
            return new InitialState(this);
        } else {
            return super.handleSpace(character);
        }
    }

    @Override
    public State handleUnderScore(Character character) throws ScannerException {
        Optional<Token> token = getDictionary().lookupToken(getToken());
        if (token.isPresent()) {
            getTokenList().addToken(token.get());
            return new IdentityState(this, character);
        } else {
            return super.handleUnderScore(character);
        }
    }

    @Override
    public State handleDash(Character character) throws ScannerException {
        return super.handleDash(character);
    }

    @Override
    public State handleSlash(Character character) throws ScannerException {
        Optional<Token> token = getDictionary().lookupToken(getToken());
        if (token.isPresent()) {
            getTokenList().addToken(token.get());
            return new CommentState(this, character);
        } else {
            return super.handleSlash(character);
        }
    }

    @Override
    public State handleOperator(Character character) throws ScannerException {
        Optional<Token> token = getDictionary().lookupToken(getToken() + character);
        if (token.isPresent()) {
            getTokenList().addToken(token.get());
            return new InitialState(this);
        } else {
            Optional<Token> operator1 = getDictionary().lookupToken(getToken());
            Optional<Token> operator2 = getDictionary().lookupToken(String.valueOf(character));
            if (operator1.isPresent() && operator2.isPresent()) {
                getTokenList().addToken(operator1.get());
                getTokenList().addToken(operator2.get());
                return new InitialState(this);
            } else {
                return super.handleOperator(character);
            }
        }
    }

    @Override
    public State handleUnknown(Character character) throws ScannerException {
        return super.handleUnknown(character);
    }
}
