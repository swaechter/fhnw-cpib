package ch.fhnw.cpib.platform.parser.context;

import java.util.HashMap;

public class RoutineTable {
    private HashMap<String, Routine> map = new HashMap<String, Routine>();

    public boolean insert(Routine r) {
        if (!map.containsKey(r.getIdentifier())) {
            map.put(r.getIdentifier(),r);
            return true;
        }
        return false;
    }

    public Routine lookup(String identifier) {
        return map.get(identifier);
    }

    public HashMap<String, Routine> getTable() {
        return map;
    }
}
