package sorting;

import java.util.Objects;

public enum SortingType {
    INVALID("unknown"),
    NATURAL("natural"),
    BY_COUNT("byCount");

    private final String STRING_VALUE;

    SortingType(String stringValue) {
        this.STRING_VALUE = stringValue;
    }

    public String getStringValue() {
        return STRING_VALUE;
    }

    public static SortingType getSortingType(String stringValue) {
        for (SortingType sortingType : SortingType.values()) {
            if (Objects.equals(sortingType.getStringValue(), stringValue)) {
                return sortingType;
            }
        }
        return INVALID;
    }
}
