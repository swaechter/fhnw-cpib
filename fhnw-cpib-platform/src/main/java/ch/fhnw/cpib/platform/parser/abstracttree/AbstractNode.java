package ch.fhnw.cpib.platform.parser.abstracttree;

import ch.fhnw.cpib.platform.generator.Generator;
import ch.fhnw.cpib.platform.generator.GeneratorException;
import ch.fhnw.cpib.platform.parser.util.Node;

public abstract class AbstractNode extends Node {

    public AbstractNode(int idendation) {
        super(idendation);
    }

    public void check() {
        // TODO: Implement checks
    }

    public int generate(Generator generator) throws GeneratorException {
        throw new RuntimeException("Code not implemented yet!");
    }
}
