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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lamqu
 */
public class LTM_BTTL1_server {
   
        public static void Server() throws ClassNotFoundException{
            try {
                ServerSocket ss = new ServerSocket(11133);
                System.out.println("Server already...");
                Socket socket = ss.accept();
                
                
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                
                String[] str = dis.readUTF().split("/");
                
                JDBC.updateNV(str[0],str[1], str[2]);
                
                
                
            } catch (IOException ex) {
                System.out.println("Loi");
            }
            
        }
        public static void main(String[] args) throws IOException, ClassNotFoundException {
        // TODO code application logic here
            Server();
        }
}
