package sorting;

import java.util.Objects;

public enum CommandLineArgument {
    UNKNOWN("unknown"),
    DATA_TYPE("-dataType"),
    SORTING_TYPE("-sortingType"),
    INPUT_FILE("-inputFile"),
    OUTPUT_FILE("-outputFile");

    private final String ARGUMENT;

    CommandLineArgument(String argument) {
        this.ARGUMENT = argument;
    }

    private String getCodeName(){
        return ARGUMENT;
    }

    public static CommandLineArgument getArgument(String string) {
        for (CommandLineArgument commandLineArgument : CommandLineArgument.values()) {
            if (Objects.equals(commandLineArgument.getCodeName(), string)) {
                return  commandLineArgument;
            }
        }
        return UNKNOWN;
    }

}
