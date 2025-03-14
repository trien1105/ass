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
public class mathang {
    private int mamh;
    private String tenmh;
    private double gia;
    private int soluongton;
    private Date hsd;
    private String mota;
    private int maloai;
    private int mancc;
    private String img;

    public mathang(int mamh, String tenmh, double gia, int soluongton, Date hsd, String mota, int maloai, int mancc, String img) {
        this.mamh = mamh;
        this.tenmh = tenmh;
        this.gia = gia;
        this.soluongton = soluongton;
        this.hsd = hsd;
        this.mota = mota;
        this.maloai = maloai;
        this.mancc = mancc;
        this.img = img;
    }

    public int getMamh() {
        return mamh;
    }

    public void setMamh(int mamh) {
        this.mamh = mamh;
    }

    public String getTenmh() {
        return tenmh;
    }

    public void setTenmh(String tenmh) {
        this.tenmh = tenmh;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public int getSoluongton() {
        return soluongton;
    }

    public void setSoluongton(int soluongton) {
        this.soluongton = soluongton;
    }

    public Date getHsd() {
        return hsd;
    }

    public void setHsd(Date hsd) {
        this.hsd = hsd;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }

    public int getMancc() {
        return mancc;
    }

    public void setMancc(int mancc) {
        this.mancc = mancc;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
    
    
}
