package ch.fhnw.cpib.platform.generator;

import ch.fhnw.cpib.vm.IVirtualMachine;
import ch.fhnw.cpib.vm.VirtualMachine;
import org.junit.Test;

import java.io.ByteArrayInputStream;

public class TestGenerator {

    @Test
    public void testGenerator() throws Exception {
        // Fake the standard input and provide a value for debugin
        ByteArrayInputStream in = new ByteArrayInputStream("42\n".getBytes());
        System.setIn(in);

        // Create the virtual machine
        int codesize = 1000;
        int storesize = 1000;
        IVirtualMachine machine = new VirtualMachine(codesize, storesize);

        // Generate the code by hand
        machine.IntLoad(0, 995);
        machine.IntInput(1, "x");
        machine.IntLoad(2, 995);
        machine.Deref(3);
        machine.IntOutput(4, "x");
        machine.Stop(5);

        // Execute the code for the abstract tree
        machine.execute();
    }
}
