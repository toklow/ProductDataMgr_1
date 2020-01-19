package enums;

public enum Separators {
    FIELD_SEPARATOR("#");

    private String value;

    Separators(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}
