package com.example.webhoctap.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.webhoctap.Database.JDBCUtil;
import com.example.webhoctap.model.ChiTietBaiLam;
import com.example.webhoctap.model.ChiTietBaiLamDS;

public class ChiTietBaiLamDAO implements DAOInterface<ChiTietBaiLam> {

    public static ChiTietBaiLamDAO getInstance() {
        return new ChiTietBaiLamDAO();
    }

    public int insert(ChiTietBaiLam t) {
        int ketQua = 0;
        try {
            Connection c = JDBCUtil.getConnection();
            // Can luu lai
            // CAUHOI_ID, DAP_AN_CHON
            // NEU DAP_AN_CHON != QUIZ.DAP_AN_DUNG

            // TODO: doi ten cot tu TONGQUANBL_ID thanh ID_TQBL (vcv)

            String sql = "INSERT INTO CHITIETBAILAM (CAUHOI_ID, DAP_AN_CHON, ID_TQBL) " +
                    "VALUES (?,?,?)";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, t.getIDQuiz());
            pst.setInt(2, t.getDapAnChon());
            pst.setInt(3, t.getIDTQBL());

            System.out.println("Thực thi: " + sql);
            ketQua = pst.executeUpdate();

            JDBCUtil.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public int delete(ChiTietBaiLam t) {
        int ketQua = 0;
        try {
            Connection c = JDBCUtil.getConnection();

            String sql = "DELETE FROM CHITIETBAILAM WHERE ID_BAILAMCT = ?";
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

    public ArrayList<ChiTietBaiLam> selectAll() {
        ArrayList<ChiTietBaiLam> ketQua = new ArrayList<ChiTietBaiLam>();
        try {
            Connection c = JDBCUtil.getConnection();

            String sql = "SELECT * FROM CHITIETBAILAM";
            PreparedStatement pst = c.prepareStatement(sql);

            System.out.println("Thực thi: " + sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID_BAILAMCT");
                int dapanchon = rs.getInt("DAP_AN_CHON");
                int idquiz = rs.getInt("CAUHOI_ID");
                int idtqbl = rs.getInt("ID_TQBL");
                ChiTietBaiLam chitietbailam = new ChiTietBaiLam(id, dapanchon, idquiz, idtqbl);
                ketQua.add(chitietbailam);
            }

            JDBCUtil.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public ChiTietBaiLam selectById(ChiTietBaiLam t) {
        ChiTietBaiLam ketQua = null;
        try {
            Connection c = JDBCUtil.getConnection();

            String sql = "SELECT * FROM CHITIETBAILAM WHERE ID_BAILAMCT = ?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, t.getID());

            System.out.println("Thực thi: " + sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID_BAILAMCT");
                int dapanchon = rs.getInt("DAP_AN_CHON");
                int idquiz = rs.getInt("CAUHOI_ID");
                int idtqbl = rs.getInt("ID_TQBL");
                ketQua = new ChiTietBaiLam(id, dapanchon, idquiz, idtqbl);
            }
            JDBCUtil.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public ArrayList<ChiTietBaiLam> selectByCondition(String condition) {
        ArrayList<ChiTietBaiLam> ketQua = new ArrayList<ChiTietBaiLam>();
        try {
            Connection c = JDBCUtil.getConnection();

            String sql = "SELECT * FROM CHITIETBAILAM WHERE " + condition;
            PreparedStatement pst = c.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            System.out.println(sql);

            while (rs.next()) {
                int id = rs.getInt("ID_BAILAMCT");
                int dapanchon = rs.getInt("DAP_AN_CHON");
                int idquiz = rs.getInt("CAUHOI_ID");
                int idtqbl = rs.getInt("ID_TQBL");

                ChiTietBaiLam chitietbailam = new ChiTietBaiLam(id, dapanchon, idquiz, idtqbl);
                ketQua.add(chitietbailam);
            }

            JDBCUtil.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public ArrayList<ChiTietBaiLamDS> selectByTongQuanBaiLamId(int tqblid) {
        ArrayList<ChiTietBaiLamDS> ketQua = new ArrayList<ChiTietBaiLamDS>();
        try {
            Connection c = JDBCUtil.getConnection();

            String sql = "SELECT CTBL.*, CH.* " +
                         "FROM CHITIETBAILAM CTBL " +
                         "JOIN CAUHOI CH ON CTBL.CAUHOI_ID = CH.ID_CAUHOI " +
                         "WHERE CTBL.IDTQBL = ?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, tqblid);

            System.out.println("Thực thi: " + sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID_BAILAMCT");
                int dapanchon = rs.getInt("DAP_AN_CHON");
                int idquiz = rs.getInt("CAUHOI_ID");
                // Get extra fields from join
                String cauHoi = rs.getString("CAUHOI");
                int dapAnDung = rs.getInt("DAP_AN_DUNG");
                String dapAnA = rs.getString("DAP_AN_A");
                String dapAnB = rs.getString("DAP_AN_B");
                String dapAnC = rs.getString("DAP_AN_C");
                String dapAnD = rs.getString("DAP_AN_D");

                ChiTietBaiLamDS chitietbailam = new ChiTietBaiLamDS(id, cauHoi, dapanchon, dapAnDung, idquiz, 
                                                                                dapAnA, dapAnB, dapAnC, dapAnD);
                ketQua.add(chitietbailam);
            }

            JDBCUtil.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    // lấy đáp án người dùng chọn theo id
    public Integer selectDapAnChonById(int id) {
        Integer dapanchon = null;
        try {
            Connection c = JDBCUtil.getConnection();

            String sql = "SELECT DAP_AN_CHON FROM CHITIETBAILAM WHERE ID_BAILAMCT = ?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, id);

            System.out.println("Thực thi: " + sql);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                dapanchon = rs.getInt("DAP_AN_CHON");
            }

            JDBCUtil.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dapanchon;
    }
}
