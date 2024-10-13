package hexlet.code.formatters;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class Json {

    public static String getPlainString(Map<String, Map<String, Object>> diffMap) throws Exception {
//        ObjectMapper mapper = new ObjectMapper();
//        DefaultPrettyPrinter p = new DefaultPrettyPrinter();
//        DefaultPrettyPrinter.Indenter i = new DefaultIndenter("  ", "\n");
//        p.indentArraysWith(i);
//        p.indentObjectsWith(i);
//        mapper.setDefaultPrettyPrinter(p);
//        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(diffMap);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(diffMap);
    }
}
