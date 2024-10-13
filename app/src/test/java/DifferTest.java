import hexlet.code.Differ;
import hexlet.code.Parser;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DifferTest {
    private static final String EXPECTEDSTYLISH =
            """
            {
                chars1: [a, b, c]
              - chars2: [d, e, f]
              + chars2: false
              - checked: false
              + checked: true
              - default: null
              + default: [value1, value2]
              - id: 45
              + id: null
              - key1: value1
              + key2: value2
                numbers1: [1, 2, 3, 4]
              - numbers2: [2, 3, 4, 5]
              + numbers2: [22, 33, 44, 55]
              - numbers3: [3, 4, 5]
              + numbers4: [4, 5, 6]
              + obj1: {nestedKey=value, isNested=true}
              - setting1: Some value
              + setting1: Another value
              - setting2: 200
              + setting2: 300
              - setting3: true
              + setting3: none
            }
            """;

    private static final String EXPECTEDPLAIN =
            """
            Property 'chars2' was updated. From [complex value] to false
            Property 'checked' was updated. From false to true
            Property 'default' was updated. From null to [complex value]
            Property 'id' was updated. From 45 to null
            Property 'key1' was removed
            Property 'key2' was added with value: 'value2'
            Property 'numbers2' was updated. From [complex value] to [complex value]
            Property 'numbers3' was removed
            Property 'numbers4' was added with value: [complex value]
            Property 'obj1' was added with value: [complex value]
            Property 'setting1' was updated. From 'Some value' to 'Another value'
            Property 'setting2' was updated. From 200 to 300
            Property 'setting3' was updated. From true to 'none'
            """;

    @Test
    public void testWithOutFormatJSON() throws Exception {
        final String path1 = "src/test/resources/file1.json";
        final String path2 = "src/test/resources/file2.json";
        String actual = Differ.generate(path1, path2);
        assertThat(EXPECTEDSTYLISH.trim()).isEqualTo(actual);
    }

    @Test
    public void testWithOutFormatYAML() throws Exception {
        final String path1 = "src/test/resources/file3.yaml";
        final String path2 = "src/test/resources/file4.yaml";
        String actual = Differ.generate(path1, path2);
        assertThat(EXPECTEDSTYLISH.trim()).isEqualTo(actual);
    }

    @Test
    public void testStylishJSON() throws Exception {
        String format = "stylish";
        final String path1 = "src/test/resources/file1.json";
        final String path2 = "src/test/resources/file2.json";
        String actual = Differ.generate(path1, path2, format);
        assertThat(EXPECTEDSTYLISH.trim()).isEqualTo(actual);
    }

    @Test
    public void testStylishYAML() throws Exception {
        String format = "stylish";
        final String path1 = "src/test/resources/file3.yaml";
        final String path2 = "src/test/resources/file4.yaml";
        String actual = Differ.generate(path1, path2, format);
        assertThat(EXPECTEDSTYLISH.trim()).isEqualTo(actual);
    }

    @Test
    public void testPlainJSON() throws Exception {
        String format = "plain";
        final String path1 = "src/test/resources/file1.json";
        final String path2 = "src/test/resources/file2.json";
        String actual = Differ.generate(path1, path2, format);
        assertThat(EXPECTEDPLAIN.trim()).isEqualTo(actual);
    }

    @Test
    public void testPlainYAML() throws Exception {
        String format = "plain";
        final String path1 = "src/test/resources/file3.yaml";
        final String path2 = "src/test/resources/file4.yaml";
        String actual = Differ.generate(path1, path2, format);
        assertThat(EXPECTEDPLAIN.trim()).isEqualTo(actual);
    }

    @Test
    public void testJsonJSON() throws Exception {
        String format = "json";
        String expectedJson = Parser.readFile("src/test/resources/result.json");
        final String path1 = "src/test/resources/file1.json";
        final String path2 = "src/test/resources/file2.json";
        String actual = Differ.generate(path1, path2, format);
        assertThat(expectedJson.trim()).isEqualTo(actual);
    }

    @Test
    public void testJsonYAML() throws Exception {
        String format = "json";
        String expectedJson = Parser.readFile("src/test/resources/result.json");
        final String path1 = "src/test/resources/file3.yaml";
        final String path2 = "src/test/resources/file4.yaml";
        String actual = Differ.generate(path1, path2, format);
        assertThat(expectedJson.trim()).isEqualTo(actual);
    }
}
