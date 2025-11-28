package com.example.webhoctap.DAO;

import com.example.webhoctap.model.TongQuanBaiLam;

public class TongQuanBaiLamDAO implements DAOInterface<TongQuanBaiLam> {

    public static TongQuanBaiLamDAO getInstance()
    {
        return new TongQuanBaiLamDAO();
    }
    //viết câu lệnh tường minh cho các hàm trong interface
}
