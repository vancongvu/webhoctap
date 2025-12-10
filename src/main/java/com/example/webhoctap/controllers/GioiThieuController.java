package com.example.webhoctap.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GioiThieuController {
    @RequestMapping(value = "/gioithieu", method = RequestMethod.GET)
    public String gioithieu() 
    {
        return "GioiThieu";
    }
}
