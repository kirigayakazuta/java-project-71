package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Plain {
    private static String valueToString(Object value) {
        String result;
        if (value == null) {
            result = "null";
        } else if (value instanceof Number || value instanceof Boolean || value instanceof Character) {
            result = value.toString();
        } else if (value instanceof String) {
            result = "'" + value + "'";
        } else {
            result = "[complex value]";
        }
        return result;
    }

    public static String getPlainString(Map<String, Map<String, Object>> diffMap) {
        StringBuilder result = new StringBuilder();
        List<String> keyList = diffMap.keySet().stream().sorted().toList();

        for (String key : keyList) {
            Map<String, Object> data = diffMap.get(key);
            if (data.get("status").equals("modified")) {
                result.append(String.format("Property '%s' was updated. From %s to %s\n",
                        key, valueToString(data.get("value before")), valueToString(data.get("value after"))));
            } else if (data.get("status").equals("removed")) {
                result.append(String.format("Property '%s' was removed\n", key));
            } else if (data.get("status").equals("added")) {
                result.append(String.format("Property '%s' was added with value: %s\n",
                        key, valueToString(data.get("value"))));
            }
        }

        result.setLength(result.length() - 1);
        return result.toString();
    }
}
