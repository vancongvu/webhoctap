package com.example.webhoctap.model;

public class RequestChiTietBaiLam {
    private int dapAnChon;
    private int tqblID;
    private int quizID;

    public RequestChiTietBaiLam ()
    {}

    public RequestChiTietBaiLam(int dapanchon, int idtqbl, int idquiz)
    {
        this.dapAnChon = dapanchon;
        this.tqblID = idtqbl;
        this.quizID = idquiz;
    }

    public int getDapAnChon()
    {
        return this.dapAnChon;
    }
    public void setDapAnChon(int dapanchon)
    {
        this.dapAnChon = dapanchon;
    }

    public int getTQBLID()
    {
        return this.tqblID;
    }
    public void setTQBLID(int id)
    {
        this.tqblID = id;
    }
    
    public int getQuizID()
    {
        return this.quizID;
    }
    public void setQuizID(int id)
    {
        this.quizID = id;
    }

}
