
package controllers;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 *
 * @author Habib
 */
public class BaseController{
    private Parent root;
    private Stage stage;
    private Scene scene;
    
   
    public void goToHomePage(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../views/home.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
    public void goToLoginPage(ActionEvent event) throws IOException{
        
        root = FXMLLoader.load(getClass().getResource("../views/login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void goToRegisterPage(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../views/signup.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
    @FXML
    public void goToGuideRegisterPage(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../views/guidesignup.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
    public void goToPlanATourPage(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../views/planatour.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();  
    }
    public void goToCheckWeatherPage(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../views/checkweather.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
    public void goToExchangeRatePage(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../views/convertcurrency.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
    public void goToEmergencySOSPage(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../views/sos.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
    public void goToBookAnythingPage(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../views/bookanything.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
    public void goToTravelWithTravsyPage(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../views/travelwithtravsy.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
    public void goToCopyTravelWithTravsyPage(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../views/travelwithtravsy1.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
    public void goToSettingsPage(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../views/setting.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
    public void goToSupportPage(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../views/support.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
    public void goToAboutPage(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../views/about.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
    
    public static void showDetails()
    {
        //System.out.println(StaticItemsClass.name);
    }
    public void profileIconClick(ActionEvent event) throws IOException{
       if(StaticItemsClass.logInStatus == true){
        root = FXMLLoader.load(getClass().getResource("../views/profile.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();  
       }
       else{
           this.goToLoginPage(event);
       }
    }  
    public void goToAdminPage(ActionEvent event)throws IOException{
        root = FXMLLoader.load(getClass().getResource("../views/admin_views/home_view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void goToGuidePage(ActionEvent event)throws IOException{
        root = FXMLLoader.load(getClass().getResource("../views/guide_views/home.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void goToExplorePage(ActionEvent event)throws IOException{
        root = FXMLLoader.load(getClass().getResource("../views/explore.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
        public void forbiddenAction(ActionEvent event,String header) throws IOException{
		Alert alert = new Alert(Alert.AlertType.NONE);
                alert.getDialogPane().getButtonTypes().add(ButtonType.YES);
                alert.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
		alert.setTitle("Login Now");
		alert.setHeaderText(header);
		alert.setContentText("Do you want to Login Now?");
                
		if (alert.showAndWait().get() == ButtonType.YES){
                    this.goToLoginPage(event);
		}
                else if (alert.showAndWait().get() == ButtonType.CANCEL){
                        alert.close();
		}
    }
    
}
