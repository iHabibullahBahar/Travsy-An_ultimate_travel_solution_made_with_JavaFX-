/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.admin_panel;

import java.awt.Desktop;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Habib
 */
public class EditPackageController implements Initializable{
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setData();
    }
    //Database Tools
    private Connection connect = StaticClass.connect;
    private PreparedStatement statement = null;
    private ResultSet result = null ;
    String query = null;
    
    
    @FXML
    private Button addBtn;

    @FXML
    private Button clearBtn;

    @FXML
    private DatePicker dateField;

    @FXML
    private TextArea descriptionfield;

    @FXML
    private TextField placeField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField routeField;

    @FXML
    private TextField stayField;
    
    @FXML
    private Label promtLabel ;       
    
    String place;
    String route;
    String stay;
    String price;
    String description;
    LocalDate date;
    
    @FXML
    public void clear()
    {
        try {
            
        } catch (Exception e) {
        }
    }
    public void getData()
    {
        query = "SELECT * FROM `package_of_tour` WHERE package_id = ?";
        try {
            
            statement = connect.prepareStatement(query);
            statement.setString(1, Integer.toString(StaticClass.current_packageId));
            result = statement.executeQuery();
            
            if(result.next())
            {
                
                place = result.getString("place");
                route =result.getString("route");
                date = convertToLocalDateViaMilisecond(result.getDate("date"));
                stay = result.getString("stay");
                price = Integer.toString(result.getInt("price"));
                description = result.getString("description");
            }
        } catch (Exception e) {
        }
    }
    public void setData()
    {
        getData();
        placeField.setText(place);
        routeField.setText(route);
        stayField.setText(stay);
        priceField.setText(price);
        descriptionfield.setText(description);
        dateField.setValue(date);
    }
    //Date to localDate Converter
    public LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
    return Instant.ofEpochMilli(dateToConvert.getTime())
      .atZone(ZoneId.systemDefault())
      .toLocalDate();
    }
    
    
    @FXML
    public void addPackage()
    {
        try {
            place = placeField.getText();
            route = routeField.getText();
            stay = stayField.getText();
            price =priceField.getText();
            description = descriptionfield.getText();
            date = dateField.getValue();
            if(place.isEmpty()||route.isEmpty()||stay.isEmpty()||price.isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please Fill All DATA");
                alert.showAndWait();
            }
            else{
                executeQuery();
            }
            
        } catch (Exception e) {
        }
    }
    public void executeQuery(){
        query = "UPDATE `package_of_tour` SET `place`= ?,`route`= ? ,`date`= ?,`stay`= ?,`price`= ?,`description`= ?  WHERE package_id = ?";
            try {
                statement = connect.prepareStatement(query);
                statement.setString(1, place);
                statement.setString(2, route);
                statement.setString(3, String.valueOf(date));
                statement.setString(4, stay);
                statement.setString(5, price);
                
                statement.setString(6, description);
                statement.setString(7, Integer.toString(StaticClass.current_packageId));
                statement.execute();
                promtLabel.setText("Successfully Edited.");
                
            } catch (Exception e) {
                System.out.println("Something went wrong.");
            }
    }
    public void clean(){
        placeField.setText(null);
        dateField.setValue(null);
        routeField.setText(null);
        priceField.setText(null);
        stayField.setText(null);
        descriptionfield.setText(null);
    }
}
