package CementZavod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ResourceFileReader {

    public List<String> readLines(String filePath) throws IOException {
        InputStream is = getClass().getClassLoader()
                .getResourceAsStream(filePath);

        if (is == null) {
            throw new IOException("Файл не найден в resources: " + filePath);
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, StandardCharsets.UTF_8))) {
            return  reader.lines().toList();
        }
    }
}
