package com.example.webhoctap.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.webhoctap.Database.JDBCUtil;
import com.example.webhoctap.model.NguoiDung;

public class NguoiDungDAO implements DAOInterface<NguoiDung> {

    public static NguoiDungDAO getInstance() {
        return new NguoiDungDAO();
    }

    public int insert(NguoiDung t) {
        int ketQua = 0;
        try {
            Connection c = JDBCUtil.getConnection();

            String sql = "INSERT INTO NGUOIDUNG (HOTEN, TENDANGNHAP, MATKHAU) " +
                    "VALUES (?,?,?)";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, t.getHoTen());
            pst.setString(2, t.getTenDangNhap());
            pst.setString(3, t.getMatKhau());

            System.out.println("Thực thi: " + sql);
            ketQua = pst.executeUpdate();

            JDBCUtil.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public int update(NguoiDung t) {
        int ketQua = 0;
        try {
            Connection c = JDBCUtil.getConnection();

            String sql = "UPDATE NGUOIDUNG " +
                    "SET HOTEN = ? , TENDANGNHAP = ? , MATKHAU = ?" +
                    " WHERE ID_NGUOIDUNG = ?";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, t.getHoTen());
            pst.setString(2, t.getTenDangNhap());
            pst.setString(3, t.getMatKhau());
            pst.setInt(4, t.getID());

            System.out.println("Thực thi: " + sql);
            ketQua = pst.executeUpdate();

            JDBCUtil.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public int delete(NguoiDung t) {
        int ketQua = 0;
        try {
            Connection c = JDBCUtil.getConnection();

            String sql = "DELETE FROM NGUOIDUNG WHERE ID_NGUOIDUNG = ?";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, t.getID());

            System.out.println("Thực thi: " + sql);
            ketQua = pst.executeUpdate();

            JDBCUtil.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public ArrayList<NguoiDung> selectAll() {
        ArrayList<NguoiDung> ketQua = new ArrayList<NguoiDung>();
        try {
            Connection c = JDBCUtil.getConnection();

            String sql = "SELECT * FROM NGUOIDUNG";
            PreparedStatement pst = c.prepareStatement(sql);

            System.out.println("Thực thi: " + sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID_NGUOIDUNG");
                String hoten = rs.getString("HOTEN");
                String tendangnhap = rs.getString("TENDANGNHAP");
                String matkhau = rs.getString("MATKHAU");

                NguoiDung nguoidung = new NguoiDung(id, hoten, tendangnhap, matkhau);
                ketQua.add(nguoidung);
            }

            JDBCUtil.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public NguoiDung selectById(NguoiDung t) {
        NguoiDung ketQua = null;
        try {
            Connection c = JDBCUtil.getConnection();

            String sql = "SELECT * FROM NGUOIDUNG WHERE ID_NGUOIDUNG = ?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, t.getID());

            System.out.println("Thực thi: " + sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID_NGUOIDUNG");
                String hoten = rs.getString("HOTEN");
                String tendangnhap = rs.getString("TENDANGNHAP");
                String matkhau = rs.getString("MATKHAU");

                ketQua = new NguoiDung(id, hoten, tendangnhap, matkhau);
            }
            JDBCUtil.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    // lấy tên đăng nhập
    public int selectTenDangNhap(String tendangnhap) {
        int ketQua = -1;
        try {
            Connection c = JDBCUtil.getConnection();

            String sql = "SELECT ID_NGUOIDUNG FROM NGUOIDUNG WHERE TENDANGNHAP = ?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, tendangnhap);

            System.out.println("Thực thi: " + sql);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                ketQua = rs.getInt("ID_NGUOIDUNG");
            }

            JDBCUtil.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    // lấy người dùng theo user và pass
    public NguoiDung selectByUserPass(String user, String pass) {
        NguoiDung ketQua = null;
        try {
            Connection c = JDBCUtil.getConnection();

            String sql = "SELECT * FROM NGUOIDUNG WHERE TENDANGNHAP = ? AND MATKHAU = ?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, user);
            pst.setString(2, pass);

            System.out.println("Thực thi: " + sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID_NGUOIDUNG");
                String hoten = rs.getString("HOTEN");
                String tendangnhap = rs.getString("TENDANGNHAP");
                String matkhau = rs.getString("MATKHAU");

                ketQua = new NguoiDung(id, hoten, tendangnhap, matkhau);
            }
            JDBCUtil.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public ArrayList<NguoiDung> selectByCondition(String condition) {
        ArrayList<NguoiDung> ketQua = new ArrayList<NguoiDung>();
        try {
            Connection c = JDBCUtil.getConnection();

            String sql = "SELECT * FROM NGUOIDUNG WHERE " + condition;
            PreparedStatement pst = c.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            System.out.println(sql);

            while (rs.next()) {
                int id = rs.getInt("ID_NGUOIDUNG");
                String hoten = rs.getString("HOTEN");
                String tendangnhap = rs.getString("TENDANGNHAP");
                String matkhau = rs.getString("MATKHAU");

                NguoiDung nguoidung = new NguoiDung(id, hoten, tendangnhap, matkhau);
                ketQua.add(nguoidung);
            }

            JDBCUtil.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

}
