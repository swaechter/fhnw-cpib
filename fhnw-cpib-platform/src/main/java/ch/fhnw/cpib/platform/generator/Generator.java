package ch.fhnw.cpib.platform.generator;

import ch.fhnw.cpib.platform.parser.abstracttree.AbstractTree;
import ch.fhnw.cpib.platform.utils.ReaderUtils;
import jasmin.Main;
import org.javatuples.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class Generator {

    private final StringBuilder stringbuilder;

    public Generator() {
        this.stringbuilder = new StringBuilder();
    }

    public String generateJasminContent(AbstractTree.Program program) {
        //appendLine(".source Program.j");
        appendLine(".class public Program");
        appendLine(".super java/lang/Object");
        appendLine(".method public static main([Ljava/lang/String;)V");
        appendLine(".limit stack 100");
        appendLine(".limit locals 100");
        appendLine("getstatic java/lang/System/out Ljava/io/PrintStream;");
        appendLine("ldc 42");
        appendLine("invokevirtual java/io/PrintStream/println(I)V");
        appendLine("return");
        appendLine(".end method");
        return stringbuilder.toString();
    }

    public File generateJavaClassFile(String jasmincontent) throws GeneratorException {
        try {
            File inputfile = ReaderUtils.createTemporaryFileFromContent(jasmincontent, StandardCharsets.UTF_8);
            String[] parameters = new String[]{inputfile.getAbsolutePath(), "-d", inputfile.getParent()};
            Main.main(parameters);
            return new File(inputfile.getParent() + "/Program.class");
        } catch (Exception excetpion) {
            throw new GeneratorException("Unable to temporary store the Jasmin file or read the compiled file", excetpion);
        }
    }

    public Pair<String, String> executeJavaClassFile(File file) throws GeneratorException {
        try {
            ProcessBuilder processbuilder = new ProcessBuilder("java", "Program");
            processbuilder.directory(file.getParentFile());
            Process process = processbuilder.start();
            process.waitFor();

            Reader inputreader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            Reader errorreader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String inputcontent = ReaderUtils.convertReaderToString(inputreader);
            String errorcontent = ReaderUtils.convertReaderToString(errorreader);
            return new Pair<>(inputcontent, errorcontent);
        } catch (Exception exception) {
            throw new GeneratorException("Unable to execute the Java code", exception);
        }
    }

    public void appendLine(String line) {
        stringbuilder.append(line + '\n');
    }

    public String getRandomIdentifierName() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        return uuid;
    }
}
