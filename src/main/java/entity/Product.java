package entity;

public class Product {

    public final static String PRODUCT_SEPARATOR = "#";

    private Long id;
    private String productName;
    private double price;
    private double weight;
    private String color;
    private double productCount;

    public Product(Long id, String productName, double price, double weight, String color, double productCount) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.weight = weight;
        this.color = color;
        this.productCount = productCount;
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

    public String getColor() {
        return color;
    }

    public double getProductCount() {
        return productCount;
    }

    @Override
    public String toString() {
        return id + PRODUCT_SEPARATOR + productName + PRODUCT_SEPARATOR + price + PRODUCT_SEPARATOR + weight + PRODUCT_SEPARATOR + color + PRODUCT_SEPARATOR + productCount;
    }
}
