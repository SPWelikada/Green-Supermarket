package controller;

import com.google.gson.Gson;
import entity.CartItem;
import serviceImpl.CartItemServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/cartitem")
public class CartItemServlet extends HttpServlet {
    private final CartItemServiceImpl cartItemService = new CartItemServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("add".equals(action)) {
            addCartItem(request, response);
        } else if ("update".equals(action)) {
            updateCartItem(request, response);
        } else if ("delete".equals(action)) {
            deleteCartItem(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("view".equals(action)) {
            viewCartItem(request, response);
        } else if ("list".equals(action)) {
            listCartItem(request, response);
        } else {
            request.getRequestDispatcher("/cartItemForm.jsp").forward(request, response);
        }
    }

    private void addCartItem(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int cartId = Integer.parseInt(request.getParameter("cartId"));
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        BigDecimal price = new BigDecimal(request.getParameter("price"));


        CartItem cartItem = new CartItem();
        cartItem.setCart_id(cartId);
        cartItem.setProduct_id(productId);
        cartItem.setQuantity(quantity);
        cartItem.setPrice(price);

        cartItemService.addCartItem(cartItem);

        response.sendRedirect(request.getContextPath() + "/cartitem?action=list");
    }

    private void updateCartItem(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        int cartId = Integer.parseInt(request.getParameter("cartId"));
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        BigDecimal price = new BigDecimal(request.getParameter("price"));

        CartItem cartItem = new CartItem();
        cartItem.setId(id);
        cartItem.setCart_id(cartId);
        cartItem.setProduct_id(productId);
        cartItem.setQuantity(quantity);
        cartItem.setPrice(price);

        cartItemService.updateCartItem(cartItem);

        response.sendRedirect(request.getContextPath() + "/cartitem?action=list");
    }

    private void deleteCartItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int cartItemId = Integer.parseInt(request.getParameter("id"));

        cartItemService.deleteCartItem(cartItemId);

        response.sendRedirect(request.getContextPath() + "/cartitem?action=list");
    }

    private void viewCartItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int cartItemId = Integer.parseInt(request.getParameter("id"));

        CartItem cartItem = cartItemService.getCartItemById(cartItemId);
        String jsonCartItem = new Gson().toJson(cartItem);
        response.setContentType("application/json");
        response.getWriter().write(jsonCartItem);
    }

    private void listCartItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CartItem> cartItemList = cartItemService.getAllCartItem();
        String jsonCartItems = new Gson().toJson(cartItemList);
        response.setContentType("application/json");
        response.getWriter().write(jsonCartItems);
    }
}
