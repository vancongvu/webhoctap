package com.example.webhoctap.DAO;

import com.example.webhoctap.model.Quiz;

public class QuizDAO implements DAOInterface<Quiz> {

    public static QuizDAO getInstance()
    {
        return new QuizDAO();
    }
    //viết câu lệnh tường minh cho các hàm trong interface
}
