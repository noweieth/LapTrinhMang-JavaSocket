/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cau1DeThiHk1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author hoagl
 */
public class Server {
    
    public static void Transport() throws IOException{
        ServerSocket server = new ServerSocket(1234);
        System.out.println("Server đã sẵn sàng");
        Socket socket = server.accept();
        
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        
        int n = dis.readInt();
        System.out.println(n);
      
        System.out.println("Tổng các chữ số là: " +tinhTongCacChuSoDungWhile(n));
        dos.writeInt(tinhTongCacChuSoDungWhile(n));
    }
    
        public static int tinhTongCacChuSoDungWhile(int n)
    {
	int nRem, nSum = 0;
        int m = n;
        int temp = 0;
        if(n < 0){
            n = -n;
        }
	while (n != 0)
	{
		// Lấy chữ số cuối của số nguyên sử dụng toán tử chia lấy dư (Modulus)
                
		nRem = n % 10;

		// Cộng chữ số cuối vào biến Sum
		nSum = nSum + nRem;

		// Xóa chữ số cuối bằng cách sử dụng toán tử chia lấy phần nguyên (Division)
                temp = n;
		n = n / 10;
	}
        if(m < 0){
            nSum = nSum - temp - temp;
        }
	return nSum;
    }   
        
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Transport();
    }
}
