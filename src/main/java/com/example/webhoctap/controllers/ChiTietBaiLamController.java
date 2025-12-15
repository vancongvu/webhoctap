package com.example.webhoctap.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.webhoctap.model.RequestChiTietBaiLam;
import com.example.webhoctap.model.ResponseChiTietBaiLam;
import com.example.webhoctap.service.ChiTietBaiLamService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
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
        {...}
        ]
    */

    //lấy chi tiết bài làm theo id tổng quan bài làm 
    @ResponseBody
    @RequestMapping(value = { "/tongquanbailam/{idtqbl}/chitietbailam" }, method = RequestMethod.GET)
    public ArrayList<ResponseChiTietBaiLam> hienThiChiTietBaiLamTheoTongQuanBaiLam(HttpServletRequest req, @PathVariable int idtqbl) 
    {
        return ChiTietBaiLamService.getInstance().selectByTQBLId(idtqbl);
    }


    //thêm chi tiết bài làm theo id tổng quan bài làm và id quiz
    @ResponseBody
    @RequestMapping(value = {"/luuchitiet"}, method = RequestMethod.POST)
    public boolean luuChiTietBaiLam(@RequestParam("idtqbl") int idTongQuanBL,
                                    @RequestParam("idcauhoi") int idQuiz,
                                    @RequestParam("dapanchon") int dapAnChon)
    {
        RequestChiTietBaiLam ct = new RequestChiTietBaiLam(dapAnChon, idTongQuanBL, idQuiz);
        System.out.println("aaaaa");

        return ChiTietBaiLamService.getInstance().luuChiTietBaiLam(ct);
    }
}
