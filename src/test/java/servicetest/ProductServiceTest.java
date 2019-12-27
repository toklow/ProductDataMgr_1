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
        List<Product> products = createProductsForTests();

        //then
        ProductServiceImpl productService = new ProductServiceImpl(products);
        List<Product> productsFromTestClass = productService.getAllProducts();

        //expected
        Assertions.assertEquals(products, productsFromTestClass);
    }

    @Test
    public void testGetAllProductsNegative() {
        //is
        List<Product> products = createProductsForTests();

        //then
        ProductServiceImpl productService = new ProductServiceImpl(products);
        List<Product> productsFromTestClass = productService.getAllProducts();
        products.remove(1);

        //expected
        Assertions.assertNotEquals(products, productsFromTestClass);
    }

    @Test
    public void testGetProductByName() {
        //is
        List<Product> products = createProductsForTests();

        //then
        ProductServiceImpl productService = new ProductServiceImpl(products);
        Product productFromTestClass = productService.getProductByName(products.get(1).getProductName());

        //expected
        Assertions.assertNotNull(productFromTestClass);
    }

    @Test
    public void testGetProductByNameNegative() {
        //is
        List<Product> products = createProductsForTests();

        //then
        ProductServiceImpl productService = new ProductServiceImpl(products);
        Product productFromTestClass = productService.getProductByName("Not existing product");

        //expected
        Assertions.assertNull(productFromTestClass);
    }

    @Test
    public void testIsProductOnStockByName() {
        //is
        List<Product> products = createProductsForTests();

        //then
        ProductServiceImpl productService = new ProductServiceImpl(products);
        boolean isOnStock = productService.isProductOnStockByName(products.get(1).getProductName());

        //expected
        Assertions.assertTrue(isOnStock);
    }

    @Test
    public void testIsProductOnStockByNameNegative1() {
        //is
        List<Product> products = createProductsForTests();

        //then
        ProductServiceImpl productService = new ProductServiceImpl(products);
        boolean isOnStock = productService.isProductOnStockByName(products.get(2).getProductName()); // Coat - not on stock

        //expected
        Assertions.assertFalse(isOnStock);
    }

    @Test
    public void testIsProductOnStockByNameNegative2() {
        //is
        List<Product> products = createProductsForTests();

        //then
        ProductServiceImpl productService = new ProductServiceImpl(products);
        boolean isOnStock = productService.isProductOnStockByName("Not existing product");

        //expected
        Assertions.assertFalse(isOnStock);
    }

    @Test
    public void testDoesProductExistByName() {
        //is
        List<Product> products = createProductsForTests();

        //then
        ProductServiceImpl productService = new ProductServiceImpl(products);
        boolean doesProductExist = productService.doesProductExistByName(products.get(2).getProductName());

        //expected
        Assertions.assertTrue(doesProductExist);
    }

    @Test
    public void testDoesProductExistByNameNegative() {
        //is
        List<Product> products = createProductsForTests();

        //then
        ProductServiceImpl productService = new ProductServiceImpl(products);
        boolean doesProductExist = productService.doesProductExistByName("Not existing product");

        //expected
        Assertions.assertFalse(doesProductExist);
    }

    @Test
    public void testDoesProductExistById() {
        //is
        List<Product> products = createProductsForTests();

        //then
        ProductServiceImpl productService = new ProductServiceImpl(products);
        boolean doesProductExist = productService.doesProductExistById(products.get(2).getId());

        //expected
        Assertions.assertTrue(doesProductExist);
    }

    @Test
    public void testDoesProductExistByIdNegative() {
        //is
        List<Product> products = createProductsForTests();

        //then
        ProductServiceImpl productService = new ProductServiceImpl(products);
        boolean doesProductExist = productService.doesProductExistById(220L);

        //expected
        Assertions.assertFalse(doesProductExist);
    }

    private List<Product> createProductsForTests () {
        List<Product> products = new ArrayList<Product>();
        products.add(new Product(100L, "Marvel", 200.75, 10.0, "Brown", 100.0));
        products.add(new Product(200L, "Shirt", 300.0, 0.4, "White", 5.0));
        products.add(new Product(210L, "Coat", 300.0, 0.4, "White", 0.0));
        products.add(new Product(300L, "Sneakers", 750.0, 0.8, "Brown", 15.0));

        return products;
    }
}
