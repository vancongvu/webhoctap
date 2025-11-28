package com.example.webhoctap.model;

public class ChiTietBaiLam {
    private int id;
    private int DapAnChon;
    private boolean KiemTraDS;

    public ChiTietBaiLam()
    {}
    public ChiTietBaiLam(int id, int dapanchon)
    {
        this.id = id;
        this.DapAnChon = dapanchon;
    }

    public int getID()
    {
        return this.id;
    }
    public void setID(int id)
    {
        this.id = id;
    }

    public int getDapAnChon()
    {
        return this.DapAnChon;
    }
    public void setDapAnChon(int dapanchon)
    {
        this.DapAnChon = dapanchon;
    }
    
    public boolean getKiemTraDS()
    {
        return this.KiemTraDS;
    }
    public void setKiemTraDS(boolean kiemtrads)
    {
        this.KiemTraDS = kiemtrads;
    }
}
