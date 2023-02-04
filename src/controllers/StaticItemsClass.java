/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Habib
 */
public class StaticItemsClass {
    
    //Database tools
    static Connection connect;
    static String user_name ="" ;
    static int user_id ;
    static boolean logInStatus = false;
    
    
    //Signup Status
    static boolean signup_status = false;
    public static Connection connectDB(){
    try{
        //plsek database
        //connect = DriverManager.getConnection("jdbc:mysql://134.122.10.205:3306/db_travsy?autoReconnect=true&useSSL=false","travsy_admin","admin@1221##");
        connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/db_travsy?autoReconnect=true&useSSL=false","root","");
        System.out.println("Connected Successfully");
        return connect;
    }
    catch(SQLException e){e.printStackTrace();}
        return null;
}
}
