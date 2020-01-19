package entity.parser;

import entity.Boots;
import entity.Cloth;
import entity.Product;
import enums.Colors;
import enums.Material;
import enums.ProductType;
import enums.SkinType;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ProductParser {

    public static Product resultSetToProduct(ResultSet resultSet) throws SQLException {
        Product product = null;
        ProductType productType = ProductType.valueToEnum(resultSet.getString("type"));

        switch (productType) {
            case PRODUCT_ID:
                product = convertToProduct(resultSet);
                break;
            case CLOTH_ID:
                product = convertToCloth(resultSet);
                break;
            case BOOTS_ID:
                product = convertToBoots(resultSet);
                break;
            default:
                break;
        }
        return product;

    }


    private static Product convertToProduct(ResultSet resultSet) throws SQLException {

        return (new Product(resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getDouble("price"),
                resultSet.getDouble("weight"),
                Colors.valueOf(resultSet.getString("color")),
                resultSet.getDouble("count")));

    }

    //public Boots(Long id, String productName, double price, double weight, Colors color, double productCount, Integer size, SkinType skinType)
    private static Boots convertToBoots(ResultSet resultSet) throws SQLException {
        return (new Boots(resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getDouble("price"),
                resultSet.getDouble("weight"),
                Colors.valueOf(resultSet.getString("color")),
                resultSet.getDouble("count"),
                resultSet.getInt("sizeNum"),
                SkinType.valueOf(resultSet.getString("material"))));
    }

    private static Cloth convertToCloth(ResultSet resultSet) throws SQLException {
        return (new Cloth(resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getDouble("price"),
                resultSet.getDouble("weight"),
                Colors.valueOf(resultSet.getString("color")),
                resultSet.getDouble("count"),
                resultSet.getString("sizeStr"),
                Material.valueOf(resultSet.getString("material"))));

    }

}