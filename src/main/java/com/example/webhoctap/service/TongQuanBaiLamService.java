package com.example.webhoctap.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.example.webhoctap.DAO.QuizDAO;
import com.example.webhoctap.DAO.TongQuanBaiLamDAO;
import com.example.webhoctap.model.TongQuanBaiLam;

public class TongQuanBaiLamService {
    //thêm TONGQUANBAILAM
    public void luuTongQuanBaiLam(int[] id, int[] dapanNguoiDung)
    {
        int socaudung = QuizService.getInstance().tinhDiem(id, dapanNguoiDung);
        int tongsocau = QuizDAO.getInstance().countAll();
        float tongdiem = ((float) socaudung/tongsocau) * 10;


        //tạo đối tượng TONGQUANBAILAM
        TongQuanBaiLam  tq = new TongQuanBaiLam();
        tq.setTongDiem(tongdiem);
        tq.setSoCauDung(socaudung);
        tq.setTongSoCau(tongsocau);
        tq.setThoiGian(Timestamp.valueOf(LocalDateTime.now()));

        TongQuanBaiLamDAO.getInstance().insert(tq);
    }
}
