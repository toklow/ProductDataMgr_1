package service;

import dao.ProductDaoImpl;
import entity.Product;
import iface.ProductService;
import utils.FileUtils;

import java.io.IOException;
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


    public List<Product> getAllProducts() throws IOException {
        return productDao.getAllProducts();
    }

    public Integer getCountProducts() throws IOException {
        return productDao.getAllProducts().size();
    }

    // It may happen that we have 2 products with the same name, so function should return a list of found products
    public Product getProductByName(String productName) throws IOException {
        return productDao.getProductByProductName(productName);
    }

    public boolean isProductOnStockByName(String productName) throws IOException {
        Product product = productDao.getProductByProductName(productName);
        if (product == null) {return false;}
        if (product.getProductCount() > 0) {return true;}
        return false;
    }

    public boolean isProductExist(String productName) throws IOException {
        if (productDao.getProductByProductName(productName) == null) {
            return false;
        }
        return true;
    }

    public boolean isProductExist(Long productId) throws IOException {
        if (productDao.getProductById(productId) == null) {
            return false;
        }
        return true;
    }

    public void saveProducts(List<Product> products) {
        productDao.saveProducts(products);
    }

}
