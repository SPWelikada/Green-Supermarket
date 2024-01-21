package repoImpl;

import entity.CartItem;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CartItemRepositoryImpl {

    private static final String INSERT_CARTITEM_QUERY = "INSERT INTO supermarket.cartitem (Id, Cart_id, Product_id, Quantity, Price) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_CARTITEM_QUERY = "UPDATE supermarket.cartitem SET Cart_id = ?, Product_id = ?, Quantity = ?, Price = ? WHERE id = ?";
    private static final String DELETE_CARTITEM_QUERY = "DELETE FROM supermarket.cartitem WHERE id = ?";
    private static final String SELECT_CARTITEM_BY_ID_QUERY = "SELECT * FROM supermarket.cartitem WHERE id = ?";
    private static final String SELECT_ALL_CARTITEM_QUERY = "SELECT * FROM supermarket.cartitem";

    public void addCartItem(CartItem cartItem) {
        try (Connection connection = util.DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CARTITEM_QUERY)) {
            preparedStatement.setInt(1, cartItem.getId());
            preparedStatement.setInt(2, cartItem.getCart_id());
            preparedStatement.setInt(3, cartItem.getProduct_id());
            preparedStatement.setInt(4, cartItem.getQuantity());
            preparedStatement.setBigDecimal(5, cartItem.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void updateCartItem(CartItem cartItem) {
        try (Connection connection = util.DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CARTITEM_QUERY)) {
            preparedStatement.setInt(1, cartItem.getId());
            preparedStatement.setInt(2, cartItem.getCart_id());
            preparedStatement.setInt(3, cartItem.getProduct_id());
            preparedStatement.setInt(4, cartItem.getQuantity());
            preparedStatement.setBigDecimal(5, cartItem.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void deleteCartItem(int cartItem) {
        try (Connection connection = util.DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CARTITEM_QUERY)) {
            preparedStatement.setInt(1, cartItem);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public CartItem getCartItemById(int cartId) {
        try (Connection connection = util.DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CARTITEM_BY_ID_QUERY)) {
            preparedStatement.setInt(1, cartId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return extractCartItemFromResultSet(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    public List<CartItem> getAllCartItem() {
        List<CartItem> cartitem = new ArrayList<>();
        try (Connection connection = util.DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CARTITEM_QUERY)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cartitem.add(extractCartItemFromResultSet(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cartitem;
    }

    private CartItem extractCartItemFromResultSet(ResultSet resultSet) throws SQLException {
        return new CartItem(
                resultSet.getInt("id"),
                resultSet.getInt("cart_id"),
                resultSet.getInt("product_id"),
                resultSet.getInt("quantity"),
                resultSet.getBigDecimal("price")
        );
    }

}
