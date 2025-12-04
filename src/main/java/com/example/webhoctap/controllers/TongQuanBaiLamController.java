package com.example.webhoctap.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.webhoctap.DAO.TongQuanBaiLamDAO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/tongquanbailam")
public class TongQuanBaiLamController {
    // hiển thị tổng quan bài làm
    @RequestMapping(method = RequestMethod.GET)
    public String hienThiTongQuanBaiLam(HttpServletRequest req) {
        req.setAttribute("dsTongQuan", TongQuanBaiLamDAO.getInstance().selectAll());
        return "tongquanbailam";
    }

}
