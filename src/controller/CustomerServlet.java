// CustomerServlet.java
package controller;

import entity.Customer;
import service.CustomerService;
import serviceImpl.CustomerServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {
    private final CustomerService customerService = new CustomerServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("register".equals(action)) {
            registerCustomer(request, response);
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else if ("delete".equals(action)) {
            deleteCustomer(request, response);
        } else if ("update".equals(action)) {
            updateCustomer(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("view".equals(action)) {
            viewCustomer(request, response);
        } else if ("edit".equals(action)) {
            editCustomer(request, response);
        } else {
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }

    private void registerCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String contact = request.getParameter("contact");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Customer customer = new Customer();
        customer.setContact(contact);
        customer.setUserName(username);
        customer.setEmail(email);
        customer.setPassword(password);

        customerService.registerCustomer(customer);

        response.sendRedirect(request.getContextPath() + "/confirmation.jsp?username=" + username);
    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int customerId = Integer.parseInt(request.getParameter("id"));
        customerService.deleteCustomer(customerId);
        response.sendRedirect(request.getContextPath() + "/customerList.jsp");
    }

    private void updateCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String contact = request.getParameter("contact");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Customer customer = new Customer();
        customer.setId(id);
        customer.setContact(contact);
        customer.setUserName(username);
        customer.setEmail(email);
        customer.setPassword(password);

        customerService.updateCustomer(customer);
        response.sendRedirect(request.getContextPath() + "/customerList.jsp");
    }

    private void viewCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("id"));
        Customer customer = customerService.getCustomerById(customerId);
        request.setAttribute("customer", customer);
        request.getRequestDispatcher("/viewCustomer.jsp").forward(request, response);
    }

    private void editCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("id"));
        Customer customer = customerService.getCustomerById(customerId);
        request.setAttribute("customer", customer);
        request.getRequestDispatcher("/editCustomer.jsp").forward(request, response);
    }
}
