package sample.controller;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.Database.DatabaseHandler;
import sample.model.Employee;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
    private ImageView deleteBack;

    @FXML
    private JFXDatePicker addEmployeeCDate;

    LocalDate CDate;

    @FXML
    void selectDate(ActionEvent event) {
        CDate = addEmployeeCDate.getValue();
        System.out.println(CDate);
    }


    @FXML
    void initialize() {


        addEmployeeButton.setOnAction(actionEvent -> {
            createEmployee();
        });
        deleteBack.setOnMouseClicked((mouseEvent -> {
            deleteBack.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/main.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }));
    }


    private void createEmployee() {
        DatabaseHandler databaseHandler = new DatabaseHandler();

        String Name = addEmployeeName.getText();
        String LastName = addEmployeeLastName.getText();
        String Username = addEmployeeUsername.getText();
        int Level = 100000;
        try {
            Level = Integer.parseInt(addEmployeeLevel.getText());
        } catch (java.lang.NumberFormatException e) {
            Frame parent = new JFrame();
            JOptionPane.showMessageDialog(parent, "Please enter a number in the level field");
        }

        String Password = addEmployeePassword.getText();
        String Work = "";

        if (addApprover.isSelected()) {
            Work = "Approver";
        } else if (addOperator.isSelected()) {
            Work = "Operator";
        } else {
            Work = "Rater";
        }

        Employee employee = new Employee(Name, LastName, Username, Level, Password, Work,CDate);
        databaseHandler.addEmployee(employee);
    }
}
