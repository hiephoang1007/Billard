/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import Config.ConnectDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class MatHangBLL {
      public List<DAL.MatHang>  getAllProduct() throws SQLException {
        ConnectDB db = new ConnectDB();
        List<DAL.MatHang> products = new ArrayList<>();
        Connection conn = db.getConnection();
        if (conn == null) {
            System.out.println("❌ Kết nối thất bại, conn = null tại getAllProduct");
            return products;
        }

        String sql = "SELECT * FROM mathang";
       try (Statement stmt = conn.createStatement();
     ResultSet rs = stmt.executeQuery(sql)) {

    while (rs.next()) {
        DAL.MatHang product = new DAL.MatHang(
            rs.getInt("MaMatHang"),
            rs.getString("TenMatHang"),
            rs.getFloat("Gia"),
            rs.getInt("MaLoai")
          
        );
        products.add(product);
    }

} catch (SQLException e) {
    e.printStackTrace();
}
        return products;
    }
}
