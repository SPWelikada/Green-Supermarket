package controller;
import com.google.gson.Gson;
import entity.HelpDesk;

import serviceImpl.HelpDeskServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/helpdesk")
public class HelpDeskServlet  extends HttpServlet{
    private final HelpDeskServiceImpl helpDeskService = new HelpDeskServiceImpl();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            addHelpDesk(request, response);
        } else if ("update".equals(action)) {
            updateHelpDesk(request, response);
        } else if ("delete".equals(action)) {
            deleteHelpDesk(request, response);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("view".equals(action)) {
            viewHelpDesk(request, response);
        } else if ("list".equals(action)) {
            listHelpDesk(request, response);
        } else {
            request.getRequestDispatcher("/helpDeskForm.jsp").forward(request, response);
        }
    }

    private void addHelpDesk(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Retrieve parameters from the request
        String FAQs = request.getParameter("FAQs");
        String questions = request.getParameter("questions");
        String answers = request.getParameter("answers");
        String guidance = request.getParameter("guidance");

        // Create a HelpDesk object
        HelpDesk helpDesk = new HelpDesk();
        helpDesk.setFAQs(FAQs);
        helpDesk.setQuestions(questions);
        helpDesk.setAnswers(answers);
        helpDesk.setGuidance(guidance);

        // Add HelpDesk to the database
        helpDeskService.addHelpDesk(helpDesk);

        // Redirect to the list page
        response.sendRedirect(request.getContextPath() + "/helpdesk?action=list");
    }

    private void updateHelpDesk(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Retrieve parameters from the request
        int id = Integer.parseInt(request.getParameter("id"));
        String FAQs = request.getParameter("FAQs");
        String questions = request.getParameter("questions");
        String answers = request.getParameter("answers");
        String guidance = request.getParameter("guidance");

        // Create a HelpDesk object
        HelpDesk helpDesk = new HelpDesk();
        helpDesk.setId(id);
        helpDesk.setFAQs(FAQs);
        helpDesk.setQuestions(questions);
        helpDesk.setAnswers(answers);
        helpDesk.setGuidance(guidance);

        // Update HelpDesk in the database
        helpDeskService.updateHelpDesk(helpDesk);

        // Redirect to the list page
        response.sendRedirect(request.getContextPath() + "/helpdesk?action=list");
    }

    private void deleteHelpDesk(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Retrieve the HelpDesk ID from the request
        int helpDeskId = Integer.parseInt(request.getParameter("id"));

        // Delete HelpDesk from the database
        helpDeskService.deleteHelpDesk(helpDeskId);

        // Redirect to the list page
        response.sendRedirect(request.getContextPath() + "/helpdesk?action=list");
    }

    private void viewHelpDesk(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the HelpDesk ID from the request
        int helpDeskId = Integer.parseInt(request.getParameter("id"));

        // Retrieve the HelpDesk from the database
        HelpDesk helpDesk = helpDeskService.getHelpDeskById(helpDeskId);
        String jsonProducts = new Gson().toJson(helpDesk);
        response.setContentType("application/json");
        response.getWriter().write(jsonProducts);
    }

    private void listHelpDesk(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve all HelpDesk entries from the database
        List<HelpDesk> helpDeskList = helpDeskService.getAllHelpDesk();
        String jsonProducts = new Gson().toJson(helpDeskList);
        response.setContentType("application/json");
        response.getWriter().write(jsonProducts);
    }
}