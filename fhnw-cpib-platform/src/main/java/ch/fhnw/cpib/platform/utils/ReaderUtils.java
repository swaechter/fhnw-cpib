package ch.fhnw.cpib.platform.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ReaderUtils {

    public static String getContentFromFile(File file, Charset charset) throws IOException {
        return FileUtils.readFileToString(file, charset);
    }

    public static String getContentFromInputStream(InputStream inputstream, Charset charset) throws IOException {
        String content = IOUtils.toString(inputstream, charset);
        inputstream.close();
        return content;
    }

    public static List<String> getContentLinesFromInputStream(InputStream inputstream, Charset charset) throws IOException {
        List<String> lines = IOUtils.readLines(inputstream, StandardCharsets.UTF_8);
        inputstream.close();
        return lines;
    }
}
