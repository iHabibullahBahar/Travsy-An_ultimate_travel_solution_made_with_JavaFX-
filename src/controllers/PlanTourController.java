/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import models.PlanModel;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 *
 * @author Habib
 */
public class PlanTourController implements Initializable{

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadPlanData();
    }
    private Parent root;
    private Scene scene;
    private Stage stage;
    
    
    //Database Tools
    private Connection connect = null;
    private PreparedStatement statement = null;
    private ResultSet result = null ;
    String query = null;
    
    PlanModel planModel = null;
    
    @FXML 
    Button DoneBtn;
    @FXML
    private TableView<PlanModel> planViewTable;
//    @FXML
//    private TableColumn<PlanModel, Integer> userIdColumn;
//    @FXML
//    private TableColumn<PlanModel, Integer> planIdColumn;
    @FXML
    private TableColumn<PlanModel, String> descriptionColumn;
    @FXML
    private TableColumn<PlanModel, Integer> importanceColumn;
    @FXML
    private TableColumn<PlanModel, Integer> processColumn;
    @FXML
    private TableColumn<PlanModel, String> dueDateColumn;
    @FXML
    private TableColumn<PlanModel, String> actionColumn;
    
    
    ObservableList<PlanModel>  planList = FXCollections.observableArrayList();
    
    public void addPlanBtnAction(ActionEvent event) throws IOException{
        if(StaticItemsClass.logInStatus == false){
            baseController.forbiddenAction(event,"You can not add a plan without login");
        }
        else{
            root = FXMLLoader.load(getClass().getResource("../views/addplan.fxml"));
            stage = new Stage();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show(); 
        }   
        
    }
    
    
    private void refreshTable() {
        try {
            planList.clear();

            String query = "SELECT * FROM `plan_a_tour`";
            int id = StaticItemsClass.user_id;

            
                
                
            //statement.setString(1, String.valueOf(id));
            statement = connect.prepareStatement(query);
            result = statement.executeQuery();
            if(StaticItemsClass.logInStatus == false){
                id = 2;
            }
            while (result.next()){

                if(result.getInt("user_id")!=id){continue;}       
                int userId  = result.getInt("user_id");
                int planId = result.getInt("plan_id");
                String planDescription = result.getString("plan_description");
                int planImportance = result.getInt("plan_importance");
                int process =result.getInt("process");
                LocalDate due_date = convertToLocalDateViaMilisecond(result.getDate("due_date"));
                planList.add(new  PlanModel(userId,planId,planDescription,planImportance,process,due_date));
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
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("planDescription"));
        importanceColumn.setCellValueFactory(new PropertyValueFactory<>("importance"));
        processColumn.setCellValueFactory(new PropertyValueFactory<>("process"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("due_date"));
        
        //add cell of button edit 
         Callback<TableColumn<PlanModel, String>, TableCell<PlanModel, String>> cellFoctory = (TableColumn<PlanModel, String> param) -> {
            // make cell containing buttons
            final TableCell<PlanModel, String> cell = new TableCell<PlanModel, String>() {
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
                        final Button viewIcon = new Button("VIEW");
                        
                        editIcon.setStyle("-fx-background-color:#102948;"
                        +"-fx-text-fill:#ffffff;");
                        deleteIcon.setStyle("-fx-background-color:#102948;"
                        +"-fx-text-fill:#ffffff;");
                        viewIcon.setStyle("-fx-background-color:#102948;"
                        +"-fx-text-fill:#ffffff;");
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            try {
                                planModel = getTableView().getItems().get(getIndex());
                                query = "DELETE FROM `plan_a_tour` WHERE plan_id  ="+planModel.getPlanId();
                                statement = connect.prepareStatement(query);
                                statement.execute();
                                refreshTable();
                            } catch (SQLException ex) {
                                Logger.getLogger(PlanTourController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            planModel = getTableView().getItems().get(getIndex());
                            AddPlanController.planId = planModel.getPlanId();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("../views/addplan.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(PlanTourController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            AddPlanController addPlanController = loader.getController();
                            addPlanController.setUpdate(true);
                            addPlanController.setTextField(planModel.getPlanDescription(), planModel.getImportance(), planModel.getProcess(), planModel.getDue_date());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                        });
                        viewIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            planModel = getTableView().getItems().get(getIndex());
                            AddPlanController.planId = planModel.getPlanId();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("../views/viewplan.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(PlanTourController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            AddPlanController addPlanController = loader.getController();
                            addPlanController.setUpdate(true);
                            addPlanController.setTextField(planModel.getPlanDescription(), planModel.getImportance(), planModel.getProcess(), planModel.getDue_date());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                            

                        });
                        

                        HBox managebtn = new HBox(viewIcon,editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
                        HBox.setMargin(viewIcon, new Insets(2, 3, 0, 2));
                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };

        actionColumn.setCellFactory(cellFoctory);
        planViewTable.setItems(planList);
    }


            
    //BUTTON ACTION 
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
