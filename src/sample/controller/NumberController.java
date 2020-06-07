package sample.controller;

import com.jfoenix.controls.JFXButton;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.util.converter.IntegerStringConverter;
import sample.Database.Const;
import sample.Database.DatabaseHandler;
import sample.model.Company;
import sample.model.Employee;

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

    DatabaseHandler databaseHandler = new DatabaseHandler();

    @FXML
    void cancel(ActionEvent event) {
        jobOrderNumberAnchor.setVisible(false);
        OfferNumberAnchor.setVisible(false);
        jCName.clear();
        jNumber.clear();
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
    void oSearch(KeyEvent event) {
        if(!oSearchText.getText().equals("")){
            offers.clear();
            ResultSet resultSet = null;
            String query = "SELECT companies.Name,companies.Place,offerno.Id FROM companies,offerno WHERE (companies.name = offerno.CompanyIdO) AND companies.name = ?";

            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = databaseHandler.getDbConnection().prepareStatement(query);
                preparedStatement.setString(1, oSearchText.getText());

                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Company company = new Company();
                    company.setName(resultSet.getString("Name"));
                    company.setPlace(resultSet.getString("Place"));
                    company.setOfferNo(resultSet.getString("Id"));
                    offers.add(company);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            oNameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
            oPlaceColumn.setCellValueFactory(new PropertyValueFactory<>("Place"));
            OfferNoColumn.setCellValueFactory(new PropertyValueFactory<>("OfferNo"));
            OfferNumbersTable.setItems(offers);
        }else {
            viewAllOffers();
        }
    }

    @FXML
    void jSearch(KeyEvent event) {
        if(!jSearchText.getText().equals("")){
            joborders.clear();
            ResultSet resultSet = null;
            String query =  "SELECT  companies.Name,companies.Place,joborderno.Id FROM companies,joborderno WHERE (companies.Name = joborderno.CompanyIdJ) AND (companies.Name = ?)";

            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = databaseHandler.getDbConnection().prepareStatement(query);
                preparedStatement.setString(1, jSearchText.getText());

                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Company company = new Company();
                    company.setName(resultSet.getString("Name"));
                    company.setPlace(resultSet.getString("Place"));
                    company.setJobOrderNo(resultSet.getString("Id"));
                    joborders.add(company);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            oNameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
            oPlaceColumn.setCellValueFactory(new PropertyValueFactory<>("Place"));
            OfferNoColumn.setCellValueFactory(new PropertyValueFactory<>("JobOrderNo"));
            OfferNumbersTable.setItems(joborders);
        }else {
            viewAllJobOrders();
        }
    }


    private ObservableList<Company> joborders = FXCollections.observableArrayList();
    private ObservableList<Company> offers = FXCollections.observableArrayList();



    @FXML
    void initialize() {
        viewAllOffers();
        viewAllJobOrders();

        jDeleteButton.setOnAction(event -> {
            deleteJoOrderNo();
        });

        oDeleteButton.setOnAction(event -> {
            deleteOfferNo();
        });

        oEditButton.setOnAction(event -> {
            editOfferNo();
        });

        jEditButton.setOnAction(event -> {
            ediJobOrderNo();
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
        joborders.clear();
        ResultSet resultSet = null;
        String query = "SELECT  companies.Name,companies.Place,joborderno.Id FROM companies,joborderno WHERE companies.name = joborderno.CompanyIdJ";
        //String query = "SELECT "+Const.COMPANY_NAME +","+Const.COMPANY_PLACE+","+Const.JOBORDERNO_NO +" FROM " + Const.COMPANYS_TABLE+","+Const.JOBORDERS_TABLE +" WHERE " + Const.COMPANY_NAME + " = " + Const.COMPANY_NAMEJ;

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = databaseHandler.getDbConnection().prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Company company = new Company();
                company.setName(resultSet.getString("Name"));
                company.setPlace(resultSet.getString("Place"));
                company.setJobOrderNo(resultSet.getString("Id"));
                joborders.add(company);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        jNameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        jPlaceColumn.setCellValueFactory(new PropertyValueFactory<>("Place"));
        jobOrderNumbersColumn.setCellValueFactory(new PropertyValueFactory<>("JobOrderNo"));
        JobOrderNumbersTable.setItems(joborders);
    }



    public void viewAllOffers() {
        offers.clear();
        ResultSet resultSet = null;
        String query = "SELECT companies.Name,companies.Place,offerno.Id FROM companies,offerno where companies.name = offerno.CompanyIdO";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = databaseHandler.getDbConnection().prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Company company = new Company();
                company.setName(resultSet.getString("Name"));
                company.setPlace(resultSet.getString("Place"));
                company.setOfferNo(resultSet.getString("Id"));
                offers.add(company);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        oNameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        oPlaceColumn.setCellValueFactory(new PropertyValueFactory<>("Place"));
        OfferNoColumn.setCellValueFactory(new PropertyValueFactory<>("OfferNo"));
        OfferNumbersTable.setItems(offers);
    }


    public void deleteJoOrderNo() {
        Company company = JobOrderNumbersTable.getSelectionModel().getSelectedItem();
        JobOrderNumbersTable.setEditable(true);
        //System.out.println(employee.getId());
        String delete = "DELETE FROM " + Const.JOBORDERS_TABLE + " WHERE " + "(" + Const.JOBORDERNO_NO + " =?" + ")";
        try {
            PreparedStatement preparedStatement = databaseHandler.getDbConnection().prepareStatement(delete);
            preparedStatement.setString(1, String.valueOf(company.getJobOrderNo()));

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Are you sure you ?");
            alert.setContentText("You will delete the job order from the " + company.getName() + " company which is " + company.getJobOrderNo() + " From your Job Orders list");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                // ... user choose OK
                System.out.println("yes");
                preparedStatement.executeUpdate();
                int index = joborders.indexOf(company);
                JobOrderNumbersTable.getItems().remove(index);
            } else {
                // ... user choose CANCEL or closed the dialog
                System.out.println("cancle");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void deleteOfferNo() {
        Company company = OfferNumbersTable.getSelectionModel().getSelectedItem();
        OfferNumbersTable.setEditable(true);
        //System.out.println(employee.getId());
        String delete = "DELETE FROM " + Const.OFFERNO_TABLE + " WHERE " + "(" + Const.JOBOFFERNO_NO + " =?" + ")";
        try {
            PreparedStatement preparedStatement = databaseHandler.getDbConnection().prepareStatement(delete);
            preparedStatement.setString(1, String.valueOf(company.getOfferNo()));

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Are you sure you ?");
            alert.setContentText("You will delete the offer no from the " + company.getName() + " company which is " + company.getOfferNo() + " From your Offers list");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                // ... user choose OK
                System.out.println("yes");
                preparedStatement.executeUpdate();
                int index = offers.indexOf(company);
                OfferNumbersTable.getItems().remove(index);
            } else {
                // ... user choose CANCEL or closed the dialog
                System.out.println("cancle");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    void editOfferNo(){
        OfferNumbersTable.setEditable(true);
        OfferNumbersTable.getSelectionModel().cellSelectionEnabledProperty().set(true);

        OfferNoColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        OfferNoColumn.setOnEditCommit(employeeStringCellEditEvent -> {
            Company company = OfferNumbersTable.getSelectionModel().getSelectedItem();
            String old = company.getOfferNo();
            company.setOfferNo(employeeStringCellEditEvent.getNewValue());
            String update = "UPDATE " + Const.OFFERNO_TABLE + " SET " + Const.JOBOFFERNO_NO + "=?" + " WHERE " + Const.JOBOFFERNO_NO + "=?";
            try {
                PreparedStatement preparedStatement = databaseHandler.getDbConnection().prepareStatement(update);
                preparedStatement.setString(1, company.getOfferNo());
                preparedStatement.setString(2, old);
                preparedStatement.executeUpdate();
                Frame parent = new JFrame();
                JOptionPane.showMessageDialog(parent, "Done");
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    void ediJobOrderNo(){
        JobOrderNumbersTable.setEditable(true);
        JobOrderNumbersTable.getSelectionModel().cellSelectionEnabledProperty().set(true);

        jobOrderNumbersColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        jobOrderNumbersColumn.setOnEditCommit(employeeStringCellEditEvent -> {
            Company company = JobOrderNumbersTable.getSelectionModel().getSelectedItem();
            String old = company.getJobOrderNo();
            company.setJobOrderNo(employeeStringCellEditEvent.getNewValue());
            String update = "UPDATE " + Const.JOBORDERS_TABLE + " SET " + Const.JOBORDERNO_NO + "=?" + " WHERE " + Const.JOBORDERNO_NO + "=?";
            try {
                PreparedStatement preparedStatement = databaseHandler.getDbConnection().prepareStatement(update);
                preparedStatement.setString(1, company.getJobOrderNo());
                preparedStatement.setString(2, old);
                preparedStatement.executeUpdate();
                Frame parent = new JFrame();
                JOptionPane.showMessageDialog(parent, "Done");
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
}
