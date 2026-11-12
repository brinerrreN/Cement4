package CementZavod;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeParseException;

public class HashOrderReader implements OrderReader {
    @Override
    public List<Order> read(String filePath) throws IOException {
        List<Order> orders = new ArrayList();
        List<String> lines = Files.readAllLines(Paths.get(filePath));

        for (String line : lines) {
            if (line.isBlank()) continue;
            String[] parts = line.split("#");

            if (parts.length != 3) {
                System.out.println("Пропущена строка (мало данных): " + line);
                continue;
            }

            try {
                LocalDateTime time = LocalDateTime.parse(parts[0]);
                String company = parts[1];
                double quantity = Double.parseDouble(parts[2]);

                if (quantity < 0) {
                    System.out.println("Пропущена строка (отрицательное число): " + line);
                    continue;
                }
                orders.add(new Order(time, company, quantity));

            } catch (DateTimeParseException e) {
                System.out.println("Ошибка: неверная дата в строке: " + line);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка; текст вместо количества в строке: " + line);
            }
        }

        return orders;
    }
}