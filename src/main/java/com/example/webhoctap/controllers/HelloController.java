package com.example.webhoctap.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.example.webhoctap.Database.JDBCUtil;

public class HelloController {
    public static void main(String[] args)
    {
        try (Connection c = JDBCUtil.getConnection()) {
            String sql = "SELECT * FROM NGUOIDUNG";
            PreparedStatement st = c.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                System.out.println(
                    "HOTEN: " + rs.getString("HOTEN") +
                    ", TENDANGNHAP: " + rs.getString("TENDANGNHAP") +
                    ", MATKHAU: " + rs.getString("MATKHAU")
                );
            
            }
            
        } catch (Exception e) {
            
        }
    }
}
