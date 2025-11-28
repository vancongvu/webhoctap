package com.example.webhoctap.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.webhoctap.Database.JDBCUtil;
import com.example.webhoctap.model.ChiTietBaiLam;

public class ChiTietBaiLamDAO implements DAOInterface<ChiTietBaiLam> {

    public static ChiTietBaiLamDAO getInstance()
    {
        return new ChiTietBaiLamDAO();
    }
    
    public int insert (ChiTietBaiLam t)
    {
        int ketQua = 0;
        try
        {
            Connection c = JDBCUtil.getConnection();

            String sql = "INSERT INTO CHITIETBAILAM (DAP_AN_CHON)" +
                         "VALUES (?)";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, t.getDapAnChon());

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

    public int update (ChiTietBaiLam t)
    {
        int ketQua = 0;
        try
        {
            Connection c = JDBCUtil.getConnection();

            String sql = "UPDATE CAUHOI" +
                         "SET DAP_AN_CHON = ? " +
                         "WHERE ID_BAILAMCT = ?";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, t.getDapAnChon());
            pst.setInt(2, t.getID());

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

    public int delete (ChiTietBaiLam t)
    {
       int ketQua = 0;
        try
        {
            Connection c = JDBCUtil.getConnection();

            String sql = "DELETE FROM CHITIETBAILAM WHERE ID_BAILAMCT = ?";
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

    public ArrayList<ChiTietBaiLam> selectAll()
    {
        ArrayList<ChiTietBaiLam> ketQua = new ArrayList<ChiTietBaiLam>();
        try
        {
            Connection c = JDBCUtil.getConnection();

            String sql = "SELECT * FROM CHITIETBAILAM";
            PreparedStatement pst = c.prepareStatement(sql);

            System.out.println("Thực thi: " + sql);
            ResultSet rs = pst.executeQuery();
                        
            while(rs.next())
            {
                int id = rs.getInt("ID");
                int dapanchon = rs.getInt("DAP_AN_CHON");

                ChiTietBaiLam chitietbailam = new ChiTietBaiLam(id, dapanchon);
                ketQua.add(chitietbailam);
            }

            JDBCUtil.closeConnection(c);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return ketQua;
    }

    public ChiTietBaiLam selectById(ChiTietBaiLam t)
    {
        ChiTietBaiLam ketQua = null;
        try
        {
            Connection c = JDBCUtil.getConnection();

            String sql = "SELECT * FROM CHITIETBAILAM WHERE ID_BAILAMCT = ?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, t.getID());

            System.out.println("Thực thi: " + sql);
            ResultSet rs = pst.executeQuery();

            while(rs.next())
            {
                int id = rs.getInt("ID");
                int dapanchon = rs.getInt("DAP_AN_CHON");

                ketQua = new ChiTietBaiLam(id, dapanchon);
            }
            JDBCUtil.closeConnection(c);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return ketQua;
    }

    public ArrayList<ChiTietBaiLam> selectByCondition(String condition)
    {
        ArrayList<ChiTietBaiLam> ketQua = new ArrayList<ChiTietBaiLam>();
        try
        {
            Connection c = JDBCUtil.getConnection();

            String sql = "SELECT * FROM CHITIETBAILAM WHERE " + condition;
            PreparedStatement pst = c.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            System.out.println(sql);
            
            while(rs.next())
            {
                int id = rs.getInt("ID");
                int dapanchon = rs.getInt("DAP_AN_CHON");

                ChiTietBaiLam chitietbailam = new ChiTietBaiLam(id, dapanchon);
                ketQua.add(chitietbailam);
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
