package servicetest;

import entity.Product;
import impl.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceTest {

    @Test
    public void testGetAllProducts() {
        //is
        Product marvel = new Product(100L, "Marvel", 200.75, 10.0, "Brown", 100.0);
        Product shirt = new Product(200L, "Shirt", 300.0, 0.4, "White", 5.0);
        Product sneakers = new Product(300L, "Sneakers", 750.0, 0.8, "Brown", 15.0);

        List<Product> products = new ArrayList<Product>();
        products.add(marvel);
        products.add(shirt);
        products.add(sneakers);

        //then
        ProductServiceImpl productService = new ProductServiceImpl(products);
        List<Product> productsFromTestClass = productService.getAllProducts();

        //expected
        Assertions.assertEquals(products, productsFromTestClass);   
        
    }
}
