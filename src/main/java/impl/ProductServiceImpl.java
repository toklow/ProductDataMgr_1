package impl;

import entity.Product;
import entity.User;
import iface.ProductService;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService  {

    private List<Product> products;

    // Constructor with empty list
    public ProductServiceImpl() {
        this.products = new ArrayList<Product>();
    }

    // Constructor with non-empty list
    public ProductServiceImpl(List<Product> products) {
        this.products = new ArrayList<Product>(products);
    }

    public List<Product> getAllProducts() {
        return this.products;
    }

    public Product getProductByName(String productName) {
        return null;
    }

    public boolean isProductOnStockByName(String productName) {
        return false;
    }

    public boolean doesProductExistByName(String productName) {
        return false;
    }

    public boolean doesProductExistById(Long productId){
        return false;
    }
}
