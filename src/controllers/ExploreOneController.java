/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Habib
 */
public class ExploreOneController implements Initializable{
    private Parent root;
    private Stage stage;
    private Scene scene;
    
    @FXML
    private Pane explorePane;
    
    
    @FXML
    private Label welcomeLabel;
    @FXML
    private ImageView imageView;
    
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setImage();
        
    }
    String image = StaticItemsClass.image;
    Image myImage = new Image(getClass().getResourceAsStream(image));
    public void setImage(){
        imageView.setImage(myImage);
    }
    
    @FXML
    public void explorePlace(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../views/travelwithtravsy_1.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
 
}
