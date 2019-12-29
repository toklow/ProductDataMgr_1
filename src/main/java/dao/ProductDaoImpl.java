package dao;

import entity.Product;
import iface.ProductDao;
import utils.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static entity.parser.ProductParser.stringToProduct;

public class ProductDaoImpl implements ProductDao {

    private String fileName;
    private String productType;

    // Constructor with empty list
    public ProductDaoImpl(String fileName) throws IOException {
        this.fileName = fileName;
        this.productType = "PRODUCT";
        FileUtils.createNewFile(fileName);
    }

    // Constructor with empty list
    public ProductDaoImpl(String fileName, String productType) throws IOException {
        this.fileName = fileName;
        this.productType = productType;
        FileUtils.createNewFile(fileName);
    }
    // Constructor with non-empty list
    public ProductDaoImpl(String fileName, String productType, List<Product> products) throws IOException {
        this.fileName = fileName;
        this.productType = productType;
        FileUtils.createNewFile(fileName);

        this.saveProducts(products);
     }

    public List<Product> getAllProducts() throws IOException {
        FileReader fileReader = new FileReader(this.fileName);
        BufferedReader reader = new BufferedReader(fileReader);
        List<Product> products = new ArrayList<Product>();
        String singleLineFromFile;

        while ((singleLineFromFile = reader.readLine()) != null) {
            products.add(stringToProduct(singleLineFromFile, this.productType));
        }
        return products;
    }

    public Product getProductById(Long productId) throws IOException {
        List<Product> products = getAllProducts();
        for (Product product : products) {
            if (product.getId().compareTo(productId) == 0) {
                return product;
            }
        }
        return null;
    }

    public Product getProductByProductName(String productName) throws IOException {
        List<Product> products = getAllProducts();
        for (Product product : products) {
            if (product.getProductName().compareTo(productName) == 0) {
                return product;
            }
        }
        return null;
    }

    // Save on product means add product to the existing products (avoid erasing existing file by PrintWriter)
    public void saveProduct(Product product) throws IOException {
        List<Product> products = getAllProducts();
        products.add(product);
        saveProducts(products);
    }

    //Save products means erase existing file contents
    public void saveProducts(List<Product> products) throws FileNotFoundException {
        FileUtils.clearFile(fileName);

        PrintWriter printWriter = new PrintWriter(new FileOutputStream(this.fileName, true));
        for (Product product : products) {
            printWriter.write(product.toString() + "\n");
        }
        printWriter.close();
    }

    public void removeProductById(Long productId) throws IOException {
        List<Product> products = getAllProducts();
        for (Product product : products) {
            if (product.getId().compareTo(productId) == 0) {
                products.remove(product);
                saveProducts(products);
                break;
            }
        }
     }

    public void removeProductByName(String productName) throws IOException {
        List<Product> products = getAllProducts();
        for (Product product : products) {
            if (product.getProductName().compareTo(productName) == 0) {
                products.remove(product);
                saveProducts(products);
                break;
            }
        }
    }

}
