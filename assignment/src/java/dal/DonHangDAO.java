/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dto.Donhangdto;
import jakarta.servlet.jsp.jstl.sql.Result;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
/**
 *
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * cassssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss
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
        while(rs.next() ){
            double monney = rs.getDouble("tongtien");
            Date ngaymua = rs.getDate("ngaymua");
            Donhangdto d = new Donhangdto(monney, ngaymua);
            thunhap.add(d);
        }
        } catch (Exception e) {
        }
      
       
       
        return thunhap;

    }

}
