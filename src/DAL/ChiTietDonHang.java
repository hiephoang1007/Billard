/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

/**
 *
 * @author Windows
 */
public class ChiTietDonHang {
    private int MaChiTiet;
    private int MaDonHang;
    private int MaMatHang;
    private int SoLuong;
    private float  ThanhTien;
        public ChiTietDonHang() {
    }

    public ChiTietDonHang(int MaChiTiet, int MaDonHang, int MaMatHang, int SoLuong, float ThanhTien) {
        this.MaChiTiet = MaChiTiet;
        this.MaDonHang = MaDonHang;
        this.MaMatHang = MaMatHang;
        this.SoLuong = SoLuong;
        this.ThanhTien = ThanhTien;
    }

    public int getMaChiTiet() {
        return MaChiTiet;
    }

    public int getMaDonHang() {
        return MaDonHang;
    }

    public int getMaMatHang() {
        return MaMatHang;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public float getThanhTien() {
        return ThanhTien;
    }

    public void setMaChiTiet(int MaChiTiet) {
        this.MaChiTiet = MaChiTiet;
    }

    public void setMaDonHang(int MaDonHang) {
        this.MaDonHang = MaDonHang;
    }

    public void setMaMatHang(int MaMatHang) {
        this.MaMatHang = MaMatHang;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public void setThanhTien(float ThanhTien) {
        this.ThanhTien = ThanhTien;
    }
        
}
