package com.example.webhoctap.DAO;

import com.example.webhoctap.model.Flashcard;

public class FlashcardDAO implements DAOInterface<Flashcard> {

    public static FlashcardDAO getInstance()
    {
        return new FlashcardDAO();
    }
    //viết câu lệnh tường minh cho các hàm trong interface
}
