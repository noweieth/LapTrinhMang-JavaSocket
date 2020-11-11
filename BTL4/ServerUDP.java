/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BTL4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.JDBC;

/**
 *
 * @author lamqu
 */
public class ServerUDP {

    public static void updateDatatoSQL(String str) {
        if(str==null){
            return;
        }
        String[] infor = str.split("//");

        Connection conn = null;
        conn = jdbc.Connection1.CreateConn();
        String sql = "insert into qlnv.ql_sv(HoVaTen,MaSV,IDLop,DBT1,DBT2,DBT3,DGK) values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, infor[0]);
            ptmt.setString(2, infor[1]);
            ptmt.setString(3, infor[2]);
            ptmt.setFloat(4, Float.parseFloat(infor[3]));
            ptmt.setFloat(5, Float.parseFloat(infor[4]));
            ptmt.setFloat(6, Float.parseFloat(infor[5]));
            ptmt.setFloat(7, (Float.parseFloat(infor[5]) + Float.parseFloat(infor[3]) + Float.parseFloat(infor[4])) / 3);

            int kt = ptmt.executeUpdate();
            if (kt != 0) {
                System.out.println("Thanh Cong");
            } else {
                System.out.println("That bai");
            }

        } catch (SQLException ex) {
            Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public static boolean check_MSSV(String str) {
        String[] infor = str.split("//");
        Connection conn = null;
        conn = jdbc.Connection1.CreateConn();
        String sql = "SELECT * FROM qlnv.ql_sv where MaSV = '"+infor[1]+"'";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
               return false;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return true;
    }

    public static String getList() {
        String str = "";
        Connection conn = null;
        conn = jdbc.Connection1.CreateConn();
        String sql = "SELECT * FROM qlnv.ql_sv;";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                str += rs.getString("HoVaTen");
                str += "\t\t\t";
                str += String.valueOf(rs.getFloat("DGK"));
                str += "\n";
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return str;
    }

    public static void tranfersData() throws IOException {
        try {
            DatagramSocket server = new DatagramSocket(1245);
            while (true) {

                byte[] buff = new byte[1024];
                System.out.println("Server aldready...");
                DatagramPacket receive = new DatagramPacket(buff, 1024);
                server.receive(receive);
                byte[] receiveData = receive.getData();
                String receiveString = new String(receiveData, 0, receive.getLength());
                System.out.println(receiveString);
                if(!receiveString.equals("no data")){
                    updateDatatoSQL(receiveString);
                }
                
                String answerClient = "-----------DANH SÁCH SINH VIÊN-------------\n\n";
//                if(check_MSSV(receiveString)){
//                    updateDatatoSQL(receiveString);
//                    answerClient+="--> Sinh viên đã được cập nhật\n\n DANH SÁCH SINH VIÊN\n\n";
//                }else{
//                    answerClient+="--> Mã sinh viên đã trùng\n\n DANH SÁCH SINH VIÊN\n\n";
//                }
                

                answerClient += getList();
                byte[] strtoClient = answerClient.getBytes();
                System.out.println(strtoClient);
                int port = receive.getPort();// lay port tu goi packet o tren
                InetAddress add = receive.getAddress();// lay dia chi tu goi packet o tren
                DatagramPacket toclient = new DatagramPacket(strtoClient, strtoClient.length, add, port);
                server.send(toclient);//send packet
            }

        } catch (SocketException ex) {
            Logger.getLogger(ServerUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        tranfersData();
        //menu();
    }
}
