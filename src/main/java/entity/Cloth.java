package entity;

public class Cloth extends Product {

    private String size;
    private String material;

    public Cloth(Long id, String productName, double price, double weight, String color, double productCount, String size, String material) {

        super(id, productName, price, weight, color, productCount);

        this.size = size;
        this.material = material;

    }

    @Override
    public String toString() {
        return super.toString() + PRODUCT_SEPARATOR + size + PRODUCT_SEPARATOR + material;
    }
}
