package entity.parser;

import entity.Boots;
import entity.Cloth;
import entity.Product;
import utils.FileUtils;

import static utils.FileUtils.enumProductType.*;

public class ProductParser {

    public static Product stringToProduct(String productStr) {
        Product product = null;
        FileUtils.enumProductType productType = parseProductType(productStr);

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

    private static FileUtils.enumProductType parseProductType(String productStr) {
        String [] productInformation = productStr.split(Product.PRODUCT_SEPARATOR);
        FileUtils.enumProductType productType;

        switch (productInformation[0]) {
            case "BOOTS":
                productType = BOOTS;
                break;
            case "CLOTH":
                productType = CLOTH;
                break;
            case "PRODUCT":
            default:
                productType = PRODUCT;
                break;
        }
        return productType;
    }


    private static Product convertToProduct(String productStr) {
        String [] productInformation = productStr.split(Product.PRODUCT_SEPARATOR);
        int i = 1;

        Long id = Long.parseLong(productInformation[i++]);
        String productName = productInformation[i++];
        double price = Float.parseFloat(productInformation[i++]);
        double weight = Float.parseFloat(productInformation[i++]);
        String color = productInformation[i++];
        double productCount = Float.parseFloat(productInformation[i]);

        return new Product(id, productName, price, weight, color, productCount);
    }



    private static Boots convertToBoots(String productStr) {
        String [] productInformation = productStr.split(Product.PRODUCT_SEPARATOR);
        int i = 1;

        Long id = Long.parseLong(productInformation[i++]);
        String productName = productInformation[i++];
        double price = Float.parseFloat(productInformation[i++]);
        double weight = Float.parseFloat(productInformation[i++]);
        String color = productInformation[i++];
        double productCount = Float.parseFloat(productInformation[i++]);
        Integer size = Integer.parseInt(productInformation[i++]);
        boolean isNaturalSkin = Boolean.parseBoolean(productInformation[i]);

        return new Boots(id, productName, price, weight, color, productCount, size, isNaturalSkin);
    }

    private static Cloth convertToCloth(String productStr) {
        String [] productInformation = productStr.split(Product.PRODUCT_SEPARATOR);
        int i = 1;

        Long id = Long.parseLong(productInformation[i++]);
        String productName = productInformation[i++];
        double price = Float.parseFloat(productInformation[i++]);
        double weight = Float.parseFloat(productInformation[i++]);
        String color = productInformation[i++];
        double productCount = Float.parseFloat(productInformation[i++]);
        String size = productInformation[i++];
        String material = productInformation[i];

        return new Cloth(id, productName, price, weight, color, productCount, size, material);
    }
}
