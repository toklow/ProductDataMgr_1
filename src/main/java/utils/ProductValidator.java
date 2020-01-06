package utils;

import entity.Product;
import exception.ProductCountNegativeException;
import exception.ProductNameEmptyException;
import exception.ProductPriceNoPositiveException;
import exception.ProductWeightNoPositiveException;

public class ProductValidator {

    private static ProductValidator instance = null;

    private ProductValidator () {
    }

    public static ProductValidator getInstance() {
        if (instance == null) {
            instance = new ProductValidator();
        }
        return instance;
    }

    public boolean isValidate(Product product) throws ProductCountNegativeException, ProductNameEmptyException, ProductPriceNoPositiveException, ProductWeightNoPositiveException {

        if (product.getProductCount() < 0) {
            throw new ProductCountNegativeException("Product: " + product.getId() + " - " + product.getProductName() + " has a negative count: " + product.getProductCount());
        }

        if (product.getProductName().isEmpty()) {
            throw new ProductNameEmptyException("Product: " + product.getId() + " has no name");
        }

        if (product.getPrice() <= 0) {
            throw new ProductPriceNoPositiveException("Product: " + product.getId() + " - " + product.getProductName() + " has a wrong price: " + product.getPrice());
        }

        if (product.getWeight() <= 0) {
            throw new ProductWeightNoPositiveException("Product: " + product.getId() + " - " + product.getProductName() + " has a wrong weight: " + product.getWeight());
        }

        return true;
    }


}
