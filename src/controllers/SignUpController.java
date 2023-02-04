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
public class SignUpController implements Initializable{

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
    private TextField emailField;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;


    @FXML
    private Button signUpBtn;
    
    @FXML
    private Label warningLabel;

    @FXML
    private TextField userNameField;

    BaseController baseController = new BaseController();
    //"What is your pet name?","What is your childhood nick name?"
    //private String[] questions = {"What is your pet name?","What is your childhood nick name?"};
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
    public void signUpBtnAction(ActionEvent event) throws SQLException
    {
        warningLabel.setText("");
        boolean notUniqeName = false,notUniqueEmail =false;
        
        if(nameField.getText().length()<3){
            warningLabel.setText("Name at least 3 characters in length");
        }
        else if(userNameField.getText().length()<4){
            warningLabel.setText("User name at least 4 characters in length");
        }
        else if(emailField.getText().length()<3){
            warningLabel.setText("Enter A valid Email");
        }
        else if((passwordField.getText().length()<6 )){
            warningLabel.setText("Password at least 6 characters in length");
        }
        else{
            try{
                String sql = ("SELECT * FROM user_info WHERE user_name = ?");
                statement = connect.prepareStatement(sql);
                statement.setString(1, userNameField.getText());
                result = statement.executeQuery(); 
                if(result.next())
                {
                    notUniqeName =true;
                }
                String sql1 = ("SELECT * FROM user_info WHERE email = ?");
                statement = connect.prepareStatement(sql1);
                statement.setString(1, emailField.getText());
                result = statement.executeQuery();
                if(result.next())
                {
                    notUniqueEmail= true;
                }
                //Setting data to warning Label
                if(notUniqeName && notUniqueEmail)
                {
                    warningLabel.setText("Email and username already exists");
                }
                else if(notUniqeName)
                {
                    warningLabel.setText("Username already exists");
                }
                else if(notUniqueEmail){
                    warningLabel.setText("Email already exists");
                }
                else
                {
                    try{
                        sql = ("INSERT INTO auth (user_name,password) values(?,?)");
                        statement = connect.prepareStatement(sql);
                        statement.setString(1, userNameField.getText());
                        statement.setString(2, passwordField.getText());
                        statement.execute();
                        sql = ("INSERT INTO user_info (user_id,user_name) SELECT auth.user_id,auth.user_name from auth where user_name = ?");
                        statement = connect.prepareStatement(sql);
                        statement.setString(1, userNameField.getText());
                        statement.execute();
                        sql =("UPDATE user_info SET full_name = ?,email=? where user_name = ?");
                        statement = connect.prepareStatement(sql);
                        statement.setString(1, nameField.getText());
                        statement.setString(2, emailField.getText());
                        statement.setString(3, userNameField.getText());
                        statement.execute();
                        StaticItemsClass.signup_status = true;
                        baseController.goToLoginPage(event);
                        
                    }
                    catch(Exception e){
                        System.out.println("Something went worng please try again");
                    }
                    
                        
                        
                    System.out.println("Success");
                }
            }
            catch(Exception e)
            {
                
            }     
        }

    }
}
