package controller;


import entity.FeedBack;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import repoImpl.CustomerRepositoryImpl;
import repoImpl.FeedBackRepositoryImpl;

import serviceImpl.CustomerServiceImpl;
import serviceImpl.FeedBackServiceImpl;
import util.ResponseUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/feedback")
public class FeedBackServlet extends HttpServlet {
    FeedBackRepositoryImpl feedBackRepository = new FeedBackRepositoryImpl(new CustomerRepositoryImpl());
    CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl();
    FeedBackServiceImpl feedBackService = new FeedBackServiceImpl(feedBackRepository, customerRepository);
    CustomerServiceImpl customerService = new CustomerServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            addFeedBack(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        listFeedbacks(request, response);
    }


    private void addFeedBack(HttpServletRequest request, HttpServletResponse response) throws Exception {
            String customerIdParam = request.getParameter("customerId");
        String ratingParam = request.getParameter("rating");

        if (customerIdParam == null || ratingParam == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameters");
            return;
        }

        int customerId;
        try {
            customerId = Integer.parseInt(customerIdParam);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid customerId parameter");
            return;
        }

        String message = request.getParameter("message");

        int rating;
        try {
            rating = Integer.parseInt(ratingParam);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid rating parameter");
            return;
        }

        FeedBack feedBack = new FeedBack();
        feedBack.setCustomerId(customerId);
        feedBack.setMessage(message);
        feedBack.setRating(rating);

        try {
            feedBackService.addFeedBack(feedBack);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        ResponseUtil.sendJsonResponse(response, HttpServletResponse.SC_OK,"feed back send success",feedBack);
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

        response.sendRedirect(request.getContextPath() + "/manageFeedbacks.jsp");
    }

    private void deleteFeedBack(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int feedbackId = Integer.parseInt(request.getParameter("feedbackId"));

        try {
            feedBackService.deleteFeedBack(feedbackId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/manageFeedbacks.jsp");
    }

    private void listFeedbacks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<FeedBack> feedbacks = feedBackService.getAllFeedbacks();
            ResponseUtil.sendJsonResponse(response, HttpServletResponse.SC_OK,"feed back send success",feedbacks);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void customerFeedbacks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("customerId"));

        try {
            List<FeedBack> feedbacks = feedBackService.getFeedbacksByCustomerId(customerId);
            request.setAttribute("feedbacks", feedbacks);
            request.getRequestDispatcher("/manageFeedbacks.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
