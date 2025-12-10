package com.example.webhoctap.service;

import java.util.ArrayList;

import com.example.webhoctap.DAO.MonHocDAO;
import com.example.webhoctap.model.MonHoc;

public class MonHocService {
    public static MonHocService getInstance()
    {
        return new MonHocService();
    }
    
    //liệt kê tất cả môn học
    public ArrayList<MonHoc> selectAll()
    {
        return MonHocDAO.getInstance().selectAll();
    }
}
