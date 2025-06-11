/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Administrator
 */
import BLL.ChiTietHoaDonBLL;
import BLL.HoaDonBLL;
import java.time.Duration;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import BLL.MatHangBLL;
import DAL.ChiTietDonHang;
import DAL.MatHang;
import DAL.DonHang;
import java.time.LocalDateTime;
import java.util.List;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class FromChiTietBan extends JFrame {
    private LocalDateTime gioBatDau;
    private LocalDateTime gioKetThuc;
    private int soBan;
    private int maHoaDonMoi;
    private Timer timer;
    private long totalPlay;
    private float totalTimeMoney;
    private JButton btnLienKet;
    private Color originalBgColor;
    private Color originalFgColor;

    public void FromChiTietBan (){
         initComponents();
        loadDataToTable();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
    private void loadDataToTable() {
    try {
         tblMon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "MaMatHang", "TenMatHang", "Gia", "MaLoai"
            }
        ));
        MatHangBLL dao = new MatHangBLL();
        List<MatHang> list = dao.getAllProduct();
          System.out.println(list.size());
        DefaultTableModel model = (DefaultTableModel) tblMon.getModel();
        model.setRowCount(0); 
        for (MatHang b : list) {
        System.out.println(b); // In ra Book nhờ toString()
        model.addRow(new Object[]{
        b.getMaMatHang(), b.getTenMatHang(),b.getGia(),b.getMaLoai()
        });
    }
    } catch  (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi load dữ liệu!");
    }
    }
    private void loadDataToTableBill() {
    try {
         tblMondamua.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
              "MaChiTiet","MaDonHang",  "MaMatHang", "SoLuong", "ThanhTien"
            }
        ));
        ChiTietHoaDonBLL dao = new ChiTietHoaDonBLL();
        List<ChiTietDonHang> list = dao.getChiTietDonHangTheoMa(maHoaDonMoi);
          System.out.println(list.size());
        DefaultTableModel model = (DefaultTableModel) tblMondamua.getModel();
        model.setRowCount(0); 
        for (ChiTietDonHang b : list) {
        System.out.println(b); // In ra Book nhờ toString()
        model.addRow(new Object[]{
        b.getMaChiTiet(),b.getMaDonHang(),b.getMaMatHang(), b.getSoLuong(),b.getThanhTien()
        });
    }
    } catch  (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi load dữ liệu!");
    }
    }
    
    public FromChiTietBan(int soBan, JButton btnLienKet) {
           System.out.println("soban"+soBan);
        this.soBan = soBan;
        this.btnLienKet = btnLienKet;
        if (btnLienKet != null) {
            originalBgColor = btnLienKet.getBackground();
            originalFgColor = btnLienKet.getForeground();
        }
          initComponents();
        setTitle("Chi tiết Bàn " + soBan);
        lblTenBan.setText("CHI TIẾT BÀN " + soBan);
 loadDataToTable();
        btnBatDau.addActionListener(e -> batDau());
        btnKetThuc.addActionListener(e -> ketThuc());
        btnTinhThoiGian.addActionListener(e -> tinhThoiGian());
        btnTinhTien.addActionListener(e -> tinhTien());

        
        jButton4.addActionListener(e -> chonMon());
        tblMondamua.setModel(new javax.swing.table.DefaultTableModel(
    new Object [][] {},
    new String [] { "MaMatHang", "TenMatHang", "Gia", "SoLuong" }
));

    }

    private void batDau() {
        LocalDateTime thoiGianDat = LocalDateTime.now();
        HoaDonBLL Bill =new HoaDonBLL();
        DonHang hd = new DonHang(0,thoiGianDat,soBan,0);
        
        gioBatDau = LocalDateTime.now();
        gioKetThuc = null;
        JOptionPane.showMessageDialog(this, "Đã bắt đầu lúc: " + gioBatDau);

        if (btnLienKet != null) {
            btnLienKet.setBackground(Color.GREEN);
            btnLienKet.setForeground(Color.WHITE);
            btnLienKet.setOpaque(true);
        }

        if (timer != null && timer.isRunning()) timer.stop();

        timer = new Timer(1000, e -> {
            if (gioBatDau != null) {
                Duration duration = Duration.between(gioBatDau, LocalDateTime.now());
                long phut = duration.toMinutes();
                long giay = duration.getSeconds() % 60;
                lblThoiGian.setText("Thời gian: " + phut + " phút " + giay + " giây");
            }
        });
        Bill.insertHoaDon(hd);
        maHoaDonMoi=Bill.selectNewBill();
        System.out.println("ma hoa don moi"+maHoaDonMoi);
        timer.start();
    }

    private void ketThuc() {
        if (gioBatDau == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhấn 'Bắt đầu' trước.");
            return;
        }

        gioKetThuc = LocalDateTime.now();
        if (timer != null && timer.isRunning()) timer.stop();
        JOptionPane.showMessageDialog(this, "Đã kết thúc lúc: " + gioKetThuc);

        if (btnLienKet != null) {
            btnLienKet.setBackground(originalBgColor);
            btnLienKet.setForeground(originalFgColor);
            btnLienKet.setOpaque(true);
            btnLienKet.setContentAreaFilled(true);
            btnLienKet.repaint();
        }
    }

    private void tinhThoiGian() {
        if (gioBatDau == null || gioKetThuc == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhấn 'Bắt đầu' và 'Kết thúc' trước.");
            return;
        }
        Duration duration = Duration.between(gioBatDau, gioKetThuc);
        long phut = duration.toMinutes();
        JOptionPane.showMessageDialog(this, "Thời gian chơi: " + phut + " phút");
    }

    private void tinhTien() {
        if (gioBatDau == null || gioKetThuc == null) {
            JOptionPane.showMessageDialog(this, "Chưa đủ thông tin để tính tiền.");
            return;
        }
        Duration duration = Duration.between(gioBatDau, gioKetThuc);
        long phut = duration.toMinutes();
        totalPlay = phut;
        
        float  giaTien = (phut / 60f) * 45000;
        totalTimeMoney= giaTien;
        moneyBill.setText(String.valueOf(giaTien));
         ChiTietHoaDonBLL detailBill = new ChiTietHoaDonBLL();
       float totalBill=  detailBill.tinhTongTienCongThem(maHoaDonMoi, totalTimeMoney);
      
        lblTotalBill.setText(String.format("%.0f", totalBill));
        JOptionPane.showMessageDialog(this, "Tổng tiền thời gian chơi chưa tính đồ uống : " + String.format("%.0f", giaTien) + " đ");
    }

   
    private void chonMon() {
    ChiTietHoaDonBLL detailBill = new ChiTietHoaDonBLL();
    int selectedRow = tblMon.getSelectedRow();  // <-- lấy từ bảng menu

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn một món trong menu!");
        return;
    }

    String maMon = tblMon.getValueAt(selectedRow, 0).toString();
    String tenMon = tblMon.getValueAt(selectedRow, 1).toString();
    Float gia = Float.parseFloat(tblMon.getValueAt(selectedRow, 2).toString());

    String soLuongStr = JOptionPane.showInputDialog(this, "Nhập số lượng cho món: " + tenMon, "Nhập số lượng", JOptionPane.PLAIN_MESSAGE);

    if (soLuongStr != null && !soLuongStr.trim().isEmpty()) {
        try {
            int soLuong = Integer.parseInt(soLuongStr.trim());
            if (soLuong <= 0) {
                JOptionPane.showMessageDialog(this, "Số lượng phải > 0!");
                return;
            }
        Float thanhTien = gia*Float.parseFloat(soLuongStr);
        ChiTietDonHang detailBillDAL=new ChiTietDonHang(0,maHoaDonMoi,Integer.parseInt(maMon),Integer.parseInt(soLuongStr),thanhTien);
        detailBill.insertDetailBill(detailBillDAL);
       loadDataToTableBill();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}





    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTenBan = new javax.swing.JLabel();
        btnBatDau = new javax.swing.JButton();
        btnKetThuc = new javax.swing.JButton();
        btnTinhThoiGian = new javax.swing.JButton();
        btnTinhTien = new javax.swing.JButton();
        lblThoiGian = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cboLoaiMon = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblTongthoigian = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblTongtien = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMondamua = new javax.swing.JTable();
        moneyBill = new javax.swing.JLabel();
        lblTotalBill = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMon = new javax.swing.JTable();
        DeleteProduct = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTenBan.setFont(new java.awt.Font("Showcard Gothic", 2, 48)); // NOI18N
        lblTenBan.setText("Chi tiet bAn");

        btnBatDau.setText("Bắt đầu");

        btnKetThuc.setText("Kết thúc");

        btnTinhThoiGian.setText("Tính thời gian");

        btnTinhTien.setText("Tính tiền");
        btnTinhTien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTinhTienMouseClicked(evt);
            }
        });

        lblThoiGian.setText("Thời gian vào");

        jLabel1.setText("Đồ ăn uống");

        cboLoaiMon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Đồ ăn", "Đồ uống", "Thuốc lá" }));
        cboLoaiMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLoaiMonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 2, 48)); // NOI18N
        jLabel2.setText("HOA ĐON");

        jSeparator1.setForeground(new java.awt.Color(255, 153, 153));

        lblTongthoigian.setText("Tổng thời gian chơi :");

        jLabel5.setText("Món đã gọi :");

        lblTongtien.setText("Tổng tiền :");

        tblMondamua.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Menu", "Giá", "Số lượng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblMondamua);

        moneyBill.setText("jLabel3");

        lblTotalBill.setText("............");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblTongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTotalBill, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblTongthoigian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(59, 59, 59)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(moneyBill)
                                        .addGap(255, 255, 255)))))
                        .addGap(0, 517, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTongthoigian, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(moneyBill))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotalBill, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(73, 73, 73))
        );

        jSeparator2.setForeground(new java.awt.Color(255, 153, 153));

        jButton4.setText("Chọn món");

        tblMon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "MaMatHang", "TenMatHang", "Gia", "MaLoai"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Double.class, java.lang.Float.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblMon);

        DeleteProduct.setText("Xóa món");
        DeleteProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DeleteProductMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblThoiGian)
                                    .addComponent(btnBatDau))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnKetThuc)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTinhThoiGian)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTinhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboLoaiMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton4)
                                        .addGap(27, 27, 27)
                                        .addComponent(DeleteProduct))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(23, 23, 23))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(lblTenBan, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(lblTenBan, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnBatDau)
                            .addComponent(btnKetThuc)
                            .addComponent(btnTinhThoiGian)
                            .addComponent(btnTinhTien))
                        .addGap(18, 18, 18)
                        .addComponent(lblThoiGian)
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cboLoaiMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4)
                            .addComponent(DeleteProduct)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboLoaiMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiMonActionPerformed
                                       
    
    }//GEN-LAST:event_cboLoaiMonActionPerformed

    private void btnTinhTienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTinhTienMouseClicked
        // TODO add your handling code here: Total money
        
    }//GEN-LAST:event_btnTinhTienMouseClicked

    private void DeleteProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeleteProductMouseClicked
        // TODO add your handling code here:
        int selectedRow = tblMondamua.getSelectedRow();  
        ChiTietHoaDonBLL detailBill = new ChiTietHoaDonBLL();
          String maChiTietDonHang = tblMondamua.getValueAt(selectedRow, 0).toString();
        try {
            detailBill.deleteDetailBill(Integer.parseInt(maChiTietDonHang));
            loadDataToTableBill();
        } catch (SQLException ex) {
            Logger.getLogger(FromChiTietBan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_DeleteProductMouseClicked
      
 public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FromChiTietBan(1, null).setVisible(true));
    }


    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DeleteProduct;
    private javax.swing.JButton btnBatDau;
    private javax.swing.JButton btnKetThuc;
    private javax.swing.JButton btnTinhThoiGian;
    private javax.swing.JButton btnTinhTien;
    private javax.swing.JComboBox<String> cboLoaiMon;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblTenBan;
    private javax.swing.JLabel lblThoiGian;
    private javax.swing.JLabel lblTongthoigian;
    private javax.swing.JLabel lblTongtien;
    private javax.swing.JLabel lblTotalBill;
    private javax.swing.JLabel moneyBill;
    private javax.swing.JTable tblMon;
    private javax.swing.JTable tblMondamua;
    // End of variables declaration//GEN-END:variables
}
