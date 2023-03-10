package sorting;

import java.util.Objects;

public enum DataType {
    INVALID("unknown", "unknown", " "),
    LONG("long", "numbers", " "),
    LINE("line", "lines", "\n"),
    WORD("word", "words", " ");

    private final String STRING_ARGUMENT;
    private final String NAME_PLURAL;
    private final String SPECIAL_ENDING;

    DataType(String stringArgument, String namePlural, String specialEnding) {
        this.STRING_ARGUMENT = stringArgument;
        this.NAME_PLURAL = namePlural;
        this.SPECIAL_ENDING = specialEnding;
    }

    public String getStringArgument() {
        return STRING_ARGUMENT;
    }

    public String getNamePlural() {
        return NAME_PLURAL;
    }

    public String getSpecialEnding() {
        return SPECIAL_ENDING;
    }

    public static DataType getDataType(String stringValue) {
        for (DataType dataType : DataType.values()) {
            if (Objects.equals(dataType.getStringArgument(), stringValue)) {
                return dataType;
            }
        }
        return INVALID;
    }
}
