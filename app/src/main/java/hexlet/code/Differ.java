package hexlet.code;

import hexlet.code.formatters.Formatter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Differ {
    private static boolean objEquals(Object obj1, Object obj2) {
        boolean result;
        if (obj1 == null || obj2 == null) {
            result = obj1 == obj2;
        } else {
            result = obj1.equals(obj2);
        }
        return result;
    }

    private static Map<String, Map<String, Object>> getDiffMap(String filepath1, String filepath2) throws Exception {
        Map<String, Object> file1 = Parser.deserialize(filepath1);
        Map<String, Object> file2 = Parser.deserialize(filepath2);
        Map<String, Object> generalMap = new HashMap<>();
        Map<String, Map<String, Object>> diffMap = new HashMap<>();
        generalMap.putAll(file1);
        generalMap.putAll(file2);

        Set<String> keyList = generalMap.keySet();

        for (String key : keyList) {
            Map<String, Object> data = new HashMap<>();
            if (file1.containsKey(key) && file2.containsKey(key)) {
                if (objEquals(file1.get(key), file2.get(key))) {
                    data.put("status", "unmodified");
                    data.put("value", generalMap.get(key));
                } else {
                    data.put("status", "modified");
                    data.put("value before", file1.get(key));
                    data.put("value after", file2.get(key));
                }
            } else if (file1.containsKey(key)) {
                data.put("status", "removed");
                data.put("value", file1.get(key));
            } else if (file2.containsKey(key)) {
                data.put("status", "added");
                data.put("value", file2.get(key));
            }
            diffMap.put(key, data);
        }

        return diffMap;
    }

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Map<String, Map<String, Object>> diffMap = getDiffMap(filepath1, filepath2);
        return Formatter.getDifferString(diffMap, format);
    }
}
