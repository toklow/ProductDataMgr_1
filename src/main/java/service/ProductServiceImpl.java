package service;

import dao.ProductDaoImpl;
import entity.Product;
import exception.ProductCountNegativeException;
import exception.ProductNameEmptyException;
import exception.ProductPriceNoPositiveException;
import exception.ProductWeightNoPositiveException;
import iface.ProductService;
import utils.FileUtils;
import utils.ProductValidator;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService  {

    private static FileUtils.enumProductType productType;
    private static ProductServiceImpl instance = null;
    private static ProductDaoImpl productDao = null;

    private ProductServiceImpl(FileUtils.enumProductType productType) {
        ProductServiceImpl.productType = productType;
        productDao = new ProductDaoImpl(productType);
    }


    public static ProductServiceImpl getInstance(FileUtils.enumProductType productType) {
        if (instance == null) {
            instance = new ProductServiceImpl(productType);
        }
        if (ProductServiceImpl.productType != productType) {
            productDao.setProductParameters(productType);
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
        return productDao.getProductByProductName(productName);
    }

    public boolean isProductOnStockByName(String productName) {
        Product product = productDao.getProductByProductName(productName);
        if (product == null) {return false;}
        if (product.getProductCount() > 0) {return true;}
        return false;
    }

    public boolean isProductExist(String productName) {
        if (productDao.getProductByProductName(productName) == null) {
            return false;
        }
        return true;
    }

    public boolean isProductExist(Long productId) {
        if (productDao.getProductById(productId) == null) {
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
