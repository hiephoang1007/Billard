/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

/**
 *
 * @author Administrator
 */
public class MatHang {
      private int MaMatHang;
    private String TenMatHang;
    private float Gia;
    private int MaLoai;

    public void setMaMatHang(int MaMatHang) {
        this.MaMatHang = MaMatHang;
    }

    public void setTenMatHang(String TenMatHang) {
        this.TenMatHang = TenMatHang;
    }

    public void setGia(float Gia) {
        this.Gia = Gia;
    }

    public void setMaLoai(int MaLoai) {
        this.MaLoai = MaLoai;
    }

    public int getMaMatHang() {
        return MaMatHang;
    }

    public String getTenMatHang() {
        return TenMatHang;
    }

    public float getGia() {
        return Gia;
    }

    public int getMaLoai() {
        return MaLoai;
    }

    public MatHang(int MaMatHang, String TenMatHang, float Gia, int MaLoai) {
        this.MaMatHang = MaMatHang;
        this.TenMatHang = TenMatHang;
        this.Gia = Gia;
        this.MaLoai = MaLoai;
    }

    public MatHang() {
    }
}
