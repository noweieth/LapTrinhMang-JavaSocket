/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cau2DeThiHk1;

import static Client.Client_UDP_1.Client_UDP;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lamqu
 */
public class Server {

    public static void Transport() throws IOException {
        try {
            DatagramSocket socket = new DatagramSocket(12344);
            System.out.println("Server aldready");
            byte[] buff = new byte[1024];
            DatagramPacket packetRecieve = new DatagramPacket(buff, buff.length);
            byte[] receiveData = packetRecieve.getData();
            socket.receive(packetRecieve);
            String strRecieve = new String(receiveData, 0, receiveData.length);

            System.out.println(strRecieve);
            String[] link = strRecieve.split(",");

            System.out.println(readFile(link[0]));

            String[] arrString = readFile(link[0]).split(" ");
            int arrInt[] = new int[1024];
            int dem = 0;
            for (int i = 0; i < arrString.length; i++) {
                if (arrString[i] != null) {
                    arrInt[dem] = Integer.parseInt(arrString[i]);
                    dem++;
                }
            }
            String str1 = "";// chuyen lai thanh mang 2 chieu
            Arrays.sort(arrInt, 0, dem);
            for (int i = 0; i < dem; i++) {
                str1 += String.valueOf(arrInt[i]) + " ";
                if (((i + 1) % 7) == 0) {
                    str1 += "\n";
                }
            }
            link[1] += "//MATRANMANG.txt";
            // tao file 
            try {
                File myObj = new File("MATRANMANG.txt");
                if (myObj.createNewFile()) {
                    System.out.println("File created: " + myObj.getName());
                } else {
                    System.out.println("File da ton tai.");
                }
            } catch (IOException e) {
                System.out.println("Loi tao file.");
                e.printStackTrace();
            }
            //ghi data vao file
            try {
                FileWriter myWriter = new FileWriter("MATRANMANG.txt");
                myWriter.write(str1);
                myWriter.close();
                System.out.println(str1 + "Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            // thong bao cho client
            byte[] sendClient = "COPY FILE THANH CONG".getBytes();
            int port = packetRecieve.getPort();
            InetAddress add = packetRecieve.getAddress();
            DatagramPacket toclient = new DatagramPacket(sendClient, sendClient.length, add, port);
            socket.send(toclient);//send packet

        } catch (SocketException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String readFile(String link) {
        String str = "";
        try {
            File myObj = new File(link);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                str += data + " ";
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Loi doc file.");
            e.printStackTrace();
        }
        return str;
    }

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Transport();
        //menu();
    }
}
