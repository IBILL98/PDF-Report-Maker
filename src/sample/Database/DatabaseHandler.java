package sample.Database;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import sample.model.Admin;
import sample.model.Employee;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Optional;


public class DatabaseHandler extends Configs {

    private DatabaseHandler databaseHandler;
    private JPanel pane;

    Connection dbConnection;
    public Connection getDbConnection() throws ClassNotFoundException, SQLException {

        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?useSSL=false";
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser , dbPass);

        return dbConnection;
    }
    //Add
    public void addEmployee(Employee employee){
        String insert = "INSERT INTO "+Const.EMPLOYEE_TABLE+"("+Const.EMPLOYEE_NAME+","+Const.EMPLOYEE_LASTNAME
                +","+Const.EMPLOYEE_USERNAME+","
                +Const.EMPLOYEE_LEVEL+","+Const.EMPLOYEE_PASSWORD+","+Const.EMPLOYEE_WORK+")"+"VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);

            preparedStatement.setString(1,employee.getName());
            preparedStatement.setString(2,employee.getLastName());
            preparedStatement.setString(3,employee.getUsername());
            preparedStatement.setString(4,employee.getLevel());
            preparedStatement.setString(5,employee.getPassword());
            preparedStatement.setString(6,employee.getWork());

            preparedStatement.executeUpdate();
            done();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    //Read Employee
    public ResultSet getEmployee(Employee employee){
        ResultSet resultSet = null;

        if(!employee.getUsername().equals("") || !employee.getPassword().equals("")){
            String query = "SELECT * FROM "+ Const.EMPLOYEE_TABLE + " WHERE "
                    +Const.EMPLOYEE_USERNAME + "=?"+" AND "
                    +Const.EMPLOYEE_PASSWORD + "=?";

            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
                preparedStatement.setString(1,employee.getUsername());
                preparedStatement.setString(2,employee.getPassword());

                resultSet = preparedStatement.executeQuery();

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }else{
            System.out.println("please entere your correct infos");
        }
        return resultSet;
    }


    public void addAdmin(Admin admin) {

        String insert = "INSERT INTO "+Const.ADMINS_TABLE+"("+Const.ADMINS_NAME+","+Const.ADMINS_LASTNAME
                +","+Const.ADMINS_USERNAME+","
                +Const.ADMINS_PASSWORD+")"+"VALUES(?,?,?,?)";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);

            preparedStatement.setString(1,admin.getName());
            preparedStatement.setString(2,admin.getLastName());
            preparedStatement.setString(3,admin.getUsername());
            preparedStatement.setString(4,admin.getPassword());

            preparedStatement.executeUpdate();
            done();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }





    public ResultSet getEmployeeByUsername(Employee employee){

        ResultSet resultSet = null;

        if(!employee.getUsername().equals("")){
            String query = "SELECT * FROM "+ Const.EMPLOYEE_TABLE + " WHERE "
                    +Const.EMPLOYEE_USERNAME + "=?";
            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
                preparedStatement.setString(1,employee.getUsername());
                resultSet = preparedStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else{
        System.out.println("please entere your correct infos");
        }
        return resultSet;
    }


    //UPDATE `my_database`.`employee` SET `LastName` = 'sssss' WHERE  (`Username` = 'Username12');
    //Update
    public void editEmployeeWindow(ResultSet employeeRow) throws SQLException {
        databaseHandler = new DatabaseHandler();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Select your Info");
        alert.setHeaderText("Which Information would you like to edit");
        alert.setContentText("when you edit you cant have your old infos back");

        ButtonType buttonTypeOne = new ButtonType("Name");
        ButtonType buttonTypeTwo = new ButtonType("LastName");
        ButtonType buttonTypeThree = new ButtonType("Level");
        ButtonType buttonTypeFour = new ButtonType("Password");
        ButtonType buttonTypeFive = new ButtonType("Work");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree,buttonTypeFour,buttonTypeFive, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            // ... Edit Name
            String Name = showNewInfos(employeeRow,"Name");
            String update = "UPDATE " + Const.EMPLOYEE_TABLE + " SET " + Const.EMPLOYEE_NAME + "=?" +  " WHERE "
                    +Const.EMPLOYEE_USERNAME + "=?";
            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(update);

                preparedStatement.setString(1,Name);
                preparedStatement.setString(2,employeeRow.getString("Username"));

                preparedStatement.executeUpdate();
                done();

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else if (result.get() == buttonTypeTwo) {
            // ... Edite LastName
            String LastName = showNewInfos(employeeRow,"LastName");
            String update = "UPDATE " + Const.EMPLOYEE_TABLE + " SET " + Const.EMPLOYEE_LASTNAME + "=?" +  " WHERE "
                    +Const.EMPLOYEE_USERNAME + "=?";


            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(update);

                preparedStatement.setString(1,LastName);
                preparedStatement.setString(2,employeeRow.getString("Username"));

                preparedStatement.executeUpdate();
                done();

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else if (result.get() == buttonTypeThree) {
            // ... user chose "Three"
            String Level = showNewInfos(employeeRow,"Level");
            String update = "UPDATE " + Const.EMPLOYEE_TABLE + " SET " + Const.EMPLOYEE_LEVEL + "=?" +  " WHERE "
                    +Const.EMPLOYEE_USERNAME + "=?";
            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(update);

                preparedStatement.setString(1,Level);
                preparedStatement.setString(2,employeeRow.getString("Username"));

                preparedStatement.executeUpdate();
                done();

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else if (result.get() == buttonTypeFour) {
            //
            String Password = showNewInfos(employeeRow,"Password");
            String update = "UPDATE " + Const.EMPLOYEE_TABLE + " SET " + Const.EMPLOYEE_PASSWORD + "=?" +  " WHERE "
                    +Const.EMPLOYEE_USERNAME + "=?";
            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(update);

                preparedStatement.setString(1,Password);
                preparedStatement.setString(2,employeeRow.getString("Username"));

                preparedStatement.executeUpdate();
                done();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else if (result.get() == buttonTypeFive) {
            String Work = showNewInfos(employeeRow,"Work");
            String update = "UPDATE " + Const.EMPLOYEE_TABLE + " SET " + Const.EMPLOYEE_WORK + "=?" +  " WHERE "
                    +Const.EMPLOYEE_USERNAME + "=?";
            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(update);

                preparedStatement.setString(1,Work);
                preparedStatement.setString(2,employeeRow.getString("Username"));

                preparedStatement.executeUpdate();
                done();

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else {
            // ... user chose CANCEL or closed the dialog
        }


    }
    public String showNewInfos(ResultSet employeeRow,String target) throws SQLException {
        String currentname = employeeRow.getString(target);
        JFrame frame = new JFrame();
        Object result = JOptionPane.showInputDialog(frame, "Your current "+ currentname + " is : " + employeeRow.getString(target)+" Entere the new value");
        return (String) result;
    }


    public void done(){
        Frame parent = new JFrame();
        JOptionPane.showMessageDialog(parent, "Done");
    }











    //Delete

    //DELETE FROM `my_database`.`companies` WHERE (`Username` = 'Username1');
    public void deleteEmployee(Employee employee){
        String delete = "DELETE FROM "+Const.EMPLOYEE_TABLE+" WHERE "+"(" +Const.EMPLOYEE_USERNAME +" =?"+ ")";
        try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(delete);
                preparedStatement.setString(1,employee.getUsername());

            preparedStatement.executeUpdate();
            done();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
