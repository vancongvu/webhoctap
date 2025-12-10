package com.example.webhoctap.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.webhoctap.Database.JDBCUtil;
import com.example.webhoctap.model.Flashcard;

public class FlashcardDAO implements DAOInterface<Flashcard> {

    public static FlashcardDAO getInstance()
    {
        return new FlashcardDAO();
    }
    
    public int insert (Flashcard t)
    {
        int ketQua = 0;
        try
        {
            Connection c = JDBCUtil.getConnection();

            String sql = "INSERT INTO FLASHCARD (MATTRUOC, MATSAU, MONHOC_ID)" +
                         "VALUES (?,?,?)";
            
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, t.getMatTruoc());
            pst.setString(2, t.getMatSau());
            pst.setInt(3, t.getMonHocId());

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

    public int update (Flashcard t)
    {
        int ketQua = 0;
        try
        {
            Connection c = JDBCUtil.getConnection();

            String sql = "UPDATE FLASHCARD" +
                         "SET MATTRUOC = ? , MATSAU = ? , MONHOC_ID = ? " +
                         "WHERE ID_CAUHOI = ?";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, t.getMatTruoc());
            pst.setString(2, t.getMatSau());
            pst.setInt(3, t.getMonHocId());
            pst.setInt(4, t.getID());

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

    public int delete (Flashcard t)
    {
       int ketQua = 0;
        try
        {
            Connection c = JDBCUtil.getConnection();

            String sql = "DELETE FROM FLASHCARD WHERE ID_CAUHOI = ?";
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

    public ArrayList<Flashcard> selectAll()
    {
        ArrayList<Flashcard> ketQua = new ArrayList<Flashcard>();
        try
        {
            Connection c = JDBCUtil.getConnection();

            String sql = "SELECT * FROM FLASHCARD";
            PreparedStatement pst = c.prepareStatement(sql);

            System.out.println("Thực thi: " + sql);
            ResultSet rs = pst.executeQuery();
                        
            while(rs.next())
            {
                int id = rs.getInt("ID_CAUHOI");
                String mattruoc = rs.getString("MATTRUOC");
                String matsau = rs.getString("MATSAU");
                int monhocid = rs.getInt("MONHOC_ID");

                Flashcard flashcard = new Flashcard(id, mattruoc, matsau, monhocid);
                ketQua.add(flashcard);
            }

            JDBCUtil.closeConnection(c);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return ketQua;
    }

    public Flashcard selectById(Flashcard t)
    {
        Flashcard ketQua = null;
        try
        {
            Connection c = JDBCUtil.getConnection();

            String sql = "SELECT * FROM FLASHCARD WHERE ID_CAUHOI = ?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, t.getID());

            System.out.println("Thực thi: " + sql);
            ResultSet rs = pst.executeQuery();

            while(rs.next())
            {
                int id = rs.getInt("ID_CAUHOI");
                String mattruoc = rs.getString("MATTRUOC");
                String matsau = rs.getString("MATSAU");
                int monhocid = rs.getInt("MONHOC_ID");

                ketQua = new Flashcard(id, mattruoc, matsau, monhocid);
            }
            JDBCUtil.closeConnection(c);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return ketQua;
    }

    public ArrayList<Flashcard> selectByMonHocId(int monhocid)
    {
        ArrayList<Flashcard> ketQua = new ArrayList<Flashcard>();
        try
        {
            Connection c = JDBCUtil.getConnection();

            String sql = "SELECT * FROM FLASHCARD WHERE MONHOC_ID = ?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, monhocid);

            System.out.println("Thực thi: " + sql);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next())
            {
                int id = rs.getInt("MONHOC_ID");
                String mattruoc = rs.getString("MATTRUOC");
                String matsau = rs.getString("MATSAU");
                Flashcard flashcard = new Flashcard(id, mattruoc, matsau, monhocid);
                ketQua.add(flashcard);
            }

            JDBCUtil.closeConnection(c);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return ketQua;
    }

    public ArrayList<Flashcard> selectByCondition(String condition)
    {
        ArrayList<Flashcard> ketQua = new ArrayList<Flashcard>();
        try
        {
            Connection c = JDBCUtil.getConnection();

            String sql = "SELECT * FROM FLASHCARD WHERE " + condition;
            PreparedStatement pst = c.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            System.out.println(sql);
            
            while(rs.next())
            {
                int id = rs.getInt("ID_CAUHOI");
                String mattruoc = rs.getString("MATTRUOC");
                String matsau = rs.getString("MATSAU");
                int monhocid = rs.getInt("MONHOC_ID");

                Flashcard flashcard = new Flashcard(id, mattruoc, matsau, monhocid);
                ketQua.add(flashcard);
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
