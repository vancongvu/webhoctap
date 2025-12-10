package com.example.webhoctap.model;

import java.sql.Timestamp;

public class TongQuanBaiLamDS {
    private int id;
    private String tenMonHoc;
    private float tongDiem;
    private int soCauDung;
    private int tongSoCau;
    private Timestamp thoiGianLam;

    public TongQuanBaiLamDS()
    {}

    public TongQuanBaiLamDS(int id, String tenmonhoc, float tongdiem, int socaudung, int tongsocau, Timestamp thoigian)
    {
        this.id = id;
        this.tenMonHoc = tenmonhoc;
        this.tongDiem = tongdiem;
        this.soCauDung = socaudung;
        this.tongSoCau = tongsocau;
        this.thoiGianLam = thoigian;
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
        return this.tenMonHoc;
    }
    public void setTenMonHoc(String tenmonhoc)
    {
        this.tenMonHoc = tenmonhoc;
    }

    public float getTongDiem()
    {
        return this.tongDiem;
    }
    public void setTongDiem(float diem)
    {
        this.tongDiem = diem;
    }

    public int getSoCauDung()
    {
        return this.soCauDung;
    }
    public void setSoCauDung(int scd)
    {
        this.soCauDung = scd;
    }

    public int getTongSoCau()
    {
        return this.tongSoCau;
    }
    public void setTongSoCau(int tsc)
    {
        this.tongSoCau = tsc;
    }

    public Timestamp getThoiGianLam()
    {
        return this.thoiGianLam;
    }
    public void setThoiGianLam(Timestamp tgl)
    {
        this.thoiGianLam = tgl;
    }
}
