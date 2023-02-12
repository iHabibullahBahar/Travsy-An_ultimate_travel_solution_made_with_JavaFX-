/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.guide_panel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Habib
 */
public class StaticClass {
    //Database tools
    static Connection connect ;
    static String guide_user_name ;
    
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
