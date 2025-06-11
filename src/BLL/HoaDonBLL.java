/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import Config.ConnectDB;
import DAL.ChiTietDonHang;

import DAL.DonHang;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.sql.ResultSet;
/**
 *
 * @author Windows
 */
public class HoaDonBLL {
     LocalDateTime thoiGianDat = LocalDateTime.now();
    public boolean insertHoaDon(DonHang hd) {
          ConnectDB connection= new ConnectDB();
        Connection con = connection.getConnection();
        String sql = "INSERT INTO donhang ( ThoiGianDat,MaBan, TongTien ) VALUES (  ?,?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setTimestamp(1, Timestamp.valueOf(thoiGianDat));
               ps.setFloat(2, hd.getMaBan());
            ps.setFloat(3, hd.getTongTien());
           
            int rows = ps.executeUpdate();
            ps.close();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public int selectNewBill() {
    int maDonHangMoi = -1; // giá trị mặc định nếu không tìm thấy

    try {
        ConnectDB connection = new ConnectDB();
        Connection con = connection.getConnection();

        String sql = "SELECT TOP 1 MaDonHang FROM DonHang ORDER BY MaDonHang DESC";
        PreparedStatement pst = con.prepareStatement(sql);

        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            maDonHangMoi = rs.getInt("MaDonHang");
        }

        rs.close();
        pst.close();
        con.close();
    } catch (Exception e) {
        e.printStackTrace();
    }

    return maDonHangMoi;
}

}
