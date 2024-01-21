package config;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import util.ResponseUtil;

import java.io.IOException;

@WebFilter("/*")
public class CROSFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

           HttpServletResponse response = (HttpServletResponse) servletResponse;
           ObjectMapper objectMapper = new ObjectMapper();

        try {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
            filterChain.doFilter(servletRequest, servletResponse);

        } catch (Exception e) {
            response.setContentType("application/json");
            servletRequest.setAttribute("errorMessage", "An error occurred: " + e.getMessage());
            ResponseUtil data = new ResponseUtil(HttpServletResponse.SC_INTERNAL_SERVER_ERROR , e.getCause().getLocalizedMessage() ,e.getMessage());
            response.getWriter().write(objectMapper.writeValueAsString(data));
        }
    }
}
