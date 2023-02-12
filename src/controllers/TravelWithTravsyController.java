/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.PackageModel;

/**
 *
 * @author Habib
 */
public class TravelWithTravsyController implements Initializable{

    //Database Tools
    private Connection connect = null;
    private PreparedStatement statement = null;
    private ResultSet result = null ;
    String query = null;
    PackageModel packageModel = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadPlanData();
     }
    private Parent root;
    private Stage stage;
    private Scene scene;
    
    @FXML
    private TextField keywordsField;
    
    @FXML
    private TableView<PackageModel> packageTableView;

    @FXML
    private TableColumn<PackageModel, String> actionColumn;

    @FXML
    private TableColumn<PackageModel, String> placeColumn;
    
    @FXML
    private TableColumn<PackageModel, String> routeColumn;

    @FXML
    private TableColumn<PackageModel, String> stayColumn;

    @FXML
    private TableColumn<PackageModel, Integer> priceColumn;

    @FXML
    private TableColumn<PackageModel, LocalDate> dateColumn;
    

    ObservableList<PackageModel>  packageList = FXCollections.observableArrayList();
    
    private void refreshTable() {
        
        try {
            packageList.clear();

            String query = "SELECT * FROM `package_of_tour`";
            statement = connect.prepareStatement(query);
            result = statement.executeQuery();
            while (result.next()){
        
                int packageId  = result.getInt("package_id");
                int price = result.getInt("price");
                String place = result.getString("place");
                String route = result.getString("route");
                String stay = result.getString("stay");
                LocalDate date = convertToLocalDateViaMilisecond(result.getDate("date"));
                packageList.add(new  PackageModel(packageId,place,route,stay,price,date));
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(PlanTourController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    //Date to localDate Converter
    public LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
    return Instant.ofEpochMilli(dateToConvert.getTime())
      .atZone(ZoneId.systemDefault())
      .toLocalDate();
    }
    private void loadPlanData(){
        connect = StaticItemsClass.connectDB();
        refreshTable();
        placeColumn.setCellValueFactory(new PropertyValueFactory<>("palce"));
        routeColumn.setCellValueFactory(new PropertyValueFactory<>("route"));
        stayColumn.setCellValueFactory(new PropertyValueFactory<>("stay"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
      
        
        //add cell of button edit 
         Callback<TableColumn<PackageModel, String>, TableCell<PackageModel, String>> cellFoctory = (TableColumn<PackageModel, String> param) -> {
            // make cell containing buttons
            final TableCell<PackageModel, String> cell = new TableCell<PackageModel, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                        final Button editIcon = new Button("View");
                        editIcon.setStyle("-fx-background-color:#102948;"
                        +"-fx-text-fill:#ffffff;");
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            packageModel = getTableView().getItems().get(getIndex());
                            StaticItemsClass.current_packageId = packageModel.getPackageId();
                            System.out.println(StaticItemsClass.current_packageId);
                            try {
                                root = FXMLLoader.load(getClass().getResource("../views/package_details.fxml"));
                                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                                scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();            
                            } catch (IOException ex) {
                                System.out.println("Cant load fxml");
                            }
                        });
                        HBox managebtn = new HBox(editIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        
        actionColumn.setCellFactory(cellFoctory);
        packageTableView.setItems(packageList);
        
        
        FilteredList<PackageModel> filteredData = new FilteredList<>(packageList, b-> true);
            keywordsField.textProperty().addListener((observable,oldValue,newValue)->{
            filteredData.setPredicate(PackageModel ->{
                if(newValue.isEmpty()||newValue.isBlank()||newValue == null){
                    return true;
                }
                String searchKeywords = newValue.toLowerCase();
                if(PackageModel.getPalce().toLowerCase().indexOf(searchKeywords)> -1){
                    return true;
                }
                else if(PackageModel.getRoute().toLowerCase().indexOf(searchKeywords)> -1){
                    return true;
                }
                else if(PackageModel.getStay().toLowerCase().indexOf(searchKeywords)> -1){
                    return true;
                }
//                else if(PackageModel.getDate().indexOf(searchKeywords)> -1){
//                    return true;
//                }
                else
                    return false;
            });
        });
            SortedList<PackageModel>sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(packageTableView.comparatorProperty());
            packageTableView.setItems(sortedData);
    }
        
    
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
    
    
      
}
