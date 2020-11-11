/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;
import java.sql.*;
/**
 *
 * @author lamqu
 */
public class Connection1 {
        public static Connection CreateConn() {
		Connection conn=null;
		String name = "root";
		String pass = "1234";
		String url ="jdbc:mysql://localhost:3306/finalweb?useUnicode=true&amp;characterEncoding=utf8";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,name,pass);
			
		}
		catch(ClassNotFoundException|SQLException e){
			e.printStackTrace();
		}
		
		return conn;
	}
}
