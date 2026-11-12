package CementZavod;


import java.io.IOException;
import java.util.List;

public interface OrderReader {
    List<Order> read(String var1) throws IOException;
}

