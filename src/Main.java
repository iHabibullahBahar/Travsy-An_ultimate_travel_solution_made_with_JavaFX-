
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
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
    double x,y;
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("views/dashboard.fxml"));
        Scene scene =new Scene(root);
        root.setOnMousePressed(event ->{
            x=event.getSceneX();
            y=event.getSceneY();
        });
        root.setOnMouseDragged(event ->{
            stage.setX(event.getScreenX()-x);
            stage.setY(event.getScreenY()-y);
        });
        stage.setScene(scene);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
