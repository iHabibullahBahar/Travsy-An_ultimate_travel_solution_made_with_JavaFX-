/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import controllers.admin_panel.StaticClass;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Habib
 */
public class EditProfileInfoController implements Initializable{

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getData();
        setData();
    }
    String userName = StaticItemsClass.user_name;
    
    //Database Tools
    private Connection connect = StaticItemsClass.connect;
    private PreparedStatement statement = null;
    private ResultSet result = null ;
    String query = null;
    
    
    String name,contactNo,email,adress;
    LocalDate dateOfBirth;
    
    @FXML
    private TextField addressField;

    @FXML
    private DatePicker dateField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneField;
    
    @FXML 
    private Label promtLabel;
    
    public void getData()
    {
        query = "SELECT * FROM `user_info` WHERE user_name = ?";
        try {
            
            statement = connect.prepareStatement(query);
            statement.setString(1, userName);
            result = statement.executeQuery();
            
            if(result.next())
            {
                
                name = result.getString("full_name");
                contactNo =result.getString("contact_no");
                dateOfBirth = convertToLocalDateViaMilisecond(result.getDate("date_of_birth"));
                email = result.getString("email");
                adress = result.getString("address");
            }
            } 
        catch (Exception e) {
              e.printStackTrace();
        }
    }
    public void setData(){
        nameField.setText(name);
        emailField.setText(email);
        addressField.setText(adress);
        phoneField.setText(contactNo);
        dateField.setValue(dateOfBirth);
    }
            
    //Date to localDate Converter
    public LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
    return Instant.ofEpochMilli(dateToConvert.getTime())
      .atZone(ZoneId.systemDefault())
      .toLocalDate();
    }
    public void editProfile(){
        query ="UPDATE `user_info` SET full_name = ?,email = ?, contact_no = ? ,address = ? WHERE user_name =?";
        try {
                statement = connect.prepareStatement(query);
                statement.setString(1, nameField.getText());
                statement.setString(2, emailField.getText());
                statement.setString(3, phoneField.getText());
                statement.setString(4,addressField.getText() );
                //statement.setString(5, String.valueOf(dateField.getValue()));
                statement.setString(5, userName);
                statement.execute();
                promtLabel.setText("Profile info edit successfully.");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
