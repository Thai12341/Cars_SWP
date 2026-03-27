package dal;

import model.CarBrand;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
<<<<<<< HEAD
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * CarBrandDAO - Data Access Object for Car Brand management
 */
public class CarBrandDAO extends DBContext {
    
    /**
     * Get all car brands
     * @return List of all brands
     */
    public List<CarBrand> getAllBrands() {
        List<CarBrand> brands = new ArrayList<>();
        String sql = "SELECT * FROM CarBrands ORDER BY BrandName";
        
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            
=======
import java.util.ArrayList;
import java.util.List;

public class CarBrandDAO extends DBContext {

    //true neu dang dc dung
    public boolean hasModels(int brandId) {
        String sql = "SELECT COUNT(*) FROM CarModels WHERE BrandID = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, brandId);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error checking hasModels: " + e.getMessage());
        }
        return false;
    }

 
    public boolean deleteBrand(int brandId) {
       
        String sql = "DELETE FROM CarBrands WHERE BrandID = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, brandId);
            return stm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error deleteBrand: " + e.getMessage());
        }
        return false;
    }


    public List<CarBrand> getAllBrands() {
        List<CarBrand> brands = new ArrayList<>();
        String sql = "SELECT * FROM CarBrands ORDER BY BrandName";
        try (PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
            while (rs.next()) {
                brands.add(extractBrandFromResultSet(rs));
            }
        } catch (SQLException e) {
<<<<<<< HEAD
            System.out.println("Error getting all brands: " + e.getMessage());
        }
        return brands;
    }
    
    /**
     * Get brand by ID
     * @param brandId
     * @return CarBrand object or null
     */
    public CarBrand getBrandById(int brandId) {
        String sql = "SELECT * FROM CarBrands WHERE BrandID = ?";
        
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, brandId);
            ResultSet rs = stm.executeQuery();
            
            if (rs.next()) {
                return extractBrandFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error getting brand by ID: " + e.getMessage());
        }
        return null;
    }
    
    /**
     * Get brand by name
     * @param brandName
     * @return CarBrand object or null
     */
    public CarBrand getBrandByName(String brandName) {
        String sql = "SELECT * FROM CarBrands WHERE BrandName = ?";
        
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, brandName);
            ResultSet rs = stm.executeQuery();
            
            if (rs.next()) {
                return extractBrandFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error getting brand by name: " + e.getMessage());
        }
        return null;
    }
    
    /**
     * Create a new brand
     * @param brand
     * @return true if successful, false otherwise
     */
    public boolean createBrand(CarBrand brand) {
        String sql = "INSERT INTO CarBrands (BrandName, Country, LogoURL) VALUES (?, ?, ?)";
        
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, brand.getBrandName());
            stm.setString(2, brand.getCountry());
            stm.setString(3, brand.getLogoURL());
            
            return stm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error creating brand: " + e.getMessage());
        }
        return false;
    }
    
    /**
     * Update brand
     * @param brand
     * @return true if successful, false otherwise
     */
    public boolean updateBrand(CarBrand brand) {
        String sql = "UPDATE CarBrands SET BrandName = ?, Country = ?, LogoURL = ? WHERE BrandID = ?";
        
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
=======
            System.err.println("Error getAllBrands: " + e.getMessage());
        }
        return brands;
    }


    public CarBrand getBrandById(int brandId) {
        String sql = "SELECT * FROM CarBrands WHERE BrandID = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, brandId);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return extractBrandFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getBrandById: " + e.getMessage());
        }
        return null;
    }

    
    public boolean createBrand(CarBrand brand) {
        String sql = "INSERT INTO CarBrands (BrandName, Country, LogoURL) VALUES (?, ?, ?)";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, brand.getBrandName());
            stm.setString(2, brand.getCountry());
            stm.setString(3, brand.getLogoURL());
            return stm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error createBrand: " + e.getMessage());
        }
        return false;
    }

   
    public boolean updateBrand(CarBrand brand) {
        String sql = "UPDATE CarBrands SET BrandName = ?, Country = ?, LogoURL = ? WHERE BrandID = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
            stm.setString(1, brand.getBrandName());
            stm.setString(2, brand.getCountry());
            stm.setString(3, brand.getLogoURL());
            stm.setInt(4, brand.getBrandId());
<<<<<<< HEAD
            
            return stm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating brand: " + e.getMessage());
        }
        return false;
    }
    
    /**
     * Delete brand
     * @param brandId
     * @return true if successful, false otherwise
     */
    public boolean deleteBrand(int brandId) {
        String sql = "DELETE FROM CarBrands WHERE BrandID = ?";
        
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, brandId);
            
            return stm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting brand: " + e.getMessage());
        }
        return false;
    }
    
    /**
     * Extract CarBrand object from ResultSet
     * @param rs
     * @return CarBrand object
     * @throws SQLException
     */
=======
            return stm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updateBrand: " + e.getMessage());
        }
        return false;
    }

>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
    private CarBrand extractBrandFromResultSet(ResultSet rs) throws SQLException {
        CarBrand brand = new CarBrand();
        brand.setBrandId(rs.getInt("BrandID"));
        brand.setBrandName(rs.getString("BrandName"));
        brand.setCountry(rs.getString("Country"));
        brand.setLogoURL(rs.getString("LogoURL"));
<<<<<<< HEAD
        brand.setCreatedAt(rs.getTimestamp("CreatedAt"));
        return brand;
    }
=======
        brand.setCreatedAt(rs.getTimestamp("CreatedAt")); 
        return brand;
    }

public List<CarBrand> searchByName(String name) {
    List<CarBrand> brands = new ArrayList<>();
    String sql = "SELECT * FROM CarBrands WHERE BrandName LIKE ? ORDER BY BrandName";
    try (PreparedStatement stm = connection.prepareStatement(sql)) {
        stm.setString(1, "%" + name + "%");
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            brands.add(extractBrandFromResultSet(rs));
        }
    } catch (SQLException e) { e.printStackTrace(); }
    return brands;
}


public List<CarBrand> searchByCountry(String country) {
    List<CarBrand> brands = new ArrayList<>();
    String sql = "SELECT * FROM CarBrands WHERE Country LIKE ? ORDER BY BrandName";
    try (PreparedStatement stm = connection.prepareStatement(sql)) {
        stm.setString(1, "%" + country + "%");
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            brands.add(extractBrandFromResultSet(rs));
        }
    } catch (SQLException e) { e.printStackTrace(); }
    return brands;
}



public boolean isBrandNameExists(String brandName) {
    String sql = "SELECT COUNT(*) FROM CarBrands WHERE BrandName = ?";
    try (PreparedStatement stm = connection.prepareStatement(sql)) {
        stm.setString(1, brandName.trim());
        try (ResultSet rs = stm.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
    } catch (SQLException e) {
        System.err.println("Error isBrandNameExists: " + e.getMessage());
    }
    return false;
}


public boolean isBrandNameExists(String brandName, int excludeId) {
    String sql = "SELECT COUNT(*) FROM CarBrands WHERE BrandName = ? AND BrandID != ?";
    try (PreparedStatement stm = connection.prepareStatement(sql)) {
        stm.setString(1, brandName.trim());
        stm.setInt(2, excludeId);
        try (ResultSet rs = stm.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
    } catch (SQLException e) {
        System.err.println("Error isBrandNameExists with excludeId: " + e.getMessage());
    }
    return false;
}

>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
}
