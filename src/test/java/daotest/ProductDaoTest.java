package daotest;

import dao.ProductDaoImpl;
import entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoTest {

    private final String daoFileName = "Products.txt";

    @Test
    public void testGetAllProducts() throws IOException {
        //is - File contents

        //then
        ProductDaoImpl productDaoImpl = new ProductDaoImpl(this.daoFileName);
        List<Product> productsFromFile = productDaoImpl.getAllProducts();

        //expected
        Assertions.assertNotNull(productsFromFile);
    }


    @Test
    public void testSaveProducts() throws IOException {
        //is
        List<Product> products = createProductsForTests();

        //then
        ProductDaoImpl productDaoImpl = new ProductDaoImpl(this.daoFileName);
        productDaoImpl.saveProducts(products);
        List<Product> productsFromFile = productDaoImpl.getAllProducts();

        //expected
        Assertions.assertEquals(products.size(), productsFromFile.size());
    }

    @Test
    public void testSaveProduct() throws IOException {
        //is
        Product product = createProductForTests();

        //then
        ProductDaoImpl productDaoImpl = new ProductDaoImpl(this.daoFileName);
        productDaoImpl.saveProduct(product);
        Product productFromFile = productDaoImpl.getProductById(product.getId());

        //expected
        Assertions.assertNotNull(productFromFile);
    }

    @Test
    public void testRemoveProductById() throws IOException {
        //is - File contents
        ProductDaoImpl productDaoImpl = new ProductDaoImpl(this.daoFileName);
        List<Product> products = productDaoImpl.getAllProducts();
        Long productId = products.get(1).getId();

        //then
        productDaoImpl.removeProductById(productId);
        Product productFromFile = productDaoImpl.getProductById(productId);

        //expected
        Assertions.assertNull(productFromFile);
    }

    private List<Product> createProductsForTests() {
        List<Product> products = new ArrayList<Product>();
        products.add(new Product(100L, "Marvel", 200.75, 10.0, "Brown", 100.0));
        products.add(new Product(200L, "Shirt", 300.0, 0.4, "White", 5.0));
        products.add(new Product(210L, "Coat", 300.0, 0.4, "White", 0.0));
        products.add(new Product(300L, "Sneakers", 750.0, 0.8, "Brown", 15.0));

        return products;
    }

    private Product createProductForTests() {
        return new Product(400L, "Chardonnay Australia", 29.99, 0.90, "White", 200.0);
    }
}
