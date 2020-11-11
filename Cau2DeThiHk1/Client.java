/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cau2DeThiHk1;

import static Client.Client_UDP_1.Client_UDP;
import Client.Input;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lamqu
 */
public class Client {

    public static String input() throws IOException {
        String str = "";
        System.out.println("Nhap duong dan nguon: ");
        String Adress1 = Input.input_String();
        System.out.println("Nhap duong dan dich: ");
        String Adress2 = Input.input_String();
        str = Adress1 + "," + Adress2;
        return str;
    }

    public static void Transport() throws IOException {
        try {
            String str = input();
            DatagramSocket socket = new DatagramSocket();
            byte[] buff = str.getBytes();

            InetAddress remote = InetAddress.getByName("localhost");
            DatagramPacket packet = new DatagramPacket(buff, buff.length, remote, 12344);

            socket.send(packet);

            // send data aldready
            
            byte[] buff1 = new byte[1024];
            DatagramPacket receivefromServer = new DatagramPacket(buff1, 1024);
            socket.receive(receivefromServer);
            byte[] receiveData = receivefromServer.getData();
            String receiveString = new String(receiveData, 0, receivefromServer.getLength());
            System.out.println(receiveString);
            
            //recieve data from server

        } catch (SocketException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Transport();
        //menu();
    }
}
