package com.example.webhoctap.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
    public static Connection getConnection()
    {
        Connection c = null;
        
        try
        {
            // tao connection string toi sql server
            String url = "jdbc:sqlserver://localhost:1433;"
            + "databaseName=WebHocTap;"
            + "encrypt=true;"
            + "trustServerCertificate=true";

            String user = "sa";
            String password = "123456";

            c = DriverManager.getConnection(url, user, password);
            System.out.println("Ket noi thanh cong");
        }
        catch(SQLException e)
        {
            System.out.println("Ket noi that bai");
            e.printStackTrace();
        }
        return c;
    }
    public static void closeConnection(Connection c)
    {
        try
        {
            if(c != null)
            {
                c.close();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
