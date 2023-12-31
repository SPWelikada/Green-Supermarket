package repo;

import entity.Customer;

/**
 * @author Dulanjana Lakshan Kumarasingha
 */
public interface CustomerRepository {
    void addCustomer(Customer customer);
    boolean customerExists(int customerId);
    void deleteCustomer(int customerId);
    void updateCustomer(Customer customer);
    Customer searchCustomerById(int customerId);
    Customer searchCustomerByUsername(String username);
}
