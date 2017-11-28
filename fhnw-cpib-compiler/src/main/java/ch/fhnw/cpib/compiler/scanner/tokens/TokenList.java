package ch.fhnw.cpib.compiler.scanner.tokens;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TokenList {

    private final List<Token> tokens;

    private int counter;

    public TokenList() {
        this.tokens = new ArrayList<>();
        this.counter = 0;
    }

    public void addToken(Token token) {
        tokens.add(token);
    }

    public Token nextToken() {
        if (tokens.size() > counter) {
            Token token = tokens.get(counter);
            counter++;
            return token;
        } else {
            return null;
        }
    }

    public int getSize() {
        return tokens.size();
    }

    public void resetCounter() {
        counter = 0;
    }

    @Override
    public String toString() {
        return (!tokens.isEmpty()) ? tokens.stream().map(Object::toString).collect(Collectors.joining(", ")) : "[]";
    }
}
