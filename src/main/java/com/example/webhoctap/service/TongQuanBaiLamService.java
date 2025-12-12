package com.example.webhoctap.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.example.webhoctap.DAO.QuizDAO;
import com.example.webhoctap.DAO.TongQuanBaiLamDAO;
import com.example.webhoctap.model.TongQuanBaiLam;
import com.example.webhoctap.model.TongQuanBaiLamDS;

public class TongQuanBaiLamService {

    public static TongQuanBaiLamService getInstance()
    {
        return new TongQuanBaiLamService();
    }

    //LIỆT KÊ tất cả TONGQUANBAILAM theo user
    public ArrayList<TongQuanBaiLamDS> selectAllByUser(int id)
    {
        return TongQuanBaiLamDAO.getInstance().selectAllByUserID(id);
    }

    //thêm TONGQUANBAILAM
    public void luuTongQuanBaiLam(int[] id, int[] dapanNguoiDung, int idMonHoc, int idNguoiDung)
    {
        int socaudung = QuizService.getInstance().tinhDiem(id, dapanNguoiDung);
        int tongsocau = QuizDAO.getInstance().countAll();
        float tongdiem = ((float) socaudung/tongsocau) * 10;
        int idmonhoc = idMonHoc;
        int idnguoidung = idNguoiDung;


        //tạo đối tượng TONGQUANBAILAM
        TongQuanBaiLam  tq = new TongQuanBaiLam();
        tq.setTongDiem(tongdiem);
        tq.setSoCauDung(socaudung);
        tq.setTongSoCau(tongsocau);
        tq.setThoiGian(Timestamp.valueOf(LocalDateTime.now()));
        tq.setMonHocId(idmonhoc);
        tq.setNguoiDungId(idnguoidung);

        TongQuanBaiLamDAO.getInstance().insert(tq);
        //TODO: lấy id tongquanbailam
    }
}
