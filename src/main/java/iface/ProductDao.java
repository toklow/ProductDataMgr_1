package iface;

import entity.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getAllProducts();
    Product getProduct(Long productId);
    Product getProduct(String productName);
    void saveProduct(Product product);
    void saveProducts(List<Product> products);
    void removeProduct(Long productId);
    void removeProduct(String productName);
}
