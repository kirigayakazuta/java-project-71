package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {
    private static String getExtension(String path) {
        Path fullPath = Paths.get(path).toAbsolutePath().normalize();
        String fileName = fullPath.getFileName().toString();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex);
    }

    private static String readFile(String path) throws Exception {
        Path fullPath = Paths.get(path).toAbsolutePath().normalize();
        return Files.readString(fullPath);
    }

    private static Map<String, String> deserializeJSON(String path) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = readFile(path);
        return mapper.readValue(content, new TypeReference<Map<String, String>>(){});
    }

    private static Map<String, String> deserializeYAML(String path) throws Exception {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER));
        mapper.findAndRegisterModules();
        String content = readFile(path);
        return mapper.readValue(content, new TypeReference<Map<String, String>>(){});
    }

    public static Map<String, String> deserialize(String path) throws Exception {
        Map<String, String> result;
        String extension = getExtension(path);
        if (extension.equals(".json")) {
            result = deserializeJSON(path);
        } else if (extension.equals(".yaml") || extension.equals(".yml")) {
            result = deserializeYAML(path);
        } else {
            throw new IllegalArgumentException("Unsupported extension: " + extension);
        }
        return result;
    }
}
