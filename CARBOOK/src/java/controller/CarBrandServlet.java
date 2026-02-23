package controller;

import dal.CarBrandDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.CarBrand;
import model.User; 

@WebServlet(name="CarBrandServlet", urlPatterns={"/brand"})
public class CarBrandServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    HttpSession session = request.getSession();
    model.User user = (model.User) session.getAttribute("user");
    String action = request.getParameter("action");
    CarBrandDAO dao = new CarBrandDAO();

    if (user == null || (user.getRoleId() != 1 && user.getRoleId() != 2)) {
        response.sendRedirect("home");
        return;
    }

    if (action == null || "list".equals(action)) {
        request.setAttribute("brands", dao.getAllBrands());
        request.getRequestDispatcher("/brand-list.jsp").forward(request, response);
    } 
    else if ("add".equals(action)) {
        request.getRequestDispatcher("/brand-add.jsp").forward(request, response);
    }
    else if ("edit".equals(action)) {
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("brand", dao.getBrandById(id));
        request.getRequestDispatcher("/brand-edit.jsp").forward(request, response);
    }
    else if ("delete".equals(action)) {
        dao.deleteBrand(Integer.parseInt(request.getParameter("id")));
        response.sendRedirect("brand?action=list");
    }
}

@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String action = request.getParameter("action");
    CarBrandDAO dao = new CarBrandDAO();
    
    String name = request.getParameter("brandName");
    String country = request.getParameter("country");
    String logo = request.getParameter("logoURL");

    model.CarBrand b = new model.CarBrand();
    b.setBrandName(name);
    b.setCountry(country);
    b.setLogoURL(logo);

    if ("add".equals(action)) {
        dao.createBrand(b);
    } else if ("edit".equals(action)) {
        b.setBrandId(Integer.parseInt(request.getParameter("brandId")));
        dao.updateBrand(b);
    }
    response.sendRedirect("brand?action=list");
}
}