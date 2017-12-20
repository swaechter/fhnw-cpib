package ch.fhnw.cpib.platform.parser.concrete;

public abstract class Item {

    private final int idendation;

    Item(int idendation) {
        this.idendation = idendation;
    }

    protected String getHead(String content) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < idendation; i++) {
            builder.append('\t');
        }
        builder.append(content);
        builder.append('\n');
        return builder.toString();
    }

    public abstract String toString();
}
