package controller;

import entity.Order_Item;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import serviceImpl.OrderItemServiceImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class OrderItemsServlet extends HttpServlet {
    private OrderItemServiceImpl orderItemService = new OrderItemServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Order_Item> orderItems = orderItemService.getAllOrderItems();

            for (Order_Item orderItem : orderItems) {
                System.out.println("Order Item ID: " + orderItem.getId());
                System.out.println("Order ID: " + orderItem.getOrderId());
                System.out.println("Product ID: " + orderItem.getProductId());
                System.out.println("Quantity: " + orderItem.getQuantity());
                System.out.println("Price: " + orderItem.getPrice());
                System.out.println("---------------------------");
            }

            resp.getWriter().println("Order Items retrieved successfully!");

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
            resp.getWriter().println("Error retrieving Order Items!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            // Assuming the request contains parameters for creating a new order item
            String orderId =req.getParameter("orderId");
            int productId = Integer.parseInt(req.getParameter("productId"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            double price = Double.parseDouble(req.getParameter("price"));

            Order_Item newOrderItem = new Order_Item();
            newOrderItem.setOrderId(orderId);
            newOrderItem.setProductId(productId);
            newOrderItem.setQuantity(quantity);
            newOrderItem.setPrice(price);

           // orderItemService.createOrderItem(newOrderItem);

            resp.getWriter().println("Order Item created successfully!");




    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Assuming the request contains parameters for updating an existing order item
            int orderItemId = Integer.parseInt(req.getParameter("orderItemId"));
            String orderId = req.getParameter("orderId");
            int productId = Integer.parseInt(req.getParameter("productId"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            double price = Double.parseDouble(req.getParameter("price"));

            Order_Item updatedOrderItem = new Order_Item();
            updatedOrderItem.setId(orderItemId);
            updatedOrderItem.setOrderId(orderId);
            updatedOrderItem.setProductId(productId);
            updatedOrderItem.setQuantity(quantity);
            updatedOrderItem.setPrice(price);

            orderItemService.updateOrderItem(updatedOrderItem);

            resp.getWriter().println("Order Item updated successfully!");

        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
            resp.getWriter().println("Error updating Order Item!");
        }


    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Assuming the request contains the orderItemId parameter for deleting an order item
            int orderItemId = Integer.parseInt(req.getParameter("orderItemId"));

            orderItemService.deleteOrderItem(orderItemId);

            resp.getWriter().println("Order Item deleted successfully!");

        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
            resp.getWriter().println("Error deleting Order Item!");
        }
    }

}
