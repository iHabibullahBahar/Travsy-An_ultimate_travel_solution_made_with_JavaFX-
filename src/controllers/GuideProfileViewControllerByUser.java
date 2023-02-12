/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import controllers.admin_panel.StaticClass;
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
public class GuideProfileViewControllerByUser implements Initializable{
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setInfoData();
    }
    
    //Database tools
    private Connection connect = StaticItemsClass.connect;
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
    private Button markBtn; 
    
    @FXML
    private Label statusLabel;       
     
     String name;
     String email;
     String address;
     String contactNo;
     int hourlyRate;
     int dailyRate;
     double rating;
     String status ;
     String verifiedStatus;
     boolean bookedByCurrentUser = false;
    
     void setInfoData() {
         String sql = ("SELECT  * FROM `guide_info` where guide_id = ?");
        try {
            statement = connect.prepareStatement(sql);
            statement.setString(1, String.valueOf(StaticItemsClass.current_guideId));
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
                status = result.getString("activity_status");
                verifiedStatus = result.getString("verified_status");       
                
                guideNameLabel.setText(name);
                AdressLabel.setText(address);
                emailLabel.setText(email);
                contactNoLabel.setText(contactNo);
                hRateLabel.setText(Integer.toString(hourlyRate));
                dailyRateLabel.setText(Integer.toString(dailyRate));
                ratingLabel.setText(Double.toString(rating));
                
                if(status.equals("free")){
                    markBtn.setVisible(true);
                    statusLabel.setText("The guide is free now. You can hire him.");
                }
                else{
                    markBtn.setVisible(false);
                    if(bookedByCurrentUser)
                    {
                        statusLabel.setText("You Successfully book the guide.");
                    }
                    else{
                        statusLabel.setText("The guide is booked now. You can't hire him.");
                    }
                        
                    
                }
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(GuideProfileViewControllerByUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     }
     
     
    public void bookkGuide(){
       
       String sql = "INSERT INTO `guide_order`(`guide_id`, `guides_user_name`, `user_id`, `users_user_name`) VALUES (?,?,?,?)";
       try {
       
       statement = connect.prepareStatement(sql);
       statement.setString(1, String.valueOf(StaticItemsClass.current_guideId));
       statement.setString(2, StaticItemsClass.current_guideUserName);
       statement.setString(3, String.valueOf(StaticItemsClass.user_id));
       statement.setString(4, StaticItemsClass.user_name);
       statement.execute();
       
       sql = "UPDATE `guide_info` SET `activity_status` = 'booked' WHERE guide_id = ?";
       statement = connect.prepareStatement(sql);
       statement.setString(1, String.valueOf(StaticItemsClass.current_guideId));
       statement.execute();
       bookedByCurrentUser = true;
       setInfoData();
       } catch (Exception e) {
           System.out.println("Cant Book");
       }
   }
  
    
    BaseController baseController = new BaseController();
    
    
    public void goToHomePage(ActionEvent event) throws IOException
    {
        baseController.goToHomePage(event);
    }
    public void goToLoginPage(ActionEvent event) throws IOException
    {
       baseController.goToLoginPage(event);
    }
    public void goToRegisterPage(ActionEvent event) throws IOException
    {
        baseController.goToRegisterPage(event);
    }
    
    public void goToPlanATourPage(ActionEvent event) throws IOException
    {
         baseController.goToPlanATourPage(event);
    }
    public void goToCheckWeatherPage(ActionEvent event) throws IOException
    {
        baseController.goToCheckWeatherPage(event);
    }
    public void goToExchangeRatePage(ActionEvent event) throws IOException
    {
        baseController.goToExchangeRatePage(event);
    }
    public void goToEmergencySOSPage(ActionEvent event) throws IOException
    {
        baseController.goToEmergencySOSPage(event);
    }
    public void goToBookAnythingPage(ActionEvent event) throws IOException
    {
         baseController.goToBookAnythingPage(event);
    }
    public void goToTravelWithTravsyPage(ActionEvent event) throws IOException
    {
        baseController.goToTravelWithTravsyPage(event);
    }
    
    public void goToSettingsPage(ActionEvent event) throws IOException
    {
        baseController.goToSettingsPage(event);
    }
    public void goToSupportPage(ActionEvent event) throws IOException
    {
        baseController.goToSupportPage(event);
    }
    public void goToAboutPage(ActionEvent event) throws IOException
    {
        baseController.goToAboutPage(event);
    }
    //Profile Icon Click Handeler 
    public void profileIconClick(ActionEvent event) throws IOException
    {
        baseController.profileIconClick(event);
    }

    
}
