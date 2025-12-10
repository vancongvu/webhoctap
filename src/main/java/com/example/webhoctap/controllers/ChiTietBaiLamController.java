package com.example.webhoctap.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.webhoctap.model.ChiTietBaiLamDS;
import com.example.webhoctap.service.ChiTietBaiLamService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/chitietbailam")
public class ChiTietBaiLamController {

    /*
        [
        {
            "id": 1,
            "cauhoi": "Cau hoi 1",
            "dapana": "a",
            "dapanb": "b",
            "dapanc": "c",
            "dapand": "d",
            "dapanchon": 1,
            "dapandung": 1 // lay tu bang quiz
        },
        {
            "id": 2,
            "cauhoi": "Cau hoi 2",
            "dapana": "a",
            "dapanb": "b",
            "dapanc": "c",
            "dapand": "d",
            "dapanchon": 2,
            "dapandung": 2 // lay tu bang quiz
        },
        {...}
        ]
    */

    //Lay chi tiet bai lam theo id cua tong quan bai 
    @ResponseBody
    @RequestMapping(value = { "/chitietbailam/tongquanbailam/{id}" }, method = RequestMethod.GET)
    public ArrayList<ChiTietBaiLamDS> hienThiChiTietBaiLamTheoTongQuanBaiLam(HttpServletRequest req, @PathVariable int id) 
    {
        return ChiTietBaiLamService.getInstance().selectByTQBLId(id);
    }
}
