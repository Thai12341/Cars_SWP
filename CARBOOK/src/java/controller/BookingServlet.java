package controller;

import dal.BookingDAO;
import dal.CarDAO;
import dal.PaymentDAO;
import model.Booking;
import model.Car;
import model.User;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * BookingServlet - Handles booking operations
 */
@WebServlet(name = "BookingServlet", urlPatterns = {"/booking"})
public class BookingServlet extends HttpServlet {

    private BookingDAO bookingDAO = new BookingDAO();
    private CarDAO carDAO = new CarDAO();
    private PaymentDAO paymentDAO = new PaymentDAO();

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
                listBookings(request, response, user);
                break;
            case "create":
                showBookingForm(request, response);
                break;
            case "view":
                viewBooking(request, response);
                break;
            case "cancel":
                cancelBooking(request, response, user);
                break;
            case "approve":
                approveBooking(request, response, user);
                break;
            case "reject":
                rejectBooking(request, response, user);
                break;
            case "complete":
                completeBooking(request, response, user);
                break;
            default:
                listBookings(request, response, user);
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
            createBooking(request, response, user);
        }
    }

    private void listBookings(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        List<Booking> bookings;
        
        if (user.getRoleId() == 1) { // Admin
            bookings = bookingDAO.getAllBookings();
        } else if (user.getRoleId() == 2) { // CarOwner
            // Get bookings for cars owned by this user
            List<Car> ownerCars = carDAO.getCarsByOwnerId(user.getUserId());
            bookings = bookingDAO.getAllBookings(); // Filter in JSP or create specific method
        } else { // Customer
            bookings = bookingDAO.getBookingsByCustomerId(user.getUserId());
        }
        
        request.setAttribute("bookings", bookings);
        request.getRequestDispatcher("bookings.jsp").forward(request, response);
    }
