package com.example.webhoctap.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.webhoctap.DAO.QuizDAO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class QuizController {

    // hiển thị quiz
    @RequestMapping(value = { "/quiz" }, method = RequestMethod.GET)
    public String hienThiQuiz(HttpServletRequest req) {
        req.setAttribute("dsQuiz", QuizDAO.getInstance().selectAll());
        return "quiz";
    }

    // lay quiz theo mon hoc
    // cach 1: dung selectByCondition
    // @RequestMapping(value = { "/quiz/monhoc/{id}" }, method = RequestMethod.GET)
    // public String hienThiQuizTheoMonHoc(HttpServletRequest req, @PathVariable int id) {
    //     req.setAttribute("dsQuiz", QuizDAO.getInstance().selectByCondition("MONHOC_ID = " + id));
    //     return "quiz_theo_monhoc";
    // }

    // cach 2: dung selectByMonHocId
    @RequestMapping(value = { "/quiz/monhoc/{id}" }, method = RequestMethod.GET)
    public String hienThiQuizTheoMonHoc(HttpServletRequest req, @PathVariable int id) {
        req.setAttribute("dsQuiz", QuizDAO.getInstance().selectByMonHocId(id));
        return "quiz_theo_monhoc";
    }
}
