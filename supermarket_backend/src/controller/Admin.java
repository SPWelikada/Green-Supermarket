package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.AdminDTO;
import dto.LoginDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import serviceImpl.AdminServiceImpl;
import util.ResponseUtil;

import java.io.IOException;

@WebServlet("/admin/auth")
public class Admin extends HttpServlet {

    AdminServiceImpl adminService = new AdminServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String section = request.getParameter("section");
        if ("login".equals(section)) {
            try {
                login(request,response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if ("signup".equals(section)) {
            try {
                registerAdmin(request, response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


    private void login(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        LoginDTO login = adminService.adminLogin(new LoginDTO(username, password));
        ResponseUtil.sendJsonResponse(response,HttpServletResponse.SC_OK,"admin login success",login);
    }


    private void registerAdmin(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        AdminDTO adminDTO = new AdminDTO(name,email,username,password);
        AdminDTO adminRes = adminService.addAdmin(adminDTO);

        // sendRegistrationSuccessEmail(email);

        response.setContentType("application/json");
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseUtil data = new ResponseUtil(HttpServletResponse.SC_OK,"Admin-Registration-success", adminRes);
        String jsonData = objectMapper.writeValueAsString(data);
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(jsonData);

    }

}
