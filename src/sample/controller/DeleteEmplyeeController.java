package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import sample.Animations.Shaker;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class DeleteEmplyeeController {

    //DELETE FROM `my_database`.`employee` WHERE (`id` = '11120') and (`Username` = 'Username1');


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
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Look, a Confirmation Dialog");
            alert.setContentText("Are you ok with this?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                // ... user chose OK
                System.out.println("yes");
            } else {
                // ... user chose CANCEL or closed the dialog
                System.out.println("cancle");
            }
        });



    }
}
