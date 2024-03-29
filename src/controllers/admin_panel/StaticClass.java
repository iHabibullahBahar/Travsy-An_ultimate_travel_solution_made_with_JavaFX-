/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.admin_panel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.prefs.Preferences;
/**
 *
 * @author Habib
 */
public class StaticClass {
        //Database tools
    static Connection connect ;
    static String user_name ="" ;
    static int user_id ;
    static boolean logInStatus;
    
    //admin Info
    static String adminUserName = "admin";
    
    //Signup Status
    static boolean signup_status = false;
    
    static Preferences prefs = Preferences.userRoot().node("/user/custom/root");
    
    //Guide Info
    static int current_guideId ;
    static boolean blockStatus = false;
    
    //tour package info
    static int current_packageId;
    
    //user Info View
    static int currentUserId;
    static String currentUserName;
    static boolean userBlockStatus = false;
    
    public static Connection connectDB(){
    try{
        //plsek database
        //connect = DriverManager.getConnection("jdbc:mysql://134.122.10.205:3306/db_travsy?autoReconnect=true&useSSL=false","travsy_admin","admin@1221##");
        connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/db_travsy?autoReconnect=true&useSSL=false","root","");
        System.out.println("Connnected");
        return connect;
    }
    catch(SQLException e){e.printStackTrace();}
        return null;
    }
    
}
