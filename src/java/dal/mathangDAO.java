/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dto.mathangdhctdto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import models.mathang;

/**
 *
 * @author PC
 */
public class mathangDAO extends DBContext {

    public mathangDAO() {
        super();
    }

    public List<mathang> getAllProducts() throws Exception {
        // Khai báo danh sách chứa các đối tượng kiểu Product
        List<mathang> products = new ArrayList<>();
        try {
            // Tạo truy vấn lấy dữ liệu từ DB
            String sql = "select*from MatHang";
            // Thực thi truy vấn
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            // Đọc dữ liệu vào list products
            while (rs.next()) {
                // Đọc từng bản ghi dữ liệu để đưa vào đối tượng kiểu Product
                int id = rs.getInt("MaMH"); // Tên các trường từ DB
                String name = rs.getString("TenMH");
                double price = rs.getDouble("Gia");
                int quantity = rs.getInt("SoLuongTon");
                Date date = rs.getDate("HSD");
                String mota = rs.getString("MoTa");
                int maloai = rs.getInt("MaLoai");
                int mncc = rs.getInt("MaNCC");
                String img = rs.getString("img");
                mathang product = new mathang(id, name, price, quantity, date, mota, maloai, mncc, img);
                products.add(product);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
        return products;
    }

 

    public mathang getproduct(int idp) {
        mathang mathang = null;
        try {
            String sql = "select*from MatHang where mamh = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, idp);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("MaMH"); // Tên các trường từ DB
                String name = rs.getString("TenMH");
                double price = rs.getDouble("Gia");
                int quantity = rs.getInt("SoLuongTon");
                Date date = rs.getDate("HSD");
                String mota = rs.getString("MoTa");
                int maloai = rs.getInt("MaLoai");
                int mncc = rs.getInt("MaNCC");
                String img = rs.getString("img");
                mathang = new mathang(id, name, price, quantity, date, mota, maloai, mncc, img);
            }

        } catch (Exception e) {
        }
        return mathang;
    }

    public List<mathang> spbanchay() {
    List<mathang> list = new ArrayList<>();
    try {
        String sql = "SELECT TOP 5 MH.MaMH, MH.TenMH, MH.Gia, SUM(CT.SoLuong) AS SoLuong, MH.HSD, MH.MoTa, MH.MaLoai, MH.MaNCC, MH.img " +
                     "FROM MatHang MH " +
                     "JOIN ChiTietDonHang CT ON MH.MaMH = CT.MaMH " +
                     "GROUP BY MH.MaMH, MH.TenMH, MH.Gia, MH.HSD, MH.MoTa, MH.MaLoai, MH.MaNCC, MH.img " +
                     "ORDER BY SUM(CT.SoLuong) DESC";   
        
        PreparedStatement st = connection.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        
        while (rs.next()) {
            int id = rs.getInt("MaMH"); 
            String name = rs.getString("TenMH");
            double price = rs.getDouble("Gia");  
            int quantity = rs.getInt("SoLuong");
            Date date = rs.getDate("HSD");
            String mota = rs.getString("MoTa");
            int maloai = rs.getInt("MaLoai");   
            int mncc = rs.getInt("MaNCC");     
            String img = rs.getString("img");
            
            mathang product = new mathang(id, name, price, quantity, date, mota, maloai, mncc, img);
            list.add(product);
        }
        
    } catch (Exception e) {
      
    }
    return list;
}


    public List<mathangdhctdto> spdamua(int idkh) {
        List<mathangdhctdto> list = new ArrayList<>();

        try {
            String sql = "SELECT CT.MaMH, MH.TenMH,MH.Gia, SUM(CT.SoLuong) AS SoLuong,MH.HSD,Mh.MoTa,MH.img\n"
                    + "FROM DonHang DH\n"
                    + "JOIN ChiTietDonHang CT ON DH.MaDH = CT.MaDH\n"
                    + "JOIN MatHang MH ON CT.MaMH = MH.MaMH\n"
                    + "WHERE DH.MaKH = ? and NgayMua is not null\n"
                    + "GROUP BY CT.MaMH, MH.TenMH,MH.Gia,MH.HSD,Mh.MoTa,MH.img";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idkh);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("MaMH"); // Tên các trường từ DB
                String name = rs.getString("TenMH");
                double price = rs.getDouble("Gia");
                int quantity = rs.getInt("SoLuong");
                Date date = rs.getDate("HSD");
                String mota = rs.getString("MoTa");
                String img = rs.getString("img");
                mathangdhctdto product = new mathangdhctdto(id, name, price, quantity, date, mota, img);
                list.add(product);
            }

        } catch (Exception e) {
        }

        return list;
    }

    public List<mathang> hangtrongio(int idkh) {
        List<mathang> list = new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT MH.MaMH, MH.TenMH,Mh.Gia,CT.SoLuong, MH.HSD, MH.MoTa,MH.MaLoai,MH.MaNCC, MH.img\n"
                    + "FROM DonHang DH\n"
                    + "JOIN ChiTietDonHang CT ON DH.MaDH = CT.MaDH\n"
                    + "JOIN MatHang MH ON CT.MaMH = MH.MaMH\n"
                    + "WHERE DH.MaKH = ? and dh.MaNV is null and dh.NgayMua is null";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idkh);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("MaMH"); // Tên các trường từ DB
                String name = rs.getString("TenMH");
                double price = rs.getDouble("Gia");
                int quantity = rs.getInt("SoLuong");
                Date date = rs.getDate("HSD");
                String mota = rs.getString("MoTa");
                int maloai = rs.getInt("MaLoai");
                int mncc = rs.getInt("MaNCC");
                String img = rs.getString("img");
                mathang product = new mathang(id, name, price, quantity, date, mota, maloai, mncc, img);
                list.add(product);
            }

        } catch (Exception e) {
        }

        return list;
        }

   
    
    
      

}
