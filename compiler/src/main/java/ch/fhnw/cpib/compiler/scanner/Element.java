package ch.fhnw.cpib.compiler.scanner;

public class Element {

    private final char character;

    public Element(char character) {
        this.character = character;
    }

    public boolean isDigit() {
        return Character.isDigit(character);
    }

    public boolean isLetter() {
        return Character.isLetter(character);
    }

    public boolean isSequence() {
        return toString().matches("[ \\r\\n\\t]");
    }

    public boolean isOperator() {
        return toString().matches("[!,;:():=<>%/*+-]");
    }

    @Override
    public String toString() {
        return String.valueOf(character);
    }
}
