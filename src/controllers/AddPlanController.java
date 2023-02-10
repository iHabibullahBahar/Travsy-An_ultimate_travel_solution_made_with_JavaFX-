/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.PlanModel;

/**
 * FXML Controller class
 *
 * @author hocin
 */
public class AddPlanController implements Initializable {
    @FXML
    private Button clearBtn;

    @FXML
    private Button closeBtn;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private DatePicker dueDatePicker;

    @FXML
    private TextField importanceTextField;

    @FXML
    private TextField processTextField;

    @FXML
    private Button saveBtn;


    String query = null;
    Connection connect = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    PlanModel planModel = null;
    private boolean update=false;
    int planID;
    
    public static int planId = 1;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void save(ActionEvent event) {

        connect = StaticItemsClass.connectDB();
        String description = descriptionTextArea.getText();
        String dueDate = String.valueOf(dueDatePicker.getValue());
        String importance  = importanceTextField.getText();
        String process = processTextField.getText();
        if (description.isEmpty()||dueDate.isEmpty()||importance.isEmpty()||process.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } else {
            getQuery();
            insert();
            clean();
        }
    }
    @FXML
    private void clean() {
        descriptionTextArea.setText(null);
        dueDatePicker.setValue(null);
        importanceTextField.setText(null);
        processTextField.setText(null);
    }

    private void getQuery() {

        if (update == false) {
            
            query = "INSERT INTO `plan_a_tour`( `user_id`, `plan_description`, `plan_importance`, `process`,`due_date`) VALUES (?,?,?,?,?)";

        }else{
            query = "UPDATE `plan_a_tour` SET "
                    + "`user_id`=?,"
                    + "`plan_description`=?,"
                    + "`plan_importance`=?,"
                    + "`process`=?,"
                    + "`due_date`= ? WHERE plan_id = '"+planId+"'";
        }
    }
    private void insert() {

        try {

            
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(StaticItemsClass.user_id));
            preparedStatement.setString(2, descriptionTextArea.getText());
            preparedStatement.setString(3, importanceTextField.getText());
            preparedStatement.setString(4, processTextField.getText());
            preparedStatement.setString(5, String.valueOf(dueDatePicker.getValue()));
            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AddPlanController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void setTextField(String description, int importance, int process,LocalDate dueDate) {
        descriptionTextArea.setText(description);
        importanceTextField.setText(Integer.toString(importance));
        processTextField.setText(Integer.toString(process));
        dueDatePicker.setValue(dueDate);

    }

    void setUpdate(boolean b) {
        this.update = b;

    }

}
