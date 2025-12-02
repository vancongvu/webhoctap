package com.example.webhoctap.service;

import com.example.webhoctap.DAO.QuizDAO;

public class QuizService {
    public static QuizService getInstace()
    {
        return new QuizService();
    }
    //kiểm tra đáp án người dùng chọn
    public boolean kiemtraDapAn(int id, int dapanNguoiDung)
    {
        boolean ketQua;
        Integer dapandung = QuizDAO.getInstance().selectDapAnDung(id);
        if(dapandung != dapanNguoiDung)
        {
            ketQua = false;
        }
        else
        {
            ketQua = true;
        }
        return ketQua;
    }
    //tính điểm nếu người dùng chọn đáp án đúng
    public int tinhDiem(int[] id, int[] dapanNguoiDung)
    {
        int diem = 0;
        for(int i=0; i < id.length; i++)
        {
            if(kiemtraDapAn(id[i], dapanNguoiDung[i]))
            {
                diem++;
            }
        }
        return diem;
    }
}
