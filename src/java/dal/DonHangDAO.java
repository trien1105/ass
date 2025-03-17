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
    
    public boolean inserttoday(int makh) {

         String sql = "UPDATE DonHang SET NgayMua = ? WHERE NgayMua IS NULL and MaNV IS Null and MaKH= ?";
    
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setDate(1, Date.valueOf(LocalDate.now())); 
        ps.setInt(2, makh);
        int rowsUpdated = ps.executeUpdate();
        return rowsUpdated > 0;

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

    public int mamhdatenull(int makh) {
        int id = 0;

        try {
            String sql = "select top 1 mamh from DonHang where MaNV Is null and NgayMua Is null and makh=?";
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

    public boolean capNhatSoLuongTon(int maMH, int soLuongMua) {
        try {
           //Lấy số lượng tồn kho hiện tại
            String sqlCheck = "SELECT SoLuongTon FROM MatHang WHERE MaMH = ?";
            PreparedStatement psCheck = connection.prepareStatement(sqlCheck);
            psCheck.setInt(1, maMH);
            ResultSet rs = psCheck.executeQuery();

            if (rs.next()) {
                int soLuongTonHienTai = rs.getInt("SoLuongTon");

              // Kiểm tra nếu số lượng mua nhỏ hơn số lượng tồn 
                if (soLuongMua <= soLuongTonHienTai) {
                    //Nếu hợp lệ thì cập nhật số lượng tồn kho
                    String sqlUpdate = "UPDATE MatHang SET SoLuongTon = SoLuongTon - ? WHERE MaMH = ?";
                    PreparedStatement psUpdate = connection.prepareStatement(sqlUpdate);
                    psUpdate.setInt(1, soLuongMua);
                    psUpdate.setInt(2, maMH);

                    int rowsUpdated = psUpdate.executeUpdate();
                    return rowsUpdated > 0;
                } 
            }
        } catch (Exception e) {
         
        }
        return false;
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
           if(count1>0&&count2>0){
               return true;
           }
        } catch (Exception e) {
            e.printStackTrace(); // Hiển thị lỗi để debug
        }
        return false;
    }

    public int mamhnvnull(int makh) {
        int id = 0;
        try {
            String sql = "select top 1 madh from DonHang where MaNV Is null and NgayMua is not null and MaKH =? order by MaDH desc";
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
    
    public double totalprice(int makh){
        double totalprice=0;
        try {
            String sql = "select top 1 tongtien from DonHang where MaNV Is null and NgayMua is not null and MaKH =? order by MaDH desc";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, makh);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                totalprice=rs.getDouble("tongtien");
            }

        } catch (Exception e) {
        }
        
        return totalprice;
    }
    
    public double totalpricegiohang(int makh){
        double totalprice=0;
        try {
            String sql = "select top 1 tongtien from DonHang where MaNV Is null and NgayMua is null and MaKH =? order by MaDH desc";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, makh);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                totalprice=rs.getDouble("tongtien");
            }

        } catch (Exception e) {
        }
        
        return totalprice;
    }
    
    
    
    

    public static void main(String[] args) {
        DonHangDAO dh = new DonHangDAO();
        boolean tf = dh.updategiahaibang();
        System.out.println(tf);
    }

}
