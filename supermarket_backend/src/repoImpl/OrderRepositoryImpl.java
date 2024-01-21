package repoImpl;


import entity.Enum.OrderStatus;
import entity.Order;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class OrderRepositoryImpl  {
    private static final String INSERT_ORDER_QUERY = "INSERT INTO `order` (id,customer_id,order_date,status) VALUES (?, ?, ?,?)";
    private static final String DELETE_ORDER_QUERY = "DELETE FROM `order` WHERE id = ?";
    private static final String UPDATE_ORDER_QUERY = "UPDATE `order` SET id = ?, customer_id = ?,order_date=?, status = ? WHERE id = ?";

    private static final String SEARCH_ORDER_BY_ID_QUERY = "SELECT * FROM `order` WHERE id = ?";
    private static final String GET_ALL_ORDERS = "SELECT * FROM `order`";

    public Order createOrder(Order order) throws Exception {

        try (Connection connection = util.DBConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER_QUERY)) {
            String orderId = UUID.randomUUID().toString();
            preparedStatement.setString(1,orderId);
            preparedStatement.setInt(2, order.getCustomerId());
            preparedStatement.setDate(3, (Date) order.getOrderDate());
            preparedStatement.setString(4, order.getStatus().name());
            preparedStatement.executeUpdate();
            order.setId(orderId);
            return order;

        } catch (SQLException e) {
           throw new Exception(e.getMessage());
        }

    }


    public void DeleteOrder(String id) {
        try (Connection connection = util.DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER_QUERY)) {
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public void UpdateOrder(Order order) throws Exception {
        try (Connection connection =util.DBConnection.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER_QUERY)){
            preparedStatement.setString(1,order.getId());
            preparedStatement.setInt(2,order.getCustomerId());
            preparedStatement.setDate(3, (Date) order.getOrderDate());
            preparedStatement.setString(4,order.getStatus().toString());
            preparedStatement.setString(5, order.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new Exception(throwables.getMessage());
        }
    }


    public Order SerchOrderById(String id) {
        try (Connection connection = util.DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_ORDER_BY_ID_QUERY)) {
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return extractHelpDeskFromResultSet(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    public List<Order> getAllOrder() throws Exception {
        List<Order> orders = new ArrayList<>();

        try (Connection connection = util.DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ORDERS);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {

                Order order = new Order();
                order.setId(resultSet.getString("id"));
                order.setCustomerId(resultSet.getInt("customer_id"));
                order.setOrderDate(resultSet.getDate("order_date"));
                String string = resultSet.getString("status");
                OrderStatus status = null;
                if(string.equals("pending")){
                     status = OrderStatus.PENDING;
                } else if (string.equals("delivered")) {
                     status = OrderStatus.DELIVERED;
                }else if(string.equals("cancel")){
                    status = OrderStatus.CANCEL;
                }
                order.setStatus(status);
                orders.add(order);
            }
            return orders;
        } catch (SQLException throwables) {
           throw new Exception(throwables.getMessage());
        }
    }

    private Order extractHelpDeskFromResultSet(ResultSet resultSet) throws SQLException {
        return new Order(
                resultSet.getString("id"),
                resultSet.getInt("customer_id"),
                resultSet.getTimestamp("order_date"),
                resultSet.getObject("status")
        );
    }
}
