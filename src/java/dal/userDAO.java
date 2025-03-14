/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import user.account;
import user.khachhang;

/**
 *
 * @author PC
 */
public class userDAO extends DBContext{

    public userDAO() {
        super();
    }
    public List<account> getAllacc() throws Exception {
        // Khai báo danh sách chứa các đối tượng kiểu Product
        List<account> listacc = new ArrayList<>();
        try {
            // Tạo truy vấn lấy dữ liệu từ DB
            String sql = "select * from DangNhap";
            // Thực thi truy vấn
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            // Đọc dữ liệu vào list products
            while (rs.next()) {
                // Đọc từng bản ghi dữ liệu để đưa vào đối tượng kiểu Product
                int id = rs.getInt("MaDangNhap"); // Tên các trường từ DB
                String email = rs.getString("Email");
                String matkhau = rs.getString("MatKhau");
                int role = rs.getInt("Role");
                int manguoidung = rs.getInt("manguoidung");
                
                account acc = new account(id, email, matkhau, role, manguoidung);
                listacc.add(acc);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
        return listacc;
    }
    
     public account getacc (String email) {
        account acc = null;
        try {
            String sql = "select * from DangNhap where Email= "+"'"+email+"'" ;
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) { // Kiểm tra xem có tồn tại dữ liệu từ ResultSet không
                int id = rs.getInt("MaDangNhap"); // Tên các trường từ DB
                String uemail = rs.getString("Email");
                String matkhau = rs.getString("MatKhau");
                int role = rs.getInt("Role");
                int manguoidung = rs.getInt("manguoidung");
                acc= new account(id, uemail, matkhau, role, manguoidung);
            }
        } catch (Exception e) {
           acc=null;
        }
        return acc;
    }
     
     
     public boolean create1(account acc) {
        try {
            String sql = "INSERT INTO DangNhap ( Email, MatKhau,Role,manguoidung)\n"
                    + "VALUES (?, ?, ?, ?);";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, acc.getEmail());
            st.setString(2, acc.getMatkhau());
            st.setInt(3, 3);
            st.setInt(4, makh(acc.getEmail()));


            int count = st.executeUpdate();
            if (count > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }
     
     
     public boolean create2(khachhang cus) {
        try {
            String sql = "INSERT INTO KhachHang( HoTen, SoDienThoai,Email)\n"
                    + "VALUES (?, ?, ?);";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, cus.getHoten());
            st.setString(2, cus.getSodienthoai());
            st.setString(3, cus.getEmail());
            int count = st.executeUpdate();
            if (count > 0) {
               
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }
     
     
     public  int makh(String email){
         
         int makh=0;
         try {
             String sql="select makh from KhachHang where Email = " +"'"+email+"'";
             PreparedStatement ps = connection.prepareStatement(sql);
             
             ResultSet rs = ps.executeQuery();
             
             if(rs.next()){
                 makh = rs.getInt("MaKH");
             }
             if(makh>0){
                 return makh;
             }
             
         } catch (Exception e) {
         }
          return  0;
     }
     
             
           
     
}
