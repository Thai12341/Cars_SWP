package controller;

import dal.CarModelDAO;
import dal.CarBrandDAO;
import model.CarModel;
import model.User;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.google.gson.Gson;

@WebServlet(name = "CarModelServlet", urlPatterns = {"/car-models"})
public class CarModelServlet extends HttpServlet {

    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CarModelDAO modelDAO = new CarModelDAO();

        CarBrandDAO brandDAO = new CarBrandDAO();

        try {
            String action = request.getParameter("action");

            if ("getByBrand".equals(action)) {

                int brandId = Integer.parseInt(request.getParameter("brandId"));

                response.setContentType("application/json;charset=UTF-8");

                response.getWriter().write(gson.toJson(modelDAO.getModelsByBrandId(brandId)));

                return;
            }

            HttpSession session = request.getSession();

            User user = (User) session.getAttribute("user");

            if (user == null || (user.getRoleId() != 1 && user.getRoleId() != 2)) {

                response.sendRedirect("home");
                return;
            }

            if ("add".equals(action)) {
                request.setAttribute("brands", brandDAO.getAllBrands());

                request.getRequestDispatcher("/model-add.jsp").forward(request, response);

            } else if ("edit".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));

                request.setAttribute("model", modelDAO.getModelById(id));

                request.setAttribute("brands", brandDAO.getAllBrands());
                request.getRequestDispatcher("/model-edit.jsp").forward(request, response);
            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                if (modelDAO.deleteModel(id)) {
                    session.setAttribute("success", "Xóa thành công!");
                } else {
                    session.setAttribute("error", "Xóa thất bại!");
                }
                response.sendRedirect("car-models");
            } else {
                request.setAttribute("models", modelDAO.getAllModels());
                request.getRequestDispatcher("/model-list.jsp").forward(request, response);
            }
        } finally {
            modelDAO.closeConnection();
            brandDAO.closeConnection();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CarModelDAO modelDAO = new CarModelDAO();
        try {
            String action = request.getParameter("action");

            int brandId = Integer.parseInt(request.getParameter("brandId"));

            String name = request.getParameter("modelName");

            int year = Integer.parseInt(request.getParameter("year"));

            if ("create".equals(action)) {

                CarModel m = new CarModel(0, brandId, name, year);

                if (modelDAO.createModel(m)) {
                    request.getSession().setAttribute("success", "Thêm mới thành công!");
                }

                response.sendRedirect("car-models");

            } else if ("update".equals(action)) {

                int id = Integer.parseInt(request.getParameter("modelId"));

                CarModel m = new CarModel(id, brandId, name, year);

                if (modelDAO.updateModel(m)) {
                    request.getSession().setAttribute("success", "Cập nhật thành công!");
                }

                response.sendRedirect("car-models");
            }
        } finally {
            modelDAO.closeConnection();
        }
    }
}
