package sorting;


import java.util.*;

public class Main<K> {
    public static final DataType DEFAULT_DATA_TYPE = DataType.WORD;
    public static final SortingType DEFAULT_SORTING_TYPE = SortingType.NATURAL;
    public static void main(final String[] args) {
        getResult(args);
    }

    public static <K> void getResult(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Default values that are used if no other argument value is passed
        DataType dataType = DEFAULT_DATA_TYPE;
        SortingType sortingType = DEFAULT_SORTING_TYPE;

        ParsingStrategy parsingStrategy = null;

        Map<String, String> argsMap = new HashMap<>();

        // Parsing args[] for arguments
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("-")) {
                CommandLineArgument argument = CommandLineArgument.getArgument(args[i]);
                switch (argument) {
                    case UNKNOWN -> {
                        System.out.printf("\"%s\" is not a valid parameter. It will be skipped\n".formatted(args[i]));
                    }
                    case SORTING_TYPE -> {
                        if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                            sortingType = SortingType.getSortingType(args[i + 1]);
                        } else {
                            System.out.println("No sorting type defined!");
                        }
                    }
                    case DATA_TYPE -> {
                        if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                            dataType = DataType.getDataType(args[i + 1]);
                        } else {
                            System.out.println("No data type defined!");
                        }
                    }
                }
            }
        }

        // Checking if data type argument is valid
        if (dataType == null) {
            System.out.println("Invalid data type argument");
            System.out.println("Supported data type arguments:");
            StringBuilder supportedDataTypes = new StringBuilder();
            for (DataType type : DataType.values()) {
                supportedDataTypes.append(type.getStringArgument()).append(", ");
            }
            supportedDataTypes.deleteCharAt(supportedDataTypes.lastIndexOf(", "));
            System.out.println(supportedDataTypes);
            return;
        }
        // Checking if sorting type argument is valid
        if (sortingType == null) {
            System.out.println("Invalid sorting type argument");
            System.out.println("Supported sorting type arguments:");
            StringBuilder supportedSortingTypes = new StringBuilder();
            for (SortingType type : SortingType.values()) {
                supportedSortingTypes.append(type.getStringValue()).append(", ");
            }
            supportedSortingTypes.deleteCharAt(supportedSortingTypes.lastIndexOf(", "));
            System.out.println(supportedSortingTypes);
            return;
        }

        switch (dataType) {
            case LONG -> parsingStrategy = new LongParser(scanner);
            case LINE -> parsingStrategy = new LineParser(scanner);
            case WORD -> parsingStrategy = new WordParser(scanner);
        }

        Map<?, Integer> inputMap = parsingStrategy.parse();
        int total = parsingStrategy.getTotal();

        System.out.printf("Total %s: %d.\n".formatted(dataType.getNamePlural(), total));
        switch (sortingType) {
            case NATURAL -> {
                System.out.print("Sorted data:");
                String specialEnding = dataType.getSpecialEnding();
                System.out.print(specialEnding);
                inputMap.forEach((userInput, times) -> {
                   for (int i = 0; i < times; i++) {
                       System.out.print(userInput + specialEnding);
                   }
                });
            }
            case BY_COUNT -> {
                inputMap.entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByValue())
                        .forEach(entry -> {
                            String userInput = entry.getKey().toString();
                            int times = entry.getValue();
                            double percentileDouble = (double) times / total * 100;
                            int percentile = (int) Math.round(percentileDouble);
                            String output = "%s: %d time(s), %d%%".formatted(userInput, times, percentile);
                            System.out.println(output);
                        });
            }
        }

    }

}
