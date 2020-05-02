package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.Database.Const;
import sample.Database.DatabaseHandler;
import sample.model.Employee;

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
    private JFXButton employeesAddButton;

    @FXML
    private JFXButton employeesEditButton;

    @FXML
    private JFXButton employeesRemoveButton;

    @FXML
    private ImageView deleteBack;


    private ObservableList<Employee> employees = FXCollections.observableArrayList();
    DatabaseHandler databaseHandler = new DatabaseHandler();



    @FXML
    void initialize() {
        //employeeTable.getSelectionModel().cellSelectionEnabledProperty().set(true);
        viewAllEmployee();
        employeesRemoveButton.setOnAction(actionEvent -> {
            deleteEmployees();
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




    public void deleteEmployees(){
        Employee employee = employeeTable.getSelectionModel().getSelectedItem();
        employeeTable.setEditable(true);
        //System.out.println(employee.getId());
        String delete = "DELETE FROM "+Const.EMPLOYEE_TABLE+" WHERE "+"(" +Const.EMPLOYEE_ID +" =?"+ ")";
        try {
            PreparedStatement preparedStatement = databaseHandler.getDbConnection().prepareStatement(delete);
            preparedStatement.setString(1,String.valueOf(employee.getId()));

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Are you sure you ?");
            alert.setContentText("You will delete "+employee.getName()+" "+employee.getLastName()+" From your employee list");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                // ... user choose OK
                System.out.println("yes");
                preparedStatement.executeUpdate();
                int index = employees.indexOf(employee);
                employeeTable.getItems().remove(index);
            }else {
                // ... user choose CANCEL or closed the dialog
                System.out.println("cancle");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }







}
