package hexlet.code.formatters;

import java.util.Map;

public class Formatter {
    public static String getDifferString(Map<String, Map<String, Object>> diffMap, String format) throws Exception {
        String result;
        if (format.equals("plain")) {
            result = Plain.getPlainString(diffMap);
        } else if (format.equals("json")) {
            result = Json.getPlainString(diffMap);
        } else {
            result = Stylish.getStylishString(diffMap);
        }
        return result;
    }
}
