/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cau3DeThiHk1;

import java.io.IOException;

/**
 *
 * @author lamqu
 */
public class Client {
    public static String inputLogin() throws IOException {
        String str = "";
        System.out.println("Nhap tai khoan: ");
        str += Input.input_String();
        str += "///";
        System.out.println("Nhap mat khau: ");
        str += Input.input_String();
        str += "///";

        return str;
    }
    
}
