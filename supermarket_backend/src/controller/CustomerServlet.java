// CustomerServlet.java
package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import dto.LoginDTO;
import entity.Customer;

import serviceImpl.CustomerServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.ResponseUtil;

import java.io.IOException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@WebServlet(urlPatterns = {"/customer"})
public class CustomerServlet extends HttpServlet {

    private final CustomerServiceImpl customerService = new CustomerServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("register".equals(action)) {
            registerCustomer(request, response);
        } else if ("delete".equals(action)) {
            deleteCustomer(request, response);
        } else if ("update".equals(action)) {
            updateCustomer(request, response);
        }else if(action.equals("login")){
            try {
                login(request, response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (action.equals("by-id")) {

        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("view".equals(action)) {
            viewCustomer(request, response);
        } else if ("edit".equals(action)) {
            editCustomer(request, response);
        }

    }


/*
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = request.getParameter("action");
//
//        if ("view".equals(action)) {
//            viewCustomer(request, response);
//        } else if ("edit".equals(action)) {
//            editCustomer(request, response);
//        } else {
//            request.getRequestDispatcher("/register.jsp").forward(request, response);
//        }

        response.setContentType("application/json");
        String action = request.getParameter("action");
        System.out.println("sddddddddddddd" + action);
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseUtil data = new ResponseUtil(HttpServletResponse.SC_OK,"sdsd", "asasasas");
        String jsonData = objectMapper.writeValueAsString(data);
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(jsonData);
    }
     */


    private void registerCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
        // sendRegistrationSuccessEmail(email);

        ResponseUtil.sendJsonResponse(response,HttpServletResponse.SC_OK,"Registration-success",customer);
    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int customerId = Integer.parseInt(request.getParameter("id"));
        customerService.deleteCustomer(customerId);

       // response.sendRedirect(request.getContextPath() + "/customerList.jsp");

        ResponseUtil.sendJsonResponse(response,HttpServletResponse.SC_OK,"delete-customer-success",customerId);
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
        response.sendRedirect(request.getContextPath() + "/index1.jsp");

        ResponseUtil.sendJsonResponse(response,HttpServletResponse.SC_OK,"update-customer-success",customer);
    }

    private void viewCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("id"));
        Customer customer = customerService.getCustomerById(customerId);

        ObjectMapper objectMapper = new ObjectMapper();
        ResponseUtil data = new ResponseUtil(HttpServletResponse.SC_OK,"get-customers-by-id", customer);
        String jsonData = objectMapper.writeValueAsString(data);
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(jsonData);

    }

    private void editCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("id"));
        Customer customer = customerService.getCustomerById(customerId);
        String jsonProducts = new Gson().toJson(customer);
        response.setContentType("application/json");
        response.getWriter().write(jsonProducts);
    }

    private void sendRegistrationSuccessEmail(String userEmail) {
        final String username = "greensupermarketb70@gmail.com"; // Your Gmail username
        final String password = "vcndfopvgdpbqlgi"; // Your Gmail password

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("greensupermarketb70@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
            message.setSubject("Registration Success");
            message.setText("Dear User,\n\nCongratulations! Your registration was successful.");

            Transport.send(message);
            System.out.println("Registration success email sent to: " + userEmail);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Customer customer =  customerService.customerSign(new LoginDTO(username,password));
        ResponseUtil.sendJsonResponse(response,HttpServletResponse.SC_OK,"get-customer",customer);

    }

}
