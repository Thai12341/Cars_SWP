package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBContext {

  protected Connection connection;

    public DBContext() {
        try {
            String user = "sa";
            String pass = "123456";
           String url = "jdbc:sqlserver://localhost:1433;databaseName=CRMS_DB";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
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