package entity;

import enums.Colors;
import enums.SkinType;

import static enums.ProductType.BOOTS_ID;
import static enums.Separators.FIELD_SEPARATOR;

public class Boots extends Product {

    private Integer size;
    private SkinType skinType;

    public Boots(Long id, String productName, double price, double weight, Colors color, double productCount, Integer size, SkinType skinType) {

        super(BOOTS_ID, id, productName, price, weight, color, productCount);

        this.size = size;
        this.skinType = skinType;

    }

    public Integer getSize() {
        return size;
    }

    public SkinType getSkinType() {
        return skinType;
    }

    @Override
    public String toString() {
        return BOOTS_ID.getValue() + FIELD_SEPARATOR.getValue() + getBasicProductString() + FIELD_SEPARATOR.getValue() + size + FIELD_SEPARATOR.getValue() + skinType.name();
    }
}
