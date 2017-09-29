package ch.fhnw.cpib.compiler;

import org.junit.Test;

import java.io.*;

public class CompilerTest {

    @Test
    public void testCompiler() throws Exception{
        File file = createTemporaryFileFromInputStream("Temp", ".tmp", ClassLoader.class.getResourceAsStream("/Program.iml"));

        Compiler compiler = new Compiler();
        compiler.compileFile(file);
    }

    public static File createTemporaryFileFromInputStream(String suffix, String prefix, InputStream inputstream) throws IOException {
        File file = File.createTempFile(suffix, prefix);
        OutputStream outputstream = new FileOutputStream(file);
        int result = inputstream.read();
        while (result != -1) {
            outputstream.write((byte) result);
            result = inputstream.read();
        }
        return file;
    }
}
