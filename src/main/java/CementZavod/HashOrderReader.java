package CementZavod;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HashOrderReader implements OrderReader {
    public List<Order> read(String filePath) throws IOException {
        List<Order> orders = new ArrayList();

        for(String line : Files.readAllLines(Paths.get(filePath))) {
            if (!line.isBlank()) {
                String[] parts = line.split("#");
                LocalDateTime time = LocalDateTime.parse(parts[0]);
                String company = parts[1];
                double quantity = Double.parseDouble(parts[2]);
                orders.add(new Order(time, company, quantity));
            }
        }

        return orders;
    }
}
