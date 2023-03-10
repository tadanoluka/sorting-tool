package sorting;


import java.io.*;
import java.util.*;

public class Main {
    public static final DataType DEFAULT_DATA_TYPE = DataType.WORD;
    public static final SortingType DEFAULT_SORTING_TYPE = SortingType.NATURAL;
    public static void main(final String[] args) {
        getResult(args);
    }

    public static boolean isArgumentDefined(String[] args, int index) {
        return index + 1 < args.length && !args[index + 1].startsWith("-");
    }

    public static void getResult(String[] args) {

        // Default values that are used if no other argument value is passed
        DataType dataType = DEFAULT_DATA_TYPE;
        SortingType sortingType = DEFAULT_SORTING_TYPE;
        File inputFile = null;
        File outputFile = null;

        ParsingStrategy parsingStrategy = null;


        // Parsing args[] for arguments
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("-")) {
                CommandLineArgument argument = CommandLineArgument.getArgument(args[i]);
                switch (argument) {
                    case UNKNOWN -> {
                        System.out.printf("\"%s\" is not a valid parameter. It will be skipped\n".formatted(args[i]));
                    }
                    case SORTING_TYPE -> {
                        if (isArgumentDefined(args, i)) {
                            sortingType = SortingType.getSortingType(args[i + 1]);
                        } else {
                            System.out.println("No sorting type defined!");
                        }
                    }
                    case DATA_TYPE -> {
                        if (isArgumentDefined(args, i)) {
                            dataType = DataType.getDataType(args[i + 1]);
                        } else {
                            System.out.println("No data type defined!");
                        }
                    }
                    case INPUT_FILE -> {
                        if (isArgumentDefined(args, i)) {
                            inputFile = new File(args[i + 1]);
                        } else {
                            System.out.println("No input file path defined!");
                        }
                    }
                    case OUTPUT_FILE -> {
                        if (isArgumentDefined(args, i)) {
                            outputFile = new File(args[i + 1]);
                        } else {
                            System.out.println("No output file path defined!");
                        }
                    }
                }
            }
        }

        // Checking if data type argument is valid
        if (dataType == DataType.INVALID) {
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
        if (sortingType == SortingType.INVALID) {
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

        Scanner scanner;
        if (inputFile != null) {
            try {
                scanner = new Scanner(inputFile);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            scanner = new Scanner(System.in);
        }

        PrintWriter printWriter = null;
        if (outputFile != null) {
            try {
                printWriter = new PrintWriter(outputFile);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        switch (dataType) {
            case LONG -> parsingStrategy = new LongParser(scanner, printWriter);
            case LINE -> parsingStrategy = new LineParser(scanner);
            case WORD -> parsingStrategy = new WordParser(scanner);
        }

        Map<?, Integer> inputMap = parsingStrategy.parse();
        int total = parsingStrategy.getTotal();

        scanner.close();

        if (printWriter != null) {
            printWriter.close();
        }

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
