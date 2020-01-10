package entity;

import enums.Colors;
import enums.ProductTypes;

import java.util.Objects;

import static enums.Separators.FIELD_SEPARATOR;


public class Product {

    private ProductTypes productType;
    private Long id;
    private String productName;
    private double price;
    private double weight;
    private Colors color;
    private double productCount;

    public Product(Long id, String productName, double price, double weight, Colors color, double productCount) {
        this.productType = ProductTypes.PRODUCT;
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.weight = weight;
        this.color = color;
        this.productCount = productCount;
    }

    public Product(ProductTypes productType, Long id, String productName, double price, double weight, Colors color, double productCount) {
        this.productType = productType;
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.weight = weight;
        this.color = color;
        this.productCount = productCount;
    }

    public ProductTypes getProductType() {
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
        sb.append(FIELD_SEPARATOR.getFieldSeparator());

        sb.append(productName);
        sb.append(FIELD_SEPARATOR.getFieldSeparator());

        sb.append(price);
        sb.append(FIELD_SEPARATOR.getFieldSeparator());

        sb.append(weight);
        sb.append(FIELD_SEPARATOR.getFieldSeparator());

        sb.append(color.name());
        sb.append(FIELD_SEPARATOR.getFieldSeparator());

        sb.append(productCount);

        return sb.toString();
    }

    @Override
    public String toString() {
        return ProductTypes.PRODUCT.toString() + FIELD_SEPARATOR.getFieldSeparator() + getBasicProductString();
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
