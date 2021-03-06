package service;

import dao.ProductDaoImpl;
import entity.Product;
import enums.ProductType;
import exception.ProductCountNegativeException;
import exception.ProductNameEmptyException;
import exception.ProductPriceNoPositiveException;
import exception.ProductWeightNoPositiveException;
import iface.ProductService;
import utils.ProductValidator;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService  {

    private static ProductType productType;
    private static ProductServiceImpl instance = null;
    private static ProductDaoImpl productDao = null;

    private ProductServiceImpl(ProductType productType) {
        productDao = new ProductDaoImpl(productType);
    }


    public static ProductServiceImpl getInstance(ProductType productType) {
        if (instance == null) {
            instance = new ProductServiceImpl(productType);
            ProductServiceImpl.productType = productType;
        }
        if (ProductServiceImpl.productType != productType) {
            productDao.setProductType(productType);
            ProductServiceImpl.productType = productType;
        }

        return instance;
    }

    private static boolean isProductValid (Product product)
    {
        ProductValidator productValidator = ProductValidator.getInstance();

        try { return productValidator.isValidate(product); }
        catch (ProductCountNegativeException | ProductNameEmptyException | ProductPriceNoPositiveException | ProductWeightNoPositiveException e) {
            e.printStackTrace();
            return false;
        }
    }   

    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    public Integer getCountProducts() {
        return productDao.getAllProducts().size();
    }

    public Product getProductByName(String productName) {
        return productDao.getProduct(productName);
    }

    public boolean isProductOnStockByName(String productName) {
        Product product = productDao.getProduct(productName);
        if (product == null) {return false;}
        if (product.getProductCount() > 0) {return true;}
        return false;
    }

    public boolean isProductExist(String productName) {
        if (productDao.getProduct(productName) == null) {
            return false;
        }
        return true;
    }

    public boolean isProductExist(Long productId) {
        if (productDao.getProduct(productId) == null) {
            return false;
        }
        return true;
    }

    public void saveProducts(List<Product> products) {
        List<Product> productsChecked = new ArrayList<>();
        for (Product product : products) {
            if (isProductValid(product)) {
                productsChecked.add(product);
            }
        }
        if (!productsChecked.isEmpty()) {
            productDao.saveProducts(productsChecked);
        }
    }

    public void saveProduct(Product product) {
        if (isProductValid(product)) {
            productDao.saveProduct(product);
        }
    }

}
