package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.Event;
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

import javax.xml.crypto.Data;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
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
        Employee employee = new Employee(Username);
        databaseHandler.deleteEmployee(employee);

    }

    private boolean confirmation(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // ... user chose OK
            System.out.println("yes");
            return true;
        } else {
            // ... user chose CANCEL or closed the dialog
            System.out.println("cancle");
            return false;
        }
    }
}
