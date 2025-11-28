package com.example.webhoctap.DAO;

import com.example.webhoctap.model.NguoiDung;

public class NguoiDungDAO implements DAOInterface<NguoiDung> {
    
    public static NguoiDungDAO getInstance()
    {
        return new NguoiDungDAO();
    }

    //viết các lệnh tường minh cho các hàm trong interface
}
