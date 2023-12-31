package controller;
import entity.FeedBack;
import repo.CustomerRepository;
import repo.FeedBackRepository;
import repoImpl.CustomerRepositoryImpl;
import repoImpl.FeedBackRepositoryImpl;
import service.FeedBackService;
import service.CustomerService;
import serviceImpl.FeedBackServiceImpl;
import serviceImpl.CustomerServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
/**
 * @author Dulanjana Lakshan Kumarasingha
 */
@WebServlet("/feedback")
public class FeedBackServlet extends HttpServlet {
    FeedBackRepository feedBackRepository = new FeedBackRepositoryImpl(new CustomerRepositoryImpl());
    CustomerRepository customerRepository = new CustomerRepositoryImpl();
    FeedBackService feedBackService = new FeedBackServiceImpl(feedBackRepository, customerRepository);
    CustomerService customerService = new CustomerServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            addFeedBack(request, response);
        } else if ("update".equals(action)) {
            updateFeedBack(request, response);
        } else if ("delete".equals(action)) {
            deleteFeedBack(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("list".equals(action)) {
            listFeedbacks(request, response);
        } else if ("customerFeedbacks".equals(action)) {
            customerFeedbacks(request, response);
        }
    }

    private void addFeedBack(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        String message = request.getParameter("message");
        int rating = Integer.parseInt(request.getParameter("rating"));

        FeedBack feedBack = new FeedBack();
        feedBack.setCustomerId(customerId);
        feedBack.setMessage(message);
        feedBack.setRating(rating);

        try {
            feedBackService.addFeedBack(feedBack);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/confirmation.jsp");
    }

    private void updateFeedBack(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int feedbackId = Integer.parseInt(request.getParameter("feedbackId"));
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        String message = request.getParameter("message");
        int rating = Integer.parseInt(request.getParameter("rating"));

        FeedBack feedBack = new FeedBack();
        feedBack.setId(feedbackId);
        feedBack.setCustomerId(customerId);
        feedBack.setMessage(message);
        feedBack.setRating(rating);

        try {
            feedBackService.updateFeedBack(feedBack);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/confirmation.jsp");
    }

    private void deleteFeedBack(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int feedbackId = Integer.parseInt(request.getParameter("feedbackId"));

        try {
            feedBackService.deleteFeedBack(feedbackId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/confirmation.jsp");
    }

    private void listFeedbacks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<FeedBack> feedbacks = feedBackService.getAllFeedbacks();
            request.setAttribute("feedbacks", feedbacks);
            request.getRequestDispatcher("/feedbackList.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void customerFeedbacks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("customerId"));

        try {
            List<FeedBack> feedbacks = feedBackService.getFeedbacksByCustomerId(customerId);
            request.setAttribute("feedbacks", feedbacks);
            request.getRequestDispatcher("/customerFeedbacks.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
