package com.example.webhoctap.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.webhoctap.model.Flashcard;
import com.example.webhoctap.service.FlashcardService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@ResponseBody
public class FlashcardController {
    // lay flashcard theo mon hoc
    @ResponseBody
    @RequestMapping(value = { "/monhoc/{id}/flashcard" }, method = RequestMethod.GET)
    public ArrayList<Flashcard> hienThiFlashcardTheoMonHoc(HttpServletRequest req, @PathVariable int id) 
    {
        return FlashcardService.getInstance().selectByMonHocId(id);
    }
}
