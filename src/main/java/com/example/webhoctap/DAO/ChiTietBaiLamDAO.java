package com.example.webhoctap.DAO;

import com.example.webhoctap.model.ChiTietBaiLam;

public class ChiTietBaiLamDAO implements DAOInterface<ChiTietBaiLam> {

    public static ChiTietBaiLamDAO getInstance()
    {
        return new ChiTietBaiLamDAO();
    }
    //viết câu lệnh tường minh cho các hàm trong interface
}
