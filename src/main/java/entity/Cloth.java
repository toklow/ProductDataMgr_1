package entity;

import enums.Colors;
import enums.ProductTypes;

import java.util.Objects;

import static enums.Separators.FIELD_SEPARATOR;

public class Cloth extends Product {

    private String size;
    private String material;

    public Cloth(Long id, String productName, double price, double weight, Colors color, double productCount, String size, String material) {

        super(ProductTypes.CLOTH, id, productName, price, weight, color, productCount);

        this.size = size;
        this.material = material;

    }

    @Override
    public String toString() {
        return ProductTypes.CLOTH.toString() + FIELD_SEPARATOR.getFieldSeparator() + getBasicProductString() + FIELD_SEPARATOR.getFieldSeparator() + size + FIELD_SEPARATOR.getFieldSeparator() + material;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;

        Cloth cloth = (Cloth) o;
        return Objects.equals(this.size, cloth.size) &&
                Objects.equals(this.material, cloth.material);
    }
}
