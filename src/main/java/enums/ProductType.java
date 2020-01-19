package enums;

public enum ProductType {
    PRODUCT_ID("P"),
    CLOTH_ID("C"),
    BOOTS_ID("B");

    private String value;

    ProductType(String value) { this.value = value; }

    public static ProductType valueToEnum(String value) {
        for (ProductType ProductType : ProductType.values()) {
            if (ProductType.value.equals(value)) {
                return ProductType;
            }
        }
        return PRODUCT_ID;
    }

    public String getValue() {
        return value;
    }
}
