/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.admin_panel;

import controllers.GuideProfileViewControllerByUser;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author Habib
 */
public class GuidesProfileViewController implements Initializable{
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setInfoData();
    }
    
    //Database tools
    private Connection connect = StaticClass.connect;
    private PreparedStatement statement;
    private ResultSet result;
    
    
    
    @FXML
    private Label guideNameLabel;
    
    @FXML
    private Label AdressLabel;
    
    @FXML
    private Label hRateLabel;
    
    @FXML
    private Label dailyRateLabel;
    
    @FXML
    private Label emailLabel;
    
     @FXML
    private Label contactNoLabel;
     
    @FXML
    private Label ratingLabel;
    
    @FXML
    private Label statusLabel;
    
    @FXML
    private Button activeBtn;
    
    @FXML
    private Button blockBtn;
    
    @FXML
    private Button markBtn;      
     
    String name;
    String email;
    String address;
    String contactNo;
    int hourlyRate;
    int dailyRate;
    double rating;
    String status;
    String activity;
    
    void setInfoData() {
        String sql = ("SELECT  * FROM `guide_info` where guide_id = ?");
       try {
           statement = connect.prepareStatement(sql);
           statement.setString(1, String.valueOf(StaticClass.current_guideId));
           result = statement.executeQuery();

           if(result.next())
           {
               name = result.getString("full_name");
               email = result.getString("email");
               address = result.getString("service_place");
               hourlyRate = result.getInt("hourly_rate");
               dailyRate = result.getInt("daily_rate");
               rating  = result.getDouble("rating");
               contactNo = result.getString("contact_no");
               status = result.getString("verified_status");
               activity = result.getString("activity_status");
               
               guideNameLabel.setText(name);
               AdressLabel.setText(address);
               emailLabel.setText(email);
               contactNoLabel.setText(contactNo);
               hRateLabel.setText(Integer.toString(hourlyRate));
               dailyRateLabel.setText(Integer.toString(dailyRate));
               ratingLabel.setText(Double.toString(rating));
               statusLabel.setText(status);
               
               if(status.equals("Pending")){
                   markBtn.setVisible(false);
                   activeBtn.setVisible(true);
               }
               if(activity.equals("free"))
               {
                   markBtn.setText("Mark Booked");
               }
               else if(activity.equals("booked")){
                    markBtn.setText("Mark Free");
               }
               
               if (status.equals("Blocked")) {
                   blockBtn.setText("Unblock Guide");
                   StaticClass.blockStatus = true;
                   markBtn.setVisible(false);
                   
               }
               else if(status.equals("Verified"))
               {
                   blockBtn.setText("Block Guide");
                   StaticClass.blockStatus = false;
                   markBtn.setVisible(true);
               }           
           }
       } 
       catch (SQLException ex) {
           Logger.getLogger(GuideProfileViewControllerByUser.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    @FXML
    public void activeGuide(){
        String sql = "UPDATE guide_info SET verified_status = ? WHERE guide_id = ?";
        
        try {
            statement = connect.prepareStatement(sql);
            statement.setString(1, "Verified");
            statement.setString(2, String.valueOf(StaticClass.current_guideId));
            statement.execute();
            activeBtn.setVisible(false); 
            markBtn.setVisible(true);
        } catch (Exception e) {
        }
        setInfoData();
    }
    @FXML
    public void blockGuide(){
        
        
        if(StaticClass.blockStatus)
        {
            String sql = "UPDATE `guide_info` SET `verified_status` = 'Verified' WHERE guide_id = ?";
            try {
            
            statement = connect.prepareStatement(sql);
            statement.setString(1, String.valueOf(StaticClass.current_guideId));
            statement.execute();
            
            } catch (Exception e) {
                
            }
        }
        else{
            String sql = "UPDATE `guide_info` SET `verified_status` = 'Blocked' WHERE guide_id = ?";
            try {
            
            statement = connect.prepareStatement(sql);
            statement.setString(1, String.valueOf(StaticClass.current_guideId));
            statement.execute();
            
            } catch (Exception e) {
                
            }
        }
        setInfoData();
    }
    
    public void bookkGuide(){
        
        
        if(activity.equals("free"))
        {
            String sql = "UPDATE `guide_info` SET `activity_status` = 'booked' WHERE guide_id = ?";
            try {
            
            statement = connect.prepareStatement(sql);
            statement.setString(1, String.valueOf(StaticClass.current_guideId));
            statement.execute();
            
            } catch (Exception e) {
                
            }
        }
        else{
            String sql = "UPDATE `guide_info` SET `activity_status` = 'free' WHERE guide_id = ?";
            try {
            
            statement = connect.prepareStatement(sql);
            statement.setString(1, String.valueOf(StaticClass.current_guideId));
            statement.execute();
            
            } catch (Exception e) {
                
            }
        }
        setInfoData();
    }
    
        
    AdminBaseController baseController = new AdminBaseController();
    public void goToLoginPage(ActionEvent event) throws IOException{
        baseController.goToLoginPage(event);
    }
    public void goToHomePage(ActionEvent event) throws IOException{
        baseController.goToHomePage(event);
    }
    public void goToUsersPage(ActionEvent event) throws IOException{
        baseController.goToUsersPage(event);
    }
    public void goToPackagesPage(ActionEvent event) throws IOException{
        baseController.goToPackagesPage(event);
    }
    public void goToGuidesPage(ActionEvent event) throws IOException{
        baseController.goToGuidesPage(event);
    }
    public void profileIconClick(ActionEvent event) throws IOException{
        baseController.profileIconClick(event);
    }
}
