package serviceImpl;

import dto.LoginDTO;
import entity.Customer;

import repoImpl.CustomerRepositoryImpl;

import util.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;


public class CustomerServiceImpl {
    private final CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl();


    public void registerCustomer(Customer customer) {
        try (Connection connection = DBConnection.getConnection()) {
            customerRepository.addCustomer(customer);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void deleteCustomer(int customerId) {
        try (Connection connection = DBConnection.getConnection()) {
            customerRepository.deleteCustomer(customerId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void updateCustomer(Customer customer) {
        try (Connection connection = DBConnection.getConnection()) {
            customerRepository.updateCustomer(customer);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public Customer getCustomerById(int customerId) {
        try (Connection connection = DBConnection.getConnection()) {
            return customerRepository.searchCustomerById(customerId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    public Customer getCustomerByUsername(String username) {
        try (Connection connection = DBConnection.getConnection()) {
            return customerRepository.searchCustomerByUsername(username);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    public Customer signInCustomer(String username, String password) throws SQLException {
        Customer customer = customerRepository.searchCustomerByUsername(username);

        if (customer != null && customer.getPassword().equals(password)) {
            return customer;
        }

        return null;
    }


    public Customer customerSign(LoginDTO loginDTO) throws Exception {
        return customerRepository.getCustomerByUsernameAndPassword(loginDTO);
    }


}
