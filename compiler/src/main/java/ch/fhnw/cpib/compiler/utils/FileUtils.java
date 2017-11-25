package ch.fhnw.cpib.compiler.utils;

import java.io.*;

public class FileUtils {

    public static File createTemporaryFileFromInputStream(String suffix, String prefix, InputStream inputstream) throws IOException {
        File file = File.createTempFile(suffix, prefix);
        file.deleteOnExit();

        OutputStream outputstream = new FileOutputStream(file);
        int result = inputstream.read();
        while (result != -1) {
            outputstream.write((byte) result);
            result = inputstream.read();
        }

        outputstream.close();

        return file;
    }
}
