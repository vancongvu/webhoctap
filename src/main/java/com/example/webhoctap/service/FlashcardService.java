package com.example.webhoctap.service;

import java.util.ArrayList;

import com.example.webhoctap.DAO.FlashcardDAO;
import com.example.webhoctap.model.Flashcard;

public class FlashcardService {
    public static FlashcardService getInstance()
    {
        return new FlashcardService();
    }

    //liệt kê flashcard theo môn học
    public ArrayList<Flashcard> selectByMonHocId(int id)
    {
        return FlashcardDAO.getInstance().selectByMonHocId(id);
    }
}
