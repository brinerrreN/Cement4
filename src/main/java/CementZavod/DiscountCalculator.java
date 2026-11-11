package CementZavod;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DiscountCalculator {
    private final double basePrice;
    private final double startDiscount;
    private final double stepDiscount;

    public DiscountCalculator(double basePrice, double startDiscount, double stepDiscount) {
        this.basePrice = basePrice;
        this.startDiscount = startDiscount;
        this.stepDiscount = stepDiscount;
    }

    public Map<String, Double> calculate(List<Order> sortedOrders) {
        Map<String, Double> result = new TreeMap();
        double currentDiscount = this.startDiscount;

        for(Order order : sortedOrders) {
            double price = order.getQuantity() * this.basePrice * ((double)1.0F - currentDiscount);
            result.merge(order.getCompany(), price, Double::sum);
            currentDiscount = Math.max((double)0.0F, currentDiscount - this.stepDiscount);
        }

        return result;
    }
}
