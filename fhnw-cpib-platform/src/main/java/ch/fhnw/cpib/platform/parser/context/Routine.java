package ch.fhnw.cpib.platform.parser.context;

import ch.fhnw.cpib.platform.scanner.tokens.Tokens;

import java.util.ArrayList;
import java.util.List;

public class Routine {
    private Scope scope;
    private String identifier;
    private RoutineType routinetype;
    private Tokens.TypeToken.Type returntype;
    private List<Parameter> param = new ArrayList<>();
    private List<GlobalImport> globalimports = new ArrayList<>();

    public Routine(String identifier, RoutineType routinetype) {
        this.identifier = identifier;
        this.routinetype = routinetype;
        this.scope = new Scope();
    }

    public Routine(String identifier, RoutineType routinetype, Tokens.TypeToken.Type returntype) {
        this(identifier, routinetype);
        this.returntype = returntype;
    }

    public Scope getScope() {
        return scope;
    }

    public String getIdentifier() {
        return identifier;
    }

    public RoutineType getRoutineType() {
        return routinetype;
    }

    public Tokens.TypeToken.Type getReturnType() {
        return returntype;
    }

    public void addGlobalImport(GlobalImport g) {
        globalimports.add(g);
    }

    public List<Parameter> getParameters(){
        return this.param;
    }

}
