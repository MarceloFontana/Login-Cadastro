package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        String driver = "com.mysql.jdbc.Driver";
        String user = "root";
        String pass = "";
        String location = "jdbc:mysql://localhost:3307/cadastro_db";
        
        Class.forName(driver);
        
        Connection conn = DriverManager.getConnection(
                location, user, pass
        );
        return conn;
    }
}
