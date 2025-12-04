package com.example.webhoctap.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.webhoctap.DAO.ChiTietBaiLamDAO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/chitietbailam")
public class ChiTietBaiLamController {
    // hiển thị chi tiết bài làm
    @RequestMapping(method = RequestMethod.GET)
    public String hienThiChiTietBaiLam(HttpServletRequest req) {
        req.setAttribute("dsChiTiet", ChiTietBaiLamDAO.getInstance().selectAll());
        return "chitietbailam";
    }
}
