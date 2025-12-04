package com.example.webhoctap.model;

public class Quiz {
    private int id;
    private String CauHoi;
    private String DapAnA;
    private String DapAnB;
    private String DapAnC;
    private String DapAnD;
    private int DapAnDung;
    private int MonHocId;

    public Quiz()
    {}
    public Quiz(int id, String cauhoi, String dapana, String dapanb, String dapanc, String dapand, int dapandung, int monhocid)
    {
        this.id = id;
        this.CauHoi = cauhoi;
        this.DapAnA = dapana;
        this.DapAnB = dapanb;
        this.DapAnC = dapanc;
        this.DapAnD = dapand;
        this.DapAnDung = dapandung;
        this.MonHocId = monhocid;
    }

    public int getID()
    {
        return this.id;
    }
    public void setID(int id)
    {
        this.id = id;
    }

    public String getCauHoi()
    {
        return this.CauHoi;
    }
    public void setCauHoi(String cauhoi)
    {
        this.CauHoi = cauhoi;
    }

    public String getDapAnA()
    {
        return this.DapAnA;
    }
    public void setDapAnA(String dapana)
    {
        this.DapAnA = dapana;
    }

    public String getDapAnB()
    {
        return this.DapAnB;
    }
    public void setDapAnB(String dapanb)
    {
        this.DapAnB = dapanb;
    }

    public String getDapAnC()
    {
        return this.DapAnC;
    }
    public void setDapAnC(String dapanc)
    {
        this.DapAnC = dapanc;
    }

    public String getDapAnD()
    {
        return this.DapAnD;
    }
    public void setDapAnD(String dapand)
    {
        this.DapAnD = dapand;
    }

    public int getDapAnDung()
    {
        return this.DapAnDung;
    }

    public void setDapAnDung(int dapandung)
    {
        this.DapAnDung = dapandung;
    }

     public int getMonHocId()
    {
        return this.MonHocId;
    }

    public void setMonHocId(int monhocid)
    {
        this.MonHocId = monhocid;
    }
}
