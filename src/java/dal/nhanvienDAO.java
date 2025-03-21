/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dto.nhanvienDTO;
import java.util.ArrayList;
import java.util.List;
import models.nhanvien;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.phancong;

public class nhanvienDAO extends DBContext {

    public nhanvienDAO() {
        super();
    }

    //R: get all employee
    public List<nhanvien> getAllEmployees() {
        //khai bao danh sach chua cac doi tuong kieu nhanvien
        List<nhanvien> employees = new ArrayList<>();

        try {
            //Tao truy van lay du lieu tu DB
            String sql = "select * from NhanVien";

            //Thuc thi truy van
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            //Doc du lieu tu rs vao list nhanvien
            while (rs.next()) {
                int idNV = rs.getInt("MaNV");
                String name = rs.getString("HoTen");
                String role = rs.getString("ChucVu");
                String email = rs.getString("Email");
                String phoneNumber = rs.getString("SoDienThoai");
                Date startingDate = rs.getDate("NgayVaoLam");
                int idAdmin = rs.getInt("MaAdmin");

                nhanvien nv = new nhanvien(idNV, name, role, email, phoneNumber, startingDate, idAdmin);
                employees.add(nv);
            }

            if (employees.isEmpty()) {
                System.out.println("No employees found in database.");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return employees;
    }

    //R: Get a single employees
    public nhanvien getEmployee(int id) {
        nhanvien emp = null;
        try {
            String sql = "select * from NhanVien where MaNV =" + id;

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int idNV = rs.getInt("MaNV");
                String name = rs.getString("HoTen");
                String role = rs.getString("ChucVu");
                String email = rs.getString("Email");
                String phoneNumber = rs.getString("SoDienThoai");
                Date startingDate = rs.getDate("NgayVaoLam");
                int idAdmin = rs.getInt("MaAdmin");

                emp = new nhanvien(idNV, name, role, email, phoneNumber, startingDate, idAdmin);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return emp;
    }

    //Create nhanvien
    public boolean addEmployee(String name, String chucvu, String email, String sodienthoai, Date ngayvaolam, int maadmin) {

        try {
            String sql = "INSERT INTO nhanvien (HoTen,ChucVu , Email, SoDienThoai, NgayVaoLam, MaAdmin)  \n"
                    + "VALUES (?,?,?,?,?,?)";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, chucvu);
            ps.setString(3, email);
            ps.setString(4, sodienthoai);
            ps.setDate(5, new java.sql.Date(ngayvaolam.getTime()));
            ps.setInt(6, maadmin);

            int count = ps.executeUpdate();

            if (count > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    //U: Update employee
    public boolean upDateEmployee(nhanvien nv) {

        try {
            String sql = "UPDATE NhanVien\n"
                    + "SET HoTen = ?,\n"
                    + "    ChucVu = ?,\n"
                    + "    Email = ?,\n"
                    + "    SoDienThoai = ?,\n"
                    + "    NgayVaoLam = ?,\n"
                    + "    MaAdmin = ?\n"
                    + "WHERE MaNV = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, nv.getHoTen());
            ps.setString(2, nv.getChucVu());
            ps.setString(3, nv.getEmail());
            ps.setString(4, nv.getSoDienThoai());
            ps.setDate(5, new java.sql.Date(nv.getNgayVaoLam().getTime()));
            ps.setInt(6, nv.getMaAdmin());
            ps.setInt(7, nv.getMaNV());

            int count = ps.executeUpdate();
            if (count > 0) {
                return true;
            }

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return false;
    }

    //Get ca làm Của nhân viên
    public List<nhanvienDTO> getDanhSachPhanCong() {
        List<nhanvienDTO> dspc = new ArrayList<>();

        try {
            String sql = "SELECT \n"
                    + "    nv.MaNV, \n"
                    + "    nv.HoTen, \n"
                    + "    cl.TenCa, \n"
                    + "    pc.NgayLam \n"
                    + "FROM NhanVien nv\n"
                    + "LEFT JOIN PhanCong pc ON nv.MaNV = pc.MaNV\n"
                    + "LEFT JOIN CaLam cl ON pc.MaCa = cl.MaCa\n"
                    + "ORDER BY nv.MaNV, pc.NgayLam;";

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int maNV = rs.getInt("MaNV");
                String hoTen = rs.getString("HoTen");
                String ca = rs.getString("TenCa");
                Date ngaylam = rs.getDate("NgayLam");

                nhanvienDTO phancong = new nhanvienDTO(maNV, hoTen, ca, ngaylam);

                dspc.add(phancong);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return dspc;
    }

    //Add ca làm, ngày làm cho nhân viên
    public boolean addCaLamNgayLam(int Manv, int maca, Date ngaylam) {

        try {
            String sql = "insert into PhanCong(MaNV, MaCa, NgayLam) values (?,?,?)";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, Manv);
            ps.setInt(2, maca);
            ps.setDate(3, new java.sql.Date(ngaylam.getTime()));

            int count = ps.executeUpdate();
            if (count > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    //Check trùng ca,ngày làm của dăng kí ca, ngày làm
    public boolean kiemTraTrungCaLam(int calam, Date ngaylam) {
        try {
            String sql = "SELECT COUNT(*) FROM phancong WHERE MaCa = ? AND NgayLam = ?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, calam);
            ps.setDate(2, new java.sql.Date(ngaylam.getTime()));

            ResultSet rs = ps.executeQuery(); // Dùng executeQuery() thay vì executeUpdate()

            if (rs.next()) { // Kiểm tra xem có kết quả trả về không
                int count = rs.getInt(1); // Lấy giá trị COUNT(*)
                return count > 0; // Nếu count > 0 tức là đã có dữ liệu trùng
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //Check trùng id nhan vien
    public boolean checkIdNhanVien(int id) {

        try {
            String sql = "SELECT COUNT(*) FROM nhanvien WHERE MaNV = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Nếu COUNT(*) > 0, nghĩa là ID đã tồn tại
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }

        return false;
    }

    //search by id 
    public List<nhanvienDTO> searchID(int id) {
        List<nhanvienDTO> nv = new ArrayList<>();

        try {
            String sql = "SELECT \n"
                    + "    nv.MaNV, \n"
                    + "    nv.HoTen, \n"
                    + "    cl.TenCa, \n"
                    + "    pc.NgayLam \n"
                    + "FROM NhanVien nv\n"
                    + "LEFT JOIN PhanCong pc ON nv.MaNV = pc.MaNV\n"
                    + "LEFT JOIN CaLam cl ON pc.MaCa = cl.MaCa\n"
                    + "WHERE nv.MaNV = ?"
                    + "ORDER BY nv.MaNV, pc.NgayLam;";;
        } catch (Exception e) {
        }

        return nv;
    }

    //search by name
    //search by id 
    public List<nhanvienDTO> searchHoTen(String name) {
        List<nhanvienDTO> nv = new ArrayList<>();

        try {
            String sql = "SELECT \n"
                    + "    nv.MaNV, \n"
                    + "    nv.HoTen, \n"
                    + "    cl.TenCa, \n"
                    + "    pc.NgayLam \n"
                    + "FROM NhanVien nv\n"
                    + "LEFT JOIN PhanCong pc ON nv.MaNV = pc.MaNV\n"
                    + "LEFT JOIN CaLam cl ON pc.MaCa = cl.MaCa\n"
                    + "WHERE nv.HoTen COLLATE SQL_Latin1_General_CP1_CI_AS LIKE ? "
                    + "ORDER BY nv.MaNV, pc.NgayLam;";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int manv = rs.getInt("MaNV");
                String hoten = rs.getString("HoTen");
                String tenca = rs.getString("TenCa");
                Date ngaylam = rs.getDate("NgayLam");

                nhanvienDTO nv1 = new nhanvienDTO(manv, hoten, tenca, ngaylam);

                nv.add(nv1);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return nv;
    }

}
