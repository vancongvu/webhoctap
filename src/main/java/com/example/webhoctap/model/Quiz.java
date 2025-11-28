package com.example.webhoctap.model;

public class Quiz {
    private int id;
    private String CauHoi;
    private String DapAnA;
    private String DapAnB;
    private String DapAnC;
    private String DapAnD;
    private char DapAnDung;

    public Quiz()
    {}
    public Quiz(int id, String cauhoi, String dapana, String dapanb, String dapanc, String dapand, char dapandung)
    {
        this.id = id;
        this.CauHoi = cauhoi;
        this.DapAnA = dapana;
        this.DapAnB = dapanb;
        this.DapAnC = dapanc;
        this.DapAnD = dapand;
        this.DapAnDung = dapandung;
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

    public char getDapAnDung()
    {
        return this.DapAnDung;
    }
    public void setDapAnDung(char dapandung)
    {
        this.DapAnDung = dapandung;
    }
}
