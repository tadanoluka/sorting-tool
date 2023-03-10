package sorting;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class LineParser implements ParsingStrategy {
    Scanner scanner;
    int total = 0;

    public LineParser(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public Map<String, Integer> parse() {
        // Map (Line, Amount of times)
        TreeMap<String, Integer> lineMap = new TreeMap<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            total += 1;
            lineMap.put(line, lineMap.getOrDefault(line, 0) + 1);
        }
        return lineMap;
    }

    @Override
    public int getTotal() {
        return total;
    }
}
