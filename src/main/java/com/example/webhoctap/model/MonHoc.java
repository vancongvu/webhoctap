package com.example.webhoctap.model;

public class MonHoc {
    private int id;
    private String TenMonHoc;
    private String MoTa;

    public MonHoc()
    {
        this.id = 0;
        this.TenMonHoc = "";
        this.MoTa = "";
    }
    public MonHoc(int id, String TenMonHoc, String MoTa)
    {
        this.id = id;
        this.TenMonHoc = TenMonHoc;
        this.MoTa = MoTa;
    }

    public int getID()
    {
        return this.id;
    }
    public void setID(int id)
    {
        this.id = id;
    }

    public String getTenMonHoc()
    {
        return this.TenMonHoc;
    }
    public void setTenMonHoc(String tenmonhoc)
    {
        this.TenMonHoc = tenmonhoc;
    }

    public String getMoTa()
    {
        return this.MoTa;
    }
    public void setMoTa(String mota)
    {
        this.MoTa = mota;
    }
}
