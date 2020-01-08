package entity;

import enums.enumProductType;

import static enums.enumProductType.BOOTS;

public class Boots extends Product {

    private Integer size;
    private boolean isNaturalSkin;

    public Boots(Long id, String productName, double price, double weight, String color, double productCount, Integer size, boolean isNaturalSkin)  {

        super(BOOTS, id, productName, price, weight, color, productCount);

        this.size = size;
        this.isNaturalSkin = isNaturalSkin;

    }

    @Override
    public String toString() {
        return enumProductType.BOOTS.toString() + PRODUCT_SEPARATOR + getBasicProductString() + PRODUCT_SEPARATOR + size + PRODUCT_SEPARATOR + isNaturalSkin;
    }
}
