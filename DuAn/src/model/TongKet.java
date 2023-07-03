/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class TongKet {
    private String maSv;
    private String hoTen;
    private String monHocID;
    private float dtb;

    public TongKet() {
    }

    public TongKet(String maSv, String hoTen, String monHocID, float dtb) {
        this.maSv = maSv;
        this.hoTen = hoTen;
        this.monHocID = monHocID;
        this.dtb = dtb;
    }

    public String getMaSv() {
        return maSv;
    }

    public void setMaSv(String maSv) {
        this.maSv = maSv;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMonHocID() {
        return monHocID;
    }

    public void setMonHocID(String monHocID) {
        this.monHocID = monHocID;
    }

    public float getDtb() {
        return dtb;
    }

    public void setDtb(float dtb) {
        this.dtb = dtb;
    }
    public String getHocLuc(){
        if(dtb<= 3){
            return "Kém";
        }else if(dtb<=5){
            return "Yếu";
        }else if(dtb<=7){
            return "Trung Bình";
        }else if(dtb<=8){
            return "Khá";
        }else if(dtb<=9){
            return "Giỏi";
        }
        return "Xuất Sắc";     
    }
    public String hocBong(){
       if(dtb<8.0){
           return "Không đủ điều kiện!";
       } else if(dtb < 8.5){
           return "đủ điều kiện xét học bổng";
       }
       else if(dtb<9.0){
           return "đủ điều kiện xét học bổng";
       }
       else if(dtb<9.5){
           return "đủ điều kiện xét học bổng";
       }
       return "đủ điều kiện xét học bổng";
    }
}
    
