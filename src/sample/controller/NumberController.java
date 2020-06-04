package sample.controller;

import com.jfoenix.controls.JFXButton;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Database.DatabaseHandler;
import sample.model.Company;

import javax.swing.*;

public class NumberController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane basicAnchor;

    @FXML
    private TableView<?> JobOrderNumbersTable;

    @FXML
    private TableColumn<?, ?> jNameColumn;

    @FXML
    private TableColumn<?, ?> jPlaceColumn;

    @FXML
    private TableColumn<?, ?> jobOrderNumbersColumn;

    @FXML
    private TextField jSearchText;

    @FXML
    private JFXButton jAddButton;

    @FXML
    private JFXButton jEditButton;

    @FXML
    private JFXButton jDeleteButton;

    @FXML
    private TableView<?> OfferNumbersTable;

    @FXML
    private TableColumn<?, ?> oNameColumn;

    @FXML
    private TableColumn<?, ?> oPlaceColumn;

    @FXML
    private TableColumn<?, ?> OfferNoColumn;

    @FXML
    private TextField oSearchText;

    @FXML
    private JFXButton oAddButton;

    @FXML
    private JFXButton oEditButton;

    @FXML
    private JFXButton oDeleteButton;

    @FXML
    private ImageView deleteBack;

    @FXML
    private AnchorPane jobOrderNumberAnchor;

    @FXML
    private TextField jCName;

    @FXML
    private TextField jNumber;

    @FXML
    private JFXButton jAdd;

    @FXML
    private JFXButton cancel;

    @FXML
    private AnchorPane OfferNumberAnchor;

    @FXML
    private TextField oName;

    @FXML
    private TextField oNumber;

    @FXML
    private JFXButton oAdd;

    @FXML
    private JFXButton cancel1;

    DatabaseHandler databaseHandler = new DatabaseHandler();

    @FXML
    void cancel(ActionEvent event) {
        jobOrderNumberAnchor.setVisible(false);
        OfferNumberAnchor.setVisible(false);
    }

    @FXML
    void createJobOrderNo(ActionEvent event) {
        jobOrderNumberAnchor.toFront();
        jobOrderNumberAnchor.setVisible(true);
    }

    @FXML
    void jAddJobOrderNo(ActionEvent event) {
        String Name = jCName.getText();
        String JobOrderNo = jNumber.getText();
        if (Name.equals("")){
            Frame parent = new JFrame();
            JOptionPane.showMessageDialog(parent, "Please Entre the Company Name");
        }else {
            Company company = new Company(Name,JobOrderNo,true,true);
            databaseHandler.addJobOrderNo(company);
        }
        jCName.clear();
        jNumber.clear();
    }

    @FXML
    void createOfferNo(ActionEvent event) {
        OfferNumberAnchor.toFront();
        OfferNumberAnchor.setVisible(true);
    }

    @FXML
    void oAddOfferNo(ActionEvent event) {
        String Name = oName.getText();
        String OfferNo = oNumber.getText();
        System.out.println(Name);
        if (Name.equals("")){
            Frame parent = new JFrame();
            JOptionPane.showMessageDialog(parent, "Please Entre the Company Name");
        }else {
            Company company = new Company(Name,OfferNo,true);
            databaseHandler.addOfferNo(company);
        }
        oName.clear();
        oNumber.clear();
    }

    @FXML
    void initialize() {
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
}
