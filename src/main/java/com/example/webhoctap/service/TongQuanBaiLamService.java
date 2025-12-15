package com.example.webhoctap.service;

import java.util.ArrayList;

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

    //tính điểm
    public float TinhDiem(int socaudung, int tongsocau)
    {
        float tongdiem = ((float) socaudung/tongsocau) * 10;
        return tongdiem;
    } 

    //thêm TONGQUANBAILAM
    public int luuTongQuanBaiLam(TongQuanBaiLam tqbl)
    {
        int idtqbl = TongQuanBaiLamDAO.getInstance().insert(tqbl);
        if(idtqbl != 0)
        {
            return idtqbl;
        }
        return 0;
    }
}
