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
            return String.valueOf(minCost(arr, n-1, m-1));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "failed";
        }
    }

    public static void getLink() {
        try {
            ServerSocket ss = new ServerSocket(13334);
            System.out.println("Server getLink already...");

            Socket socket = ss.accept();
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            String str = dis.readUTF();
            System.out.println(readFile(str));
            String answer = readFile(str);
            
            dos.writeUTF(answer);

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void Login() {
        try {
            ServerSocket ss = new ServerSocket(13333);
            System.out.println("Server Login already...");
            while (true) {
                Socket socket = ss.accept();
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                String str = dis.readUTF();

                String[] arrStr = str.split("/");
                if (arrStr[0].equals("login")) {// kiem tra coi la login hay register
                    if (checkLogin(str)) {
                        dos.writeUTF("success");
                        getLink();
                    } else {
                        dos.writeUTF("failed");
                    }
                } else {
                    if (isExistUser(str)) {
                        dos.writeUTF("exist");
                    } else {
                        if (InsertUser(str)) {
                            dos.writeUTF("success");
                        } else {
                            dos.writeUTF("failed");
                        }
                    }

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
