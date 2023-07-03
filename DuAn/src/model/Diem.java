/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Diem {
    private String maSv;
    private String hoTen;
    private String monHocID;
    private float lab1;
    private float lab2;
    private float lab3;
    private float lab4;
    private float tienDo;
    private float asm1;
    private float asm2;

    public Diem() {
    }

    public Diem(String maSv, String hoTen, String monHocID, float lab1, float lab2, float lab3, float lab4, float tienDo, float asm1, float asm2) {
        this.maSv = maSv;
        this.hoTen = hoTen;
        this.monHocID = monHocID;
        this.lab1 = lab1;
        this.lab2 = lab2;
        this.lab3 = lab3;
        this.lab4 = lab4;
        this.tienDo = tienDo;
        this.asm1 = asm1;
        this.asm2 = asm2;
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

    public float getLab1() {
        return lab1;
    }

    public void setLab1(float lab1) {
        this.lab1 = lab1;
    }

    public float getLab2() {
        return lab2;
    }

    public void setLab2(float lab2) {
        this.lab2 = lab2;
    }

    public float getLab3() {
        return lab3;
    }

    public void setLab3(float lab3) {
        this.lab3 = lab3;
    }

    public float getLab4() {
        return lab4;
    }

    public void setLab4(float lab4) {
        this.lab4 = lab4;
    }

    public float getTienDo() {
        return tienDo;
    }

    public void setTienDo(float tienDo) {
        this.tienDo = tienDo;
    }

    public float getAsm1() {
        return asm1;
    }

    public void setAsm1(float asm1) {
        this.asm1 = asm1;
    }

    public float getAsm2() {
        return asm2;
    }

    public void setAsm2(float asm2) {
        this.asm2 = asm2;
    }

   
    public float dtb(){
        float dtb = (((lab1+lab2+lab3+lab4+tienDo)*5)+asm1*30 + asm2*45 )/100;
        return dtb;
    } 
}
