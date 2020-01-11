package entity;

import enums.Colors;
import enums.Material;

import java.util.Objects;

import static enums.Separators.CLOTH_ID;
import static enums.Separators.FIELD_SEPARATOR;

public class Cloth extends Product {

    private String size;
    private Material material;

    public Cloth(Long id, String productName, double price, double weight, Colors color, double productCount, String size, Material material) {

        super(CLOTH_ID, id, productName, price, weight, color, productCount);

        this.size = size;
        this.material = material;

    }

    @Override
    public String toString() {
        return CLOTH_ID.getValue() + FIELD_SEPARATOR.getValue() + getBasicProductString() + FIELD_SEPARATOR.getValue() + size + FIELD_SEPARATOR.getValue() + material.name();
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;

        Cloth cloth = (Cloth) o;
        return Objects.equals(this.size, cloth.size) &&
                Objects.equals(this.material, cloth.material);
    }
}
