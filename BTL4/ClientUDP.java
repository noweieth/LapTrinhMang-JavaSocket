/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BTL4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lamqu
 */
public class ClientUDP {
    public static void clrscr(){
   //Clears Screen in java
        try
            {
                final String os = System.getProperty("os.name");

                if (os.contains("Windows"))
                {
                    Runtime.getRuntime().exec("cls");
                }
                else
                {
                    Runtime.getRuntime().exec("clear");
                }
            }
            catch (final Exception e)
            {
                //  Handle any exceptions.
            }
     }
    public static String getInfor() throws IOException{
        System.out.println("Nhap thong tin : \n");
        String str = "";
        System.out.println("Nhap Ho va Ten: ");
        str+=Input.input_String();
        str+="//";
        
        System.out.println("Nhap Ma so sinh vien: ");
        str+=Input.input_String();
        str+="//";
        
        System.out.println("Nhap Ma lop: ");
        str+=Input.input_String();
        str+="//";
        
        System.out.println("Nhap Diem BT1: ");
        str+=String.valueOf(Input.input_float());
        str+="//";
        
        System.out.println("Nhap Diem BT2: ");
        str+=String.valueOf(Input.input_float());
        str+="//";
        
        System.out.println("Nhap Diem BT3: ");
        str+=String.valueOf(Input.input_float());
        str+="//";
        
        return str;
    }
    public static void tranfersData(String str) throws IOException{
        try {
            DatagramSocket socketClient = new DatagramSocket();
            byte[] strClient = str.getBytes();
            InetAddress remoteAddress = InetAddress.getByName("localhost");
            DatagramPacket dataPacClient = new DatagramPacket(strClient, strClient.length, remoteAddress, 1245);
            socketClient.send(dataPacClient);
            
            byte[] getfromServer = new byte[10010];
            DatagramPacket receivefromServer = new DatagramPacket(getfromServer, getfromServer.length);
            socketClient.receive(receivefromServer);
            byte[]receiveData = receivefromServer.getData();
            String dataStr = new String(receiveData, 0, receivefromServer.getLength());
            System.out.println(dataStr);
            
        } catch (SocketException ex) {
            Logger.getLogger(ClientUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String check ="n";
        String str="";
        while(true){
            System.out.println("Bạn có muốn nhập thông tin không?[y/n]: ");
            check = Input.input_String();
            if(!check.equals("y")){
                str="no data";
                tranfersData(str);
            }else{
                str=getInfor();
                tranfersData(str);
            }
        }
        
        //menu();
    }
}
