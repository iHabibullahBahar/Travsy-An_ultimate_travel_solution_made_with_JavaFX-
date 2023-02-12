/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.admin_panel;


import controllers.PlanTourController;
import controllers.StaticItemsClass;
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
public class PackageViewController  implements Initializable{

    //Database Tools
    private Connection connect = StaticClass.connect;
    private PreparedStatement statement = null;
    private ResultSet result = null ;
    String query = null;
    PackageModel packageModel = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshTable();
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
    private TableColumn<PackageModel, Integer> pkgIdLabel;
    
    @FXML
    private TableColumn<PackageModel, LocalDate> dateColumn;
    
    
    ObservableList<PackageModel>  packageList = FXCollections.observableArrayList();
    
    public void refreshButton(){
        refreshTable();
    }
    
    
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
                //System.out.println(packageId + " "+place+" "+route+" "+stay+" "+price+" ");
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
        refreshTable();
        placeColumn.setCellValueFactory(new PropertyValueFactory<>("palce"));
        routeColumn.setCellValueFactory(new PropertyValueFactory<>("route"));
        stayColumn.setCellValueFactory(new PropertyValueFactory<>("stay"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));
        pkgIdLabel.setCellValueFactory(new PropertyValueFactory<>("packageId"));
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
                        final Button editIcon = new Button("EDIT");
                        final Button deleteIcon = new Button("DELETE");
                        
                        editIcon.setStyle("-fx-background-color:#102948;"
                        +"-fx-text-fill:#ffffff;");
                        deleteIcon.setStyle("-fx-background-color:#102948;"
                        +"-fx-text-fill:#ffffff;");
                        
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            packageModel = getTableView().getItems().get(getIndex());
                            StaticClass.current_packageId = packageModel.getPackageId();
                            try {
                                root = FXMLLoader.load(getClass().getResource("../../views/admin_views/edit_package.fxml"));
                                stage  = new Stage();
                                scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();           
                            } catch (IOException ex) {
                                System.out.println("Cant load fxml");
                            }
                        });
                        HBox managebtn = new HBox(editIcon,deleteIcon);
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
                else if(Integer.toString(PackageModel.getPackageId()).indexOf(searchKeywords)> -1){
                    return true;
                }
                else
                    return false;
            });
        });
            SortedList<PackageModel>sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(packageTableView.comparatorProperty());
            packageTableView.setItems(sortedData);
    }
    
    public void addNewPackage(ActionEvent event)throws IOException{
        root = FXMLLoader.load(getClass().getResource("../../views/admin_views/add_package.fxml"));
        stage  = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
