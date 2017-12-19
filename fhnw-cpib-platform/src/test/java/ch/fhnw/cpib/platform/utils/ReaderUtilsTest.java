package ch.fhnw.cpib.platform.utils;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ReaderUtilsTest {

    private final String TEST_MESSAGE = "Hello stranger!\nThis is a test message to test the reader!\nBye!\n";

    @Test
    public void testGetContentFromFile() throws IOException {
        File file = createTemporaryFile();
        String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        Assert.assertEquals(TEST_MESSAGE, content);
    }

    @Test
    public void testGetContentFromInputStream() throws IOException {
        File file = createTemporaryFile();
        String content = ReaderUtils.getContentFromInputStream(new FileInputStream(file), StandardCharsets.UTF_8);
        Assert.assertEquals(TEST_MESSAGE, content);
    }

    @Test
    public void testGetContentLinesFromInputStream() throws IOException {
        File file = createTemporaryFile();
        List<String> lines = ReaderUtils.getContentLinesFromInputStream(new FileInputStream(file), StandardCharsets.UTF_8);
        String[] testlines = TEST_MESSAGE.split("\n");
        Assert.assertEquals(3, lines.size());
        Assert.assertEquals(testlines[0], lines.get(0));
        Assert.assertEquals(testlines[1], lines.get(1));
        Assert.assertEquals(testlines[2], lines.get(2));
    }

    private File createTemporaryFile() throws IOException {
        File file = File.createTempFile("cpib", "tmp");
        FileUtils.writeStringToFile(file, TEST_MESSAGE, StandardCharsets.UTF_8);
        return file;
    }
}
