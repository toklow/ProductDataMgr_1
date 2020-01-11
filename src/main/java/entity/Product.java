package entity;

import enums.Colors;
import enums.Separators;

import java.util.Objects;

import static enums.Separators.FIELD_SEPARATOR;
import static enums.Separators.PRODUCT_ID;


public class Product {

    private Separators productType;
    private Long id;
    private String productName;
    private double price;
    private double weight;
    private Colors color;
    private double productCount;

    public Product(Long id, String productName, double price, double weight, Colors color, double productCount) {
        this.productType = PRODUCT_ID;
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.weight = weight;
        this.color = color;
        this.productCount = productCount;
    }

    public Product(Separators productType, Long id, String productName, double price, double weight, Colors color, double productCount) {
        this.productType = productType;
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.weight = weight;
        this.color = color;
        this.productCount = productCount;
    }

    public Separators getProductType() {
        return productType;
    }

    public Long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public double getWeight() {
        return weight;
    }

    public Colors getColor() {
        return color;
    }

    public double getProductCount() {
        return productCount;
    }

    protected String getBasicProductString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id);
        sb.append(FIELD_SEPARATOR.getValue());

        sb.append(productName);
        sb.append(FIELD_SEPARATOR.getValue());

        sb.append(price);
        sb.append(FIELD_SEPARATOR.getValue());

        sb.append(weight);
        sb.append(FIELD_SEPARATOR.getValue());

        sb.append(color.name());
        sb.append(FIELD_SEPARATOR.getValue());

        sb.append(productCount);

        return sb.toString();
    }

    @Override
    public String toString() {
        return PRODUCT_ID.getValue() + FIELD_SEPARATOR.getValue() + getBasicProductString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;                                        // Same object
        if (o == null || getClass() != o.getClass()) return false;         // Null object or wrong class

        Product product = (Product) o;
        return Objects.equals(this.productType, product.productType) &&
               Objects.equals(this.id, product.id) &&
               Objects.equals(this.productName, product.productName) &&
               Objects.equals(this.price, product.price) &&
               Objects.equals(this.weight, product.weight) &&
               Objects.equals(this.color, product.color) &&
               Objects.equals(this.productCount, product.productCount);
    }
}
