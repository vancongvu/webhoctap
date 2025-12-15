package com.example.webhoctap.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.webhoctap.model.TongQuanBaiLam;
import com.example.webhoctap.model.TongQuanBaiLamDS;
import com.example.webhoctap.service.TongQuanBaiLamService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
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
    @RequestMapping(value = {"/user/{idUser}/bangdiem"}, method = RequestMethod.GET)
    public ArrayList<TongQuanBaiLamDS> hienThiTongQuanBaiLam(HttpServletRequest req) 
    {
        Integer idUser = (Integer) req.getSession().getAttribute("id_user");
        return TongQuanBaiLamService.getInstance().selectAllByUser(idUser);
    }

    //lưu bài làm khi hoàn thành
    @ResponseBody
    @RequestMapping(value = {"/luubai"}, method=RequestMethod.POST)
    public int luuTongQuanBaiLam(@RequestParam("socaudung") int soCauDung,
                                    @RequestParam("tongsocau") int tongSoCau,
                                    @RequestParam("idmonhoc") int idMonHoc,
                                    HttpServletRequest req)
    {
        //tính điểm
        float diem = TongQuanBaiLamService.getInstance().TinhDiem(soCauDung, tongSoCau);

        Integer idUser = (Integer) req.getSession().getAttribute("id_user");

        TongQuanBaiLam tqbl = new TongQuanBaiLam();
        tqbl.setMonHocId(idMonHoc);
        tqbl.setNguoiDungId(idUser);
        tqbl.setSoCauDung(soCauDung);
        tqbl.setTongSoCau(tongSoCau);
        tqbl.setTongDiem(diem);

        return TongQuanBaiLamService.getInstance().luuTongQuanBaiLam(tqbl);
    }

}
