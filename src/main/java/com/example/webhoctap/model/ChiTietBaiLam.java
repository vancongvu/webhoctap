package com.example.webhoctap.model;

public class ChiTietBaiLam {
    private int id;
    private int DapAnChon;
    private int IDQuiz;
    private int IDTQBL;

    public ChiTietBaiLam() {
    }

    public ChiTietBaiLam(int id, int dapanchon, int idquiz, int idtqbl) {
        this.id = id;
        this.DapAnChon = dapanchon;
        this.IDQuiz = idquiz;
        this.IDTQBL = idtqbl;
    }

    public int getID() {
        return this.id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getDapAnChon() {
        return this.DapAnChon;
    }

    public void setDapAnChon(int dapanchon) {
        this.DapAnChon = dapanchon;
    }

    public boolean getKiemTraDS() {
        return this.KiemTraDS;
    }

    public void setKiemTraDS(boolean kiemtrads) {
        this.KiemTraDS = kiemtrads;
    }

    public int getIDQuiz() {
        return this.IDQuiz;
    }

    public void setIDQuiz(int idquiz) {
        this.IDQuiz = idquiz;
    }

    public int getIDTQBL() {
        return this.IDTQBL;
    }

    public void setIDTQBL(int idtqbl) {
        this.IDTQBL = idtqbl;
    }
}
