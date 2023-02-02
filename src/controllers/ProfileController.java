/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author Habib
 */
public class ProfileController implements Initializable{

    
    //Database Tools
    private Connection connect;
    private PreparedStatement statement;
    private ResultSet result;
    
    @FXML
    private Label userNameLabel;
    
    @FXML
    private Label phoneLabel;
    
    @FXML
    private Label fullNameLabel;
    
    @FXML
    private Label emailLabel;
    
    @FXML
    private Label dobLabel;
    
    @FXML
    private Label addressLabel;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connect = StaticItemsClass.connectDB();
        showProfileDetails();
        System.out.println(StaticItemsClass.user_name);
        System.out.println(StaticItemsClass.user_id);
    }
    //private int user_id = StaticItemsClass.user_id;
    
    public String user_name = StaticItemsClass.user_name;
   
    public void showProfileDetails()
    {
        try{
            String sql = ("SELECT * FROM user_info where user_name = ?");
            statement = connect.prepareStatement(sql);
            statement.setString(1, user_name);
            
            result = statement.executeQuery();
            if(result.next())
            {
                fullNameLabel.setText(result.getString("full_name"));
                userNameLabel.setText(result.getString("user_name"));
                phoneLabel.setText(result.getString("contact_no"));
                emailLabel.setText(result.getString("email"));
                dobLabel.setText(result.getString("date_of_birth"));
                addressLabel.setText(result.getString("address"));
            }
            
            
            
        }
        catch(SQLException e){}
    }
    BaseController baseController = new BaseController();
    
    public void logOutBtnAction(ActionEvent event) throws IOException{
        StaticItemsClass.logInStatus=false;
        baseController.goToHomePage(event);
    }
            
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
        baseController.goToCheckWeatherPage(event);
    }
    public void goToEmergencySOSPage(ActionEvent event) throws IOException
    {
        baseController.goToEmergencySOSPage(event);
        System.out.println("This is sos");
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
        public void profileIconClick(ActionEvent event) throws IOException
    {
        baseController.profileIconClick(event);
    }
    
    
}
