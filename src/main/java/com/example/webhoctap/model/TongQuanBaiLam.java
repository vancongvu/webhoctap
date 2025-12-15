package com.example.webhoctap.model;

import java.sql.Timestamp;

public class TongQuanBaiLam {
    private int id;
    private float TongDiem;
    private int SoCauDung;
    private int TongSoCau;
    private Timestamp ThoiGian;
    private int MonHocID;
    private int NguoiDungID;

    public TongQuanBaiLam()
    {}
    public TongQuanBaiLam(int id, float TongDiem, int SoCauDung, int TongSoCau, Timestamp ThoiGian, int MonHocID, int NguoiDungID)
    {
        this.id = id;
        this.TongDiem = TongDiem;
        this. SoCauDung = SoCauDung;
        this.TongSoCau = TongSoCau;
        this.ThoiGian = ThoiGian;
        this.MonHocID = MonHocID;
        this.NguoiDungID = NguoiDungID;
    }

    public int getID()
    {
        return this.id;
    }
    public void setID(int id)
    {
        this.id = id;
    }
    
    public float getTongDiem()
    {
        return this.TongDiem;
    }
    public void setTongDiem(float TongDiem)
    {
        this.TongDiem = TongDiem;
    }

    public int getSoCauDung()
    {
        return this.SoCauDung;
    }
    public void setSoCauDung(int SoCauDung)
    {
        this.SoCauDung = SoCauDung;
    }

    public int getTongSoCau()
    {
        return this.TongSoCau;
    }
    public void setTongSoCau(int TongSoCau)
    {
        this.TongSoCau = TongSoCau;
    }

    public Timestamp getThoiGian()
    {
        return this.ThoiGian;
    }
    public void setThoiGian(Timestamp ThoiGian)
    {
        this.ThoiGian = ThoiGian;
    }

    public int getMonHocId()
    {
        return this.MonHocID;
    }
    public void setMonHocId(int id)
    {
        this.MonHocID = id;
    }
    
    public int getNguoiDungId()
    {
        return this.NguoiDungID;
    }
    public void setNguoiDungId(int id)
    {
        this.NguoiDungID = id;
    }
}
