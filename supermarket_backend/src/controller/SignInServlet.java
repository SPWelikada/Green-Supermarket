package controller;

import entity.Customer;

import serviceImpl.CustomerServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/signin")
public class SignInServlet extends HttpServlet {
    private final CustomerServiceImpl customerService = new CustomerServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Customer customer = customerService.signInCustomer(username, password);

            if (customer != null) {
                request.getSession().setAttribute("customer", customer);
                response.sendRedirect(request.getContextPath() + "/admin/adminHome.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/404.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index1.jsp").forward(request, response);
    }
}