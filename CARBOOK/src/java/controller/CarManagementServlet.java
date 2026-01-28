package controller;

import dal.CarDAO;
import dal.CarBrandDAO;
import dal.CarModelDAO;
import dal.CarCategoryDAO;
import model.Car;
import model.CarBrand;
import model.CarModel;
import model.CarCategory;
import model.User;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * CarManagementServlet - Handles car CRUD operations
 */
@WebServlet(name = "CarManagementServlet", urlPatterns = {"/car-management"})
public class CarManagementServlet extends HttpServlet {

    private CarDAO carDAO = new CarDAO();
    private CarBrandDAO brandDAO = new CarBrandDAO();
    private CarModelDAO modelDAO = new CarModelDAO();
    private CarCategoryDAO categoryDAO = new CarCategoryDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            response.sendRedirect("login");
            return;
        }
        
        String action = request.getParameter("action");
        
        if (action == null) {
            action = "list";
        }
        
        switch (action) {
            case "list":
                listCars(request, response, user);
                break;
            case "add":
                showAddForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteCar(request, response, user);
                break;
            case "view":
                viewCar(request, response);
                break;
            case "verify":
                verifyCar(request, response, user);
                break;
            default:
                listCars(request, response, user);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            response.sendRedirect("login");
            return;
        }
        
        String action = request.getParameter("action");
        
        if ("create".equals(action)) {
            createCar(request, response, user);
        } else if ("update".equals(action)) {
            updateCar(request, response, user);
        } else if ("updateStatus".equals(action)) {
            updateCarStatus(request, response);
        }
    }

    private void listCars(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        List<Car> cars;
        
        // Admin sees all cars, CarOwner sees only their cars
        if (user.getRoleId() == 1) { // Admin
            cars = carDAO.getAllCars();
           // System.out.println("Admin - Loading all cars: " + (cars != null ? cars.size() : "null"));
        } else if (user.getRoleId() == 2) { // CarOwner
            cars = carDAO.getCarsByOwnerId(user.getUserId());
            // System.out.println("CarOwner - Loading cars for userId " + user.getUserId() + ": " + (cars != null ? cars.size() : "null"));
        } else {
            cars = carDAO.getAvailableCars();
            // System.out.println("Customer - Loading available cars: " + (cars != null ? cars.size() : "null"));
        }
        
        // Load brands and categories for filters and form
        List<CarBrand> brands = brandDAO.getAllBrands();
        List<CarCategory> categories = categoryDAO.getAllCategories();
        
        //System.out.println("Brands loaded: " + (brands != null ? brands.size() : "null"));
        //System.out.println("Categories loaded: " + (categories != null ? categories.size() : "null"));
        
        // Detailed logging of cars
        // if (cars != null) {
        //     System.out.println("=== Cars Details ===");
        //     for (Car car : cars) {
        //         System.out.println("Car ID: " + car.getCarId() + 
        //                          ", License: " + car.getLicensePlate() + 
        //                          ", Model ID: " + car.getModelId() + 
        //                          ", Status: " + car.getStatus());
        //     }
        //     System.out.println("===================");
        // }
        
        request.setAttribute("cars", cars);
        request.setAttribute("brands", brands);
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("car-management.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<CarBrand> brands = brandDAO.getAllBrands();
        List<CarCategory> categories = categoryDAO.getAllCategories();
        
        request.setAttribute("brands", brands);
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("car-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int carId = Integer.parseInt(request.getParameter("id"));
        Car car = carDAO.getCarById(carId);
        
        if (car == null) {
            HttpSession session = request.getSession();
            session.setAttribute("error", "Không tìm thấy xe");
            response.sendRedirect("car-management");
            return;
        }
        
        List<CarBrand> brands = brandDAO.getAllBrands();
        List<CarCategory> categories = categoryDAO.getAllCategories();
        
        List<CarModel> models = null;
        if (car.getModel() != null && car.getModel().getBrandId() > 0) {
            models = modelDAO.getModelsByBrandId(car.getModel().getBrandId());
        }
        
        request.setAttribute("car", car);
        request.setAttribute("brands", brands);
        request.setAttribute("categories", categories);
        request.setAttribute("models", models);
        request.getRequestDispatcher("car-form.jsp").forward(request, response);
    }

   