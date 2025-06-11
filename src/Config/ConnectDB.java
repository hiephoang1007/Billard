/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Config;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
/**
 *
 * @author Administrator
 */
public class ConnectDB {

    public  Connection getConnection() {

        Connection c = null;
        try {
            // Đăng ký SQL Server Driver với DriverManager
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());

            // Các thông số kết nối

            String url = "jdbc:sqlserver://localhost:50267;databaseName=QuanLiQuanBida;encrypt=true;trustServerCertificate=true";

            String userName = "sa";
            String password = "123456";


            // Tạo kết nối
            c = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            // Xử lý ngoại lệ
            JOptionPane.showMessageDialog(null, "Không thể kết nối đến cơ sở dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return c;
    }

    public  void closeConnection(Connection c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  void printInfo(Connection c) {
        try {
            if (c != null) {
                DatabaseMetaData mtdt = c.getMetaData();
                System.out.println(mtdt.getDatabaseProductName());
                System.out.println(mtdt.getDatabaseProductVersion());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }  
   }
     public static void main(String[] args) {
        ConnectDB db = new ConnectDB(); // Tạo đối tượng
    Connection conn = db.getConnection(); // Gọi phươn
        if (conn != null) {
            System.out.println("✅ Kết nối thành công!");
        } else {
            System.out.println("❌ Kết nối thất bại.");
        }
        }
    
    
    
}
