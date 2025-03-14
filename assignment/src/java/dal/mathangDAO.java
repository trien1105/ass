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
public class mathangDAO extends DBContext{
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
               String mota =rs.getString("MoTa");
               int maloai=rs.getInt("MaLoai");
               int mncc=rs.getInt("MaNCC");
                String img =rs.getString("img");
                mathang product = new mathang(id, name, price, quantity, date, mota, maloai, mncc, img);
                products.add(product);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
        return products;
    }
}
