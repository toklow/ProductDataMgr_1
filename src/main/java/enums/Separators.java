package enums;

public enum Separators {
    FIELD_SEPARATOR('#');

    char fieldSeparator;

    Separators(char fieldSeparator) {
        this.fieldSeparator = fieldSeparator;
    }

    public char getFieldSeparator() {
        return fieldSeparator;
    }
}
