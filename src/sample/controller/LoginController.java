package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Database.DatabaseHandler;
import sample.model.Employee;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField loginUsername;

    @FXML
    private JFXPasswordField loginPassword;

    @FXML
    private JFXButton loginButton;

    @FXML
    private JFXButton loginSignupButton;

    private DatabaseHandler databaseHandler;
    @FXML
    void initialize() {
        databaseHandler = new DatabaseHandler();


        loginSignupButton.setOnAction(actionEvent -> {
            loginSignupButton.getScene().getWindow().hide();
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




        loginButton.setOnAction(actionEvent -> {
            String loginText = loginUsername.getText();
            String loginPwd = loginPassword.getText();

            Employee employee = new Employee();
            employee.setUsername(loginText);
            employee.setPassword(loginPwd);

            ResultSet employeeRow = databaseHandler.getEmployee(employee);
            int counter = 0;
            try {
                while(employeeRow.next()) {
                    counter++;
                }
                if (counter == 1) {
                    System.out.println("login succefful !!!!");
                }

            }
            catch(SQLException e){
                e.printStackTrace();
            }
        });
    }
}
