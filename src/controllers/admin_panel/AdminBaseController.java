/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.admin_panel;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author Habib
 */
public class AdminBaseController {
    private Parent root;
    private Stage stage;
    private Scene scene;
    
    public void goToLoginPage(ActionEvent event) throws IOException{
        
        root = FXMLLoader.load(getClass().getResource("../../views/login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void goToHomePage(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../../views/admin_views/home_view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
    
    public void goToUsersPage(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../../views/admin_views/user_view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
    public void goToPackagesPage(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../../views/admin_views/package_view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
    public void goToGuidesPage(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../../views/admin_views/guide_view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
    public void profileIconClick(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../../views/profile.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();  
    }
    
}
