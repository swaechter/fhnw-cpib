package ch.fhnw.cpib.platform.checker;

import ch.fhnw.cpib.platform.scanner.tokens.Tokens;

public class Store extends Symbol {

    private boolean writeable;

    private boolean initialized;

    private boolean isConst;

    private boolean relative = false;

    private boolean reference = true;

    public Store(
        final String identifier,
        final Tokens.TypeToken type,
        final boolean isConst) {
        super(identifier, type);
        this.writeable = true;
        this.initialized = false;
        this.isConst = isConst;
    }

    public boolean isConst() {
        return isConst;
    }

    public boolean isWriteable() {
        return writeable;
    }

    public boolean isInitialized() {
        return initialized;
    }

    public void initialize() {
        initialized = true;
        if (isConst) {
            writeable = false;
        }
    }

    public void setRelative(final boolean relative) {
        this.relative = relative;
    }

    public void setReference(final boolean reference) {
        this.reference = reference;
    }

}
