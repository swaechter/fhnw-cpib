package ch.fhnw.cpib.platform.checker;

import ch.fhnw.cpib.platform.scanner.tokens.Tokens;

import java.util.ArrayList;
import java.util.List;

public class Switch {

    private String name;

    private Tokens.TypeToken.Type type;

    private List<SwitchCase> switchCaseList = new ArrayList<>();

    public Switch(String name, Tokens.TypeToken.Type type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Tokens.TypeToken.Type getType() {
        return type;
    }

    public void setType(Tokens.TypeToken.Type type) {
        this.type = type;
    }

    public List<SwitchCase> getSwitchCaseList() {
        return switchCaseList;
    }

    public void addSwitchCase(SwitchCase switchCase) {
        switchCaseList.add(switchCase);
    }
}
