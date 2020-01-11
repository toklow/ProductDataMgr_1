package dao;

import entity.Product;
import enums.Separators;
import iface.ProductDao;
import utils.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static entity.parser.ProductParser.stringToProduct;

public class ProductDaoImpl implements ProductDao {

    private String fileName;


    public ProductDaoImpl(Separators productType) {
        setProductFileName(productType);
    }

    public void setProductFileName(Separators productType) {
        switch (productType) {
            case BOOTS_ID:
                this.fileName = "boots.txt";
                break;
            case CLOTH_ID:
                this.fileName = "clothes.txt";
                break;
            case PRODUCT_ID:
            default:
                this.fileName = "products.txt";
                break;
        }

        try {
            FileUtils.createNewFile(fileName);
        } catch (IOException e) {
            System.out.println("Error with file path");
            System.exit(-1);   // exit closes application
        }
    }

    public List<Product> getAllProducts() {

        try {
            FileReader fileReader = new FileReader(this.fileName);
            BufferedReader reader = new BufferedReader(fileReader);
            List<Product> products = new ArrayList<>();
            String singleLineFromFile;

            while ((singleLineFromFile = reader.readLine()) != null) {
                products.add(stringToProduct(singleLineFromFile));
            }
            return products;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Product getProductById(Long productId) {
        List<Product> products = getAllProducts();
        for (Product product : products) {
            if (product.getId().compareTo(productId) == 0) {
                return product;
            }
        }
        return null;
    }

    public Product getProductByProductName(String productName) {
        List<Product> products = getAllProducts();
        for (Product product : products) {
            if (product.getProductName().compareTo(productName) == 0) {
                return product;
            }
        }
        return null;
    }

    // Save on product means add product to the existing products (avoid erasing existing file by PrintWriter)
    public void saveProduct(Product product) {
        List<Product> products = getAllProducts();
        products.add(product);
        saveProducts(products);
    }

    //Save products means erase existing file contents
    public void saveProducts(List<Product> products) {

        try {
            FileUtils.clearFile(fileName);
            PrintWriter printWriter = new PrintWriter(new FileOutputStream(this.fileName, true));
            for (Product product : products) {
                printWriter.write(product.toString() + "\n");
            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void removeProductById(Long productId) {
        List<Product> products = getAllProducts();
        for (Product product : products) {
            if (product.getId().compareTo(productId) == 0) {
                products.remove(product);
                saveProducts(products);
                break;
            }
        }
     }

    public void removeProductByName(String productName) {
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
