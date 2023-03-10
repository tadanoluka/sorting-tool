package sorting;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class WordParser implements ParsingStrategy {
    Scanner scanner;
    int total = 0;

    public WordParser(Scanner scanner) {
        this.scanner = scanner;
    }
    @Override
    public Map<String, Integer> parse() {
        // Map (Word, Amount of times)
        Map<String, Integer> wordMap = new TreeMap<>();

        while (scanner.hasNext()) {
            String word = scanner.next();
            total += 1;
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }
        return wordMap;
    }

    @Override
    public int getTotal() {
        return total;
    }
}
