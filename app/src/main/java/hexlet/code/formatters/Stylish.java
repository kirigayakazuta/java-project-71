package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {
    private static String valueToString(Object value) {
        String result;
        if (value == null) {
            result = "null";
        } else {
            result = value.toString();
        }
        return result;
    }

    public static String getStylishString(Map<String, Map<String, Object>> diffMap) {
        StringBuilder result = new StringBuilder();
        List<String> keyList = diffMap.keySet().stream().sorted().toList();

        result.append("{\n");
        for (String key : keyList) {
            Map<String, Object> data = diffMap.get(key);
            if (data.get("status").equals("unmodified")) {
                result.append(String.format("    %s: %s\n", key, valueToString(data.get("value"))));
            } else if (data.get("status").equals("modified")) {
                result.append(String.format("  - %s: %s\n", key, valueToString(data.get("value before"))));
                result.append(String.format("  + %s: %s\n", key, valueToString(data.get("value after"))));
            } else if (data.get("status").equals("removed")) {
                result.append(String.format("  - %s: %s\n", key, valueToString(data.get("value"))));
            } else if (data.get("status").equals("added")) {
                result.append(String.format("  + %s: %s\n", key, valueToString(data.get("value"))));
            }
        }
        result.append("}");

        return result.toString();
    }
}
