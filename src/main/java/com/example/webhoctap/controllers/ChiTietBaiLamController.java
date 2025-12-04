package com.example.webhoctap.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

    // Vi du chi tiet bai lam:
    /*
        [
        {
            "id": 1,
            "cauhoi": "Cau hoi 1",
            "dapanchon": 1,
            "dapandung": 1 // lay tu bang quiz
        },
        {
            "id": 2,
            "cauhoi": "Cau hoi 2",
            "dapanchon": 2,
            "dapandung": 2 // lay tu bang quiz
        },
        {
            "id": 3,
            "cauhoi": "Cau hoi 3",
            "dapanchon": 3,
            "dapandung": 3 // lay tu bang quiz
            "idquiz": 2
        }
        ]
    */

    //Lay chi tiet bai lam theo id cua tong quan bai lam
    @RequestMapping(value = { "/chitietbailam/tongquanbailam/{id}" }, method = RequestMethod.GET)
    public String hienThiChiTietBaiLamTheoTongQuanBaiLam(HttpServletRequest req, @PathVariable int id) {
        req.setAttribute("dsChiTiet", ChiTietBaiLamDAO.getInstance().selectByTongQuanBaiLamId(id));
        return "chitietbailam_theo_tongquanbailam";
    }
}
