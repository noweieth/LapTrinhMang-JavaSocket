/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KTL2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lamqu
 */
public class ConnectDTB {
    public static boolean InsertUser(String str){
        String[] arrStr = str.split("/");
        Connection conn = getConnect();
        String sql = "insert into USERS(Users,Password) values ('"+arrStr[1]+"','"+arrStr[2]+"')";
        try {
            PreparedStatement ptsm = conn.prepareStatement(sql);
            int kt = ptsm.executeUpdate();
            if(kt!=0){
                return true;
            }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDTB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public static boolean isExistUser(String str){
        String[] arrStr = str.split("/");
        Connection conn = getConnect();
        String sql = "select * from USERS where USERS.Users ='"+arrStr[1]+"'";
        try {
            PreparedStatement ptsm = conn.prepareStatement(sql);
            ResultSet rs = ptsm.executeQuery();
            if(rs.next()){
                return true;
            }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDTB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public static boolean checkLogin(String str){
        String[] arrStr = str.split("/");
        Connection conn = getConnect();
        String sql = "select * from USERS where USERS.Users ='"+arrStr[1]+"' and USERS.Password ='"+arrStr[2]+"'";
        try {
            PreparedStatement ptsm = conn.prepareStatement(sql);
            ResultSet rs = ptsm.executeQuery();
            if(rs.next()){
                return true;
            }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDTB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public static Connection getConnect() {// lay ket noi voi database sql server
        Connection conn = null;
        String name = "lamquangvinh";
        String pass = "A1t2o3";
        String url = "jdbc:sqlserver://localhost:1433;databaseName=KTL2";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, name, pass);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static void main(String[] args) {
        System.out.println(getConnect());
    }
}
