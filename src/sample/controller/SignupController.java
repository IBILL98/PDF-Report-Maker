package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import sample.Database.DatabaseHandler;
import sample.model.Employee;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class SignupController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField signUpName;

    @FXML
    private JFXTextField signUpLastName;

    @FXML
    private JFXTextField signUpUsername;

    @FXML
    private JFXTextField signUpLevel;

    @FXML
    private JFXButton signUpButton;

    @FXML
    private JFXTextField signUpPassword;

    @FXML
    private JFXRadioButton signUpOperator;

    @FXML
    private ToggleGroup Group1;

    @FXML
    private JFXRadioButton signUpRater;

    @FXML
    private JFXRadioButton signUpApprover;

    @FXML
    void initialize() {
        signUpButton.setOnAction(actionEvent -> {
            createEmployee();
        });
    }






    private void createEmployee(){
        DatabaseHandler databaseHandler = new DatabaseHandler();

        String Name = signUpName.getText();
        String LastName = signUpLastName.getText();
        String Username = signUpUsername.getText();
        String Level = signUpLevel.getText();
        String Password = signUpPassword.getText();
        String Work = "";

        if(signUpApprover.isSelected()) {
            Work = "Approver";
        }else if(signUpOperator.isSelected()){
            Work = "Operator";
        }else{
            Work = "Rater";
        }
        Employee employee = new Employee(Name,LastName,Username,Level,Password,Work);
        databaseHandler.signUpEmployee(employee);
    }
}
