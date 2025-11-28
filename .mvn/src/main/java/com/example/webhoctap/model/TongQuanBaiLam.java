package com.example.webhoctap.model;

import java.sql.Timestamp;

public class TongQuanBaiLam {
    private int id;
    private float TongDiem;
    private int SoCauDung;
    private int TongSoCau;
    private Timestamp ThoiGian;

    public TongQuanBaiLam()
    {}
    public TongQuanBaiLam(int id, float TongDiem, int SoCauDung, int TongSoCau, Timestamp ThoiGian)
    {
        this.id = id;
        this.TongDiem = TongDiem;
        this. SoCauDung = SoCauDung;
        this.TongSoCau = TongSoCau;
        this.ThoiGian = ThoiGian;
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
}
