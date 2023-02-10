/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Habib
 */
public class GuideSignupController implements Initializable{

    private Parent root;
    private Stage stage;
    private Scene scene;
    
    //Database Tools
    private Connection connect;
    private PreparedStatement statement;
    private ResultSet result;
    
    //
    private String password;
    
    
    
    @FXML
    private TextField serviceField;

    @FXML
    private TextField nameField;
    
    @FXML
    private TextField phoneField;

    @FXML
    private PasswordField passwordField;
    
    @FXML
    private Button signUpBtn;
    
    @FXML
    private Label warningLabel;

    @FXML
    private TextField userNameField;

    BaseController baseController = new BaseController();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        connect = StaticItemsClass.connectDB();
    }
    public void goToLoginPage(ActionEvent event) throws IOException{
        
        baseController.goToLoginPage(event);
    }
    public void goToHomePage(ActionEvent event) throws IOException{
        baseController.goToHomePage(event);
    }
    public void goToRegisterPage(ActionEvent event) throws IOException
    {
        baseController.goToRegisterPage(event);
    }
    public void signUpBtnAction(ActionEvent event) throws SQLException
    {
        warningLabel.setText("");
        boolean notUniqeName = false;
        
        if(nameField.getText().length()<3){
            warningLabel.setText("Name at least 3 characters in length");
        }
        else if(userNameField.getText().length()<4){
            warningLabel.setText("User name at least 4 characters in length");
        }
        else if((phoneField.getText().length()<10 )){
            warningLabel.setText("Please Enter a Valid Phone no.");
        }
        else if(serviceField.getText().length()<3){
            warningLabel.setText("Enter A valid Place");
        }
        else if((passwordField.getText().length()<6 )){
            warningLabel.setText("Password at least 6 characters in length");
        }
        
        else{
            try{
                String sql = ("SELECT * FROM guide_info WHERE user_name = ?");
                statement = connect.prepareStatement(sql);
                statement.setString(1, userNameField.getText());
                result = statement.executeQuery(); 
                if(result.next())
                {
                    notUniqeName = true;
                }
                
                
                //Setting data to warning Label
                if(notUniqeName)
                {
                    warningLabel.setText("Username already exists");
                }
       
                else
                {
                    try{
                        String userName = userNameField.getText();
                        sql = ("INSERT INTO auth (user_name,password,role) values(?,?,'guide')");
                        statement = connect.prepareStatement(sql);
                        statement.setString(1, userNameField.getText());
                        statement.setString(2, passwordField.getText());
                        statement.execute();
                        System.out.println(userNameField.getText());
                        sql = ("INSERT INTO guide_info (user_id,user_name) SELECT user_id,user_name from auth where user_name = ?");
                        statement = connect.prepareStatement(sql);
                        statement.setString(1, userName);
                        statement.execute();
                        sql =("UPDATE guide_info SET full_name = ?,service_place = ?,contact_no = ? where user_name = ?");
                        statement = connect.prepareStatement(sql);
                        statement.setString(1, nameField.getText());
                        statement.setString(2, serviceField.getText());
                        statement.setString(3, phoneField.getText());
                        statement.setString(4, userNameField.getText());
                        statement.execute();
                        StaticItemsClass.signup_status = true;
                        baseController.goToLoginPage(event);
                        System.out.println("Success");
                    }
                    catch(Exception e){
                        System.out.println("Something went worng please try again");
                    }
                    
                   
                        
                    
                }
            }
            catch(Exception e)
            {
                
            }     
        }

    }
}
