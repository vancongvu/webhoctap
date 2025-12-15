package com.example.webhoctap.model;

public class ResponseChiTietBaiLam {
    private int id;
    private String cauhoi;
    private int dapanchon;
    private int dapandung;
    private String dap_an_a;
    private String dap_an_b;
    private String dap_an_c;
    private String dap_an_d;

    public ResponseChiTietBaiLam() {
    }

    public ResponseChiTietBaiLam(int id, String cauhoi, int dapanchon, int dapandung, 
                                  String dap_an_a, String dap_an_b, String dap_an_c, String dap_an_d) {
        this.id = id;
        this.cauhoi = cauhoi;
        this.dapanchon = dapanchon;
        this.dapandung = dapandung;
        this.dap_an_a = dap_an_a;
        this.dap_an_b = dap_an_b;
        this.dap_an_c = dap_an_c;
        this.dap_an_d = dap_an_d;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCauhoi() {
        return cauhoi;
    }

    public void setCauhoi(String cauhoi) {
        this.cauhoi = cauhoi;
    }

    public int getDapanchon() {
        return dapanchon;
    }

    public void setDapanchon(int dapanchon) {
        this.dapanchon = dapanchon;
    }

    public int getDapandung() {
        return dapandung;
    }

    public void setDapandung(int dapandung) {
        this.dapandung = dapandung;
    }

    public String getDap_an_a() {
        return dap_an_a;
    }

    public void setDap_an_a(String dap_an_a) {
        this.dap_an_a = dap_an_a;
    }

    public String getDap_an_b() {
        return dap_an_b;
    }

    public void setDap_an_b(String dap_an_b) {
        this.dap_an_b = dap_an_b;
    }

    public String getDap_an_c() {
        return dap_an_c;
    }

    public void setDap_an_c(String dap_an_c) {
        this.dap_an_c = dap_an_c;
    }

    public String getDap_an_d() {
        return dap_an_d;
    }

    public void setDap_an_d(String dap_an_d) {
        this.dap_an_d = dap_an_d;
    }
}

