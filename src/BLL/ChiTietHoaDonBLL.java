/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;
import Config.ConnectDB;
import DAL.ChiTietDonHang;
import DAL.DonHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Windows
 */
public class ChiTietHoaDonBLL {
    
 public List<DAL.ChiTietDonHang> getChiTietDonHangTheoMa(int maDonHang) throws SQLException {
    List<DAL.ChiTietDonHang> products = new ArrayList<>();
    ConnectDB db = new ConnectDB();
    Connection conn = db.getConnection();

    if (conn == null) {
        System.out.println("❌ Kết nối thất bại, conn = null tại getChiTietDonHangTheoMa");
        return products;
    }

    String sql = "SELECT * FROM ChiTietDonHang WHERE MaDonHang = ?";

    try (
        PreparedStatement pst = conn.prepareStatement(sql)
    ) {
        pst.setInt(1, maDonHang);  // Gán giá trị cho ?
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            DAL.ChiTietDonHang product = new DAL.ChiTietDonHang(
                rs.getInt("MaChiTiet"),
                rs.getInt("MaDonHang"),
                rs.getInt("MaMatHang"),
                rs.getInt("SoLuong"),
                rs.getFloat("ThanhTien")
            );
            products.add(product);
        }

        rs.close();
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        conn.close();
    }

    return products;
}

      public boolean insertDetailBill (ChiTietDonHang ctdh) {
          ConnectDB connection= new ConnectDB();
        Connection con = connection.getConnection();
        String sql = "INSERT INTO chitietdonhang ( madonhang, mamathang,soluong,thanhtien) VALUES ( ?,?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,ctdh.getMaDonHang() );
            ps.setInt(2,ctdh.getMaMatHang());
            ps.setInt(3,ctdh.getSoLuong());
            ps.setFloat(4,ctdh.getThanhTien());
            int rows = ps.executeUpdate();
            ps.close();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
      
       public boolean deleteDetailBill (int MaChiTiet) throws SQLException {
        ConnectDB connection= new ConnectDB();
        Connection conn = connection.getConnection();
         String sql = "DELETE FROM ChiTietDonHang WHERE MaChiTiet=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, MaChiTiet);
        int rows = ps.executeUpdate();
        ps.close();
        conn.close();
        return rows > 0;
     }
      
      public float tinhTongTienCongThem(int maDonHang, float phuThu) {
    float tongTien = 0.0f;

    try {
        ConnectDB db = new ConnectDB();
        Connection conn = db.getConnection();
        String sql = "SELECT SUM(ThanhTien) + ? AS TongTien FROM ChiTietDonHang WHERE MaDonHang = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setFloat(1, phuThu);      // số cần cộng thêm
        pst.setInt(2, maDonHang);     // mã đơn hàng

        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            tongTien = rs.getFloat("TongTien");
        }

        rs.close();
        pst.close();
        conn.close();
    } catch (Exception e) {
        e.printStackTrace();
    }

     return tongTien;
    }

}
