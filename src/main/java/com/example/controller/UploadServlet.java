package com.example.controller;

import com.example.dao.ProductDao;
import com.example.dao.ProductDaoImpl;
import com.example.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/upload")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
        maxFileSize=1024*1024*10,      // 10MB
        maxRequestSize=1024*1024*50)   // 50MB
public class UploadServlet extends HttpServlet {
    private ProductDao db;
    private ObjectMapper mapper = new ObjectMapper();

    /**
     * Name of the directory where uploaded files will be saved, relative to
     * the web application directory.
     */
    private static final String SAVE_DIR = "image";

    @Override
    public void init() throws ServletException {
        db = new ProductDaoImpl();
    }

    /**
     * handles file upload
     */
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        // gets absolute path of the web application
        String appPath = request.getServletContext().getRealPath("");

        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + SAVE_DIR;

        // creates the save directory if it does not exists
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        // refines the fileName in case it is an absolute path
        Part part = request.getPart("image");

        if (part != null) {
            String fileName = extractFileName(part);
            String image = getUniqueFileName(fileName);
            fileName = new File(image).getName();

            part.write(savePath + File.separator + fileName);

            String name = request.getParameter("name");
            String make = request.getParameter("make");
            String model = request.getParameter("model");
            String year = request.getParameter("year");
            String description = request.getParameter("description");
            String unitPrice = request.getParameter("unitPrice");

            Product p = new Product(0, name, description, image, Double.parseDouble(unitPrice), model, make, Integer.parseInt(year));
            db.addProduct(p);
            response.getWriter().write(mapper.writeValueAsString(p));
        }
    }

    /**
     * Extracts file name from HTTP header content-disposition
     */
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }

    private String getUniqueFileName(String name) {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "") + "_" + name ;
    }
}