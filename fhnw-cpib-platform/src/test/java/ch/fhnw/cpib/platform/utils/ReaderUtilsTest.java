package ch.fhnw.cpib.platform.utils;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ReaderUtilsTest {

    private final String TEST_MESSAGE = "Hello stranger!\nThis is a test message to test the reader!\nBye!\n";

    @Test
    public void testConvertReaderToString() throws IOException {
        File file = ReaderUtils.createTemporaryFileFromContent(TEST_MESSAGE, StandardCharsets.UTF_8);
        Reader reader = new BufferedReader(new FileReader(file));
        String content = ReaderUtils.convertReaderToString(reader);
        Assert.assertEquals(TEST_MESSAGE, content);
    }

    @Test
    public void testCreateTemporaryFileFromContent() throws IOException {
        File file = ReaderUtils.createTemporaryFileFromContent(TEST_MESSAGE, StandardCharsets.UTF_8);
        Assert.assertTrue(file.exists());
    }

    @Test
    public void testGetContentFromFile() throws IOException {
        File file = ReaderUtils.createTemporaryFileFromContent(TEST_MESSAGE, StandardCharsets.UTF_8);
        String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        Assert.assertEquals(TEST_MESSAGE, content);
    }

    @Test
    public void testGetContentFromInputStream() throws IOException {
        File file = ReaderUtils.createTemporaryFileFromContent(TEST_MESSAGE, StandardCharsets.UTF_8);
        String content = ReaderUtils.getContentFromInputStream(new FileInputStream(file), StandardCharsets.UTF_8);
        Assert.assertEquals(TEST_MESSAGE, content);
    }

    @Test
    public void testGetContentLinesFromInputStream() throws IOException {
        File file = ReaderUtils.createTemporaryFileFromContent(TEST_MESSAGE, StandardCharsets.UTF_8);
        List<String> lines = ReaderUtils.getContentLinesFromInputStream(new FileInputStream(file), StandardCharsets.UTF_8);
        String[] testlines = TEST_MESSAGE.split("\n");
        Assert.assertEquals(3, lines.size());
        Assert.assertEquals(testlines[0], lines.get(0));
        Assert.assertEquals(testlines[1], lines.get(1));
        Assert.assertEquals(testlines[2], lines.get(2));
    }
}
