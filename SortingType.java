package sorting;

import java.util.Objects;

public enum SortingType {
    NATURAL("natural"),
    BY_COUNT("byCount");

    String stringValue;

    SortingType(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public static SortingType getSortingType(String stringValue) {
        for (SortingType sortingType : SortingType.values()) {
            if (Objects.equals(sortingType.getStringValue(), stringValue)) {
                return sortingType;
            }
        }
        return null;
    }
}
