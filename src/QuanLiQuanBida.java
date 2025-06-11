/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Administrator
 */
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class QuanLiQuanBida extends javax.swing.JFrame {

    // Map lưu các form chi tiết bàn đã mở
    private Map<Integer, FromChiTietBan> mapFormsDaMo = new HashMap<>();

    public QuanLiQuanBida() {
        initComponents();
        addEventForButtons();
    }

      private void addEventForButtons() {
        javax.swing.JButton[] danhSachBan = {
            btnBan1, btnBan2, btnBan3, btnBan4, btnBan5,
            btnBan6, btnBan7, btnBan8, btnBan9, btnBan10,
            btnBan11, btnBan12, btnBan13, btnBan14, btnBan15,
            btnBan16, btnBan17, btnBan18, btnBan19, btnBan20,
            btnBan21, btnBan22, btnBan23, btnBan24, btnBan25
        };

        for (int i = 0; i < danhSachBan.length; i++) {
            
            final int soBan = i + 1;
    System.out.println("soban"+soBan);
            final JButton btn = danhSachBan[i];

            btn.addActionListener(e -> {
    if (mapFormsDaMo.containsKey(soBan)) {
        System.out.println("soban"+soBan);
        FromChiTietBan form = mapFormsDaMo.get(soBan);
           System.out.println("soban"+soBan);
        if (!form.isVisible()) {
            form.setVisible(true);
        } else {
            // Nếu form đã mở rồi, đảm bảo nó được đưa lên trên
            if (form.getExtendedState() == JFrame.ICONIFIED) {
                form.setExtendedState(JFrame.NORMAL); // Bỏ ẩn nếu bị minimize
            }
            form.toFront();
            form.requestFocus();
            form.repaint();
        }
    } else {
        FromChiTietBan form = new FromChiTietBan(soBan, btn);
        form.setVisible(true);
        mapFormsDaMo.put(soBan, form);

        form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        form.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                mapFormsDaMo.remove(soBan);
            }
        });
    }
});
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnBan1 = new javax.swing.JButton();
        btnBan2 = new javax.swing.JButton();
        btnBan3 = new javax.swing.JButton();
        btnBan4 = new javax.swing.JButton();
        btnBan5 = new javax.swing.JButton();
        btnBan6 = new javax.swing.JButton();
        btnBan7 = new javax.swing.JButton();
        btnBan8 = new javax.swing.JButton();
        btnBan9 = new javax.swing.JButton();
        btnBan10 = new javax.swing.JButton();
        btnBan11 = new javax.swing.JButton();
        btnBan12 = new javax.swing.JButton();
        btnBan13 = new javax.swing.JButton();
        btnBan14 = new javax.swing.JButton();
        btnBan15 = new javax.swing.JButton();
        btnBan16 = new javax.swing.JButton();
        btnBan17 = new javax.swing.JButton();
        btnBan18 = new javax.swing.JButton();
        btnBan19 = new javax.swing.JButton();
        btnBan20 = new javax.swing.JButton();
        btnBan21 = new javax.swing.JButton();
        btnBan22 = new javax.swing.JButton();
        btnBan23 = new javax.swing.JButton();
        btnBan24 = new javax.swing.JButton();
        btnBan25 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Stencil", 2, 48)); // NOI18N
        jLabel1.setText("HH ARENA CLUB");

        jPanel1.setLayout(new java.awt.GridLayout(5, 5, 10, 10));

        btnBan1.setBackground(new java.awt.Color(255, 153, 153));
        btnBan1.setText("BÀN 1");
        btnBan1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 51), 1, true));
        jPanel1.add(btnBan1);

        btnBan2.setBackground(new java.awt.Color(255, 153, 153));
        btnBan2.setText("BÀN 2");
        jPanel1.add(btnBan2);

        btnBan3.setBackground(new java.awt.Color(255, 153, 153));
        btnBan3.setText("BÀN 3");
        jPanel1.add(btnBan3);

        btnBan4.setBackground(new java.awt.Color(255, 153, 153));
        btnBan4.setText("BÀN 4");
        jPanel1.add(btnBan4);

        btnBan5.setBackground(new java.awt.Color(255, 153, 153));
        btnBan5.setText("BÀN 5");
        jPanel1.add(btnBan5);

        btnBan6.setBackground(new java.awt.Color(255, 153, 153));
        btnBan6.setText("BÀN 6");
        jPanel1.add(btnBan6);

        btnBan7.setBackground(new java.awt.Color(255, 153, 153));
        btnBan7.setText("BÀN 7");
        jPanel1.add(btnBan7);

        btnBan8.setBackground(new java.awt.Color(255, 153, 153));
        btnBan8.setText("BÀN 8");
        jPanel1.add(btnBan8);

        btnBan9.setBackground(new java.awt.Color(255, 153, 153));
        btnBan9.setText("BÀN 9");
        jPanel1.add(btnBan9);

        btnBan10.setBackground(new java.awt.Color(255, 153, 153));
        btnBan10.setText("BÀN 10");
        jPanel1.add(btnBan10);

        btnBan11.setBackground(new java.awt.Color(255, 153, 153));
        btnBan11.setText("BÀN 11");
        jPanel1.add(btnBan11);

        btnBan12.setBackground(new java.awt.Color(255, 153, 153));
        btnBan12.setText("BÀN 12");
        jPanel1.add(btnBan12);

        btnBan13.setBackground(new java.awt.Color(255, 153, 153));
        btnBan13.setText("BÀN 13");
        jPanel1.add(btnBan13);

        btnBan14.setBackground(new java.awt.Color(255, 153, 153));
        btnBan14.setText("BÀN 14");
        jPanel1.add(btnBan14);

        btnBan15.setBackground(new java.awt.Color(255, 153, 153));
        btnBan15.setText("BÀN 15");
        jPanel1.add(btnBan15);

        btnBan16.setBackground(new java.awt.Color(255, 153, 153));
        btnBan16.setText("BÀN 16");
        jPanel1.add(btnBan16);

        btnBan17.setBackground(new java.awt.Color(255, 153, 153));
        btnBan17.setText("BÀN 17");
        jPanel1.add(btnBan17);

        btnBan18.setBackground(new java.awt.Color(255, 153, 153));
        btnBan18.setText("BÀN 18");
        jPanel1.add(btnBan18);

        btnBan19.setBackground(new java.awt.Color(255, 153, 153));
        btnBan19.setText("BÀN 19");
        jPanel1.add(btnBan19);

        btnBan20.setBackground(new java.awt.Color(255, 153, 153));
        btnBan20.setText("BÀN 20");
        jPanel1.add(btnBan20);

        btnBan21.setBackground(new java.awt.Color(255, 153, 153));
        btnBan21.setText("BÀN 21");
        jPanel1.add(btnBan21);

        btnBan22.setBackground(new java.awt.Color(255, 153, 153));
        btnBan22.setText("BÀN 22");
        jPanel1.add(btnBan22);

        btnBan23.setBackground(new java.awt.Color(255, 153, 153));
        btnBan23.setText("BÀN 23");
        jPanel1.add(btnBan23);

        btnBan24.setBackground(new java.awt.Color(255, 153, 153));
        btnBan24.setText("BÀN 24");
        jPanel1.add(btnBan24);

        btnBan25.setBackground(new java.awt.Color(255, 153, 153));
        btnBan25.setText("BÀN 25");
        jPanel1.add(btnBan25);

        jLabel2.setFont(new java.awt.Font("Tempus Sans ITC", 3, 36)); // NOI18N
        jLabel2.setText("Phòng bàn & dich vu");

        jLabel3.setFont(new java.awt.Font("Tempus Sans ITC", 2, 18)); // NOI18N
        jLabel3.setText("Room table and service");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 910, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
            .addGroup(layout.createSequentialGroup()
                .addGap(454, 454, 454)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
      public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new QuanLiQuanBida().setVisible(true));
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBan1;
    private javax.swing.JButton btnBan10;
    private javax.swing.JButton btnBan11;
    private javax.swing.JButton btnBan12;
    private javax.swing.JButton btnBan13;
    private javax.swing.JButton btnBan14;
    private javax.swing.JButton btnBan15;
    private javax.swing.JButton btnBan16;
    private javax.swing.JButton btnBan17;
    private javax.swing.JButton btnBan18;
    private javax.swing.JButton btnBan19;
    private javax.swing.JButton btnBan2;
    private javax.swing.JButton btnBan20;
    private javax.swing.JButton btnBan21;
    private javax.swing.JButton btnBan22;
    private javax.swing.JButton btnBan23;
    private javax.swing.JButton btnBan24;
    private javax.swing.JButton btnBan25;
    private javax.swing.JButton btnBan3;
    private javax.swing.JButton btnBan4;
    private javax.swing.JButton btnBan5;
    private javax.swing.JButton btnBan6;
    private javax.swing.JButton btnBan7;
    private javax.swing.JButton btnBan8;
    private javax.swing.JButton btnBan9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
