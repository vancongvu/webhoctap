package com.example.webhoctap.service;

import java.util.ArrayList;

import com.example.webhoctap.DAO.NguoiDungDAO;
import com.example.webhoctap.model.NguoiDung;

public class NguoiDungService {
    
    //Đăng ký
    public boolean DangKy(NguoiDung user)
    {
        //kiểm tra user trùng tên đăng nhập
        String condition = "TENDANGNHAP = '" + user.getTenDangNhap() + "'";
        ArrayList<NguoiDung> dsUser = NguoiDungDAO.getInstance().selectByCondition(condition);
        if(dsUser.size() > 0)
        {
            return false; //user bị trùng
        }

        //nếu không trùng thì thêm vào bảng NGUOIDUNG
        NguoiDungDAO.getInstance().insert(user);
        return true;
    }

    public NguoiDung DangNhap(String user, String pass)
    {
        String condition = "TENDANGNHAP = '" + user + "' AND MATKHAU = '" + pass + "'";
        ArrayList<NguoiDung> dsUser = NguoiDungDAO.getInstance().selectByCondition(condition);
        //kiểm tra người dùng đã đăng ký tài khoản?
        if(dsUser.size() == 0)
        {
            return null; //tài khoản không tồn tại
        }
        return dsUser.get(0); // trả về người dùng đầu tiên do tên đăng nhập chỉ dùng cho 1 tài khoản
    }
}
