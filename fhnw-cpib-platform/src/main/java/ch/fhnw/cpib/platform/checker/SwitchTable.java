package ch.fhnw.cpib.platform.checker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SwitchTable {

    private HashMap<String, List<SwitchCase>> map = new HashMap<>();

    public boolean insert(String exprName, SwitchCase s) {
        List<SwitchCase> itemsList = map.get(exprName);
        if(itemsList == null) {
            itemsList = new ArrayList<>();
            itemsList.add(s);
            //first argument key switch expr name
            //second argument switch object with switch expr type and case literal token with value and type
            map.put(exprName, itemsList);
        }
        return true;
    }

    public List<SwitchCase> getEntry(String exprName) {
        return map.get(exprName);
    }

    public HashMap<String, List<SwitchCase>> getTable() {
        return map;
    }

}
