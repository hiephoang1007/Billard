/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.time.LocalDateTime;

/**
 *
 * @author Windows
 */
public class DonHang {
    private int MaDonHang;
      private LocalDateTime thoiGianDat;
      private int MaBan;
    private float TongTien;

    public DonHang() {
    }

    public DonHang(int MaDonHang, LocalDateTime thoiGianDat, int MaBan, float TongTien) {
        this.MaDonHang = MaDonHang;
        this.thoiGianDat = thoiGianDat;
        this.MaBan = MaBan;
        this.TongTien = TongTien;
    }

    public int getMaDonHang() {
        return MaDonHang;
    }

    public LocalDateTime getThoiGianDat() {
        return thoiGianDat;
    }

    public int getMaBan() {
        return MaBan;
    }

    public float getTongTien() {
        return TongTien;
    }

    public void setMaDonHang(int MaDonHang) {
        this.MaDonHang = MaDonHang;
    }

    public void setThoiGianDat(LocalDateTime thoiGianDat) {
        this.thoiGianDat = thoiGianDat;
    }

    public void setMaBan(int MaBan) {
        this.MaBan = MaBan;
    }

    public void setTongTien(float TongTien) {
        this.TongTien = TongTien;
    }

   
    
}
