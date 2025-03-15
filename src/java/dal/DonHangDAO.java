/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dto.Donhangdto;
import jakarta.servlet.jsp.jstl.sql.Result;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author PC
 */
public class DonHangDAO extends DBContext {

    public DonHangDAO() {
        super();
    }

    public List<Donhangdto> thunhap1() {
        List<Donhangdto> thunhap = new ArrayList<>();
        try {
            String sql = "SELECT SUM(tongtien) as tongtien ,NgayMua FROM DonHang\n"
                    + "WHERE ngaymua >= CAST(DATEADD(DAY, -1, GETDATE()) AS DATE) \n"
                    + "  AND NgayMua < CAST(DATEADD(DAY, 1, GETDATE()) AS DATE)\n"
                    + "  group by ngaymua\n"
                    + "order by NgayMua desc";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                double monney = rs.getDouble("tongtien");
                Date ngaymua = rs.getDate("ngaymua");
                Donhangdto d = new Donhangdto(monney, ngaymua);
                thunhap.add(d);
            }
        } catch (Exception e) {
        }
        return thunhap;

    }

    public boolean taodonhang(int makh) {

        try {

            String sql = "INSERT INTO DonHang (MaKH,tongtien) VALUES (?,0)";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, makh);
            int count = ps.executeUpdate();
            if (count > 0) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public boolean taodonhangDate(int makh) {

        try {

            String sql = "INSERT INTO DonHang (MaKH,NgayMua,tongtien) VALUES (?,?,0)";
            LocalDate date = LocalDate.now();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, makh);
            ps.setDate(2, Date.valueOf(date));
            int count = ps.executeUpdate();
            if (count > 0) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public boolean taochitietdonhang(int madh, int mamh, int soluong) {

        try {
            String sql = "INSERT INTO ChiTietDonHang (MaDH,MaMH,SoLuong,Giaban) VALUES (?,?,?,0)";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, madh);
            ps.setInt(2, mamh);
            ps.setInt(3, soluong);
            int count = ps.executeUpdate();
            if (count > 0) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public int madhdatenull(int makh) {
        int id = 0;

        try {
            String sql = "select top 1 madh from DonHang where MaNV Is null and NgayMua Is null and makh=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, makh);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("madh");
            }

        } catch (Exception e) {
        }
        return id;
    }

   public boolean updategiahaibang() {
       try {
           // Cập nhật GiaBan trong bảng ChiTietDonHang
           String sql1 = "UPDATE ChiTietDonHang "
                       + "SET GiaBan = SoLuong * (SELECT Gia FROM MatHang WHERE MatHang.MaMH = ChiTietDonHang.MaMH)";
           
           PreparedStatement ps1 = connection.prepareStatement(sql1);
           int count1 = ps1.executeUpdate();
           
           // Cập nhật tongtien trong bảng DonHang
           String sql2 = "UPDATE DonHang "
                       + "SET tongtien = (SELECT SUM(GiaBan) FROM ChiTietDonHang WHERE ChiTietDonHang.MaDH = DonHang.MaDH)";
        
           PreparedStatement ps2 = connection.prepareStatement(sql2);
        int count2 = ps2.executeUpdate();
           return count1 > 0 && count2 > 0; // Nếu có ít nhất một bản ghi được cập nhật thì trả về true
       } catch (Exception e) {
           e.printStackTrace(); // Hiển thị lỗi để debug
       }
       return false;
   }
   
   public int manvnull(int makh){
       int id =0;
       try {
            String sql = "select top 1 madh from DonHang where MaNV Is null and NgayMua is not null and MaKH =?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, makh);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("madh");
            }

        } catch (Exception e) {
        }
       
       
       return id;
       
       
   }
   
   
    public static void main(String[] args) {
        DonHangDAO dh = new DonHangDAO();
        boolean tf = dh.updategiahaibang();
        System.out.println(tf);
    }

}
