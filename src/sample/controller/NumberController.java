package sample.controller;

import com.jfoenix.controls.JFXButton;

import java.awt.*;
import java.io.IOException;
import java.net.URL;

import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
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
    private TableView<Company> JobOrderNumbersTable;

    @FXML
    private TableColumn<Company, String> jNameColumn;

    @FXML
    private TableColumn<Company, String> jPlaceColumn;

    @FXML
    private TableColumn<Company, String> jobOrderNumbersColumn;

    @FXML
    private TextField jSearchText;

    @FXML
    private JFXButton jAddButton;

    @FXML
    private JFXButton jEditButton;

    @FXML
    private JFXButton jDeleteButton;

    @FXML
    private TableView<Company> OfferNumbersTable;

    @FXML
    private TableColumn<Company, String> oNameColumn;

    @FXML
    private TableColumn<Company, String> oPlaceColumn;

    @FXML
    private TableColumn<Company, String> OfferNoColumn;

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

    @FXML
    private JFXButton OffersDone;

    @FXML
    private JFXButton JobsDone;


    @FXML
    void cancel(ActionEvent event) {
        jobOrderNumberAnchor.setVisible(false);
        OfferNumberAnchor.setVisible(false);
        jCName.clear();
        jNumber.clear();
    }

    @FXML
    void createJobOrderNo(ActionEvent event) {
        if (JobsDone.isVisible()){
            Frame parent = new JFrame();
            JOptionPane.showMessageDialog(parent, "Finish your editing first please");
        }else {
            jobOrderNumberAnchor.toFront();
            jobOrderNumberAnchor.setVisible(true);
        }

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
            DatabaseHandler.addJobOrderNo(company);
        }
        jCName.clear();
        jNumber.clear();
    }

    @FXML
    void createOfferNo(ActionEvent event) {
        if (OffersDone.isVisible()){
            Frame parent = new JFrame();
            JOptionPane.showMessageDialog(parent, "Finish your editing first please");
        }else {
            OfferNumberAnchor.toFront();
            OfferNumberAnchor.setVisible(true);
        }
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
            DatabaseHandler.addOfferNo(company);
        }
        oName.clear();
        oNumber.clear();
    }


    @FXML
    void oSearch(KeyEvent event) {
        if(!oSearchText.getText().equals("")){
            oNameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
            oPlaceColumn.setCellValueFactory(new PropertyValueFactory<>("Place"));
            OfferNoColumn.setCellValueFactory(new PropertyValueFactory<>("OfferNo"));
            OfferNumbersTable.setItems(DatabaseHandler.searchoffersList(oSearchText.getText()));
        }else {
            viewAllOffers();
        }
    }

    @FXML
    void jSearch(KeyEvent event) {
        if(!jSearchText.getText().equals("")){

            oNameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
            oPlaceColumn.setCellValueFactory(new PropertyValueFactory<>("Place"));
            OfferNoColumn.setCellValueFactory(new PropertyValueFactory<>("JobOrderNo"));
            OfferNumbersTable.setItems(DatabaseHandler.searchJobsList(jSearchText.getText()));
        }else {
            viewAllJobOrders();
        }
    }


    @FXML
    void initialize() {
        viewAllOffers();
        viewAllJobOrders();

        jDeleteButton.setOnAction(event -> {
            if (JobsDone.isVisible()){
                Frame parent = new JFrame();
                JOptionPane.showMessageDialog(parent, "Finish your editing first please");
            }else{
                deleteJoOrderNo();
            }

        });

        oDeleteButton.setOnAction(event -> {
            if (OffersDone.isVisible()){
                Frame parent = new JFrame();
                JOptionPane.showMessageDialog(parent, "Finish your editing first please");
            }else{
                deleteOfferNo();
            }
        });

        oEditButton.setOnAction(event -> {
            editOfferNo();
        });

        jEditButton.setOnAction(event -> {
            ediJobOrderNo();
        });

        OffersDone.setOnAction(event -> {
            OfferNumbersTable.getSelectionModel().cellSelectionEnabledProperty().set(false);
            OffersDone.setVisible(false);
            Frame parent = new JFrame();
            JOptionPane.showMessageDialog(parent, "Done");
        });

        JobsDone.setOnAction(event -> {
            JobOrderNumbersTable.getSelectionModel().cellSelectionEnabledProperty().set(false);
            JobsDone.setVisible(false);
            Frame parent = new JFrame();
            JOptionPane.showMessageDialog(parent, "Done");
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


    public void viewAllJobOrders() {
        jNameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        jPlaceColumn.setCellValueFactory(new PropertyValueFactory<>("Place"));
        jobOrderNumbersColumn.setCellValueFactory(new PropertyValueFactory<>("JobOrderNo"));
        JobOrderNumbersTable.setItems(DatabaseHandler.viewAllJobOrders());
    }


    public void viewAllOffers() {
        oNameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        oPlaceColumn.setCellValueFactory(new PropertyValueFactory<>("Place"));
        OfferNoColumn.setCellValueFactory(new PropertyValueFactory<>("OfferNo"));
        OfferNumbersTable.setItems(DatabaseHandler.viewAllOffers());
    }

    public void deleteJoOrderNo() {
        Company company = JobOrderNumbersTable.getSelectionModel().getSelectedItem();
        JobOrderNumbersTable.setEditable(true);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Are you sure you ?");
            alert.setContentText("You will delete the job order from the " + company.getName() + " company which is " + company.getJobOrderNo() + " From your Job Orders list");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                // ... user choose OK
                System.out.println("yes");
                DatabaseHandler.deleteJobNo(company);
                OfferNumbersTable.getItems().remove(DatabaseHandler.getJobIndex(company));
            } else {
                // ... user choose CANCEL or closed the dialog
                System.out.println("cancle");
            }
    }


    public void deleteOfferNo() {
        Company company = OfferNumbersTable.getSelectionModel().getSelectedItem();
        OfferNumbersTable.setEditable(true);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Are you sure you ?");
            alert.setContentText("You will delete the offer no from the " + company.getName() + " company which is " + company.getOfferNo() + " From your Offers list");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                System.out.println("yes");
                DatabaseHandler.deleteOfferNo(company);
                OfferNumbersTable.getItems().remove(DatabaseHandler.getOfferIndex(company));
            } else {
                System.out.println("cancle");
            }
    }

    void editOfferNo(){
        OffersDone.setVisible(true);
        OfferNumbersTable.setEditable(true);
        OfferNumbersTable.getSelectionModel().cellSelectionEnabledProperty().set(true);
        OfferNoColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        OfferNoColumn.setOnEditCommit(employeeStringCellEditEvent -> {
            Company company = OfferNumbersTable.getSelectionModel().getSelectedItem();
            String old = company.getOfferNo();
            company.setOfferNo(employeeStringCellEditEvent.getNewValue());
            DatabaseHandler.editOffer(company,old);
        });
    }

    void ediJobOrderNo(){
        JobsDone.setVisible(true);
        JobOrderNumbersTable.setEditable(true);
        JobOrderNumbersTable.getSelectionModel().cellSelectionEnabledProperty().set(true);
        jobOrderNumbersColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        jobOrderNumbersColumn.setOnEditCommit(employeeStringCellEditEvent -> {
            Company company = JobOrderNumbersTable.getSelectionModel().getSelectedItem();
            String old = company.getJobOrderNo();
            company.setJobOrderNo(employeeStringCellEditEvent.getNewValue());
            DatabaseHandler.editJob(company,old);

        });
    }
}
