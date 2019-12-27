package iface;

import entity.Product;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface ProductDao {
    List<Product> getAllProducts() throws IOException;

    Product getProductById(Long productId) throws IOException;

    Product getProductByProductName(String productName);

    void saveProduct(Product product) throws IOException;

    void saveProducts(List<Product> products) throws FileNotFoundException;

    void removeProductById(Long productId);

    void removeProductByName(String productName);
}
