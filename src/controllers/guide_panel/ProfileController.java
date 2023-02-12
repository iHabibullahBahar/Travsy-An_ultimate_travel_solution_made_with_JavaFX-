/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers.guide_panel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

/**
 *
 * @author Habib
 */
public class ProfileController implements Initializable{
    
    GuideBaseController guideBaseController = new GuideBaseController();
    
    public void profileIconClick(ActionEvent event) throws IOException{
        guideBaseController.profileIconClick(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }
}
