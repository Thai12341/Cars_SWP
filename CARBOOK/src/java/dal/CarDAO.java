package dal;

import model.Car;
import model.CarModel;
import model.CarCategory;
import model.CarBrand;
import model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * CarDAO - Data Access Object for Car management
 */
public class CarDAO extends DBContext {
<<<<<<< HEAD

    private CarModelDAO modelDAO = new CarModelDAO();
    private CarCategoryDAO categoryDAO = new CarCategoryDAO();
    private CarBrandDAO brandDAO = new CarBrandDAO();

    /**
     * Get all cars (no pagination)
     * 
=======
    
    private CarModelDAO modelDAO = new CarModelDAO();
    private CarCategoryDAO categoryDAO = new CarCategoryDAO();
    private CarBrandDAO brandDAO = new CarBrandDAO();
    
    /**
     * Get all cars
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
     * @return List of all cars
     */
    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
<<<<<<< HEAD
        String sql = "SELECT * FROM Cars WHERE IsDeleted = 0 ORDER BY CreatedAt DESC";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

=======
        String sql = "SELECT * FROM Cars ORDER BY CreatedAt DESC";
        
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
            while (rs.next()) {
                cars.add(extractCarFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error getting all cars: " + e.getMessage());
        }
        return cars;
    }
<<<<<<< HEAD

    /**
     * Get all cars with pagination
     * 
     * @param page     Page number (starting from 1)
     * @param pageSize Number of records per page
     * @return List of cars
     */
    public List<Car> getAllCars(int page, int pageSize) {
        List<Car> cars = new ArrayList<>();
        int offset = (page - 1) * pageSize;

        String sql = "SELECT * FROM Cars " +
                "WHERE IsDeleted = 0 " +
                "ORDER BY CreatedAt DESC " +
                "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, offset);
            stm.setInt(2, pageSize);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                cars.add(extractCarFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error getting all cars with pagination: " + e.getMessage());
            e.printStackTrace();
        }
        return cars;
    }

    /**
     * Get total number of cars
     * 
     * @return Total car count
     */
    public int getTotalCars() {
        String sql = "SELECT COUNT(*) FROM Cars WHERE IsDeleted = 0";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error getting total cars: " + e.getMessage());
        }
        return 0;
    }

    /**
     * Get car by ID
     * 
=======
    
    /**
     * Get car by ID
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
     * @param carId
     * @return Car object or null
     */
    public Car getCarById(int carId) {
        String sql = "SELECT * FROM Cars WHERE CarID = ?";
<<<<<<< HEAD

=======
        
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, carId);
            ResultSet rs = stm.executeQuery();
<<<<<<< HEAD

=======
            
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
            if (rs.next()) {
                return extractCarFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error getting car by ID: " + e.getMessage());
        }
        return null;
    }
<<<<<<< HEAD

    /**
     * Get cars by owner ID
     * 
=======
    
    /**
     * Get cars by owner ID
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
     * @param ownerId
     * @return List of cars owned by the user
     */
    public List<Car> getCarsByOwnerId(int ownerId) {
        List<Car> cars = new ArrayList<>();
<<<<<<< HEAD
        String sql = "SELECT * FROM Cars WHERE OwnerID = ? AND IsDeleted = 0 ORDER BY CreatedAt DESC";

=======
        String sql = "SELECT * FROM Cars WHERE OwnerID = ? ORDER BY CreatedAt DESC";
        
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, ownerId);
            ResultSet rs = stm.executeQuery();
<<<<<<< HEAD

=======
            
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
            while (rs.next()) {
                cars.add(extractCarFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error getting cars by owner ID: " + e.getMessage());
        }
        return cars;
    }
<<<<<<< HEAD

    /**
     * Get available cars
     * 
=======
    
    /**
     * Get available cars
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
     * @return List of available cars
     */
    public List<Car> getAvailableCars() {
        List<Car> cars = new ArrayList<>();
<<<<<<< HEAD
        String sql = "SELECT * FROM Cars WHERE Status = 'Available' AND IsVerified = 1 AND IsDeleted = 0 ORDER BY CreatedAt DESC";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

=======
        String sql = "SELECT * FROM Cars WHERE Status = 'Available' AND IsVerified = 1 ORDER BY CreatedAt DESC";
        
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
            while (rs.next()) {
                cars.add(extractCarFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error getting available cars: " + e.getMessage());
        }
        return cars;
    }
<<<<<<< HEAD

    /**
     * Get cars by category
     * 
=======
    
    /**
     * Get cars by category
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
     * @param categoryId
     * @return List of cars in the category
     */
    public List<Car> getCarsByCategory(int categoryId) {
        List<Car> cars = new ArrayList<>();
<<<<<<< HEAD
        String sql = "SELECT * FROM Cars WHERE CategoryID = ? AND Status = 'Available' AND IsVerified = 1 AND IsDeleted = 0";

=======
        String sql = "SELECT * FROM Cars WHERE CategoryID = ? AND Status = 'Available' AND IsVerified = 1";
        
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, categoryId);
            ResultSet rs = stm.executeQuery();
<<<<<<< HEAD

=======
            
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
            while (rs.next()) {
                cars.add(extractCarFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error getting cars by category: " + e.getMessage());
        }
        return cars;
    }
<<<<<<< HEAD

    /**
     * Search cars by criteria (old method - kept for compatibility)
     * 
     * @param keyword    Search keyword
     * @param categoryId Category filter (0 for all)
     * @param minPrice   Minimum price
     * @param maxPrice   Maximum price
=======
    
    /**
     * Search cars by criteria
     * @param keyword Search keyword
     * @param categoryId Category filter (0 for all)
     * @param minPrice Minimum price
     * @param maxPrice Maximum price
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
     * @return List of matching cars
     */
    public List<Car> searchCars(String keyword, int categoryId, double minPrice, double maxPrice) {
        List<Car> cars = new ArrayList<>();
<<<<<<< HEAD
        StringBuilder sql = new StringBuilder(
                "SELECT * FROM Cars WHERE Status = 'Available' AND IsVerified = 1 AND IsDeleted = 0");

=======
        StringBuilder sql = new StringBuilder("SELECT * FROM Cars WHERE Status = 'Available' AND IsVerified = 1");
        
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
        if (keyword != null && !keyword.trim().isEmpty()) {
            sql.append(" AND (LicensePlate LIKE ? OR Description LIKE ?)");
        }
        if (categoryId > 0) {
            sql.append(" AND CategoryID = ?");
        }
        if (minPrice > 0) {
            sql.append(" AND PricePerDay >= ?");
        }
        if (maxPrice > 0) {
            sql.append(" AND PricePerDay <= ?");
        }
        sql.append(" ORDER BY CreatedAt DESC");
<<<<<<< HEAD

        try {
            PreparedStatement stm = connection.prepareStatement(sql.toString());
            int paramIndex = 1;

=======
        
        try {
            PreparedStatement stm = connection.prepareStatement(sql.toString());
            int paramIndex = 1;
            
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
            if (keyword != null && !keyword.trim().isEmpty()) {
                String searchPattern = "%" + keyword + "%";
                stm.setString(paramIndex++, searchPattern);
                stm.setString(paramIndex++, searchPattern);
            }
            if (categoryId > 0) {
                stm.setInt(paramIndex++, categoryId);
            }
            if (minPrice > 0) {
                stm.setDouble(paramIndex++, minPrice);
            }
            if (maxPrice > 0) {
                stm.setDouble(paramIndex++, maxPrice);
            }
<<<<<<< HEAD

=======
            
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                cars.add(extractCarFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error searching cars: " + e.getMessage());
        }
        return cars;
    }
<<<<<<< HEAD

    /**
     * Search cars with filters and pagination (new method for car-management)
     * 
     * @param search     Search keyword
     * @param brandId    Brand filter
     * @param categoryId Category filter
     * @param status     Status filter
     * @param page       Page number
     * @param pageSize   Page size
     * @return List of matching cars
     */
    public List<Car> searchCars(String search, Integer brandId, Integer categoryId,
            String status, int page, int pageSize) {
        List<Car> cars = new ArrayList<>();
        int offset = (page - 1) * pageSize;

        StringBuilder sql = new StringBuilder(
                "SELECT c.* FROM Cars c " +
                        "INNER JOIN CarModels cm ON c.ModelID = cm.ModelID " +
                        "INNER JOIN CarBrands cb ON cm.BrandID = cb.BrandID " +
                        "WHERE c.IsDeleted = 0 ");

        // Add filters
        if (search != null && !search.trim().isEmpty()) {
            sql.append(
                    "AND (cm.ModelName LIKE ? OR cb.BrandName LIKE ? OR c.LicensePlate LIKE ? OR c.Description LIKE ?) ");
        }
        if (brandId != null) {
            sql.append("AND cb.BrandID = ? ");
        }
        if (categoryId != null) {
            sql.append("AND c.CategoryID = ? ");
        }
        if (status != null && !status.isEmpty()) {
            sql.append("AND c.Status = ? ");
        }

        sql.append("ORDER BY c.CreatedAt DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

        try {
            PreparedStatement stm = connection.prepareStatement(sql.toString());
            int paramIndex = 1;

            if (search != null && !search.trim().isEmpty()) {
                String searchPattern = "%" + search + "%";
                stm.setString(paramIndex++, searchPattern);
                stm.setString(paramIndex++, searchPattern);
                stm.setString(paramIndex++, searchPattern);
                stm.setString(paramIndex++, searchPattern);
            }
            if (brandId != null) {
                stm.setInt(paramIndex++, brandId);
            }
            if (categoryId != null) {
                stm.setInt(paramIndex++, categoryId);
            }
            if (status != null && !status.isEmpty()) {
                stm.setString(paramIndex++, status);
            }

            stm.setInt(paramIndex++, offset);
            stm.setInt(paramIndex, pageSize);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                cars.add(extractCarFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error searching cars: " + e.getMessage());
            e.printStackTrace();
        }
        return cars;
    }

    /**
     * Count search results
     * 
     * @param search     Search keyword
     * @param brandId    Brand filter
     * @param categoryId Category filter
     * @param status     Status filter
     * @return Total count
     */
    public int countSearchCars(String search, Integer brandId, Integer categoryId, String status) {
        StringBuilder sql = new StringBuilder(
                "SELECT COUNT(*) FROM Cars c " +
                        "INNER JOIN CarModels cm ON c.ModelID = cm.ModelID " +
                        "INNER JOIN CarBrands cb ON cm.BrandID = cb.BrandID " +
                        "WHERE c.IsDeleted = 0 ");

        if (search != null && !search.trim().isEmpty()) {
            sql.append(
                    "AND (cm.ModelName LIKE ? OR cb.BrandName LIKE ? OR c.LicensePlate LIKE ? OR c.Description LIKE ?) ");
        }
        if (brandId != null) {
            sql.append("AND cb.BrandID = ? ");
        }
        if (categoryId != null) {
            sql.append("AND c.CategoryID = ? ");
        }
        if (status != null && !status.isEmpty()) {
            sql.append("AND c.Status = ? ");
        }

        try {
            PreparedStatement stm = connection.prepareStatement(sql.toString());
            int paramIndex = 1;

            if (search != null && !search.trim().isEmpty()) {
                String searchPattern = "%" + search + "%";
                stm.setString(paramIndex++, searchPattern);
                stm.setString(paramIndex++, searchPattern);
                stm.setString(paramIndex++, searchPattern);
                stm.setString(paramIndex++, searchPattern);
            }
            if (brandId != null) {
                stm.setInt(paramIndex++, brandId);
            }
            if (categoryId != null) {
                stm.setInt(paramIndex++, categoryId);
            }
            if (status != null && !status.isEmpty()) {
                stm.setString(paramIndex++, status);
            }

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error counting search cars: " + e.getMessage());
        }
        return 0;
    }

    /**
     * Create a new car
     * 
=======
    
    /**
     * Create a new car
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
     * @param car
     * @return true if successful, false otherwise
     */
    public boolean createCar(Car car) {
        String sql = "INSERT INTO Cars (OwnerID, ModelID, CategoryID, LicensePlate, VINNumber, Color, Seats, " +
<<<<<<< HEAD
                "FuelType, Transmission, Mileage, PricePerDay, PricePerHour, Status, Location, Description, " +
                "Features, InsuranceExpiryDate, RegistrationExpiryDate, IsDeleted) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0)";

=======
                     "FuelType, Transmission, Mileage, PricePerDay, PricePerHour, Status, Location, Description, " +
                     "Features, InsuranceExpiryDate, RegistrationExpiryDate) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, car.getOwnerId());
            stm.setInt(2, car.getModelId());
            stm.setInt(3, car.getCategoryId());
            stm.setString(4, car.getLicensePlate());
            stm.setString(5, car.getVinNumber());
            stm.setString(6, car.getColor());
            stm.setInt(7, car.getSeats());
            stm.setString(8, car.getFuelType());
            stm.setString(9, car.getTransmission());
            stm.setBigDecimal(10, car.getMileage());
            stm.setBigDecimal(11, car.getPricePerDay());
            stm.setBigDecimal(12, car.getPricePerHour());
            stm.setString(13, car.getStatus());
            stm.setString(14, car.getLocation());
            stm.setString(15, car.getDescription());
            stm.setString(16, car.getFeatures());
            stm.setDate(17, car.getInsuranceExpiryDate());
            stm.setDate(18, car.getRegistrationExpiryDate());
<<<<<<< HEAD

=======
            
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
            return stm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error creating car: " + e.getMessage());
        }
        return false;
    }
<<<<<<< HEAD

    /**
     * Update car
     * 
=======
    
    /**
     * Update car
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
     * @param car
     * @return true if successful, false otherwise
     */
    public boolean updateCar(Car car) {
        String sql = "UPDATE Cars SET ModelID = ?, CategoryID = ?, LicensePlate = ?, VINNumber = ?, Color = ?, " +
<<<<<<< HEAD
                "Seats = ?, FuelType = ?, Transmission = ?, Mileage = ?, PricePerDay = ?, PricePerHour = ?, " +
                "Status = ?, Location = ?, Description = ?, Features = ?, InsuranceExpiryDate = ?, " +
                "RegistrationExpiryDate = ?, UpdatedAt = GETDATE() WHERE CarID = ?";

=======
                     "Seats = ?, FuelType = ?, Transmission = ?, Mileage = ?, PricePerDay = ?, PricePerHour = ?, " +
                     "Status = ?, Location = ?, Description = ?, Features = ?, InsuranceExpiryDate = ?, " +
                     "RegistrationExpiryDate = ?, UpdatedAt = GETDATE() WHERE CarID = ?";
        
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, car.getModelId());
            stm.setInt(2, car.getCategoryId());
            stm.setString(3, car.getLicensePlate());
            stm.setString(4, car.getVinNumber());
            stm.setString(5, car.getColor());
            stm.setInt(6, car.getSeats());
            stm.setString(7, car.getFuelType());
            stm.setString(8, car.getTransmission());
            stm.setBigDecimal(9, car.getMileage());
            stm.setBigDecimal(10, car.getPricePerDay());
            stm.setBigDecimal(11, car.getPricePerHour());
            stm.setString(12, car.getStatus());
            stm.setString(13, car.getLocation());
            stm.setString(14, car.getDescription());
            stm.setString(15, car.getFeatures());
            stm.setDate(16, car.getInsuranceExpiryDate());
            stm.setDate(17, car.getRegistrationExpiryDate());
            stm.setInt(18, car.getCarId());
<<<<<<< HEAD

=======
            
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
            return stm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating car: " + e.getMessage());
        }
        return false;
    }
<<<<<<< HEAD

    /**
     * Update car status
     * 
=======
    
    /**
     * Update car status
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
     * @param carId
     * @param status
     * @return true if successful, false otherwise
     */
    public boolean updateCarStatus(int carId, String status) {
        String sql = "UPDATE Cars SET Status = ?, UpdatedAt = GETDATE() WHERE CarID = ?";
<<<<<<< HEAD

=======
        
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, status);
            stm.setInt(2, carId);
<<<<<<< HEAD

=======
            
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
            return stm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating car status: " + e.getMessage());
        }
        return false;
    }
<<<<<<< HEAD

    /**
     * Verify car
     * 
=======
    
    /**
     * Verify car
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
     * @param carId
     * @param isVerified
     * @return true if successful, false otherwise
     */
    public boolean verifyCar(int carId, boolean isVerified) {
        String sql = "UPDATE Cars SET IsVerified = ?, UpdatedAt = GETDATE() WHERE CarID = ?";
<<<<<<< HEAD

=======
        
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setBoolean(1, isVerified);
            stm.setInt(2, carId);
<<<<<<< HEAD

=======
            
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
            return stm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error verifying car: " + e.getMessage());
        }
        return false;
    }
<<<<<<< HEAD

    /**
     * Delete car (hard delete)
     * 
=======
    
    /**
     * Delete car
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
     * @param carId
     * @return true if successful, false otherwise
     */
    public boolean deleteCar(int carId) {
        String sql = "DELETE FROM Cars WHERE CarID = ?";
<<<<<<< HEAD

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, carId);

=======
        
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, carId);
            
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
            return stm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting car: " + e.getMessage());
        }
        return false;
    }
<<<<<<< HEAD

    /**
     * Soft delete car (mark as deleted)
     * 
     * @param carId Car ID
     * @return true if successful
     */
    public boolean softDeleteCar(int carId) {
        String sql = "UPDATE Cars SET IsDeleted = 1, UpdatedAt = GETDATE() WHERE CarID = ?";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, carId);

            return stm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error soft deleting car: " + e.getMessage());
        }
        return false;
    }

    /**
     * Extract Car object from ResultSet
     * 
=======
    
    /**
     * Extract Car object from ResultSet
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
     * @param rs
     * @return Car object
     * @throws SQLException
     */
    private Car extractCarFromResultSet(ResultSet rs) throws SQLException {
        Car car = new Car();
        car.setCarId(rs.getInt("CarID"));
        car.setOwnerId(rs.getInt("OwnerID"));
        car.setModelId(rs.getInt("ModelID"));
        car.setCategoryId(rs.getInt("CategoryID"));
        car.setLicensePlate(rs.getString("LicensePlate"));
<<<<<<< HEAD

        // Handle optional columns
        try {
            car.setVinNumber(rs.getString("VINNumber"));
        } catch (SQLException e) {
            car.setVinNumber(null);
        }

=======
        car.setVinNumber(rs.getString("VINNumber"));
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
        car.setColor(rs.getString("Color"));
        car.setSeats(rs.getInt("Seats"));
        car.setFuelType(rs.getString("FuelType"));
        car.setTransmission(rs.getString("Transmission"));
<<<<<<< HEAD

        try {
            car.setMileage(rs.getBigDecimal("Mileage"));
        } catch (SQLException e) {
            car.setMileage(BigDecimal.ZERO);
        }

        car.setPricePerDay(rs.getBigDecimal("PricePerDay"));

        try {
            car.setPricePerHour(rs.getBigDecimal("PricePerHour"));
        } catch (SQLException e) {
            car.setPricePerHour(BigDecimal.ZERO);
        }

        car.setStatus(rs.getString("Status"));
        car.setLocation(rs.getString("Location"));
        car.setDescription(rs.getString("Description"));

        try {
            car.setFeatures(rs.getString("Features"));
        } catch (SQLException e) {
            car.setFeatures(null);
        }

        try {
            car.setInsuranceExpiryDate(rs.getDate("InsuranceExpiryDate"));
        } catch (SQLException e) {
            car.setInsuranceExpiryDate(null);
        }

        try {
            car.setRegistrationExpiryDate(rs.getDate("RegistrationExpiryDate"));
        } catch (SQLException e) {
            car.setRegistrationExpiryDate(null);
        }

        car.setVerified(rs.getBoolean("IsVerified"));
        car.setCreatedAt(rs.getTimestamp("CreatedAt"));
        car.setUpdatedAt(rs.getTimestamp("UpdatedAt"));

        // Load related data
        loadCarRelatedData(car);

        return car;
    }

    /**
     * Load related data for car (model, brand, category, image)
     * 
=======
        car.setMileage(rs.getBigDecimal("Mileage"));
        car.setPricePerDay(rs.getBigDecimal("PricePerDay"));
        car.setPricePerHour(rs.getBigDecimal("PricePerHour"));
        car.setStatus(rs.getString("Status"));
        car.setLocation(rs.getString("Location"));
        car.setDescription(rs.getString("Description"));
        car.setFeatures(rs.getString("Features"));
        car.setInsuranceExpiryDate(rs.getDate("InsuranceExpiryDate"));
        car.setRegistrationExpiryDate(rs.getDate("RegistrationExpiryDate"));
        car.setVerified(rs.getBoolean("IsVerified"));
        car.setCreatedAt(rs.getTimestamp("CreatedAt"));
        car.setUpdatedAt(rs.getTimestamp("UpdatedAt"));
        
        // Load related objects
        loadCarRelatedData(car);
        
        return car;
    }
    
    /**
     * Load related data for car (model, brand, category, image)
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
     * @param car
     */
    private void loadCarRelatedData(Car car) {
        try {
            // Load model and brand
            if (car.getModelId() > 0) {
                CarModel model = modelDAO.getModelById(car.getModelId());
                if (model != null) {
                    if (model.getBrandId() > 0) {
                        CarBrand brand = brandDAO.getBrandById(model.getBrandId());
                        if (brand != null) {
                            model.setBrand(brand);
                        }
                    }
                    car.setModel(model);
                }
            }
<<<<<<< HEAD

=======
            
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
            // Load category
            if (car.getCategoryId() > 0) {
                CarCategory category = categoryDAO.getCategoryById(car.getCategoryId());
                if (category != null) {
                    car.setCategory(category);
                }
            }
<<<<<<< HEAD

=======
            
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
            // Load owner
            if (car.getOwnerId() > 0) {
                UserDAO userDAO = new UserDAO();
                User owner = userDAO.getUserById(car.getOwnerId());
                if (owner != null) {
                    car.setOwner(owner);
                }
            }
<<<<<<< HEAD

=======
            
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
            // Load first image
            String imageSql = "SELECT TOP 1 ImageURL FROM CarImages WHERE CarID = ? ORDER BY DisplayOrder";
            PreparedStatement stm = connection.prepareStatement(imageSql);
            stm.setInt(1, car.getCarId());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                car.setImageUrl(rs.getString("ImageURL"));
            }
        } catch (SQLException e) {
            System.out.println("Error loading car related data: " + e.getMessage());
            e.printStackTrace();
        }
    }
<<<<<<< HEAD

    /**
     * Get available cars during specified date range and location
     * 
     * @param pickupDate      Pickup date string (yyyy-MM-dd or dd/MM/yyyy)
     * @param dropoffDate     Dropoff date string (yyyy-MM-dd or dd/MM/yyyy)
     * @param pickupLocation  Pickup location (optional)
     * @param dropoffLocation Dropoff location (optional)
     * @return List of available cars
     */
    public List<Car> getAvailableCarsForBooking(String pickupDate, String dropoffDate,
            String pickupLocation, String dropoffLocation) {
        List<Car> availableCars = new ArrayList<>();

        // Get all available cars first
        List<Car> allCars = getAvailableCars();

=======
    
    /**
     * Get available cars during specified date range and location
     * @param pickupDate Pickup date string (yyyy-MM-dd or dd/MM/yyyy)
     * @param dropoffDate Dropoff date string (yyyy-MM-dd or dd/MM/yyyy)
     * @param pickupLocation Pickup location (optional)
     * @param dropoffLocation Dropoff location (optional)
     * @return List of available cars
     */
    public List<Car> getAvailableCarsForBooking(String pickupDate, String dropoffDate, 
                                                  String pickupLocation, String dropoffLocation) {
        List<Car> availableCars = new ArrayList<>();
        
        // Get all available cars first
        List<Car> allCars = getAvailableCars();
        
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
        // If no date filter, return all available cars
        if (pickupDate == null || pickupDate.isEmpty() || dropoffDate == null || dropoffDate.isEmpty()) {
            return filterByLocation(allCars, pickupLocation, dropoffLocation);
        }
<<<<<<< HEAD

=======
        
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
        try {
            // Convert date strings to SQL dates
            java.sql.Date sqlPickupDate = parseDateString(pickupDate);
            java.sql.Date sqlDropoffDate = parseDateString(dropoffDate);
<<<<<<< HEAD

=======
            
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
            // Filter cars by availability
            for (Car car : allCars) {
                if (isCarAvailableForPeriod(car.getCarId(), sqlPickupDate, sqlDropoffDate)) {
                    availableCars.add(car);
                }
            }
<<<<<<< HEAD

            // Filter by location if provided
            return filterByLocation(availableCars, pickupLocation, dropoffLocation);

=======
            
            // Filter by location if provided
            return filterByLocation(availableCars, pickupLocation, dropoffLocation);
            
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
        } catch (Exception e) {
            System.out.println("Error getting available cars for booking: " + e.getMessage());
            return allCars; // Return all cars if error
        }
    }
<<<<<<< HEAD

    /**
     * Check if a car is available during the specified date range
     * 
     * @param carId       Car ID
     * @param pickupDate  Pickup date
=======
    
    /**
     * Check if a car is available during the specified date range
     * @param carId Car ID
     * @param pickupDate Pickup date
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
     * @param dropoffDate Dropoff date
     * @return true if available, false otherwise
     */
    public boolean isCarAvailableForPeriod(int carId, java.sql.Date pickupDate, java.sql.Date dropoffDate) {
        // Check for existing bookings
        String sqlBookings = "SELECT COUNT(*) FROM Bookings " +
<<<<<<< HEAD
                "WHERE CarID = ? " +
                "AND Status NOT IN ('Cancelled', 'Rejected') " +
                "AND ((PickupDate <= ? AND ReturnDate >= ?) " +
                "OR (PickupDate <= ? AND ReturnDate >= ?) " +
                "OR (PickupDate >= ? AND ReturnDate <= ?))";

=======
                     "WHERE CarID = ? " +
                     "AND Status NOT IN ('Cancelled', 'Rejected') " +
                     "AND ((PickupDate <= ? AND ReturnDate >= ?) " +
                     "OR (PickupDate <= ? AND ReturnDate >= ?) " +
                     "OR (PickupDate >= ? AND ReturnDate <= ?))";
        
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
        try {
            PreparedStatement stm = connection.prepareStatement(sqlBookings);
            stm.setInt(1, carId);
            stm.setDate(2, dropoffDate);
            stm.setDate(3, dropoffDate);
            stm.setDate(4, pickupDate);
            stm.setDate(5, pickupDate);
            stm.setDate(6, pickupDate);
            stm.setDate(7, dropoffDate);
<<<<<<< HEAD

=======
            
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                int conflictCount = rs.getInt(1);
                if (conflictCount > 0) {
                    return false; // Has booking conflicts
                }
            }
<<<<<<< HEAD

            // Check for blocked periods in CarAvailability
            String sqlAvailability = "SELECT COUNT(*) FROM CarAvailability " +
                    "WHERE CarID = ? AND IsAvailable = 0 " +
                    "AND ((StartDate <= ? AND EndDate >= ?) " +
                    "OR (StartDate <= ? AND EndDate >= ?) " +
                    "OR (StartDate >= ? AND EndDate <= ?))";

=======
            
            // Check for blocked periods in CarAvailability
            String sqlAvailability = "SELECT COUNT(*) FROM CarAvailability " +
                     "WHERE CarID = ? AND IsAvailable = 0 " +
                     "AND ((StartDate <= ? AND EndDate >= ?) " +
                     "OR (StartDate <= ? AND EndDate >= ?) " +
                     "OR (StartDate >= ? AND EndDate <= ?))";
            
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
            stm = connection.prepareStatement(sqlAvailability);
            stm.setInt(1, carId);
            stm.setDate(2, dropoffDate);
            stm.setDate(3, dropoffDate);
            stm.setDate(4, pickupDate);
            stm.setDate(5, pickupDate);
            stm.setDate(6, pickupDate);
            stm.setDate(7, dropoffDate);
<<<<<<< HEAD

=======
            
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
            rs = stm.executeQuery();
            if (rs.next()) {
                int blockedCount = rs.getInt(1);
                if (blockedCount > 0) {
                    return false; // Has blocked periods
                }
            }
<<<<<<< HEAD

            // Check for active maintenance (In Progress or Scheduled)
            String sqlMaintenance = "SELECT COUNT(*) FROM MaintenanceRecords " +
                    "WHERE CarID = ? " +
                    "AND Status IN ('Scheduled', 'In Progress') " +
                    "AND ((CAST(ServiceDate AS DATE) <= ? AND CAST(ISNULL(NextServiceDate, ServiceDate) AS DATE) >= ?) "
                    +
                    "OR (CAST(ServiceDate AS DATE) <= ? AND CAST(ISNULL(NextServiceDate, ServiceDate) AS DATE) >= ?) " +
                    "OR (CAST(ServiceDate AS DATE) >= ? AND CAST(ServiceDate AS DATE) <= ?))";

=======
            
            // Check for active maintenance (In Progress or Scheduled)
            String sqlMaintenance = "SELECT COUNT(*) FROM MaintenanceRecords " +
                     "WHERE CarID = ? " +
                     "AND Status IN ('Scheduled', 'In Progress') " +
                     "AND ((CAST(ServiceDate AS DATE) <= ? AND CAST(ISNULL(NextServiceDate, ServiceDate) AS DATE) >= ?) " +
                     "OR (CAST(ServiceDate AS DATE) <= ? AND CAST(ISNULL(NextServiceDate, ServiceDate) AS DATE) >= ?) " +
                     "OR (CAST(ServiceDate AS DATE) >= ? AND CAST(ServiceDate AS DATE) <= ?))";
            
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
            stm = connection.prepareStatement(sqlMaintenance);
            stm.setInt(1, carId);
            stm.setDate(2, dropoffDate);
            stm.setDate(3, dropoffDate);
            stm.setDate(4, pickupDate);
            stm.setDate(5, pickupDate);
            stm.setDate(6, pickupDate);
            stm.setDate(7, dropoffDate);
<<<<<<< HEAD

=======
            
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
            rs = stm.executeQuery();
            if (rs.next()) {
                int maintenanceCount = rs.getInt(1);
                if (maintenanceCount > 0) {
                    return false; // Has maintenance scheduled
                }
            }
<<<<<<< HEAD

            return true; // Available if no conflicts and not blocked

        } catch (SQLException e) {
            System.out.println("Error checking car availability: " + e.getMessage());
        }

        return true; // Default to available if error
    }

    /**
     * Filter cars by location
     * 
     * @param cars            List of cars to filter
     * @param pickupLocation  Pickup location (optional)
=======
            
            return true; // Available if no conflicts and not blocked
            
        } catch (SQLException e) {
            System.out.println("Error checking car availability: " + e.getMessage());
        }
        
        return true; // Default to available if error
    }
    
    /**
     * Filter cars by location
     * @param cars List of cars to filter
     * @param pickupLocation Pickup location (optional)
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
     * @param dropoffLocation Dropoff location (optional)
     * @return Filtered list of cars
     */
    private List<Car> filterByLocation(List<Car> cars, String pickupLocation, String dropoffLocation) {
<<<<<<< HEAD
        if ((pickupLocation == null || pickupLocation.trim().isEmpty()) &&
                (dropoffLocation == null || dropoffLocation.trim().isEmpty())) {
            return cars;
        }

=======
        if ((pickupLocation == null || pickupLocation.trim().isEmpty()) && 
            (dropoffLocation == null || dropoffLocation.trim().isEmpty())) {
            return cars;
        }
        
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
        List<Car> filteredCars = new ArrayList<>();
        for (Car car : cars) {
            String carLocation = car.getLocation();
            if (carLocation != null) {
<<<<<<< HEAD
                boolean matchPickup = (pickupLocation == null || pickupLocation.trim().isEmpty() ||
                        carLocation.toLowerCase().contains(pickupLocation.toLowerCase()));
                boolean matchDropoff = (dropoffLocation == null || dropoffLocation.trim().isEmpty() ||
                        carLocation.toLowerCase().contains(dropoffLocation.toLowerCase()));

=======
                boolean matchPickup = (pickupLocation == null || pickupLocation.trim().isEmpty() || 
                                      carLocation.toLowerCase().contains(pickupLocation.toLowerCase()));
                boolean matchDropoff = (dropoffLocation == null || dropoffLocation.trim().isEmpty() || 
                                       carLocation.toLowerCase().contains(dropoffLocation.toLowerCase()));
                
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
                if (matchPickup && matchDropoff) {
                    filteredCars.add(car);
                }
            }
        }
<<<<<<< HEAD

        return filteredCars;
    }

    /**
     * Parse date string from various formats
     * 
=======
        
        return filteredCars;
    }
    
    /**
     * Parse date string from various formats
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
     * @param dateStr Date string (yyyy-MM-dd or dd/MM/yyyy)
     * @return SQL Date
     */
    private java.sql.Date parseDateString(String dateStr) {
        try {
            // Try yyyy-MM-dd format first
            if (dateStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
                return java.sql.Date.valueOf(dateStr);
            }
<<<<<<< HEAD

=======
            
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
            // Try dd/MM/yyyy format
            if (dateStr.matches("\\d{2}/\\d{2}/\\d{4}")) {
                String[] parts = dateStr.split("/");
                String isoDate = parts[2] + "-" + parts[1] + "-" + parts[0];
                return java.sql.Date.valueOf(isoDate);
            }
<<<<<<< HEAD

=======
            
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
            // Default: try direct conversion
            return java.sql.Date.valueOf(dateStr);
        } catch (Exception e) {
            System.out.println("Error parsing date: " + dateStr + " - " + e.getMessage());
            return null;
        }
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
