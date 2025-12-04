package com.example.webhoctap.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.webhoctap.DAO.FlashcardDAO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class FlashcardController {

    // // hiển thị flashcard
    // @RequestMapping(value = { "/flashcard" }, method = RequestMethod.GET)
    // public String hienThiFlashcard(HttpServletRequest req) {
    //     req.setAttribute("dsFlashcard", FlashcardDAO.getInstance().selectAll());
    //     return "flashcard";
    // }

    // lay flashcard theo mon hoc
    @RequestMapping(value = { "/flashcard/monhoc/{id}" }, method = RequestMethod.GET)
    public String hienThiFlashcardTheoMonHoc(HttpServletRequest req, @PathVariable int id) {
        req.setAttribute("dsFlashcard", FlashcardDAO.getInstance().selectByMonHocId(id));
        return "flashcard_theo_monhoc";
    }
}
