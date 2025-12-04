package com.example.webhoctap.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.webhoctap.DAO.MonHocDAO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MonHocController {

    // hiển thị danh sách môn học
    @RequestMapping(value = { "/monhoc" }, method = RequestMethod.GET)
    public String hienThiDanhSachMonHoc(HttpServletRequest req) {
        req.setAttribute("dsMonHoc", MonHocDAO.getInstance().selectAll());
        return "monhoc";
    }
}
