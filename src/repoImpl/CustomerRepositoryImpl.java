package repoImpl;

import entity.Customer;
import repo.CustomerRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Dulanjana Lakshan Kumarasingha
 */
public class CustomerRepositoryImpl implements CustomerRepository {
    private static final String INSERT_USER_QUERY = "INSERT INTO customer (id,contact, email, username, password) VALUES (?, ?, ?, ?, ?)";
    private static final String CHECK_CUSTOMER_QUERY = "SELECT COUNT(*) FROM customer WHERE id = ?";
    private static final String DELETE_CUSTOMER_QUERY = "DELETE FROM customer WHERE id = ?";
    private static final String UPDATE_CUSTOMER_QUERY = "UPDATE customer SET contact = ?, email = ?, username = ?, password = ? WHERE id = ?";
    private static final String SEARCH_CUSTOMER_BY_ID_QUERY = "SELECT * FROM customer WHERE id = ?";
    private static final String SEARCH_CUSTOMER_BY_USERNAME_QUERY = "SELECT * FROM customer WHERE username = ?";

    @Override
    public void addCustomer(Customer customer) {
        try (Connection connection = util.DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_QUERY)) {
            preparedStatement.setInt(1, customer.getId());
            preparedStatement.setString(2, customer.getContact());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setString(4, customer.getUserName());
            preparedStatement.setString(5, customer.getUserName());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean customerExists(int customerId) {
        try (Connection connection = util.DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_CUSTOMER_QUERY)) {
            preparedStatement.setInt(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }


    @Override
    public void deleteCustomer(int customerId) {
        try (Connection connection = util.DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CUSTOMER_QUERY)) {
            preparedStatement.setInt(1, customerId);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void updateCustomer(Customer customer) {
        try (Connection connection = util.DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMER_QUERY)) {
            preparedStatement.setString(1, customer.getContact());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setString(3, customer.getUserName());
            preparedStatement.setString(4, customer.getPassword());
            preparedStatement.setInt(5, customer.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Customer searchCustomerById(int customerId) {
        try (Connection connection = util.DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_CUSTOMER_BY_ID_QUERY)) {
            preparedStatement.setInt(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return extractCustomerFromResultSet(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Customer searchCustomerByUsername(String username) {
        try (Connection connection = util.DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_CUSTOMER_BY_USERNAME_QUERY)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return extractCustomerFromResultSet(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private Customer extractCustomerFromResultSet(ResultSet resultSet) throws SQLException {
        return new Customer(
                resultSet.getInt("id"),
                resultSet.getString("contact"),
                resultSet.getString("email"),
                resultSet.getString("username"),
                resultSet.getString("password")
        );
    }

}
