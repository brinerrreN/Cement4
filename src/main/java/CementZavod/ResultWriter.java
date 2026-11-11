package CementZavod;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResultWriter {
    public void write(String filePath, Map<String, Double> results) throws IOException {
        List<String> lines = new ArrayList();

        for(Map.Entry<String, Double> entry : results.entrySet()) {
            String var10000 = (String)entry.getKey();
            String line = var10000 + " - " + Math.round((Double)entry.getValue());
            lines.add(line);
        }

        Files.write(Paths.get(filePath), lines);
    }
}