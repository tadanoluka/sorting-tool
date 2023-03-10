package sorting;

import java.util.Objects;

public enum CommandLineArgument {
    UNKNOWN("unknown"),
    DATA_TYPE("-dataType"),
    SORT_INTEGERS("-sortingType");

    private String argument;

    CommandLineArgument(String argument) {
        this.argument = argument;
    }

    private String getCodeName(){
        return argument;
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
