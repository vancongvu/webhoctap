package com.example.webhoctap.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.webhoctap.Database.JDBCUtil;
import com.example.webhoctap.model.Quiz;

public class QuizDAO implements DAOInterface<Quiz> {

    public static QuizDAO getInstance() {
        return new QuizDAO();
    }

    public int insert(Quiz t) {
        int ketQua = 0;
        try {
            Connection c = JDBCUtil.getConnection();

            String sql = "INSERT INTO CAUHOI (CAUHOI, DAP_AN_A, DAP_AN_B, DAP_AN_C, DAP_AN_D, DAP_AN_DUNG, MONHOC_ID)" +
                    "VALUES (?,?,?,?,?,?)";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, t.getCauHoi());
            pst.setString(2, t.getDapAnA());
            pst.setString(3, t.getDapAnB());
            pst.setString(4, t.getDapAnC());
            pst.setString(5, t.getDapAnD());
            pst.setInt(6, t.getDapAnDung());
            pst.setInt(7, t.getMonHocId());

            System.out.println("Thực thi: " + sql);
            ketQua = pst.executeUpdate();

            JDBCUtil.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public int update(Quiz t) {
        int ketQua = 0;
        try {
            Connection c = JDBCUtil.getConnection();

            String sql = "UPDATE CAUHOI" +
                    "SET CAUHOI = ? ,  DAP_AN_A = ? , DAP_AN_B = ? , DAP_AN_C = ? , DAP_AN_D = ?, DAP_AN_DUNG = ?, MONHOC_ID = ?" +
                    "WHERE ID_CAUHOI = ?";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, t.getCauHoi());
            pst.setString(2, t.getDapAnA());
            pst.setString(3, t.getDapAnB());
            pst.setString(4, t.getDapAnC());
            pst.setString(5, t.getDapAnD());
            pst.setInt(6, t.getDapAnDung());
            pst.setInt(7, t.getMonHocId());
            pst.setInt(8, t.getID());

            System.out.println("Thực thi: " + sql);
            ketQua = pst.executeUpdate();

            JDBCUtil.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public int delete(Quiz t) {
        int ketQua = 0;
        try {
            Connection c = JDBCUtil.getConnection();

            String sql = "DELETE FROM CAUHOI WHERE ID_CAUHOI = ?";
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

    public ArrayList<Quiz> selectAll() {
        ArrayList<Quiz> ketQua = new ArrayList<Quiz>();
        try {
            Connection c = JDBCUtil.getConnection();

            String sql = "SELECT * FROM CAUHOI";
            PreparedStatement pst = c.prepareStatement(sql);

            System.out.println("Thực thi: " + sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID_CAUHOI");
                String cauhoi = rs.getString("CAUHOI");
                String dapana = rs.getString("DAP_AN_A");
                String dapanb = rs.getString("DAP_AN_B");
                String dapanc = rs.getString("DAP_AN_C");
                String dapand = rs.getString("DAP_AN_D");
                int dapandung = rs.getInt("DAP_AN_DUNG");
                int monhocid = rs.getInt("MONHOC_ID");
                Quiz quiz = new Quiz(id, cauhoi, dapana, dapanb, dapanc, dapand, dapandung, monhocid);
                ketQua.add(quiz);
            }

            JDBCUtil.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public Quiz selectById(Quiz t) {
        Quiz ketQua = null;
        try {
            Connection c = JDBCUtil.getConnection();

            String sql = "SELECT * FROM CAUHOI WHERE ID_CAUHOI = ?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, t.getID());

            System.out.println("Thực thi: " + sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID_CAUHOI");
                String cauhoi = rs.getString("CAUHOI");
                String dapana = rs.getString("DAP_AN_A");
                String dapanb = rs.getString("DAP_AN_B");
                String dapanc = rs.getString("DAP_AN_C");
                String dapand = rs.getString("DAP_AN_D");
                int dapandung = rs.getInt("DAP_AN_DUNG");
                int monhocid = rs.getInt("MONHOC_ID");
                ketQua = new Quiz(id, cauhoi, dapana, dapanb, dapanc, dapand, dapandung, monhocid);
            }
            JDBCUtil.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public ArrayList<Quiz> selectByCondition(String condition) {
        ArrayList<Quiz> ketQua = new ArrayList<Quiz>();
        try {
            Connection c = JDBCUtil.getConnection();

            String sql = "SELECT * FROM CAUHOI WHERE " + condition;
            PreparedStatement pst = c.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            System.out.println(sql);

            while (rs.next()) {
                int id = rs.getInt("ID_CAUHOI");
                String cauhoi = rs.getString("CAUHOI");
                String dapana = rs.getString("DAP_AN_A");
                String dapanb = rs.getString("DAP_AN_B");
                String dapanc = rs.getString("DAP_AN_C");
                String dapand = rs.getString("DAP_AN_D");
                int dapandung = rs.getInt("DAP_AN_DUNG");
                int monhocid = rs.getInt("MONHOC_ID");

                Quiz quiz = new Quiz(id, cauhoi, dapana, dapanb, dapanc, dapand, dapandung, monhocid);
                ketQua.add(quiz);
            }

            JDBCUtil.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    // cach 2: dung selectByMonHocId
    public ArrayList<Quiz> selectByMonHocId(int monhocid) {
        ArrayList<Quiz> ketQua = new ArrayList<Quiz>();
        try {
            Connection c = JDBCUtil.getConnection();
            String sql = "SELECT * FROM CAUHOI WHERE MONHOC_ID = ?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, monhocid);

            System.out.println("Thực thi: " + sql);
            ResultSet rs = pst.executeQuery();

            while(rs.next())
            {
                int id = rs.getInt("ID_CAUHOI");
                String cauhoi = rs.getString("CAUHOI");
                String dapana = rs.getString("DAP_AN_A");
                String dapanb = rs.getString("DAP_AN_B");
                String dapanc = rs.getString("DAP_AN_C");
                String dapand = rs.getString("DAP_AN_D");
                int dapandung = rs.getInt("DAP_AN_DUNG");

                Quiz quiz = new Quiz(id, cauhoi, dapana, dapanb, dapanc, dapand, dapandung);
                ketQua.add(quiz);
            }

            JDBCUtil.closeConnection(c);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return ketQua;
    }

    // lấy đáp án đúng theo id câu hỏi
    public Integer selectDapAnDung(int idCauHoi) {
        Integer dapandung = null;
        try {
            Connection c = JDBCUtil.getConnection();

            String sql = "SELECT DAP_AN_DUNG FROM CAUHOI WHERE ID_CAUHOI = ?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, idCauHoi);

            System.out.println("Thực thi: " + sql);
            ResultSet rs = pst.executeQuery();

            dapandung = rs.getInt("DAP_AN_DUNG");

            JDBCUtil.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dapandung;
    }

    // đếm số lượng quiz
    public int countAll() {
        int tong = 0;
        try {
            Connection c = JDBCUtil.getConnection();

            String sql = "SELECT COUNT(*) AS TONG FROM CAUHOI";
            PreparedStatement pst = c.prepareStatement(sql);

            System.out.println("Thực thi: " + sql);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                tong = rs.getInt("TONG");
            }

            JDBCUtil.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tong;
    }
}
