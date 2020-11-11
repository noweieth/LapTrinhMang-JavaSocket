/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lamqu
 */
public class JDBC {

    /**
     * @param args the command line arguments
     */
    public static void updateNV(String ma,String ten,String chucvu) {
        Connection conn = null;
        conn = Connection1.CreateConn();
        String sql = "insert into qlnv.ql_nhanvien(MaNV,TenNV,ChucVu) values(?,?,?)";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, ma);
            ptmt.setString(2, ten);
            ptmt.setString(3, chucvu);

            int kt = ptmt.executeUpdate();
            if(kt!=0){
                System.out.println("Thanh Cong");
            }else{
                System.out.println("That bai");
            }

        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        Connection conn = null;
        conn = Connection1.CreateConn();
        System.out.println(conn);
        updateNV("NV01","Nguyen Van A","Giam Doc");
    }

}
