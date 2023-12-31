package serviceImpl;

import entity.Customer;
import repo.CustomerRepository;
import repoImpl.CustomerRepositoryImpl;
import service.CustomerService;
import util.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Dulanjana Lakshan Kumarasingha
 */
public class CustomerServiceImpl  implements CustomerService {
    private final CustomerRepository customerRepository = new CustomerRepositoryImpl();

    @Override
    public void registerCustomer(Customer customer) {
        try (Connection connection = DBConnection.getConnection()) {
            customerRepository.addCustomer(customer);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteCustomer(int customerId) {
        try (Connection connection = DBConnection.getConnection()) {
            customerRepository.deleteCustomer(customerId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void updateCustomer(Customer customer) {
        try (Connection connection = DBConnection.getConnection()) {
            customerRepository.updateCustomer(customer);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Customer getCustomerById(int customerId) {
        try (Connection connection = DBConnection.getConnection()) {
            return customerRepository.searchCustomerById(customerId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Customer getCustomerByUsername(String username) {
        try (Connection connection = DBConnection.getConnection()) {
            return customerRepository.searchCustomerByUsername(username);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Customer signInCustomer(String username, String password) throws SQLException {
        Customer customer = customerRepository.searchCustomerByUsername(username);

        if (customer != null && customer.getPassword().equals(password)) {
            return customer;
        }

        return null;
    }
}
