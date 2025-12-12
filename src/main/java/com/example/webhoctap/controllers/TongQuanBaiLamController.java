package com.example.webhoctap.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.webhoctap.model.TongQuanBaiLamDS;
import com.example.webhoctap.service.TongQuanBaiLamService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api")
public class TongQuanBaiLamController {
    /*
    ví dụ:
    [
    {
        "id": 1,
        "tenmonhoc": "toan",
        "tongdiem": 8,
        "socaudung": 8,
        "tongsocau": 10,
        "thoigianlam": 10/10/2025 15:00,
    },
    {...}
    ] */
    // hiển thị tất cả tổng quan bài làm
    @ResponseBody
    @RequestMapping(value = {"/bangdiem"}, method = RequestMethod.GET)
    public ArrayList<TongQuanBaiLamDS> hienThiTongQuanBaiLam(HttpServletRequest req, @PathVariable int id) 
    {
        return TongQuanBaiLamService.getInstance().selectAllByUser(id);
    }

}
