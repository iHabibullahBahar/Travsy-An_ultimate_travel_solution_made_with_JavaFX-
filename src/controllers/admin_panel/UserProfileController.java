/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.admin_panel;

import controllers.StaticItemsClass;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author Habib
 */
public class UserProfileController implements Initializable{
    
        //Database Tools
    private Connection connect = StaticClass.connect;
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
    
    @FXML
    private Button blockBtn;
    
    @FXML
    private Label statusLabel;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showProfileDetails();
    }
    //private int user_id = StaticItemsClass.user_id;
    
    public String user_name = StaticClass.currentUserName;
    public String status ;
    public void showProfileDetails()
    {
        System.out.println(user_name);
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
                status = result.getString("activity_status");
                statusLabel.setText(status);
                
            }
            
            if (status.equals("Blocked")) {
                   blockBtn.setText("Unblock User");
                   StaticClass.userBlockStatus = true;
               }
            else if(status.equals("Active"))
            {
                blockBtn.setText("Block User");
                StaticClass.userBlockStatus = false;
            }
            
            
            
        }
        catch(SQLException e){}
    }

    @FXML
    public void blockGuide(){
        
        
        if(StaticClass.userBlockStatus)
        {
            
            String sql = "UPDATE `user_info` SET `activity_status` = 'Active' WHERE user_id = ?";
            try {
            
            statement = connect.prepareStatement(sql);
            System.out.println(StaticClass.currentUserId);
            statement.setString(1, String.valueOf(StaticClass.currentUserId));
            statement.execute();
            
            } catch (Exception e) {
                
            }
        }
        else{
            String sql = "UPDATE `user_info` SET `activity_status` = 'Blocked' WHERE user_id = ?";
            try {
            
            statement = connect.prepareStatement(sql);
            statement.setString(1, String.valueOf(StaticClass.currentUserId));
            statement.execute();
            } catch (Exception e) {
                
            }
        }
        showProfileDetails();
    }
    
    
    AdminBaseController baseController = new AdminBaseController();
    public void goToLoginPage(ActionEvent event) throws IOException{
        baseController.goToLoginPage(event);
    }
    public void goToHomePage(ActionEvent event) throws IOException{
        baseController.goToHomePage(event);
    }
    public void goToUsersPage(ActionEvent event) throws IOException{
        baseController.goToUsersPage(event);
    }
    public void goToPackagesPage(ActionEvent event) throws IOException{
        baseController.goToPackagesPage(event);
    }
    public void goToGuidesPage(ActionEvent event) throws IOException{
        baseController.goToGuidesPage(event);
    }
    public void profileIconClick(ActionEvent event) throws IOException{
        baseController.profileIconClick(event);
    }
}
