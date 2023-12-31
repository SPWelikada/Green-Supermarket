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
import java.sql.SQLException;
/**
 * @author Dulanjana Lakshan Kumarasingha
 */
@WebServlet("/signin")
public class SignInServlet extends HttpServlet {
    private final CustomerService customerService = new CustomerServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Customer customer = customerService.signInCustomer(username, password);

            if (customer != null) {
                // Successfully signed in
                request.getSession().setAttribute("customer", customer);
                response.sendRedirect(request.getContextPath() + "/welcome.jsp");
            } else {
                // Sign-in failed
                response.sendRedirect(request.getContextPath() + "/index.jsp?error=true");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}