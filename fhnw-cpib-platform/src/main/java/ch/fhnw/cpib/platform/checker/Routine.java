package ch.fhnw.cpib.platform.checker;

import ch.fhnw.cpib.platform.parser.abstracttree.AbstractTree;
import ch.fhnw.cpib.platform.scanner.tokens.Tokens;

import java.util.ArrayList;
import java.util.List;

public class Routine {

    private Scope scope;

    private String identifier;

    private RoutineType routinetype;

    private Tokens.TypeToken returntype;

    private List<Parameter> param = new ArrayList<>();

    private List<AbstractTree.GlobalImport> globalimports = new ArrayList<>();

    public Routine(String identifier, RoutineType routinetype) {
        this.identifier = identifier;
        this.routinetype = routinetype;
        this.scope = new Scope();
    }

    public Routine(String identifier, RoutineType routinetype, Tokens.TypeToken returntype) {
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

    public Tokens.TypeToken getReturnType() {
        return returntype;
    }

    public void addGlobalImport(AbstractTree.GlobalImport globalimport) {
        globalimports.add(globalimport);
    }

    public List<Parameter> getParameters() {
        return this.param;
    }

}
