/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import helper.DbConnector;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Diem;
import model.MonHoc;
import model.SinhVien;
import model.TongKet;
import model.Diemrenluyen;

/**
 *
 * @author ADMIN
 */
public class QuanLySinhVien extends javax.swing.JFrame {

    ArrayList<SinhVien> lstSinhVien = new ArrayList<>();
    ArrayList<Diem> lstDiem = new ArrayList<>();
    ArrayList<TongKet> lstTongKet = new ArrayList<>();
    ArrayList<MonHoc> lstMonHoc = new ArrayList<>();
    ArrayList<Diemrenluyen> lstDiemrenluyen = new ArrayList<>();
    int vitri;

    /**
     * Creates new form QuanLySinhVien
     */
    public QuanLySinhVien() {
        initComponents();
        loadTable();
        loadTableMonHoc();
        loadTableDiem();
        loadTableTK();
       loadTableDiemrl();
  
    }
    
private void loadTableDiemrl(){
 try {
            DefaultTableModel model = (DefaultTableModel) tblDiemrenluyen.getModel();
            model.setColumnCount(0);
            model.addColumn("Điểm 1");
            model.addColumn("Điểm 2");
            model.addColumn("Điểm 3");
            model.addColumn("Điểm 4");
            model.addColumn("Điểm 5");
            model.addColumn("Điểm 6");
       
            //1. Tạo kết nối  đến CSDL
            Connection connection = DbConnector.getConnection();
            //2. Viết câu truy vấn
            // nên copy từ sql sver sang 
            String query = "select * from diemrenluyen";
            Statement statement = connection.createStatement();// câu lệnh
            //3. Thực thi câu lệnh
            ResultSet rs = statement.executeQuery(query);
            lstDiemrenluyen = new ArrayList<>();
            while (rs.next()) {
                Float diem1 = rs.getFloat("diem1");
                Float diem2 = rs.getFloat("diem2");
                Float diem3 = rs.getFloat("diem3");
                Float diem4 = rs.getFloat("diem4");
                Float diem5 = rs.getFloat("diem5");
                Float diem6 = rs.getFloat("diem6");

                lstDiemrenluyen.add(new Diemrenluyen(diem1,diem2,diem3,diem4,diem5,diem6));
            }
            model.setRowCount(0);
            for (Diemrenluyen rl : lstDiemrenluyen) {
                model.addRow(new Object[]{rl.getdiem1(),rl.getdiem2(),rl.getdiem3(),rl.getdiem4(),rl.getdiem5(),rl.getdiem6()});
            }
        } catch (Exception e) {
        }
}
    
    private void sua() {
        try {
            int hoi = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa không ?");
            if (hoi != JOptionPane.YES_OPTION) {
                return;
            }
            vitri = tblThongTinSV.getSelectedRow();
            String masv = tblThongTinSV.getValueAt(vitri, 0).toString();
            String msv = txtMaSv.getText();
            String hoTen = txtHoTen.getText();
            String monHocID = (String) cboLop.getSelectedItem();
            String gioiTinh = txtGioiTinh.getText();
            Integer tuoi = Integer.parseInt(txtTuoi.getText());
            String diaChi = txtDiaChi.getText();
            String sdt = txtSdt.getText();
            String email = txtEmail.getText();
            Connection connection = DbConnector.getConnection();
            //2. Viết câu truy vấn
            // nên copy từ sql sver sang 
            String query = "update sinhvien set masv = '" + msv + "',hoten = N'" + hoTen + "',monhocID = '" + monHocID + "',gioitinh = N'" + gioiTinh + "',tuoi = " + tuoi + ",diachi = N'" + diaChi + "',sdt = '" + sdt + "',email = '" + email + "' where masv like  '%" + masv + "%'";
            Statement statement = connection.createStatement();// câu lệnh
            //3. Thực thi câu lệnh
            int rs = statement.executeUpdate(query);
            JOptionPane.showMessageDialog(this, "Update Thành Công");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Update Thất Bại");
        }
    }
    private void suaMonHoc() {
        try {
            int hoi = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa không ?");
            if (hoi != JOptionPane.YES_OPTION) {
                return;
            }
            vitri = tblLop.getSelectedRow();
            String mamh = tblLop.getValueAt(vitri, 0).toString();
            String tenMonHoc = txtTenLop.getText();
            String Gvcn = txtGVCN.getText();
            Integer sotinchi = Integer.parseInt(txtSoTinChi.getText());
            Connection connection = DbConnector.getConnection();
            //2. Viết câu truy vấn
            // nên copy từ sql sver sang 
            String query = "update monhoc set tenmh = N'" + tenMonHoc + "',gvcn = N'" + Gvcn + "',sotinchi = " + sotinchi + " where monhocid like  '%" + mamh + "%'";
            Statement statement = connection.createStatement();// câu lệnh
            //3. Thực thi câu lệnh
            int rs = statement.executeUpdate(query);
            JOptionPane.showMessageDialog(this, "Update Thành Công");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Update Thất Bại");
        }
    }
private void suaDiem(){
    try {
            int hoi = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa không ?");
            if (hoi != JOptionPane.YES_OPTION) {
                return;
            }
            vitri = tblDiem.getSelectedRow();
            String msv = tblDiem.getValueAt(vitri, 0).toString();
            Float lab1 = Float.parseFloat(txtLab1.getText());
            Float lab2 = Float.parseFloat(txtLab2.getText());
            Float lab3 = Float.parseFloat(txtLab3.getText());
            Float lab4 = Float.parseFloat(txtLab4.getText());
            Float tiendo = Float.parseFloat(txtTienDo.getText());
            Float asm1 = Float.parseFloat(txtAsm1.getText());
            Float asm2 = Float.parseFloat(txtASM2.getText());
            Connection connection = DbConnector.getConnection();
            //2. Viết câu truy vấn
            // nên copy từ sql sver sang 
            String query = "update diem set lab1 = "+lab1+",lab2 = "+lab2+",lab3 ="+lab3+",lab4 ="+lab4+", tiendo ="+tiendo+", asm1 ="+asm1+",asm2 ="+asm2+"  where masv like  '%" + msv + "%'";
            Statement statement = connection.createStatement();// câu lệnh
            //3. Thực thi câu lệnh
            int rs = statement.executeUpdate(query);
            JOptionPane.showMessageDialog(this, "Update Thành Công");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Update Thất Bại");
        }
}
   private void clearDiemrenluyen(){
         txtdiem1.setText("");
         txtdiem2.setText("");
         txtdiem3.setText("");
         txtdiem4.setText("");
         txtdiem5.setText("");
         txtdiem6.setText("");
   }
    private void clearForm() {
        txtMaSv.setText("");
        txtHoTen.setText("");
        cboLop.setSelectedIndex(-1);
        txtGioiTinh.setText("");
        txtTuoi.setText("");
        txtDiaChi.setText("");
        txtSdt.setText("");
        txtEmail.setText("");
    }
    private void clearMonHoc(){
        cboLop1.setSelectedIndex(-1);
        txtTenLop.setText("");
        txtGVCN.setText("");
        txtSoTinChi.setText("");
    }
    private void clearDiem(){
        txtMsv.setText("");
        txthoten.setText("");
        cboLop2.setSelectedIndex(-1);
        txtLab1.setText("");
        txtLab2.setText("");
        txtLab3.setText("");
        txtLab4.setText("");
        txtTienDo.setText("");
        txtAsm1.setText("");
        txtASM2.setText("");
        txtdtb.setText("");
    }
   
private void loadTableMonHoc() {
        try {
            DefaultTableModel model = (DefaultTableModel) tblLop.getModel();
            model.setColumnCount(0);
            model.addColumn("Mã Môn Học");
            model.addColumn("Tên Môn Học");
            model.addColumn("Giảng viên");
            model.addColumn("Số Tín");
           
            //1. Tạo kết nối  đến CSDL
            Connection connection = DbConnector.getConnection();
            //2. Viết câu truy vấn
            // nên copy từ sql sver sang 
            String query = "select * from monhoc";
            Statement statement = connection.createStatement();// câu lệnh
            //3. Thực thi câu lệnh
            ResultSet rs = statement.executeQuery(query);
            lstMonHoc = new ArrayList<>();
            while (rs.next()) {
                String mamh = rs.getString("monhocid");
                String tenmonhoc = rs.getString("tenmh");
                String GVCN = rs.getString("gvcn");
                
                Integer sotinchi = rs.getInt("sotinchi");
                

                lstMonHoc.add(new MonHoc(mamh, tenmonhoc, GVCN, sotinchi));
            }
            model.setRowCount(0);
            for (MonHoc mh : lstMonHoc) {
                model.addRow(new Object[]{mh.getMonhocID(),mh.getTenMh(),mh.getGvcn(),mh.getSoTinChi()});
            }
        } catch (Exception e) {
        }

    }
    private void loadTable() {
        try {
            DefaultTableModel model = (DefaultTableModel) tblThongTinSV.getModel();
            model.setColumnCount(0);
            model.addColumn("Mã Sinh Viên");
            model.addColumn("Họ Tên Sinh Viên");
            model.addColumn("Môn Học ID");
            model.addColumn("Giới Tính");
            model.addColumn("Tuổi");
            model.addColumn("Địa Chỉ");
            model.addColumn("Số Điện Thoại");
            model.addColumn("Email");
            //1. Tạo kết nối  đến CSDL
            Connection connection = DbConnector.getConnection();
            //2. Viết câu truy vấn
            // nên copy từ sql sver sang 
            String query = "select * from sinhvien";
            Statement statement = connection.createStatement();// câu lệnh
            //3. Thực thi câu lệnh
            ResultSet rs = statement.executeQuery(query);
            lstSinhVien = new ArrayList<>();
            while (rs.next()) {
                String masv = rs.getString("masv");
                String hoTen = rs.getString("hoten");
                String monhocID = rs.getString("monhocID");
                String gioiTinh = rs.getString("gioitinh");
                Integer tuoi = rs.getInt("tuoi");
                String diaChi = rs.getString("diachi");
                String sdt = rs.getString("sdt");
                String email = rs.getString("email");

                lstSinhVien.add(new SinhVien(masv, hoTen, monhocID, gioiTinh, tuoi, diaChi, sdt, email));
            }
            model.setRowCount(0);
            for (SinhVien sv : lstSinhVien) {
                model.addRow(new Object[]{sv.getMaSv(), sv.getHoTenSV(), sv.getMonHocID(), sv.getGioiTinh(), sv.getTuoi(), sv.getDiaChi(), sv.getSdt(), sv.getEmail()});
            }
        } catch (Exception e) {
        }

    }

    private void loadTableDiem() {
        try {
            DefaultTableModel model = (DefaultTableModel) tblDiem.getModel();
            model.setColumnCount(0);
            model.addColumn("Mã Sinh Viên");
            model.addColumn("Họ Tên Sinh Viên");
            model.addColumn("Môn Học ID");
            model.addColumn("Môn 1");
            model.addColumn("Môn 2");
            model.addColumn("Môn 3");
            model.addColumn("Môn 4");
            model.addColumn("Môn 5");
            model.addColumn("Môn 6");
            model.addColumn("Môn 7");
            model.addColumn("Điểm TB");

            //1. Tạo kết nối  đến CSDL
            Connection connection = DbConnector.getConnection();
            //2. Viết câu truy vấn
            // nên copy từ sql sver sang 
            String query = "SELECT diem.masv,hoten,diem.monhocID,lab1,lab2,lab3,lab4,tiendo,asm1,asm2 from diem join sinhvien on diem.masv= sinhvien.masv";
            Statement statement = connection.createStatement();// câu lệnh
            //3. Thực thi câu lệnh
            ResultSet rs = statement.executeQuery(query);
            lstDiem = new ArrayList<>();
            while (rs.next()) {
                String masv = rs.getString("masv");
                String hoTen = rs.getString("hoten");
                String monhocID = rs.getString("monhocID");
                Float lab1 = rs.getFloat("lab1");
                Float lab2 = rs.getFloat("lab2");
                Float lab3 = rs.getFloat("lab3");
                Float lab4 = rs.getFloat("lab4");
                Float tiendo = rs.getFloat("tiendo");
                Float asm1 = rs.getFloat("asm1");
                Float asm2 = rs.getFloat("asm2");
                lstDiem.add(new Diem(masv, hoTen, monhocID, lab1, lab2, lab3, lab4, tiendo, asm1, asm2));
            }
            model.setRowCount(0);
            for (Diem di : lstDiem) {
                model.addRow(new Object[]{di.getMaSv(), di.getHoTen(), di.getMonHocID(), di.getLab1(), di.getLab2(), di.getLab3(), di.getLab4(), di.getTienDo(), di.getAsm1(), di.getAsm2(), di.dtb()});
            }
        } catch (Exception e) {
        }

    }
    private void loadTableTK() {
        try {
            DefaultTableModel model = (DefaultTableModel) tblTongKet.getModel();
            model.setColumnCount(0);
            model.addColumn("Mã Sinh Viên");
            model.addColumn("Họ Tên Sinh Viên");
            model.addColumn("Môn Học ID");
            model.addColumn("Điểm TB");
            model.addColumn("Học Lực");
            model.addColumn("Điều kiện xét học bổng");
            
            //1. Tạo kết nối  đến CSDL
            Connection connection = DbConnector.getConnection();
            //2. Viết câu truy vấn
            // nên copy từ sql sver sang 
            String query = "select diem.masv,hoten,diem.monhocID , (((lab1+lab2+lab3+lab4+tienDo)*5)+asm1*30 + asm2*45 )/100 as dtb from diem join sinhvien on diem.masv= sinhvien.masv";
            Statement statement = connection.createStatement();// câu lệnh
            //3. Thực thi câu lệnh
            ResultSet rs = statement.executeQuery(query);
            lstTongKet = new ArrayList<>();
            while (rs.next()) {
                String masv = rs.getString("masv");
                String hoTen = rs.getString("hoten");
                String monhocID = rs.getString("monhocID");
                Float dtb = rs.getFloat("dtb");
                   lstTongKet.add(new TongKet(masv, hoTen, monhocID, dtb));
                
            }
            model.setRowCount(0);
            for (TongKet tk : lstTongKet) {
                model.addRow(new Object[]{tk.getMaSv(),tk.getHoTen(),tk.getMonHocID(),tk.getDtb(),tk.getHocLuc(),tk.hocBong()});
            }
        } catch (Exception e) {
        }}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMaSv = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtGioiTinh = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTuoi = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblThongTinSV = new javax.swing.JTable();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        cboLop = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtSdt = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        btnClear = new javax.swing.JButton();
        btnTim = new javax.swing.JButton();
        btnThoat1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        cboLop1 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtGVCN = new javax.swing.JTextField();
        txtTenLop = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtSoTinChi = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLop = new javax.swing.JTable();
        btnSuaLop = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        btnTim1 = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtMsv = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txthoten = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        cboLop2 = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtLab1 = new javax.swing.JTextField();
        txtLab2 = new javax.swing.JTextField();
        txtLab3 = new javax.swing.JTextField();
        txtLab4 = new javax.swing.JTextField();
        txtTienDo = new javax.swing.JTextField();
        txtAsm1 = new javax.swing.JTextField();
        txtASM2 = new javax.swing.JTextField();
        txtdtb = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDiem = new javax.swing.JTable();
        btnSuaDiem = new javax.swing.JButton();
        btnClearDiem = new javax.swing.JButton();
        btnTimDiem = new javax.swing.JButton();
        btnThoatDiem = new javax.swing.JButton();
        jComboBox5 = new javax.swing.JComboBox<>();
        jComboBox6 = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        txtMaSv1 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtHoTen1 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        cboLop3 = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtDiemTB1 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        cboHocLuc = new javax.swing.JComboBox<>();
        txtKhenThuong = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblTongKet = new javax.swing.JTable();
        btnTimTK = new javax.swing.JButton();
        btnThoatDiem1 = new javax.swing.JButton();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        txtdiem3 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txtdiem4 = new javax.swing.JTextField();
        txtdiem1 = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        txtdiem5 = new javax.swing.JTextField();
        txtdiem6 = new javax.swing.JTextField();
        txtdiem2 = new javax.swing.JTextField();
        btxoadiemrl = new javax.swing.JButton();
        btdiemrenluyen = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblDiemrenluyen = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Mã Sinh Viên");

        txtMaSv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSvActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Họ Tên Sinh Viên");

        txtHoTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHoTenActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Giới Tính");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Năm Sinh");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Địa Chỉ ");

        tblThongTinSV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Sinh Viên", "Họ và Tên", "Môn Học ID", "Giới Tính", "Năm Sinh", "Địa Chỉ", "Số Điện Thoại", "Email"
            }
        ));
        tblThongTinSV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThongTinSVMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblThongTinSV);

        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Add.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Edit.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Delete.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Môn Học ID");

        cboLop.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cboLop.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "IT18203", "SOB203", "IT18202", "COM18203" }));
        cboLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLopActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Số Điện Thoại");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Email");

        btnClear.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Refresh.png"))); // NOI18N
        btnClear.setText("Làm Mới");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnTim.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Search.png"))); // NOI18N
        btnTim.setText("Tìm Kiếm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        btnThoat1.setBackground(new java.awt.Color(255, 0, 0));
        btnThoat1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThoat1.setForeground(new java.awt.Color(255, 255, 255));
        btnThoat1.setText("Thoát");
        btnThoat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoat1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtMaSv))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtGioiTinh)))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(81, 81, 81)
                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cboLop, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(97, 97, 97)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTuoi, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                                    .addComponent(txtEmail)
                                    .addComponent(txtSdt)
                                    .addComponent(txtDiaChi)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(346, 346, 346)
                                .addComponent(btnTim)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnClear, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnXoa, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSua, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnThem, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnThoat1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1035, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cboLop, txtGioiTinh, txtHoTen, txtMaSv});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnClear, btnSua, btnThem, btnThoat1, btnTim, btnXoa});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtMaSv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtTuoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnThem)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)
                                .addComponent(btnSua)))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(cboLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8))
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                        .addComponent(btnTim)
                        .addGap(69, 69, 69))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(btnXoa)
                        .addGap(18, 18, 18)
                        .addComponent(btnClear)
                        .addGap(29, 29, 29)
                        .addComponent(btnThoat1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cboLop, txtDiaChi, txtEmail, txtGioiTinh, txtHoTen, txtMaSv, txtSdt, txtTuoi});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnClear, btnSua, btnThem, btnThoat1, btnTim, btnXoa});

        jTabbedPane1.addTab("Nhập Thông Tin Sinh Viên", jPanel1);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Môn Học ID");

        cboLop1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cboLop1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "IT18203", "SOB203", "IT18202", "COM18203" }));
        cboLop1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLop1ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Tên Môn");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Giảng viên");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Số Tín Chỉ");

        tblLop.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Lớp", "Tên Lớp", "Giảng viên", "Số Tín Chỉ"
            }
        ));
        tblLop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLopMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblLop);

        btnSuaLop.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSuaLop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Edit.png"))); // NOI18N
        btnSuaLop.setText("Sửa");
        btnSuaLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaLopActionPerformed(evt);
            }
        });

        btnLamMoi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Refresh.png"))); // NOI18N
        btnLamMoi.setText("Làm Mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnTim1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTim1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Search.png"))); // NOI18N
        btnTim1.setText("Tìm Kiếm");
        btnTim1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTim1ActionPerformed(evt);
            }
        });

        btnThoat.setBackground(new java.awt.Color(255, 0, 0));
        btnThoat.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThoat.setForeground(new java.awt.Color(255, 255, 255));
        btnThoat.setText("Thoát");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Năm 2023", "Năm 2024", "Năm 2025", "Năm 2026" }));

        jComboBox2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kỳ I", "Kỳ II" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtGVCN))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cboLop1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(94, 94, 94)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnTim1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 458, Short.MAX_VALUE)
                                .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTenLop)
                                    .addComponent(txtSoTinChi, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
                                .addGap(12, 52, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(32, 32, 32)
                                        .addComponent(btnSuaLop))
                                    .addComponent(btnLamMoi))))))
                .addGap(26, 26, 26))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel11, jLabel12, jLabel9});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cboLop1, txtGVCN, txtSoTinChi, txtTenLop});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnLamMoi, btnSuaLop, btnThoat, btnTim1});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboLop1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenLop, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaLop)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtGVCN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtSoTinChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTim1)
                    .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(87, 87, 87)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel10, jLabel11, jLabel12, jLabel9});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cboLop1, txtGVCN, txtSoTinChi, txtTenLop});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnLamMoi, btnSuaLop, btnThoat, btnTim1});

        jTabbedPane1.addTab("Thông Tin Môn Học", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Mã Sinh Viên");

        txtMsv.setEnabled(false);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Họ Và Tên");

        txthoten.setEnabled(false);
        txthoten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txthotenActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Môn Học ID");

        cboLop2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cboLop2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "IT18203", "SOB203", "IT18202", "COM18203" }));
        cboLop2.setEnabled(false);
        cboLop2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLop2ActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Môn 1");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Môn 2");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Môn 3");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Môn 4");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Môn 5");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Môn 6");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Môn7");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Điểm TB");

        txtTienDo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienDoActionPerformed(evt);
            }
        });

        txtdtb.setEnabled(false);

        tblDiem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Sinh Viên", "Họ và Tên", "Môn Học ID", "Môn 1", "Môn 2", "Môn 3", "Môn 4", "Môn 5", "Môn 6", "Môn 7", "Điểm TB"
            }
        ));
        tblDiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDiemMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblDiem);

        btnSuaDiem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSuaDiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Edit.png"))); // NOI18N
        btnSuaDiem.setText("Sửa");
        btnSuaDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaDiemActionPerformed(evt);
            }
        });

        btnClearDiem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnClearDiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Refresh.png"))); // NOI18N
        btnClearDiem.setText("Làm Mới");
        btnClearDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearDiemActionPerformed(evt);
            }
        });

        btnTimDiem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTimDiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Search.png"))); // NOI18N
        btnTimDiem.setText("Tìm Kiếm");
        btnTimDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimDiemActionPerformed(evt);
            }
        });

        btnThoatDiem.setBackground(new java.awt.Color(255, 0, 0));
        btnThoatDiem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThoatDiem.setForeground(new java.awt.Color(255, 255, 255));
        btnThoatDiem.setText("Thoát");
        btnThoatDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatDiemActionPerformed(evt);
            }
        });

        jComboBox5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Năm 2023", "Năm 2024", "Năm 2025", "Năm 2026" }));

        jComboBox6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kỳ I", "Kỳ II" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(150, 150, 150)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtLab2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addComponent(btnSuaDiem)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtLab3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(114, 114, 114)
                                .addComponent(btnClearDiem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
                                .addComponent(btnTimDiem)))
                        .addGap(132, 132, 132))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtLab1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel16)))
                        .addGap(15, 15, 15)
                        .addComponent(txtMsv, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txthoten, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(cboLop2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel22)
                            .addComponent(txtASM2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(76, 76, 76)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtdtb, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(btnThoatDiem)
                        .addContainerGap())))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(411, 411, 411)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLab4, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTienDo, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(65, 65, 65)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAsm1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel16, jLabel17, jLabel18, jLabel19, jLabel20, jLabel21, jLabel22, jLabel23});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtASM2, txtAsm1, txtLab1, txtLab2, txtLab3, txtLab4, txtTienDo, txtdtb});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnClearDiem, btnSuaDiem, btnThoatDiem, btnTimDiem});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtMsv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txthoten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(cboLop2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLab1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLab2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLab3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTienDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAsm1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtASM2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdtb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLab4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSuaDiem)
                    .addComponent(btnClearDiem)
                    .addComponent(btnTimDiem)
                    .addComponent(btnThoatDiem))
                .addGap(51, 51, 51)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel16, jLabel17, jLabel18, jLabel19, jLabel20, jLabel21, jLabel22, jLabel23});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtASM2, txtAsm1, txtLab1, txtLab2, txtLab3, txtLab4, txtTienDo, txtdtb});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnClearDiem, btnSuaDiem, btnThoatDiem, btnTimDiem});

        jTabbedPane1.addTab("Điểm Của Từng Môn Học", jPanel3);

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel24.setText("Mã Sinh Viên");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel25.setText("Họ Tên Sinh Viên");

        txtHoTen1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHoTen1ActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel26.setText("Môn Học ID");

        cboLop3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cboLop3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "IT18203", "SOB203", "IT18202", "COM18203" }));
        cboLop3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLop3ActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel27.setText("Điểm TB");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel28.setText("Học Lực");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Điều kiện xét học bổng");

        cboHocLuc.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cboHocLuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Yếu", "Kém", "Trung Bình", "Khá", "Giỏi", "Xuất Sắc" }));

        tblTongKet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Sinh Viên", "Họ và Tên", "Môn Học ID", "Điểm TB", "Học Lực", "Điều kiện xét học bổng"
            }
        ));
        tblTongKet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTongKetMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblTongKet);

        btnTimTK.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTimTK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Search.png"))); // NOI18N
        btnTimTK.setText("Tìm Kiếm");
        btnTimTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimTKActionPerformed(evt);
            }
        });

        btnThoatDiem1.setBackground(new java.awt.Color(255, 0, 0));
        btnThoatDiem1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThoatDiem1.setForeground(new java.awt.Color(255, 255, 255));
        btnThoatDiem1.setText("Thoát");
        btnThoatDiem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatDiem1ActionPerformed(evt);
            }
        });

        jComboBox3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Năm 2023", "Năm 2024", "Năm 2025", "Năm 2026" }));

        jComboBox4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kỳ I", "Kỳ II" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaSv1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHoTen1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(59, 59, 59)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(cboHocLuc, 0, 154, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtDiemTB1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGap(9, 9, 9)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(txtKhenThuong, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(21, 21, 21))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(cboLop3, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(220, 220, 220)
                        .addComponent(btnTimTK, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThoatDiem1)
                        .addGap(138, 138, 138))))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel24, jLabel25, jLabel26, jLabel27, jLabel28});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cboHocLuc, cboLop3, txtDiemTB1, txtHoTen1, txtKhenThuong, txtMaSv1});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnThoatDiem1, btnTimTK});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaSv1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27)
                    .addComponent(txtDiemTB1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(jLabel31)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtHoTen1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel28)
                        .addComponent(cboHocLuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtKhenThuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(cboLop3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimTK)
                    .addComponent(btnThoatDiem1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel24, jLabel25, jLabel26, jLabel27, jLabel28});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cboHocLuc, cboLop3, txtDiemTB1, txtHoTen1, txtKhenThuong, txtMaSv1});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnThoatDiem1, btnTimTK});

        jTabbedPane1.addTab("Tổng Kết_Đánh Giá Sinh Viên", jPanel4);

        jLabel29.setText("Điểm 3");

        txtdiem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdiem3ActionPerformed(evt);
            }
        });

        jLabel32.setText("Điểm 4");

        jLabel33.setText("Điểm 2");

        jLabel34.setText("Điểm 1");

        txtdiem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdiem1ActionPerformed(evt);
            }
        });

        jLabel35.setText("Điểm 6");

        jLabel36.setText("Điểm 5");

        txtdiem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdiem5ActionPerformed(evt);
            }
        });

        txtdiem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdiem6ActionPerformed(evt);
            }
        });

        txtdiem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdiem2ActionPerformed(evt);
            }
        });

        btxoadiemrl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Delete.png"))); // NOI18N
        btxoadiemrl.setText("Xóa");
        btxoadiemrl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btxoadiemrlActionPerformed(evt);
            }
        });

        btdiemrenluyen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Add.png"))); // NOI18N
        btdiemrenluyen.setText("Thêm");
        btdiemrenluyen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btdiemrenluyenActionPerformed(evt);
            }
        });

        tblDiemrenluyen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Điểm 1", "Điểm 2", "Điểm 3", "Điểm 4", "Điểm 5", "Điểm 6"
            }
        ));
        tblDiemrenluyen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDiemrenluyenMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblDiemrenluyen);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane5)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap(57, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(btdiemrenluyen, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(btxoadiemrl, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addComponent(txtdiem1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(36, 36, 36)
                                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(txtdiem4, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtdiem2, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtdiem5, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(38, 38, 38)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtdiem6, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtdiem3, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(26, 26, 26))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdiem1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdiem2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdiem3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdiem5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdiem4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdiem6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btdiemrenluyen, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btxoadiemrl, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Điểm Rèn Luyện", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
        loadTable();
        loadTableMonHoc();
        loadTableDiem();
        loadTableTK();
        loadTableDiemrl();

    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void btnThoatDiem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatDiem1ActionPerformed
        // TODO add your handling code here:
        Login lg = new Login();
        lg.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_btnThoatDiem1ActionPerformed

    private void btnTimTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimTKActionPerformed
        // TODO add your handling code here:
        try {
            String msv = JOptionPane.showInputDialog("Mời nhập mã Sinh viên cần tìm:");
            DefaultTableModel model = (DefaultTableModel) tblTongKet.getModel();
            model.setColumnCount(0);
            model.addColumn("Mã Sinh Viên");
            model.addColumn("Họ Tên Sinh Viên");
            model.addColumn("Môn Học ID");
            model.addColumn("Điểm TB");
            model.addColumn("Học Lực");
            model.addColumn("Điều kiện xét học bổng");

            //1. Tạo kết nối  đến CSDL
            Connection connection = DbConnector.getConnection();
            //2. Viết câu truy vấn
            // nên copy từ sql sver sang
            String query = "select diem.masv,hoten,diem.monhocID , (((lab1+lab2+lab3+lab4+tienDo)*5)+asm1*30 + asm2*45 )/100 as dtb from diem join sinhvien on diem.masv= sinhvien.masv  where diem.masv like '%"+msv+"%'";
            Statement statement = connection.createStatement();// câu lệnh
            //3. Thực thi câu lệnh
            ResultSet rs = statement.executeQuery(query);
            lstTongKet = new ArrayList<>();
            while (rs.next()) {
                String masv = rs.getString("masv");
                String hoTen = rs.getString("hoten");
                String monhocID = rs.getString("monhocID");
                Float dtb = rs.getFloat("dtb");
                lstTongKet.add(new TongKet(masv, hoTen, monhocID, dtb));

            }
            model.setRowCount(0);
            for (TongKet tk : lstTongKet) {
                model.addRow(new Object[]{tk.getMaSv(),tk.getHoTen(),tk.getMonHocID(),tk.getDtb(),tk.getHocLuc(),tk.hocBong()});
            }
        } catch (Exception e) {
        }

    }//GEN-LAST:event_btnTimTKActionPerformed

    private void tblTongKetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTongKetMouseClicked
        // TODO add your handling code here:
        int row = tblTongKet.getSelectedRow();
        if (row == -1) {
            return;
        }
        String msv = tblTongKet.getValueAt(row, 0).toString();
        String hoTen = tblTongKet.getValueAt(row, 1).toString();
        String monHocID = tblTongKet.getValueAt(row, 2).toString();
        Float dtb = Float.parseFloat(tblTongKet.getValueAt(row, 3).toString());
        String hocLuc = tblTongKet.getValueAt(row, 4).toString();
        String khenThuong = tblTongKet.getValueAt(row, 5).toString();
        txtMaSv1.setText(msv);
        txtHoTen1.setText(hoTen);
        cboLop2.setSelectedItem(monHocID);
        txtDiemTB1.setText(dtb+"");
        cboHocLuc.setSelectedItem(hocLuc);
        txtKhenThuong.setText(khenThuong);

    }//GEN-LAST:event_tblTongKetMouseClicked

    private void cboLop3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLop3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboLop3ActionPerformed

    private void txtHoTen1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoTen1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoTen1ActionPerformed

    private void btnThoatDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatDiemActionPerformed
        // TODO add your handling code here:
        Login lg = new Login();
        lg.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnThoatDiemActionPerformed

    private void btnTimDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimDiemActionPerformed
        // TODO add your handling code here:
        try {
            String msv = JOptionPane.showInputDialog("Mời nhập mã Sinh viên cần tìm:");
            DefaultTableModel model = (DefaultTableModel) tblDiem.getModel();
            model.setColumnCount(0);
            model.addColumn("Mã Sinh Viên");
            model.addColumn("Họ Tên Sinh Viên");
            model.addColumn("Môn Học ID");
            model.addColumn("Môn 1");
            model.addColumn("Môn 2");
            model.addColumn("Môn 3");
            model.addColumn("Môn 4");
            model.addColumn("Môn 5");
            model.addColumn("Môn 6");
            model.addColumn("Môn 7");
            model.addColumn("Điểm TB");

            //1. Tạo kết nối  đến CSDL
            Connection connection = DbConnector.getConnection();
            //2. Viết câu truy vấn
            // nên copy từ sql sver sang
            String query = "SELECT diem.masv,hoten,diem.monhocID,lab1,lab2,lab3,lab4,tiendo,asm1,asm2 from diem join sinhvien on diem.masv= sinhvien.masv where diem.masv like '%"+msv+"'";
            Statement statement = connection.createStatement();// câu lệnh
            //3. Thực thi câu lệnh
            ResultSet rs = statement.executeQuery(query);
            lstDiem = new ArrayList<>();
            while (rs.next()) {
                String masv = rs.getString("masv");
                String hoTen = rs.getString("hoten");
                String monhocID = rs.getString("monhocID");
                Float lab1 = rs.getFloat("lab1");
                Float lab2 = rs.getFloat("lab2");
                Float lab3 = rs.getFloat("lab3");
                Float lab4 = rs.getFloat("lab4");
                Float tiendo = rs.getFloat("tiendo");
                Float asm1 = rs.getFloat("asm1");
                Float asm2 = rs.getFloat("asm2");
                lstDiem.add(new Diem(masv, hoTen, monhocID, lab1, lab2, lab3, lab4, tiendo, asm1, asm2));
            }
            model.setRowCount(0);
            for (Diem di : lstDiem) {
                model.addRow(new Object[]{di.getMaSv(), di.getHoTen(), di.getMonHocID(), di.getLab1(), di.getLab2(), di.getLab3(), di.getLab4(), di.getTienDo(), di.getAsm1(), di.getAsm2(), di.dtb()});
            }
        } catch (Exception e) {
        }

    }//GEN-LAST:event_btnTimDiemActionPerformed

    private void btnClearDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearDiemActionPerformed
        // TODO add your handling code here:
        clearDiem();
    }//GEN-LAST:event_btnClearDiemActionPerformed

    private void btnSuaDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaDiemActionPerformed
        // TODO add your handling code here:
        suaDiem();
        loadTableDiem();
        clearDiem();
    }//GEN-LAST:event_btnSuaDiemActionPerformed

    private void tblDiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDiemMouseClicked
        // TODO add your handling code here:
        int row = tblDiem.getSelectedRow();
        if (row == -1) {
            return;
        }
        String msv = tblDiem.getValueAt(row, 0).toString();
        String hoTen = tblDiem.getValueAt(row, 1).toString();
        String monHocID = tblDiem.getValueAt(row, 2).toString();
        Float lab1 = Float.parseFloat(tblDiem.getValueAt(row, 3).toString());
        Float lab2 = Float.parseFloat(tblDiem.getValueAt(row, 4).toString());
        Float lab3 = Float.parseFloat(tblDiem.getValueAt(row, 5).toString());
        Float lab4 = Float.parseFloat(tblDiem.getValueAt(row, 6).toString());
        Float tiendo = Float.parseFloat(tblDiem.getValueAt(row, 7).toString());
        Float asm1 = Float.parseFloat(tblDiem.getValueAt(row, 8).toString());
        Float asm2 = Float.parseFloat(tblDiem.getValueAt(row, 9).toString());
        Float dtb = Float.parseFloat(tblDiem.getValueAt(row, 10).toString());
        txtMsv.setText(msv);
        txthoten.setText(hoTen);
        cboLop2.setSelectedItem(monHocID);
        txtLab1.setText(lab1+"");
        txtLab2.setText(lab2+"");
        txtLab3.setText(lab3+"");
        txtLab4.setText(lab4+"");
        txtTienDo.setText(tiendo+"");
        txtAsm1.setText(asm1+"");
        txtASM2.setText(asm2+"");
        txtdtb.setText(dtb+"");
    }//GEN-LAST:event_tblDiemMouseClicked

    private void txtTienDoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienDoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienDoActionPerformed

    private void cboLop2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLop2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboLop2ActionPerformed

    private void txthotenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthotenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txthotenActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        // TODO add your handling code here:
        Login lg = new Login();
        lg.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnThoatActionPerformed

    private void btnTim1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTim1ActionPerformed
        // TODO add your handling code here:
        try {
            String msv = JOptionPane.showInputDialog("Mời nhập mã môn học  cần tìm:");
            DefaultTableModel model = (DefaultTableModel) tblLop.getModel();
            model.setColumnCount(0);
            model.addColumn("Mã Môn Học");
            model.addColumn("Tên Môn Học");
            model.addColumn("Giảng viên");
            model.addColumn("Số Tín Chỉ");

            //1. Tạo kết nối  đến CSDL
            Connection connection = DbConnector.getConnection();
            //2. Viết câu truy vấn
            // nên copy từ sql sver sang
            String query = "select * from monhoc where monhocid like '%" + msv + "'";
            Statement statement = connection.createStatement();// câu lệnh
            //3. Thực thi câu lệnh
            ResultSet rs = statement.executeQuery(query);
            lstMonHoc = new ArrayList<>();
            while (rs.next()) {
                String mamh = rs.getString("monhocid");
                String tenmh = rs.getString("tenmh");
                String gvcn = rs.getString("gvcn");

                Integer tinchi = rs.getInt("sotinchi");

                lstMonHoc.add(new MonHoc(mamh,tenmh,gvcn,tinchi));
            }
            model.setRowCount(0);
            for (MonHoc mh : lstMonHoc) {
                model.addRow(new Object[]{mh.getMonhocID(),mh.getTenMh(),mh.getGvcn(),mh.getSoTinChi()});
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnTim1ActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        clearMonHoc();

    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnSuaLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaLopActionPerformed
        // TODO add your handling code here:
        suaMonHoc();
        loadTableMonHoc();
        clearMonHoc();

    }//GEN-LAST:event_btnSuaLopActionPerformed

    private void tblLopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLopMouseClicked
        // TODO add your handling code here:
        int row = tblLop.getSelectedRow();
        if (row == -1) {
            return;
        }
        String mamh = tblLop.getValueAt(row, 0).toString();
        String tenmh = tblLop.getValueAt(row, 1).toString();
        String Gvcn = tblLop.getValueAt(row, 2).toString();
        Integer tinchi = Integer.parseInt(tblLop.getValueAt(row, 3).toString());

        cboLop1.setSelectedItem(mamh);
        txtTenLop.setText(tenmh);
        txtGVCN.setText(Gvcn);
        txtSoTinChi.setText(tinchi+"");
    }//GEN-LAST:event_tblLopMouseClicked

    private void cboLop1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLop1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboLop1ActionPerformed

    private void btnThoat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoat1ActionPerformed
        // TODO add your handling code here:
        Login lg = new Login();
        lg.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnThoat1ActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
        try {
            String msv = JOptionPane.showInputDialog("Mời nhập mã sinh viên  cần tìm:");
            DefaultTableModel model = (DefaultTableModel) tblThongTinSV.getModel();
            model.setColumnCount(0);
            model.addColumn("Mã Sinh Viên");
            model.addColumn("Họ Tên Sinh Viên");
            model.addColumn("Môn Học ID");
            model.addColumn("Giới Tính");
            model.addColumn("Tuổi");
            model.addColumn("Địa Chỉ");
            model.addColumn("Số Điện Thoại");
            model.addColumn("Email");
            //1. Tạo kết nối  đến CSDL
            Connection connection = DbConnector.getConnection();
            //2. Viết câu truy vấn
            // nên copy từ sql sver sang
            String query = "select * from sinhvien where masv like '%" + msv + "'";
            Statement statement = connection.createStatement();// câu lệnh
            //3. Thực thi câu lệnh
            ResultSet rs = statement.executeQuery(query);
            lstSinhVien = new ArrayList<>();
            while (rs.next()) {
                String masv = rs.getString("masv");
                String hoTen = rs.getString("hoten");
                String monhocID = rs.getString("monhocID");
                String gioiTinh = rs.getString("gioitinh");
                Integer tuoi = rs.getInt("tuoi");
                String diaChi = rs.getString("diachi");
                String sdt = rs.getString("sdt");
                String email = rs.getString("email");

                lstSinhVien.add(new SinhVien(masv, hoTen, monhocID, gioiTinh, tuoi, diaChi, sdt, email));
            }
            model.setRowCount(0);
            for (SinhVien sv : lstSinhVien) {
                model.addRow(new Object[]{sv.getMaSv(), sv.getHoTenSV(), sv.getMonHocID(), sv.getGioiTinh(), sv.getTuoi(), sv.getDiaChi(), sv.getSdt(), sv.getEmail()});
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnTimActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_btnClearActionPerformed

    private void cboLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLopActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboLopActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        try {
            int hoi = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không ?");
            if (hoi != JOptionPane.YES_OPTION) {
                return;
            }
            vitri = tblThongTinSV.getSelectedRow();
            String masv = tblThongTinSV.getValueAt(vitri, 0).toString();

            Connection connection = DbConnector.getConnection();
            //2. Viết câu truy vấn
            // nên copy từ sql sver sang
            String query = "delete from sinhvien where masv like N'%" + masv + "%'";
            Statement statement = connection.createStatement();// câu lệnh
            //3. Thực thi câu lệnh
            int result = statement.executeUpdate(query);

            JOptionPane.showMessageDialog(null, "Xóa thành công");

            lstSinhVien.remove(vitri);
            loadTable();
            clearForm();
            statement.close();
            connection.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi Khóa Ngoại");

        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        sua();
        loadTable();
        clearForm();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        try {
            String msv = txtMaSv.getText();
            String hoTen = txtHoTen.getText();
            String monHocID = (String) cboLop.getSelectedItem();
            String gioiTinh = txtGioiTinh.getText();
            Integer tuoi = Integer.parseInt(txtTuoi.getText());
            String diaChi = txtDiaChi.getText();
            String sdt = txtSdt.getText();
            String email = txtEmail.getText();

            Connection connection = DbConnector.getConnection();
            //2. Viết câu truy vấn
            // nên copy từ sql sver sang
            String query = "insert into sinhvien values ('" + msv + "',N'" + hoTen + "','" + monHocID + "',N'" + gioiTinh + "'," + tuoi + ",N'" + diaChi + "','" + sdt + "','" + email + "')";
            Statement statement = connection.createStatement();// câu lệnh

            //3. Thực thi câu lệnh
            int rs = statement.executeUpdate(query);
            JOptionPane.showMessageDialog(this, "Thêm Thành Công");
            clearForm();
            statement.close();
            connection.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Thêm Thất Bại");
        }
        loadTable();

    }//GEN-LAST:event_btnThemActionPerformed
// trang_1 dong 1823
    private void tblThongTinSVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThongTinSVMouseClicked
        // TODO add your handling code here:
        int row = tblThongTinSV.getSelectedRow();
        if (row == -1) {
            return;
        }
        String msv = tblThongTinSV.getValueAt(row, 0).toString();
        String hoTen = tblThongTinSV.getValueAt(row, 1).toString();
        String monHocID = tblThongTinSV.getValueAt(row, 2).toString();
        String gioiTinh = tblThongTinSV.getValueAt(row, 3).toString();
        Integer tuoi = Integer.parseInt(tblThongTinSV.getValueAt(row, 4).toString());
        String diaChi = tblThongTinSV.getValueAt(row, 5).toString();
        String sdt = tblThongTinSV.getValueAt(row, 6).toString();
        String email = tblThongTinSV.getValueAt(row, 7).toString();

        txtMaSv.setText(msv);
        txtHoTen.setText(hoTen);
        cboLop.setSelectedItem(monHocID);
        txtGioiTinh.setText(gioiTinh);
        txtTuoi.setText(tuoi + "");
        txtDiaChi.setText(diaChi);
        txtSdt.setText(sdt);
        txtEmail.setText(email);
    }//GEN-LAST:event_tblThongTinSVMouseClicked

    private void txtHoTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoTenActionPerformed

    private void txtdiem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdiem3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdiem3ActionPerformed

    private void txtdiem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdiem5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdiem5ActionPerformed

    private void txtdiem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdiem6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdiem6ActionPerformed

    private void txtdiem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdiem2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdiem2ActionPerformed

    private void txtdiem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdiem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdiem1ActionPerformed

    private void txtMaSvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSvActionPerformed

    private void btdiemrenluyenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btdiemrenluyenActionPerformed
        // TODO add your handling code here:
         try {
            Float diem1 = Float.parseFloat(txtdiem1.getText());
           Float diem2 = Float.parseFloat(txtdiem2.getText());
           Float diem3 = Float.parseFloat(txtdiem3.getText());
           Float diem4 = Float.parseFloat(txtdiem4.getText());
           Float diem5 = Float.parseFloat(txtdiem5.getText());
           Float diem6 = Float.parseFloat(txtdiem6.getText());
            Connection connection = DbConnector.getConnection();
            //2. Viết câu truy vấn
            // nên copy từ sql sver sang
            String query = "insert into diemrenluyen values ('" + diem1 + "','" + diem2 + "','" + diem3 + "','" + diem4 + "'," + diem5 + ",'" + diem6  + "')";
            Statement statement = connection.createStatement();// câu lệnh

            //3. Thực thi câu lệnh
            int rs = statement.executeUpdate(query);
            JOptionPane.showMessageDialog(this, "Thêm Thành Công");
            clearDiemrenluyen();
            statement.close();
            connection.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Thêm Thất Bại");
        }
         loadTableDiemrl();
       


    }//GEN-LAST:event_btdiemrenluyenActionPerformed

    private void tblDiemrenluyenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDiemrenluyenMouseClicked
        // TODO add your handling code here:
        /*
        int row = tblDiemrenluyen.getSelectedRow();
        if (row == -1) {
            return;
        }
        Float diem1 = Float.parseFloat(txtdiem1.getText());
       Float diem2 = Float.parseFloat(txtdiem2.getText());
       Float diem3 = Float.parseFloat(txtdiem3.getText());
       Float diem4 = Float.parseFloat(txtdiem4.getText());
       Float diem5 = Float.parseFloat(txtdiem5.getText());
       Float diem6 = Float.parseFloat(txtdiem6.getText());
        
        txtdiem1.setText(diem1+ "");
        txtdiem2.setText(diem2+ "");
        txtdiem3.setText(diem3+ "");
        txtdiem4.setText(diem4+ "");
        txtdiem5.setText(diem5+ "");
        txtdiem6.setText(diem6+ "");
        */
    }//GEN-LAST:event_tblDiemrenluyenMouseClicked

    private void btxoadiemrlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btxoadiemrlActionPerformed
        // TODO add your handling code here:
        try {
            int hoi = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không ?");
            if (hoi != JOptionPane.YES_OPTION) {
                return;
            }
            vitri = tblDiemrenluyen.getSelectedRow();
            String diem1 = tblDiemrenluyen.getValueAt(vitri, 0).toString();

            Connection connection = DbConnector.getConnection();
            //2. Viết câu truy vấn
            // nên copy từ sql sver sang
            String query = "delete from diemrenluyen where diem1 like N'%" + diem1 + "%'";
            Statement statement = connection.createStatement();// câu lệnh
            //3. Thực thi câu lệnh
            int result = statement.executeUpdate(query);

            JOptionPane.showMessageDialog(null, "Xóa thành công");

            lstDiemrenluyen.remove(vitri);
            loadTableDiemrl();
            clearDiemrenluyen();
            statement.close();
            connection.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi Khóa Ngoại");

        }
    }//GEN-LAST:event_btxoadiemrlActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QuanLySinhVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLySinhVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLySinhVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLySinhVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLySinhVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btdiemrenluyen;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnClearDiem;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSuaDiem;
    private javax.swing.JButton btnSuaLop;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnThoat1;
    private javax.swing.JButton btnThoatDiem;
    private javax.swing.JButton btnThoatDiem1;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnTim1;
    private javax.swing.JButton btnTimDiem;
    private javax.swing.JButton btnTimTK;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btxoadiemrl;
    private javax.swing.JComboBox<String> cboHocLuc;
    private javax.swing.JComboBox<String> cboLop;
    private javax.swing.JComboBox<String> cboLop1;
    private javax.swing.JComboBox<String> cboLop2;
    private javax.swing.JComboBox<String> cboLop3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblDiem;
    private javax.swing.JTable tblDiemrenluyen;
    private javax.swing.JTable tblLop;
    private javax.swing.JTable tblThongTinSV;
    private javax.swing.JTable tblTongKet;
    private javax.swing.JTextField txtASM2;
    private javax.swing.JTextField txtAsm1;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtDiemTB1;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtGVCN;
    private javax.swing.JTextField txtGioiTinh;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtHoTen1;
    private javax.swing.JTextField txtKhenThuong;
    private javax.swing.JTextField txtLab1;
    private javax.swing.JTextField txtLab2;
    private javax.swing.JTextField txtLab3;
    private javax.swing.JTextField txtLab4;
    private javax.swing.JTextField txtMaSv;
    private javax.swing.JTextField txtMaSv1;
    private javax.swing.JTextField txtMsv;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtSoTinChi;
    private javax.swing.JTextField txtTenLop;
    private javax.swing.JTextField txtTienDo;
    private javax.swing.JTextField txtTuoi;
    private javax.swing.JTextField txtdiem1;
    private javax.swing.JTextField txtdiem2;
    private javax.swing.JTextField txtdiem3;
    private javax.swing.JTextField txtdiem4;
    private javax.swing.JTextField txtdiem5;
    private javax.swing.JTextField txtdiem6;
    private javax.swing.JTextField txtdtb;
    private javax.swing.JTextField txthoten;
    // End of variables declaration//GEN-END:variables
}
