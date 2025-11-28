package com.example.webhoctap.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.webhoctap.Database.JDBCUtil;
import com.example.webhoctap.model.MonHoc;

public class MonHocDAO implements DAOInterface<MonHoc> {

    public static MonHocDAO getInstance()
    {
        return new MonHocDAO();
    }

    public int insert (MonHoc t)
    {
        int ketQua = 0;
        try
        {
            Connection c = JDBCUtil.getConnection();

            String sql = "INSERT INTO MONHOC (TENMONHOC, MOTA)" +
                         "VALUES (?,?)";
            
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, t.getTenMonHoc());
            pst.setString(2, t.getMoTa());
            
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

    public int update (MonHoc t)
    {
        int ketQua = 0;
        try
        {
            Connection c = JDBCUtil.getConnection();

            String sql = "UPDATE MONHOC" +
                         "SET TENMONHOC = ? , MOTA = ? " +
                         "WHERE ID_MONHOC = ?";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, t.getTenMonHoc());
            pst.setString(2, t.getMoTa());
            pst.setInt(3, t.getID());

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

    public int delete (MonHoc t)
    {
       int ketQua = 0;
        try
        {
            Connection c = JDBCUtil.getConnection();

            String sql = "DELETE FROM MONHOC WHERE ID_MONHOC = ?";
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

    public ArrayList<MonHoc> selectAll()
    {
        ArrayList<MonHoc> ketQua = new ArrayList<MonHoc>();
        try
        {
            Connection c = JDBCUtil.getConnection();

            String sql = "SELECT * FROM MONHOC";
            PreparedStatement pst = c.prepareStatement(sql);

            System.out.println("Thực thi: " + sql);
            ResultSet rs = pst.executeQuery();
                        
            while(rs.next())
            {
                int id = rs.getInt("ID_MONHOC");
                String tenmonhoc = rs.getString("TENMONHOC");
                String mota = rs.getString("MOTA");

                MonHoc monhoc = new MonHoc(id, tenmonhoc, mota);
                ketQua.add(monhoc);
            }

            JDBCUtil.closeConnection(c);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return ketQua;
    }

    public MonHoc selectById(MonHoc t)
    {
        MonHoc ketQua = null;
        try
        {
            Connection c = JDBCUtil.getConnection();

            String sql = "SELECT * FROM MONHOC WHERE ID_MONHOC = ?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, t.getID());

            System.out.println("Thực thi: " + sql);
            ResultSet rs = pst.executeQuery();

            while(rs.next())
            {
                int id = rs.getInt("ID_MONHOC");
                String tenmonhoc = rs.getString("TENMONHOC");
                String mota = rs.getString("MOTA");

                ketQua = new MonHoc(id, tenmonhoc, mota);
            }
            JDBCUtil.closeConnection(c);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return ketQua;
    }

    public ArrayList<MonHoc> selectByCondition(String condition)
    {
        ArrayList<MonHoc> ketQua = new ArrayList<MonHoc>();
        try
        {
            Connection c = JDBCUtil.getConnection();

            String sql = "SELECT * FROM MONHOC WHERE " + condition;
            PreparedStatement pst = c.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            System.out.println(sql);
            
            while(rs.next())
            {
                int id = rs.getInt("ID_MONHOC");
                String tenmonhoc = rs.getString("TENMONHOC");
                String mota = rs.getString("MOTA");

                MonHoc monhoc = new MonHoc(id, tenmonhoc, mota);
                ketQua.add(monhoc);
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
