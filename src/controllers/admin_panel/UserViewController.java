/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.admin_panel;


import controllers.PlanTourController;
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
import models.UserModel;

/**
 *
 * @author Habib
 */
public class UserViewController implements Initializable{
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadUserData();
    }
    //Database Tools
    private Connection connect = StaticClass.connect;
    private PreparedStatement statement = null;
    private ResultSet result = null ;
    String query = null;
    
    private Parent root;
    private Stage stage;
    private Scene scene;
    
    UserModel userModel = null;
    
    
    
    @FXML
    private TableView<UserModel> userInfoTableView;

    @FXML
    private TableColumn<UserModel, String> actionColumn;

    @FXML
    private TableColumn<UserModel, Integer> idColumn;
    
    @FXML
    private TableColumn<UserModel, String> userNameColumn;

    @FXML
    private TableColumn<UserModel, String> fullNameColumn;

    @FXML
    private TableColumn<UserModel, String> statusColumn;


    @FXML
    private TableColumn<UserModel, String> emailColumn;
    
//    @FXML
//    private TableColumn<PackageModel, LocalDate> dateColumn;
    
    
    ObservableList<UserModel>  userList = FXCollections.observableArrayList();
    
    public void refreshButton(){
        refreshTable();
    }
    
    
    private void refreshTable() {
        
        try {
            userList.clear();

            String query = "SELECT * FROM `user_info`";
            statement = connect.prepareStatement(query);
            result = statement.executeQuery();
            while (result.next()){
        
                int userId = result.getInt("user_id");
                String userName = result.getString("user_name");
                String fullName = result.getString("full_name");
                String status = result.getString("activity_status");
                String email = result.getString("email");
                //System.out.println(packageId + " "+place+" "+route+" "+stay+" "+price+" ");
                userList.add(new  UserModel(userId,userName,fullName,email,status));
                
                
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(PlanTourController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    private void loadUserData(){
        refreshTable();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("ActivityStatus"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        
        //add cell of button edit 
         Callback<TableColumn<UserModel, String>, TableCell<UserModel, String>> cellFoctory = (TableColumn<UserModel, String> param) -> {
            // make cell containing buttons
            final TableCell<UserModel, String> cell = new TableCell<UserModel, String>() {
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
                            
                            userModel = getTableView().getItems().get(getIndex());
                            StaticClass.currentUserId = userModel.getUserId();
                            StaticClass.currentUserName = userModel.getUserName();
                            try {
                                root = FXMLLoader.load(getClass().getResource("../../views/admin_views/user_profile_info.fxml"));
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
        userInfoTableView.setItems(userList);
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
