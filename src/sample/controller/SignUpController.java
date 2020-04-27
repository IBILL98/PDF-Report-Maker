package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import sample.Database.DatabaseHandler;
import sample.model.Admin;
import sample.model.Employee;

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
    void initialize() {
        signUpButton.setOnAction(actionEvent -> {
            createAdmin();
        });
    }

    private void createAdmin(){
        DatabaseHandler databaseHandler = new DatabaseHandler();

        String Name = signUpName.getText();
        String LastName = signUpLastName.getText();
        String Username = signUpUsername.getText();
        String Password = signUpPassword.getText();

        Admin admin = new Admin(Name,LastName,Username,Password);
        databaseHandler.addAdmin(admin);
    }
}
