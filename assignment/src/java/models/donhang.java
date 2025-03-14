/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Date;

/**
 *
 * @author PC
 */
public class donhang {
    int madh;
    int manv;
    int makh;
    Date ngaymua;
    double tongtien;

    public donhang(int madh, int manv, int makh, Date ngaymua, double tongtien) {
        this.madh = madh;
        this.manv = manv;
        this.makh = makh;
        this.ngaymua = ngaymua;
        this.tongtien = tongtien;
    }

    public int getMadh() {
        return madh;
    }

    public void setMadh(int madh) {
        this.madh = madh;
    }

    public int getManv() {
        return manv;
    }

    public void setManv(int manv) {
        this.manv = manv;
    }

    public int getMakh() {
        return makh;
    }

    public void setMakh(int makh) {
        this.makh = makh;
    }

    public Date getNgaymua() {
        return ngaymua;
    }

    public void setNgaymua(Date ngaymua) {
        this.ngaymua = ngaymua;
    }

    public double getTongtien() {
        return tongtien;
    }

    public void setTongtien(double tongtien) {
        this.tongtien = tongtien;
    }
    
    
}
