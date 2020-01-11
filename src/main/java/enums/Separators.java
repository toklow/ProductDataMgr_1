package enums;

public enum Separators {
    FIELD_SEPARATOR("#"),
    PRODUCT_ID("P"),
    CLOTH_ID("C"),
    BOOTS_ID("B");

    String value;

    Separators(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
