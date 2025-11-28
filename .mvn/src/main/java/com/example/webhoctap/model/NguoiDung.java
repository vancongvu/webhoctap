package com.example.webhoctap.model;

public class NguoiDung {
    private int id;
    private String HoTen;
    private String TenDangNhap;
    private String MatKhau;

    public NguoiDung()
    {
        this.id = 0;
        this.HoTen = "";
        this.TenDangNhap = "";
        this.MatKhau = "";
    }
    public NguoiDung(int id, String HoTen, String TenDangNhap, String MatKhau)
    {
        this.id = id;
        this.HoTen = HoTen;
        this.TenDangNhap = TenDangNhap;
        this.MatKhau = MatKhau;
    }

    public int getID()
    {
        return this.id;
    }
    public void setID(int id)
    {
        this.id = id;
    }

    public String getHoTen()
    {
        return this.HoTen;
    }
    public void setHoTen(String hoten)
    {
        this.HoTen = hoten;
    }

    public String getTenDangNhap()
    {
        return this.TenDangNhap;
    }
    public void setTenDangNhap(String tendangnhap)
    {
        this.TenDangNhap = tendangnhap;
    }
    
    public String getMatKhau()
    {
        return this.MatKhau;
    }
    public void setMatKhau(String matkhau)
    {
        this.MatKhau = matkhau;
    }
}
