package controller;

import com.google.gson.Gson;
import entity.ShoppingCart;
import entity.Enum.Status;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import serviceImpl.ShoppingCartSericeImpl;

import java.io.IOException;
import java.util.List;

@WebServlet("/shoppingcart")
public class ShoppingCartServlet extends HttpServlet {
    private final ShoppingCartSericeImpl shoppingCartService = new ShoppingCartSericeImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            addShoppingCart(request, response);
        } else if ("update".equals(action)) {
            updateShoppingCart(request, response);
        } else if ("delete".equals(action)) {
            deleteShoppingCart(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("view".equals(action)) {
            viewShoppingCart(request, response);
        } else if ("list".equals(action)) {
            listShoppingCart(request, response);
        } else {
            request.getRequestDispatcher("/shoppingCartForm.jsp").forward(request, response);
        }
    }

    private void addShoppingCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        int statusValue = Integer.parseInt(request.getParameter("status"));
        Status status = Status.values()[statusValue];

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCustomer_id(customerId);
        shoppingCart.setStatus(status);

        shoppingCartService.addShoppingCart(shoppingCart);

        response.sendRedirect(request.getContextPath() + "/shoppingcart?action=list");
    }

    private void updateShoppingCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        int statusValue = Integer.parseInt(request.getParameter("status"));
        Status status = Status.values()[statusValue];

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(id);
        shoppingCart.setCustomer_id(customerId);
        shoppingCart.setStatus(status);

        shoppingCartService.updateShoppingCart(shoppingCart);

        response.sendRedirect(request.getContextPath() + "/shoppingcart?action=list");
    }

    private void deleteShoppingCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int shoppingCartId = Integer.parseInt(request.getParameter("id"));

        shoppingCartService.deleteShoppingCart(shoppingCartId);

        response.sendRedirect(request.getContextPath() + "/shoppingcart?action=list");
    }

    private void viewShoppingCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int shoppingCartId = Integer.parseInt(request.getParameter("id"));

        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartById(shoppingCartId);
        String jsonShoppingCart = new Gson().toJson(shoppingCart);
        response.setContentType("application/json");
        response.getWriter().write(jsonShoppingCart);
    }

    private void listShoppingCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ShoppingCart> shoppingCartList = shoppingCartService.getAllShoppingCart();
        String jsonShoppingCarts = new Gson().toJson(shoppingCartList);
        response.setContentType("application/json");
        response.getWriter().write(jsonShoppingCarts);
    }
}