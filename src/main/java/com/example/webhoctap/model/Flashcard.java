package com.example.webhoctap.model;

public class Flashcard {
    private int id;
    private String MatTruoc;
    private String MatSau;
    private int MonHocId;
    private String TenMonHoc;

    public Flashcard()
    {}
    public Flashcard(int id, String mattruoc, String matsau, int monhocid, String tenmonhoc)
    {
        this.id = id;
        this.MatTruoc = mattruoc;
        this.MatSau = matsau;
        this.MonHocId = monhocid;
        this.TenMonHoc = tenmonhoc;
    }

    public int getID()
    {
        return this.id;
    }
    public void setID(int id)
    {
        this.id = id;
    }

    public String getMatTruoc()
    {
        return this.MatTruoc;
    }
    public void setMatTruoc(String mattruoc)
    {
        this.MatTruoc = mattruoc;
    }
    
    public String getMatSau()
    {
        return this.MatSau;
    }

    public void setMatSau(String matsau)
    {
        this.MatSau = matsau;
    }

    public int getMonHocId()
    {
        return this.MonHocId;
    }
    
    public void setMonHocId(int monhocid)
    {
        this.MonHocId = monhocid;
    }

    public String getTenMonHoc()
    {
        return this.TenMonHoc;
    }

    public void setTenMonHoc(String tenmonhoc)
    {
        this.TenMonHoc = tenmonhoc;
    }
}
