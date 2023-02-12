/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.guide_panel;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.GuideOrderModel;
import models.GuideViewModel;

/**
 *
 * @author Habib
 */
public class HomeController implements Initializable{
    
    Preferences prefs = Preferences.userRoot().node("/user/custom/root");
    //Database Tools
    private Connection connect ;
    private PreparedStatement statement = null;
    private ResultSet result = null ;
    String query = null;
    String userName ;
    GuideViewModel guideViewModel =null;
    
    
    
    
    @FXML
    private TableView<GuideViewModel> gOrderTableView;

    @FXML
    private TableColumn<GuideViewModel, Integer> orderIdColumn;
    
    @FXML
    private TableColumn<GuideViewModel, Integer> userNameColumn;
    
    @FXML
    private TableColumn<GuideViewModel, Integer> contactNoColumn;
    
    @FXML
    private TableColumn<GuideViewModel, String> emailColumn;
  
    @FXML
    private TableColumn<GuideViewModel, LocalDate> orderDateColumn;
    
    ObservableList<GuideViewModel>  guideOrderList = FXCollections.observableArrayList();
    
    
    private void refreshTable() {
        try {
            guideOrderList.clear();
            connect = StaticClass.connect;
            query = "SELECT guide_order.guide_order_id,user_info.user_name,user_info.contact_no,user_info.email,guide_order.order_date " +
                    " FROM guide_order " +
                    " INNER JOIN user_info ON guide_order.user_id = user_info.user_id " +
                    " where guide_order.guides_user_name = ?";
            statement = connect.prepareStatement(query);
            statement.setString(1, userName);
            result = statement.executeQuery();
            while (result.next()){
                
                int orderId = result.getInt("guide_order.guide_order_id");
                String userName = result.getString("user_info.user_name");
                String contactNo = result.getString("user_info.contact_no");
                String email = result.getString("user_info.email");
                LocalDate date = convertToLocalDateViaMilisecond(result.getDate("guide_order.order_date"));
                
                guideOrderList.add(new  GuideViewModel(orderId,userName,contactNo,email,date));
            }
        } 
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Cant refresh");
        }
        
    }
    private void loadGuideOrderdata(){
        refreshTable();
         try {
            orderIdColumn.setCellValueFactory(new  PropertyValueFactory<>("orderId"));
            userNameColumn.setCellValueFactory(new  PropertyValueFactory<>("userName"));
            contactNoColumn.setCellValueFactory(new  PropertyValueFactory<>("contactNo"));
            emailColumn.setCellValueFactory(new  PropertyValueFactory<>("email"));
            orderDateColumn.setCellValueFactory(new  PropertyValueFactory<>("date"));
            gOrderTableView.setItems(guideOrderList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    //Date to localDate Converter
    public LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
    return Instant.ofEpochMilli(dateToConvert.getTime())
      .atZone(ZoneId.systemDefault())
      .toLocalDate();
    }
    GuideBaseController guideBaseController = new GuideBaseController();
    public void profileIconClick(ActionEvent event) throws IOException{
        guideBaseController.profileIconClick(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       StaticClass.connect = StaticClass.connectDB();
       StaticClass.guide_user_name = prefs.get("username", "none");
       userName =  StaticClass.guide_user_name;
       loadGuideOrderdata();
       //Database Tools
       
    
    }
    
    
}
