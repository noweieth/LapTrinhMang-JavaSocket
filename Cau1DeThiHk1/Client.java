/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cau1DeThiHk1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author hoagl
 */
public class Client {
    public static int Input(){
        int n = 0;
        boolean check = false;
        Scanner sc = new Scanner(System.in);
        while(!check){
            try{
                System.out.println("Nhap vao so nguyen: ");
                n = Integer.parseInt(sc.nextLine());
                check = true;
            }
            catch(Exception e){
                check = false;
                System.out.println("Nhap lai!");
            }
        }
        return n;
    }
    
    public static void Transport() throws IOException{
        Socket client = new Socket("localhost", 1234);
        
        DataInputStream dis = new DataInputStream(client.getInputStream());
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        
        int n = Input();
        dos.writeInt(n);
        int kq = dis.readInt();
        System.out.println("Ket qua la: " +kq);
    }
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Transport();
    }
}
