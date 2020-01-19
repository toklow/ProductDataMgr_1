package daotest;

import dao.ProductDaoImpl;
import entity.Product;
import enums.Colors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static enums.ProductType.PRODUCT_ID;


public class ProductDaoTest {

    private final String daoFileName = "Products.txt";

    @Test
    public void testGetAllProducts() {
        //is - File contents

        //then
        ProductDaoImpl productDaoImpl = new ProductDaoImpl(PRODUCT_ID);
        List<Product> productsFromFile = productDaoImpl.getAllProducts();

        //expected
        Assertions.assertNotNull(productsFromFile);
    }


    @Test
    public void testSaveProducts() {
        //is
        List<Product> products = createProductsForTests();

        //then
        ProductDaoImpl productDaoImpl = new ProductDaoImpl(PRODUCT_ID);
        productDaoImpl.saveProducts(products);
        List<Product> productsFromFile = productDaoImpl.getAllProducts();

        //expected
        Assertions.assertEquals(products.size(), productsFromFile.size());
    }

    @Test
    public void testSaveProduct() {
        //is
        Product product = createProductForTests();

        //then
        ProductDaoImpl productDaoImpl = new ProductDaoImpl(PRODUCT_ID);
        productDaoImpl.saveProduct(product);
        Product productFromFile = productDaoImpl.getProduct(product.getId());

        //expected
        Assertions.assertNotNull(productFromFile);
    }

    @Test
    public void testRemoveProductById() {
        //is - File contents
        ProductDaoImpl productDaoImpl = new ProductDaoImpl(PRODUCT_ID);
        List<Product> products = productDaoImpl.getAllProducts();
        Long productId = products.get(1).getId();

        //then
        productDaoImpl.removeProduct(productId);
        Product productFromFile = productDaoImpl.getProduct(productId);

        //expected
        Assertions.assertNull(productFromFile);
    }

    private List<Product> createProductsForTests() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(100L, "Marvel", 200.75, 10.0, Colors.RED, 100.0));
        products.add(new Product(200L, "Shirt", 300.0, 0.4, Colors.WHITE, 5.0));
        products.add(new Product(210L, "Coat", 300.0, 0.4, Colors.BLACK, 0.0));
        products.add(new Product(300L, "Sneakers", 750.0, 0.8, Colors.YELLOW, 15.0));

        return products;
    }

    private Product createProductForTests() {
        return new Product(400L, "Chardonnay Australia", 29.99, 0.90, Colors.GREEN, 200.0);
    }
}
