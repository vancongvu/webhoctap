package com.example.webhoctap.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.webhoctap.model.Quiz;
import com.example.webhoctap.service.QuizService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class QuizController {

    // hiển thị quiz
    @ResponseBody
    @RequestMapping(value = { "/quiz/monhoc/{id}" }, method = RequestMethod.GET)
    public ArrayList<Quiz> hienThiQuizTheoMonHoc(HttpServletRequest req, @PathVariable int id) 
    {
        return QuizService.getInstance().QuizByMonHocId(id);
    }
}
