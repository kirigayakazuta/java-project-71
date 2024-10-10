import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DifferTest {
    @Test
    public void test() throws Exception {
        final String path1 = "src/test/resources/file1.json";
        final String path2 = "src/test/resources/file2.json";
        String expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }
                """;
        String actual = Differ.generate(path1, path2);
        assertThat(expected.trim()).isEqualTo(actual.trim());
    }

}
