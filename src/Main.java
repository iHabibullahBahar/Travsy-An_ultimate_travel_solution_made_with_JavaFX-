
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Habib
 */
public class Main extends Application{
    
    public static double x,y;
    public static Stage stage;
    public static Scene scene;
    public static Parent root;
    public static Main mainclass;
    
    static public void setScene() throws IOException
    {
        root.setOnMousePressed(event ->{
            x=event.getSceneX();
            y=event.getSceneY();
        });
        root.setOnMouseDragged(event ->{
            stage.setX(event.getScreenX()-x);
            stage.setY(event.getScreenY()-y);
        });
    }
    @Override
    public void start(Stage stage) throws Exception {
        
        
        
        root = FXMLLoader.load(getClass().getResource("views/home.fxml"));
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
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
