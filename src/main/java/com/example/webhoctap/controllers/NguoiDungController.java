package com.example.webhoctap.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.webhoctap.model.NguoiDung;
import com.example.webhoctap.model.ResponseDangNhap;
import com.example.webhoctap.service.NguoiDungService;

import jakarta.servlet.http.HttpServletRequest;

//import jakarta.servlet.http.HttpServletRequest;

@Controller
public class NguoiDungController {

    // // xử lý đăng ký
    @ResponseBody
    @RequestMapping("/dangky")
    public String xulyDangKy(@RequestParam("tendangnhap") String tenDangNhap,
                             @RequestParam("matkhau") String matKhau,
                             @RequestParam("hoten") String hoTen) 
    {
        NguoiDung user = new NguoiDung();
        user.setTenDangNhap(tenDangNhap);
        user.setMatKhau(matKhau);
        user.setHoTen(hoTen);

        // kiểm tra tên đăng nhập đã tồn tại chưa
        boolean ketQua = NguoiDungService.getInstance().checkDangKy(user);

        if (ketQua) 
        {
            // tên người dùng không trùng -> đăng ký thành công
            NguoiDungService.getInstance().DangKy(user);
            return "success";
        }
        else
        {
            return "fail";
        }
    }

    // xử lý đăng nhập
    @ResponseBody
    @RequestMapping("/dangnhap")
    public ResponseDangNhap xulyDangNhap( @RequestParam("tendangnhap") String user,
                                          @RequestParam("matkhau") String pass,
                                          HttpServletRequest req) 
    {
        // kiểm tra tên đăng nhập và mật khẩu có đúng không
        NguoiDung nd = NguoiDungService.getInstance().DangNhap(user, pass);

        if (nd != null) 
        {
            // đăng nhập thành công
            ResponseDangNhap r = new ResponseDangNhap(nd.getID(), nd.getHoTen(), true);
            req.getSession().setAttribute("id_user", nd.getID());
            req.getSession().setAttribute("user_name", nd.getHoTen());
            return r;
        }
        else
        {
            //sai tên đăng nhập hoặc mật khẩu
            ResponseDangNhap r = new ResponseDangNhap(0,"",false);
            return r;
        }
    }

    // xử lý đăng xuất
    // @RequestMapping("/dangxuat")
    // public String xulyDangXuat(HttpServletRequest req) 
    // {
    //     req.getSession().invalidate(); // xoá toàn bộ session
    //     return "";
    // }
}
