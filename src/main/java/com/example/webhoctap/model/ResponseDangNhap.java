package com.example.webhoctap.model;

public class ResponseDangNhap {
    private int id;
    private String HoTen;
    private boolean TrangThai;

    public ResponseDangNhap()
    {}

    public ResponseDangNhap(int id, String hoten, boolean tt)
    {
        this.id = id;
        this.HoTen = hoten;
        this.TrangThai = tt;
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

    public boolean getTrangThai()
    {
        return this.TrangThai;
    }
    public void setTrangThai(boolean tt)
    {
        this.TrangThai = tt;
    }

}
