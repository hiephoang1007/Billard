/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Administrator
 */
import java.time.Duration;
import java.time.LocalDateTime;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import BLL.MatHangBLL;
import DAL.MatHang;
import java.util.List;
import java.sql.SQLException;
public class FromChiTietBan extends JFrame {
    private LocalDateTime gioBatDau;
    private LocalDateTime gioKetThuc;
    private int soBan;
    private Timer timer;
    private JButton btnLienKet;
    private Color originalBgColor;
    private Color originalFgColor;

    public void FromChiTietBan (){
         initComponents();
        loadDataToTable();
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

        jButton1.addActionListener(e -> themMon());
        jButton2.addActionListener(e -> suaMon());
        jButton3.addActionListener(e -> xoaMon());
        jButton4.addActionListener(e -> chonMon());
        tblMondamua.setModel(new javax.swing.table.DefaultTableModel(
    new Object [][] {},
    new String [] { "MaMatHang", "TenMatHang", "Gia", "SoLuong" }
));

    }

    private void batDau() {
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
        double giaTien = (phut / 60.0) * 45000;
        JOptionPane.showMessageDialog(this, "Tổng tiền: " + String.format("%.0f", giaTien) + " đ");
    }

    private void themMon() {
    JTextField txtMaMatHang = new JTextField();
    JTextField txtTenMatHang = new JTextField();
    JTextField txtGia = new JTextField();
    JTextField txtMaLoai = new JTextField();

    Object[] message = {
        "Mã mặt hàng:", txtMaMatHang,
        "Tên mặt hàng:", txtTenMatHang,
        "Giá:", txtGia,
        "Mã loại:", txtMaLoai
    };

    int option = JOptionPane.showConfirmDialog(this, message, "Thêm món", JOptionPane.OK_CANCEL_OPTION);
    if (option == JOptionPane.OK_OPTION) {
        String maMatHang = txtMaMatHang.getText().trim();
        String tenMatHang = txtTenMatHang.getText().trim();
        String giaStr = txtGia.getText().trim();
        String maLoai = txtMaLoai.getText().trim();

        // Kiểm tra rỗng
        if (maMatHang.isEmpty() || tenMatHang.isEmpty() || giaStr.isEmpty() || maLoai.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        double gia;
        try {
            gia = Double.parseDouble(giaStr);
            if (gia < 0) {
                JOptionPane.showMessageDialog(this, "Giá phải >= 0!");
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Giá không hợp lệ!");
            return;
        }

        // Thêm vào bảng tblMon (đúng 4 cột)
        DefaultTableModel model = (DefaultTableModel) tblMon.getModel();
        model.addRow(new Object[]{maMatHang, tenMatHang, gia, maLoai});
    }
}


    private void suaMon() {
    int row = tblMon.getSelectedRow();

    if (row == -1) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng trong menu để sửa.");
        return;
    }

    DefaultTableModel model = (DefaultTableModel) tblMon.getModel();

    // Lấy dữ liệu cũ
    String maMatHangCu = model.getValueAt(row, 0).toString();
    String tenMatHangCu = model.getValueAt(row, 1).toString();
    String giaCu = model.getValueAt(row, 2).toString();
    String maLoaiCu = model.getValueAt(row, 3).toString();

    // Tạo ô nhập mới
    JTextField txtMaMatHang = new JTextField(maMatHangCu);
    JTextField txtTenMatHang = new JTextField(tenMatHangCu);
    JTextField txtGia = new JTextField(giaCu);
    JTextField txtMaLoai = new JTextField(maLoaiCu);

    Object[] message = {
        "Mã mặt hàng:", txtMaMatHang,
        "Tên mặt hàng:", txtTenMatHang,
        "Giá:", txtGia,
        "Mã loại:", txtMaLoai
    };

    int option = JOptionPane.showConfirmDialog(this, message, "Sửa món", JOptionPane.OK_CANCEL_OPTION);
    if (option == JOptionPane.OK_OPTION) {
        String maMatHang = txtMaMatHang.getText().trim();
        String tenMatHang = txtTenMatHang.getText().trim();
        String giaStr = txtGia.getText().trim();
        String maLoai = txtMaLoai.getText().trim();

        // Kiểm tra rỗng
        if (maMatHang.isEmpty() || tenMatHang.isEmpty() || giaStr.isEmpty() || maLoai.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        double gia;
        try {
            gia = Double.parseDouble(giaStr);
            if (gia < 0) {
                JOptionPane.showMessageDialog(this, "Giá phải >= 0!");
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Giá không hợp lệ!");
            return;
        }

        // Cập nhật lại bảng
        model.setValueAt(maMatHang, row, 0);
        model.setValueAt(tenMatHang, row, 1);
        model.setValueAt(gia, row, 2);
        model.setValueAt(maLoai, row, 3);
    }
}

    private void xoaMon() {
        int row = tblMondamua.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để xóa.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa món này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            DefaultTableModel model = (DefaultTableModel) tblMondamua.getModel();
            model.removeRow(row);
        }
    }

    private void chonMon() {
    int selectedRow = tblMon.getSelectedRow();  // <-- lấy từ bảng menu

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn một món trong menu!");
        return;
    }

    String maMon = tblMon.getValueAt(selectedRow, 0).toString();
    String tenMon = tblMon.getValueAt(selectedRow, 1).toString();
    double gia = Double.parseDouble(tblMon.getValueAt(selectedRow, 2).toString());

    String soLuongStr = JOptionPane.showInputDialog(this, "Nhập số lượng cho món: " + tenMon, "Nhập số lượng", JOptionPane.PLAIN_MESSAGE);

    if (soLuongStr != null && !soLuongStr.trim().isEmpty()) {
        try {
            int soLuong = Integer.parseInt(soLuongStr.trim());
            if (soLuong <= 0) {
                JOptionPane.showMessageDialog(this, "Số lượng phải > 0!");
                return;
            }

            DefaultTableModel modelHoaDon = (DefaultTableModel) tblMondamua.getModel();  // <-- bảng hóa đơn
            modelHoaDon.addRow(new Object[]{maMon, tenMon, gia, soLuong});

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
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
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
        jSeparator2 = new javax.swing.JSeparator();
        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMon = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTenBan.setFont(new java.awt.Font("Showcard Gothic", 2, 48)); // NOI18N
        lblTenBan.setText("Chi tiet bAn");

        btnBatDau.setText("Bắt đầu");

        btnKetThuc.setText("Kết thúc");

        btnTinhThoiGian.setText("Tính thời gian");

        btnTinhTien.setText("Tính tiền");

        lblThoiGian.setText("Thời gian vào");

        jButton1.setText("Thêm ");

        jButton2.setText("Sửa");

        jButton3.setText("Xóa");

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(523, Short.MAX_VALUE))
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
                    .addComponent(jLabel4))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                        .addComponent(lblTongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(73, 73, 73))
        );

        jSeparator2.setForeground(new java.awt.Color(255, 153, 153));

        jButton4.setText("Chọn món");

        tblMon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Menu", "Giá"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblMon);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
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
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cboLoaiMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jButton1)
                                                .addComponent(jButton2)
                                                .addComponent(jButton3)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(23, 23, 23))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addComponent(lblTenBan, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(189, 189, 189)))
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
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboLoaiMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(36, 36, 36)
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3)
                                .addGap(118, 118, 118)
                                .addComponent(jButton4))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboLoaiMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiMonActionPerformed
                                       
    
    }//GEN-LAST:event_cboLoaiMonActionPerformed
      
 public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FromChiTietBan(1, null).setVisible(true));
    }


    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatDau;
    private javax.swing.JButton btnKetThuc;
    private javax.swing.JButton btnTinhThoiGian;
    private javax.swing.JButton btnTinhTien;
    private javax.swing.JComboBox<String> cboLoaiMon;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
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
    private javax.swing.JTable tblMon;
    private javax.swing.JTable tblMondamua;
    // End of variables declaration//GEN-END:variables
}
