
package controllers;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author Habib
 */
public class HomeController implements Initializable{
    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    private Label welcomeLabel;
    
    Preferences prefs = Preferences.userRoot().node("/user/custom/root");
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

    CheckWeatherController chkWCon = new CheckWeatherController();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        StaticItemsClass.logInStatus = prefs.getBoolean("logInStatus", false);
        if(StaticItemsClass.logInStatus == true)
        {
            StaticItemsClass.user_name = prefs.get("username", "bappa");
            StaticItemsClass.user_id = prefs.getInt("userid", 5);
            welcomeLabel.setText(welcomeLabel.getText()+ " "+StaticItemsClass.user_name);
        }
        else{
            welcomeLabel.setText(welcomeLabel.getText()+ " User");
        }
        
        try{
            
            chkWCon.getWeatherReport();}
        catch(Exception e){
            System.out.println("unable to connect the check weather api");
        }
        try {
            StaticItemsClass.connect = StaticItemsClass.connectDB();
        } catch (Exception e) {
        }
        
        
     }
    
}
