package entity;

import enums.Colors;
import enums.ProductTypes;

import static enums.ProductTypes.BOOTS;
import static enums.Separators.FIELD_SEPARATOR;

public class Boots extends Product {

    private Integer size;
    private boolean isNaturalSkin;

    public Boots(Long id, String productName, double price, double weight, Colors color, double productCount, Integer size, boolean isNaturalSkin) {

        super(BOOTS, id, productName, price, weight, color, productCount);

        this.size = size;
        this.isNaturalSkin = isNaturalSkin;

    }

    @Override
    public String toString() {
        return ProductTypes.BOOTS.toString() + FIELD_SEPARATOR.getFieldSeparator() + getBasicProductString() + FIELD_SEPARATOR.getFieldSeparator() + size + FIELD_SEPARATOR.getFieldSeparator() + isNaturalSkin;
    }
}
