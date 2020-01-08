package entity;

import enums.enumProductType;
import java.util.Objects;


public class Product {

    public final static String PRODUCT_SEPARATOR = "#";

    private enumProductType productType;
    private Long id;
    private String productName;
    private double price;
    private double weight;
    private String color;
    private double productCount;

    public Product(Long id, String productName, double price, double weight, String color, double productCount) {
        this.productType = enumProductType.PRODUCT;
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.weight = weight;
        this.color = color;
        this.productCount = productCount;
    }

    public Product(enumProductType productType, Long id, String productName, double price, double weight, String color, double productCount) {
        this.productType = productType;
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.weight = weight;
        this.color = color;
        this.productCount = productCount;
    }

    public enumProductType getProductType() { return productType; }

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

    public String getColor() {
        return color;
    }

    public double getProductCount() {
        return productCount;
    }

    protected String getBasicProductString() {
        return id + PRODUCT_SEPARATOR + productName + PRODUCT_SEPARATOR + price + PRODUCT_SEPARATOR + weight + PRODUCT_SEPARATOR + color + PRODUCT_SEPARATOR + productCount;
    }

    @Override
    public String toString() {
        return enumProductType.PRODUCT.toString() + PRODUCT_SEPARATOR + getBasicProductString();
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
