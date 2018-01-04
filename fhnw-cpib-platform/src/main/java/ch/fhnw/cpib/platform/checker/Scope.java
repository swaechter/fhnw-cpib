package ch.fhnw.cpib.platform.checker;

import ch.fhnw.cpib.platform.scanner.tokens.Tokens;

public class Scope {

    private final StoreTable storeTable;

    public Scope() {
        this(new StoreTable());
    }

    public Scope(StoreTable storeTable) {
        this.storeTable = storeTable;
    }

    public StoreTable getStoreTable() {
        return storeTable;
    }

    public Tokens.TypeToken.Type getType(final String identifier) {
        return storeTable.getType(identifier);
    }

    public boolean addStore(final StoreTable stores) {
        for (Store symbol : stores.getTable().values()) {
            if (!storeTable.addStore(symbol)) {
                return false;
            }
        }
        return true;
    }
}
