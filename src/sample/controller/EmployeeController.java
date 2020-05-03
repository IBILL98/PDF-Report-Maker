package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import sample.Database.Const;
import sample.Database.DatabaseHandler;
import sample.model.Employee;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;


public class EmployeeController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Employee> employeeTable;

    @FXML
    private TableColumn<Employee, Integer> employeesIdColumn;

    @FXML
    private TableColumn<Employee, String> employeesNameColumn;

    @FXML
    private TableColumn<Employee, String> employeesLastNameColumn;

    @FXML
    private TableColumn<Employee, Integer> employeesLevelColumn;

    @FXML
    private TableColumn<Employee, String> employeesWorkColumn;

    @FXML
    private JFXButton employeesEditButton;

    @FXML
    private JFXButton employeesRemoveButton;

    @FXML
    private JFXButton employeesApplyButton;

    @FXML
    private ImageView deleteBack;

    private ObservableList<Employee> employees = FXCollections.observableArrayList();
    DatabaseHandler databaseHandler = new DatabaseHandler();

    @FXML
    void initialize() {
        viewAllEmployee();
        employeesRemoveButton.setOnAction(actionEvent -> {
            deleteEmployees();
        });
        employeesEditButton.setOnAction(actionEvent -> {
            editEmployees();
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
    public void viewAllEmployee() {
        ResultSet resultSet = null;
        String query = "SELECT * FROM " + Const.EMPLOYEE_TABLE;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = databaseHandler.getDbConnection().prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("Name"));
                employee.setLastName(resultSet.getString("LastName"));
                employee.setLevel(resultSet.getInt("Level"));
                employee.setWork(resultSet.getString("Work"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        employeesIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        employeesNameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        employeesLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        employeesLevelColumn.setCellValueFactory(new PropertyValueFactory<>("Level"));
        employeesWorkColumn.setCellValueFactory(new PropertyValueFactory<>("Work"));
        employeeTable.setItems(employees);

    }
    public void deleteEmployees() {
        Employee employee = employeeTable.getSelectionModel().getSelectedItem();
        employeeTable.setEditable(true);
        //System.out.println(employee.getId());
        String delete = "DELETE FROM " + Const.EMPLOYEE_TABLE + " WHERE " + "(" + Const.EMPLOYEE_ID + " =?" + ")";
        try {
            PreparedStatement preparedStatement = databaseHandler.getDbConnection().prepareStatement(delete);
            preparedStatement.setString(1, String.valueOf(employee.getId()));

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Are you sure you ?");
            alert.setContentText("You will delete " + employee.getName() + " " + employee.getLastName() + " From your employee list");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                // ... user choose OK
                System.out.println("yes");
                preparedStatement.executeUpdate();
                int index = employees.indexOf(employee);
                employeeTable.getItems().remove(index);
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
    public void editEmployees() {
        employeeTable.setEditable(true);
        Employee employee = employeeTable.getSelectionModel().getSelectedItem();
        employeeTable.getSelectionModel().cellSelectionEnabledProperty().set(true);
        employeesNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        employeesNameColumn.setOnEditCommit(employeeStringCellEditEvent -> {
            employee.setName(employeeStringCellEditEvent.getNewValue());
            String update = "UPDATE " + Const.EMPLOYEE_TABLE + " SET " + Const.EMPLOYEE_NAME + "=?" +  " WHERE " +Const.EMPLOYEE_ID + "=?";
            try {
                PreparedStatement preparedStatement = databaseHandler.getDbConnection().prepareStatement(update);
                preparedStatement.setString(1,employee.getName());
                preparedStatement.setString(2,String.valueOf(employee.getId()));
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        employeesLastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        employeesLastNameColumn.setOnEditCommit(employeeStringCellEditEvent -> {
            employee.setLastName(employeeStringCellEditEvent.getNewValue());
            String update = "UPDATE " + Const.EMPLOYEE_TABLE + " SET " + Const.EMPLOYEE_LASTNAME + "=?" +  " WHERE " +Const.EMPLOYEE_ID + "=?";
            try {
                PreparedStatement preparedStatement = databaseHandler.getDbConnection().prepareStatement(update);
                preparedStatement.setString(1,employee.getLastName());
                preparedStatement.setString(2,String.valueOf(employee.getId()));
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        employeesLevelColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        employeesLevelColumn.setOnEditCommit(employeeStringCellEditEvent -> {
            employee.setLevel(employeeStringCellEditEvent.getNewValue());
            String update = "UPDATE " + Const.EMPLOYEE_TABLE + " SET " + Const.EMPLOYEE_LEVEL + "=?" +  " WHERE " +Const.EMPLOYEE_ID + "=?";
            try {
                PreparedStatement preparedStatement = databaseHandler.getDbConnection().prepareStatement(update);
                preparedStatement.setString(1, String.valueOf(employee.getLevel()));
                preparedStatement.setString(2,String.valueOf(employee.getId()));
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        employeesWorkColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        employeesWorkColumn.setOnEditCommit(employeeStringCellEditEvent -> {
            employee.setWork(employeeStringCellEditEvent.getNewValue());
            if (employee.getWork().equals("Approver")||employee.getWork().equals("Rater")||employee.getWork().equals("Operator")){
                String update = "UPDATE " + Const.EMPLOYEE_TABLE + " SET " + Const.EMPLOYEE_WORK + "=?" +  " WHERE " +Const.EMPLOYEE_ID + "=?";
                try {
                    PreparedStatement preparedStatement = databaseHandler.getDbConnection().prepareStatement(update);
                    preparedStatement.setString(1, employee.getWork());
                    preparedStatement.setString(2,String.valueOf(employee.getId()));
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }else{
                Frame parent = new JFrame();
                JOptionPane.showMessageDialog(parent, "Please Entere a valid Work(Approver),(Rater),(Operator)");
            }
        });
    }
}