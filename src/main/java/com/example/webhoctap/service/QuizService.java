package com.example.webhoctap.service;

import java.util.ArrayList;

import com.example.webhoctap.DAO.QuizDAO;
import com.example.webhoctap.model.Quiz;

public class QuizService {
    public static QuizService getInstance() {
        return new QuizService();
    }

    //lấy danh sách quiz theo id 
    public ArrayList<Quiz> QuizByMonHocId(int id)
    {
        return QuizDAO.getInstance().selectByMonHocId(id);
    }

    // kiểm tra đáp án người dùng chọn
    public boolean kiemtraDapAn(int idCauHoi, int dapanNguoiDung) {
        boolean ketQua;
        Integer dapandung = QuizDAO.getInstance().selectDapAnDung(idCauHoi);
        if (dapandung != dapanNguoiDung) {
            ketQua = false;
        } else {
            ketQua = true;
        }
        return ketQua;
    }
}
