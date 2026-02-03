package controller;

import dal.CarImageDAO;
import dal.CarDAO;
import model.CarImage;
import model.Car;
import model.User;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

/**
 * CarImageServlet - Handle car image upload and management
 */
@WebServlet(name = "CarImageServlet", urlPatterns = {"/car-images"})
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2,  // 2MB
    maxFileSize = 1024 * 1024 * 10,       // 10MB
    maxRequestSize = 1024 * 1024 * 50     // 50MB
)
public class CarImageServlet extends HttpServlet {

    private static final String UPLOAD_DIR = "uploads/cars";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        // Check if user is logged in
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        // Only Admin (1) and Car Owner (2) can manage images
        if (user.getRoleId() != 1 && user.getRoleId() != 2) {
            session.setAttribute("error", "Bạn không có quyền truy cập trang này");
            response.sendRedirect("dashboard.jsp");
            return;
        }
        
        String action = request.getParameter("action");
        String carIdStr = request.getParameter("carId");
        
        if (carIdStr == null || carIdStr.isEmpty()) {
            session.setAttribute("error", "Không tìm thấy thông tin xe");
            response.sendRedirect("car-management.jsp");
            return;
        }
        
        int carId = Integer.parseInt(carIdStr);
        CarDAO carDAO = new CarDAO();
        Car car = carDAO.getCarById(carId);
        
        if (car == null) {
            session.setAttribute("error", "Không tìm thấy xe");
            response.sendRedirect("car-management.jsp");
            return;
        }
        
        // Car Owner can only manage their own cars
        if (user.getRoleId() == 2 && car.getOwnerId() != user.getUserId()) {
            session.setAttribute("error", "Bạn không có quyền quản lý ảnh của xe này");
            response.sendRedirect("car-management.jsp");
            return;
        }
        
        // Load images
        CarImageDAO imageDAO = new CarImageDAO();
        List<CarImage> images = imageDAO.getImagesByCarId(carId);
        
        request.setAttribute("car", car);
        request.setAttribute("images", images);
        request.getRequestDispatcher("car-images.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        // Check if user is logged in
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        // Only Admin (1) and Car Owner (2) can manage images
        if (user.getRoleId() != 1 && user.getRoleId() != 2) {
            session.setAttribute("error", "Bạn không có quyền truy cập trang này");
            response.sendRedirect("dashboard.jsp");
            return;
        }
        
        String action = request.getParameter("action");
        String carIdStr = request.getParameter("carId");
        
        if (carIdStr == null || carIdStr.isEmpty()) {
            session.setAttribute("error", "Không tìm thấy thông tin xe");
            response.sendRedirect("car-management.jsp");
            return;
        }
        
        int carId = Integer.parseInt(carIdStr);
        CarDAO carDAO = new CarDAO();
        Car car = carDAO.getCarById(carId);
        
        if (car == null) {
            session.setAttribute("error", "Không tìm thấy xe");
            response.sendRedirect("car-management.jsp");
            return;
        }
        
        // Car Owner can only manage their own cars
        if (user.getRoleId() == 2 && car.getOwnerId() != user.getUserId()) {
            session.setAttribute("error", "Bạn không có quyền quản lý ảnh của xe này");
            response.sendRedirect("car-management.jsp");
            return;
        }
        
        CarImageDAO imageDAO = new CarImageDAO();
        
        switch (action != null ? action : "") {
            case "upload":
                handleUpload(request, response, carId, imageDAO, session);
                break;
            case "delete":
                handleDelete(request, response, carId, imageDAO, session);
                break;
            case "setPrimary":
                handleSetPrimary(request, response, carId, imageDAO, session);
                break;
            default:
                session.setAttribute("error", "Hành động không hợp lệ");
                response.sendRedirect("car-images?carId=" + carId);
                break;
        }
    }
    
    