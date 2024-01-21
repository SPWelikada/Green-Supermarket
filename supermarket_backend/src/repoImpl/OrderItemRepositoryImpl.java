package repoImpl;

import entity.Order_Item;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class OrderItemRepositoryImpl  {

    private static final String INSERT_ORDER_ITEM_QUERY =
            "INSERT INTO order_item (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";

    private static final String SELECT_ORDER_ITEM_QUERY =
            "SELECT * FROM order_item WHERE id = ?";

    private static final String UPDATE_ORDER_ITEM_QUERY =
            "UPDATE order_item SET order_id = ?, product_id = ?, quantity = ?, price = ? WHERE id = ?";

    private static final String DELETE_ORDER_ITEM_QUERY =
            "DELETE FROM order_item WHERE id = ?";
    private static final String SELECT_ALL_ORDER_ITEMS_QUERY =
            "SELECT * FROM order_item";


    public void createOrderItem(List<Order_Item> orderItems) throws SQLException {
        try (Connection connection = util.DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER_ITEM_QUERY,
                     Statement.RETURN_GENERATED_KEYS)) {

            for (Order_Item orderItem : orderItems) {
                preparedStatement.setString(1, orderItem.getOrderId());
                preparedStatement.setInt(2, orderItem.getProductId());
                preparedStatement.setInt(3, orderItem.getQuantity());
                preparedStatement.setDouble(4, orderItem.getPrice());

                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            int i = 0;
            while (generatedKeys.next()) {
                orderItems.get(i).setId(generatedKeys.getInt(1));
                i++;
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }


    public void updateOrderItem(Order_Item order_item) throws SQLException {
        try (Connection connection = util.DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER_ITEM_QUERY)) {

            preparedStatement.setString(1, order_item.getOrderId());
            preparedStatement.setInt(2, order_item.getProductId());
            preparedStatement.setInt(3, order_item.getQuantity());
            preparedStatement.setDouble(4, order_item.getPrice());
            preparedStatement.setInt(5, order_item.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }




    public void deleteOrderItem(int orderItemId) throws SQLException {
        try (Connection connection = util.DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER_ITEM_QUERY)) {

            preparedStatement.setInt(1, orderItemId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }


    public List<Order_Item> getAllOrderItems() throws SQLException {
        List<Order_Item> orderItems = new ArrayList<>();

        try (Connection connection = util.DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ORDER_ITEMS_QUERY)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order_Item orderItem = (Order_Item) mapResultSetToOrderItem(resultSet);
                orderItems.add(orderItem);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
            throw e;
        }

        return orderItems;
    }


    public List<OrderItemRepositoryImpl> getOrderItemById(int orderItemId) throws SQLException {
        try (Connection connection = util.DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER_ITEM_QUERY)) {

            preparedStatement.setInt(1, orderItemId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToOrderItem(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        return null;
    }

    private List<OrderItemRepositoryImpl> mapResultSetToOrderItem(ResultSet resultSet) throws SQLException {
        Order_Item orderItem = new Order_Item();
        orderItem.setId(resultSet.getInt("id"));
        orderItem.setOrderId(resultSet.getString("order_id"));
        orderItem.setProductId(resultSet.getInt("product_id"));
        orderItem.setQuantity(resultSet.getInt("quantity"));
        orderItem.setPrice(resultSet.getDouble("price"));
        return (List<OrderItemRepositoryImpl>) orderItem;
    }
}
