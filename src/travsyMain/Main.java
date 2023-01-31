package travsyMain;


import controllers.CheckWeatherController;
import controllers.CurrencyConverterController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
    
    CheckWeatherController chkWCon = new CheckWeatherController();
    CurrencyConverterController curConvrtCon =  new CurrencyConverterController();
//    public void setScene(String fxml,Button btn) throws IOException
//    {
//            root = FXMLLoader.load(getClass().getResource(fxml));
//            stage = (Stage)btn.getScene().getWindow();
//            scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//    }
    @Override
    public void start(Stage stage) throws Exception {
        
        
        
        root = FXMLLoader.load(getClass().getResource("../views/home.fxml"));
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
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        
        try{
            System.out.println("Hello");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }
    public static void main(String[] args) {
        launch(args);
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("initialize");
        //chkWCon.getWeatherReport();
        curConvrtCon.convertCurrency();
    }

}
