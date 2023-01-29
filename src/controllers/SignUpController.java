/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Habib
 */
public class SignUpController implements Initializable{

    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    private TextField emailField;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField questionAnswerField;


    @FXML
    private ComboBox<String> securityQComboBox;
    @FXML
    private Button signUpBtn;

    @FXML
    private TextField userNameField;

    //"What is your pet name?","What is your childhood nick name?"
    private String[] questions = {"What is your pet name?","What is your childhood nick name?"};
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        securityQComboBox.getItems().addAll(questions);
        
    }
    public void goToLoginPage(ActionEvent event) throws IOException{
        
        root = FXMLLoader.load(getClass().getResource("../views/login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void goToHomePage(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../views/home.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
    
}
