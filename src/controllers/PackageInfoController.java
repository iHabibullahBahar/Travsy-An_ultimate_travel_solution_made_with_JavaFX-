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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author Habib
 */
public class PackageInfoController implements Initializable{
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setInfoData();
    }
    
    //Database tools
    private Connection connect = StaticItemsClass.connect;
    private PreparedStatement statement;
    private ResultSet result;
    
    
    
    @FXML
    private Label placeLabel;
    
    @FXML
    private Label routeLabel;
    
    @FXML
    private Label stayLabel;
    
    @FXML
    private Label costLabel;
    

     
     String place;
     String route;
     String stay;
     int cost;
     void setInfoData() {
         String sql = ("SELECT  * FROM `package_of_tour` where package_id = ?");
        try {
            statement = connect.prepareStatement(sql);
            statement.setString(1, String.valueOf(StaticItemsClass.current_packageId));
            result = statement.executeQuery();
            
            if(result.next())
            {
                place = result.getString("place");
                route = result.getString("route");
                stay = result.getString("stay");
                cost = result.getInt("price");

                
                placeLabel.setText(place);
                routeLabel.setText(route);
                stayLabel.setText(stay);
                costLabel.setText(Integer.toString(cost));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GuideProfileViewControllerByUser.class.getName()).log(Level.SEVERE, null, ex);
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
        baseController.goToCheckWeatherPage(event);
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
