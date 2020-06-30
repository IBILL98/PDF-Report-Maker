package sample.controller;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.*;
import javafx.scene.image.ImageView;

import javafx.scene.input.KeyEvent;
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
import java.sql.SQLException;
import java.time.LocalDate;
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
    private TableColumn<DatePicker, String> employeesDateColumn;

    @FXML
    private JFXButton employeesEditButton;

    @FXML
    private JFXButton employeesRemoveButton;

    @FXML
    private TextField employeesSearchText;

    @FXML
    private JFXButton employeesDone;

    @FXML
    private ImageView deleteBack;

    ObservableList<String> options = FXCollections.observableArrayList("Operator", "Rater", "Approver");
    private ObservableList<Employee> employees = FXCollections.observableArrayList();

    @FXML
    void searhEmployee(KeyEvent event) {
        if (!employeesSearchText.getText().equals("")){
            employeesIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            employeesNameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
            employeesLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("LastName"));
            employeesLevelColumn.setCellValueFactory(new PropertyValueFactory<>("Level"));
            employeesWorkColumn.setCellValueFactory(new PropertyValueFactory<>("Work"));
            employeesDateColumn.setCellValueFactory(new PropertyValueFactory<DatePicker, String>("Cdate"));
            employeeTable.setItems(DatabaseHandler.searchemployeeList(employeesSearchText.getText()));

        }else {
            viewElements();
        }
    }


    @FXML
    void initialize() {
        viewElements();
        employeesRemoveButton.setOnAction(event -> {
            if (employeesDone.isVisible()){
                Frame parent = new JFrame();
                JOptionPane.showMessageDialog(parent, "Finish your editing first please");
            }else {
                deleteEmployees();
            }
        });
        employeesEditButton.setOnAction(event -> {
            editEmployees();
        });

        employeesDone.setOnAction(event -> {
            employeeTable.getSelectionModel().cellSelectionEnabledProperty().set(false);
            employeesDone.setVisible(false);
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

    public void viewElements() {
        employeesIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        employeesNameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        employeesLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        employeesLevelColumn.setCellValueFactory(new PropertyValueFactory<>("Level"));
        employeesWorkColumn.setCellValueFactory(new PropertyValueFactory<>("Work"));
        employeesDateColumn.setCellValueFactory(new PropertyValueFactory<DatePicker, String>("Cdate"));
        employeeTable.setItems(DatabaseHandler.viewAllEmployee());
    }

    public void deleteEmployees() {
        Employee employee = employeeTable.getSelectionModel().getSelectedItem();
        employeeTable.setEditable(true);
        String delete = "DELETE FROM " + Const.EMPLOYEE_TABLE + " WHERE " + "(" + Const.EMPLOYEE_ID + " =?" + ")";
        try {
            PreparedStatement preparedStatement = DatabaseHandler.getDbConnection().prepareStatement(delete);
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
        }
    }
    public void editEmployees() {
        employeesDone.setVisible(true);
        employeeTable.setEditable(true);
        employeeTable.getSelectionModel().cellSelectionEnabledProperty().set(true);

        employeesNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        employeesNameColumn.setOnEditCommit(employeeStringCellEditEvent -> {
            Employee employee = employeeTable.getSelectionModel().getSelectedItem();
            employee.setName(employeeStringCellEditEvent.getNewValue());
            String update = "UPDATE " + Const.EMPLOYEE_TABLE + " SET " + Const.EMPLOYEE_NAME + "=?" + " WHERE " + Const.EMPLOYEE_ID + "=?";
            try {
                PreparedStatement preparedStatement = DatabaseHandler.getDbConnection().prepareStatement(update);
                preparedStatement.setString(1, employee.getName());
                preparedStatement.setString(2, String.valueOf(employee.getId()));
                preparedStatement.executeUpdate();
                Frame parent = new JFrame();
                JOptionPane.showMessageDialog(parent, "Done");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        employeesLastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        employeesLastNameColumn.setOnEditCommit(employeeStringCellEditEvent -> {
            Employee employee = employeeTable.getSelectionModel().getSelectedItem();
            employee.setLastName(employeeStringCellEditEvent.getNewValue());
            String update = "UPDATE " + Const.EMPLOYEE_TABLE + " SET " + Const.EMPLOYEE_LASTNAME + "=?" + " WHERE " + Const.EMPLOYEE_ID + "=?";
            try {
                PreparedStatement preparedStatement = DatabaseHandler.getDbConnection().prepareStatement(update);
                preparedStatement.setString(1, employee.getLastName());
                preparedStatement.setString(2, String.valueOf(employee.getId()));
                preparedStatement.executeUpdate();
                Frame parent = new JFrame();
                JOptionPane.showMessageDialog(parent, "Done");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        employeesLevelColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        employeesLevelColumn.setOnEditCommit(employeeStringCellEditEvent -> {
            Employee employee = employeeTable.getSelectionModel().getSelectedItem();
            employee.setLevel(employeeStringCellEditEvent.getNewValue());
            String update = "UPDATE " + Const.EMPLOYEE_TABLE + " SET " + Const.EMPLOYEE_LEVEL + "=?" + " WHERE " + Const.EMPLOYEE_ID + "=?";
            try {
                PreparedStatement preparedStatement = DatabaseHandler.getDbConnection().prepareStatement(update);
                preparedStatement.setString(1, String.valueOf(employee.getLevel()));
                preparedStatement.setString(2, String.valueOf(employee.getId()));
                preparedStatement.executeUpdate();
                Frame parent = new JFrame();
                JOptionPane.showMessageDialog(parent, "Done");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        employeesWorkColumn.setCellFactory(ComboBoxTableCell.forTableColumn(options));
        employeesWorkColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Employee, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Employee, String> event) {
                Employee employee = employeeTable.getSelectionModel().getSelectedItem();
                String s = event.getNewValue();
                if(s.equals("Approver")){
                    employee.setWork("Approver");
                }else if(s.equals("Rater")){
                    employee.setWork("Rater");
                }else if(s.equals("Operator")){
                    employee.setWork("Operator");
                }

                String update = "UPDATE " + Const.EMPLOYEE_TABLE + " SET " + Const.EMPLOYEE_WORK + "=?" + " WHERE " + Const.EMPLOYEE_ID + "=?";
                try {
                    PreparedStatement preparedStatement = DatabaseHandler.getDbConnection().prepareStatement(update);
                    preparedStatement.setString(1, employee.getWork());
                    preparedStatement.setString(2, String.valueOf(employee.getId()));
                    preparedStatement.executeUpdate();
                    Frame parent = new JFrame();
                    JOptionPane.showMessageDialog(parent, "Done");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });




    }
}