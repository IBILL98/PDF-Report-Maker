package sample.controller;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import sample.Database.DatabaseHandler;
import sample.model.Company;
import sample.model.Employee;

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

    DatabaseHandler databaseHandler = new DatabaseHandler();

    Company company = new Company();

    ObservableList operators = FXCollections.observableArrayList();
    ObservableList Approver = FXCollections.observableArrayList();
    ObservableList Rater = FXCollections.observableArrayList();



    @FXML
    void ccustomerdefine(ActionEvent event) {
        company.setName(pickerCompany.getSelectionModel().getSelectedItem());
        pickerCustomer.setText(databaseHandler.getCompany(company).getCustomer());
    }


    @FXML
    void initialize() {
        pickerCompany.setItems(databaseHandler.allCompaniesList());
        pickerEquipment.setItems(databaseHandler.allEquipment());

        for (int i=0;i<databaseHandler.viewAllEmployee().size();i++){
            Employee employee = (Employee) databaseHandler.viewAllEmployee().get(i);
            if (employee.getWork().equals("Operator")){
                String employeeFullName = employee.getName() +" "+ employee .getLastName();
                operators.add(employeeFullName);
                pickerOperator.setItems(operators);

            } if (employee.getWork().equals("Rater")){
                String employeeFullName = employee.getName() +" "+ employee .getLastName();
                Rater.add(employeeFullName);
                pickerRater.setItems(Rater);

            } if (employee.getWork().equals("Approver")){
                String employeeFullName = employee.getName() +" "+ employee .getLastName();
                Approver.add(employeeFullName);
                pickerApprover.setItems(Approver);
            }

        }
        //pickerOperator.setItems();





    }

}
