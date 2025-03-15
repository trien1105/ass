/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

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

    public List<Integer> idspbanchay() {
        List<Integer> id = new ArrayList<>();
        try {
            String sql = "SELECT TOP 5 \n"
                    + "    MatHang.MaMH, \n"
                    + "    MatHang.TenMH, \n"
                    + "    SUM(ChiTietDonHang.SoLuong) AS soluong \n"
                    + "FROM MatHang \n"
                    + "JOIN ChiTietDonHang ON MatHang.MaMH = ChiTietDonHang.MaMH \n"
                    + "JOIN DonHang ON ChiTietDonHang.MaDH = DonHang.MaDH \n"
                    + "WHERE DonHang.NgayMua IS NOT NULL  \n"
                    + "GROUP BY MatHang.MaMH, MatHang.TenMH \n"
                    + "ORDER BY soluong DESC;";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            // Đọc dữ liệu vào list products
            while (rs.next()) {
                int idp = rs.getInt("MaMH");
                id.add(idp);
            }

        } catch (Exception e) {

        }
        return id;
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
        for (int i = 0; i < idspbanchay().size(); i++) {
            int a = Integer.parseInt(idspbanchay().get(i).toString());
            mathang product = null;
            product = getproduct(a);
            if (product != null) {
                list.add(product);
            }

        }

        return list;

    }

    public static void main(String[] args) {
        mathangDAO mh = new mathangDAO();
        System.out.println(mh.spbanchay().size());

        for (int i = 0; i < mh.spbanchay().size(); i++) {
            System.out.println(mh.spbanchay().get(i).getTenmh());
        }

        for (int i = 0; i < mh.idspbanchay().size(); i++) {
            System.out.println(mh.idspbanchay().get(i));
        }
    }

}
