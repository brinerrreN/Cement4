package CementZavod;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        double basePrice = 10.0;
        double startDiscount = 0.50;
        double stepDiscount = 0.05;

        OrderReader txtReader = new TxtOrderReader();
        OrderReader hashReader = new HashOrderReader();

        List<Order> orders = new ArrayList<>();
        orders.addAll(txtReader.read("discount_day.txt"));
        orders.addAll(hashReader.read("discount_day_without_ext"));

        orders.sort(null);

        DiscountCalculator calculator = new DiscountCalculator(basePrice, startDiscount, stepDiscount);
        Map<String, Double> results = calculator.calculate(orders);

        ResultWriter writer = new ResultWriter();
        writer.write("result.txt", results);

        System.out.println("Готово! Результат в result.txt");
    }
}