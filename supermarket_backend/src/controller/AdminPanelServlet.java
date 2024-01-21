package controller;


import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;




public class AdminPanelServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String section = request.getParameter("section");

        if ("products".equals(section)) {
            request.getRequestDispatcher("/admin/manageProducts.jsp").forward(request, response);
        } else if ("helpdesk".equals(section)) {
            request.getRequestDispatcher("/admin/manageHelpDesk.jsp").forward(request, response);
        } else if ("feedbacks".equals(section)) {
            request.getRequestDispatcher("/admin/manageFeedbacks.jsp").forward(request, response);
        } else if ("profile".equals(section)) {
            request.getRequestDispatcher("/admin/profile.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/admin/adminHome.jsp").forward(request, response);
        }
    }






}




//               response.setContentType("application/json");
//               ObjectMapper objectMapper = new ObjectMapper();
//               ResponseUtil data = new ResponseUtil(HttpServletResponse.SC_OK,"Registration-success", customer);
//               String jsonData = objectMapper.writeValueAsString(data);
//               response.setStatus(HttpServletResponse.SC_OK);
//               response.getWriter().write(jsonData);