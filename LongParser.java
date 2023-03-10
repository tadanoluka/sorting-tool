package sorting;

import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class LongParser implements ParsingStrategy {
    Scanner scanner;
    PrintWriter printWriter;
    Map<Long, Integer> parsedMap;
    int total = 0;

    public LongParser(Scanner scanner, PrintWriter printWriter) {
        this.scanner = scanner;
        this.printWriter = printWriter;
    }

    @Override
    public Map<Long, Integer> parse() {
        // Map (Long, Amount of times)
        TreeMap<Long, Integer> numberMap = new TreeMap<>();

        while (scanner.hasNext()) {
            String userInput = scanner.next();
            Long number;
            try {
                number = Long.parseLong(userInput);
            } catch (NumberFormatException e) {
                String errorMessage = "\"%s\" is not a long. It will be skipped.\n".formatted(userInput);
                if (printWriter != null) {
                    printWriter.print(errorMessage);
                } else {
                    System.out.printf("\"%s\" is not a long. It will be skipped.\n".formatted(userInput));
                }
                continue;
            }
            total += 1;
            numberMap.put(number, numberMap.getOrDefault(number, 0) + 1);
        }
        parsedMap = numberMap;
        return parsedMap;
    }

    @Override
    public int getTotal() {
        return total;
    }
}
