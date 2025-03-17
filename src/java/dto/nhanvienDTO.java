
package dto;
import java.util.Date;


public class nhanvienDTO {
    private int maNV;
    private String hoTen;
    private String tenCa;
    private Date ngayLam;

    public nhanvienDTO(int maNV, String hoTen, String tenCa, Date ngayLam) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.tenCa = tenCa;
        this.ngayLam = ngayLam;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getTenCa() {
        return tenCa;
    }

    public void setTenCa(String tenCa) {
        this.tenCa = tenCa;
    }

    public Date getNgayLam() {
        return ngayLam;
    }

    public void setNgayLam(Date ngayLam) {
        this.ngayLam = ngayLam;
    }

    
    
}
