package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.Animations.Shaker;
import sample.Database.DatabaseHandler;
import sample.model.Employee;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class DeleteEmplyeeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton deleteEmployeeBottun;

    @FXML
    private JFXTextField deleteEmployeeUsername;

    @FXML
    private ImageView deleteBack;

    @FXML
    void initialize() {
        deleteEmployeeBottun.setOnAction(actionEvent -> {
            if(confirmation()){
                deleteEmployee();
            }
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




    private void deleteEmployee(){
        DatabaseHandler databaseHandler = new DatabaseHandler();

        String Username = deleteEmployeeUsername.getText();
        Employee employee = new Employee();
        employee.setUsername(Username);
        ResultSet employeeRow = databaseHandler.getEmployeeByUsername(employee);
        int counter = 0;
        try {
            while(employeeRow.next()) {
                counter++;
                databaseHandler.deleteEmployee(employee);
            }
            if (counter == 0) {
                Shaker UsernameShaker = new Shaker(deleteEmployeeUsername);
                UsernameShaker.shake();
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }

    }

    private boolean confirmation(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // ... user choose OK
            System.out.println("yes");
            return true;
        } else {
            // ... user choose CANCEL or closed the dialog
            System.out.println("cancle");
            return false;
        }
    }
}
