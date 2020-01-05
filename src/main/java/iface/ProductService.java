package iface;

import entity.Product;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    List<Product> getAllProducts() throws IOException;
    Integer getCountProducts() throws IOException;
    Product getProductByName(String productName) throws IOException;
    boolean isProductOnStockByName(String productName) throws IOException;
    boolean isProductExist(String productName) throws IOException;
    boolean isProductExist(Long productId) throws IOException;
}
