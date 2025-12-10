package com.example.webhoctap.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.webhoctap.model.MonHoc;
import com.example.webhoctap.service.MonHocService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MonHocController {

    // hiển thị danh sách môn học
    @ResponseBody
    @RequestMapping(value = { "/monhoc" }, method = RequestMethod.GET)
    public ArrayList<MonHoc> hienThiDanhSachMonHoc(HttpServletRequest req) 
    {
        return MonHocService.getInstance().selectAll();
    }
}
