package ch.fhnw.cpib.platform.parser.abstracttree;

import ch.fhnw.cpib.platform.parser.util.Node;
import ch.fhnw.cpib.vm.IVirtualMachine;

public abstract class AbstractNode extends Node {

    public AbstractNode(int idendation) {
        super(idendation);
    }

    public void check() {
        System.out.println("Checks not implemented yet!");
        System.out.println();
    }

    public int code(IVirtualMachine machine, int location) throws IVirtualMachine.CodeTooSmallError {
        throw new RuntimeException("Code not implemented yet!");
    }
}
