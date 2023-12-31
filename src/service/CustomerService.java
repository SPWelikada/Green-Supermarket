package service;

import entity.Customer;

import java.sql.SQLException;

/**
 * @author Dulanjana Lakshan Kumarasingha
 */
public interface CustomerService {
    void registerCustomer(Customer customer);
    void deleteCustomer(int customerId);
    void updateCustomer(Customer customer);
    Customer getCustomerById(int customerId);
    Customer getCustomerByUsername(String username);
    Customer signInCustomer(String username, String password) throws SQLException;
}
