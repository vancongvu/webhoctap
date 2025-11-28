package com.example.webhoctap.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.example.webhoctap.Database.JDBCUtil;
import com.example.webhoctap.model.TongQuanBaiLam;

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

            String sql = "INSERT INTO TONGQUANBAILAM (TONGDIEM, SOCAUDUNG, TONGSOCAU, THOI_GIAN_LAM)" +
                         "VALUES (?,?,?,?)";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setFloat(1, t.getTongDiem());
            pst.setInt(2, t.getSoCauDung());
            pst.setInt(3, t.getTongSoCau());
            pst.setTimestamp(4, t.getThoiGian());

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

                TongQuanBaiLam tongquanbailam = new TongQuanBaiLam(id, tongdiem, socadung, tongsocau, thoigian);
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

                ketQua = new TongQuanBaiLam(id, tongdiem, socadung, tongsocau, thoigian);
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

                TongQuanBaiLam tongquanbailam = new TongQuanBaiLam(id, tongdiem, socadung, tongsocau, thoigian);
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
