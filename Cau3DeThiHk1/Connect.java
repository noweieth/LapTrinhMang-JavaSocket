/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cau3DeThiHk1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author lamqu
 */
public class Connect {
    public static Connection getConnection(){
        Connection conn = null;
        String name = "lamquangvinh";
        String pass = "A1t2o3";
        String url = "jdbc:sqlserver:/s/localhost:1433;databaseName=LTM";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, name, pass);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static void main(String[] args) throws IOException {
        System.out.println(getConnection());
    }
}
