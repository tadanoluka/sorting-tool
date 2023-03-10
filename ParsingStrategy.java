package sorting;

import java.util.Map;

public interface ParsingStrategy {

    Map<?, Integer> parse();

    int getTotal();
}
