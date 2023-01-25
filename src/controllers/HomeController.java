
package controllers;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author Habib
 */
public class HomeController{
    private Parent root;
    private Stage stage;
    private Scene scene;
    public void goToHomePage(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("../views/home.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
    public void goToLoginPage(ActionEvent event) throws IOException
    {
        System.out.println("Hello");
        root = FXMLLoader.load(getClass().getResource("../views/login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void goToRegisterPage(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("../views/signup.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
    
    public void goToPlanATourPage(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("../views/planatour.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();  
    }
    public void goToCheckWeatherPage(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("../views/checkweather.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
    public void goToExchangeRatePage(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("../views/convertcurrency.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
    public void goToEmergencySOSPage(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("../views/sos.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
    public void goToBookAnythingPage(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("../views/travelwithtravsy.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
    public void goToTravelWithTravsyPage(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("../views/travelwithtravsy.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
    
    public void goToSettingsPage(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("../views/setting.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
    public void goToSupportPage(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("../views/setting.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
    public void goToAboutPage(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("../views/setting.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
    //Profile Icon Click Handeler 
    public void profileIconClick(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("../views/setting.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
    
}
