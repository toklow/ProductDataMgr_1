package iface;

import entity.Product;
import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductByName(String productName);
    boolean isProductOnStockByName(String productName);
    boolean doesProductExistByName(String productName);
    boolean doesProductExistById(Long productId);
}
