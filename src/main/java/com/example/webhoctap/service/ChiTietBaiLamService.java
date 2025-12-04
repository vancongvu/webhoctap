package com.example.webhoctap.service;

import com.example.webhoctap.DAO.ChiTietBaiLamDAO;
import com.example.webhoctap.model.ChiTietBaiLam;

public class ChiTietBaiLamService {

    // LƯU BÀI NGƯỜI DÙNG ĐÃ LÀM VÀO CHITIETBAILAM
    public void luuChiTietBaiLam(int id, int dapanNguoiDung) {
        boolean checkdapan = QuizService.getInstance().kiemtraDapAn(id, dapanNguoiDung);

        // tạo đối tượng CHITIETBAILAM
        ChiTietBaiLam ct = new ChiTietBaiLam();
        ct.setDapAnChon(dapanNguoiDung);
        ct.setKiemTraDS(checkdapan);

        // lưu vào database
        ChiTietBaiLamDAO.getInstance().insert(ct);
    }

}
