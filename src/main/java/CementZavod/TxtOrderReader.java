package CementZavod;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TxtOrderReader implements OrderReader {
    public List<Order> read(String filePath) throws IOException {
        List<Order> orders = new ArrayList();
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(filePath);
        if (is == null) {
            throw new IOException("Файл не найден в resources: " + filePath);
        } else {
            List<String> lines;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
                lines = (List)reader.lines().collect(Collectors.toList());
            }

            for(String line : lines) {
                if (!line.isBlank()) {
                    String[] parts = line.split("\\|");
                    LocalDateTime time = LocalDateTime.parse(parts[0]);
                    String company = parts[1];
                    double quantity = Double.parseDouble(parts[2]);
                    orders.add(new Order(time, company, quantity));
                }
            }

            return orders;
        }
    }
}
