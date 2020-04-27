package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import sample.Database.DatabaseHandler;
import sample.model.Employee;

import java.net.URL;
import java.util.ResourceBundle;

public class AddEmployeeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField addEmployeeName;

    @FXML
    private JFXTextField addEmployeeLastName;

    @FXML
    private JFXTextField addEmployeeUsername;

    @FXML
    private JFXTextField addEmployeeLevel;

    @FXML
    private JFXButton addEmployeeButton;

    @FXML
    private JFXTextField addEmployeePassword;

    @FXML
    private JFXRadioButton addOperator;

    @FXML
    private ToggleGroup Group1;

    @FXML
    private JFXRadioButton addRater;

    @FXML
    private JFXRadioButton addApprover;

    @FXML
    void initialize() {
        addEmployeeButton.setOnAction(actionEvent -> {
            createEmployee();
        });
    }


    private void createEmployee(){
        DatabaseHandler databaseHandler = new DatabaseHandler();

        String Name = addEmployeeName.getText();
        String LastName = addEmployeeLastName.getText();
        String Username = addEmployeeUsername.getText();
        String Level = addEmployeeLevel.getText();
        String Password = addEmployeePassword.getText();
        String Work = "";

        if(addApprover.isSelected()) {
            Work = "Approver";
        }else if(addOperator.isSelected()){
            Work = "Operator";
        }else{
            Work = "Rater";
        }
        Employee employee = new Employee(Name,LastName,Username,Level,Password,Work);
        databaseHandler.addEmployee(employee);
    }
}
