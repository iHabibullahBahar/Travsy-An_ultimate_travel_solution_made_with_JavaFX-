/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;
import utils.emergencysos.ContactSearchModel;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author Habib
 */
public class EmergencySOSController implements Initializable{
    
    //Database tools
    private Connection connect;
    private PreparedStatement statement;
    private ResultSet result;
    
    @FXML
     private TableView <ContactSearchModel> sosListTableview;
    
    @FXML
    private TableColumn<ContactSearchModel,String>contactNoColumn;
    
    @FXML
    private TableColumn<ContactSearchModel,String>serviceNameColumn;
    
    @FXML
    private TableColumn<ContactSearchModel,String>placeOfServiceColumn;
    
    @FXML
    private TableColumn<ContactSearchModel,String>countryNameColumn;
    
    @FXML
    private TextField keywordsField;
    
    ObservableList <ContactSearchModel> contactSearchModelObservableList = FXCollections.observableArrayList();
    
    
    
    
    
    
    
    
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
        System.out.println("This is sos");
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
        connect = StaticItemsClass.connectDB();
        String sql = ("SELECT contact_no,service_name,place_of_service,country_name from emergency_sos");
        try{
            statement = connect.prepareStatement(sql);
            result = statement.executeQuery(); 
            while(result.next())
            {
                //contactSearchModelObservableList.add(new ContactSearchModel(result.getString("contact_no")));
                
                String contactNo = result.getString("contact_no");
                String serviceName = result.getString("service_name");
                String placeOfService = result.getString("place_of_service");
                String country = result.getString("country_name");
                //System.out.println(contactNo+" "+serviceName+" "+placeOfService+" "+country);
                contactSearchModelObservableList.add(new ContactSearchModel(contactNo,serviceName,placeOfService,country));
                //contactSearchModelObservableList.add(new ContactSearchModel())
            }
            contactNoColumn.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
            serviceNameColumn.setCellValueFactory(new PropertyValueFactory<>("ServiceName"));
            placeOfServiceColumn.setCellValueFactory(new PropertyValueFactory<>("place"));
            countryNameColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
            
            sosListTableview.setItems(contactSearchModelObservableList);
            FilteredList<ContactSearchModel> filteredData = new FilteredList<>(contactSearchModelObservableList, b-> true);
            
            keywordsField.textProperty().addListener((observable,oldValue,newValue)->{
            filteredData.setPredicate(ContactSearchModel ->{
                if(newValue.isEmpty()||newValue.isBlank()||newValue == null){
                    return true;
                }
                String searchKeywords = newValue.toLowerCase();
                if(ContactSearchModel.getContactNo().toLowerCase().indexOf(searchKeywords)> -1){
                    return true;
                }
                else if(ContactSearchModel.getServiceName().toLowerCase().indexOf(searchKeywords)> -1){
                    return true;
                }
                else if(ContactSearchModel.getPlace().toLowerCase().indexOf(searchKeywords)> -1){
                    return true;
                }
                else if(ContactSearchModel.getCountry().toLowerCase().indexOf(searchKeywords)> -1){
                    return true;
                }
                else
                    return false;
            });
        });
            SortedList<ContactSearchModel>sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(sosListTableview.comparatorProperty());
            sosListTableview.setItems(sortedData);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
