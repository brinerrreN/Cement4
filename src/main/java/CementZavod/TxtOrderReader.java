package CementZavod;


import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class TxtOrderReader implements OrderReader {
    public List<Order> read(String filePath) throws IOException {
        List<Order> orders = new ArrayList();

        List<String> lines = new ResourceFileReader().readLines(filePath);

            for (String line : lines) {
                if (!line.isBlank()) {
                    String[] parts = line.split("\\|");
                    if (parts.length != 3) {
                        printError("Пропущена строка (неверное количество данных): ", line);
                        continue;
                    }

                    try {
                        LocalDateTime time = LocalDateTime.parse(parts[0]);
                        String company = parts[1];
                        double quantity = Double.parseDouble(parts[2]);
                        if (quantity < 0) {
                            printError("Пропущена строка (отрицательное количество): ", line);
                            continue;
                        }
                        orders.add(new Order(time, company, quantity));
                    } catch (DateTimeParseException e) {
                        printError("Ошибка: неверная дата в строке: ", line);

                    } catch (NumberFormatException e) {
                        printError("Ошибка: текст вместо количества в строке: ", line);
                    }

                }
            }
        return orders;
    }
    private void printError(String message, String line) {
        System.out.println(message + line);
    }
}
