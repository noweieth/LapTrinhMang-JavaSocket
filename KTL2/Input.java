/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KTL2;

import BTL4.*;
import jdbc.*;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author lamqu
 */
public class Input {
    public static String input_String() throws IOException{
        String str = null;
        
        boolean check = false;
        Scanner sc = new Scanner(System.in);
        while(!check){
                try{
                    str=sc.nextLine();
                    check = true;
                }catch(Exception ex){
                    check = false;
                    System.out.println(ex);
                }
         }
        return str;
    }
    public static int input_int(){
        int n=0;
        boolean check = false;
        Scanner sc = new Scanner(System.in);
        while(!check){
                try{
                    n=Integer.parseInt(sc.nextLine());
                    if(n<0){
                        check = false;
                    }else check = true;
                }catch(Exception ex){
                    check = false;
                    System.out.println("Nhap sai! Vui Long Nhap Lai");
                }
         }
        return n;
    }
    
    public static float input_float(){
        float n=0;
        boolean check = false;
        Scanner sc = new Scanner(System.in);
        while(!check){
                try{
                    n=Float.parseFloat(sc.nextLine());
                    check = true;
                }catch(Exception ex){
                    check = false;
                    System.out.println(ex);
                }
         }
        
        
        return n;
    }
}
