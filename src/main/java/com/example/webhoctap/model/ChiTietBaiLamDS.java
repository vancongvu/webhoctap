package com.example.webhoctap.model;

public class ChiTietBaiLamDS {
    private int id;
    private String cauhoi;
    private int dapanchon;
    private int dapandung;
    private int idquiz;
    private String dap_an_a;
    private String dap_an_b;
    private String dap_an_c;
    private String dap_an_d;

    public ChiTietBaiLamDS() {
    }

    public ChiTietBaiLamDS(int id, String cauhoi, int dapanchon, int dapandung, int idquiz, 
                                  String dap_an_a, String dap_an_b, String dap_an_c, String dap_an_d) {
        this.id = id;
        this.cauhoi = cauhoi;
        this.dapanchon = dapanchon;
        this.dapandung = dapandung;
        this.idquiz = idquiz;
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

    public int getIdquiz() {
        return idquiz;
    }

    public void setIdquiz(int idquiz) {
        this.idquiz = idquiz;
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

