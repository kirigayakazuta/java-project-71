package hexlet.code;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Differ {


    public static String generate(String filepath1, String filepath2) throws Exception {
        Map<String, String> file1 = Parser.deserialize(filepath1);
        Map<String, String> file2 = Parser.deserialize(filepath2);
        Map<String, String> generalMap = new HashMap<>();
        generalMap.putAll(file1);
        generalMap.putAll(file2);
        List<String> keyList = generalMap.keySet().stream().sorted().toList();
        StringBuilder result = new StringBuilder();
        result.append("{\n");

        for (String key : keyList) {
            if (file1.containsKey(key) && file2.containsKey(key)) {
                if (file1.get(key).equals(file2.get(key))) {
                    result.append(String.format("    %s: %s\n", key, file1.get(key)));
                } else {
                    result.append(String.format("  - %s: %s\n", key, file1.get(key)));
                    result.append(String.format("  + %s: %s\n", key, file2.get(key)));
                }
            } else if (file1.containsKey(key)) {
                result.append(String.format("  - %s: %s\n", key, file1.get(key)));
            } else if (file2.containsKey(key)) {
                result.append(String.format("  + %s: %s\n", key, file2.get(key)));
            }
        }

        result.append("}");
        return result.toString();
    }
}
