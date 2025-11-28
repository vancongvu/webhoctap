package com.example.webhoctap.model;

public class ChiTietBaiLam {
    private int id;
    private char DapAnChon;
    private boolean KiemTraDS;

    public ChiTietBaiLam()
    {}
    public ChiTietBaiLam(int id, char dapanchon, boolean kiemtrads)
    {
        this.id = id;
        this.DapAnChon = dapanchon;
        this.KiemTraDS = kiemtrads;
    }

    public int getID()
    {
        return this.id;
    }
    public void setID(int id)
    {
        this.id = id;
    }

    public char getDapAnChon()
    {
        return this.DapAnChon;
    }
    public void setDapAnChon(char dapanchon)
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
