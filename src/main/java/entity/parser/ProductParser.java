package entity.parser;

import entity.Boots;
import entity.Cloth;
import entity.Product;
import enums.Colors;
import enums.ProductTypes;

import java.util.EnumSet;

import static enums.ProductTypes.PRODUCT;
import static enums.Separators.FIELD_SEPARATOR;


public class ProductParser {

    public static Product stringToProduct(String productStr) {
        Product product = null;
        ProductTypes productType = parseProductType(productStr);

        switch (productType) {
            case PRODUCT:
                product = convertToProduct(productStr);
                break;
            case CLOTH:
                product = convertToCloth(productStr);
                break;
            case BOOTS:
                product = convertToBoots(productStr);
                break;
            default:
                break;
        }
        return product;
    }

    private static ProductTypes parseProductType(String productStr) {
        EnumSet<ProductTypes> productTypes = EnumSet.allOf(ProductTypes.class);
        String[] productInformation = productStr.split(String.valueOf(FIELD_SEPARATOR));

        for (ProductTypes productType : productTypes) {
            if (productType.name().equals(productInformation[0])) {
                return productType;
            }
        }

        return PRODUCT;
    }


    private static Product convertToProduct(String productStr) {
        String[] productInformation = productStr.split(String.valueOf(FIELD_SEPARATOR));
        int i = 1;

        Long id = Long.parseLong(productInformation[i++]);
        String productName = productInformation[i++];
        double price = Float.parseFloat(productInformation[i++]);
        double weight = Float.parseFloat(productInformation[i++]);
        String colorName = productInformation[i++];
        double productCount = Float.parseFloat(productInformation[i]);

        return new Product(id, productName, price, weight, Colors.valueOf(colorName), productCount);
    }



    private static Boots convertToBoots(String productStr) {
        String[] productInformation = productStr.split(String.valueOf(FIELD_SEPARATOR));
        int i = 1;

        Long id = Long.parseLong(productInformation[i++]);
        String productName = productInformation[i++];
        double price = Float.parseFloat(productInformation[i++]);
        double weight = Float.parseFloat(productInformation[i++]);
        String colorName = productInformation[i++];
        double productCount = Float.parseFloat(productInformation[i++]);
        Integer size = Integer.parseInt(productInformation[i++]);
        boolean isNaturalSkin = Boolean.parseBoolean(productInformation[i]);

        return new Boots(id, productName, price, weight, Colors.valueOf(colorName), productCount, size, isNaturalSkin);
    }

    private static Cloth convertToCloth(String productStr) {
        String[] productInformation = productStr.split(String.valueOf(FIELD_SEPARATOR));
        int i = 1;

        Long id = Long.parseLong(productInformation[i++]);
        String productName = productInformation[i++];
        double price = Float.parseFloat(productInformation[i++]);
        double weight = Float.parseFloat(productInformation[i++]);
        String colorName = productInformation[i++];
        double productCount = Float.parseFloat(productInformation[i++]);
        String size = productInformation[i++];
        String material = productInformation[i];

        return new Cloth(id, productName, price, weight, Colors.valueOf(colorName), productCount, size, material);
    }

}
