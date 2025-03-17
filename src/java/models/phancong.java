

package models;

import java.util.Date;


public class phancong {
    private int maPhanCong;
    private int maNV;
    private int maCa;
    private Date ngayLam;

    public phancong(int maPhanCong, int maNV, int maCa, Date ngayLam) {
        this.maPhanCong = maPhanCong;
        this.maNV = maNV;
        this.maCa = maCa;
        this.ngayLam = ngayLam;
    }

    public int getMaPhanCong() {
        return maPhanCong;
    }

    public void setMaPhanCong(int maPhanCong) {
        this.maPhanCong = maPhanCong;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public int getMaCa() {
        return maCa;
    }

    public void setMaCa(int maCa) {
        this.maCa = maCa;
    }

    public Date getNgayLam() {
        return ngayLam;
    }

    public void setNgayLam(Date ngayLam) {
        this.ngayLam = ngayLam;
    }
    
    
}
