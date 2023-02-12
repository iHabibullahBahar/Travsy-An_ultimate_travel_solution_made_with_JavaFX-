/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.management.relation.Role;
import controllers.admin_panel.StaticClass;
import java.util.prefs.Preferences;
/**
 * FXML Controller class
 *
 * @author Habib
 */
public class LoginController implements Initializable {

    private Parent root;
    private Scene scene;
    private Stage stage;
    
    //Database tools
    private Connection connect;
    private PreparedStatement statement;
    private ResultSet result;
    @FXML
    private PasswordField passwordField;

    @FXML
    private Label warningLabel;
    @FXML
    private TextField userNameTextField;
    
    String role;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(StaticItemsClass.signup_status == true)
        {
            warningLabel.setText("Successfully signed up");
            StaticItemsClass.signup_status = false;
        }
        connect = StaticItemsClass.connectDB();
    }
    
    Preferences prefs = Preferences.userRoot().node("/user/custom/root");
    //reference of BaseController Class
    BaseController baseController = new BaseController();
    
    //This Task Will performe when log in button will be clicked
    public void loginBtnAction(ActionEvent event) {
        warningLabel.setText("");
        //connect = connectDB();
        try{
            String sql = ("SELECT * FROM auth WHERE user_name = ? AND password = ?");
            statement = connect.prepareStatement(sql);
            statement.setString(1, userNameTextField.getText());
            statement.setString(2, passwordField.getText());
            result = statement.executeQuery(); 
            if(result.next())
            {
                String userName = result.getString("user_name");
                int userId = result.getInt("user_id");
                StaticItemsClass.user_name = userName;
                StaticItemsClass.user_id = userId;
                
                role = result.getString("role");
                prefs.put("username", userName);
                prefs.putInt("userid", userId);
                prefs.putBoolean("logInStatus", true);
                prefs.put("role", role);
                roleChecker(event);
            }
            else
            {
                warningLabel.setText("Wrong user name or Password");
            }
        }
        catch(Exception e){e.printStackTrace();}
    }
    public void roleChecker(ActionEvent event) throws IOException{
        if(role.equals("user")){
            
            baseController.profileIconClick(event);
        }
        else if(role.equals("admin"))
        {
            baseController.goToAdminPage(event);
        }
        else if(role.equals("guide"))
        {
            baseController.goToGuidePage(event);
        }
            
    }
    
        

    @FXML
    private void goToRegisterPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../views/signup.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
    }
    
    @FXML
    private void goToResetPasswordPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../views/resetpassword.fxml"));
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
