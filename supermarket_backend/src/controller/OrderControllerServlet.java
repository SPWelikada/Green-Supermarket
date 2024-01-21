package controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import entity.Customer;
import entity.Enum.OrderStatus;

import entity.Order;
import entity.Order_Item;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import serviceImpl.CustomerServiceImpl;
import serviceImpl.OrderItemServiceImpl;
import serviceImpl.OrderServiceImpl;
import util.Constant;
import util.MailSender;
import util.ResponseUtil;

import java.io.BufferedReader;
import java.io.IOException;

import java.sql.SQLException;

import java.util.List;




@WebServlet("/order")
public class OrderControllerServlet extends HttpServlet {

    private OrderServiceImpl orderService = new OrderServiceImpl();

    OrderItemServiceImpl orderItemService = new OrderItemServiceImpl();
    CustomerServiceImpl customerService = new CustomerServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String action = req.getParameter("action");

        if ("add".equals(action)) {
            try {
                addOrder(req, resp);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if ("deliver".equals(action)) {
            try {
                updateOrder(req, resp);
            } catch (Exception throwables) {

                throw new RuntimeException(throwables.getMessage());
            }
        } else if ("delete".equals(action)) {
            deleteOrder(req, resp);
        }else if("purchase".equals(action)){
             purchaseOrder(req,resp);
        }
    }

    private void purchaseOrder(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter("email");

        try {
            BufferedReader reader = req.getReader();
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String jsonData = sb.toString();

            ObjectMapper objectMapper = new ObjectMapper();
            List<Order_Item> orderItems = objectMapper.readValue(jsonData, new TypeReference<List<Order_Item>>() {});

            orderItemService.createOrderItem(orderItems);
            MailSender.orderPlaceSuccess(email,orderItems);
            ResponseUtil.sendJsonResponse(resp, HttpServletResponse.SC_OK,"place order",orderItems);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("view".equals(action)) {
            try {
                viewOrder(req, resp);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else if ("list".equals(action)) {
            try {
                listOrder(req, resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
           // req.getRequestDispatcher("/order.jsp").forward(req, resp);
            getAll(req,resp);
        }
    }


    private void getAll(HttpServletRequest req, HttpServletResponse res){

    }


    private void listOrder(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<Order> orderList = orderService.getAllOrder();
        ResponseUtil.sendJsonResponse(resp, HttpServletResponse.SC_OK,"update order",orderList);
    }

    private void viewOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        // Retrieve the HelpDesk ID from the request
        String id = req.getParameter("id");

        // Retrieve the HelpDesk from the database
        Object order = orderService.SerchOrderById(id);
        String jsonProducts = new Gson().toJson(order);
        resp.setContentType("application/json");
        resp.getWriter().write(jsonProducts);
    }

    private void deleteOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Retrieve the HelpDesk ID from the request
        String id = req.getParameter("id");

        // Delete HelpDesk from the database
        orderService.DeleteOrder(id);

        // Redirect to the list page
        resp.sendRedirect(req.getContextPath() + "/helpdesk?action=list");
    }

    private void updateOrder(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // Retrieve parameters from the request
        String id = req.getParameter("orderId");
        int customer_id = Integer.parseInt(req.getParameter("customer_id"));
        Object order_date = req.getParameter("order_date");
        String status = req.getParameter("status");

        Order order = new Order();
        order.setId(id);
        order.setCustomerId(customer_id);
        order.setOrderDate(Constant.convertParamToDate(order_date));
        if (status.equals("pending") || status.equals("PENDING")){
            order.setStatus(OrderStatus.PENDING);
        }else if(status.equals("delivered") || status.equals("DELIVERED")){
            order.setStatus(OrderStatus.DELIVERED);
        }else if(status.equals("cancel") || status.equals("CANCEL")){
            order.setStatus(OrderStatus.CANCEL);
        }

        orderService.UpdateOrder(order);
        if(status.equals("delivered") || status.equals("DELIVERED")){
            Customer customerById = customerService.getCustomerById(order.getCustomerId());
            System.out.println(customerById.getEmail());
            try {
                MailSender.orderDeliverSuccess(customerById.getEmail(),order);
                ResponseUtil.sendJsonResponse(resp, HttpServletResponse.SC_OK,"update order",order);
            }catch (Exception r){
                throw new Exception(r.getMessage());
            }


        }else if(status.equals("cancel") || status.equals("CANCEL")){
            Customer customerById = customerService.getCustomerById(order.getCustomerId());
            try {
                MailSender.orderCancelMail(customerById.getEmail(),order);
                ResponseUtil.sendJsonResponse(resp, HttpServletResponse.SC_OK,"cancel order",order);
            }catch (Exception r){
                throw new Exception(r.getMessage());
            }
        }

    }

    private void addOrder(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // Retrieve parameters from the request
        String id = req.getParameter("id");
        int customer_id = Integer.parseInt(req.getParameter("customer_id"));
        Object order_date = req.getParameter("order_date");
        Object status = req.getParameter("status");

        // Create a order object
        Order order = new Order();
        order.setId(id);
        order.setCustomerId(customer_id);
        order.setOrderDate(Constant.convertParamToDate(order_date));
        order.setStatus(OrderStatus.PENDING);

        // Add order to the database
        orderService.createOrder(order);
        ResponseUtil.sendJsonResponse(resp, HttpServletResponse.SC_OK,"get-customer",order);
        // Redirect to the list page
      //  resp.sendRedirect(req.getContextPath() + "/helpdesk?action=list");
    }
}
