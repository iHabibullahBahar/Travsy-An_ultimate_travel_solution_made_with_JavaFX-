
package controllers;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import models.GuideModel;

/**
 *
 * @author Habib
 */
public class BookAGuideController implements Initializable{
    
    
    
    
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
    private TableView<GuideModel> guideTableView;

    @FXML
    private TableColumn<GuideModel, String> actionColumn;

    @FXML
    private TableColumn<GuideModel, String> customColumn;

    @FXML
    private TableColumn<GuideModel, Double> dailyRateColumn;
    
    @FXML
    private TableColumn<GuideModel, Double> hourlyRateColumn;

    @FXML
    private TableColumn<GuideModel, String> nameColumn;

    @FXML
    private TableColumn<GuideModel, String> placeOfServiceColumn;

    @FXML
    private TableColumn<GuideModel, Double> ratingColumn;

    ObservableList<GuideModel>  guideList = FXCollections.observableArrayList();
    
    String status ;
    String verifiedStatus;
    String guideUserName;
    private void refreshTable() {
        
        try {
            guideList.clear();

            String query = "SELECT * FROM `guide_info`";

            
            //statement.setString(1, String.valueOf(id));
            statement = connect.prepareStatement(query);
            result = statement.executeQuery();
            System.out.println("Hiii");
            while (result.next()){
        
                int userId  = result.getInt("user_id");
                int guideId = result.getInt("guide_id");
                String name = result.getString("full_name");
                String placeOfService = result.getString("service_place");
                int hourlyRate = result.getInt("hourly_rate");
                int dailyRate = result.getInt("daily_rate");
                double rating = result.getDouble("rating");
                status = result.getString("activity_status");
                verifiedStatus = result.getString("verified_status");
                guideUserName = result.getString("user_name");
                guideList.add(new  GuideModel(userId,guideId,name,placeOfService,hourlyRate,dailyRate,rating,status.toUpperCase(),guideUserName));
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
        hourlyRateColumn.setCellValueFactory(new PropertyValueFactory<>("hourlyRate"));
        dailyRateColumn.setCellValueFactory(new PropertyValueFactory<>("dailyRate"));
        customColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
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
                            StaticItemsClass.current_guideId = guideModel.getGuideId();
                            StaticItemsClass.current_guideUserName =guideModel.getUserName();
                            System.out.println(StaticItemsClass.current_guideId);
                            try {
                                root = FXMLLoader.load(getClass().getResource("../views/guide_profile_view_from_user.fxml"));
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
        baseController.goToCheckWeatherPage(event);
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
    
}
