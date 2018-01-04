package ch.fhnw.cpib.platform.checker;

import java.util.HashMap;

public class SwitchTable {

    private HashMap<String, Switch> map = new HashMap<>();

    public boolean insert(Switch s) {
        if (!map.containsKey(s.getName())) {
            map.put(s.getName(), s);
            return true;
        }
        return false;
    }

    public Switch lookup(String name) {
        return map.get(name);
    }

    public HashMap<String, Switch> getTable() {
        return map;
    }
}
