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
}
