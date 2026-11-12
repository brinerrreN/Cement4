package CementZavod;


import java.time.LocalDateTime;

public class Order implements Comparable<Order> {
    private final LocalDateTime time;
    private final String company;
    private final double quantity;

    public Order(LocalDateTime time, String company, double quantity) {
        this.time = time;
        this.company = company;
        this.quantity = quantity;
    }

    public LocalDateTime getTime() {
        return this.time;
    }

    public String getCompany() {
        return this.company;
    }

    public double getQuantity() {
        return this.quantity;
    }

    public int compareTo(Order other) {
        return this.time.compareTo(other.time);
    }
}

