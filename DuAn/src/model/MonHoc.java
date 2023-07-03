/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class MonHoc {
    private String monhocID;
    private String tenMh;
    private String gvcn;
    private int soTinChi;

    public MonHoc() {
    }

    public MonHoc(String monhocID, String tenMh, String gvcn, int soTinChi) {
        this.monhocID = monhocID;
        this.tenMh = tenMh;
        this.gvcn = gvcn;
        this.soTinChi = soTinChi;
    }

    public String getMonhocID() {
        return monhocID;
    }

    public void setMonhocID(String monhocID) {
        this.monhocID = monhocID;
    }

    public String getTenMh() {
        return tenMh;
    }

    public void setTenMh(String tenMh) {
        this.tenMh = tenMh;
    }

    public String getGvcn() {
        return gvcn;
    }

    public void setGvcn(String gvcn) {
        this.gvcn = gvcn;
    }

    public int getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(int soTinChi) {
        this.soTinChi = soTinChi;
    }
    
}
