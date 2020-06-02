package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.Database.DatabaseHandler;
import sample.model.Company;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddCompanyController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField addCompanyName;

    @FXML
    private JFXTextField addCompanyPlace;

    @FXML
    private JFXTextField addCompanyOfferNo;

    @FXML
    private JFXTextField addCompanyJobOrderNo;

    @FXML
    private JFXButton addCompanyButton;

    @FXML
    private ImageView deleteBack;

    @FXML
    private JFXButton AddJobOrderNo;

    @FXML
    private JFXButton AddOfferNo;

    @FXML
    void createJobOrderNo(ActionEvent event) {
        String Name = addCompanyName.getText();
        String JobOrderNo = addCompanyJobOrderNo.getText();
        if (Name.equals("")){
            Frame parent = new JFrame();
            JOptionPane.showMessageDialog(parent, "Please Entre the Company Name");
        }else {
            Company company = new Company(Name,JobOrderNo);
            databaseHandler.addJobOrderNo(company);

        }
    }

    @FXML
    void createOfferNo(ActionEvent event) {
        String Name = addCompanyName.getText();
        String OfferNo = addCompanyOfferNo.getText();
        System.out.println(Name);
        if (Name.equals("")){
            Frame parent = new JFrame();
            JOptionPane.showMessageDialog(parent, "Please Entre the Company Name");
        }else {
            Company company = new Company(Name,OfferNo,true);
            databaseHandler.addOfferNo(company);
        }
    }

    DatabaseHandler databaseHandler = new DatabaseHandler();

    void initialize() {

        addCompanyButton.setOnAction(actionEvent -> {
            createCompany();
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


    private void createCompany(){
        String Name = addCompanyName.getText();
        String Place = addCompanyPlace.getText();
        Company company = new Company(Name,Place);
        databaseHandler.addCompany(company);
    }
}
