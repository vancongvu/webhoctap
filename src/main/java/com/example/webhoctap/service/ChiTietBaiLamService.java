package com.example.webhoctap.service;

import java.util.ArrayList;

import com.example.webhoctap.DAO.ChiTietBaiLamDAO;
import com.example.webhoctap.model.RequestChiTietBaiLam;
import com.example.webhoctap.model.ResponseChiTietBaiLam;

public class ChiTietBaiLamService {

    public static ChiTietBaiLamService getInstance()
    {
        return new ChiTietBaiLamService();
    }

    //liệt kê CHITIETBAILAM theo TONGQUANBAILAM ID
    public ArrayList<ResponseChiTietBaiLam> selectByTQBLId(int id)
    {
        return ChiTietBaiLamDAO.getInstance().selectByTongQuanBaiLamId(id);
    }

    // LƯU BÀI NGƯỜI DÙNG ĐÃ LÀM VÀO CHITIETBAILAM
    public boolean luuChiTietBaiLam(RequestChiTietBaiLam ct) 
    {
        int ketQua = ChiTietBaiLamDAO.getInstance().insert(ct);
        if(ketQua == 0)
        {
            return false;
        }
        return true;
    }

}
