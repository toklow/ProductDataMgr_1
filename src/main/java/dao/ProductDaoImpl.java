package dao;

import entity.Boots;
import entity.Cloth;
import entity.Product;
import enums.ProductType;
import iface.ProductDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static entity.parser.ProductParser.resultSetToProduct;

public class ProductDaoImpl implements ProductDao {

    private final String tableName = "products";
    private ConnectionDB connectionDB;
    private ProductType productType;

    public ProductDaoImpl(ProductType productType) {
        connectionDB = ConnectionDB.getInstance();
        this.productType = productType;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public List<Product> getAllProducts() {
        String query = "select * from " + tableName + " where type = ?";
        PreparedStatement statement;
        List<Product> products = null;
        try {
            statement = connectionDB.getConnection().prepareStatement(query);
            statement.setString(1, this.productType.getValue());
            products = getAllProducts(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    private List<Product> getAllProducts(PreparedStatement statement) {
        List<Product> products = new LinkedList<>();

        try {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                products.add(resultSetToProduct(resultSet));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public Product getProduct(Long productId) {
        String query = "select * from " + tableName + " where type = ? and id = ?";
        PreparedStatement statement;
        List<Product> products = null;
        try {
            statement = connectionDB.getConnection().prepareStatement(query);
            statement.setString(1, this.productType.getValue());
            statement.setLong(2, productId);
            products = getAllProducts(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (products.isEmpty()) {
            return null;
        }
        return products.get(0);
     }

    public Product getProduct(String productName) {
        String query = "select * from " + tableName + " where type = ? and name = ?";
        PreparedStatement statement;
        List<Product> products = null;
        try {
            statement = connectionDB.getConnection().prepareStatement(query);
            statement.setString(1, this.productType.getValue());
            statement.setString(2, productName);
            products = getAllProducts(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (products.isEmpty()) {
            return null;
        }
        return products.get(0);
    }



    // Save on product means add product to the existing products (avoid erasing existing file by PrintWriter)
    public void saveProduct(Product product) {
        PreparedStatement statement;
        String query = "insert into " + tableName +
                " (type, name, price, weight, count, color, sizeStr, sizeNum, material) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            statement = connectionDB.getConnection().prepareStatement(query);

            statement.setString(1, product.getProductType().getValue());
            statement.setString(2, product.getProductName());
            statement.setDouble(3, product.getPrice());
            statement.setDouble(4, product.getWeight());
            statement.setDouble(5, product.getProductCount());
            statement.setString(6, product.getColor().name());

            switch (product.getProductType()) {
                case BOOTS_ID:
                    Boots boots = (Boots) product;
                    statement.setString(7, "");
                    statement.setInt(8, boots.getSize());
                    statement.setString(9, boots.getSkinType().name());
                    break;
                case CLOTH_ID:
                    Cloth cloth = (Cloth) product;
                    statement.setString(7, cloth.getSize());
                    statement.setInt(8, 0);
                    statement.setString(9, cloth.getMaterial().name());
                    break;
                case PRODUCT_ID:
                default:
                    statement.setString(7, "");
                    statement.setInt(8, 0);
                    statement.setString(9, "");
                    break;
            }
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Save products means erase existing file contents
    public void saveProducts(List<Product> products) {
            for (Product product : products) {
                saveProduct(product);
            }
    }

    public void removeProduct(Long productId) {
        String query = "delete from " + tableName + " where id = ?";
        PreparedStatement statement;

        try {
            statement = connectionDB.getConnection().prepareStatement(query);
            statement.setLong(1, productId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }      List<Product> products = getAllProducts();
     }

    public void removeProduct(String productName) {
        String query = "delete from " + tableName + " where name = ?";
        PreparedStatement statement;

        try {
            statement = connectionDB.getConnection().prepareStatement(query);
            statement.setString(1, productName);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
     }

}
