package travsyMain;


import controllers.CheckWeatherController;
import controllers.CurrencyConverterController;
import controllers.StaticItemsClass;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Habib
 */
public class Main extends Application implements Initializable{
    
    public static double x,y;
    public static Stage stage;
    public static Scene scene;
    public static Parent root;
    public static Main mainClass;
    @FXML
    public Button btn;
    
    Preferences prefs = Preferences.userRoot().node("/user/custom/root");
    
    
    @Override
    public void start(Stage stage) throws Exception {
        if(prefs.getBoolean("logInStatus", false)){
            String role = prefs.get("role", "none");
            if(role.equals("admin")){
                root = FXMLLoader.load(getClass().getResource("../views/admin_views/home_view.fxml"));
            }
            else if(role.equals("user")){
                root = FXMLLoader.load(getClass().getResource("../views/home.fxml"));
            }
            else if(role.equals("guide")){
                root = FXMLLoader.load(getClass().getResource("../views/guide_views/home.fxml"));
            }
             
        }
        else
        {
            root = FXMLLoader.load(getClass().getResource("../views/home.fxml"));
        }
        //root = FXMLLoader.load(getClass().getResource("../views/guide_views/home.fxml"));
        //root = FXMLLoader.load(getClass().getResource("../views/admin_views/home_view.fxml"));
        //root = FXMLLoader.load(getClass().getResource("../views/login.fxml"));
        scene =new Scene(root);
        root.setOnMousePressed(event ->{
            x=event.getSceneX();
            y=event.getSceneY();
        });
        root.setOnMouseDragged(event ->{
            stage.setX(event.getScreenX()-x);
            stage.setY(event.getScreenY()-y);
        });
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Travsy - Make Your Travel Easy");
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

}
