/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.guide_panel;

import controllers.StaticItemsClass;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author Habib
 */
public class ProfileController implements Initializable{
    
    
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
    
    public String user_name = controllers.guide_panel.StaticClass.guide_user_name;
   
    public void showProfileDetails()
    {
        try{
            String sql = ("SELECT * FROM `guide_info` where user_name = ?");
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
                addressLabel.setText(result.getString("service_place"));
            }
        }
        catch(SQLException e){e.printStackTrace();}
    }
    
    GuideBaseController guideBaseController = new GuideBaseController();
    public void profileIconClick(ActionEvent event) throws IOException{
        guideBaseController.profileIconClick(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       showProfileDetails();
    }
    Preferences prefs = Preferences.userRoot().node("/user/custom/root");
    public void logOutBtnAction(ActionEvent event) throws IOException{
        try {
            prefs.clear();
            
        } catch (BackingStoreException ex) {
            Logger.getLogger(controllers.ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        guideBaseController.goToLogInPage(event);
    }
}
