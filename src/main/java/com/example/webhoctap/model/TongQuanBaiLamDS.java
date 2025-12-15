package com.example.webhoctap.model;


public class TongQuanBaiLamDS {
    private int id;
    private String tenMonHoc;
    private float tongDiem;
    private String thoiGianLam;

    public TongQuanBaiLamDS()
    {}

    public TongQuanBaiLamDS(int id, String tenmonhoc, float tongdiem, String thoigian)
    {
        this.id = id;
        this.tenMonHoc = tenmonhoc;
        this.tongDiem = tongdiem;
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

    public String getThoiGianLam()
    {
        return this.thoiGianLam;
    }
    public void setThoiGianLam(String tgl)
    {
        this.thoiGianLam = tgl;
    }
}
