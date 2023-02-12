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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.GuideOrderModel;
import models.PackageOrderModel;

/**
 *
 * @author Habib
 */
public class HomeController implements Initializable{

    Preferences prefs = Preferences.userRoot().node("/user/custom/root");
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            StaticClass.adminUserName = prefs.get("username", "root");
            StaticClass.connect = StaticClass.connectDB();
        } catch (Exception e) {
            System.out.println("Database Connection Failed");
        }
        loadUserData();
        loadGuideOrderdata();
    }
    //Database Tools
    private Connection connect ;
    private PreparedStatement statement = null;
    private ResultSet result = null ;
    String query = null;
    
    PackageOrderModel packageOrderModel =null;
    
    @FXML
    private TextField packKeywordsField;
    
    @FXML
    private TextField guideKeywordsField;
    
    
    
    @FXML
    private TableView<PackageOrderModel> orderTableView;

    @FXML
    private TableColumn<PackageOrderModel, Integer> orderIdColumn;
    
    @FXML
    private TableColumn<PackageOrderModel, Integer> packageIdColumn;
    
    @FXML
    private TableColumn<PackageOrderModel, Integer> userIdColumn;
    
    @FXML
    private TableColumn<PackageOrderModel, String> userNameColumn;
    
    @FXML
    private TableColumn<PackageOrderModel, LocalDate> orderTimeColumn;
    
    ObservableList<PackageOrderModel>  packageOrderList = FXCollections.observableArrayList();
    
    
    
   
    ObservableList<GuideOrderModel>  guideOrderList = FXCollections.observableArrayList();
    
    private void refreshTable() {
        try {
            packageOrderList.clear();
            
            connect = StaticClass.connect;
            query = "SELECT * FROM `package_order`";
            statement = connect.prepareStatement(query);
            
            result = statement.executeQuery();
            while (result.next()){
        
                int pkgOrderId = result.getInt("package_order_id");
                int pkgId = result.getInt("package_id");
                int userId = result.getInt("user_id");
                String userName = result.getString("user_name");
                LocalDate orderDate = convertToLocalDateViaMilisecond(result.getDate("order_date"));
               
                //System.out.println(packageId + " "+place+" "+route+" "+stay+" "+price+" ");
                packageOrderList.add(new  PackageOrderModel(pkgOrderId,pkgId,userId,userName,orderDate));
            }
        } 
        catch (Exception ex) {
            System.out.println("Cant refresh");
        }
        
    }
    
     @FXML
    private TableView<GuideOrderModel> gOrderTableView;

    @FXML
    private TableColumn<GuideOrderModel, Integer> gOrderIdColumn;
    
    @FXML
    private TableColumn<GuideOrderModel, Integer> guideIdColumn;
    
    @FXML
    private TableColumn<GuideOrderModel, Integer> gUserIdColumn;
    
    @FXML
    private TableColumn<GuideOrderModel, String> guideNameColumn;
    
    @FXML
    private TableColumn<GuideOrderModel, String> gUserNameColumn;
    
    @FXML
    private TableColumn<GuideOrderModel, LocalDate> gOrderTimeColumn;
 
    private void loadUserData(){
        refreshTable();
        try {
            orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("pkgOrderId"));
            packageIdColumn.setCellValueFactory(new PropertyValueFactory<>("pkgId"));
            userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
            userNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
            orderTimeColumn.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
            orderTableView.setItems(packageOrderList);
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }
    private void refreshGuideOrderTable(){
        try {
            guideOrderList.clear();
            query = "SELECT * FROM `guide_order`";
            statement = connect.prepareStatement(query);
            result = statement.executeQuery();
            
            while (result.next()){
        
                
                int orderId = result.getInt("guide_order_id");
                int guideId = result.getInt("guide_id");
                int userId = result.getInt("user_id");
                String userName = result.getString("users_user_name");
                String guserName =result.getString("guides_user_name");
                LocalDate orderDate = convertToLocalDateViaMilisecond(result.getDate("order_date"));
                guideOrderList.add(new  GuideOrderModel(orderId,guideId,guserName,userId,userName,orderDate));
                
            }
        } 
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
            
    private void loadGuideOrderdata(){
        refreshGuideOrderTable();
         try {
            gOrderIdColumn.setCellValueFactory(new  PropertyValueFactory<>("orderId"));
            guideIdColumn.setCellValueFactory(new  PropertyValueFactory<>("guideId"));
            guideNameColumn.setCellValueFactory(new  PropertyValueFactory<>("guideName"));
            gUserIdColumn.setCellValueFactory(new  PropertyValueFactory<>("userId"));
            
            gUserNameColumn.setCellValueFactory(new  PropertyValueFactory<>("userName"));
            gOrderTimeColumn.setCellValueFactory(new  PropertyValueFactory<>("date"));
            
            
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
