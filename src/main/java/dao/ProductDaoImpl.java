package dao;

import entity.Product;
import iface.ProductDao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductDaoImpl implements ProductDao {

    private String productFileName;

    // Constructor with empty list
    public ProductDaoImpl(String productFileName) {
        this.productFileName = productFileName;
    }

    // Constructor with non-empty list
    public ProductDaoImpl(String productFileName, List<Product> products) {
        this.productFileName = productFileName;

        try {
            this.saveProducts(products);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Product> getAllProducts() throws IOException {
        FileReader fileReader = new FileReader(this.productFileName);
        BufferedReader reader = new BufferedReader(fileReader);
        List<Product> products = new ArrayList<Product>();
        String readOneLineFromFile;

        while ((readOneLineFromFile = reader.readLine()) != null) {
            products.add(parseStringToProduct(readOneLineFromFile));
        }
        return products;
    }

    public Product getProductById(Long productId) throws IOException {

        List<Product> products = getAllProducts();
        for (Product product : products) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    public Product getProductByProductName(String productName) {
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
        PrintWriter printWriter = new PrintWriter(this.productFileName);

        for (Product product : products) {
            printWriter.println(this.toString(product));
        }
        printWriter.close();
    }

    public void removeProductById(Long productId) {
        ;
    }

    public void removeProductByName(String productName) {
        ;
    }

    private String toString(Product product) {
        return String.format(Locale.ENGLISH, "%d@%s@%8.2f@%8.2f@%s@%8.2f", product.getId(), product.getProductName(), product.getPrice(), product.getWeight(), product.getColor(), product.getProductCount());
    }

    private Product parseStringToProduct(String stringToParse) {
        String [] parse = new String [6];
        int beginIndex = 0;
        int endIndex = 0;

        for (int i = 0; i < 6; i++)
        {
            if ((endIndex = stringToParse.indexOf('@', beginIndex)) == -1) {
                parse[i] = stringToParse.substring(beginIndex);
            }
            else {
                parse[i] = stringToParse.substring(beginIndex, endIndex);
                beginIndex = endIndex + 1;
            }
        }

        return new Product(Long.parseLong(parse[0]), parse[1], Float.parseFloat(parse[2]), Float.parseFloat(parse[3]), parse[4], Float.parseFloat(parse[5]) );
    }



}
