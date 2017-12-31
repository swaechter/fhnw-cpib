package ch.fhnw.cpib.platform.generator;

import ch.fhnw.cpib.platform.parser.abstracttree.AbstractTree;
import ch.fhnw.cpib.platform.utils.ReaderUtils;
import jasmin.Main;
import org.apache.commons.io.IOUtils;
import org.javatuples.Pair;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

public class Generator {

    private final StringBuilder stringbuilder;

    private int branchnumber;

    public Generator() {
        this.stringbuilder = new StringBuilder();
    }

    public String generateJasminContent(AbstractTree.Program program) throws GeneratorException {
        program.generateCode(this);
        return stringbuilder.toString();
    }

    public File generateJarFile(AbstractTree.Program program, String jasmincontent) throws GeneratorException {
        try {
            File jasminfile = ReaderUtils.createTemporaryFileFromContent(program.getProgramName() + ".j", jasmincontent, StandardCharsets.UTF_8);
            File classfile = new File(program.getProgramName() + ".class");
            File jarfile = new File(program.getProgramName() + ".jar");

            String[] parameters = new String[]{jasminfile.getAbsolutePath()};
            Main.main(parameters);

            Manifest manifest = new Manifest();
            manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
            manifest.getMainAttributes().put(Attributes.Name.MAIN_CLASS, program.getProgramName());
            JarOutputStream outputstream = new JarOutputStream(new FileOutputStream(jarfile), manifest);

            JarEntry entry = new JarEntry(classfile.getPath().replace("\\", "/"));
            entry.setTime(classfile.lastModified());
            outputstream.putNextEntry(entry);

            BufferedInputStream jarinputstream = new BufferedInputStream(new FileInputStream(classfile));
            IOUtils.copy(jarinputstream, outputstream);
            outputstream.closeEntry();
            outputstream.close();

            return jarfile;
        } catch (Exception excetpion) {
            throw new GeneratorException("Unable to create the Java JAR file: " + excetpion.getMessage(), excetpion);
        }
    }

    public Pair<String, String> executeJarFile(File file) throws GeneratorException {
        try {
            ProcessBuilder processbuilder = new ProcessBuilder("java", "-jar", file.getAbsolutePath());
            processbuilder.directory(file.getParentFile());
            Process process = processbuilder.start();
            process.waitFor();

            Reader inputreader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            Reader errorreader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String inputcontent = ReaderUtils.convertReaderToString(inputreader);
            String errorcontent = ReaderUtils.convertReaderToString(errorreader);
            return new Pair<>(inputcontent, errorcontent);
        } catch (Exception exception) {
            throw new GeneratorException("Unable to execute the Java JAR file: " + exception.getMessage(), exception);
        }
    }

    public void appendLine(String line) {
        stringbuilder.append(line).append(System.lineSeparator());
    }

    public int getNextFreeBranchNumber() {
        return branchnumber++;
    }
}
