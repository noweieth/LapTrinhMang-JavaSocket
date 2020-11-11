/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lamqu
 */
public class LTM_BTTL1_client {
       public static String nhapInfor() throws IOException{
           String str ="";
           System.out.println("Nhap Ma Nhan Vien: ");
           str+=Input.input_String();
           str+="/";
           System.out.println("Nhap Ten Nhan Vien: ");
           str+=Input.input_String();
           str+="/";
           System.out.println("Nhap Chuc Vu: ");
           str+=Input.input_String();
           str+="/";
           return str;
       }
       public static void tranferData(String str){
           try {
               Socket socket = new Socket("localhost",11133);
               System.out.println("Client Already...");
               
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                
                
                
                dos.writeUTF(str);
              
               
           } catch (IOException ex) {
               Logger.getLogger(LTM_BTTL1_client.class.getName()).log(Level.SEVERE, null, ex);
           }
           
       }
       public static void main(String[] args) throws IOException {
        // TODO code application logic here\
           String str = nhapInfor();
           tranferData(str);
    }
}
