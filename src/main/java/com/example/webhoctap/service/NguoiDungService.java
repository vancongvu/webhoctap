package com.example.webhoctap.service;

import com.example.webhoctap.DAO.NguoiDungDAO;
import com.example.webhoctap.model.NguoiDung;

public class NguoiDungService {

    public static NguoiDungService getInstance() {
        return new NguoiDungService();
    }

    // Đăng ký
    public boolean DangKy(NguoiDung user) {
        // kiểm tra user trùng tên đăng nhập
        int id = NguoiDungDAO.getInstance().selectTenDangNhap(user.getTenDangNhap());
        if (id != -1) {
            return false; // user bị trùng
        }

        // nếu không trùng thì thêm vào bảng NGUOIDUNG
        NguoiDungDAO.getInstance().insert(user);
        return true;
    }

    public NguoiDung DangNhap(String user, String pass) {
        NguoiDung nd = NguoiDungDAO.getInstance().selectByUserPass(user, pass);
        // kiểm tra người dùng đã đăng ký tài khoản?
        if (nd == null) {
            return null; // tài khoản không tồn tại
        }
        return nd; // trả về người dùng
    }
}
