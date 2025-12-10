package com.example.webhoctap.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TrangChuController 
{
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() 
    {
        return "TrangChu";
    }

    @RequestMapping(value = "/trangchu", method = RequestMethod.GET)
    public String trangchu() 
    {
        return "TrangChu";
    }
}
