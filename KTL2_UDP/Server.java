/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KTL2;

import static KTL2.ConnectDTB.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lamqu
 */
public class Server {

    //    code xu li link : https://www.geeksforgeeks.org/min-cost-path-dp-6/
    static int min(int x, int y, int z) {
        if (x < y) {
            return (x < z) ? x : z;
        } else {
            return (y < z) ? y : z;
        }
    }

    static int minCost(int cost[][], int m,
            int n) {
        if (n < 0 || m < 0) {
            return Integer.MAX_VALUE;
        } else if (m == 0 && n == 0) {
            return cost[m][n];
        } else {
            return cost[m][n]
                    + min(minCost(cost, m - 1, n - 1),
                            minCost(cost, m - 1, n),
                            minCost(cost, m, n - 1));
        }
    }

    public static String readFile(String link) { // doc file va chuyen file thanh mang int[][]
        int arr[][] = new int[100][100];
        int m = 0, n = -1;
        try {
            File myObj = new File(link);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                n++;
                String[] arrStr = data.split(" ");
                m = arrStr.length;
                for (int i = 0; i < m; i++) {
                    arr[n][i] = Integer.parseInt(arrStr[i]);
                }
            }
            n++;
            for (int i = 0; i < n; i++) {
                System.out.println("");
                for (int j = 0; j < m; j++) {
                    System.out.print(arr[i][j] + " ");
                }
                System.out.println("");
            }
            myReader.close();
            return String.valueOf(minCost(arr, n - 1, m - 1));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "failed";
        }
    }

    public static void getLink() {
        try {
            DatagramSocket socket = new DatagramSocket(12345);
            System.out.println("Server getLink already...");

            byte[] buff = new byte[1024];
            DatagramPacket packetRecieve = new DatagramPacket(buff, buff.length);
            byte[] receiveData = packetRecieve.getData();
            socket.receive(packetRecieve);
            String str = new String(receiveData, 0, receiveData.length);

            
            str = str.replace("//", "/");
            System.out.println(str);
            String answer = readFile(str);

            byte[] sendClient = answer.getBytes();
            int port = packetRecieve.getPort();
            InetAddress add = packetRecieve.getAddress();
            DatagramPacket toclient = new DatagramPacket(sendClient, sendClient.length, add, port);
            socket.send(toclient);//send packet
            socket.close();

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void Login() {
        try {

            while (true) {
                //recieve du lieu tu client
                DatagramSocket socket = new DatagramSocket(12344);
                System.out.println("Server Login already...");
                byte[] buff = new byte[1024];
                DatagramPacket packetRecieve = new DatagramPacket(buff, buff.length);
                byte[] receiveData = packetRecieve.getData();
                socket.receive(packetRecieve);
                String str = new String(receiveData, 0, receiveData.length);

                String[] arrStr = str.split("/");

                String respone_client = "";
                if (arrStr[0].equals("login")) {// kiem tra coi la login hay register
                    if (checkLogin(str)) {
                        respone_client = "success";
                    } else {
                        respone_client = "failed";
                    }
                } else {
                    if (isExistUser(str)) {
                        respone_client = "exist";
                    } else {
                        if (InsertUser(str)) {
                            respone_client = "success";
                        } else {
                            respone_client = "failed";
                        }
                    }

                }
                // thong bao cho client
                byte[] sendClient = respone_client.getBytes();
                int port = packetRecieve.getPort();
                InetAddress add = packetRecieve.getAddress();
                DatagramPacket toclient = new DatagramPacket(sendClient, sendClient.length, add, port);
                socket.send(toclient);//send packet
                socket.close();
                if (respone_client.equals("success")) {
                    getLink();
                }

            }

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        Login();
    }
}
