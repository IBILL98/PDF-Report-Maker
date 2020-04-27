package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.Animations.Shaker;
import sample.Database.Const;
import sample.Database.DatabaseHandler;
import sample.model.Employee;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class EditEmployeeController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton viewEmployeeBottun;

    @FXML
    private JFXTextField editEmployeeUsername;

    private JPanel pane;

    private DatabaseHandler databaseHandler;
    @FXML
    private ImageView deleteBack;

    @FXML
    void initialize() {
        viewEmployeeBottun.setOnAction(actionEvent -> {
            searchEmployee();
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

    private void searchEmployee(){
        databaseHandler = new DatabaseHandler();
        String Username = editEmployeeUsername.getText();
        Employee employee = new Employee();
        employee.setUsername(Username);
        ResultSet employeeRow = databaseHandler.getEmployeeByUsername(employee);
        int counter = 0;
        try {
            while(employeeRow.next()) {
                counter++;
                databaseHandler.editEmployeeWindow(employeeRow);

            }
            if (counter == 0) {
                Shaker UsernameShaker = new Shaker(editEmployeeUsername);
                UsernameShaker.shake();
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

































   /* private void editEmployeeWindow(ResultSet employeeRow) throws SQLException {
        databaseHandler = new DatabaseHandler();


        pane = new JPanel();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Select your Info");
        alert.setHeaderText("Which Information would you like to edit");
        alert.setContentText("when you edit you cant have your old infos back");

        ButtonType buttonTypeOne = new ButtonType("Name");
        ButtonType buttonTypeTwo = new ButtonType("LastName");
        ButtonType buttonTypeThree = new ButtonType("Level");
        ButtonType buttonTypeFour = new ButtonType("Password");
        ButtonType buttonTypeFive = new ButtonType("Work");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree,buttonTypeFour,buttonTypeFive, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            // ... user chose "One
            databaseHandler.editEmployee(employeeRow,Const.ADMINS_NAME);
            pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
            pane.add(new JLabel("Your new name is : " + employeeRow.getString("Name")));

        } else if (result.get() == buttonTypeTwo) {
            // ... user chose "Two"
            databaseHandler.editEmployee(employeeRow,Const.EMPLOYEE_LASTNAME);
            pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
            pane.add(new JLabel("Your new LastName is : " + employeeRow.getString("LastName")));
        } else if (result.get() == buttonTypeThree) {
            // ... user chose "Three"
            databaseHandler.editEmployee(employeeRow,Const.EMPLOYEE_LEVEL);
            pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
            pane.add(new JLabel("Your new Level is : " + employeeRow.getString("Level")));

        } else if (result.get() == buttonTypeFour) {
            //
            databaseHandler.editEmployee(employeeRow,Const.EMPLOYEE_PASSWORD);
            pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
            pane.add(new JLabel("Your new Password is : " + employeeRow.getString("Password")));

        } else if (result.get() == buttonTypeFive) {
            databaseHandler.editEmployee(employeeRow, Const.EMPLOYEE_WORK);
            pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
            pane.add(new JLabel("Your new work is : " + employeeRow.getString("Work")));
        } else {
            // ... user chose CANCEL or closed the dialog
        }





    }*/
}
