package CementZavod;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeParseException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HashOrderReader implements OrderReader {
    @Override
    public List<Order> read(String filePath) throws IOException {
        List<Order> orders = new ArrayList();
        InputStream is = getClass().getClassLoader().getResourceAsStream(filePath);

        if (is == null) {
            throw new IOException("Файл не найден в resources: " + filePath);
        }

        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }



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