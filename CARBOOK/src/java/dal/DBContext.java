<<<<<<< HEAD
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
=======
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
<<<<<<< HEAD

/**
 *
 * @author Acer
 */
public class DBContext {
    protected Connection connection;
    public DBContext()
    {
        try {
            // Edit URL , username, password to authenticate with your MS SQL Server
            String url = "jdbc:sqlserver://localhost:1433;databaseName=CRMS_DB;encrypt=false";
            String username = "sa";
            String password = "123";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    }
public static void main(String[] args) {
    DBContext db = new DBContext();
    try {
        if (db.connection != null && !db.connection.isClosed()) {
            System.out.println("✅ Kết nối OK và đang hoạt động");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}

=======
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBContext {

  protected Connection connection;

    public DBContext() {
        try {

            String user = "sa";
            String pass = "123456";
          String url = "jdbc:sqlserver://localhost:1433;databaseName=CRMS_DB;encrypt=false;trustServerCertificate=true";



            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                // In ra console để bạn theo dõi lúc debug, khi xong có thể xóa dòng này
                System.out.println(">>> Đã đóng kết nối Database thành công!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   public static void main(String[] args) {
    DBContext a = new DBContext();
    if (a.connection != null) {
        System.out.println("Kết nối thành công!");
    } else {
        System.out.println("Kết nối thất bại!");
    }
}

}
>>>>>>> 9f0cc680ef36485b50734f948fd8b5fe4c8b52b8
