package ch.fhnw.cpib.platform.parser.concrete;

public abstract class Item {
    private int ident;

    public void setIdent(int ident) {
        this.ident = ident;
    }

    public int getIdent() {
        return ident;
    }

    public abstract String toString();
}
