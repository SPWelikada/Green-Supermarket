package repoImpl;


import entity.Product;

import util.Constant;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductRepositoryImpl  {

    private static final String ADD_PRODUCT_QUERY = "INSERT INTO supermarket.product (name, list_price, unit_price, qty, image_path) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_PRODUCT_QUERY = "UPDATE supermarket.product SET name = ?, list_price = ?, unit_price = ?, qty = ?, image_path = ? WHERE id = ?";
    private static final String DELETE_PRODUCT_QUERY = "DELETE FROM supermarket.product WHERE id = ?";
    private static final String GET_PRODUCT_BY_ID_QUERY = "SELECT * FROM supermarket.product WHERE id = ?";
    private static final String GET_PRODUCT_BY_NAME_QUERY = "SELECT * FROM supermarket.product WHERE name = ?";
    private static final String GET_ALL_PRODUCTS_QUERY = "SELECT * FROM supermarket.product";


    public void addProduct(Product product) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_PRODUCT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setBigDecimal(2, product.getListPrice());
            preparedStatement.setBigDecimal(3, product.getUnitPrice());
            preparedStatement.setInt(4, product.getQty());
            preparedStatement.setString(5, product.getImagePath());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedId = generatedKeys.getInt(1);
                product.setId(generatedId);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void updateProduct(Product product) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_QUERY)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setBigDecimal(2, product.getListPrice());
            preparedStatement.setBigDecimal(3, product.getUnitPrice());
            preparedStatement.setInt(4, product.getQty());
            preparedStatement.setString(5, product.getImagePath());
            preparedStatement.setInt(6, product.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void deleteProduct(int productId) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_QUERY)) {
            preparedStatement.setInt(1, productId);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public Product getProductById(int productId) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT_BY_ID_QUERY)) {
            preparedStatement.setInt(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                Product product = extractProductFromResultSet(resultSet);
                String imagePath = product.getImagePath();
                String fileResourcePath =  Constant.HOST +":"+ Constant.SERVER_PORT +"/"+ Constant.WAR_FILE_NAME + Constant.RESOURCE_PATH +"/"+ imagePath;
                product.setImagePath(fileResourcePath);

                return extractProductFromResultSet(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    public Product getProductByName(String productName) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT_BY_NAME_QUERY)) {
            preparedStatement.setString(1, productName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return extractProductFromResultSet(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    public List<Product> getAllProducts() throws Exception {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(GET_ALL_PRODUCTS_QUERY)) {

            while (resultSet.next()) {
                Product product = extractProductFromResultSet(resultSet);
                String fileResourcePath =  "http://" + Constant.HOST +":"+ Constant.SERVER_PORT +"/"+ Constant.WAR_FILE_NAME + Constant.RESOURCE_PATH +"/"+ product.getImagePath();
                product.setImagePath(fileResourcePath);
                products.add(product);
            }
            return products;

        } catch (SQLException throwables) {
            throw new Exception(throwables.getMessage());
        }

    }

    private Product extractProductFromResultSet(ResultSet resultSet) throws SQLException {
        return new Product(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getBigDecimal("list_price"),
                resultSet.getBigDecimal("unit_price"),
                resultSet.getInt("qty"),
                resultSet.getString("image_path")
        );
    }
}
