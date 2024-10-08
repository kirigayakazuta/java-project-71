package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Differ {
    public static String readFile(String path) throws Exception {
        Path fullPath = Paths.get(path).toAbsolutePath().normalize();
        return Files.readString(fullPath);
    }

    public static Map<String, String> deserialize(String content) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, new TypeReference<Map<String, String>>(){});
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        Map<String, String> json1 = deserialize(readFile(filepath1));
        Map<String, String> json2 = deserialize(readFile(filepath2));
        Map<String, String> generalJson = new HashMap<>();
        generalJson.putAll(json1);
        generalJson.putAll(json2);
        List<String> keyList = generalJson.keySet().stream().sorted().toList();
        StringBuilder result = new StringBuilder();
        result.append("{\n");

        for (String key : keyList) {
            if (json1.containsKey(key) && json2.containsKey(key)) {
                if (json1.get(key).equals(json2.get(key))) {
                    result.append(String.format("    %s: %s\n", key, json1.get(key)));
                } else {
                    result.append(String.format("  - %s: %s\n", key, json1.get(key)));
                    result.append(String.format("  + %s: %s\n", key, json2.get(key)));
                }
            } else if (json1.containsKey(key)) {
                result.append(String.format("  - %s: %s\n", key, json1.get(key)));
            } else if (json2.containsKey(key)) {
                result.append(String.format("  + %s: %s\n", key, json2.get(key)));
            }
        }

        result.append("}");
        return result.toString();
    }
}
