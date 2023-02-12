/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.admin_panel;


import controllers.BookAGuideController;
import controllers.PlanTourController;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.GuideModel;
import controllers.StaticItemsClass;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TextField;

/**
 *
 * @author Habib
 */
public class GuideViewController implements Initializable{

    
        @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadPlanData();
    }
    //Database Tools
    private Connection connect = null;
    private PreparedStatement statement = null;
    private ResultSet result = null ;
    String query = null;
    GuideModel guideModel = null;
    
    private Parent root;
    private Stage stage;
    private Scene scene;
    
    @FXML
    private TextField keywordsField;
    
    @FXML
    private TableView<GuideModel> guideTableView;

    @FXML
    private TableColumn<GuideModel, String> actionColumn;

    @FXML
    private TableColumn<GuideModel, String> statusColumn;

//    @FXML
//    private TableColumn<GuideModel, Double> dailyRateColumn;
    
    @FXML
    private TableColumn<GuideModel, Integer> GuideIdColumn;

    @FXML
    private TableColumn<GuideModel, String> nameColumn;

    @FXML
    private TableColumn<GuideModel, String> placeOfServiceColumn;

    @FXML
    private TableColumn<GuideModel, Double> ratingColumn;

    ObservableList<GuideModel>  guideList = FXCollections.observableArrayList();
    
    private void refreshTable() {
        
        try {
            guideList.clear();
            String query = "SELECT * FROM `guide_info`";
            //statement.setString(1, String.valueOf(id));
            statement = connect.prepareStatement(query);
            result = statement.executeQuery();
            while (result.next()){
                int userId  = result.getInt("user_id");
                int guideId = result.getInt("guide_id");
                String name = result.getString("full_name");
                String placeOfService = result.getString("service_place");
                int hourlyRate = result.getInt("hourly_rate");
                int dailyRate = result.getInt("daily_rate");
                double rating = result.getDouble("rating");
                String status = result.getString("verified_status");
                guideList.add(new  GuideModel(userId,guideId,name,placeOfService,hourlyRate,dailyRate,rating,status));
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(PlanTourController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    private void loadPlanData(){
        connect = StaticItemsClass.connectDB();
        refreshTable();
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        placeOfServiceColumn.setCellValueFactory(new PropertyValueFactory<>("placeOfService"));
        GuideIdColumn.setCellValueFactory(new PropertyValueFactory<>("guideId"));
        //dailyRateColumn.setCellValueFactory(new PropertyValueFactory<>("dailyRate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        //add cell of button edit 
         Callback<TableColumn<GuideModel, String>, TableCell<GuideModel, String>> cellFoctory = (TableColumn<GuideModel, String> param) -> {
            // make cell containing buttons
            final TableCell<GuideModel, String> cell = new TableCell<GuideModel, String>() {
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
                            
                            guideModel = getTableView().getItems().get(getIndex());
                            StaticClass.current_guideId = guideModel.getGuideId();
                            try {
                                root = FXMLLoader.load(getClass().getResource("../../views/admin_views/guide_profile.fxml"));
                                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                                scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();            
                            } catch (IOException ex) {
                                Logger.getLogger(BookAGuideController.class.getName()).log(Level.SEVERE, null, ex);
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
        guideTableView.setItems(guideList);
        
        
        FilteredList<GuideModel> filteredData = new FilteredList<>(guideList, b-> true);
            keywordsField.textProperty().addListener((observable,oldValue,newValue)->{
            filteredData.setPredicate(GuideModel ->{
                if(newValue.isEmpty()||newValue.isBlank()||newValue == null){
                    return true;
                }
                String searchKeywords = newValue.toLowerCase();
                if(GuideModel.getName().toLowerCase().indexOf(searchKeywords)> -1){
                    return true;
                }
                else if(GuideModel.getPlaceOfService().toLowerCase().indexOf(searchKeywords)> -1){
                    return true;
                }
                else if(GuideModel.getStatus().toLowerCase().indexOf(searchKeywords)> -1){
                    return true;
                }
                else if(Integer.toString(GuideModel.getGuideId()).indexOf(searchKeywords)> -1){
                    return true;
                }
                else
                    return false;
            });
        });
            SortedList<GuideModel>sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(guideTableView.comparatorProperty());
            guideTableView.setItems(sortedData);
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
