package controller;

import entity.Product;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;

import serviceImpl.ProductServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.Constant;
import util.ResponseUtil;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.List;

@WebServlet(urlPatterns = { "/products" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class ProductServlet extends HttpServlet {

    private final ProductServiceImpl productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

            if("get_by_id".equals(action)){
                    viewProduct(request, response);
            }else{
                try {
                    viewAllProducts(request, response);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            addProduct(request, response);
        } else if ("update".equals(action)) {
            updateProduct(request, response);
        }
        else if ("delete".equals(action)) {
            deleteProduct(request, response);
        }


    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        updateProduct(request, response);
    }

    private void viewAllProducts(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Product> products = productService.getAllProducts();
        ResponseUtil.sendJsonResponse(response,200,"all products",products);
    }


    private void viewProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("id"));
        Product product = productService.getProductById(productId);
        ResponseUtil.sendJsonResponse(response,200,"get product by id",product);
    }


    private void addProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String name = request.getParameter("name");
        String listPrice1 = request.getParameter("listPrice");
        String unitPrice1 = request.getParameter("unitPrice");
        String qty1 = request.getParameter("qty");
        Part filePart = request.getPart("imageFile");

        BigDecimal listPrice = new BigDecimal(listPrice1);
        BigDecimal unitPrice = new BigDecimal(unitPrice1);
        int qty = Integer.parseInt(qty1);

        if (filePart != null) {
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String savePath = request.getServletContext().getRealPath("/assets/upload");

            String fileResourcePath =  Constant.HOST +":"+ Constant.SERVER_PORT +"/"+ Constant.WAR_FILE_NAME + Constant.RESOURCE_PATH +"/"+ fileName;

            File directory = new File(savePath);
             if (!directory.exists()) {
                directory.mkdirs();
             }

            String imagePath = savePath + File.separator + fileName;

            try (InputStream input = filePart.getInputStream();
                 OutputStream output = new FileOutputStream(imagePath)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = input.read(buffer)) != -1) {
                    output.write(buffer, 0, bytesRead);
                }
            }

            Product product = new Product();
            product.setName(name);
            product.setListPrice(listPrice);
            product.setUnitPrice(unitPrice);
            product.setQty(qty);
            product.setImagePath(fileName);
            productService.addProduct(product);
            product.setImagePath(fileResourcePath);
            ResponseUtil.sendJsonResponse(response,HttpServletResponse.SC_OK,"save product sucess",product);

        } else {
            request.setAttribute("error", "Please select a file");
            ResponseUtil.sendJsonResponse(response,HttpServletResponse.SC_BAD_REQUEST,"err",null);
        }
    }


    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String listPrices = request.getParameter("listPrice");
        String unitPrices = request.getParameter("unitPrice");
        String qtys = request.getParameter("qty");

        BigDecimal listPrice = new BigDecimal(listPrices);
        BigDecimal unitPrice = new BigDecimal(unitPrices);
        int qty = Integer.parseInt(qtys);

        Part filePart = request.getPart("imageFile");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String savePath = "/assets/upload";
        File directory = new File(savePath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        if (!fileName.isEmpty()) {
            String imagePath = savePath + File.separator + fileName;

            try (InputStream input = filePart.getInputStream();
                 OutputStream output = new FileOutputStream(imagePath)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = input.read(buffer)) != -1) {
                    output.write(buffer, 0, bytesRead);
                }

                Product existingProduct = productService.getProductById(id);
                existingProduct.setImagePath(imagePath);
            }
        }

        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setListPrice(listPrice);
        product.setUnitPrice(unitPrice);
        product.setQty(qty);
        product.setImagePath(fileName);
        productService.updateProduct(product);

       // response.sendRedirect(request.getContextPath() + "/products?action=viewAll");
       // response.setContentType("application/json");
       // ObjectMapper objectMapper = new ObjectMapper();
       // ResponseUtil data = new ResponseUtil(HttpServletResponse.SC_OK,"product-update-success", product);
       // String jsonData = objectMapper.writeValueAsString(data);
       //  response.setStatus(HttpServletResponse.SC_OK);
       //response.getWriter().write(jsonData);

       ResponseUtil.sendJsonResponse(response,HttpServletResponse.SC_OK,"save product sucess",product);

    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int productId = Integer.parseInt(request.getParameter("id"));
          productService.deleteProduct(productId);
          //  response.sendRedirect(request.getContextPath() + "/admin?section=products");
          //  response.setContentType("application/json");
          //  ObjectMapper objectMapper = new ObjectMapper();
          //  ResponseUtil data = new ResponseUtil(HttpServletResponse.SC_OK,"product-delete-success", productId);
          //  String jsonData = objectMapper.writeValueAsString(data);
          //  response.setStatus(HttpServletResponse.SC_OK);
          //  response.getWriter().write(jsonData);

          ResponseUtil.sendJsonResponse(response,HttpServletResponse.SC_OK,"delete product sucess",productId);

    }


}