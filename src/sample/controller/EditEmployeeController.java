package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.TextInputDialog;
import sample.Animations.Shaker;
import sample.Database.DatabaseHandler;
import sample.model.Employee;

import javax.swing.text.html.ImageView;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class EditEmployeeController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton viewEmployeeBottun;

    @FXML
    private JFXTextField editEmployeeUsername;

    @FXML
    private ImageView editback;

    private DatabaseHandler databaseHandler;

    @FXML
    void initialize() {
        viewEmployeeBottun.setOnAction(actionEvent -> {
            editEmployee();
            });
        }

    private void editEmployee(){
        String Username = editEmployeeUsername.getText();
        Employee employee = new Employee(Username);
        ResultSet employeeRow = databaseHandler.getEmployeeByUsername(employee);
        int counter = 0;
        try {
            while(employeeRow.next()) {
                counter++;
            }
            if (counter == 1) {
                editEmployeeWindow(employeeRow);

            }else{
                Shaker UsernameShaker = new Shaker(editEmployeeUsername);
                UsernameShaker.shake();
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    private void editEmployeeWindow(ResultSet employewRow){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Edit this Infos");
        dialog.setHeaderText("Look, a Text Input Dialog");
        dialog.setContentText("Please enter your name:");

// Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            System.out.println("Your name: " + result.get());
        }

// The Java 8 way to get the response value (with lambda expression).
        result.ifPresent(name -> System.out.println("Your name: " + name));


    }
}
