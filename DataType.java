package sorting;

import java.util.Objects;

public enum DataType {
    LONG("long", "numbers", " "),
    LINE("line", "lines", "\n"),
    WORD("word", "words", " ");

    private String stringArgument;
    private String namePlural;

    // Kinda kludge, but it'll do
    private String specialEnding;

    DataType(String stringArgument, String namePlural, String specialEnding) {
        this.stringArgument = stringArgument;
        this.namePlural = namePlural;
        this.specialEnding = specialEnding;
    }

    public String getStringArgument() {
        return stringArgument;
    }

    public String getNamePlural() {
        return namePlural;
    }

    public String getSpecialEnding() {
        return specialEnding;
    }

    public static DataType getDataType(String stringValue) {
        for (DataType dataType : DataType.values()) {
            if (Objects.equals(dataType.getStringArgument(), stringValue)) {
                return dataType;
            }
        }
        return null;
    }
}
