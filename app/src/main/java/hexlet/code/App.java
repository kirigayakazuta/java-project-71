package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Command(name = "gendiff ", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App {
    @Parameters(paramLabel = "filepath1", description = "path to first file")
    private File filepath1;

    @Parameters(paramLabel = "filepath2", description = "path to second file")
    private File filepath2;

    @Option(names = {"-f", "--format"}, paramLabel = "format", description = "output format [default: stylish]",
            defaultValue = "stylish")
    private String format = "stylish";

    public static String readFile(String path) throws Exception {
        Path fullPath = Paths.get(path).toAbsolutePath().normalize();
        return Files.readString(fullPath);
    }

    public static Map<String, String> getData(String content) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, Map.class);
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
