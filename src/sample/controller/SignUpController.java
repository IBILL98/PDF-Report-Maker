package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.Database.DatabaseHandler;
import sample.model.Admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController {


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
    private JFXTextField signUpPassword;

    @FXML
    private JFXButton signUpButton;

    @FXML
    private ImageView deleteBack;

    @FXML
    void initialize() {
        signUpButton.setOnAction(event -> {
            createAdmin();
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

    private void createAdmin(){

        String Name = signUpName.getText();
        String LastName = signUpLastName.getText();
        String Username = signUpUsername.getText();
        String Password = signUpPassword.getText();

        Admin admin = new Admin(Name,LastName,Username,Password);
        DatabaseHandler.addAdmin(admin);
    }
}
