package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Animations.Shaker;
import sample.Database.DatabaseHandler;
import sample.model.Admin;
import sample.model.Employee;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
    private JFXComboBox<?> comboBox;

    private static String comboboxvalue;


    @FXML
    void initialize() {

        try {
            DatabaseHandler.connect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        ObservableList userKind = FXCollections.observableArrayList("Admin","Employee");
        comboBox.setItems(userKind);
        comboBox.getSelectionModel().selectFirst();


        loginButton.setOnAction(event -> {
            if(comboBox.getValue() == "Employee"){
                String loginText = loginUsername.getText();
                String loginPwd = loginPassword.getText();

                Employee employee = new Employee();
                employee.setUsername(loginText);
                employee.setPassword(loginPwd);

                ResultSet employeeRow = DatabaseHandler.getEmployee(employee);
                int counter = 0;
                try {
                    while(employeeRow.next()) {
                        counter++;
                        employee.setCdate(employeeRow.getDate("CertificateDate").toLocalDate());

                    }
                    if (counter == 1 ) {
                        if (employee.getCdate().compareTo(LocalDate.now()) != -1){
                            setComboboxvalue("Employee");
                            showMainScreen();
                        }else{
                            Frame parent = new JFrame();
                            JOptionPane.showMessageDialog(parent, "Your Certificate date has been expired");
                        }
                    }else{
                        Shaker UsernameShaker = new Shaker(loginUsername);
                        UsernameShaker.shake();
                        Shaker PasswordShaker = new Shaker(loginPassword);
                        PasswordShaker.shake();
                    }
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }else if(comboBox.getValue()== "Admin"){
                String loginText = loginUsername.getText();
                String loginPwd = loginPassword.getText();

                Admin admin = new Admin();
                admin.setUsername(loginText);
                admin.setPassword(loginPwd);

                ResultSet adminRow = DatabaseHandler.getAdmin(admin);
                int counter = 0;
                try {
                    while(adminRow.next()) {
                        counter++;
                    }
                    if (counter == 1) {
                        setComboboxvalue("Admin");
                        showMainScreen();
                    }else{
                        Shaker UsernameShaker = new Shaker(loginUsername);
                        UsernameShaker.shake();
                        Shaker PasswordShaker = new Shaker(loginPassword);
                        PasswordShaker.shake();

                    }

                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }

        });
    }


    private void showMainScreen(){
        loginButton.getScene().getWindow().hide();
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
    }


    public static String getComboboxvalue() {
        return comboboxvalue;
    }

    public static void setComboboxvalue(String comboboxvalue) {
        LoginController.comboboxvalue = comboboxvalue;
    }


}