package service;

import entity.Product;
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

    // It may happen that we have 2 products with the same name, so function should return a list of found products
    public Product getProductByName(String productName) {

        if (this.products.isEmpty()) { return null; }

        for (Product product : this.products) {
            if (product.getProductName().equals(productName)) {
                return product;
            }
        }
        return null;
    }

    public boolean isProductOnStockByName(String productName) {
        for (Product product : this.products)
        {
            // It may happen that we have 2 products with the same name.
            // That's why the loop should be interrupted if we found the product and the stock is not empty for it
            if (product.getProductName().equals(productName) && product.getProductCount() > 0.0)
            {
               return true;
            }
        }
        return false;
    }

    public boolean doesProductExistByName(String productName) {
        for (Product product : this.products)
        {
            if (product.getProductName().equals(productName))
            {
                return true;
            }
        }
        return false;
    }

    public boolean doesProductExistById(Long productId)
    {
        for (Product product : this.products)
        {
            if (product.getId().equals(productId))
            {
                return true;
            }
        }
        return false;
    }
}
