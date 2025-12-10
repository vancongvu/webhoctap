package com.example.webhoctap.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.example.webhoctap.Database.JDBCUtil;
import com.example.webhoctap.model.TongQuanBaiLam;
import com.example.webhoctap.model.TongQuanBaiLamDS;

public class TongQuanBaiLamDAO implements DAOInterface<TongQuanBaiLam> {

    public static TongQuanBaiLamDAO getInstance()
    {
        return new TongQuanBaiLamDAO();
    }
    
    public int insert (TongQuanBaiLam t)
    {
        int ketQua = 0;
        try
        {
            Connection c = JDBCUtil.getConnection();

            String sql = "INSERT INTO TONGQUANBAILAM (TONGDIEM, SOCAUDUNG, TONGSOCAU, THOI_GIAN_LAM, MONHOC_ID, NGUOIDUNG_ID)" +
                         "VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setFloat(1, t.getTongDiem());
            pst.setInt(2, t.getSoCauDung());
            pst.setInt(3, t.getTongSoCau());
            pst.setTimestamp(4, t.getThoiGian());
            pst.setInt(5, t.getMonHocId());
            pst.setInt(6, t.getNguoiDungId());

            System.out.println("Thực thi: " + sql);
            ketQua = pst.executeUpdate();

            JDBCUtil.closeConnection(c);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return ketQua;
    }

    public int update (TongQuanBaiLam t)
    {
        int ketQua = 0;
        try
        {
            Connection c = JDBCUtil.getConnection();

            String sql = "UPDATE TONGQUANBAILAM" +
                         "SET TONGDIEM = ? ,  SOCAUDUNG = ? , TONGSOCAU = ? , THOI_GIAN_LAM = ?" +
                         "WHERE ID_TQBL = ?";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setFloat(1, t.getTongDiem());
            pst.setInt(2, t.getSoCauDung());
            pst.setInt(3, t.getTongSoCau());
            pst.setTimestamp(4, t.getThoiGian());
            pst.setInt(5, t.getID());

            System.out.println("Thực thi: " + sql);
            ketQua = pst.executeUpdate();

            JDBCUtil.closeConnection(c);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return ketQua;
    }

    public int delete (TongQuanBaiLam t)
    {
       int ketQua = 0;
        try
        {
            Connection c = JDBCUtil.getConnection();

            String sql = "DELETE FROM TONGQUANBAILAM WHERE ID_TQBL = ?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, t.getID());

            System.out.println("Thực thi: " + sql);
            ketQua = pst.executeUpdate();

            JDBCUtil.closeConnection(c);
        } 
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return ketQua;
    }

    public ArrayList<TongQuanBaiLam> selectAll()
    {
        ArrayList<TongQuanBaiLam> ketQua = new ArrayList<TongQuanBaiLam>();
        try
        {
            Connection c = JDBCUtil.getConnection();

            String sql = "SELECT * FROM TONGQUANBAILAM";
            PreparedStatement pst = c.prepareStatement(sql);

            System.out.println("Thực thi: " + sql);
            ResultSet rs = pst.executeQuery();
                        
            while(rs.next())
            {
                int id = rs.getInt("ID_TQBL");
                float tongdiem = rs.getFloat("TONGDIEM");
                int socadung = rs.getInt("SOCAUDUNG");
                int tongsocau = rs.getInt("TONGSOCAU");
                Timestamp thoigian = rs.getTimestamp("THOI_GIAN_LAM");
                int monhocid = rs.getInt("MONHOC_ID");
                int nguoidungid = rs.getInt("NGUOIDUNG_ID");

                TongQuanBaiLam tongquanbailam = new TongQuanBaiLam(id, tongdiem, socadung, tongsocau, thoigian, monhocid, nguoidungid);
                ketQua.add(tongquanbailam);
            }

            JDBCUtil.closeConnection(c);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return ketQua;
    }

    //lấy tất cả tổng quan bài làm theo user
    public ArrayList<TongQuanBaiLamDS> selectAllByUserID(int idUser)
    {
        ArrayList<TongQuanBaiLamDS> ketQua = new ArrayList<TongQuanBaiLamDS>();
        try
        {
            Connection c = JDBCUtil.getConnection();

            String sql = "SELECT tq.ID_TQBL, tq.TONGDIEM, tq.SOCAUDUNG, tq.TONGSOCAU, tq.THOI_GIAN_LAM, mh.TENMONHOC " +
                         "FROM TONGQUANBAILAM tq " +
                         "JOIN MONHOC mh ON tq.MONHOC_ID = mh.ID_MONHOC " +
                         "WHERE tq.NGUOIDUNG_ID = ? " +
                         "ORDER BY tq.THOI_GIAN_LAM DESC";
;
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, idUser);

            System.out.println("Thực thi: " + sql);
            ResultSet rs = pst.executeQuery();
                        
            while(rs.next())
            {
                int id = rs.getInt("ID_TQBL");
                String tenmonhoc = rs.getString("TENMONHOC");
                float tongdiem = rs.getFloat("TONGDIEM");
                int socaudung = rs.getInt("SOCAUDUNG");
                int tongsocau = rs.getInt("TONGSOCAU");
                Timestamp thoigian = rs.getTimestamp("THOI_GIAN_LAM");
                
                TongQuanBaiLamDS tongquanbailam = new TongQuanBaiLamDS(id, tenmonhoc, tongdiem, socaudung, tongsocau, thoigian);
                ketQua.add(tongquanbailam);
            }

            JDBCUtil.closeConnection(c);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return ketQua;
    }

    public TongQuanBaiLam selectById(TongQuanBaiLam t)
    {
        TongQuanBaiLam ketQua = null;
        try
        {
            Connection c = JDBCUtil.getConnection();

            String sql = "SELECT * FROM TONGQUANBAILAM WHERE ID_TQBL = ?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, t.getID());

            System.out.println("Thực thi: " + sql);
            ResultSet rs = pst.executeQuery();

            while(rs.next())
            {
                int id = rs.getInt("ID_TQBL");
                float tongdiem = rs.getFloat("TONGDIEM");
                int socadung = rs.getInt("SOCAUDUNG");
                int tongsocau = rs.getInt("TONGSOCAU");
                Timestamp thoigian = rs.getTimestamp("THOI_GIAN_LAM");
                int monhocid = rs.getInt("MONHOC_ID");
                int nguoidungid = rs.getInt("NGUOIDUNG_ID");

                ketQua = new TongQuanBaiLam(id, tongdiem, socadung, tongsocau, thoigian, monhocid, nguoidungid);
            }
            JDBCUtil.closeConnection(c);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return ketQua;
    }

    public ArrayList<TongQuanBaiLam> selectByCondition(String condition)
    {
        ArrayList<TongQuanBaiLam> ketQua = new ArrayList<TongQuanBaiLam>();
        try
        {
            Connection c = JDBCUtil.getConnection();

            String sql = "SELECT * FROM TONGQUANBAILAM WHERE " + condition;
            PreparedStatement pst = c.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            System.out.println(sql);
            
            while(rs.next())
            {
                int id = rs.getInt("ID_TQBL");
                float tongdiem = rs.getFloat("TONGDIEM");
                int socadung = rs.getInt("SOCAUDUNG");
                int tongsocau = rs.getInt("TONGSOCAU");
                Timestamp thoigian = rs.getTimestamp("THOI_GIAN_LAM");
                int monhocid = rs.getInt("MONHOC_ID");
                int nguoidungid = rs.getInt("NGUOIDUNG_ID");

                TongQuanBaiLam tongquanbailam = new TongQuanBaiLam(id, tongdiem, socadung, tongsocau, thoigian, monhocid, nguoidungid);
                ketQua.add(tongquanbailam);
            }

            JDBCUtil.closeConnection(c);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return ketQua;
    }
}
