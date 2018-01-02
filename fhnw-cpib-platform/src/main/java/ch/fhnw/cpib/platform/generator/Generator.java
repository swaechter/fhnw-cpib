package ch.fhnw.cpib.platform.generator;

import ch.fhnw.cpib.platform.parser.abstracttree.AbstractTree;
import ch.fhnw.cpib.platform.utils.ReaderUtils;
import com.squareup.javapoet.JavaFile;
import org.javatuples.Pair;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Generator {

    public JavaFile generateJavaFile(AbstractTree.Program program) throws GeneratorException {
        return program.generateCode();
    }

    public File generateJarFile(JavaFile javafile, AbstractTree.Program program) throws GeneratorException {
        try {
            // Create the package directory
            File directory = new File("fhnw");
            directory.mkdir();

            // Create the Java source file
            File javasourcefile = new File(directory.getAbsolutePath() + "/" + program.getProgramName() + ".java");
            Files.write(javasourcefile.toPath(), javafile.toString().getBytes(StandardCharsets.UTF_8));

            // Compile Java source file
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            compiler.run(null, null, null, javasourcefile.getPath());

            return new File("Meh");
            // Move the class file
            /*File jarfile = new File(program.getProgramName() + ".jar");
            String classcode = ReaderUtils.getContentFromFile(new File(program.getProgramName() + ".class"), StandardCharsets.UTF_8);
            File classfile = new File("default/" + program.getProgramName() + ".class");

            //ReaderUtils.
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

/*            // Load and instantiate compiled class.
            File root = new File(sourceFile.getParent());
            URL url = root.toURI().toURL();
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{url});
            Class<?> cls = Class.forName("Test", true, classLoader); // Should print "hello".
            Object instance = cls.newInstance(); // Should print "world".
            System.out.println("Name: " + instance.getClass().getCanonicalName());


            File jasminfile = ReaderUtils.createTemporaryFileFromContent(program.getProgramName() + ".j", program.toString(), StandardCharsets.UTF_8);
            File classfile = new File(program.getProgramName() + ".class");
            File jarfile = new File(program.getProgramName() + ".jar");

            String[] parameters = new String[]{jasminfile.getAbsolutePath()};
            Main.main(parameters);

            Manifest manifest = new Manifest();
            manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
            manifest.getMainAttributes().put(Attributes.Name.MAIN_CLASS, jvmcompiler.getProgramName());
            JarOutputStream outputstream = new JarOutputStream(new FileOutputStream(jarfile), manifest);

            JarEntry entry = new JarEntry(classfile.getPath().replace("\\", "/"));
            entry.setTime(classfile.lastModified());
            outputstream.putNextEntry(entry);

            BufferedInputStream jarinputstream = new BufferedInputStream(new FileInputStream(classfile));
            IOUtils.copy(jarinputstream, outputstream);
            outputstream.closeEntry();
            outputstream.close();*/
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
}
