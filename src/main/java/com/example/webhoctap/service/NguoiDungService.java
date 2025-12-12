package com.example.webhoctap.service;

import com.example.webhoctap.DAO.NguoiDungDAO;
import com.example.webhoctap.model.NguoiDung;

public class NguoiDungService {

    public static NguoiDungService getInstance() {
        return new NguoiDungService();
    }
    //kiểm tra tên đăng nhập đã được đăng ký chưa?
    public boolean checkDangKy(NguoiDung user)
    {
        int id = NguoiDungDAO.getInstance().selectTenDangNhap(user.getTenDangNhap());
        if(id != -1)
        {
            return false;
        }
        return true;
    }
    // Đăng ký
    public void DangKy(NguoiDung user) 
    {
        // thêm người dùng vào database
        NguoiDungDAO.getInstance().insert(user);
    }

    
    //Đăng nhập
    public NguoiDung DangNhap(String user, String pass) 
    {
        NguoiDung nd = NguoiDungDAO.getInstance().selectByUserPass(user, pass);
        // kiểm tra người dùng đã đăng ký tài khoản?
        if (nd == null) 
        {
            return null; // tài khoản không tồn tại
        }
        return nd; // trả về người dùng
    }
}
