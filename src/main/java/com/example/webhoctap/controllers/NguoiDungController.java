package com.example.webhoctap.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.webhoctap.model.NguoiDung;
import com.example.webhoctap.service.NguoiDungService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class NguoiDungController {

    @GetMapping("/dangnhap-dangky")
    public String hienThiDangNhapDangKy() 
    {
        return "DangNhap-DangKy";
    }

    // hiển thị form đăng ký
    @RequestMapping(value = { "/dangky" }, method = RequestMethod.GET)
    public String hienThiFormDangKy() {
        return "dangky";
    }

    // xử lý đăng ký
    @RequestMapping(value = { "/dangky" }, method = RequestMethod.POST)
    public String xulyDangKy(NguoiDung model, HttpServletRequest req) 
    {
        String tenDangNhap = req.getParameter("tendangnhap");
        String matKhau = req.getParameter("matkhau");
        String hoTen = req.getParameter("hoten");

        NguoiDung user = new NguoiDung();
        user.setTenDangNhap(tenDangNhap);
        user.setMatKhau(matKhau);
        user.setHoTen(hoTen);

        // kiểm tra tên đăng nhập đã tồn tại chưa
        boolean ketQua = NguoiDungService.getInstance().DangKy(user);

        if (ketQua) 
        {
            // đăng ký thành công
            req.setAttribute("message", "Đăng ký thành công. Vui lòng đăng nhập.");
            return "dangnhap";
        } 
        else 
        {
            // đăng ký thất bại
            req.setAttribute("message", "Tên đăng nhập đã tồn tại. Vui lòng chọn tên khác.");
            return "dangky";
        }
    }

    // hiển thị form đăng nhập
    @RequestMapping(value = { "/dangnhap" }, method = RequestMethod.GET)
    public String hienThiFormDangNhap() 
    {
        return "dangnhap";
    }

    // xử lý đăng nhập
    @RequestMapping(value = { "/dangnhap" }, method = RequestMethod.POST)
    public String xulyDangNhap(NguoiDung model, HttpServletRequest req) 
    {

        String user = req.getParameter("tendangnhap");
        String pass = req.getParameter("matkhau");

        // kiểm tra tên đăng nhập và mật khẩu có đúng không
        NguoiDung nd = NguoiDungService.getInstance().DangNhap(user, pass);

        if (nd != null) 
        {
            // đăng nhập thành công
            req.getSession().setAttribute("nguoiDung", nd);
            return "trangchu";
        } 
        else 
        {
            // đăng nhập thất bại
            req.setAttribute("message", "Tên đăng nhập hoặc mật khẩu không đúng");
            return "dangnhap";
        }
    }

    // xử lý đăng xuất
    @RequestMapping("/dangxuat")
    public String xulyDangXuat(HttpServletRequest req) 
    {
        req.getSession().invalidate(); // xoá toàn bộ session
        return "dangnhap";
    }
}
