package ch.fhnw.cpib.platform.scanner;

import ch.fhnw.cpib.platform.scanner.dictionary.Dictionary;
import ch.fhnw.cpib.platform.scanner.exception.ScannerException;
import ch.fhnw.cpib.platform.scanner.states.InitialState;
import ch.fhnw.cpib.platform.scanner.states.State;
import ch.fhnw.cpib.platform.scanner.tokens.TokenList;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Scanner {

    private final Dictionary dictionary;

    public Scanner() {
        this.dictionary = new Dictionary();
    }

    public TokenList scanFile(File file) throws ScannerException {
        // Check if the file exists
        if (!file.exists()) {
            throw new ScannerException("The scanner was unable to find the input file " + file.getAbsolutePath());
        }

        // Read the file
        try {
            Path path = Paths.get(file.getAbsolutePath());
            String content = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
            return createTokenList(sanitizeContent(content));
        } catch (IOException exception) {
            throw new ScannerException("The scanner was unable to read the input file: " + exception.getMessage(), exception);
        }
    }

    public TokenList scanString(String content) throws ScannerException {
        return createTokenList(sanitizeContent(content));

    }

    private String sanitizeContent(String content) throws ScannerException {
        content = content.replace("\r\n", "\n");
        content = content.replace("\t", " ");
        return content;
    }

    private TokenList createTokenList(String content) throws ScannerException {
        TokenList tokenlist = new TokenList();
        State state = new InitialState(tokenlist, dictionary);
        for (int i = 0; i < content.length(); i++) {
            char element = content.charAt(i);
            //System.out.println("[" + i + "]: " + element);
            if (element == '\n') {
                state = state.handleNewline();
            } else if (Character.isLetter(element)) {
                state = state.handleLetter(element);
            } else if (Character.isDigit(element)) {
                state = state.handleDigit(element);
            } else if (Character.isSpaceChar(element)) {
                state = state.handleSpace(element);
            } else if (element == '\'') {
                state = state.handleDash(element);
            } else if (element == '/') {
                state = state.handleSlash(element);
            } else if (element == ' ') {
                state = state.handleSpace(element);
            } else if (element == '_') {
                state = state.handleUnderScore(element);
            } else if (String.valueOf(element).matches("[(),;:=*+-/<>&?!|]")) {
                state = state.handleOperator(element);
            } else {
                state = state.handleUnknown(element);
            }
        }

        // Add final sentinel token
        tokenlist.addToken(dictionary.lookupSentinel());

        return tokenlist;
    }
}
