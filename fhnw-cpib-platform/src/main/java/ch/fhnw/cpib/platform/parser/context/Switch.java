package ch.fhnw.cpib.platform.parser.context;

public class Switch {

    private Scope scope;
    private int literal;

    public Switch(Scope scope, int literal) {
        scope = new Scope();
        this.literal = literal;
    }

    public Scope getScope() {
        return scope;
    }

    public int getLiteral() {
        return literal;
    }

}
