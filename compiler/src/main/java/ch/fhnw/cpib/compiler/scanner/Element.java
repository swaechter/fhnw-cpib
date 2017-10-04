package ch.fhnw.cpib.compiler.scanner;

public class Element {

    private final char character;

    public Element(char character) {
        this.character = character;
    }

    public boolean isSlash() {
        return character == '/';
    }

    public boolean isNewline() {
        return character == '\n';
    }

    public boolean isSpaceOrTab() {
        return character == ' ' || character == '\t';
    }

    public boolean isDigit() {
        return Character.isDigit(character);
    }

    public boolean isLetter() {
        return Character.isLetter(character);
    }

    public boolean isOperator() {
        return toString().matches("[!,;:()=<>%/*+-]");
    }

    @Override
    public String toString() {
        return String.valueOf(character);
    }
}
