package ch.fhnw.cpib.compiler.scanner;

import ch.fhnw.cpib.compiler.scanner.dictionary.Dictionary;
import ch.fhnw.cpib.compiler.scanner.exception.ScannerException;
import ch.fhnw.cpib.compiler.scanner.tokens.Token;
import ch.fhnw.cpib.compiler.scanner.tokens.TokenList;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class Scanner {

    private enum State {
        INITIAL,
        COMMENT,
        IDENTITY,
        LITERAL,
        OPERATOR
    }

    private final Dictionary dictionary;

    public Scanner() {
        this.dictionary = new Dictionary();
    }

    public TokenList parseFile(File file) throws ScannerException {
        String content = readFile(file);
        content = sanitizeFile(content);
        return scanFile(content);
    }

    private String readFile(File file) throws ScannerException {
        // Check if the file exists
        if (!file.exists()) {
            throw new ScannerException("The scanner was unable to find the input file " + file.getAbsolutePath());
        }

        // Read the file
        try {
            Path path = Paths.get(file.getAbsolutePath());
            return new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        } catch (IOException exception) {
            throw new ScannerException("The scanner was unable to read the input file: " + exception.getMessage(), exception);
        }

    }

    private String sanitizeFile(String content) {
        return content.replace("\r\n", "\n");
    }

    private TokenList scanFile(String content) throws ScannerException {
        State state = State.INITIAL;
        Optional<Token> token;
        TokenList tokenlist = new TokenList();
        StringBuilder holder = new StringBuilder();
        for (int i = 0; i < content.length(); i++) {
            Element element = new Element(content.charAt(i));
            switch (state) {
                case INITIAL: {
                    if (element.isSlash()) {
                        holder.append(element);
                        state = State.COMMENT;
                    } else if (element.isDigit()) {
                        holder.append(element);
                        state = State.LITERAL;
                    } else if (element.isLetter()) {
                        holder.append(element);
                        state = State.IDENTITY;
                    } else if (element.isOperator()) {
                        holder.append(element);
                        state = State.OPERATOR;
                    } else if (element.isSpaceOrTab()) {
                        state = State.INITIAL;
                    } else if (element.isNewline()) {
                        state = State.INITIAL;
                    } else {
                        throw new ScannerException("Unknown character: " + element);
                    }
                    break;
                }
                case COMMENT: {
                    if (holder.length() == 1) {
                        if (element.isSlash()) {
                            holder.append(element);
                        } else {
                            state = State.OPERATOR;
                            i--;
                        }
                    } else {
                        if (!element.isNewline()) {
                            holder.append(element);
                        } else {
                            holder.setLength(0);
                            state = State.INITIAL;
                        }
                    }
                    break;
                }
                case LITERAL: {
                    if (element.isDigit()) {
                        holder.append(element);
                    } else {
                        tokenlist.addToken(dictionary.lookupLiteral(holder.toString()));
                        holder.setLength(0);
                        state = State.INITIAL;
                        i--;
                    }
                    break;
                }
                case IDENTITY: {
                    if (element.isDigit() || element.isLetter()) {
                        holder.append(element);
                    } else {
                        token = dictionary.lookupToken(holder.toString());
                        if (!token.isPresent()) {
                            tokenlist.addToken(dictionary.lookupIdentifier(holder.toString()));
                        } else {
                            tokenlist.addToken(token.get());
                        }
                        holder.setLength(0);
                        state = State.INITIAL;
                        i--;
                    }
                    break;
                }
                case OPERATOR: {
                    if ((token = dictionary.lookupToken(holder.toString() + element)).isPresent()) {
                        tokenlist.addToken(token.get());
                        holder.setLength(0);
                        state = State.INITIAL;
                    } else if ((token = dictionary.lookupToken(holder.toString())).isPresent()) {
                        tokenlist.addToken(token.get());
                        holder.setLength(0);
                        state = State.INITIAL;
                        i--;
                    }
                    break;
                }
            }
        }

        // Add the end token
        tokenlist.addToken(dictionary.lookupSentinel());

        return tokenlist;
    }
}
