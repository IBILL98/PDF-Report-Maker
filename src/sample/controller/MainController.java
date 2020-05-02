package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.mysql.cj.log.Log;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.model.Employee;

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
    private JFXButton mainAddEmployee;

    @FXML
    private JFXButton mainLogout;

    @FXML
    private JFXButton mainSignupButton;


    @FXML
    void initialize() {
        if(LoginController.getComboboxvalue()== "Employee"){
            mainSignupButton.setVisible(false);
            mainAddEmployee.setVisible(false);
            mainEditProfile.setVisible(false);
        }
            mainSignupButton.setOnAction(actionEvent -> {
            mainSignupButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/signup.fxml"));
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


        mainEditProfile.setOnAction(actionEvent -> {
            showEditScreen();

        });
        mainLogout.setOnAction(actionEvent -> {
            mainLogout.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/login.fxml"));

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



    }

    private void showEditScreen() {
        mainEditProfile.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/employees.fxml"));

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
