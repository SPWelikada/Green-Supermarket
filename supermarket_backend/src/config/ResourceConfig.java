package config;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@WebServlet("/assets/upload/*")
public class ResourceConfig extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

        String imagePath = request.getPathInfo().substring(1);
        String imageDirectoryPath = request.getServletContext().getRealPath("/assets/upload");

        File imageFile = new File(imageDirectoryPath, imagePath);

        if (imageFile.exists()) {
            String contentType = request.getServletContext().getMimeType(imageFile.getName());
            response.setContentType(contentType);
            Files.copy(imageFile.toPath(), response.getOutputStream());
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
