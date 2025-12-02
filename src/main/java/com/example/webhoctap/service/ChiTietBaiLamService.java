package com.example.webhoctap.service;

import com.example.webhoctap.DAO.ChiTietBaiLamDAO;
import com.example.webhoctap.DAO.QuizDAO;
import com.example.webhoctap.model.ChiTietBaiLam;

public class ChiTietBaiLamService {


    public boolean CheckDapAn(int id)
    {
        boolean ketQua;
        Integer dapandung = QuizDAO.getInstance().selectDapAnDung(id);
        Integer dapanchon = ChiTietBaiLamDAO.getInstance().selectDapAnChonById(id);
        if(dapanchon == dapandung)
        {
            ketQua = true;
        }
        else
        {
            ketQua = false;
        }
        ChiTietBaiLam chitiet = ChiTietBaiLamDAO.getInstance().selectById(id);
        chitiet.setKiemTraDS(ketQua);
        return ketQua;        
    }
}
