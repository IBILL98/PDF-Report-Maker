package sample.controller;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.Database.DatabaseHandler;
import sample.model.Company;
import sample.model.Employee;
import sample.model.Equipment;

import javax.swing.*;

public class PickerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView deleteBack;

    @FXML
    private ComboBox<String> pickerCompany;

    @FXML
    private Label pickerCustomer;

    @FXML
    private ComboBox<String> pickerEquipment;

    @FXML
    private ComboBox<String> pickerOperator;

    @FXML
    private ComboBox<String> pickerRater;

    @FXML
    private ComboBox<String> pickerApprover;

    @FXML
    private Button pickerNext;



    ObservableList operators = FXCollections.observableArrayList();
    ObservableList approvers = FXCollections.observableArrayList();
    ObservableList raters = FXCollections.observableArrayList();
    ObservableList allemployees = DatabaseHandler.viewAllEmployee();

    Company company = new Company();
    Equipment equipment = new Equipment();
    Employee operator = new Employee();
    Employee rater = new Employee();
    Employee approver = new Employee();



    @FXML
    void ccustomerdefine(ActionEvent event){
        company.setName(pickerCompany.getSelectionModel().getSelectedItem());
        company.setCustomer(DatabaseHandler.getCompany(company).getCustomer());
        pickerCustomer.setText(company.getCustomer());
    }



    Equipment pickedequipment = new Equipment();
    @FXML
    void equipmentDefine(ActionEvent event) throws SQLException {
        equipment.setName(pickerEquipment.getSelectionModel().getSelectedItem());
        ResultSet resultset = DatabaseHandler.getEquipment(equipment);
        while (resultset.next()){
            pickedequipment.setName(resultset.getString("Equipment"));
            pickedequipment.setId(Integer.parseInt(resultset.getString("Id")));
            pickedequipment.setPrivatePoleDistance((resultset.getString("PoleDistance")));
            pickedequipment.setMPCarrierMedium((resultset.getString("MPCarrierMedium")));
            pickedequipment.setMagTech((resultset.getString("MagTech")));
            pickedequipment.setUVLightIntensity((resultset.getString("UVLightIntensity")));
            pickedequipment.setDistanceOfLight((resultset.getString("DistanceOfLight")));
        }
    }


    Employee pickedoperator = new Employee();
    @FXML
    void operatorDefine(ActionEvent event) throws SQLException {
        String selected = pickerOperator.getSelectionModel().getSelectedItem();
        String id = idFinder(selected);
        operator.setId(Integer.parseInt(String.valueOf(id)));
        ResultSet resultset = DatabaseHandler.getEmployeeById(operator);
        while (resultset.next()){
            pickedoperator.setName(resultset.getString("Name"));
            pickedoperator.setLastName(resultset.getString("LastName"));
            pickedoperator.setLevel(Integer.parseInt(resultset.getString("Level")));
        }
    }

    Employee pickedrater = new Employee();
    @FXML
    void raterDefine(ActionEvent event) throws SQLException {
        String selected = pickerRater.getSelectionModel().getSelectedItem();
        String id = idFinder(selected);
        rater.setId(Integer.parseInt(id));
        ResultSet resultset = DatabaseHandler.getEmployeeById(rater);
        while (resultset.next()){
            pickedrater.setName(resultset.getString("Name"));
            pickedrater.setLastName(resultset.getString("LastName"));
            pickedrater.setLevel(Integer.parseInt(resultset.getString("Level")));
        }
    }

    Employee pickedapprover = new Employee();
    @FXML
    void approverDefine(ActionEvent event) throws SQLException {
        String selected = pickerApprover.getSelectionModel().getSelectedItem();
        String id = idFinder(selected);
        approver.setId(Integer.parseInt(id));
        ResultSet resultset = DatabaseHandler.getEmployeeById(approver);
        while (resultset.next()){
            pickedapprover.setName(resultset.getString("Name"));
            pickedapprover.setLastName(resultset.getString("LastName"));
            pickedapprover.setLevel(Integer.parseInt(resultset.getString("Level")));
        }
    }
    String idFinder(String selected){
        selected = selected.trim();
        String[] infos = selected.split(" ");
        String id = infos[infos.length-1];
        return id;
    }


    @FXML
    void initialize() {
        pickerCompany.setItems(DatabaseHandler.allCompaniesList());
        pickerEquipment.setItems(DatabaseHandler.allEquipment());

        for (int i=0;i<allemployees.size();i++){
            Employee employee = (Employee) allemployees.get(i);
            if (employee.getWork().equals("Operator")){
                String employeeFullName = employee.getName() +" "+ employee .getLastName() + " " +employee.getId();
                operators.add(employeeFullName);
                pickerOperator.setItems(operators);

            } if (employee.getWork().equals("Rater")){
                String employeeFullName = employee.getName() +" "+ employee .getLastName()+ " " +employee.getId();
                raters.add(employeeFullName);
                pickerRater.setItems(raters);

            } if (employee.getWork().equals("Approver")){
                String employeeFullName = employee.getName() +" "+ employee .getLastName()+ " " +employee.getId();
                approvers.add(employeeFullName);
                pickerApprover.setItems(approvers);
            }
        }

        pickerNext.setOnAction(event -> {
            if (pickerCompany.getValue() == null ||pickerApprover.getValue() == null||pickerRater.getValue() == null||pickerOperator.getValue() == null||pickerEquipment.getValue() == null){
                Frame parent = new JFrame();
                JOptionPane.showMessageDialog(parent, "Select all informations");

            }else{

                pickerNext.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/view/createreport.fxml"));
                try {
                    loader.load();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                CreatReportController controller = loader.getController();
                controller.autoPicked(company,pickedrater,pickedapprover,pickedoperator,pickedequipment);
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }
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
}
