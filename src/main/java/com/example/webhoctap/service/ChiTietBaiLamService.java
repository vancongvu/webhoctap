package com.example.webhoctap.service;

import java.util.ArrayList;

import com.example.webhoctap.DAO.ChiTietBaiLamDAO;
import com.example.webhoctap.model.ChiTietBaiLam;
import com.example.webhoctap.model.ChiTietBaiLamDS;

public class ChiTietBaiLamService {

    public static ChiTietBaiLamService getInstance()
    {
        return new ChiTietBaiLamService();
    }

    //liệt kê CHITIETBAILAM theo TONGQUANBAILAM ID
    public ArrayList<ChiTietBaiLamDS> selectByTQBLId(int id)
    {
        return ChiTietBaiLamDAO.getInstance().selectByTongQuanBaiLamId(id);
    }

    // LƯU BÀI NGƯỜI DÙNG ĐÃ LÀM VÀO CHITIETBAILAM
    public void luuChiTietBaiLam(int id, int dapanNguoiDung) {
        // tạo đối tượng CHITIETBAILAM
        ChiTietBaiLam ct = new ChiTietBaiLam();
        ct.setDapAnChon(dapanNguoiDung);

        // lưu vào database
        ChiTietBaiLamDAO.getInstance().insert(ct);
    }

}
