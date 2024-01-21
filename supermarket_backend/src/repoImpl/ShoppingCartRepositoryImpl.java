package repoImpl;

import entity.ShoppingCart;
import entity.Enum.Status;

import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class ShoppingCartRepositoryImpl {

    private static final String ADD_SHOPPINGCART_QUERY = "INSERT INTO supermarket.shoppingcart (Id, Customer_id, Status,) VALUES (?, ?, ?)";
    private static final String UPDATE_SHOPPINGCART_QUERY = "UPDATE supermarket.shoppingcart SET Customer_id = ?, Status = ? WHERE id = ?";
    private static final String DELETE_SHOPPINGCART_QUERY = "DELETE FROM supermarket.shoppingcart WHERE id = ?";
    private static final String GET_SHOPPINGCART_BY_ID_QUERY = "SELECT * FROM supermarket.shoppingcart WHERE id = ?";
    private static final String GET_ALL_SHOPPINGCART_QUERY = "SELECT * FROM supermarket.shoppingcart";




    public void addShoppingCart(ShoppingCart shoppingCart) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_SHOPPINGCART_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, shoppingCart.getId());
            preparedStatement.setInt(2, shoppingCart.getCustomer_id());
            preparedStatement.setInt(3, shoppingCart.getStatus().ordinal());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedId = generatedKeys.getInt(1);
                shoppingCart.setId(generatedId);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void updateShoppingCart(ShoppingCart shoppingCart) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SHOPPINGCART_QUERY)) {
            preparedStatement.setInt(1, shoppingCart.getId());
            preparedStatement.setInt(2, shoppingCart.getCustomer_id());
            preparedStatement.setInt(3, shoppingCart.getStatus().ordinal());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void deleteShoppingCart(int ShoppingCartId) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SHOPPINGCART_QUERY)) {
            preparedStatement.setInt(1, ShoppingCartId);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public ShoppingCart getShoppingCartById(int ShoppingCartId) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_SHOPPINGCART_BY_ID_QUERY)) {
            preparedStatement.setInt(1, ShoppingCartId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return extractShoppingCartFromResultSet(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }



    public List<ShoppingCart> getAllShoppingCart() {
        List<ShoppingCart> shoppingcart = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(GET_ALL_SHOPPINGCART_QUERY)) {
            while (resultSet.next()) {
                shoppingcart.add(extractShoppingCartFromResultSet(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return shoppingcart;
    }

    private ShoppingCart extractShoppingCartFromResultSet(ResultSet resultSet) throws SQLException {
        return new ShoppingCart(
                resultSet.getInt("id"),
                resultSet.getInt("Customer_id"),
                resultSet.getObject("status", Status.class)
        );
    }
}