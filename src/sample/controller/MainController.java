package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton mainCreateReport;

    @FXML
    private JFXButton mainEditProfile;

    @FXML
    private JFXButton mainDeleteAccount;

    @FXML
    private JFXButton mainAddEmployee;

    @FXML
    private JFXButton mainLogout;

    @FXML
    void initialize() {
        mainAddEmployee.setOnAction(actionEvent -> {
            mainAddEmployee.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/addemployee.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        });


        mainDeleteAccount.setOnAction(actionEvent -> {
            showDeleteScreen();
        });


        //UPDATE `my_database`.`employee` SET `Name` = 'Name', `Username` = 'bilal' WHERE (`id` = '11117') and (`Username` = 'Username');

    }

    private void showDeleteScreen(){
        mainDeleteAccount.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/deleteemployee.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
