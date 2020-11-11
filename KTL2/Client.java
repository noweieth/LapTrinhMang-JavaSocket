/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KTL2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author lamqu
 */
public class Client {

    public static void clearScreen() throws IOException, InterruptedException {
        //System("cls"); 
        //lalalalaRuntime.getRuntime().exec("cls");
    }

    public static String InputLink() throws IOException {
        String str = "";
        System.out.println("Nhập đường dẫn: ");
        str += Input.input_String() + "/";
        System.out.println("Nhập tên file: ");
        str += Input.input_String();
        return str;
    }

    public static void SendLink() {
        boolean check = false;

        try {
            String str = InputLink();
            Socket socket = new Socket("localhost", 13334);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            dos.writeUTF(str); // gui link qua cho server kiem tra file

            String Server_respone = dis.readUTF(); // server tra ket qua ve
            if(Server_respone.equals("failed")){
                System.out.println("[+]Server response => Lỗi đọc file!");
            }else{
                System.out.println("[+]Server response => Kết quả là: "+Server_respone);
            }

            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static boolean isValidPassword(String password, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static String InputLogin() throws IOException, InterruptedException {
        clearScreen();
        String str = "login/";
        System.out.println("Nhập tên đăng nhập: ");
        str += Input.input_String() + "/";
        System.out.println("Nhập mật khẩu: ");
        str += Input.input_String() + "/";
        return str;
    }

    public static String InputRegister() throws IOException, InterruptedException {
        clearScreen();
        String str = "register/";
        String username = "", password = "", password_re = "";
        boolean check_input = false;
        while (!check_input) {
            System.out.println("Nhập tên đăng nhập: ");
            username = Input.input_String();
            if (username.length() > 20 && username.length() < 6) {
                System.out.println("Tên đăng nhập không hợp lệ! Vui lòng nhập lại");
            } else {
                check_input = true;
            }
        }
        check_input = false;
        while (!check_input) {
            System.out.println("Nhập mật khẩu: ");
            password = Input.input_String();
            String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
            boolean validPassword = isValidPassword(password, regex);
            if (validPassword) {
                check_input = true;
            } else {
                System.out.println("Mật khẩu không hợp lệ! Vui lòng nhập lại");
            }
        }
        check_input = false;
        while (!check_input) {
            System.out.println("Nhập lại mật khẩu: ");
            password_re = Input.input_String();
            if (password_re.equals(password)) {
                check_input = true;
            } else {
                System.out.println("Mật khẩu không khớp! Vui lòng nhập lại");
            }
        }

        return str += username + "/" + password_re;
    }

    public static boolean Register() throws InterruptedException {
        boolean check = false;
        while (!check) {// vong lap vo han toi khi checkLogin() tra ve ket qua true
            try {
                String str = InputRegister();
                Socket socket = new Socket("localhost", 13333);
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

                dos.writeUTF(str); // gui User va pass qua Server check database

                String Server_respone = dis.readUTF();
                if (Server_respone.equals("success")) {
                    System.out.println("[+]Server response => Tạo tài khoản thành công");
                    return true;
                }
                if (Server_respone.equals("exist")) {
                    System.out.println("[+]Server response => Tài khoản đã tồn tài");
                    return true;
                } else {
                    System.out.println("[+]Server response => Tài khoản hoặc mật khẩu bị sai. Vui lòng nhập lại!");
                }
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public static boolean AuthenLogin() throws IOException, InterruptedException {
        boolean check = false;
        while (!check) {// vong lap vo han toi khi checkLogin() tra ve ket qua true
            try {
                String str = InputLogin();
                Socket socket = new Socket("localhost", 13333);
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

                dos.writeUTF(str); // gui User va pass qua Server check database

                String Server_respone = dis.readUTF();
                if (Server_respone.equals("success")) {
                    System.out.println("[+]Server response => Đăng nhập thành công");
                    SendLink();
                    return true;
                } else {
                    System.out.println("[+]Server response => Tài khoản hoặc mật khẩu bị sai. Vui lòng nhập lại!");
                }
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        String input_key = "";
        while (true) {
            System.out.println("--------New Session---------\n1. Login\n2. Register\nInput: ");
            input_key = Input.input_String();
            switch(input_key){
                case "1":
                    AuthenLogin();
                    break;
                case "2":
                    Register();
                    break;
                default:
                    System.out.println("Nhập sai vui lòng nhập lại!");
                    break;
                    
            }
        }
    }
}
